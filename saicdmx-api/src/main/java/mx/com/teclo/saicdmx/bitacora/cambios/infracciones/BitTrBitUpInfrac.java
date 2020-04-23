package mx.com.teclo.saicdmx.bitacora.cambios.infracciones;

import java.text.ParseException;
import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VSSPInfracConsGralFVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitTrBitUpInfrac {

	 ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(AltaInfraccionSPVO newInfraccionVO, VSSPInfracConsGralFVO listaInfraccionesVO) throws ParseException;
	 ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(VSSPInfracConsGralFVO newInfraccionVO, VSSPInfracConsGralFVO oldInfraccionVO) throws ParseException;
}