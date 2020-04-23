package mx.com.teclo.saicdmx.negocio.service.reportes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.PagoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.PagoDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias.GarantiaPagosProcedureMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.recibo.ReciboPagoVO;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *  Copyright (c) 2016, Teclo Mexicana.
 *  Descripcion					: Implementacion AcusePagoReciboServiceImpl
 *  
 *  Historial de Modificaciones	:
 *  Descripcion del Cambio		: Creacion
 *  @author 					: fjmb 
 *  @version 					: 1.0
 *  Fecha						: 29/Octubre/2016
 */

@Service("acusePagoReciboService")
public class AcusePagoReciboServiceImpl implements AcusePagoService {

	@Autowired
	private GarantiaPagosProcedureMyBatisDAO garantiaPagosProcedureMyBatis;
	@Autowired
	private PagoDAO pagoDAO;
	private ReciboPagoVO reciboPagoVO;
	private PagoDTO pagoDTO;
	@SuppressWarnings("rawtypes")
	private HashMap parametrosJasper;
	@Autowired
	private ServletContext context;
	private ByteArrayOutputStream reporte;

	@SuppressWarnings("unchecked")
 	@Override
	public ByteArrayOutputStream generarAcusePago(String infracNum) {
		reporte = new ByteArrayOutputStream();
		Long cajaId = consultarPago( infracNum ,"cajaId");
		Long pagId  = consultarPago( infracNum ,"pagId");
		reciboPagoVO = garantiaPagosProcedureMyBatis.getReciboPago(cajaId, pagId);
		
		this.validarCampos();
		this.generarParametrosReporte();
		
		if (!reciboPagoVO.getOEformaPago2().equalsIgnoreCase("") && !reciboPagoVO.getOEformaPago2().equalsIgnoreCase(" ")) {
			parametrosJasper.put("infracOEdescripcion", reciboPagoVO.getOEformaPago2());
			reciboPagoVO.setOEleyenda(	"El total del pago se\361alado \372nicamente considera el monto de los derechos por el servicio de gr\372a y almacenaje.");
			parametrosJasper.put("infracOELeyenda", reciboPagoVO.getOEleyenda());
		} else {
			parametrosJasper.put("infracOEdescripcion", reciboPagoVO.getOEformaPago2());
			parametrosJasper.put("infracOELeyenda", reciboPagoVO.getOEleyenda());
		}

		parametrosJasper.put("PathLogoSSP", context.getRealPath("images/head_infra.gif"));
		parametrosJasper.put("PathLogoPlaca", context.getRealPath("images/SSP_PLACA.gif"));
		String sRuta = context.getRealPath("/WEB-INF/reportes/impresionPagos.jasper");

		try {
			reporte.write(JasperRunManager.runReportToPdf(sRuta, parametrosJasper, new JREmptyDataSource()));
		} catch (IOException e) {
 			e.printStackTrace();
		} catch (JRException e) {
 			e.printStackTrace();
		}

		return reporte;
	}

	public void validarCampos() {
		String auxComa = null;

		if (reciboPagoVO.getInfrNumext() != null) {
			reciboPagoVO.setInfrNumext(" " + reciboPagoVO.getInfrNumext());
		}
		if ( reciboPagoVO.getInfrNumInt() != null) {
			reciboPagoVO.setInfrNumInt(" Int " + reciboPagoVO.getInfrNumInt());
		}
		if ( reciboPagoVO.getInfrCalle()!= null ||  reciboPagoVO.getInfrNumext() != null  ||  reciboPagoVO.getInfrNumInt()!=null) {
			auxComa = ", ";
		}
		if ( reciboPagoVO.getInfrCol() != null) {
			reciboPagoVO.setInfrCol(auxComa + reciboPagoVO.getInfrCol());
		}
		if ( reciboPagoVO.getInfrCalle() != null ||  reciboPagoVO.getInfrNumext() != null	||  reciboPagoVO.getInfrNumInt() != null ||  reciboPagoVO.getInfrCol() != null) {
			auxComa = ", ";
		} else {
			auxComa = "";
		}
		if ( reciboPagoVO.getInfrDel() != null) {
			reciboPagoVO.setInfrDel(auxComa + reciboPagoVO.getInfrDel());
		} 
		if ( reciboPagoVO.getInfrCalle()!= null ||  reciboPagoVO.getInfrNumext() != null	||  reciboPagoVO.getInfrNumInt() != null ||  reciboPagoVO.getInfrCol() != null
				||  reciboPagoVO.getInfrDel() != null) {
			auxComa = ", ";
		} else {
			auxComa = "";
		}
		if ( reciboPagoVO.getInfrEdo() != null) {
			reciboPagoVO.setInfrEdo(auxComa + reciboPagoVO.getInfrEdo());
		}
 		if (reciboPagoVO.getOEleyenda()== null || reciboPagoVO.getOEleyenda() == ""  ) {
			reciboPagoVO.setOEleyenda("");
		}
		if (reciboPagoVO.getTransReferencia()== null || reciboPagoVO.getTransReferencia() == ""  ) {
			reciboPagoVO.setTransReferencia("");
		}
 
		if (reciboPagoVO.getTransTarjeta()== null || reciboPagoVO.getTransTarjeta() == ""  ) {
			reciboPagoVO.setTransTarjeta("");
		}
		if (reciboPagoVO.getBanco()== null || reciboPagoVO.getBanco() == ""  ) {
			reciboPagoVO.setBanco("");
		}
		
		if (reciboPagoVO.getPagosMonto() == null || reciboPagoVO.getPagosMonto() == "" || reciboPagoVO.getPagosMonto().equalsIgnoreCase("0") ) {
			reciboPagoVO.setPagosMonto("0.00");
		}
		if (reciboPagoVO.getPagosReduccion() == null || reciboPagoVO.getPagosReduccion().equalsIgnoreCase("0") ||  reciboPagoVO.getPagosReduccion() == "") {
			reciboPagoVO.setPagosReduccion("0.00");
		}
		if (reciboPagoVO.getPagosActualizacion() == null	|| reciboPagoVO.getPagosActualizacion().equalsIgnoreCase("0") ||  reciboPagoVO.getPagosActualizacion() == "") {
			reciboPagoVO.setPagosActualizacion("0.00");
		}
		if ( reciboPagoVO.getPagosRecargos() == null || reciboPagoVO.getPagosRecargos().equalsIgnoreCase("0") || reciboPagoVO.getPagosRecargos() == "") {
			reciboPagoVO.setPagosRecargos("0.00");
		}
		if (reciboPagoVO.getPagosTotal() == null	|| reciboPagoVO.getPagosTotal().equalsIgnoreCase("0") ||  reciboPagoVO.getPagosTotal() == "") {
			reciboPagoVO.setPagosTotal("0.00");
		}
		if (reciboPagoVO.getPagosMontoDer() == null || reciboPagoVO.getPagosMontoDer().equalsIgnoreCase("0") ||  reciboPagoVO.getPagosMontoDer() == "") {
			reciboPagoVO.setPagosMontoDer("0.00");
		}
		if (reciboPagoVO.getPagosTotalPagos() == null || reciboPagoVO.getPagosTotalPagos().equalsIgnoreCase("0") ||  reciboPagoVO.getPagosTotalPagos() == "") {
			reciboPagoVO.setPagosTotalPagos("0.00");
		}
		if (reciboPagoVO.getDerechoPiso() == null || reciboPagoVO.getDerechoPiso().equalsIgnoreCase("0") ||  reciboPagoVO.getDerechoPiso() == "") {
			reciboPagoVO.setDerechoPiso("0.00");
		}
		if ( reciboPagoVO.getCandado() == null || reciboPagoVO.getCandado().equalsIgnoreCase("0") || reciboPagoVO.getCandado() == "") {
			reciboPagoVO.setCandado("0.00");
		}

	}

	@SuppressWarnings("unchecked")
	public void generarParametrosReporte() {
		parametrosJasper = new HashMap<>();
		parametrosJasper.put("infracNombre", reciboPagoVO.getInfracNombre());
		parametrosJasper.put("infracDomicilio", "PLAZA DE LA CONSTITUCION SIN No. COL. CENTRO C.P. 06080");
		parametrosJasper.put("infracRFC", "GDF9712054NA");
		parametrosJasper.put("pagosCajaCod", reciboPagoVO.getPagosCajaCod());
		parametrosJasper.put("pagosPartidaNo", reciboPagoVO.getPagosPartidaNo());
		parametrosJasper.put("infracDeposito", reciboPagoVO.getInfracDeposito());
		parametrosJasper.put("pagosPagFecha", reciboPagoVO.getPagosPagFecha());
		parametrosJasper.put("pagosIdPago", reciboPagoVO.getPagosIdPago());
		parametrosJasper.put("pagos.Monto", reciboPagoVO.getPagosMonto());
		parametrosJasper.put("pagosReduccion", reciboPagoVO.getPagosReduccion());
		parametrosJasper.put("pagosActualizacion", reciboPagoVO.getPagosActualizacion());
		parametrosJasper.put("pagosRecargos", reciboPagoVO.getPagosRecargos());
		parametrosJasper.put("pagosTotal", reciboPagoVO.getPagosTotal());
		parametrosJasper.put("pagosCapacidadArr", reciboPagoVO.getPagosCapacidadArr());
		parametrosJasper.put("pagosMontoDer", reciboPagoVO.getPagosMontoDer());
		parametrosJasper.put("pagosTotalPagos", reciboPagoVO.getPagosTotalPagos());
		parametrosJasper.put("transTarjeta","Tarjeta No. " + reciboPagoVO.getTransTarjeta() + " Banco: " + reciboPagoVO.getBanco());
		parametrosJasper.put("transReferencia", reciboPagoVO.getTransReferencia()==""?"":" Referencia: "+reciboPagoVO.getTransReferencia());
		parametrosJasper.put("infracNum", reciboPagoVO.getInfracNum());
		parametrosJasper.put("infracPlacas", reciboPagoVO.getInfracPlacas());
		parametrosJasper.put("infracAgente", reciboPagoVO.getInfracAgente());
		parametrosJasper.put("infracFecha", reciboPagoVO.getInfracFecha());
		parametrosJasper.put("artNum", reciboPagoVO.getArtNum());
		parametrosJasper.put("artFraccion", reciboPagoVO.getArtFraccion());
		parametrosJasper.put("artInciso", reciboPagoVO.getArtInciso());
		parametrosJasper.put("formaPago", reciboPagoVO.getFormaPago());
		parametrosJasper.put("pagosIdPago", reciboPagoVO.getIdPago());
		parametrosJasper.put("almacenaje_monto", reciboPagoVO.getDerechoPiso());
		parametrosJasper.put("dias_almacenaje", reciboPagoVO.getDiasPiso());
		parametrosJasper.put("candado", reciboPagoVO.getCandado());
	}
	
	public Long consultarPago(String infracNum,String campo){		
		return pagoDAO.consultaParamsPago(infracNum,campo);
	}

}


