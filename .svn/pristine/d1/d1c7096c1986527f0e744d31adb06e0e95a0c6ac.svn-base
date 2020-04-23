package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.PropiedadesDTO;

@Repository("PropiedadesDAO")
public class PropiedadesDAOImpl extends BaseDaoHibernate<PropiedadesDTO> implements PropiedadesDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<PropiedadesDTO> listaPropiedades() {
		Criteria c = getCurrentSession().createCriteria(PropiedadesDTO.class);
		List<PropiedadesDTO> listPropiedadesDTO = c.list();
		return listPropiedadesDTO;
	}

}
