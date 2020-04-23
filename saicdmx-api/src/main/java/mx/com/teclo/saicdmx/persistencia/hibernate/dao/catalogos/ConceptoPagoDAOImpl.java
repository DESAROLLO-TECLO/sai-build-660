package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ConceptoPagoDTO;

@Repository
public class ConceptoPagoDAOImpl extends BaseDaoHibernate<ConceptoPagoDTO> implements ConceptoPagoDAO {

	@Override
	public ConceptoPagoDTO buscarConceptoPagoPorCod(String conceptoPagoCod) {
		Criteria criteria = getCurrentSession().createCriteria(ConceptoPagoDTO.class);
		criteria.add(Restrictions.eq("conceptoCodigo", conceptoPagoCod));
		return (ConceptoPagoDTO) criteria.uniqueResult();
	}
}
