package mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarArchivoEstatusVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitRadarProcesarArchivoServletImpl implements BitRadarProcesarArchivoServlet{

	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Override
	public BitacoraCambiosVO complementacionCompletoBit(RadarArchivoEstatusVO radarArchivoEstatusVO) {
		
		final long componente = 8L;
		final long concepto = 2L;
		
		return new BitacoraCambiosVO(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
				componente,
				concepto,
				"",
				radarArchivoEstatusVO.getArchivoNombre(),
				usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
				String.valueOf(radarArchivoEstatusVO.getArchivoId()),
				"",
				ParametrosBitacoraEnum.ORIGEN_W.getParametro()
			);
	}

	@Override
	public BitacoraCambiosVO cancelarComplementacionBit(RadarArchivoEstatusVO radarArchivoEstatusVO) {
		
		final long componente = 8L;
		final long concepto = 5L;
		
		return new BitacoraCambiosVO(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
				componente,
				concepto,
				"",
				radarArchivoEstatusVO.getArchivoNombre(),
				usuarioFirmadoService.getUsuarioFirmadoVO().getId(),
				String.valueOf(radarArchivoEstatusVO.getArchivoId()),
				"",
				ParametrosBitacoraEnum.ORIGEN_W.getParametro()
			);
	}

    
}
