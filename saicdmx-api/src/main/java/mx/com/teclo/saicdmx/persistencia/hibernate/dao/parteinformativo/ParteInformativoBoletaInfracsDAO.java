package mx.com.teclo.saicdmx.persistencia.hibernate.dao.parteinformativo;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.parteinformativo.ParteInformativoBoletaInfracsDTO;

public interface ParteInformativoBoletaInfracsDAO extends BaseDao<ParteInformativoBoletaInfracsDTO>{

	/***
	 * @author Jesus Gutierrez
	 * @param boletaId
	 * @return Las infracciones encontradas segun la boleta ingresada
	 * @throws
	 */
	List<ParteInformativoBoletaInfracsDTO> buscarInfraccionesPorBoleta(Long boletaId);

}
