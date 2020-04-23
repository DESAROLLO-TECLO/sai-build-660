package mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros;

import java.text.ParseException;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.ControlAlmacenVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpSuministroAlmacen {

	BitacoraCambiosVO bitacoraCambiosAltaAlmacen(ControlAlmacenVO almacenVO) throws ParseException;

}
