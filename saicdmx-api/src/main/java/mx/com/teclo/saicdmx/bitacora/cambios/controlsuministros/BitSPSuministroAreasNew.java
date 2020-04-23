package mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros;

import java.text.ParseException;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialNuevoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSPSuministroAreasNew {

	BitacoraCambiosVO cambiosBitacoraAltaAuxiliar(OficialNuevoVO valoresAux) throws ParseException;

}
