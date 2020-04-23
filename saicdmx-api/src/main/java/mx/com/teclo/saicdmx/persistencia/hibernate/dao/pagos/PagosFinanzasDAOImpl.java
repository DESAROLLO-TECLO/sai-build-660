package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import java.math.BigDecimal;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.PagosFinanzasDTO;

/**
 * 
 * @author Javier Flores
 *
 */
@Repository
public class PagosFinanzasDAOImpl extends BaseDaoHibernate<PagosFinanzasDTO> implements PagosFinanzasDAO{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public BigDecimal getPagoEnLinea(String infracNum) {
		
		BigDecimal pagoMonto = new BigDecimal(0);
		Integer[] values = {1,2};	
		Criteria c = getCurrentSession().createCriteria(PagosFinanzasDTO.class,"pf");
		c.add(Restrictions.eq("pf.infracNum", infracNum));
		c.add(Restrictions.eq("pf.procesoId", 5));		
		c.add(Restrictions.in("pf.estatusPago", values));
		//c.add((Criterion) Projections.property("pf.pagoDiferencia"));		
		PagosFinanzasDTO pagosFinanzasDTO = (PagosFinanzasDTO) c.uniqueResult();
				
		if(pagosFinanzasDTO!=null)
			pagoMonto=pagosFinanzasDTO.getPagoMonto();
	
		return pagoMonto;
	}


}
