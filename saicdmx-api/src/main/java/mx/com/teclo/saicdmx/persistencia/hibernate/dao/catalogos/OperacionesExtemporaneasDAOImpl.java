package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.OperacionesExtemporaneasDTO;

@Repository
public class OperacionesExtemporaneasDAOImpl extends BaseDaoHibernate<OperacionesExtemporaneasDTO> implements OperacionesExtemporaneasDAO {

	@Override
	public OperacionesExtemporaneasDTO getFechaDeRegistro(String usuarioId) {

		OperacionesExtemporaneasDTO fechaHabilitada = new OperacionesExtemporaneasDTO();

		Criteria criteria = getCurrentSession().createCriteria(OperacionesExtemporaneasDTO.class)
				.setProjection(Projections.projectionList().add(Projections.property("fechaHab")));

		Criterion rest1 = Restrictions.or(Restrictions.eq("cajaId", new Long(0)),
				Restrictions.eq("usuId", new Long(usuarioId)));

		Criterion rest2 = Restrictions.eq("capStatus", "A");
		criteria.add(Restrictions.and(rest1, rest2));

		System.out.println("*****************" + (OperacionesExtemporaneasDTO) criteria.uniqueResult());

		if ((OperacionesExtemporaneasDTO) criteria.uniqueResult() == null) {
			fechaHabilitada.setFechaHab(new Date());
		}

		System.out.println("Resultado de getFEHCA REGISTRO DAO " + fechaHabilitada.getFechaHab());

		return fechaHabilitada;
	}

}
