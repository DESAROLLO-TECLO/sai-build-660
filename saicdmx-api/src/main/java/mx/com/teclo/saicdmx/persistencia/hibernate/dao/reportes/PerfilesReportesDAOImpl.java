package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.PerfilesReportesDTO;
import mx.com.teclomexicana.arquitectura.persistencia.comun.dao.BaseDaoHibernate;

@Repository
public class PerfilesReportesDAOImpl extends BaseDaoHibernate<PerfilesReportesDTO> implements PerfilesReportesDAO{

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PerfilesReportesDTO> getReportePerfilAtivos(String cdApp) {
		Criteria c = getCurrentSession().createCriteria(PerfilesReportesDTO.class);
		c.createAlias("perfil", "p");
		c.createAlias("p.aplicacionDTO", "app");
		c.add(Restrictions.eq("app.codigo",cdApp));
		c.add(Restrictions.eq("stActivo",1));
		return (List<PerfilesReportesDTO>)c.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public PerfilesReportesDTO byReporteAndPerfil(Long perfilId, Long reporteId, String cdApp) {
		Criteria c = getCurrentSession().createCriteria(PerfilesReportesDTO.class);
		c.createAlias("perfil", "p");
		c.createAlias("p.aplicacionDTO", "app");
		c.createAlias("reporte", "rep");
		c.add(Restrictions.eq("app.codigo",cdApp));
		c.add(Restrictions.eq("p.perfilId",perfilId));
		c.add(Restrictions.eq("rep.idReporte",reporteId));
		List<PerfilesReportesDTO> dtoList = c.list();
		if(CollectionUtils.isEmpty(dtoList))
			return null;
		return dtoList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PerfilesReportesDTO> ontenerReportesPorPerfil(Long idPerfil) {
		Criteria c = getCurrentSession().createCriteria(PerfilesReportesDTO.class);
		c.createAlias("perfil", "p");
		c.createAlias("reporte", "r");
		c.add(Restrictions.eq("p.perfilId",idPerfil));
		c.add(Restrictions.eq("stActivo",1));
		c.add(Restrictions.eq("r.stActivo",1));
		c.add(Restrictions.eq("p.stActivo",true));
		
		return (List<PerfilesReportesDTO>)c.list();
	}

}
