package mx.com.teclo.saicdmx.persistencia.hibernate.dao.administracion;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.DerechosDTO;

@Repository
public class DerechosDAOImpl extends BaseDaoHibernate<DerechosDTO> implements DerechosDAO {

	@Override
	public DerechosDTO getDerechosByUsuId(Long usuId,Long operId) {
		Criteria c = getCurrentSession().createCriteria(DerechosDTO.class);
		c.add(Restrictions.eq("id.usuId", usuId));
		c.add(Restrictions.eq("id.operId", operId));
		c.setMaxResults(1);
//		List<DerechosDTO> lista = (List<DerechosDTO>) c.list(); 
		return (DerechosDTO) c.uniqueResult();
	}
}
