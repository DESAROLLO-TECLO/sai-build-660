package mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros;

import java.text.ParseException;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.SuministroAreasVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpSuministroNuevo {

	BitacoraCambiosVO bitacoraCambiosAltaSuministro(SuministroAreasVO suministroVO) throws ParseException;

}
