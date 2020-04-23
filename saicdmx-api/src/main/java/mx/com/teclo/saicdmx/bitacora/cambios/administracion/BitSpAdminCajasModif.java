package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasModifSPVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpAdminCajasModif {

	public ArrayList<BitacoraCambiosVO> spAdminCajasModif(AdminCajasModifSPVO adminCajasModifSPVO);	
	
}
