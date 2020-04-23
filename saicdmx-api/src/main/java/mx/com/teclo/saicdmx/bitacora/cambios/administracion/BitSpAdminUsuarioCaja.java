package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminCajaParametrosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpAdminUsuarioCaja {

	public List<BitacoraCambiosVO> insertCambioUsuarioCaja(AdminCajaParametrosVO adminCajaParametrosVO);
	
}

