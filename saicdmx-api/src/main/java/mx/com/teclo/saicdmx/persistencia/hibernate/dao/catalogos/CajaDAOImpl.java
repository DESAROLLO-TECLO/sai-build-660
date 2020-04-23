package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;

@Repository
public class CajaDAOImpl extends BaseDaoHibernate<CajaDTO> implements CajaDAO {

	@Override
	@Transactional(readOnly = true)
	public CajaDTO buscarCajaEmpleado(Long empleadoId) {
		return (CajaDTO) getCurrentSession().createCriteria(CajaDTO.class).add(Restrictions.eq("empleado.empId", empleadoId)).uniqueResult();
	}
	
	@Override
	public CajaDTO buscarCajaEmpleadoPlaca(String empleadoPlaca) {
		return (CajaDTO) getCurrentSession().createCriteria(CajaDTO.class)
				.createAlias("empleado", "emp")
				.add(Restrictions.eq("emp.empPlaca", empleadoPlaca)).uniqueResult();
	}
	
	@Override
	public CajaDTO buscarCajaPorCod(String cajaCod) {
		return (CajaDTO) getCurrentSession().createCriteria(CajaDTO.class).add(Restrictions.eq("cajaCod", cajaCod)).uniqueResult();
	}
	
	@Transactional(readOnly = true)
	public CajaDTO buscarCajaPorId(Long cajaId){
		return (CajaDTO) getCurrentSession().createCriteria(CajaDTO.class).
				add(Restrictions.eq("cajaId", cajaId)).uniqueResult();
	}
	@Transactional(readOnly = true)
	public CajaDTO cajaPorId(Long cajaId){
		Query query = getCurrentSession().createQuery("select c from CajaDTO c where cajaId = :cajaId");
		query.setParameter("cajaId", cajaId);
        return (CajaDTO) query.uniqueResult();
	}
	@Transactional(readOnly = true)
	public CajaDTO cajaPorCod(String cajaCod){
		Query query = getCurrentSession().createQuery("select c from CajaDTO c where cajaCod = :cajaCod");
		query.setParameter("cajaCod", cajaCod);
        return (CajaDTO) query.uniqueResult();
	}
}
