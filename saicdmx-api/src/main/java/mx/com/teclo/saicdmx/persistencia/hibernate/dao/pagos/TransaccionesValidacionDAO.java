package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;



import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionesValidacionDTO;

/**
 *  Copyright (c) 2017, Teclo Mexicana.
 *  Descripcion					: Interfaz TransaccionesValidacionDAO
 *  
 *  Historial de Modificaciones	:
 *  Descripcion del Cambio		: Creacion
 *  @author 					: DanielUnitis
 *  @version 					: 1.0
 *  Fecha						: 12/Mayo/2017
 */
public interface TransaccionesValidacionDAO extends BaseDao<TransaccionesValidacionDTO>{
	
	public String fechaValidacion(Long tranId,String tipoOperacion,Long cajaId);
	
	public void saveTransaccionValidacionDTO(TransaccionesValidacionDTO transaValidacionDTO);
	
}
