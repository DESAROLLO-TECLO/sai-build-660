package mx.com.teclo.saicdmx.bitacora.cambios.infracciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionSPVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class BitSpInfraccionesGeneralFMplImpl implements BitSpInfraccionesGeneralFMpl{

	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	
	@Override
	public void altaBitSpInfraccionesGral(AltaInfraccionSPVO altaInfraccionSPVO) {
		String numInfraccion = altaInfraccionSPVO.getP_resultado() != null ? altaInfraccionSPVO.getP_resultado().substring(0, 11) : null;
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
			ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), // Nombre de la tabla
			5L,	// Componente 
			1L, // Concepto
			"", // INFRAC_NUM_ORIGINAL
			numInfraccion, // INFRAC_NUM_FINAL
			Long.valueOf(altaInfraccionSPVO.getP_modificado_por().toString()),	// Modificado por 
			numInfraccion, //tipoInfraccionVO.getNumInfraccion(),  // Id del registro al que se realizó el cambio.
			"",  // Descripcion del registro modificado.
			ParametrosBitacoraEnum.ORIGEN_W.getParametro() // Origen W / H
		);
		
	}

}
