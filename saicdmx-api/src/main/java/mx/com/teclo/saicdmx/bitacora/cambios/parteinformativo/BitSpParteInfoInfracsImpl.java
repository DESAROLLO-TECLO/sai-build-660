package mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo;


import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpParteInfoInfracsImpl implements BitSpParteInfoInfracs{

	@Override
	public BitacoraCambiosVO guardarCambiosAdicionInfrac(ParteInformativoInfracsVO newParteInfVO){
		
		//Concepto a aplicar para la bitacora
		final long concepto = 6L;
		final long componente = 3L;
		
		return new BitacoraCambiosVO(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
				concepto,
				componente,
				null,
				newParteInfVO.getInfracNum()+"-"+newParteInfVO.getInfracPlaca(),
				newParteInfVO.getModificadoPor() != null ? newParteInfVO.getModificadoPor() : 0L,
				newParteInfVO.getPiId().toString(),
				"",
				ParametrosBitacoraEnum.ORIGEN_W.getParametro()
			);
	}
	
	@Override
	public BitacoraCambiosVO guardarCambiosRemoverInfrac(ParteInformativoInfracsVO oldParteInfVO){
		//Concepto a aplicar para la bitacora
		final long concepto = 6L;
		final long componente = 5L;
		
		return new BitacoraCambiosVO(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
				concepto,
				componente,
				oldParteInfVO.getInfracNum()+"-"+oldParteInfVO.getInfracPlaca(),
				oldParteInfVO.getInfracNum()+"-"+oldParteInfVO.getInfracPlaca(),
				oldParteInfVO.getModificadoPor() != null ? oldParteInfVO.getModificadoPor() : 0L,
				oldParteInfVO.getPiId().toString(),
				"",
				ParametrosBitacoraEnum.ORIGEN_W.getParametro()
		);
	}

	@Override
	public BitacoraCambiosVO guardarNuevaInfraccionBoletaSancion(ParteInformativoInfracsVO oldParteInfVO){
		//Concepto a aplicar para la bitacora
		final long componente = 6L;
		final long concepto = 4L;
		
		return new BitacoraCambiosVO(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
				componente,
				concepto,
				"",
				oldParteInfVO.getInfracNum(),
				oldParteInfVO.getModificadoPor() != null ? oldParteInfVO.getModificadoPor() : 0L,
				oldParteInfVO.getPiId().toString(),
				"",
				ParametrosBitacoraEnum.ORIGEN_W.getParametro()
				);
		
	}
	
}
