package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.apache.ibatis.type.Alias;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.UTId;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.UnidadTerritorialDTO;

@Repository
public class UnidadTerritorialDAOImpl extends BaseDaoHibernate<UnidadTerritorialDTO> implements UnidadTerritorialDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<UnidadTerritorialDTO> buscarUnidadesTerritorialesPorSector(Long sectorId) {
		Criteria criteria = getCurrentSession().createCriteria(UnidadTerritorialDTO.class);
		criteria.add(Restrictions.eq("sectorDTO.secId", sectorId));
		criteria.addOrder(Order.asc("sectorDTO.secId"));
		return criteria.list();
	}

	@Override
	public UnidadTerritorialDTO buscarUnidadTerritorial(Long utId, Long secId) {
		UTId uttId = new UTId();
		uttId.setSecId(secId);
		uttId.setUtId(utId);

		UnidadTerritorialDTO unidadTerritorialDTO = findOne(utId);

		return unidadTerritorialDTO;
	}

	@Override
	public UnidadTerritorialDTO buscarUnidadTerritorialPorCod(String utCod, Long secId) {
		Criteria criteria = getCurrentSession().createCriteria(UnidadTerritorialDTO.class);
		criteria.add(Restrictions.eq("utCod", utCod));
		criteria.add(Restrictions.eq("sectorDTO.secId",secId));
		return (UnidadTerritorialDTO) criteria.uniqueResult();
	}
}
