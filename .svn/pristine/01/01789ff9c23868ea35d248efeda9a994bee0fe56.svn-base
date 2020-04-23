package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.GruaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDTO;

@Repository
public class GruaDAOImpl extends BaseDaoHibernate<GruaDTO> implements GruaDAO 
{

	@Override
	public GruaDTO buscarGrua(String status, String cod) 
	{
		//return (GruaDTO) getCurrentSession().createCriteria(GruaDTO.class).add(Restrictions.eq("gruaStatus", status)).add(Restrictions.eq("gruaCod", cod)).uniqueResult();
		return (GruaDTO) getCurrentSession().createCriteria(GruaDTO.class).add(Restrictions.eq("gruaCod", cod)).uniqueResult();
	}

	@Override
	public GruaDTO buscarGrua(Long idGrua) 
	{
		GruaDTO gruaDTO = findOne(idGrua);
		return gruaDTO;
	}

	@Override
	public String obtenerCobroArrastre(String idGrua) {
		
		Criteria criteria = getCurrentSession().createCriteria(InfraccionDTO.class)
				.setProjection(Projections.projectionList()
		            .add(Projections.property("gruaCobrarArrastre")));
               
		criteria.add(Restrictions.eq("gruaid", idGrua));
		     
		return criteria.uniqueResult().toString();
		
	}

	@Override
	public List<GruaDTO> buscarGruasPorConcesionaria(Long concesionariaId) {
		Criteria criteria = getCurrentSession().createCriteria(GruaDTO.class);
		criteria.add(Restrictions.eq("conseId", concesionariaId));
		return (List<GruaDTO>) criteria.list();
	}

	@Override
	public GruaDTO buscarGrua(String gruaCod) {
		return (GruaDTO) getCurrentSession().createCriteria(GruaDTO.class).add(Restrictions.eq("gruaCod", gruaCod)).uniqueResult();
	}

/*	@Override
	public GruaDTO buscarGruaPorNumeroInfracc(String numeroInfraccion) {
		
		Criteria criteria = getCurrentSession().createCriteria(GruaDTO.class);
		criteria.add(Restrictions.eq("infraccNum", numeroInfraccion));

		GruaDTO gruaDTO = (GruaDTO) criteria.uniqueResult();

		return gruaDTO;
		
		
	}*/
}
