package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatTipoTramiteDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoTramiteDTO;

@Repository
public  class CatTipoTramiteDAOImpl extends BaseDaoHibernate<CatTipoTramiteDTO> implements CatTipoTramiteDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<CatTipoTramiteDTO> catalogoTramite() {
		
			Criteria criteria = getCurrentSession().createCriteria(CatTipoTramiteDTO.class);
			criteria.add(Restrictions.eq("stActivo", 1));
			return criteria.list();
		}

	@SuppressWarnings("unchecked")
	@Override
	public List<CatTipoTramiteDTO> getTramitePorListId(String listId) {
		String[] lista = listId.split(",");
		Long[] l = new Long[lista.length];
		for(int i = 0; i <lista.length; i++) {
			l[i] = Long.valueOf(lista[i]);
		}
		Criteria criteria = getCurrentSession().createCriteria(CatTipoTramiteDTO.class);
		criteria.add(Restrictions.in("idTramite", l));
		criteria.add(Restrictions.eq("stActivo", 1));
		return criteria.list();
	}
}


