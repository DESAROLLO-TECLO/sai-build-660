package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.DetalleCargosDTO;

@Repository
public class DetalleCargosDAOImpl extends BaseDaoHibernate<DetalleCargosDTO> implements DetalleCargosDAO{
	
	@Transactional
	public Double getSumPagValorDetalleCargos(Long cajaId,String pagId){

		Double sum = (Double) getCurrentSession().createCriteria(DetalleCargosDTO.class)
		        .setProjection(Projections.sum("pagValor"))
		        .add(Restrictions.eq("primaryKeyDetCarg.pagId", pagId))
		        .add(Restrictions.eq("primaryKeyDetCarg.cajaDTO.cajaId", cajaId))
		        .uniqueResult();
				
		return sum;
	}
	
	@Transactional
	public List<DetalleCargosDTO> getDetalleCargoDTO(String pagId,CajaDTO cajaId){
		
		Criteria detPago=getCurrentSession().createCriteria(DetalleCargosDTO.class)
				.add(Restrictions.eq("primaryKeyDetCarg.pagId", pagId))
				.add(Restrictions.eq("primaryKeyDetCarg.cajaDTO.cajaId", cajaId.getCajaId()));
				List<DetalleCargosDTO> detCargoslist = (List<DetalleCargosDTO>) detPago.list();
				
				return detCargoslist;
	}
	
	@Transactional
	public void updateDetalleCargos(DetalleCargosDTO detCargosDTO){
		getCurrentSession().saveOrUpdate(detCargosDTO);
		getCurrentSession().flush();
	}

}
