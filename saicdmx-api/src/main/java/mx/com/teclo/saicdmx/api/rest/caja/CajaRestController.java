package mx.com.teclo.saicdmx.api.rest.caja;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javassist.NotFoundException;
import mx.com.teclo.saicdmx.negocio.service.caja.CajaReporteService;
import mx.com.teclo.saicdmx.negocio.service.caja.CajaService;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasDepositosVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasModifVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasNuevoVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasUsuariosVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.ProcInformeCorteSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaSinCorteActVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaSinCorteHistVO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VConsultaUsuariosCajaVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

@RestController
public class CajaRestController {
	
	@Autowired
	private CajaService cajaService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	@Autowired
	private CajaReporteService cajaReporteService;
	
	@Autowired
	private ServletContext context;
	
	//private ByteArrayOutputStream reporte;
	
	//private ByteArrayOutputStream reportePartidas;
	
	//private ByteArrayOutputStream reporteInfracciones;
	
	@RequestMapping(value = "/historicoTotalInfracciones", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('CAJA_SIN_CORTE_HISTORICO_TOTAL_INFRACCIONES')")
	public ResponseEntity<List<VCajaSinCorteHistVO>> historicoTotalInfracciones(@RequestBody VCajaSinCorteHistVO vCajaSinCorteHistVO){
		List<VCajaSinCorteHistVO> lVCajaSinCorteHistVO = cajaService.getVCajaSinCorteHistVOTotalPagos(vCajaSinCorteHistVO);
		if(lVCajaSinCorteHistVO == null)
			return new ResponseEntity<List<VCajaSinCorteHistVO>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<VCajaSinCorteHistVO>>(lVCajaSinCorteHistVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/historicoDetalleInfracciones", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('CAJA_SIN_CORTE_HISTORICO_DETALLE_INFRACCIONES')")
	public ResponseEntity<List<VCajaSinCorteHistVO>> historicoDetalleInfracciones(@RequestBody VCajaSinCorteHistVO vCajaSinCorteHistVO){
		List<VCajaSinCorteHistVO> lVCajaSinCorteHistVO = cajaService.getVCajaSinCorteHistVODetalle(vCajaSinCorteHistVO);
		if(lVCajaSinCorteHistVO == null)
			return new ResponseEntity<List<VCajaSinCorteHistVO>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<VCajaSinCorteHistVO>>(lVCajaSinCorteHistVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/historicoDetalleInfraccion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CAJA_SIN_CORTE_HISTORICO_DETALLE_INFRACCION')")
	public ResponseEntity<List<VCajaSinCorteHistVO>> historicoDetalleInfraccion(
			@RequestParam(value = "caja") String caja, @RequestParam(value = "emp") String emp, @RequestParam(value = "fecha") String fecha){
		List<VCajaSinCorteHistVO> lVCajaSinCorteHistVO = cajaService.getVCajaSinCorteHistVODetalleByParams(caja, emp, fecha);
		return new ResponseEntity<List<VCajaSinCorteHistVO>>(lVCajaSinCorteHistVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/actualTotalInfracciones", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CAJA_SIN_CORTE_ACTUAL_TOTAL_INFRACCIONES')")
	public ResponseEntity<List<VCajaSinCorteActVO>>getVCajaSinCorteActVOTotal(
			@RequestParam(value = "tipoBusqueda") String tipoBusqueda,
			@RequestParam(value = "parametroBusqueda") String parametroBusqueda) throws NotFoundException {
		
		List<VCajaSinCorteActVO> lVCajaSinCorteActVO = cajaService.getVCajaSinCorteActVOTotalByParams(tipoBusqueda, parametroBusqueda);
		if (lVCajaSinCorteActVO == null)
			return new ResponseEntity<List<VCajaSinCorteActVO>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<VCajaSinCorteActVO>>(lVCajaSinCorteActVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/actualDetalleInfraccion", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CAJA_SIN_CORTE_ACTUAL_DETALLE_INFRACCION')")
	public ResponseEntity<List<VCajaSinCorteActVO>> ActualDetalleInfraccion(
			@RequestParam(value = "caja") String caja, @RequestParam(value = "emp") String emp){
		List<VCajaSinCorteActVO> lVCajaSinCorteActVO = cajaService.getVCajaSinCorteActVODetalleByParams(caja, emp);
		return new ResponseEntity<List<VCajaSinCorteActVO>>(lVCajaSinCorteActVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/actualDetalleInfracciones", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CAJA_SIN_CORTE_ACTUAL_DETALLE_INFRACCIONES')")
	public ResponseEntity<List<VCajaSinCorteActVO>>getVCajaSinCorteActVODetalles(
			@RequestParam(value = "tipoBusqueda") String tipoBusqueda,
			@RequestParam(value = "parametroBusqueda") String parametroBusqueda) throws NotFoundException {
		
		List<VCajaSinCorteActVO> lVCajaSinCorteActVO = cajaService.getVCajaSinCorteActVODetallesByParams(tipoBusqueda, parametroBusqueda);
		if (lVCajaSinCorteActVO == null)
			return new ResponseEntity<List<VCajaSinCorteActVO>>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<VCajaSinCorteActVO>>(lVCajaSinCorteActVO , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/reporteCorteSinCajaHistTot", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CAJA_SIN_CORTE_HIST_TOTAL_INFRAC_REPORTE')")
    public ResponseEntity<byte[]> generarReporteExcel()  {
    	
    	String filename 				   = "Caja_Sin_Corte.xls" ;
    	ByteArrayOutputStream outputStream = cajaService.generarReporteExcel();
    	final byte[] bytes				   = outputStream.toByteArray();

    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.ms-exce"));
    	headers.add("Content-Disposition", "attachment; filename=" + filename);
    	headers.add("filename",   filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        headers.setContentLength(bytes.length);
        
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        
        return response;
    }

    @RequestMapping(value = "/consultaCajaUsuarios", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('CONSULTA_CAJA_USUARIOS')")
	public ResponseEntity<List<VCajaConsultaVO>>getConsultaCajaByUsuario
		(@RequestParam(value="param") String param, @RequestParam(value="tipoBusqueda") String tipoBusqueda) throws NotFoundException {
	    	
			List<VCajaConsultaVO> LVCajaConsultaVO = new ArrayList<VCajaConsultaVO>();
			if(tipoBusqueda.equals("")){
				UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
				EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
				LVCajaConsultaVO = cajaService.getConsultaCajaByUsuario(String.valueOf(empleado.getEmpId()));
			}
			else
				LVCajaConsultaVO = cajaService.getConsultaCajaByUsuario(param,tipoBusqueda);
			if (CollectionUtils.isEmpty(LVCajaConsultaVO))
				return new ResponseEntity<List<VCajaConsultaVO>>(HttpStatus.NOT_FOUND);
			return new ResponseEntity<List<VCajaConsultaVO>>(LVCajaConsultaVO , HttpStatus.OK);
	}
    
    @RequestMapping(value = "/ProcInformeCorte", method = RequestMethod.GET)
	@PreAuthorize("hasAnyAuthority('SP_PROC_INFORME_CORTE')")//R
	public ResponseEntity<ProcInformeCorteSPVO>procInformeCorte
	(@RequestParam(value = "cajaId") Integer cajaId, @RequestParam(value = "tipoCorte") String tipoCorte, @RequestParam(value = "fechaCorte") String fechaCorte,
			UriComponentsBuilder ucBuilder)throws BusinessException, FileNotFoundException {
    	
    	ProcInformeCorteSPVO ProcInformeCorteSPVO = cajaService.procInformeCorte(cajaId, tipoCorte, fechaCorte);
    	//String rutaArchivo = context.getRealPath("/WEB-INF/jasper/corte_caja/impresioncorte.jasper");
    	//reporte = cajaReporteService.impresionCorte(rutaArchivo, ProcInformeCorteSPVO);

		return new ResponseEntity<ProcInformeCorteSPVO>(ProcInformeCorteSPVO, HttpStatus.CREATED);
	}
    
    @RequestMapping(value = "/ProcGuardarCorte", method = RequestMethod.POST)
	@PreAuthorize("hasAnyAuthority('SP_PROC_GUARDAR_CORTE')")//R
	public ResponseEntity<ProcInformeCorteSPVO>procInformeCorte(@RequestBody ProcInformeCorteSPVO procInformeCorteSPVO) throws FileNotFoundException, ParseException{
    	UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		procInformeCorteSPVO.setEmpId(empleado.getEmpId().intValue());
		cajaService.procGuardarCorte(procInformeCorteSPVO);
		
		String rutaArchivo = context.getRealPath("/WEB-INF/jasper/corte_caja/impresioncorte.jasper");
		//reporteNew =
	    cajaReporteService.impresionCorte(rutaArchivo, procInformeCorteSPVO);
    	
		return new ResponseEntity<ProcInformeCorteSPVO>(procInformeCorteSPVO, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = "ImpresionCorteEfecutado", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('IMPRESION_CORTE_EFECTUADO')")
    public ResponseEntity<byte[]> generarPDFCorteEfectuado(@RequestBody ProcInformeCorteSPVO procInformeCorteSPVO) throws NotFoundException, IOException{
    	
    	UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		procInformeCorteSPVO.setEmpId(empleado.getEmpId().intValue());		
		String rutaArchivo = context.getRealPath("/WEB-INF/jasper/corte_caja/impresioncorte.jasper");
		ByteArrayOutputStream reporteNew = null;
		reporteNew = cajaReporteService.impresionCorte(rutaArchivo, procInformeCorteSPVO);

    	String filename = "Corte_Efectuado.pdf";
    	
    	byte[] bytes = reporteNew.toByteArray();
    	
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename =" + filename);
		headers.add("filename", filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);
		
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "ImpresionPartidasCorteEfecutado", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('IMPRESION_PARTIDAS_CORTE_EFECTUADO')")
    public ResponseEntity<byte[]> generarPDFCorteEfectuadoPartida(
    		@RequestParam(value="cajaId") String cajaId, @RequestParam(value="numCaja") String numCaja, @RequestParam(value="fecha") String fecha)
    				throws NotFoundException, IOException{
    	String filename = fecha.equals("") ? "Corte_Efectuado_Partidas.pdf" : "Corte_Consulta_Partidas.pdf";
    	
    	ByteArrayOutputStream reportePartidasNew=null;
    	reportePartidasNew = cajaReporteService.impresionRenglonesCortePartida(cajaId, numCaja, fecha);
    	byte[] bytes = reportePartidasNew.toByteArray();
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename =" + filename);
		headers.add("filename", filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "ImpresionInfraccionesCorteEfecutado", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('IMPRESION_INFRACCIONES_CORTE_EFECTUADO')")
    public ResponseEntity<byte[]> generarPDFCorteEfectuadoInfracciones(
    		@RequestParam(value="cajaId") String cajaId, @RequestParam(value="numCaja") String numCaja, @RequestParam(value="fecha") String fecha) 
    				throws NotFoundException, IOException{
    	String filename = fecha.equals("") ? "Corte_Efectuado_Infracciones.pdf" : "Corte_Consulta_Infracciones.pdf";
    	
    	ByteArrayOutputStream reporteInfraccionesNew=null;
    	reporteInfraccionesNew = cajaReporteService.impresionRenglonesCorteInfraccion(cajaId, numCaja, fecha);
    	byte[] bytes = reporteInfraccionesNew.toByteArray();
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename =" + filename);
		headers.add("filename", filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "ImpresionPartidasTarCorteEfecutado", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('IMPRESION_PARTIDASTAR_CORTE_EFECTUADO')")
    public ResponseEntity<byte[]> generarPDFCorteEfectuadoPartidaTar(
    		@RequestParam(value="cajaId") String cajaId, @RequestParam(value="numCaja") String numCaja, @RequestParam(value="fecha") String fecha, @RequestParam(value="cajaCod") String cajaCod) 
    				throws NotFoundException, IOException{
    	String filename = fecha.equals("") ? "Corte_Efectuado_PartidasTar.pdf" : "Corte_Consulta_PartidasTar.pdf";
    	
    	ByteArrayOutputStream reporteInfraccionesNew=null;
    	reporteInfraccionesNew = cajaReporteService.impresionRenglonesCortePartidaTarjeta(cajaId, numCaja, fecha, cajaCod);
    	byte[] bytes = reporteInfraccionesNew.toByteArray();
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename =" + filename);
		headers.add("filename", filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/ConsultaCajaFechaPorParam", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('CONSULTA_CAJA_FECHA_POR_PARAM')")
	public ResponseEntity<List<FilterValuesVO>> buscarCajaPorFechaYParam(
			@RequestParam(value="param")String param, @RequestParam(value="tipoBusqueda")String tipoBusqueda) throws NotFoundException {
		return new ResponseEntity<List<FilterValuesVO>>(cajaService.buscarCajaPorTipoParam(param, tipoBusqueda), HttpStatus.OK);
	}
    
    @RequestMapping(value = "ImpresionCorteConsulta", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('IMPRESION_CORTE_CONSULTA')")
    public ResponseEntity<byte[]> generarPDFCorteConsulta(@RequestBody ProcInformeCorteSPVO procInformeCorteSPVO) throws NotFoundException, IOException{
    	
    	String filename = "Corte_Consulta.pdf";
    	
    	String rutaArchivo = context.getRealPath("/WEB-INF/jasper/corte_caja/impresioncorte_consulta.jasper");
    	ByteArrayOutputStream reporteNew = null;
    	reporteNew = cajaReporteService.impresionCorte(rutaArchivo, procInformeCorteSPVO);
		
    	byte[] bytes = reporteNew.toByteArray();
    	
    	HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/pdf"));
		headers.add("Content-Disposition", "attachmnt; filename =" + filename);
		headers.add("filename", filename);
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.setContentLength(bytes.length);
		
		return new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
    }
    
    @RequestMapping(value = "ModificaCajaCod", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('EJECUCION_SP_ADMIN_CAJAS_NUEVO')")
    public ResponseEntity<AdminCajasNuevoVO> procAdminCajasNuevoSPVO
    	(@RequestBody AdminCajasNuevoVO adminCajasNuevoVO) throws NotFoundException, IOException, ParseException{
    	UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		adminCajasNuevoVO.setpModificadoPor(empleado.getEmpId());
		adminCajasNuevoVO = cajaService.procAdminCajasNuevoSPVO(adminCajasNuevoVO);
		return new ResponseEntity<AdminCajasNuevoVO>(adminCajasNuevoVO,HttpStatus.OK);
    }
    
    @RequestMapping(value = "ConsutlaUsuariosCaja", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('CONSULTA_USUARIOS_CAJA')")
    public ResponseEntity<VConsultaUsuariosCajaVO> procAdminCajasNuevoSPVO
    	(@RequestParam(value="placaUsuario") String placaUsuario) throws NotFoundException, IOException{
    	VConsultaUsuariosCajaVO vConsultaUsuariosCajaVO = cajaService.vConsultaUsuariosCajaByEmpPlaca(placaUsuario);
    	if(vConsultaUsuariosCajaVO == null)
    		return new ResponseEntity<VConsultaUsuariosCajaVO>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<VConsultaUsuariosCajaVO>(vConsultaUsuariosCajaVO,HttpStatus.OK);
    }
    
    @RequestMapping(value = "adminSPCajasUsuarios", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('EJECUCION_SP_ADMIN_CAJAS_USUARIOS')")
    public ResponseEntity<AdminCajasUsuariosVO> procAdminCajasUsuarios
    	(@RequestBody AdminCajasUsuariosVO adminCajasUsuariosVO) throws ParseException{
    	UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		adminCajasUsuariosVO.setpModificadoPor(empleado.getEmpId());
		adminCajasUsuariosVO = cajaService.procAdminCajasUsuarios(adminCajasUsuariosVO);
		return new ResponseEntity<AdminCajasUsuariosVO>(adminCajasUsuariosVO, HttpStatus.OK);
    }
    
    @RequestMapping(value = "adminSPCajasDepositos", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('EJECUCION_SP_ADMIN_CAJAS_DEPOSITOS')")
    public ResponseEntity<AdminCajasDepositosVO> procAdminCajasDepositos
    	(@RequestBody AdminCajasDepositosVO adminCajasDepositosVO) throws Exception{
    	UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		adminCajasDepositosVO.setpModificadoPor(empleado.getEmpId());
		adminCajasDepositosVO = cajaService.procAdminCajasDepositos(adminCajasDepositosVO);
		return new ResponseEntity<AdminCajasDepositosVO>(adminCajasDepositosVO, HttpStatus.OK);
    }
    
    @RequestMapping(value = "adminSPCajasModif", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('EJECUCION_SP_ADMIN_CAJAS_MODIF')")
    public ResponseEntity<AdminCajasModifVO> procAdminCajasModif
    	(@RequestBody AdminCajasModifVO adminCajasModifVO) throws ParseException{
    	UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empleado = empleadoService.getEmpleadoById(usuario.getId());
		adminCajasModifVO.setpModificadoPor(empleado.getEmpId());
		adminCajasModifVO = cajaService.procAdminCajasModif(adminCajasModifVO);
		return new ResponseEntity<AdminCajasModifVO>(adminCajasModifVO, HttpStatus.OK);
    }
}