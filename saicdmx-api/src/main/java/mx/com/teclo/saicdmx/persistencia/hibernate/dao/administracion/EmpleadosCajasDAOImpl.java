package mx.com.teclo.saicdmx.persistencia.hibernate.dao.administracion;

import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.EmpleadosCajasDTO;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@Repository
public class EmpleadosCajasDAOImpl extends BaseDaoHibernate<EmpleadosCajasDTO> implements EmpleadosCajasDAO {

	@Override
	public EmpleadosCajasDTO getEmpCajasById(Long cajaId) {
		Criteria c = getCurrentSession().createCriteria(EmpleadosCajasDTO.class);
		c.add(Restrictions.eq("id.cajaId", cajaId));
		return (EmpleadosCajasDTO) c.uniqueResult();
	}
}
