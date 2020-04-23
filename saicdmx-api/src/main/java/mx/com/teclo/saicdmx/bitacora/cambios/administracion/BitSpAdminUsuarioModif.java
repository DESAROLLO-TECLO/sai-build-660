package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.UsuarioAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.EmpleadoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpAdminUsuarioModif {
	public List<BitacoraCambiosVO> bitacoraAdminModifUsuario(UsuarioAdminVO usuarioAdminVO, EmpleadoVO voEmplOld);
}
