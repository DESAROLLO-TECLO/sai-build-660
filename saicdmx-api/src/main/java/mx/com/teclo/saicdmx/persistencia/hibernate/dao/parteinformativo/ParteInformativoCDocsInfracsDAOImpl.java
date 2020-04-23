package mx.com.teclo.saicdmx.persistencia.hibernate.dao.parteinformativo;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.parteinformativo.ParteInformativoCDocsInfracsDTO;

@Repository
public class ParteInformativoCDocsInfracsDAOImpl extends BaseDaoHibernate<ParteInformativoCDocsInfracsDTO> implements ParteInformativoCDocsInfracsDAO{

	/***
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ParteInformativoCDocsInfracsDTO> buscarInfraccionesPorDocumento(Long documentoId) {
		Criteria criteria = getCurrentSession().createCriteria(ParteInformativoCDocsInfracsDTO.class);
		criteria.add(Restrictions.eq("parteInformativoCDocsInfracsPK.piId", documentoId));
		criteria.addOrder(Order.asc("parteInformativoCDocsInfracsPK.infracNum"));
		return (List<ParteInformativoCDocsInfracsDTO>) criteria.list();
	}
}
