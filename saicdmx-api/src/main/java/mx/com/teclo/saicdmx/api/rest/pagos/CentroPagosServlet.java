package mx.com.teclo.saicdmx.api.rest.pagos;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import mx.com.teclo.saicdmx.negocio.service.pagos.ConsultaCentroPagosService;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos.CentroPagosProcedureMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.empleados.EmpleadosVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.CentroPagosValidaVO;
import mx.com.teclo.siidf.centrodepagos.mit.vo.Consulta;

@WebServlet("/servlets/centroPagos")
public class CentroPagosServlet extends HttpServlet {

	private static final Logger logger = LoggerFactory.getLogger(CentroPagosServlet.class);
    private WebApplicationContext springContext;
	private static final long serialVersionUID = 1L;
	private static final int PROCESO_TERMINADO = 1;
    private static final int PROCESO_EN_CURSO = 0;
    private static final String PAGO_HANDHELD = "USERTRX";
    private static final String PAGO_ORIGEN_HH = "H";
    private static final String PAGO_ORIGEN_WEB = "W";
    @Autowired
	@Qualifier("consultaCentroPagosService")
    private ConsultaCentroPagosService consultaCentroPagosService;
    @Autowired
    private CentroPagosProcedureMyBatisDAO centroPagosProcedureMyBatisDAO;

    public CentroPagosServlet() {
      }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            procesaPagosHHSinProcesar();
            centroPagosProcedureMyBatisDAO.crearUltimaConsultaCtroPagos();
            procesaPagosHHCentroPagosEnCurso();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private synchronized void procesaPagosHHSinProcesar() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.add(Calendar.DATE, -1);
        consultaCentroDePagos(formatter.format(calendar.getTime()));
        Date fechaActual = new Date(System.currentTimeMillis());
        consultaCentroDePagos(formatter.format(fechaActual));
        

    }

    private void consultaCentroDePagos(String fechaActual) {
 		logger.info("Ejecutando metodo consultaCentroDePagos [fecha] [{}]", fechaActual);

        CentroPagosValidaVO centroPagosValidaVO;
        boolean existeReferencia;
        List<Consulta> listaReferencias =  consultaCentroPagosService.obtenerReferenciasPagosHandheld(fechaActual);

        if (!listaReferencias.isEmpty()) {
            for (Consulta transaccion : listaReferencias) {
                existeReferencia = centroPagosProcedureMyBatisDAO.existeNCIEnCtroPagosValida(transaccion.getNbReference());
                if (!existeReferencia) {
                	centroPagosValidaVO = procesaCentroPagosValida(transaccion.getNbReference());
                    //SE COLOCA LA FECHA EN QUE SE REALIZO EL PAGO
                	centroPagosValidaVO.setFechaPagoCtroPagos(transaccion.getDateBank());
                    //SE COLOCA EL TIPO DE PAGO (DEPOSITOS O HANDHELD)
                	centroPagosValidaVO.setPagoOrigen(transaccion.getUserTransaction().equalsIgnoreCase(PAGO_HANDHELD)  ? PAGO_ORIGEN_HH : PAGO_ORIGEN_WEB);
                    validaCentroPagosValida(centroPagosValidaVO);
                    centroPagosProcedureMyBatisDAO.crearRegistroCtroPagosValida(centroPagosValidaVO);
                    centroPagosValidaVO.setId(centroPagosProcedureMyBatisDAO.getCtroPagosId(transaccion.getNbReference()));
                    centroPagosProcedureMyBatisDAO.crearRegistroCtroPagosHist(centroPagosValidaVO);
                }
            }
        }

    }

    private void procesaPagosHHCentroPagosEnCurso() {
    	
 		logger.info("Ejecutando metodo procesaPagosHHCentroPagosEnCurso");
 		
        List<String> listaReferencias = centroPagosProcedureMyBatisDAO.obtenerTodasReferenciasEnCurso();
        CentroPagosValidaVO centroPagosValidaVO;
        if (!listaReferencias.isEmpty()) {
            for (String referencia : listaReferencias) {
            	centroPagosValidaVO = procesaCentroPagosValida(referencia);
                validaCentroPagosValida(centroPagosValidaVO);
                centroPagosProcedureMyBatisDAO.actualizarRegistroCtroPagosValida(centroPagosValidaVO);
                centroPagosValidaVO.setId( centroPagosProcedureMyBatisDAO.getCtroPagosId(referencia));
                centroPagosProcedureMyBatisDAO.crearRegistroCtroPagosHist(centroPagosValidaVO);
            }
        }
    }

    private CentroPagosValidaVO procesaCentroPagosValida(String nci) {
    	CentroPagosValidaVO centroPagosValidaVO;
        EmpleadosVO empleadoVO;
        if (centroPagosProcedureMyBatisDAO.existeInfraccion(nci)) {
        	centroPagosValidaVO =  centroPagosProcedureMyBatisDAO.getPagoDetalleTransaccion(nci);
            centroPagosValidaVO.setExisteInfraccion(true);
            if (centroPagosValidaVO.isExistePago()) {
            	centroPagosValidaVO.setExisteDetalleCargos(
            			centroPagosProcedureMyBatisDAO.existeDetalleCargos(centroPagosValidaVO.getPagoId(),
                        		centroPagosValidaVO.getCajaId()));
            	centroPagosValidaVO.setExisteDetallePagos(
            			centroPagosProcedureMyBatisDAO.existeDetallePagos(centroPagosValidaVO.getPagoId(),
                        		centroPagosValidaVO.getCajaId()));

            }
            empleadoVO = centroPagosProcedureMyBatisDAO.getEmpleadoPorNci(nci);
            if (empleadoVO == null) {
            	empleadoVO = getEmpleadoVacio();
            }
            centroPagosValidaVO.setEmpId(empleadoVO.getEmpId().toString());
            centroPagosValidaVO.setEmpPlaca(empleadoVO.getEmpPlaca());
            centroPagosValidaVO.setEmpNombre(empleadoVO.getEmpNombre());

        } else {
        	centroPagosValidaVO = new CentroPagosValidaVO();
        	centroPagosValidaVO.setNci(nci);
        	empleadoVO = centroPagosProcedureMyBatisDAO.getEmpleadoInfoFoliosInfracciones(nci.substring(1, 10));
            if (empleadoVO == null) {
            	empleadoVO = getEmpleadoVacio();
            }

            centroPagosValidaVO.setEmpId(empleadoVO.getEmpId().toString());
            centroPagosValidaVO.setEmpPlaca(empleadoVO.getEmpPlaca());
            centroPagosValidaVO.setEmpNombre(empleadoVO.getEmpNombre());

        }

        return centroPagosValidaVO;
    }

    private EmpleadosVO getEmpleadoVacio() {
    	EmpleadosVO empleadosVO = new EmpleadosVO();
    	empleadosVO.setEmpId(new Long(0));
    	empleadosVO.setEmpNombre("");
    	empleadosVO.setEmpPlaca("");
        return empleadosVO;
    }

    private void validaCentroPagosValida( CentroPagosValidaVO centroPagosValidaVO ) {

        if (centroPagosValidaVO.isExisteInfraccion() && centroPagosValidaVO.isExistePago() && centroPagosValidaVO.isExisteDetalleCargos()
                && centroPagosValidaVO.isExisteDetallePagos() && centroPagosValidaVO.isExistePagoTransaccion()
                && centroPagosValidaVO.isExisteInfracTransaccion()) {
        	centroPagosValidaVO.setEstatusProceso(PROCESO_TERMINADO);
        } else {
        	centroPagosValidaVO.setEstatusProceso(PROCESO_EN_CURSO);
        }
    }
    
    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
        final AutowireCapableBeanFactory beanFactory = springContext.getAutowireCapableBeanFactory();
        beanFactory.autowireBean(this);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
