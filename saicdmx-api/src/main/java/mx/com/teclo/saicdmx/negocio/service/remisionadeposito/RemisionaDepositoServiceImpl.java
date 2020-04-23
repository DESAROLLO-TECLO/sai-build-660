package mx.com.teclo.saicdmx.negocio.service.remisionadeposito;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.bitacora.cambios.concesionaria.BitTrBitacConcesionaria;
import mx.com.teclo.saicdmx.bitacora.cambios.infracciones.BitTrBitUpInfrac;
import mx.com.teclo.saicdmx.bitacora.cambios.ingresos.BitTrgIngresoBitNuevo;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.pdf.remisionadeposito.RecibosConInfraccion;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.infracciones.InfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito.AltaIngresoInfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito.BuscarConInfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito.BuscarOficialPorPlacaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito.BuscarUpdPorIdMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito.ComplementoInfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.remisionadeposito.RecibosRemisionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VSSPInfracConsGralFVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ArticuloSancionVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ConInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.GruaConceVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.IngresoInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.OficialIngresoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ReciboArrastreVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.ReciboIngresoVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.SecAgrupArrasEmpVO;
import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.VehiculoTipoColorMarcaVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service("remisionaDepositoService")
public class RemisionaDepositoServiceImpl  implements RemisionaDepositoService {

	@Autowired
	private BuscarConInfraccionMyBatisDAO conInfracionDAO;
	
	@Autowired
	private BuscarUpdPorIdMyBatisDAO updIdDAO;
	
	@Autowired
	private AltaIngresoInfraccionMyBatisDAO altaIngresoInfracDAO;
	
	@Autowired
	private BuscarOficialPorPlacaMyBatisDAO oficialPorPlacaDAO;
	
	@Autowired
	private ComplementoInfraccionMyBatisDAO complementoInfracDAO;
	
	@Autowired
	private RecibosRemisionMyBatisDAO recibosRemisionMyBatis;
	
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	
	@Autowired
	private BitTrBitUpInfrac bitTrBitUpInfrac;
	
	@Autowired
	private BitTrgIngresoBitNuevo bitTrgIngresoBitNuevo;
	
	@Autowired
	private BitTrBitacConcesionaria bitTrBitacConcesionaria;
	
	@Autowired
	private InfraccionMyBatisDAO infraccionDAO;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Override
	@Transactional
	public List<ConInfraccionVO> buscarConInfraccion(String opcion, String valor) {
		return conInfracionDAO.buscarConInfraccion(opcion,valor);
	}
	
	
	@Override
	public IngresoInfraccionVO buscarUpdPorId(String infrac_num_ctrl) {
		return updIdDAO.buscarUpdPorId(infrac_num_ctrl);
	}
	
	@Override
	public IngresoInfraccionVO altaIngresoInfraccion(IngresoInfraccionVO ingresoInfracVO) throws ParseException{
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();    
		AltaInfraccionSPVO newInfraccionVO = new AltaInfraccionSPVO(); 
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		VSSPInfracConsGralFVO oldListaInfraccionesVO = new VSSPInfracConsGralFVO();
		oldListaInfraccionesVO.setFechaAutoriza(infraccionDAO.getFechAutoriza(ingresoInfracVO.getInfrac_num()));
		oldListaInfraccionesVO.setIdDeposito(infraccionDAO.getIdDep(ingresoInfracVO.getInfrac_num()));
		oldListaInfraccionesVO.setAutorizaId(infraccionDAO.getAutorizaId(ingresoInfracVO.getInfrac_num()));

		IngresoInfraccionVO registroVO = altaIngresoInfracDAO.altaIngresoInfraccion(ingresoInfracVO);
		try{
			altaIngresoInfracDAO.updateInventario(ingresoInfracVO);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		if(ingresoInfracVO != null){
			if(ingresoInfracVO.getResultado().equals("1")){
				newInfraccionVO.setP_modificado_por(Integer.valueOf(infraccionDAO.getAutorizaId(ingresoInfracVO.getInfrac_num())));
				newInfraccionVO.setP_dep_id(ingresoInfracVO.getDep_id());
				newInfraccionVO.setP_infrac_num_ctrl(ingresoInfracVO.getInfrac_num_ctrl());
				newInfraccionVO.setAutorizaId(ingresoInfracVO.getUsu_login());
				bitacoraCambiosVOs = bitTrBitUpInfrac.guardarCambiosBitacora(newInfraccionVO, oldListaInfraccionesVO);
				
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(11L);
				bitacoraCambiosVO.setConceptoId(ingresoInfracVO.getUsu_id() != null ? 1L : 2L);
				bitacoraCambiosVO.setValorOriginal(altaIngresoInfracDAO.getIngrResguardo(ingresoInfracVO.getInfrac_num()));
				bitacoraCambiosVO.setValorFinal(ingresoInfracVO.getDep_id().toString());
				bitacoraCambiosVO.setCreadoPor(
						ingresoInfracVO.getUsu_id() != null ? ingresoInfracVO.getUsu_id() : 0L );
				bitacoraCambiosVO.setIdRegistro(ingresoInfracVO.getInfrac_num());
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO);
				
				
				bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);

			}
		}
		return ingresoInfracVO;
	}
	
	@Override
	public IngresoInfraccionVO altaIngresoAdmin(IngresoInfraccionVO ingresoInfracVO){
		IngresoInfraccionVO registoAdminVO = altaIngresoInfracDAO.altaIngresoAdmin(ingresoInfracVO);
		return ingresoInfracVO;
	}
	
	
	@Override
	public OficialIngresoVO buscarOficialPorPlaca(String infrac_placa_empl) {
		return oficialPorPlacaDAO.buscarOficialPorPlaca(infrac_placa_empl);
	}
	
  // Reporte Resguardo
	@Override
	public ByteArrayOutputStream generaReporteResguardo(String infraccionNum, String rutaTotalArchivo) throws FileNotFoundException {
		ByteArrayOutputStream reporte = null;
		RecibosConInfraccion reciboResguardo = new RecibosConInfraccion();
		ReciboIngresoVO valoresIngresoVO = recibosRemisionMyBatis.buscarInfraccionResguardo(infraccionNum);
		List<String> inventarios = recibosRemisionMyBatis.buscarInventarios(infraccionNum);
		String listInventario = "";
		if(valoresIngresoVO != null){
			if(!inventarios.isEmpty()){
				for(int x = 0; x < inventarios.size(); x++){
					if((x+1) == inventarios.size()){
						listInventario = listInventario + inventarios.get(x);
					}else{
						listInventario = listInventario + inventarios.get(x)+", ";
					}
				}
			}
			reporte = reciboResguardo.generaReporteResguardoPDF(valoresIngresoVO,rutaTotalArchivo,listInventario);
		}
		return reporte;
	}
	
// Reporte Arrastre
	@Override
	public ByteArrayOutputStream generaReporteArrastre(String infracNum, String rutaTotalArchivo) throws FileNotFoundException {
		ByteArrayOutputStream reporte = null;
		RecibosConInfraccion reciboArrastre = new RecibosConInfraccion();
		ReciboArrastreVO valoresArrastreVO = recibosRemisionMyBatis.buscarInfraccionArrastre(infracNum);
		if(valoresArrastreVO != null){
			if(valoresArrastreVO.getGrua_id() == null){
				valoresArrastreVO.setGrua_id(0);
			}
			String dia = (new SimpleDateFormat("dd", new Locale("es", "mx"))).format(new Date()).toString();
			String operadorGrua = recibosRemisionMyBatis.buscaOperadorGrua(valoresArrastreVO.getGrua_id().toString(), dia);
			if(operadorGrua == null){
				operadorGrua = "";
			}
			valoresArrastreVO.setInfrac_oper_grua(operadorGrua);
			reporte = reciboArrastre.generaReporteArrastrePDF(valoresArrastreVO,rutaTotalArchivo);
		}
		return reporte;
	}
		
	
	@Override
	public ArticuloSancionVO valorArticuloSancion(String infrac_num) {
		return complementoInfracDAO.valorArticuloSancion(infrac_num);
	}
	
	@Override
	public VehiculoTipoColorMarcaVO valorVehiculo(String infrac_num) {
		return complementoInfracDAO.valorVehiculo(infrac_num);
	}
	
	@Override
	public SecAgrupArrasEmpVO valorSecAgrupArrasEmp(String infrac_num) {
		return complementoInfracDAO.valorSecAgrupArrasEmp(infrac_num);
	}
	
	@Override
	public GruaConceVO valorGruaConce(String infrac_num) {
		return complementoInfracDAO.valorGruaConce(infrac_num);
	}
	
	@Override
	public List<ConInfraccionVO> buscarRemisiones(String opcion, String valor, Long emp_id) {
		 String depositoBusqueda = "0";
		 String empdepositoid = conInfracionDAO.buscarDepEmpId(emp_id);
		 if (empdepositoid != null) {
			 depositoBusqueda = String.valueOf(empdepositoid);
		 }
		return conInfracionDAO.buscarRemisiones(opcion,valor,depositoBusqueda);
	}
	
	@Override
	public IngresoInfraccionVO mostarDatosPorNCI(String infrac_num_ctrl) {
		return updIdDAO.mostarDatosPorNCI(infrac_num_ctrl);
	}
	
	@Override
	public IngresoInfraccionVO altaIngresoEmbargo(IngresoInfraccionVO ingresoInfracVO) throws ParseException{
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();    
		AltaInfraccionSPVO newInfraccionVO = new AltaInfraccionSPVO(); 
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		VSSPInfracConsGralFVO oldListaInfraccionesVO = new VSSPInfracConsGralFVO();
		GruaConceVO oldConcesionariaVO = new GruaConceVO();
		GruaConceVO newConcesionariaVO = new GruaConceVO();
		oldListaInfraccionesVO.setFechaAutoriza(infraccionDAO.getFechAutoriza(ingresoInfracVO.getInfrac_num())); //FechaAutoriza
		oldConcesionariaVO = altaIngresoInfracDAO.getAltaIngresoVO(ingresoInfracVO.getGrua_cod());
		
		IngresoInfraccionVO registroEmbargoVO = altaIngresoInfracDAO.altaIngresoEmbargo(ingresoInfracVO);
		try{
			altaIngresoInfracDAO.updateInventario(ingresoInfracVO);
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		newConcesionariaVO = altaIngresoInfracDAO.getAltaIngresoVO(ingresoInfracVO.getGrua_cod());
		
		if(ingresoInfracVO != null){
			if(ingresoInfracVO.getResultado().equals("1")){
				newInfraccionVO.setP_modificado_por(ingresoInfracVO.getEmp_id());
				newInfraccionVO.setP_infrac_num_ctrl(ingresoInfracVO.getInfrac_num());
				newInfraccionVO.setAutorizaId(ingresoInfracVO.getUsu_login());
				newInfraccionVO.setP_modificado_por(empleado.getEmpId().intValue());
				ingresoInfracVO.setEmp_id(empleado.getEmpId().intValue());
				ingresoInfracVO.setNum_resguardo(altaIngresoInfracDAO.getIngrResguardo(ingresoInfracVO.getInfrac_num()));
				
				if(ingresoInfracVO.getT_ingr_id().equals("01")) {	
					newConcesionariaVO.setModificadoPor(empleado.getEmpId());
					bitacoraCambiosVOs.addAll(bitTrBitacConcesionaria.guardarCambiosBitacora(newConcesionariaVO, oldConcesionariaVO));
				}
				
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(5L);
				bitacoraCambiosVO.setConceptoId(1L);
				bitacoraCambiosVO.setValorOriginal("");
				bitacoraCambiosVO.setValorFinal(ingresoInfracVO.getInfrac_num() != null ? ingresoInfracVO.getInfrac_num(): "");
				bitacoraCambiosVO.setCreadoPor(
						empleado.getEmpId() != null ? empleado.getEmpId() : 0L );
				bitacoraCambiosVO.setIdRegistro(ingresoInfracVO.getInfrac_num() != null ? ingresoInfracVO.getInfrac_num(): "");
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO);
				bitacoraCambiosVOs.addAll(bitTrgIngresoBitNuevo.guardarCambiosBitacora(ingresoInfracVO));
				bitacoraCambiosVOs.addAll(bitTrBitUpInfrac.guardarCambiosBitacora(newInfraccionVO, oldListaInfraccionesVO));	
			
			

				bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
			}
		}
				
		return ingresoInfracVO;
	}
	
	@Override
	public IngresoInfraccionVO buscarPorNCI(String infrac_num_ctrl) {
		return updIdDAO.buscarPorNCI(infrac_num_ctrl);
	}


	@Override
	public OficialIngresoVO buscarOficialPorPlacaId(String infrac_placa_empl) {
		return oficialPorPlacaDAO.buscarOficialPorPlacaId(infrac_placa_empl);
	}

	
}
