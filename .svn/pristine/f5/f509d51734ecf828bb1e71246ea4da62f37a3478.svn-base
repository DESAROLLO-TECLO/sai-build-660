package mx.com.teclo.saicdmx.negocio.service.empleado;

import java.util.Map;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.empleados.VEmpleadoDepAutorizaVO;

public interface EmpleadoService {
	
	public boolean validarPlacaEmpleado(String placa);

	/**
	 * Retorna Empleado apto para el modulo de alta de infracciones mediante la placa  capturada
	 * @param placa
	 * @return
	 */
	public EmpleadosDTO buscarEmpleadoParaAltaInfraccion(String placa);
	
	/**
	 * Busca Empleados por empPlaca.
 	 * @param empPlaca
	 * @return Entidad EmpleadoLoginDTO, con estatus Activo (A)
	 */
	public EmpleadosDTO getEmpleadoByPlaca(String empPlaca);
	
	public EmpleadosDTO getEmpleadoById(Long empleadoId);
	
	/***
	 * @author Jesus Gutierrez
	 * Valida si el empleado cuenta con el perfil correcto para hacer alguna accion segun la pantalla 
	 * @param perfilId
	 * @param screen
	 * @return
	 */
	public Boolean validarPerfilEmpleado(Long perfilId, Integer screen);
	
	/***
	 * @author Jesus Gutierrez
	 * Valida el perfil del usuario que ha iniciado sesion para definir que acciones se deben realizar en el submódulo de usuarios
	 * @param perfilId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map validarPerfilParaUsuarios(Long perfilId);
	
	/***
	 * @author Jesus Gutierrez
	 * Valida el perfil del usuario que ha iniciado sesion para definir que acciones se deben realizar en el submódulo de Alta Certificados
	 * @param perfilId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map validarPerfilParaCertificadosSAT(Long perfilId);
	
	
	/**
	 * RETORNA UNA LISTA DE LA VISTA V_EMPLEADO_DEP_AUTORIZA FILTRADO POR PLACA, PWD, DEP_ID
	 * @author Kevin Ojeda
	 * @param v1
	 * @param v2
	 * @param v3
	 * @return List<VEmpleadoDepAutorizaVO>
	 */
	public VEmpleadoDepAutorizaVO buscarVEmpleadoDepAutorizaVOporEmpIdNombrePlaca(String v1, String v2, String v3);

}
