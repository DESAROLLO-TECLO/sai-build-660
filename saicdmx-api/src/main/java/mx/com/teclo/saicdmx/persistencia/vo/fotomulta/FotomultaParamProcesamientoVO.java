package mx.com.teclo.saicdmx.persistencia.vo.fotomulta;

import java.util.List;
import java.util.Map;

public class FotomultaParamProcesamientoVO {
	
	private List<FotomultaMarcadoVO> listaValidacionesVO;

	private List<FotomultaMarcadoVO> listaPrevalidacionesVO;
	
	private List<FotomultaMarcadoVO> listaRegistrosReporte;

	private Map<String, List<FotomultaMarcadoVO>> mapDetecciones;

	private Map<String, List<FotomultaMarcadoVO>> mapPrevalidaciones;
	
	private String fechaInicio;
	
	private String fechaFinal;

	public FotomultaParamProcesamientoVO(List<FotomultaMarcadoVO> listaValidacionesVO,
			List<FotomultaMarcadoVO> listaPrevalidacionesVO, List<FotomultaMarcadoVO> listaRegistrosReporte,
			Map<String, List<FotomultaMarcadoVO>> mapDetecciones,
			Map<String, List<FotomultaMarcadoVO>> mapPrevalidaciones, String fechaInicio, String fechaFinal) {
		super();
		this.listaValidacionesVO = listaValidacionesVO;
		this.listaPrevalidacionesVO = listaPrevalidacionesVO;
		this.listaRegistrosReporte = listaRegistrosReporte;
		this.mapDetecciones = mapDetecciones;
		this.mapPrevalidaciones = mapPrevalidaciones;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
	}

	public FotomultaParamProcesamientoVO() {
		super();
	}

	public List<FotomultaMarcadoVO> getListaValidacionesVO() {
		return listaValidacionesVO;
	}

	public void setListaValidacionesVO(List<FotomultaMarcadoVO> listaValidacionesVO) {
		this.listaValidacionesVO = listaValidacionesVO;
	}

	public List<FotomultaMarcadoVO> getListaPrevalidacionesVO() {
		return listaPrevalidacionesVO;
	}

	public void setListaPrevalidacionesVO(List<FotomultaMarcadoVO> listaPrevalidacionesVO) {
		this.listaPrevalidacionesVO = listaPrevalidacionesVO;
	}

	public List<FotomultaMarcadoVO> getListaRegistrosReporte() {
		return listaRegistrosReporte;
	}

	public void setListaRegistrosReporte(List<FotomultaMarcadoVO> listaRegistrosReporte) {
		this.listaRegistrosReporte = listaRegistrosReporte;
	}

	public Map<String, List<FotomultaMarcadoVO>> getMapDetecciones() {
		return mapDetecciones;
	}

	public void setMapDetecciones(Map<String, List<FotomultaMarcadoVO>> mapDetecciones) {
		this.mapDetecciones = mapDetecciones;
	}

	public Map<String, List<FotomultaMarcadoVO>> getMapPrevalidaciones() {
		return mapPrevalidaciones;
	}

	public void setMapPrevalidaciones(Map<String, List<FotomultaMarcadoVO>> mapPrevalidaciones) {
		this.mapPrevalidaciones = mapPrevalidaciones;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	
	
	

}
