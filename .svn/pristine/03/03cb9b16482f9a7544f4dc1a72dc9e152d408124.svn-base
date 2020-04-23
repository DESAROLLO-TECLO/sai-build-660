package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.DetallePagosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.PagoDTO;
@Repository
public class DetallePagoDAOImpl extends BaseDaoHibernate<DetallePagosDTO> implements DetallePagoDAO{
	
	@Transactional
	public Double getSumPagValorDetallePago(Long cajaId,String pagId){
		Double sum = (Double) getCurrentSession().createCriteria(DetallePagosDTO.class)
		        .setProjection(Projections.sum("pagValor"))
		        .add(Restrictions.eq("primaryKeyDetPag.pagId", pagId))
		        .add(Restrictions.eq("primaryKeyDetPag.cajaDTO.cajaId", cajaId))
		        .uniqueResult();
				
		return sum;
	}
	
	@Transactional
	public List<DetallePagosDTO> getDetallePagoDTO(String pagId,CajaDTO cajaId){
		
		Criteria detPago=getCurrentSession().createCriteria(DetallePagosDTO.class)
				.add(Restrictions.eq("primaryKeyDetPag.pagId", pagId))
				.add(Restrictions.eq("primaryKeyDetPag.cajaDTO.cajaId", cajaId.getCajaId()));
		List<DetallePagosDTO> listaDetPagoDTO = (List<DetallePagosDTO>)detPago.list();
		
				return  listaDetPagoDTO;
	}
	
	@Transactional
	public void updateDetallePago(DetallePagosDTO detPagoDTO){
		getCurrentSession().saveOrUpdate(detPagoDTO);
		getCurrentSession().flush();
	}

}
