package mx.com.teclo.saicdmx.persistencia.hibernate.dao.certificados;

import java.util.Date;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadoFirmaDTO;

public interface CertificadoDAO extends  BaseDao<EmpleadoFirmaDTO> {
	

	/** 
	 * Obtine la  entidad Certificado por placa de Empleado.
	 * @param idEmpleado 
	 * @return EmpleadoFirmaDTO
	 */
	public EmpleadoFirmaDTO obtieneCertificadoPorPlaca( Long idEmpleado );
	
	/** 
	 * Cambia estatus de certificado validado por placa de Empleado.
	 * @param idEmpleado 
	 * @return EmpleadoFirmaDTO
	 */
	public EmpleadoFirmaDTO updateStatusCertificado( Long idEmpleado );
	
	
	
	/** 
	 * Cambia a estado inactivo un certificado por placa de Empleado.
	 * @param idEmpleado
	 * @return EmpleadoFirmaDTO
	 */
	public EmpleadoFirmaDTO deleteCertificadoByPlaca( Long idEmpleado );
	
	/** 
	 *Busca la entidad Certificado por parámetros de Empleados y Rango de fechas.
	 * @param tipoBusqueda 
	 * @param paramBusqueda 
	 * @param fechaInicio 
	 * @param fechaFin  
	 * @return List<EmpleadoFirmaDTO>  Lista de Entidades encontradas .
	 */
 	public List<EmpleadoFirmaDTO> obtieneCertificados( String tipoBusqueda, String paramBusqueda, String fechaInicio, String fechaFin, Integer validado );
 
}
