package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.AplicacionDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ReportesDTO;

@Repository
public class ReporteDAOImpl extends BaseDaoHibernate<ReportesDTO> implements ReporteDAO{

	@Transactional
	@Override
	public Long selectMaximo() {
		Criteria c = getCurrentSession().createCriteria(ReportesDTO.class);
		c.setProjection(Projections.max("idReporte"));
		return (Long)c.uniqueResult();
	}

	
	@Value("${app.config.codigo}")
	private String codigo;
	
	@Transactional
	@Override
	public AplicacionDTO idApp() {
		
		
		Criteria c = getCurrentSession().createCriteria(AplicacionDTO.class);
		c.add(Restrictions.eq("codigo", codigo));
		return (AplicacionDTO) c.uniqueResult();
	}

	@Override
	@Transactional
	public ReportesDTO getReporteById(Long idReporte, String cdApp) {
		Criteria c = getCurrentSession().createCriteria(ReportesDTO.class);
		c.createAlias("aplicacion", "app");
		c.add(Restrictions.eq("app.codigo", cdApp));
		c.add(Restrictions.eq("idReporte", idReporte));
		c.add(Restrictions.eq("stActivo", 1));
		return (ReportesDTO)c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ReportesDTO> obtrenerReportesActivos(String cdApp) {
		Criteria c = getCurrentSession().createCriteria(ReportesDTO.class);
		c.createAlias("aplicacion","app");
		c.add(Restrictions.eq("app.codigo",cdApp));
		c.add(Restrictions.eq("stActivo", 1));
		return (List<ReportesDTO>)c.list();
	}
}
