package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionDTO;

@Repository
public class RegionDAOImpl extends BaseDaoHibernate<RegionDTO> implements RegionDAO {

	@Override
	public RegionDTO buscarRegPorCod(String regCod) {
		Criteria criteria = getCurrentSession().createCriteria(RegionDTO.class);
		criteria.add(Restrictions.eq("regionCodigo", regCod));
		return (RegionDTO) criteria.uniqueResult();
	}

}
