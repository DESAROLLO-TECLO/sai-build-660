package mx.com.teclo.saicdmx.util.comun;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.ParametrosPagosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.ParametrosPagosBloqueoVO;

public class AssemblerParametroPagos {
	
	public static ParametrosPagosBloqueoVO toParametrosPagosBloqueoVO(ParametrosPagosDTO paramPagDTO){
		ParametrosPagosBloqueoVO parametrosBloqueVO = new ParametrosPagosBloqueoVO();
		if(paramPagDTO != null){
			parametrosBloqueVO.setId(paramPagDTO.getIdParametroPago());
			parametrosBloqueVO.setStBloqueoPago(paramPagDTO.getStBloqueoPago());
			parametrosBloqueVO.setStActivo(paramPagDTO.getStActivo());
			
		}
		else
		{
			parametrosBloqueVO = null;
		}
		
		
		
		return parametrosBloqueVO;
	}

}
