package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CategoriaDTO;

@Repository
public class CategoriaDAOImpl extends BaseDaoHibernate<CategoriaDTO> implements CategoriaDAO {

	@Override
	public List<CategoriaDTO> buscarCategoriasActivas() {
		Criteria criteria = getCurrentSession().createCriteria(CategoriaDTO.class);
		criteria.add(Restrictions.eq("categoriaStatus", "A"));
		return criteria.list();
	}

	@Override
	public CategoriaDTO buscarCategoriaPorCod(String categoriaCod) {
		Criteria criteria = getCurrentSession().createCriteria(CategoriaDTO.class);
		criteria.add(Restrictions.eq("categoriaCodigo", categoriaCod));
		return (CategoriaDTO) criteria.uniqueResult();
	}

}
