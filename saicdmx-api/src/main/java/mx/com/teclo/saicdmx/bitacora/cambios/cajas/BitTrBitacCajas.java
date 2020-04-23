package mx.com.teclo.saicdmx.bitacora.cambios.cajas;

import java.text.ParseException;
import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitTrBitacCajas {
	
	ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(VCajaConsultaVO newCajaVO, VCajaConsultaVO oldCajaVO) throws ParseException;
}
