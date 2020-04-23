package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudRegionDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudZonaVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitAdminCatalogoDepositos {
	ArrayList<BitacoraCambiosVO> compararDepositos(CrudDepositoVO newCrudDepositoVO, CrudDepositoVO oldCrudDepositoVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararRegionDep(CrudRegionDepositoVO newCrudRegionDepositoVO, CrudRegionDepositoVO oldCrudRegionDepositoVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararZonas(CrudZonaVO newCrudZonaVO, CrudZonaVO oldCrudZonaVO, String oldEstatus);
}
