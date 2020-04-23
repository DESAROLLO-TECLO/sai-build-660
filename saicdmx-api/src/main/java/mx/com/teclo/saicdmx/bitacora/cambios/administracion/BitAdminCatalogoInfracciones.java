package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEstatusInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.catalogos.CrudEventoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitAdminCatalogoInfracciones {
	ArrayList<BitacoraCambiosVO> comparaeEventos(CrudEventoVO newCrudEventoVO, CrudEventoVO oldCrudEventoVO, String oldEstatus);
	ArrayList<BitacoraCambiosVO> compararEstatus (CrudEstatusInfraccionVO newCrudEstatusInfraccionVO, CrudEstatusInfraccionVO oldCrudEstatusInfraccionVO, String oldEstatus);
}
