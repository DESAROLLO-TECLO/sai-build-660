package mx.com.teclo.saicdmx.persistencia.hibernate.dao.parteinformativo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.parteinformativo.ParteInformativoBoletaInfracsDTO;

@Repository
public class ParteInformativoBoletaInfracsDAOImpl extends BaseDaoHibernate<ParteInformativoBoletaInfracsDTO> implements ParteInformativoBoletaInfracsDAO{

	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ParteInformativoBoletaInfracsDTO> buscarInfraccionesPorBoleta(Long boletaId) {
		Criteria criteria = getCurrentSession().createCriteria(ParteInformativoBoletaInfracsDTO.class);
		criteria.add(Restrictions.eq("parteInformativoBoletaInfracsPK.piId", boletaId));
		criteria.addOrder(Order.asc("parteInformativoBoletaInfracsPK.infracNum"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<ParteInformativoBoletaInfracsDTO>) criteria.list();
	}

}
