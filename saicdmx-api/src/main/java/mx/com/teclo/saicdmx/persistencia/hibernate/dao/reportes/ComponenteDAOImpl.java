package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.ComponentesDTO;


@Repository("ComponenteDAO")
public class ComponenteDAOImpl extends BaseDaoHibernate<ComponentesDTO> implements ComponenteDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<ComponentesDTO> listaComponentes() {
		Criteria c =getCurrentSession().createCriteria(ComponentesDTO.class);
		List<ComponentesDTO> listComponentes = c.list();
		
		return listComponentes;
	}

}
