package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatalogoWebDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatalogoWebOpcionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ConcesionariaDTO;

@Repository
public class ConcesionariaDAOImpl extends BaseDaoHibernate<ConcesionariaDTO> implements ConcesionariaDAO {

	@Override
	public ConcesionariaDTO getConcesionariaGruas(Long concesionariaId, String gruaId) {

		String hql = "select c.concesionariaPrefijo,c.arrastreConcesionaria c.idConsesionaria "
				+ "FROM Consesionaria c,Gruas g " + "WHERE g.idConcesionaria = :c.idConcesionaria"
				+ "AND g.idGrua = :gruaId";

		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("idConcesionaria", concesionariaId);
		query.setParameter("gruaId", gruaId);

		return (ConcesionariaDTO) query.uniqueResult();
	}

	@Override
	public void actualizarConcesionaria(ConcesionariaDTO concesionariaDTO) {
		this.update(concesionariaDTO);
	}

	@Override
	public ConcesionariaDTO buscarConcesionariaPorId(Long concesionariaId) {
		return this.findById(concesionariaId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConcesionariaDTO> buscarConcesionariasOrdenadas(){
		Criteria criteria = getCurrentSession().createCriteria(ConcesionariaDTO.class);
		//criteria.add(Restrictions.eq("catalogoWebDTO.catalogoId", ));
//		criteria.addOrder(Order.asc("concesionariaNombre"));
		return (List<ConcesionariaDTO>) criteria.list();
	}

	@Override
	public ConcesionariaDTO buscarconcesionariaPorCod(String concesionariaCod) {
		Criteria criteria = getCurrentSession().createCriteria(ConcesionariaDTO.class);
		criteria.add(Restrictions.eq("concesionariaCodigo", concesionariaCod));
		return (ConcesionariaDTO) criteria.uniqueResult();
	}
}
