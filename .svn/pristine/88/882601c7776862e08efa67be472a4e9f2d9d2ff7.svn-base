package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.SectorDTO;

@Repository
public class SectorDAOImpl extends BaseDaoHibernate<SectorDTO> implements SectorDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SectorDTO> buscarSectores(Long estadoId, String status) {
		Criteria criteria =  getCurrentSession().createCriteria(SectorDTO.class);
		criteria.createAlias("del", "del");
		criteria.add(Restrictions.eq("del.estadoDTO.edoId", estadoId));
		criteria.add(Restrictions.eq("secStatus", status));
		criteria.addOrder(Order.asc("secCod"));
	//	criteria.setProjection(Projections.distinct(Projections.property("secId")));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public SectorDTO buscarSector(long idSector) {
		SectorDTO sectorDTO = findOne(idSector);
		return sectorDTO;
	}

	@Override
	public SectorDTO buscarSectorPorCod(String sectorCod) {
		Criteria criteria = getCurrentSession().createCriteria(SectorDTO.class);
		criteria.add(Restrictions.eq("secCod", sectorCod));
		return (SectorDTO) criteria.uniqueResult();
	}

	@Override
	public SectorDTO buscarSectorPorCodDel(String sectorCod, Long estadoId, Long delId) {
		Criteria criteria = getCurrentSession().createCriteria(SectorDTO.class);
		criteria.createAlias("del", "del");
		criteria.add(Restrictions.eq("secCod", sectorCod));
		criteria.add(Restrictions.eq("del.estadoDTO.edoId", estadoId));
		criteria.add(Restrictions.eq("del.delId.delId", delId));
		SectorDTO sectorDTO = (SectorDTO) criteria.uniqueResult(); 
		return sectorDTO;
	}
}
