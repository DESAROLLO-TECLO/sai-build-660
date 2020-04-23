package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.RegionDepositoDTO;

@Repository
public class RegionDepositoDAOImpl extends BaseDaoHibernate<RegionDepositoDTO> implements RegionDepositoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<RegionDepositoDTO> buscarRegionesPorEstado(Long estadoId) {
		Criteria criteria = getCurrentSession().createCriteria(RegionDepositoDTO.class);
		criteria.add(Restrictions.eq("estadoDTO.edoId", estadoId));
		return (List<RegionDepositoDTO>) criteria.list();
	}

	@Override
	public RegionDepositoDTO buscarRegionesPorCodigo(String regionDepostioCod) {
		Criteria criteria = getCurrentSession().createCriteria(RegionDepositoDTO.class);
		criteria.add(Restrictions.eq("regionCodigo", regionDepostioCod));
		return (RegionDepositoDTO) criteria.uniqueResult();
	}	
}
