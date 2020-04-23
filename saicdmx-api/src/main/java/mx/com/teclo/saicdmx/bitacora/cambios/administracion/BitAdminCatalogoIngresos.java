package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudAreaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudCausaIngresoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudComponenteVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoIngresoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitAdminCatalogoIngresos {
	ArrayList<BitacoraCambiosVO> compararAreas(CrudAreaVO newCrudAreaVO, CrudAreaVO oldCrudAreaVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararCausa(CrudCausaIngresoVO newCrudCausaIngresoVO, CrudCausaIngresoVO oldCrudCausaIngresoVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararComponente(CrudComponenteVO newCrudComponenteVO, CrudComponenteVO oldCrudComponenteVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararTipo(CrudTipoIngresoVO newCrudTipoIngresoVO, CrudTipoIngresoVO oldCrudTipoIngresoVO, String oldEstatus);
}
