package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudArticuloVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudCategoriaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudCausalesVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudProgramaVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitAdminCatalogoArticulo {
	ArrayList<BitacoraCambiosVO> compararCategoria(CrudCategoriaVO newCrudCategoriaVO, CrudCategoriaVO oldCrudCategoriaVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararCausal(CrudCausalesVO newCrudCausalesVO, CrudCausalesVO oldCrudCausalesVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararArticulo(CrudArticuloVO newCrudArticuloVO, CrudArticuloVO oldCrudArticuloVO);
	ArrayList<BitacoraCambiosVO> compararPrograma(CrudProgramaVO newCrudProgramaVO, CrudProgramaVO oldCrudProgramaVO, String oldEstatus);
}
