package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.ExpedienteDTO;
 
@Repository("expedientesDAO")
public class ExpedientesDAOImpl extends BaseDaoHibernate<ExpedienteDTO> implements ExpedientesDAO{

//	Actualización: Se comenta el método que elimina el registro de la base de datos
	
//	@Override
//	public ExpedienteDTO eliminarExpediente(String infracNum, String tipo)
//	{
//		ExpedienteDTO expedienteDTO;
//		Criteria criteria = getCurrentSession().createCriteria(ExpedienteDTO.class);
//		criteria.add(Restrictions.eq("infrac_Num", infracNum));
//		criteria.add(Restrictions.eq("tipo", tipo));
//		criteria.add(Restrictions.eq("activo", 1));
////		expedienteDTO = (ExpedienteDTO) criteria.setMaxResults(1).uniqueResult();
////		getCurrentSession().delete(expedienteDTO);
//		expedienteDTO = (ExpedienteDTO) criteria.setMaxResults(1).uniqueResult();
//		
//		return expedienteDTO;
//	}
	
	public ExpedienteDTO getExpedientePorTipo(String infracNum, String tipo)
	{
		Criteria criteria = getCurrentSession().createCriteria(ExpedienteDTO.class);
		criteria.add(Restrictions.eq("infrac_Num", infracNum));
		criteria.add(Restrictions.eq("tipo", tipo));
		criteria.add(Restrictions.eq("activo", 1));
		return (ExpedienteDTO) criteria.uniqueResult();
	}
	
//	OBTENER EL ID QUE SE LE ASIGNARÁ AL ARCHIVO QUE SE REGISTRA
	public Long getIdArchivo()
	{
		Criteria criteria = getCurrentSession().createCriteria(ExpedienteDTO.class);
		criteria.setProjection(Projections.max("img_expediente_id"));
		
		Long idArchivo = (Long)criteria.uniqueResult();
		
		return idArchivo;
	}
	
//	OBTENER EL NÚMERO DE CONTROL DE LA INFRACCIÓN CONSULTADA
	public String obtenerNumControl(String infracNum)
	{
		Criteria criteria = getCurrentSession().createCriteria(InfraccionDTO.class);
		criteria.add(Restrictions.eq("infraccNum", infracNum));
		criteria.setProjection(Projections.property("infraccNumCtrl"));
		
		String numCtrlInt = (String)criteria.setMaxResults(1).uniqueResult();
		
		return numCtrlInt;
	}
	
	@SuppressWarnings("unchecked")
	public List<ExpedienteDTO> getTodoExpediente(String infracNum)
	{
		Criteria criteria = getCurrentSession().createCriteria(ExpedienteDTO.class);
		criteria.add(Restrictions.eq("infrac_Num", infracNum));
		criteria.add(Restrictions.eq("activo", 1));
		return criteria.list();
	}
}
