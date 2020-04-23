package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatTipoDocumentoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatTipoTramiteDTO;


@Repository
public class CatTipoDocumentoDAOImpl extends BaseDaoHibernate<CatTipoDocumentoDTO> implements CatTipoDocumentoDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CatTipoDocumentoDTO> catalogoDocumento() {
	
			Criteria criteria = getCurrentSession().createCriteria(CatTipoDocumentoDTO.class);
			criteria.add(Restrictions.eq("stActivo", 1));
			return criteria.list();
		}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatTipoDocumentoDTO> getcatalogoDocumentoID(String listId) {
		String[] lista = listId.split(",");
		Long[] l = new Long[lista.length];
		for(int i = 0; i <lista.length; i++) {
			l[i] = Long.valueOf(lista[i]);
		}
		Criteria criteria = getCurrentSession().createCriteria(CatTipoDocumentoDTO.class);
		criteria.add(Restrictions.in("idDocumento", l));
		criteria.add(Restrictions.eq("stActivo", 1));
		return criteria.list();
	}
	}
	


