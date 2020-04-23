package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.PagoDTO;

@Repository("pagoDAO")
public class PagoDAOImpl extends BaseDaoHibernate<PagoDTO> implements PagoDAO {

	@Override
	@Transactional
	public PagoDTO getPagoByNumInfraccion(String infracNum) {
		Criteria criteria = getCurrentSession().createCriteria(PagoDTO.class);
		criteria.add(Restrictions.eq("infracNum", infracNum));
		criteria.add(Restrictions.isNull("pagStatus"));
		return (PagoDTO) criteria.uniqueResult();
	}
	
	@Override
	@Transactional(readOnly = true)
	public PagoDTO getPagoByNumInfraccionAndTranId(String infracNum,Long tranId) {
		Criteria criteria = getCurrentSession().createCriteria(PagoDTO.class);
		criteria.add(Restrictions.eq("infracNum", infracNum));
		criteria.add(Restrictions.eq("tranId",tranId));
		criteria.add(Restrictions.isNull("pagStatus"));
		return (PagoDTO) criteria.uniqueResult();
	}
	
	
	@Override
	@Transactional
	public PagoDTO getPagoByNumControlAndTranId(String numControl,Long tranId) {
		Criteria criteria = getCurrentSession().createCriteria(PagoDTO.class);
		criteria.add(Restrictions.eq("ingrCtrlInt", numControl));
		criteria.add(Restrictions.eq("tranId",tranId));
		criteria.add(Restrictions.isNull("pagStatus"));
		return (PagoDTO) criteria.uniqueResult();
	}
	
	@Transactional
	public void updatePagoDTO(PagoDTO pagoDTO){
		getCurrentSession().saveOrUpdate(pagoDTO);
		getCurrentSession().flush();
	}

	@Override
	@Transactional
	public Long consultaParamsPago(String infracNum,String campo) {
		String hql ="";
		if(campo.equals("pagId")){
			hql = "select p.pagosId.pagId from PagoDTO p where p.pagStatus is null and ";
		}else{
			hql = "select p.pagosId.cajaId.cajaId from PagoDTO p where ";			
		}
		hql+=" p.infracNum = :param ";

		Query query = getCurrentSession().createQuery(hql).setParameter("param", infracNum);
		Long value = new Long(query.list().get(0).toString());
		return value;
	}
}
