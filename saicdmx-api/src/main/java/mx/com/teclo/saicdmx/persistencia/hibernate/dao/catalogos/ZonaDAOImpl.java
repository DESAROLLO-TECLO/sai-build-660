package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ZonaDTO;

@Repository
public class ZonaDAOImpl extends BaseDaoHibernate<ZonaDTO> implements ZonaDAO {

	@Override
	public ZonaDTO busarZonaPorCodigo(String zonaCod) {
		Criteria criteria = getCurrentSession().createCriteria(ZonaDTO.class);
		criteria.add(Restrictions.eq("zonaCodigo", zonaCod));
		return (ZonaDTO) criteria.uniqueResult();
	}
}
