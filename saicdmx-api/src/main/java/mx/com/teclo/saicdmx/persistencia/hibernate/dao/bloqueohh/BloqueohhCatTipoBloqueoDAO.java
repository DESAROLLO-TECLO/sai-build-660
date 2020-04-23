package mx.com.teclo.saicdmx.persistencia.hibernate.dao.bloqueohh;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh.BloqueohhCatTipoBloqueoDTO;

/**
 * Copyright (c) 2016, Teclo Mexicana.
 * Descripcion: Interfaz BloqueohhCatTipoBloqueoDAO
 *
 * Historial de Modificaciones:
 * Descripcion del Cambio	: Creacion
 * @author 					: fjmb 
 * @version 				: 1.0
 * Fecha					: 29/Septiembre/2016
 */

public interface BloqueohhCatTipoBloqueoDAO extends BaseDao<BloqueohhCatTipoBloqueoDTO> {
	/**
	 * Obtiene una lista de entidades TipoBloqueo
	 * @return List<BloqueohhCatTipoBloqueoDTO>
	 */
	public List<BloqueohhCatTipoBloqueoDTO> obtenerTipoBloqueo();


}
