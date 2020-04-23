package mx.com.teclo.saicdmx.persistencia.hibernate.dao.ingresos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Conjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresos.IngresosDTO;

@Repository
public class IngresosDAOImpl extends BaseDaoHibernate<IngresosDTO> implements IngresosDAO{

	/***
	 * @author Jesus Gutierrez
	 */
	@Override
	public IngresosDTO buscaIngresoByNumeroControl(String infracNumControl) {
		Criteria criteria =  getCurrentSession().createCriteria(IngresosDTO.class);
		criteria.add(Restrictions.eq("ingrNumCtrl", infracNumControl));
		return (IngresosDTO) criteria.uniqueResult();
	}
	
	@Override
	public IngresosDTO buscaTraslado(String tipoBusq, String valorParam, Long idDep, boolean isIngreso) {
		Criteria criteria =  getCurrentSession().createCriteria(IngresosDTO.class);
		criteria.createAlias("infraccion", "inf");
		criteria.createAlias("movimientos", "movs");
		criteria.add(Restrictions.eq("stMovimiento", "T"));
		criteria.add(Restrictions.eq("movs.activo", 1L));
		if(isIngreso){
			criteria.add(Restrictions.eq("movs.depDestino", idDep.toString()));
		}else{
//			criteria.add(Restrictions.eq("movs.depDestino", idDep.toString()));
		criteria.add(Restrictions.eq("depId", idDep));
		}
		
		switch(tipoBusq){	
		case "PLACA":
			criteria.add(Restrictions.eq("inf.infraccPlaca", valorParam));
			break;
		case "INFRACCION":
			criteria.add(Restrictions.eq("infracNum", valorParam));
			break;
		case "IMPRESA":
			criteria.add(Restrictions.eq("inf.infraccImpresa", valorParam));
			break;
		case "NCI":
			criteria.add(Restrictions.eq("ingrNumCtrl", valorParam));
			break;
		case "NUMDOCTO":
			criteria.add(Restrictions.eq("infracDocto", valorParam));
			break;
		default:
			break;
		}
		
		return (IngresosDTO) criteria.uniqueResult();
	}


	

	@Override
	public IngresosDTO buscaIngresoByInfraccion(String numinfrac) {
		Criteria criteria =  getCurrentSession().createCriteria(IngresosDTO.class);
		criteria.add(Restrictions.eq("infracNum", numinfrac));
		return (IngresosDTO) criteria.uniqueResult();
	}


	
}
