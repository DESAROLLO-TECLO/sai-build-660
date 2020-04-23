package mx.com.teclo.saicdmx.bitacora.cambios.infraccionesDigitalizacion;

import java.text.ParseException;
import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitTrBitUpInfracDig {
	
	ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(RespuestaWSReasignaLineaCapturaVO newInfraccionVO, RespuestaWSReasignaLineaCapturaVO oldInfraccionVO) throws ParseException;

}
