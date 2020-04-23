package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.DepositosEmpleadosDTO;

@Repository
public class DepositosDAOImpl extends BaseDaoHibernate<DepositosEmpleadosDTO> implements DepositoEmpleadoDAO {
	
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public DepositosEmpleadosDTO depositoEmpleadoPorEmpId(Long id){
		Criteria criteria = getCurrentSession().createCriteria(DepositosEmpleadosDTO.class);
		//criteria.add(Restrictions.eq("depId.empId",id));
		criteria.createAlias("empleado", "empleado");
		criteria.add(Restrictions.eq("empleado.empId",id));
		List<DepositosEmpleadosDTO> listaEmp = (List<DepositosEmpleadosDTO>) criteria.list();
		if(CollectionUtils.isNotEmpty(listaEmp))
			return listaEmp.get(0);
		return null;
	}
}
