package mx.com.teclo.saicdmx.persistencia.hibernate.dao.certificados;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.DateType;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadoFirmaDTO;
/**
 * 
 * @author javier07
 *
 */
@Repository
public class CertificadoDAOImpl extends BaseDaoHibernate<EmpleadoFirmaDTO> implements CertificadoDAO{

	 @Value("${app.config.codigo}")
	 private String cd_aplicacion;
	
	/**
	 * @author javier07
	 * {@inheritDoc}
	 */
	@Override
	public EmpleadoFirmaDTO obtieneCertificadoPorPlaca(Long empId) {
		Criteria criteria = getCurrentSession().createCriteria(EmpleadoFirmaDTO.class);
		criteria.add(Restrictions.eq("empleadoDTO.empId", empId));
		criteria.add(Restrictions.eq("estatus", new Integer(1)));
		return (EmpleadoFirmaDTO) criteria.uniqueResult();
	}


	/**
	 * @author javier07
	 * {@inheritDoc}
	 */
	@Override
	public EmpleadoFirmaDTO deleteCertificadoByPlaca(Long idEmpleado) {

		EmpleadoFirmaDTO empleadoFirmaDTO;
		Criteria criteria = getCurrentSession().createCriteria(EmpleadoFirmaDTO.class);
		criteria.add(Restrictions.eq("empleadoDTO.empId", idEmpleado));
		criteria.add(Restrictions.eq("estatus", new Integer(1)));
		empleadoFirmaDTO = (EmpleadoFirmaDTO) criteria.uniqueResult();	
		empleadoFirmaDTO.setEstatus(new Integer(0));		
		getCurrentSession().saveOrUpdate(empleadoFirmaDTO);
		return empleadoFirmaDTO;
		
	}


	/**
	 * @author javier07
	 * {@inheritDoc}
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<EmpleadoFirmaDTO> obtieneCertificados(String tipoBusqueda, String paramBusqueda,String fechaInicio, String fechaFin, Integer  validado ) {
	    
		List<EmpleadoFirmaDTO> listEmpleadoFirmaDTO;
		Criteria criteria = getCurrentSession().createCriteria(EmpleadoFirmaDTO.class);
		criteria.add(Restrictions.eq("estatus", new Integer(1)));
		criteria.createAlias("empleadoDTO", "emp");
 		criteria.createAlias("emp.perfilUsuarios", "perfiles");
		criteria.createAlias("perfiles.aplicacionDTO", "aplicacion");
		criteria.add(Restrictions.eq("aplicacion.codigo",cd_aplicacion));

		if (validado == 0 || validado == 1){
			criteria.add(Restrictions.eq("validado", validado));	
		}
		
		if (  tipoBusqueda != null &   paramBusqueda!=null ) {
			criteria.add(Restrictions.like("emp." + tipoBusqueda, paramBusqueda, MatchMode.ANYWHERE));
		} 
		if (fechaInicio != null &  fechaFin != null) {
			criteria.add(Restrictions.sqlRestriction(" TRUNC(CERT_VALIDO_HASTA) >=  to_date ( '"+ fechaInicio + "', 'dd/MM/yyyy') "
					+ " AND TRUNC(CERT_VALIDO_HASTA) <= to_date('"+ fechaFin +"', 'dd/MM/yyyy') "));
			
//			criteria.add(Restrictions.between("certValidoHasta", fechaInicio, fechaFin));
//			 criteria.addOrder(Order.asc("certValidoHasta"));
		} 
		if (fechaInicio != null ) {
			criteria.add(Restrictions.sqlRestriction(" TRUNC(CERT_VALIDO_HASTA) >=  to_date ( '"+ fechaInicio + "', 'dd/MM/yyyy') "));
//			criteria.add(Restrictions.ge("certValidoHasta",fechaInicio));
		}
		if(fechaFin != null){
			criteria.add(Restrictions.sqlRestriction(" TRUNC(CERT_VALIDO_HASTA) <=  to_date ( '"+ fechaFin + "', 'dd/MM/yyyy') "));
//			criteria.add(Restrictions.lt("certValidoHasta",fechaFin));
		}
		
	    criteria.addOrder(Order.asc("certValidoHasta"));
		listEmpleadoFirmaDTO = criteria.list();

		return listEmpleadoFirmaDTO;
	}
	

	/**
	 * @author javier07
	 * {@inheritDoc}
	 */
	@Override
	public EmpleadoFirmaDTO updateStatusCertificado(Long idEmpleado) {
		
		EmpleadoFirmaDTO empleadoFirmaDTO;
		Criteria criteria = getCurrentSession().createCriteria(EmpleadoFirmaDTO.class);
		criteria.add(Restrictions.eq("empleadoDTO.empId", idEmpleado));
		criteria.add(Restrictions.eq("estatus", new Integer(1)));
		empleadoFirmaDTO = (EmpleadoFirmaDTO) criteria.uniqueResult();
		empleadoFirmaDTO.setValidado(new Integer(1));
		getCurrentSession().saveOrUpdate(empleadoFirmaDTO);
		
		return empleadoFirmaDTO;
	}

 
}
