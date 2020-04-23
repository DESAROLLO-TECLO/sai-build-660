package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.VInfraccionUsuariosDTO;

@Repository
public class VInfraccionUsuariosDAOImpl extends BaseDaoHibernate<VInfraccionUsuariosDTO> implements VInfraccionUsuariosDAO {

	@Override
	public VInfraccionUsuariosDTO buscarOficial(String placa) {
		Criteria criteria = getCurrentSession().createCriteria(VInfraccionUsuariosDTO.class);
		criteria.add(Restrictions.eq("emplPlaca", placa));
		return (VInfraccionUsuariosDTO) criteria.uniqueResult();
	}

}
