package mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsNuevoVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpParteInformativoNuevoImpl implements BitSpParteInformativoNuevo{

	@Override
	public List<BitacoraCambiosVO> guardarCambiosCrear(ParteInformativoCDocsNuevoVO newPICDNVO){
		// TODO Auto-generated method stub
		
		//Concepto a aplicar para la bitacora
		final long concepto = 6L;
		
		BitComponente componente = new BitComponente(1L, "");
		
		//Filtro a aplicar a la bitacora
		//No Aplica
		
		List<BitacoraCambiosVO> lista = new ArrayList<BitacoraCambiosVO>();
				
		lista.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					concepto,
					componente.getComponente(),
					"",
					newPICDNVO.getNoConsecutivo(),
					newPICDNVO.getCreadoPor() != null ? newPICDNVO.getCreadoPor() : 0L,
					newPICDNVO.getId().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
		));
		
		return lista;
	}

}
