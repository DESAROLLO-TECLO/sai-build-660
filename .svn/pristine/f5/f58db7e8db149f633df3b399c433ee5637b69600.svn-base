package mx.com.teclo.saicdmx.persistencia.hibernate.dao.impugnacion;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionImpugnacionDTO;


/**
 * 
 * @author javier07
 *
 */
@Repository
public class ImpugnacionDAOImpl extends BaseDaoHibernate<InfraccionImpugnacionDTO> implements ImpugnacionDAO{

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<InfraccionImpugnacionDTO> buscarImppugnacion(String tipoBusqueda, String valor) {
		
		Criteria criteria =  getCurrentSession().createCriteria(InfraccionImpugnacionDTO.class);

		if (tipoBusqueda.equals("numJuicio" )){			
			criteria.add(Restrictions.eq("impugnacionJuicio", valor));
		}
		
		if (tipoBusqueda.equals("numOficioJuridico")){
			criteria.add(Restrictions.eq("impugnacionOficioJur", valor));
		}
		
	   return (List<InfraccionImpugnacionDTO>)criteria.list();
			
	}

}
