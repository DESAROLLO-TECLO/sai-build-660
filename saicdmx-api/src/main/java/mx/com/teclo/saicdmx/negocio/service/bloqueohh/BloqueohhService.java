package mx.com.teclo.saicdmx.negocio.service.bloqueohh;

import java.io.ByteArrayOutputStream;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh.BloqueohhCatTipoBloqueoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh.BloqueohhRegistroDTO;
import mx.com.teclo.saicdmx.persistencia.vo.bloqueohh.BloqueohhRegistroVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;

/**
 *  Copyright (c) 2016, Teclo Mexicana.
 *  Descripcion					: Interfaz BloqueohhService
 *  
 *  Historial de Modificaciones	:
 *  Descripcion del Cambio		: Creacion
 *  @author 					: fjmb 
 *  @version 					: 1.0
 *  Fecha						: 29/Septiembre/2016
 */
 
public interface BloqueohhService {
	
	/**
	 * Obtiene una lista de entidades TipoBloqueo
	 * @return List<BloqueohhCatTipoBloqueoDTO> Lista de entidades encontradas de tipos de Bloqueo
	 */
	public List<BloqueohhCatTipoBloqueoDTO> obtenerTipoBloqueo();
 
	 /**
     * Consulta la lista de registros de handheld que estan bloqueados.
     * 
     * @param String  placaOficial
     * @param Long numeroSeriehh
     * @return List<BloqueohhRegistroVO> Lista de entidades encontradas
     */
	public List<BloqueohhRegistroVO> consultaRegistrosBloqueados (String  placaOficial, String numeroSeriehh);
	 
	/**
     * Actualiza resgistro de handheld que estan bloqueados para cambiar a estado desbloqueado.
     * 
     * @param Long idHandHeld
     * @param Long oficialId
     * @return BloqueohhRegistroDTO Entidad actualizada.
     */
	public BloqueohhRegistroDTO desbloquearHandHeld (Long idHandHeld, Long oficialId);
	
	/**
     * Consulta la lista de registros de handheld con diferentes criterios de busqueda.
     * 
     * @param Integer estatusBloqueo
     * @param String placaOficial
     * @param Integer tipoBloqueo
     * @paramLong numeroSeriehh
     * @param String fechaInicio
     * @paramString fechaFin
     * @param Long oficialId
     * @return List<BloqueohhRegistroVO> Lista de entidades encontradas.
     */
	public  List<BloqueohhRegistroVO> informacionRegistros(Integer estatusBloqueo, String placaOficial, Integer tipoBloqueo,
														    String numeroSeriehh, String fechaInicio, String fechaFin);

	/**
	 * Obtiene una lista de entidades con diferentes tipos de Estatus
	 * @return List<FilterValuesVO>  Lista de entidades estatus.
	 */
	public List<FilterValuesVO> obtenerTipoEstatus ();
	
	/**
     * Genera reporte de Excel.
     * 
     * @return ByteArrayOutputStream archivo excel.
     */
	public ByteArrayOutputStream generarReporteExcel ();

}
