package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasNuevoSPVO;

public interface BitSpAdminCajasNuevo {

	public AdminCajasNuevoSPVO spAdminCajasNuevo(AdminCajasNuevoSPVO adminCajasModifSPVO, String getCajaCode);
		
}
