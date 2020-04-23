package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoReportesDTO;

@Repository("TipoReporteDAO")
public class TipoReporteDAOImpl extends BaseDaoHibernate<TipoReportesDTO> implements TipoReporteDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoReportesDTO> listaTipoReporte() {
		Criteria c = getCurrentSession().createCriteria(TipoReportesDTO.class);
		List<TipoReportesDTO> tipoReporte = c.list();
		return tipoReporte;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoReportesDTO> getAllReports() {
		Criteria c = getCurrentSession().createCriteria(TipoReportesDTO.class);
		c.add(Restrictions.eq("stActivo", 1));
		return (List<TipoReportesDTO>)c.list();
	}
}
