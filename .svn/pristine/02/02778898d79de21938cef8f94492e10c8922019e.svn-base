package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.ProgramaDTO;

@Repository
public class ProgramaDAOImpl extends BaseDaoHibernate<ProgramaDTO> implements ProgramaDAO {

	@Override
	public List<ProgramaDTO> buscarProgramasActivos() {
		Criteria criteria = getCurrentSession().createCriteria(ProgramaDTO.class);
		criteria.add(Restrictions.eq("programaStatus", "A"));
		return criteria.list();
	}

	@Override
	public ProgramaDTO buscarProgramaPorCod(String programaCod) {
		Criteria criteria = getCurrentSession().createCriteria(ProgramaDTO.class);
		criteria.add(Restrictions.eq("programaCodigo", programaCod));
		return (ProgramaDTO) criteria.uniqueResult();
	}
}
