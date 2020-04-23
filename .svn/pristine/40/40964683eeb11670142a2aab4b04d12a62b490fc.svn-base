package mx.com.teclo.saicdmx.negocio.service.bloqueohh;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.bloqueohh.BloqueohhCatTipoBloqueoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.bloqueohh.BloqueohhRegistroDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.empleado.EmpleadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh.BloqueohhCatTipoBloqueoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh.BloqueohhRegistroDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.bloqueohh.BloqueohhRegistroVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;

/**
 * Copyright (c) 2016, Teclo Mexicana. 
 * Descripcion					: BloqueohhServiceImpl
 * Historial de Modificaciones	: 
 * Descripcion del Cambio 		: Creacion
 * @author 						: fjmb
 * @version 					: 1.0 
 * Fecha 						: 29/Septiembre/2016
 **/

@Service
public class BloqueohhServiceImpl implements BloqueohhService {

	private static final Logger logger = LoggerFactory.getLogger(BloqueohhServiceImpl.class);

	@Autowired
	private EmpleadoDAO 				empleadoDAO;
	@Autowired
	private UsuarioFirmadoService 		usuarioFirmadoService;
	@Autowired
	private BloqueohhRegistroDAO 		bloqueohhRegistroDAO;
	@Autowired
	private BloqueohhCatTipoBloqueoDAO 	bloqueohhCatTipoBloqueoDAO;
	@Autowired
	private BloqueohhReporteExcel 		bloqueohhReporteExcel;
	private EmpleadosDTO 				empleadosDTO;
	private ByteArrayOutputStream 		reporte;

	@Override
	@Transactional(readOnly = true)
	public List<BloqueohhCatTipoBloqueoDTO> obtenerTipoBloqueo() {
		return bloqueohhCatTipoBloqueoDAO.obtenerTipoBloqueo();
	}

	@Override
	@Transactional(readOnly = true)
	public List<BloqueohhRegistroVO> consultaRegistrosBloqueados(String placaOficial, String numeroSeriehh) {

		Long oficialId = null;

		if (!placaOficial.equals("null")) {
			empleadosDTO = empleadoDAO.getEmpleadoByPlaca(placaOficial);
			oficialId = empleadosDTO == null ? 0 : empleadosDTO.getEmpId();
		}

		List<BloqueohhRegistroDTO> listBloqueohhRegistroDTO = bloqueohhRegistroDAO.obtenerRegistrosBloqueadoshh(oficialId, numeroSeriehh);
		List<BloqueohhRegistroVO> listBloquehhRegistroVO = new ArrayList<BloqueohhRegistroVO>();

		for (BloqueohhRegistroDTO bloqueohhRegistroDTO : listBloqueohhRegistroDTO) {

			empleadosDTO = empleadoDAO.findOne(bloqueohhRegistroDTO.getOficialId());
			BloqueohhRegistroVO bloquehhRegistroVO =  ResponseConverter.copiarPropiedadesFull(bloqueohhRegistroDTO,BloqueohhRegistroVO.class );
			bloquehhRegistroVO.setNombreOficial(empleadosDTO.getEmpNombre() + " " + empleadosDTO.getEmpApePaterno()	+ " " + empleadosDTO.getEmpApeMaterno());
			bloquehhRegistroVO.setPlacaOficial(empleadosDTO.getEmpPlaca().toUpperCase());
			listBloquehhRegistroVO.add(bloquehhRegistroVO);
		}

		return listBloquehhRegistroVO;
	}

	@Override
	@Transactional
	public BloqueohhRegistroDTO desbloquearHandHeld(Long idHandHeld, Long oficialId) {

		logger.info("Usuario Firmado: ID: " + usuarioFirmadoService.getUsuarioFirmadoVO().getId() + " \nNOMBRE : " + usuarioFirmadoService.getUsuarioFirmadoVO().getUsername());
		return bloqueohhRegistroDAO.desbloquearHandHeld(idHandHeld,  usuarioFirmadoService.getUsuarioFirmadoVO().getId());
	}

	@Override
	@Transactional
	public List<BloqueohhRegistroVO> informacionRegistros(Integer estatusBloqueo, String placaOficial, 
														  Integer tipoBloqueo, String numeroSeriehh, String fechaInicio, String fechaFin) {
		RutinasTiempoImpl utils = new RutinasTiempoImpl();
		Long oficialId = null;
		Date fechaInicioDate = null;
		Date fechaFinDate = null;

		if (!fechaInicio.equals("null") && !fechaFin.equals("null")) {
			fechaInicioDate = utils.convertirStringDate(fechaInicio.replace('-', '/'), "dd/MM/yyyy");
			fechaFinDate = utils.convertirStringDate(fechaFin.replace('-', '/'), "dd/MM/yyyy");

		} else if (!fechaInicio.equals("null")) {
			fechaInicioDate = utils.convertirStringDate(fechaInicio.replace('-', '/'), "dd/MM/yyyy");

		} else if (!fechaFin.equals("null")) {
			fechaFinDate = utils.convertirStringDate(fechaFin.replace('-', '/'), "dd/MM/yyyy");
		}

		if (placaOficial != null) {
			EmpleadosDTO empleado = empleadoDAO.getEmpleadoByPlaca(placaOficial);
			if (empleado == null) {
				oficialId = new Long(0);
			} else {
				oficialId = empleado.getEmpId();
			}
		}

		List<BloqueohhRegistroDTO> listBloqueohhRegistroDTO = bloqueohhRegistroDAO.obtenerRegistroshh(estatusBloqueo,
				oficialId, tipoBloqueo, numeroSeriehh, fechaInicio, fechaFin);
		List<BloqueohhRegistroVO> listBloquehhRegistroVO = new ArrayList<BloqueohhRegistroVO>();

		for (BloqueohhRegistroDTO bloqueohhRegistroDTO : listBloqueohhRegistroDTO) {

			empleadosDTO = empleadoDAO.findOne(bloqueohhRegistroDTO.getOficialId());
			BloqueohhRegistroVO bloquehhRegistroVO = ResponseConverter.copiarPropiedadesFull(bloqueohhRegistroDTO,	BloqueohhRegistroVO.class);
//			ResponseConverter.copiarPropriedades(bloquehhRegistroVO, bloqueohhRegistroDTO);
			SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss"); 
			bloquehhRegistroVO.setEstatusBloqueo( bloqueohhRegistroDTO.getEstatusBloqueo().equals(1)? "BLOQUEADA" : "DESBLOQUEADA");
			bloquehhRegistroVO.setNombreOficial(empleadosDTO.getEmpNombre() + " " + empleadosDTO.getEmpApePaterno()	+ " " + empleadosDTO.getEmpApeMaterno());
			bloquehhRegistroVO.setPlacaOficial(empleadosDTO.getEmpPlaca().toUpperCase());
//			bloquehhRegistroVO.setFechaBloqueo(bloqueohhRegistroDTO.getFechaBloqueo() != null ? java.sql.Date.valueOf(dt.format(bloqueohhRegistroDTO.getFechaBloqueo())) : bloqueohhRegistroDTO.getFechaBloqueo() );
//			bloquehhRegistroVO.setFechaDesbloqueo(bloquehhRegistroVO.getFechaDesbloqueo() != null ? java.sql.Date.valueOf(dt.format(bloquehhRegistroVO.getFechaDesbloqueo() )): bloquehhRegistroVO.getFechaDesbloqueo() );
			listBloquehhRegistroVO.add(bloquehhRegistroVO);

		}
		if (fechaInicio.equals("null") && fechaFin.equals("null")) {
			fechaInicio = utils.getStringDateFromFormta("dd/MM/yyyy", new Date());
		}
		if (!listBloquehhRegistroVO.isEmpty())
			this.reporte = bloqueohhReporteExcel.generarReporteExcel(listBloquehhRegistroVO, "Reporte Handheld",
																	 !fechaInicio.equals("null") ? fechaInicio : "", !fechaFin.equals("null") ? fechaFin : "");

		return listBloquehhRegistroVO;
	}

	@Override
	public List<FilterValuesVO> obtenerTipoEstatus() {
		List<FilterValuesVO> filterValues = new ArrayList<FilterValuesVO>();
		filterValues.add(new FilterValuesVO(2, "2", "Todos"));
		filterValues.add(new FilterValuesVO(1, "1", "BLOQUEADAS"));
		filterValues.add(new FilterValuesVO(0, "0", "DESBLOQUEADAS"));

		return filterValues;
	}

	@Override
	public ByteArrayOutputStream generarReporteExcel() {
		return this.reporte;
	}

}
