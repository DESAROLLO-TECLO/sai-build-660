package mx.com.teclo.saicdmx.negocio.service.fotomulta;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.fotomulta.FotomultaCancelacionDeteccionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fotomulta.FotomultaDeteccionDTO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaCatComboFechasVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaMarcadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.FotomultaParamProcesamientoVO;
import mx.com.teclo.saicdmx.persistencia.vo.fotomulta.ParametrosCancelacionVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;

/**
 * 
 * @author Javier Flores
 *
 */
@Service
public class FotomultaMarcadoServiceImpl implements FotomultaMarcadoService {

	@Autowired
	private FotomultaCancelacionDeteccionDAO fotomultaDeteccionDAO;
	//private List<FotomultaMarcadoVO> listaRegistrosReporte;	
	private FotomultaParamProcesamientoVO datosVO = new FotomultaParamProcesamientoVO();
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public Map<String, List<FotomultaMarcadoVO>>[] obtenerDeteccionesParaCancel(String fecha, int tipoRadar,
			int origenPlaca) {

		ParametrosCancelacionVO parametrosCancelacionVO = new ParametrosCancelacionVO();
		Map<String, List<FotomultaMarcadoVO>>[] arregloListas = new HashMap[2];

		if (fecha.equals("todas")) {
			datosVO.setFechaInicio(fecha);
		} else {
			datosVO.setFechaInicio("01/" + fecha);
		}
		
		parametrosCancelacionVO.setTipoRadar(tipoRadar);
		parametrosCancelacionVO.setOrigenPlaca(origenPlaca);
		parametrosCancelacionVO.setFecha(datosVO.getFechaInicio());
		parametrosCancelacionVO.setFechaFin(obtenerFechaFin());
		
		
		List<FotomultaDeteccionDTO> listaDeteccionesDTO = fotomultaDeteccionDAO.getDeteccionesPorCancelar(parametrosCancelacionVO);
		List<FotomultaDeteccionDTO> listaPrevalidacionesDTO = fotomultaDeteccionDAO.getPrevalidacionesPorCancelar(parametrosCancelacionVO);

		arregloListas[0] = clasificaListaDetecciones(listaDeteccionesDTO, "listaDetecciones");
		arregloListas[1] = clasificaListaDetecciones(listaPrevalidacionesDTO, "listaPrevalidaciones");

		datosVO.setMapDetecciones(arregloListas[0]);
		datosVO.setMapPrevalidaciones(arregloListas[1]);

		return arregloListas;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public List<FotomultaCatComboFechasVO> obtieneListaFechas() {

		List<FotomultaCatComboFechasVO> listaFechas = fotomultaDeteccionDAO.getFechaDetecciones(obtenerFechaFin());
		
		for (FotomultaCatComboFechasVO fotomultaCatComboFechasVO : listaFechas) {
			int mes = Integer.parseInt(fotomultaCatComboFechasVO.getMes());
			String year = fotomultaCatComboFechasVO.getAnio();
			String fechaFormat = getMonth(mes).substring(0, 1).toUpperCase() + getMonth(mes).substring(1);
			fotomultaCatComboFechasVO.setFechaFormat(year + "/" + fechaFormat);
			fotomultaCatComboFechasVO.setFechaValue(fotomultaCatComboFechasVO.getMes() + "/" + year);
		}
		return listaFechas;
	}

	public Map<String, List<FotomultaMarcadoVO>> clasificaListaDetecciones(List<FotomultaDeteccionDTO> listaDTO,
			String tipoLista) {

		List<FotomultaMarcadoVO> listaVO = new ArrayList<FotomultaMarcadoVO>();
		Map<String, List<FotomultaMarcadoVO>> hashMap = new HashMap<String, List<FotomultaMarcadoVO>>();

		for (FotomultaDeteccionDTO fotomultaDeteccionDTO : listaDTO) {

			String key = fotomultaDeteccionDTO.getFotomultaTipoRadar().getNombre().replaceAll("\\s", "");
			FotomultaMarcadoVO fotomultaMarcadoVO = new FotomultaMarcadoVO();

			if (hashMap.get(key) == null) {
				fotomultaMarcadoVO = setMarcadoVO(fotomultaDeteccionDTO);
				hashMap.put(key.replaceAll("\\s", ""), new ArrayList<FotomultaMarcadoVO>());
			} else {
				fotomultaMarcadoVO = setMarcadoVO(fotomultaDeteccionDTO);
			}
			listaVO.add(fotomultaMarcadoVO);
			hashMap.get(key).add(fotomultaMarcadoVO);
		}
		if (!listaVO.isEmpty() && tipoLista.equals("listaDetecciones")) {
			datosVO.setListaValidacionesVO(listaVO);
		} else {
			if (!listaVO.isEmpty() && tipoLista.equals("listaPrevalidaciones")) {
				datosVO.setListaPrevalidacionesVO(listaVO);
			}
		}
		return hashMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public long cancelarDetecciones(int tipoRadar, int origenPlaca, String motivoCancelacion,Long modificadoPor) {
	
		ParametrosCancelacionVO parametrosCancelacionVO = new ParametrosCancelacionVO();
		parametrosCancelacionVO.setTipoRadar(tipoRadar);
		parametrosCancelacionVO.setOrigenPlaca(origenPlaca);
		parametrosCancelacionVO.setFecha(datosVO.getFechaInicio());
		parametrosCancelacionVO.setMotivoCancelacion(motivoCancelacion);
		parametrosCancelacionVO.setModificadoPor(modificadoPor);	
		parametrosCancelacionVO.setFechaFin(obtenerFechaFin());
		
		long resultadoDetecciones = fotomultaDeteccionDAO.cancelarDetecciones(parametrosCancelacionVO);
		long resultadoPrevalidaciones = fotomultaDeteccionDAO.cancelarPrevalidaciones(parametrosCancelacionVO);
	  
		return resultadoDetecciones+resultadoPrevalidaciones;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public byte[] descargaExcelDeteccMarcado() {

		byte[] arregloBytes = null;
		String fechaInicio;
		
		if(datosVO.getFechaInicio().equals("todas")){
			fechaInicio="null";
		}else{
			String anio=datosVO.getFechaInicio().substring(6);
			String mes=getMonth(Integer.parseInt(datosVO.getFechaInicio().substring(3, 5)));
			fechaInicio = mes.substring(0,1).toUpperCase() + mes.substring(1)+"/"+anio;
		}
		ReporteMarcadoDeteccionesExcel reporte = new ReporteMarcadoDeteccionesExcel();
		arregloBytes = reporte.generarReporteExcel(datosVO.getListaRegistrosReporte(), "Reporte Cancelación de Detecciones", fechaInicio,
				"null");

		return arregloBytes;
	}

	public FotomultaMarcadoVO setMarcadoVO(FotomultaDeteccionDTO fotomultaDeteccionDTO) {

		RutinasTiempoImpl rutinasTiempoImpl = new RutinasTiempoImpl();
		FotomultaMarcadoVO fotomultaMarcadoVO = new FotomultaMarcadoVO();

		fotomultaMarcadoVO.setFotomultaId(fotomultaDeteccionDTO.getFotomultaId());
		fotomultaMarcadoVO.setNombre(fotomultaDeteccionDTO.getNombre());
		fotomultaMarcadoVO.setCodigo(fotomultaDeteccionDTO.getFotomultaTipoRadar() == null ? "SIN NOMBRE"
				: fotomultaDeteccionDTO.getFotomultaTipoRadar().getNombre());
		fotomultaMarcadoVO.setFecha(fotomultaDeteccionDTO.getFecha());
		fotomultaMarcadoVO
				.setFechaCreacion(rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy",fotomultaDeteccionDTO.getFechaCreacion()));
		fotomultaMarcadoVO.setFechaValidacion(fotomultaDeteccionDTO.getFechaValidacion() == null ? ""
				: rutinasTiempoImpl.getStringDateFromFormta("dd/MM/yyyy",fotomultaDeteccionDTO.getFechaValidacion()));
		fotomultaMarcadoVO.setHora(fotomultaDeteccionDTO.getHora());
		fotomultaMarcadoVO.setOficialNombre(fotomultaDeteccionDTO.getOficialNombre());
		fotomultaMarcadoVO.setOficialPlaca(fotomultaDeteccionDTO.getOficialPlaca());
		fotomultaMarcadoVO.setPlaca(fotomultaDeteccionDTO.getPlaca());
		fotomultaMarcadoVO.setTskuid(fotomultaDeteccionDTO.getTdskuid());
		fotomultaMarcadoVO.setOrigenPlaca(fotomultaDeteccionDTO.getOrigenPlaca() == 0 ? "CDMX" : "Foránea");
		return fotomultaMarcadoVO;
	}

	
	private Date obtenerFechaFin(){
		 Calendar calendar = Calendar.getInstance();
	     calendar.setTime(new Date()); 
	     int diaActual = calendar.get(Calendar.DATE);	      
	     if(diaActual>10){
	         calendar.add(Calendar.DAY_OF_YEAR, -diaActual);
	     }else{	             
	         calendar.add(Calendar.DAY_OF_YEAR, -diaActual);
	         calendar.add(Calendar.MONTH, -1);  
	      }
	     return calendar.getTime();
	}

	@Override
	public List<FotomultaMarcadoVO> obtenerValidadas() {
		datosVO.setListaRegistrosReporte(datosVO.getListaValidacionesVO());
		return datosVO.getListaValidacionesVO();
	}
	
	@Override
	public List<FotomultaMarcadoVO> obtenerPrevalidaciones() {
		datosVO.setListaRegistrosReporte(datosVO.getListaPrevalidacionesVO());
		return datosVO.getListaPrevalidacionesVO();
	}

	public String getMonth(int mes) {
		return new RutinasTiempoImpl().getNombreMes(mes);
	}

	
}
