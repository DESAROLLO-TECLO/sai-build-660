package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.AgrupamientosDTO;

@Repository
public class AgrupamientosDAOImpl extends BaseDaoHibernate<AgrupamientosDTO> implements AgrupamientosDAO {
	@Override
	public AgrupamientosDTO buscarAgrupamiento(Long idAgrupamiento) {
		AgrupamientosDTO agrupamientosDTO = findOne(idAgrupamiento);

		return agrupamientosDTO;
	}

	@Override
	public void guardarAgrupamiento(AgrupamientosDTO agrupamientoDTO) {
		Long idMaximo = (Long) getCurrentSession().createCriteria(AgrupamientosDTO.class).setProjection(Projections.max("AGRP_ID")).uniqueResult();
		idMaximo = idMaximo + 1;

		agrupamientoDTO.setAgrupacionId(idMaximo);

		this.saveOrUpdate(agrupamientoDTO);
	}

	@Override
	public AgrupamientosDTO buscarAgrupamientoPorCod(String agrupamientoCod) {
		Criteria criteria = getCurrentSession().createCriteria(AgrupamientosDTO.class);
		criteria.add(Restrictions.eqOrIsNull("agrupacionCodigo", agrupamientoCod.toUpperCase()));
		return (AgrupamientosDTO) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AgrupamientosDTO> buscarAgrupamientosOrdenados() {
		Criteria criteria = getCurrentSession().createCriteria(AgrupamientosDTO.class);
		criteria.addOrder(Order.asc("agrupacionId"));
		return criteria.list();
	}
}
