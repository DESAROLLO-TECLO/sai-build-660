package mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias;

import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaProcesoDTO; 
	


@Repository("garantiasProcesoDAO")
public class GarantiasProcesoDAOImpl extends BaseDaoHibernate<GarantiaProcesoDTO> 
		implements GarantiasProcesoDAO {
	
}
