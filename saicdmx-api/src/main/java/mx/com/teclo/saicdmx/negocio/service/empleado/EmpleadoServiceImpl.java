package mx.com.teclo.saicdmx.negocio.service.empleado;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.VInfraccionUsuariosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.empleado.EmpleadoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VInfraccionUsuariosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.vempleadodepautoriza.VEmpleadoDepAutorizaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.empleados.VEmpleadoDepAutorizaVO;
import mx.com.teclo.saicdmx.util.comun.Infracciones;
import mx.com.teclo.saicdmx.util.enumerados.CodigoPerfilesEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.dao.PerfilDAO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.dto.PerfilDTO;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	private VInfraccionUsuariosDAO vInfraccionUsuarioDAO;

	@Autowired
	private EmpleadoDAO empleadoDAO;

	@Autowired
	@Qualifier("perfilDaoOrtogonales")
	private PerfilDAO perfilDAO;

	@Autowired
	private VEmpleadoDepAutorizaMyBatisDAO vEmpleadoDepAutorizaMyBatisDAO;

	@Override
	@Transactional
	public boolean validarPlacaEmpleado(String placa) {
		boolean empleadoOK = false;
		VInfraccionUsuariosDTO empleado = vInfraccionUsuarioDAO.buscarOficial(placa);
		if (empleado != null) {
			// (EMPLEADO_TIPO)TIPO EMPLEADO = EMPLEADO CREADO POR INFRACCI
			// O (PERFILES) PERFIL = HANDHELD
			if (empleado.getEmpTipo() == Infracciones.ID_EMPLEADO_CREADO_POR_INFRACCIONES.longValue()
					|| empleado.getPerfilId() == Infracciones.ID_PERFIL_HANDHELD) {
				empleadoOK = true;
			}
		}

		return empleadoOK;
	}

	@Override
	@Transactional
	public EmpleadosDTO buscarEmpleadoParaAltaInfraccion(String placa) {
		return empleadoDAO.buscarEmpleadoParaAltaInfraccion(placa);
	}

	@Override
	@Transactional(readOnly = true)
	public EmpleadosDTO getEmpleadoByPlaca(String empPlaca) {
		return empleadoDAO.getEmpleadoByPlaca(empPlaca);
	}

	@Override
	@Transactional
	public EmpleadosDTO getEmpleadoById(Long empleadoId) {
		return empleadoDAO.findOne(empleadoId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VEmpleadoDepAutorizaVO buscarVEmpleadoDepAutorizaVOporEmpIdNombrePlaca(String v1, String v2, String v3) {
		List<VEmpleadoDepAutorizaVO> emp = vEmpleadoDepAutorizaMyBatisDAO
				.buscarVEmpleadoDepAutorizaVOporEmpIdNombrePlaca(v1, v2, v3);
		if (CollectionUtils.isNotEmpty(emp))
			return emp.get(0);
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Boolean validarPerfilEmpleado(Long perfilId, Integer screen) {
		Boolean perfilValido = false;
		PerfilDTO perfil = this.perfilDAO.findOne(perfilId);
		// 1: ReasignacionLC; Ver los descuentos a aplicar.
		switch (screen) {
		case 1:
			if (perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_ADMINISTRADOR.getCodigo())
					|| perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.RADARES.getCodigo())
					|| perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.RADARES_ADMIN.getCodigo())
					|| perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.SSP_ADMINISTRADOR.getCodigo()))
				perfilValido = true;
			break;
		// 2: Consulta de lotes de fotomulta; Ver columnas en la tabla
		case 2:
			if (perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_ADMINISTRADOR.getCodigo()))
				perfilValido = true;
			break;
		// 3: Cambio de contraseña; Validar si aplicar pattern
		case 3:
			if(perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_ADMINISTRADOR.getCodigo()) || 
					perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_OPERACION.getCodigo()) ||
						perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_CONTACT_CENTER.getCodigo()) ||
							perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_SOPORTE_ESPECIAL.getCodigo()))
				perfilValido = true;
			break;	
		default:
			break;
		}

		return perfilValido;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional(readOnly = true)
	public Map validarPerfilParaUsuarios(Long perfilId) {
		Map perfiles = new HashMap();
		perfiles.put("xSSPA", false);
		perfiles.put("xTCLA", false);
		perfiles.put("xTCLO", false);
		perfiles.put("xTCLSE", false);
		
		perfiles.put("xTCLCC", false);
		
		PerfilDTO perfil = this.perfilDAO.findOne(perfilId);
		perfiles.put("xCOD", perfil.getPerfilCodigo());
		
		if(perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.SSP_ADMINISTRADOR.getCodigo())){
			perfiles.put("xSSPA", true);
		}
		if(perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_ADMINISTRADOR.getCodigo())){
			perfiles.put("xTCLA", true);
		}
		if(perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_OPERACION.getCodigo())){
			perfiles.put("xTCLO", true);
		}
		if(perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_SOPORTE_ESPECIAL.getCodigo())){
			perfiles.put("xTCLA", true);
			perfiles.put("xTCLSE", true);
		}
		if(perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_CONTACT_CENTER.getCodigo())){
			perfiles.put("xTCLCC", true);
		}

		return perfiles;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@Transactional(readOnly = true)
	public Map validarPerfilParaCertificadosSAT(Long perfilId) {
		Map perfiles = new HashMap();
		perfiles.put("xSSPA", false);
		perfiles.put("xTCLA", false);
		perfiles.put("xTCLO", false);
		
		PerfilDTO perfil = this.perfilDAO.findOne(perfilId);
		perfiles.put("xCOD", perfil.getPerfilCodigo());
		
		if(perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.SSP_ADMINISTRADOR.getCodigo())){
			perfiles.put("xSSPA", true);
		}
		if(perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_ADMINISTRADOR.getCodigo()) ||
				perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_SOPORTE_ESPECIAL.getCodigo())){
			perfiles.put("xTCLA", true);
		}
		if(perfil.getPerfilCodigo().equals(CodigoPerfilesEnum.TCL_OPERACION.getCodigo())){
			perfiles.put("xTCLO", true);
		}

		return perfiles;
	}

}