package mx.com.teclo.saicdmx.persistencia.hibernate.dao.parteinformativo;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.parteinformativo.ParteInformativoCDocsInfracsDTO;

public interface ParteInformativoCDocsInfracsDAO extends BaseDao<ParteInformativoCDocsInfracsDTO>{

	/***
	 * @author Jesus Gutierrez
	 * @param documentoId
	 * @return Las infracciones y placas segun el documento ingresado
	 * @throws
	 */
	List<ParteInformativoCDocsInfracsDTO> buscarInfraccionesPorDocumento(Long documentoId);
}
