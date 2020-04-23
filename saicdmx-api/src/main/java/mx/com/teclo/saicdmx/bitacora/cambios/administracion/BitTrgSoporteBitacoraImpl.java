package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bitacora.SoporteBitacoraDTO;

@Repository
public class BitTrgSoporteBitacoraImpl extends BaseDaoHibernate<SoporteBitacoraDTO> implements BitTrgSoporteBitacora {

	@SuppressWarnings("unused")
	@Override
	@Transactional
	public Long consultaIdMaximoSoporteBitacora() {
		Criteria criteria = getCurrentSession().createCriteria(SoporteBitacoraDTO.class);
		criteria.setProjection(Projections.max("cambioId"));

		Long cambioId = (Long) criteria.uniqueResult() + 1;

		if (cambioId == null) {

			cambioId = 1l;
		}

		return cambioId;
	}
	
	

}
