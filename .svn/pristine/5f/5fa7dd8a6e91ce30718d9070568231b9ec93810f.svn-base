package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.OperativoDTO;

@Repository
public class OperativoDAOImpl extends BaseDaoHibernate<OperativoDTO> implements OperativoDAO {

	@Override
	@SuppressWarnings("unchecked")
	public List<OperativoDTO> buscarOperativos() {
		return getCurrentSession().createCriteria(OperativoDTO.class).add(Restrictions.eq("operativoStatus", "A")).list();
	}
}
