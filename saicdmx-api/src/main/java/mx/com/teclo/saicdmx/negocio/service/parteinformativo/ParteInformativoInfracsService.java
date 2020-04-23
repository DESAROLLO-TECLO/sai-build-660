package mx.com.teclo.saicdmx.negocio.service.parteinformativo;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.parteinformativo.ParteInformativoBoletaInfracsDTO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsPorBolsVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsPorDocsVO;

public interface ParteInformativoInfracsService {
	//List<ParteInformativoCDocsInfracsDTO> buscarInfraccionesPorDocumento(Long documentoId);
	
	List<ParteInformativoInfracsPorDocsVO> buscarInfraccionesPorDocumento(Long documentoId);
	
	List<ParteInformativoInfracsPorBolsVO> buscarInfraccionesPorBoleta(Long boletaId);
}
