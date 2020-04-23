package mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar;

import java.text.ParseException;
import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitTrBitUpInfracRadar {
	
	ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(RespuestaWSReasignaLineaCapturaVO newInfraccionVO, RespuestaWSReasignaLineaCapturaVO oldInfraccionVO) throws ParseException;

}
