package mx.com.teclo.saicdmx.persistencia.hibernate.dao.fm;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.fm.FMLotesDTO;

@Repository
public class FMLotesDAOImpl extends BaseDaoHibernate<FMLotesDTO> implements FMLotesDAO{
	
	@Override
	public FMLotesDTO buscarLotesEnCreacion(){
		Criteria criteria = getCurrentSession().createCriteria(FMLotesDTO.class);
		criteria.add(Restrictions.eq("enProceso", true));
		//criteria.add(Restrictions.eq("radarTipoId", tipoRadar));
		//criteria.add(Restrictions.eq("archivoTipo", archivoTipo));
		return (FMLotesDTO) criteria.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FMLotesDTO> BuscarLotesCreados() {
		Criteria criteria = getCurrentSession().createCriteria(FMLotesDTO.class);
		criteria.add(Restrictions.eq("estatusProcesoId", 1));
		criteria.add(Restrictions.eq("enProceso",false));
		criteria.add(Restrictions.eq("cancelado", false));
		return (List<FMLotesDTO>) criteria.list();
	}
}
