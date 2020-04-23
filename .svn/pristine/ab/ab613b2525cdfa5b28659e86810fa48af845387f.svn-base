package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.SectorDTO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudAgrupamientoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudDelegacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEstadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudRegionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudSectorVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoAlarmaVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudTipoEmpleadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudUnidadeTerritorialVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitAdminCatalogoAgrupamientos {
	ArrayList<BitacoraCambiosVO> compararTipoAgrupamiento(CrudAgrupamientoVO newCrudAgrupamientoVO, CrudAgrupamientoVO oldCrudAgrupamientoVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararDelegaciones(CrudDelegacionVO newCrudDelegacionVO, CrudDelegacionVO oldCrudDelegacionVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararEstado(CrudEstadoVO newCrudEstadoVO, CrudEstadoVO oldCrudEstadoVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> comaprarRegione(CrudRegionVO newCrudRegionVO, CrudRegionVO oldCrudEstadoVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararSector(CrudSectorVO newCrudSectorVO, CrudSectorVO oldCrudSectorVO, SectorDTO sectorSinDel, SectorDTO sectoConDel);
	ArrayList<BitacoraCambiosVO> compararUnidadTeritorial(CrudUnidadeTerritorialVO newCrudUnidadeTerritorialVO, CrudUnidadeTerritorialVO oldCrudUnidadeTerritorialVO);
	
}
