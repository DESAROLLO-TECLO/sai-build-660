package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasUsuariosSPVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpAdminCajasUsuarios {

	public ArrayList<BitacoraCambiosVO> spAdminCajasUsuarios(AdminCajasUsuariosSPVO adminCajasModifSPVO);
		
}
