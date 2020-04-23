package mx.com.teclo.saicdmx.negocio.service.placas;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.placas.PlacasDTO;
import mx.com.teclo.saicdmx.persistencia.vo.placas.PlacasVO;

public interface PlacasService {

	List<PlacasVO> obtenerListaPlacas(String tipoBusqueda, String valor, Boolean isAdmin);

	List<PlacasVO> obtenerListaPlacasFecha(String tipoBusqueda, String fInicio, String fFin, Boolean isAdmin);

	List<PlacasVO> obtenerListaPlacasFechaRango(String tipoBusqueda, Integer periodoFecha, Boolean isAdmin);

	public PlacasDTO updatePlaca(Long placaId, String placaCodigo, String observaciones);

	public PlacasDTO savePlaca(String placaCodigo, String observaciones);

	public ByteArrayOutputStream generarReportExcel(String tipoBusqueda, String valor, Integer periodoFecha,
			String fInicio, String fFin, Boolean isAdmin);

	PlacasDTO getPlacaById(Long PlacaId);

	boolean cambiarEstatus(String accion, int placaId, int empId);

	public void encriptarPlaca(PlacasVO placasVO);
}
