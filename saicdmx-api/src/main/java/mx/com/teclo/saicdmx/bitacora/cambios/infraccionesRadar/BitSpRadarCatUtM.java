package mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar;

import java.text.ParseException;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarCatConsultaUTVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpRadarCatUtM {
        
	public List<BitacoraCambiosVO> guardarCambiosBitacoraUdateUT(RadarCatConsultaUTVO convertVOEdit, RadarCatConsultaUTVO convertVOBd) throws ParseException;
	public BitacoraCambiosVO guardarNewUTBit(RadarCatConsultaUTVO newUTVO);
	   
}
