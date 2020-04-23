package mx.com.teclo.saicdmx.persistencia.hibernate.dao.bloqueohh;

import java.util.Date;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh.BloqueohhRegistroDTO;
 
/**
 * Copyright (c) 2016, Teclo Mexicana.
 * Descripcion: Interfaz BloqueohhRegistroDAO
 *
 * Historial de Modificaciones:
 * Descripcion del Cambio	: Creacion
 * @author 					: fjmb 
 * @version 				: 1.0
 * Fecha					: 29/Septiembre/2016
 */

public interface BloqueohhRegistroDAO  extends BaseDao<BloqueohhRegistroDTO>{
	/**
	 *  Búsqueda de registros de handheld con diferentes criterios de busqueda.
	 *  
 	 * @param Integer estatusBloqueo
 	 * @param Long oficialId
	 * @param Integer tipoBloqueo
	 * @param Long numeroSeriehh
	 * @param Date fechaInicio
	 * @param Date fechaFin
	 * @return List<BloqueohhRegistroDTO> 
	 */
	public List<BloqueohhRegistroDTO> obtenerRegistroshh(Integer estatusBloqueo,Long oficialId,Integer tipoBloqueo,String numeroSeriehh,String fechaInicio, String fechaFin);

	/**
	 * Obtiene lista de registros de handheld bloqueados.
	 * 
 	 * @param oficialId
	 * @param numeroSeriehh
	 * @return List<BloqueohhRegistroDTO> 
	 */
	public List<BloqueohhRegistroDTO> obtenerRegistrosBloqueadoshh(Long oficialId,String numeroSeriehh);
	
	/**
	 * Actualiza resgistro de handheld que estan bloqueados para cambiar a estado desbloqueado.
	 * 
 	 * @param Long idHandHeld
	 * @param Long numeroSeriehh
	 * @param Long idUsarioFirmado
	 * @return List<BloqueohhRegistroDTO> 
	 */
	public  BloqueohhRegistroDTO  desbloquearHandHeld (Long idHandHeld, Long idUsarioFirmado);
}
