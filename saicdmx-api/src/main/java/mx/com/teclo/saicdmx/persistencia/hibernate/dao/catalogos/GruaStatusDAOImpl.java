package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.GruaStatusDTO;

@Repository
public class GruaStatusDAOImpl extends BaseDaoHibernate<GruaStatusDTO> implements GruaStatusDAO {

	@Override
	public GruaStatusDTO buscarEstatusGruaPorCod(String estatusGruaCod) {
		Criteria criteria = getCurrentSession().createCriteria(GruaStatusDTO.class);
		criteria.add(Restrictions.eq("gruaStatusCod", estatusGruaCod));
		return (GruaStatusDTO) criteria.uniqueResult();
	}
}
