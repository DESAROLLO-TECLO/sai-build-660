package mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaVwSinProcesarDTO;


@Repository("garantiasVwSinProcesarDAO")
public class GarantiasVwSinProcesarDAOImpl extends BaseDaoHibernate<GarantiaVwSinProcesarDTO> implements  GarantiasVwSinProcesarDAO{

	@Override
	@SuppressWarnings("unchecked")
	public List<GarantiaVwSinProcesarDTO> getGarantiasSinProcesar(String placaOficial) {
		Criteria c=getCurrentSession().createCriteria(GarantiaVwSinProcesarDTO.class)
				.add(Restrictions.eq("empPlaca",placaOficial));
		return c.list() ;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<GarantiaVwSinProcesarDTO> getGarantiasSinProcesar() {
		Criteria c=getCurrentSession().createCriteria(GarantiaVwSinProcesarDTO.class);
		return c.list() ;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<GarantiaVwSinProcesarDTO> getGarantiasSinProcesarByOficial(String placaOficial) {
		Criteria c=getCurrentSession().createCriteria(GarantiaVwSinProcesarDTO.class)
				.add(Restrictions.eq("empPlaca",placaOficial));
		return c.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public GarantiaVwSinProcesarDTO getGarantiasSinProcesarByInfracNum(String infrac_num) {
		Criteria c = getCurrentSession().createCriteria(GarantiaVwSinProcesarDTO.class)
				.add(Restrictions.eq("infracNum", infrac_num));
		return (GarantiaVwSinProcesarDTO) c.uniqueResult();
	}
}