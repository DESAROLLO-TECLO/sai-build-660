package mx.com.teclo.saicdmx.negocio.service.fm;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDetalleDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMConsultaDeteccionesVO;
import mx.com.teclo.saicdmx.persistencia.vo.fm.FMDeteccionesFechasVO;

public interface ICancelacionDeteccionesFMService {
	
	public List<FMDeteccionesFechasVO> filtroTiposFecha();
	
	public List<FMConsultaDeteccionesVO> DeteccionesParaCancelar(String fechaInicio,String fechaFin,int tipoDeteccion,int tipoRadar,int origenPlaca);
	
	
	public List<FMConsultaDetalleDeteccionesVO> DeteccionesDetalle(String fechaInicio,String fechaFin,int tipoDeteccion,int tipoRadar,int origenPlaca);
	
	public Long DeteccionesCancelacionFM (String fechaCancelacion,long usuario,String fechaInicio,String fechaFin,int tipoDeteccion,int tipoRadar,int origenPlaca,String motivo);
	
	ByteArrayOutputStream descargaExcelDeteccMarcado();
	
	//ByteArrayOutputStream descargaExcelDeteccMarcado(String fechaInicio,String fechaFin,int tipoDeteccion,int tipoRadar,int origenPlaca);

}
