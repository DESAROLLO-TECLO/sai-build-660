package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoIngresoDTO;

@Repository
public class TipoIngresoDAOImpl extends BaseDaoHibernate<TipoIngresoDTO> implements TipoIngresoDAO {

	private String estatusActivo = "A";

	@Override
	public TipoIngresoDTO buscarPorId(Long idTipoIngreso) {
		return (TipoIngresoDTO) getCurrentSession().createCriteria(TipoIngresoDTO.class)
				.add(Restrictions.eq("tIngrStatus", this.estatusActivo)).add(Restrictions.eq("tIngrId", idTipoIngreso))
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoIngresoDTO> buscarDiferentesAlId(Long idTipoIngreso) {
		return getCurrentSession().createCriteria(TipoIngresoDTO.class)
				.add(Restrictions.eq("tIngrStatus", this.estatusActivo)).add(Restrictions.ne("tIngrId", idTipoIngreso))
				.list();
	}

	@Override
	public TipoIngresoDTO buscarTipoPorCod(String tipoCod) {
		Criteria criteria = getCurrentSession().createCriteria(TipoIngresoDTO.class);
		criteria.add(Restrictions.eq("tIngrCod", tipoCod));
		return (TipoIngresoDTO) criteria.uniqueResult();
	}
}
