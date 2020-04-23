package mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar;

import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoEstatusVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitRadarProcesarArchivoServlet {

	public BitacoraCambiosVO complementacionCompletoBit(RadarArchivoEstatusVO radarArchivoEstatusVO);
	public BitacoraCambiosVO cancelarComplementacionBit(RadarArchivoEstatusVO radarArchivoEstatusVO);
	
}
