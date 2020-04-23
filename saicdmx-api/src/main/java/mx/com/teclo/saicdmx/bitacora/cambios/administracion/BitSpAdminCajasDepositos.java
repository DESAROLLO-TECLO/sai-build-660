package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.caja.AdminCajasDepositosSPVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpAdminCajasDepositos {
	
	public ArrayList<BitacoraCambiosVO> spAdminCajasDepositos(AdminCajasDepositosSPVO adminCajasModifSPVO) throws Exception;	
	
}
