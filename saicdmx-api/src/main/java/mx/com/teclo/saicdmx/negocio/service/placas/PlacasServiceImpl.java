package mx.com.teclo.saicdmx.negocio.service.placas;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.placas.PlacaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.placas.PlacasDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.placas.ConsultaPlacasMyBatis;
import mx.com.teclo.saicdmx.persistencia.vo.placas.PlacasVO;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

@Service
public class PlacasServiceImpl implements PlacasService {

	private ByteArrayOutputStream reporte;

	@Autowired
	private PlacaDAO placaDAO;

	@Autowired
	private ConsultaPlacasMyBatis consultaMyBatisDAO;

	// @Autowired
	// private CajaReporteExcel cajaReporteExcel;
	@Autowired
	private PlacaReporteExcel placaReporteExcel;

	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;

	@Override
	public List<PlacasVO> obtenerListaPlacas(String tipoBusqueda, String valor, Boolean isAdmin) {

		List<PlacasVO> listaConsultaPlacas = null;

		if (tipoBusqueda.equals("placa")) {
			valor = valor != null ? valor.toUpperCase().trim() : valor;

			//if (isAdmin) {
				listaConsultaPlacas = consultaMyBatisDAO.obtieneListaPlacas(valor);
			//}
		}

		if (tipoBusqueda.equals("placaId")) {
			valor = valor != null ? valor.toUpperCase() : valor;

			//if (isAdmin) {
				listaConsultaPlacas = consultaMyBatisDAO.obtieneListaPlacasId(valor);
			//}
		}

		if (tipoBusqueda.equals("todos")) {
			//if (isAdmin) {
				listaConsultaPlacas = consultaMyBatisDAO.obtieneListaPlacasTodos();
			//}
		}

		if (!listaConsultaPlacas.isEmpty()) {
			String emptyString = "";
			for (Iterator<PlacasVO> iterator = listaConsultaPlacas.iterator(); iterator.hasNext();) {
				PlacasVO placas = (PlacasVO) iterator.next();

				placas.setPlacaStatus(placas.getPlacaStatus().equals("1") ? "Habilitado" : "Deshabilitado");
			

			}

		}

		return listaConsultaPlacas;
	}

	public List<PlacasVO> obtenerListaPlacasFechaRango(String tipoBusqueda, Integer periodoFecha, Boolean isAdmin) {
		List<PlacasVO> listaConsultaPlacasFecha = null;

		List<String> rangoFechas = generaRangoFechas(periodoFecha);
		String fechaInicio = rangoFechas.get(0);
		String fechaFin = rangoFechas.get(1);

		if (tipoBusqueda.equals("fh_creacion") && periodoFecha == 0) {

			listaConsultaPlacasFecha = consultaMyBatisDAO.obtieneListaPlacasHoyC(fechaInicio);

		}

		if (tipoBusqueda.equals("fh_modificacion") && periodoFecha == 0) {

			listaConsultaPlacasFecha = consultaMyBatisDAO.obtieneListaPlacasHoyM(fechaInicio);

		}

		if (tipoBusqueda.equals("fh_creacion") && periodoFecha == 1) {

			listaConsultaPlacasFecha = consultaMyBatisDAO.obtieneListaPlacasAyerC(fechaInicio);

		}

		if (tipoBusqueda.equals("fh_modificacion") && periodoFecha == 1) {

			listaConsultaPlacasFecha = consultaMyBatisDAO.obtieneListaPlacasAyerM(fechaInicio);

		}

		if (tipoBusqueda.equals("fh_creacion") && periodoFecha != 0 && periodoFecha != 1) {

			listaConsultaPlacasFecha = consultaMyBatisDAO.obtieneListaPlacasCreacionFH(fechaInicio, fechaFin);

		}

		if (tipoBusqueda.equals("fh_modificacion") && periodoFecha != 0 && periodoFecha != 1) {

			listaConsultaPlacasFecha = consultaMyBatisDAO.obtieneListaPLacasModificacionFH(fechaInicio, fechaFin);

		}

		if (!listaConsultaPlacasFecha.isEmpty()) {
			String emptyString = "";
			for (Iterator<PlacasVO> iterator = listaConsultaPlacasFecha.iterator(); iterator.hasNext();) {
				PlacasVO placas = (PlacasVO) iterator.next();

				placas.setPlacaStatus(placas.getPlacaStatus().equals("1") ? "Habilitado" : "Deshabilitado");
				

			}

		}

		return listaConsultaPlacasFecha;

	}

	public List<String> generaRangoFechas(Integer periodoFecha) {
		List<String> rangoFechas = new ArrayList<String>();

		Calendar dateActual = Calendar.getInstance();
		// dateActual = new GregorianCalendar(2018, 6, 8);
		Calendar dateInicio;
		dateInicio = (Calendar) dateActual.clone();
		int diaInicio = 0;
		int mesInicio = 0;
		int añoInicio = 0;
		String fechaInicio = "";
		Calendar dateFin;
		dateFin = (Calendar) dateActual.clone();
		int diaFin = 0;
		int mesFin = 0;
		int añoFin = 0;
		String fechaFin = "";
		int diaSemana = 0;

		int inicioSemana = 0;
		int diaMes = 0;
		int diasMes = 0;
		int inicioMes = 0;
		int finMes = 0;

		if (periodoFecha == 1) {// "Hoy"
			dateFin = (Calendar) dateInicio.clone();
		} else if (periodoFecha == 2) {// "Ayer"
			dateInicio.add(Calendar.DAY_OF_YEAR, -1);
			dateFin = (Calendar) dateInicio.clone();
		} else if (periodoFecha == 3) {// "Esta Semana"
			diaSemana = dateInicio.get(Calendar.DAY_OF_WEEK);
			if (diaSemana != 1) {
				inicioSemana = ((diaSemana - 1) * -1);
				dateInicio.add(Calendar.DAY_OF_YEAR, inicioSemana);
			}

			dateFin = (Calendar) dateInicio.clone();
			dateFin.add(Calendar.DAY_OF_YEAR, 6);
		} else if (periodoFecha == 4) {// "Última Semana"
			diaSemana = dateInicio.get(Calendar.DAY_OF_WEEK);
			if (diaSemana != 1) {
				inicioSemana = ((diaSemana - 1) * -1);
				dateInicio.add(Calendar.DAY_OF_YEAR, inicioSemana);
			}
			dateInicio.add(Calendar.WEEK_OF_YEAR, -1);

			dateFin = (Calendar) dateInicio.clone();
			dateFin.add(Calendar.DAY_OF_YEAR, 6);
		} else if (periodoFecha == 5) {// "Últimos 7 Días"
			dateInicio.add(Calendar.DAY_OF_YEAR, -7);
			dateFin = (Calendar) dateActual.clone();
		} else if (periodoFecha == 6) {// "Este Mes"
			diaMes = dateInicio.get(Calendar.DAY_OF_MONTH);
			if (diaMes != 1) {
				inicioMes = ((diaMes - 1) * -1);
				dateInicio.add(Calendar.DAY_OF_YEAR, inicioMes);
			}

			dateFin = (Calendar) dateInicio.clone();
			diasMes = dateFin.getActualMaximum(Calendar.DAY_OF_MONTH);
			finMes = diasMes - 1;

			dateFin.add(Calendar.DAY_OF_YEAR, finMes);
		} else if (periodoFecha == 7) {// "Último Mes"
			diaMes = dateInicio.get(Calendar.DAY_OF_MONTH);
			if (diaMes != 1) {
				inicioMes = ((diaMes - 1) * -1);
				dateInicio.add(Calendar.DAY_OF_YEAR, inicioMes);
			}
			dateInicio.add(Calendar.MONTH, -1);

			dateFin = (Calendar) dateInicio.clone();
			diasMes = dateFin.getActualMaximum(Calendar.DAY_OF_MONTH);
			finMes = diasMes - 1;

			dateFin.add(Calendar.DAY_OF_YEAR, finMes);
		} else if (periodoFecha == 8) {// "Últimos 30 Días"
			dateInicio.add(Calendar.DAY_OF_YEAR, -30);
			dateFin = (Calendar) dateActual.clone();
		}

		diaInicio = dateInicio.get(Calendar.DAY_OF_MONTH);
		mesInicio = dateInicio.get(Calendar.MONTH) + 1;
		añoInicio = dateInicio.get(Calendar.YEAR);
		fechaInicio = diaInicio + "/" + mesInicio + "/" + añoInicio;

		diaFin = dateFin.get(Calendar.DAY_OF_MONTH);
		mesFin = dateFin.get(Calendar.MONTH) + 1;
		añoFin = dateFin.get(Calendar.YEAR);
		fechaFin = diaFin + "/" + mesFin + "/" + añoFin;

		rangoFechas.add(fechaInicio);
		rangoFechas.add(fechaFin);
		return rangoFechas;
	}

	@Override
	public List<PlacasVO> obtenerListaPlacasFecha(String tipoBusqueda, String fInicio, String fFin, Boolean isAdmin) {
		RutinasTiempoImpl cambio = new RutinasTiempoImpl();

		List<PlacasVO> listaConsultaPlacasFecha = null;

		if (tipoBusqueda.equals("fh_creacion")) {

			listaConsultaPlacasFecha = consultaMyBatisDAO.obtieneListaPlacasCreacionFH(fInicio, fFin);

		}

		if (tipoBusqueda.equals("fh_modificacion")) {

			listaConsultaPlacasFecha = consultaMyBatisDAO.obtieneListaPLacasModificacionFH(fInicio, fFin);

		}

		if (!listaConsultaPlacasFecha.isEmpty()) {
			String emptyString = "";
			for (Iterator<PlacasVO> iterator = listaConsultaPlacasFecha.iterator(); iterator.hasNext();) {
				PlacasVO placas = (PlacasVO) iterator.next();

				placas.setPlacaStatus(placas.getPlacaStatus().equals("1") ? "Habilitado" : "Deshabilitado");
			
			}

		}

		return listaConsultaPlacasFecha;

	}

	@Transactional
	@Override
	public PlacasDTO getPlacaById(Long placaId) {
		return placaDAO.findOne(placaId);
		
	}

	@Transactional
	@Override
	public PlacasDTO updatePlaca(Long placaId, String placaCodigo, String observaciones) {

		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		// PlacasDTO placas=new PlacasDTO();
		PlacasDTO placasDTO = getPlacaById(placaId);

		String placaEncriptada = placaDAO.encriptarCampo(placaCodigo);

		placasDTO.setModificadoPor(usuario.getId());
		placasDTO.setUltimaModificacion(new Date());
		placasDTO.setPlacaCodigo(placaEncriptada);
		placasDTO.setObservaciones(observaciones);
		placaDAO.update(placasDTO);

		return placasDTO;
	}

	@Transactional
	@Override
	public PlacasDTO savePlaca(String placaCodigo, String observaciones) {
		UsuarioFirmadoVO usuario = usuarioFirmadoService.getUsuarioFirmadoVO();
		PlacasDTO placasDTO = new PlacasDTO();
		String placaEncriptada = placaDAO.encriptarCampo(placaCodigo);

		placasDTO.setCreadoPor(usuario.getId());
		placasDTO.setModificadoPor(usuario.getId());

		placasDTO.setFechaCreacion(new Date());
		placasDTO.setUltimaModificacion(new Date());
		placasDTO.setPlacaStatus("1");
		placasDTO.setPlacaCodigo(placaEncriptada);
		placasDTO.setObservaciones(observaciones);
		placaDAO.save(placasDTO);

		return placasDTO;

	}

	@Override
	public ByteArrayOutputStream generarReportExcel(String tipoBusqueda, String valor, Integer periodoFecha,
			String fInicio, String fFin, Boolean isAdmin) {
		List<PlacasVO> listaConsultaPlacas = null;

		if (tipoBusqueda.equals("placa") || tipoBusqueda.equals("placaId") || tipoBusqueda.equals("todos")) {
			listaConsultaPlacas = obtenerListaPlacas(tipoBusqueda, valor, isAdmin);

		}

		if (periodoFecha != 0 && fInicio.isEmpty() && fFin.isEmpty() && valor.isEmpty()) {
			listaConsultaPlacas = obtenerListaPlacasFechaRango(tipoBusqueda, periodoFecha, isAdmin);

		}

		if (!fInicio.isEmpty() && !fFin.isEmpty() && valor.isEmpty()) {
			listaConsultaPlacas = obtenerListaPlacasFecha(tipoBusqueda, fInicio, fFin, isAdmin);

		}

		reporte = placaReporteExcel.generarReportePlacas(listaConsultaPlacas, "Placas");
		return this.reporte;
	}

	public boolean cambiarEstatus(String accion, int placaId, int empId) {
		int status;
		if (accion.equalsIgnoreCase("Deshabilitado")) {
			status = 1;
		} else {
			status = 0;
		}

		String resultado = consultaMyBatisDAO.cambiarEstatus(status, placaId, empId);
		Boolean confirmacion = false;

		if (resultado == null) {
			confirmacion = true;
		}

		return confirmacion;
	}
	
	@Transactional
	@Override
	public void encriptarPlaca(PlacasVO placasVO) {
		placasVO.setPlacaCodigo(placaDAO.encriptarCampo(placasVO.getPlacaCodigo()));
	}

}
