package mx.com.teclo.saicdmx.negocio.service.placas;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.vo.placas.PlacasVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;
import mx.com.teclomexicana.generaexcel.reporteexcel.PeticionReporteBOImpl;
import mx.com.teclomexicana.generaexcel.vo.PeticioReporteVO;
import mx.com.teclomexicana.generaexcel.vo.PropiedadesReporte;

@Service("placaReporteExcel")
public class PlacaReporteExcel {
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	private ByteArrayOutputStream byteArrayOutputStream = null;

	public ByteArrayOutputStream generarReportePlacas(List<PlacasVO> listaRegistroVO, String nombreReporte) {

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();

		RutinasTiempoImpl cambio = new RutinasTiempoImpl();

		PeticioReporteVO peticionReporteVO = new PeticioReporteVO();
		PropiedadesReporte propiedadesReporte = new PropiedadesReporte();
		propiedadesReporte.setTituloExcel(nombreReporte);
		propiedadesReporte.setNombreReporte(nombreReporte);
		propiedadesReporte.setExtencionArchvio(".xls");
		/*
		 * propiedadesReporte.setFechaI("10/10/2016");
		 * propiedadesReporte.setFechaF("10/10/2016");
		 */

		PeticionReporteBOImpl peticionReporteBOImpl = new PeticionReporteBOImpl();

		List<String> titulos = new ArrayList<String>();
		titulos.add("PLACA");
		titulos.add("OBSERVACIONES");
		titulos.add("ESTATUS CAJA");
		titulos.add("FECHA ALTA");
		titulos.add("CREADA POR");
		titulos.add("ULTIMA FECHA MODIFICACION");
		titulos.add("ULTIMA MODIFICACION POR");

		List<Object> encabezadoTitulo = new ArrayList<Object>();
		encabezadoTitulo.add(titulos);

		List<Object> hojas = new ArrayList<Object>();
		List<Object> hoja1 = new ArrayList<Object>();
		List<String> listaContenido1;

		for (PlacasVO record : listaRegistroVO) {
			listaContenido1 = new ArrayList<String>();

			// long creadoPorL = record.getCreadoPor();
			// String creadoPor = Long.toString(creadoPorL);

			listaContenido1.add(record.getPlacaCodigo());
			listaContenido1.add(record.getObservaciones());
			listaContenido1.add(record.getPlacaStatus());

			String fechaCreacion = "";
			if (record.getFechaCreacion() != null) {
				fechaCreacion = cambio.getFecha_ddMMYYYY_hhmmss(record.getFechaCreacion());
			}
			listaContenido1.add(fechaCreacion);

			String creadoPor = "";
			if (record.getFechaCreacion() != null) {
				//creadoPor = Long.toString(record.getCreadoPor());
				creadoPor = record.getCreadoPorS();
			}
			listaContenido1.add(creadoPor);

			String fechaModificacion = "";
			if (record.getFechaCreacion() != null) {
				fechaModificacion = cambio.getFecha_ddMMYYYY_hhmmss(record.getUltimaModificacion());
			}
			listaContenido1.add(fechaModificacion);

			String modificadoPor = "";
			if (record.getFechaCreacion() != null) {
				//modificadoPor = Long.toString(record.getModificadoPor());
				modificadoPor = record.getModificadoPorS();
			}
			listaContenido1.add(modificadoPor);

			//

			hoja1.add(listaContenido1);

		}
		hojas.add(hoja1);

		peticionReporteVO.setPropiedadesReporte(propiedadesReporte);
		peticionReporteVO.setEncabezado(encabezadoTitulo);
		peticionReporteVO.setContenido(hojas);

		try {
			byteArrayOutputStream = peticionReporteBOImpl.generaReporteExcel(peticionReporteVO);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteArrayOutputStream;
	}

}
