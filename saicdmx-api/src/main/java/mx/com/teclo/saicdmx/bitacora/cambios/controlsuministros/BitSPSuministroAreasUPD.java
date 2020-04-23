package mx.com.teclo.saicdmx.bitacora.cambios.controlsuministros;

import java.text.ParseException;

import mx.com.teclo.saicdmx.persistencia.vo.controlsuministros.OficialModificacionVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSPSuministroAreasUPD {
	public BitacoraCambiosVO cambiosBitacoraCambioAuxiliar(OficialModificacionVO valores) throws ParseException;
}
