package mx.com.teclo.saicdmx.persistencia.hibernate.dao.infracciones;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionesImagenesDTO;

@Repository
public class InfraccionesImagenesDAOImpl extends BaseDaoHibernate<InfraccionesImagenesDTO> implements InfraccionesImagenesDAO {
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public InfraccionesImagenesDTO buscaFotoPorNombreArchivo(String nombre){
		Criteria criteria = getCurrentSession().createCriteria(InfraccionesImagenesDTO.class);
		criteria.add(Restrictions.eq("nombreArchivo",nombre));
		List<InfraccionesImagenesDTO> fotos = (List<InfraccionesImagenesDTO>) criteria.list();
		if(CollectionUtils.isNotEmpty(fotos))
			return fotos.get(0);
		return null;
	}
}
