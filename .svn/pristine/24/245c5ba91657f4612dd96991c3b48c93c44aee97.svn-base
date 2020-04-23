package mx.com.teclo.saicdmx.persistencia.hibernate.dao.bloqueohh;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bloquehohh.BloqueohhCatTipoBloqueoDTO;
 

@Repository
public class BloqueohhCatTipoBloqueoDAOImpl extends BaseDaoHibernate<BloqueohhCatTipoBloqueoDTO> implements BloqueohhCatTipoBloqueoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<BloqueohhCatTipoBloqueoDTO> obtenerTipoBloqueo() {
		Criteria criteria = getCurrentSession().createCriteria(BloqueohhCatTipoBloqueoDTO.class);
		criteria.add(Restrictions.eq("activo", new Integer(1)));
		criteria.addOrder(Order.desc("tipoBloqueoId"));
 		return  criteria.list();	
 	
	}

}
