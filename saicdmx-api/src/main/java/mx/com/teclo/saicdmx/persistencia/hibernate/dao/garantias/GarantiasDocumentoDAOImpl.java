package mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDocumentoDTO;

@Repository
public class GarantiasDocumentoDAOImpl extends BaseDaoHibernate<GarantiaDocumentoDTO>
implements GarantiasDocumentoDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<GarantiaDocumentoDTO> buscarTipoDocumentos() {

		Criteria criteria = getCurrentSession().createCriteria(GarantiaDocumentoDTO.class);
		criteria.add(Restrictions.not(Restrictions.eq("documentoId", new Integer(7))));
		criteria.addOrder(Order.asc("documentoId"));

		return criteria.list();
		 
	}
	
	

}
