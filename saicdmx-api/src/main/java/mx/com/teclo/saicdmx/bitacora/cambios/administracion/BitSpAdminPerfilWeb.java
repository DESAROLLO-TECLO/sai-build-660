package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminPerfilesParamVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

/**
 * 
 * @author Javier Flores
 *
 */
public interface BitSpAdminPerfilWeb {

	/**
	 * @author Javier Flores
	 * @param parametrosVO
	 * @return BitacoraCambiosVO
	 */
	BitacoraCambiosVO nuevoPerfil(AdminPerfilesParamVO parametrosVO);
	
	/**
	 * @author Javier Flores
	 * @param parametrosVO
	 * @return BitacoraCambiosVO
	 */
	BitacoraCambiosVO eliminaPerfil(AdminPerfilesParamVO parametrosVO);

	/**
	 * @author Javier Flores
	 * @param parametrosVO
	 * @return BitacoraCambiosVO
	 */
	BitacoraCambiosVO agregaServicioPerfil(AdminPerfilesParamVO parametrosVO);
	
	/**
	 * @author Javier Flores
	 * @param parametrosVO
	 * @return BitacoraCambiosVO
	 */
	BitacoraCambiosVO desligarServicioPerfil(AdminPerfilesParamVO parametrosVO);



}
