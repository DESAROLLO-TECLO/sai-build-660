package mx.com.teclo.saicdmx.persistencia.hibernate.dao.empleado;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;

@Repository
public class EmpleadoDAOImpl extends BaseDaoHibernate<EmpleadosDTO> implements EmpleadoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public EmpleadosDTO buscarEmpleadoParaAltaInfraccion(String placa) {

		List<EmpleadosDTO> empleados = new ArrayList<EmpleadosDTO>();

		Criteria criteria = getCurrentSession().createCriteria(EmpleadosDTO.class);

		Disjunction or = Restrictions.disjunction();
		//or.add(Restrictions.eq("empTip.empTipId", 1L));
		//or.add(Restrictions.eq("empTip.empTipId", 7L));
		//or.add(Restrictions.eq("empTip.empTipId", 10L));

		Conjunction and = Restrictions.conjunction();
		and.add(Restrictions.eq("empCod", placa));
		//and.add(Restrictions.eq("empStatus", "A"));
		and.add(or);

		criteria.add(and);

		empleados = (List<EmpleadosDTO>) criteria.list();
		if (CollectionUtils.isNotEmpty(empleados))
			return empleados.get(0);
		return null;
	}

	@Override
	public EmpleadosDTO getEmpleadoByPlaca(String empPlaca) {
		Criteria query = getCurrentSession().createCriteria(EmpleadosDTO.class).add(Restrictions.eq("empPlaca", empPlaca)).
				add(Restrictions.eq("empStatus", "A"));
		return (EmpleadosDTO) query.uniqueResult();

	}

	@Override
 	public EmpleadosDTO actualizaRFCEmpleado(String placaOficial, String rfc) {

		EmpleadosDTO empleadoDTO = null;
		Criteria criteria = getCurrentSession().createCriteria(EmpleadosDTO.class);
		criteria.add(Restrictions.eq("empPlaca", placaOficial));
		empleadoDTO = (EmpleadosDTO) criteria.uniqueResult();
		empleadoDTO.setEmpRFC(rfc);
		getCurrentSession().saveOrUpdate(empleadoDTO);
		
		return empleadoDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmpleadosDTO> consultaRFCEmpleado(String placaOficial, String rfc) {
		Criteria criteria = getCurrentSession().createCriteria(EmpleadosDTO.class);
		criteria.add(Restrictions.eq("empRFC", rfc));
		return  criteria.list();
	}
	
	@Override
	@Transactional
	public EmpleadosDTO consultaEmpleadoAutorizaSoporte(String placaOficia) {
		Criteria query = getCurrentSession().createCriteria(EmpleadosDTO.class).add(Restrictions.eq("empPlaca", placaOficia)).
				add(Restrictions.eq("empStatus", "A")).add(Restrictions.in("empTip.empTipId", new Long []{3l,4l}));
		return (EmpleadosDTO) query.uniqueResult();
	}

	@Override
	@Transactional
	public EmpleadosDTO find(Long empId) {
		Criteria query = getCurrentSession().createCriteria(EmpleadosDTO.class).add(Restrictions.eq("empId", empId));
		return (EmpleadosDTO) query.uniqueResult();
	}	
}
