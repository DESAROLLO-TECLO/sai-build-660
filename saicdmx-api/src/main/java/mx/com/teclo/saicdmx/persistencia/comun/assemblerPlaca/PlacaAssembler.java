package mx.com.teclo.saicdmx.persistencia.comun.assemblerPlaca;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.placas.PlacasDTO;
import mx.com.teclo.saicdmx.persistencia.vo.placas.PlacasVO;

public class PlacaAssembler {
	public PlacasVO obtenerPlacasVO(PlacasDTO placasDTO) {
		PlacasVO placasVO = new PlacasVO();
		placasVO.setPlacaId(placasDTO.getPlacaId());
		placasVO.setPlacaCodigo(placasDTO.getPlacaCodigo());
		placasVO.setPlacaStatus(placasDTO.getPlacaStatus());
		placasVO.setObservaciones(placasDTO.getObservaciones());
		return placasVO;
	}

	public PlacasDTO obtenerPlacasDTO(PlacasVO placasVO) {
		PlacasDTO placasDTO = new PlacasDTO();
		placasDTO.setPlacaId(placasVO.getPlacaId());
		placasDTO.setPlacaCodigo(placasVO.getPlacaCodigo());
		placasDTO.setPlacaStatus(placasVO.getPlacaStatus());
		placasDTO.setObservaciones(placasVO.getObservaciones());
		return placasDTO;
	}
}
