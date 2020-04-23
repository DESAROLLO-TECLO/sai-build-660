package mx.com.teclo.saicdmx.bitacora.cambios.pagos;

import java.text.ParseException;
import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitTrgPagosBitNuevo {
	
		ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(PagoVO newPagoVo) throws ParseException;
}
