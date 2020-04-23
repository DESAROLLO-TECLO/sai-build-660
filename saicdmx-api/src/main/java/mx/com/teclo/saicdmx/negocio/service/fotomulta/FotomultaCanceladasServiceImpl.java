package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.empleado.EmpleadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.fotomulta.FotomultaCancelacionDeteccionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta.FotomultaDeteccionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta.FotomultaMotivoCancelacionDTO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ComboFechasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ComboMotivoCancelVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FechasCancelacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaCanceladasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ParametrosCancelacionVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;

@Service
public class FotomultaCanceladasServiceImpl implements FotomultaCanceladasService{

	private RutinasTiempoImpl rutinasTiempo = new RutinasTiempoImpl();
	
	@Autowired
	private FotomultaCancelacionDeteccionDAO fotomultaDeteccionDAO;
	
	@Autowired
	private EmpleadoDAO empleadoDAO;
		
	private List<FotomultaCanceladasVO> listaCanceladasVO;
	
	private String fechaInicio;

	private String fechaFinal;


	
	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public List<ComboMotivoCancelVO> obtenerCatMotivoCancel() {
		
		List<ComboMotivoCancelVO> listaComboVO = new ArrayList<ComboMotivoCancelVO>();
		List<FotomultaMotivoCancelacionDTO> listaMotivoCancel = fotomultaDeteccionDAO.getCatMotivoCancelacion();
		
		for (FotomultaMotivoCancelacionDTO fotomultaMotivoCancelacionDTO : listaMotivoCancel) {			
			ComboMotivoCancelVO comboMotivoCancelVO = new ComboMotivoCancelVO();		
			comboMotivoCancelVO.setMotivoId(fotomultaMotivoCancelacionDTO.getMotivoId());
			comboMotivoCancelVO.setMotivoDescripcion(fotomultaMotivoCancelacionDTO.getMotivoDescripcion());			
			listaComboVO.add(comboMotivoCancelVO);
		}
		return listaComboVO;
	}


	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public List<ComboFechasVO> obtenerFechasCancelacion() {

		List<FechasCancelacionVO> listaFechas = fotomultaDeteccionDAO.obtenerFechasCancelacion();
		List<ComboFechasVO> listaCombo = new ArrayList<ComboFechasVO>();
	
		for (FechasCancelacionVO fecha : listaFechas) {			
			ComboFechasVO comboFechasVO = new ComboFechasVO();
			comboFechasVO.setFechaValue("01/"+fecha.getMes()+"/20"+fecha.getAnio());
			comboFechasVO.setFechaFormat(getMonth(Integer.parseInt(fecha.getMes())).concat("/20"+fecha.getAnio()));
			listaCombo.add(comboFechasVO);
		}
	
		return listaCombo;
	}

	public String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month - 1];
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public  Map<String, List<FotomultaCanceladasVO>> consultarDeteccionesCanceladas(ParametrosCancelacionVO parametrosCancelacionVO) {
		
		 	setFechaInicio(parametrosCancelacionVO.getFechaInicioString()==null?"null":parametrosCancelacionVO.getFechaInicioString());
		    setFechaFinal(parametrosCancelacionVO.getFechaFinString()==null?"null":parametrosCancelacionVO.getFechaFinString());
		    
		    if(!parametrosCancelacionVO.getFechaInicioString().equalsIgnoreCase("null")){
		    	parametrosCancelacionVO.setFechaInicio(rutinasTiempo.convertirStringDate(parametrosCancelacionVO.getFechaInicioString()));
		    	parametrosCancelacionVO.setFechaFin(rutinasTiempo.convertirStringDate(parametrosCancelacionVO.getFechaFinString()));  
		    }else{
		    	parametrosCancelacionVO.setFechaFin(new Date());
		    }
			Map<String, List<FotomultaCanceladasVO>> mapaCanceladas = new HashMap<String, List<FotomultaCanceladasVO>>();
			List<FotomultaDeteccionDTO> listaDetecciones = fotomultaDeteccionDAO.consultarDeteccionesCanceladas(parametrosCancelacionVO);
			mapaCanceladas=clasificaListaDetecciones(listaDetecciones);
			return mapaCanceladas;
	}
	

	public Map<String, List<FotomultaCanceladasVO>> clasificaListaDetecciones(List<FotomultaDeteccionDTO> listaDTO) {

		List<FotomultaCanceladasVO> listaVO = new ArrayList<FotomultaCanceladasVO>();
		Map<String, List<FotomultaCanceladasVO>> hashMap = new HashMap<String, List<FotomultaCanceladasVO>>();

		for (FotomultaDeteccionDTO fotomultaDeteccionDTO : listaDTO) {

			String key = fotomultaDeteccionDTO.getFotomultaTipoRadar().getNombre().replaceAll("\\s", "");
			FotomultaCanceladasVO canceladoVO = new FotomultaCanceladasVO();

			if (hashMap.get(key) == null) {
				canceladoVO = getCanceldasVO(fotomultaDeteccionDTO);
				hashMap.put(key.replaceAll("\\s", ""), new ArrayList<FotomultaCanceladasVO>());
			} else {
				canceladoVO = getCanceldasVO(fotomultaDeteccionDTO);
			}
			listaVO.add(canceladoVO);
			setListaCanceladasVO(listaVO);
			hashMap.get(key).add(canceladoVO);
		}

		return hashMap;
	}
	
	
	
	public FotomultaCanceladasVO getCanceldasVO(FotomultaDeteccionDTO fotomultaDeteccionDTO) {

		String modificadoPor;
		
		if(fotomultaDeteccionDTO.getModificadoPor()==0){
			modificadoPor = "WEB SERVICE";
		}else{
			EmpleadosDTO empleado = empleadoDAO.findOne(fotomultaDeteccionDTO.getModificadoPor());
			modificadoPor = empleado.getEmpNombre()+" "+empleado.getEmpApePaterno()+" "+empleado.getEmpApeMaterno();
		}
		
		FotomultaCanceladasVO canceladaVO = new FotomultaCanceladasVO();
		String fechaCancel = fotomultaDeteccionDTO.getFechaCancelacion()==null?"":rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy", fotomultaDeteccionDTO.getFechaCancelacion());
		String fechaCreacion =fotomultaDeteccionDTO.getFechaCreacion()==null?"":rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy", fotomultaDeteccionDTO.getFechaCreacion());
		String fechaValidacion = fotomultaDeteccionDTO.getFechaValidacion()==null?"":rutinasTiempo.getStringDateFromFormta("dd/MM/yyyy", fotomultaDeteccionDTO.getFechaValidacion());
		
		canceladaVO.setNombre(fotomultaDeteccionDTO.getNombre());
		canceladaVO.setCodigo(fotomultaDeteccionDTO.getFotomultaTipoRadar() == null ? "SIN NOMBRE"
				: fotomultaDeteccionDTO.getFotomultaTipoRadar().getNombre());
		canceladaVO.setOficialNombre(fotomultaDeteccionDTO.getOficialNombre());
		canceladaVO.setOficialPlaca(fotomultaDeteccionDTO.getOficialPlaca());
		canceladaVO.setPlaca(fotomultaDeteccionDTO.getPlaca());
		canceladaVO.setFecha(fotomultaDeteccionDTO.getFecha());
		canceladaVO.setHora(fotomultaDeteccionDTO.getHora());
		canceladaVO.setTskuid(fotomultaDeteccionDTO.getTdskuid());
		canceladaVO.setOrigenPlaca(fotomultaDeteccionDTO.getOrigenPlaca() == 0 ? "CDMX" : "Foránea");
		canceladaVO.setModificadoPor(modificadoPor);
		canceladaVO.setFechaCancelacion(fechaCancel);
		canceladaVO.setFechaCreacion(fechaCreacion);
		canceladaVO.setFechaValidacion(fechaValidacion);
		canceladaVO.setMotivoCancelacion(fotomultaDeteccionDTO.getMotivoCancelacion()==null?"Sin comentarios":fotomultaDeteccionDTO.getMotivoCancelacion());
		
		return canceladaVO;
	}

	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte[] descargaExcelDetecciones() {

		byte[] arregloBytes = null;
		String fechaInicial = getFechaInicio().equals("todas") ? "null" : getFechaInicio();
		String fechaFinal = fechaInicial.equals("null") ? "null" :getFechaFinal();

		ReporteDeteccionesCanceladasExcel reporte = new ReporteDeteccionesCanceladasExcel();

		arregloBytes = reporte.generarReporteExcel(getListaCanceladasVO(), 
												"Reporte Detecciones Canceladas", 
												fechaInicial, 
												fechaFinal);
		return arregloBytes;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}


	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	
	public Date obtenerFechaFin(){
		 Calendar calendar = Calendar.getInstance();
	     calendar.setTime(new Date()); 
	     return calendar.getTime();
	}


	public List<FotomultaCanceladasVO> getListaCanceladasVO() {
		return listaCanceladasVO;
	}


	public void setListaCanceladasVO(List<FotomultaCanceladasVO> listaCanceladasVO) {
		this.listaCanceladasVO = listaCanceladasVO;
	}


	public String getFechaFinal() {
		return fechaFinal;
	}


	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	
}
