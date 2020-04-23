package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.AreaDTO;

@Repository
public class AreaDAOImpl extends BaseDaoHibernate<AreaDTO> implements AreaDAO {

	@Override
	public AreaDTO buscarAreaPorCod(String areaCod) {
		Criteria criteria = getCurrentSession().createCriteria(AreaDTO.class);
		criteria.add(Restrictions.eq("areaCodigo", areaCod));
		return (AreaDTO) criteria.uniqueResult();
	}
}
