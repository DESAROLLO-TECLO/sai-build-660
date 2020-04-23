package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ComponentesInventarioDTO;

@Repository
public class ComponentesInventarioDAOImpl extends BaseDaoHibernate<ComponentesInventarioDTO>
		implements ComponentesInventarioDAO {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(ComponentesInventarioDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<ComponentesInventarioDTO> buscarComponentesInventario(String status) {
		return getCurrentSession().createCriteria(ComponentesInventarioDTO.class)
				.add(Restrictions.eq("statusComponente", status)).list();
	}

	@Override
	public ComponentesInventarioDTO buscarComponenteInventarioPorCOd(String componenteInventarioCod) {
		Criteria criteria = getCurrentSession().createCriteria(ComponentesInventarioDTO.class);
		criteria.add(Restrictions.eq("codigoComponente", componenteInventarioCod));
		return (ComponentesInventarioDTO) criteria.uniqueResult();
	}
}
