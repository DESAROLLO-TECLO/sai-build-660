package mx.com.teclo.saicdmx.persistencia.hibernate.dao.reportes;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.reportes.TipoTitulosDTO;

@Repository("TipoTituloDAO")
public class TipoTituloDAOImpl extends BaseDaoHibernate<TipoTitulosDTO> implements TipoTituloDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoTitulosDTO> listaTipoTitulo() {
		Criteria c = getCurrentSession().createCriteria(TipoTitulosDTO.class);
		List<TipoTitulosDTO> listTipoTituloDTO = c.list();
		return listTipoTituloDTO;
	}

}
