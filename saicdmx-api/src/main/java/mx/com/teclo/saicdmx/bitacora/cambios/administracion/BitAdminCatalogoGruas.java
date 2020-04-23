package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudConcesionariaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudGruaStatusVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudGruaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTurnoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudZonaServicioVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitAdminCatalogoGruas {
	ArrayList<BitacoraCambiosVO> compararConcesionarias(CrudConcesionariaVO newCrudConcesionariaVO, CrudConcesionariaVO oldCrudConcesionariaVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararGruas(CrudGruaVO newCrudGruaVO, CrudGruaVO oldCrudGruaVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> comparaEstatusGruas(CrudGruaStatusVO newCrudGruaStatusVO, CrudGruaStatusVO oldCrudGruaStatusVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> comparaTurnos(CrudTurnoVO newCrudTurnoVO, CrudTurnoVO oldCrudTurnoVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> comparaCrudZonaServicio(CrudZonaServicioVO newCrudZonaServicioVO, CrudZonaServicioVO oldCrudZonaServicioVO, String oldEstatus);
	
}
