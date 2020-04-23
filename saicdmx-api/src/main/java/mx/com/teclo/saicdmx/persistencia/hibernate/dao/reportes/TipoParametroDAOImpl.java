package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;



import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoParametroDTO;

@Repository("TipoParametroDAO")
public class TipoParametroDAOImpl extends BaseDaoHibernate<TipoParametroDTO> implements TipoParametroDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoParametroDTO> listaTiposParametro() {
		Criteria c = getCurrentSession().createCriteria(TipoParametroDTO.class);
		List<TipoParametroDTO> listaTipoParam = c.list();
		return listaTipoParam;
	}

}
