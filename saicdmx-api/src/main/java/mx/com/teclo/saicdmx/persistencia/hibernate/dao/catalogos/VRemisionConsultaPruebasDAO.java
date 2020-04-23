package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VRemisionConsultaPruebasDTO;

public interface VRemisionConsultaPruebasDAO extends BaseDao<VRemisionConsultaPruebasDTO> {

	public VRemisionConsultaPruebasDTO buscarIngreso(String numeroInfraccion);

}
