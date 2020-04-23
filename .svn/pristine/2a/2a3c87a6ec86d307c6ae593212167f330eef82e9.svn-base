package mx.com.teclo.saicdmx.persistencia.hibernate.dao.articulos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.SancionesArticulosDTO;

@Repository
public class SancionesArticulosDAOImpl extends BaseDaoHibernate<SancionesArticulosDTO> implements SancionesArticulosDAO {

	@Override
	public SancionesArticulosDTO buscarSancionesArticulo(Long idArticulo) {
		Criteria criteria = getCurrentSession().createCriteria(SancionesArticulosDTO.class);
		criteria.add(Restrictions.eq("sancionId.articulo.artId", idArticulo));
//		criteria.add(Restrictions.eq("sanStatus", "A"));
		return (SancionesArticulosDTO) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SancionesArticulosDTO> buscarSancionesPorArticulo(Long idArticulo) {
		Criteria criteria = getCurrentSession().createCriteria(SancionesArticulosDTO.class);
		criteria.add(Restrictions.eq("articuloInfraccion.artId", idArticulo));
//		criteria.add(Restrictions.eq("sanStatus", "A"));
		return (List<SancionesArticulosDTO>) criteria.list();
	}

}
