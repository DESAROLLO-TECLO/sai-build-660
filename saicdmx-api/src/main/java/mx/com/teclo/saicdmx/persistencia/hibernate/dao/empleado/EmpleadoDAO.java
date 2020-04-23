package mx.com.teclo.saicdmx.persistencia.hibernate.dao.empleado;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;

public interface EmpleadoDAO extends BaseDao<EmpleadosDTO>{

	/**
	 * Retorna Empleado apto para el modulo de alta de infracciones mediante la placa  capturada
	 * @param placa
	 * @return
	 */
	public EmpleadosDTO buscarEmpleadoParaAltaInfraccion(String placa);
	
	/**
	 * Retorna la entidad Empleado por placa.
	 * @param  empPlaca
	 * @return EmpleadosDTO
	 */
	public EmpleadosDTO getEmpleadoByPlaca(String empPlaca);
	
	
	
	/** 
	 * Actualiza rfc de empleado.
	 * @param String placaOficial 
	 * @param String  rfc  .
	 * @return EmpleadoDTO
	 */
 	public  EmpleadosDTO actualizaRFCEmpleado( String placaOficial, String rfc);
 	
 	/** 
	 * Consulta rfc de empleados para validar unicidad.
	 * @param String placaOficial 
	 * @param String  rfc.
	 * @return List<EmpleadoDTO>
	 */
 	public  List<EmpleadosDTO> consultaRFCEmpleado( String placaOficial, String rfc);
	
 	/** 
	 * CONSULTA INFORMACION DEL OFICIAL QUE AUTORIZA EL MOVIMIENTO EN SOPORTE BITACORA
	 * @param String placaOficial 
	 * @return EmpleadoDTO
	 */
 	public EmpleadosDTO consultaEmpleadoAutorizaSoporte(String placaOficia);

	public EmpleadosDTO find(Long empId);

}
