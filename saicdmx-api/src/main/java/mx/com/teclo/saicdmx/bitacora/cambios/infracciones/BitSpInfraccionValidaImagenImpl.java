package mx.com.teclo.saicdmx.bitacora.cambios.infracciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionValidaImagenSPVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class BitSpInfraccionValidaImagenImpl implements BitSpInfraccionValidaImagen {
	@Autowired
    private BitacoraCambiosService  bitacoraCambiosService;
	
	@Override
	public void validaSpImagen(InfraccionValidaImagenSPVO infraccionValidaImagenSPVO) {
		bitacoraCambiosService.guardarBitacoraCambiosParametros(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 5L, 18L,
				infraccionValidaImagenSPVO.getP_infrac_num(), infraccionValidaImagenSPVO.getP_estatus(),
				infraccionValidaImagenSPVO.getP_emp_id(), infraccionValidaImagenSPVO.getP_infrac_num(), "",
				ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		
	}

}
