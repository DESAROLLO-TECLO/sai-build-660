package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.FormatoDescargaDTO;

@Repository("FormatoDescargaDAO")
public class FormatoDescargaDAOImpl extends BaseDaoHibernate<FormatoDescargaDTO> implements FormatoDescargaDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<FormatoDescargaDTO> listaFormatoDescarga() {
		Criteria c = getCurrentSession().createCriteria(FormatoDescargaDTO.class);
		List<FormatoDescargaDTO> listaFormatoDesc = c.list();
		return listaFormatoDesc;
	}

}
