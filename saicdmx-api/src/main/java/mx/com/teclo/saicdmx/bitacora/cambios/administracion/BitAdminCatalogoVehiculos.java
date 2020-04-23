package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoLicenciaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoColorVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoMarcaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoModeloVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoResponsableVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoSubTipoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudVehiculoTipoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitAdminCatalogoVehiculos {
	ArrayList<BitacoraCambiosVO> compararMarca(CrudVehiculoMarcaVO newCrudVehiculoMarcaVO, CrudVehiculoMarcaVO oldCrudVehiculoMarcaVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararModelo(CrudVehiculoModeloVO newCrudVehiculoModeloVO, CrudVehiculoModeloVO oldCrudVehiculoModeloVO);
	ArrayList<BitacoraCambiosVO> compararSubtipo(CrudVehiculoSubTipoVO newCrudVehiculoSubTipoVO, CrudVehiculoSubTipoVO oldCrudVehiculoSubTipoVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararTipo(CrudVehiculoTipoVO newCrudVehiculoTipoVO, CrudVehiculoTipoVO oldCrudVehiculoTipoVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararColor(CrudVehiculoColorVO newCrudVehiculoColorVO, CrudVehiculoColorVO oldCrudVehiculoColorVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararResponsable(CrudVehiculoResponsableVO newCrudVehiculoResponsableVO, CrudVehiculoResponsableVO oldCrudVehiculoResponsableVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararTipoLicencia(CrudTipoLicenciaVO newCrudTipoLicenciaVO, CrudTipoLicenciaVO oldCrudTipoLicenciaVO, String oldEstatus);
}
