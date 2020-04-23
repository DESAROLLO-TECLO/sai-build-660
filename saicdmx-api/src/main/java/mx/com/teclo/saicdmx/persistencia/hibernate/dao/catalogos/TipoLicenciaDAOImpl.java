package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.TipoLicenciaDTO;

@Repository
public class TipoLicenciaDAOImpl extends BaseDaoHibernate<TipoLicenciaDTO> implements TipoLicenciaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoLicenciaDTO> buscarListaTipoLicencia(String status) {
		Criteria criteria = getCurrentSession().createCriteria(TipoLicenciaDTO.class);
		criteria.add(Restrictions.eq("tipoLStatus", status));
		criteria.addOrder(Order.asc("tipoLCod"));
		return criteria.list();
	}

	@Override
	public TipoLicenciaDTO buscarTipoLicencia(Long idTipoLicencia) {
		TipoLicenciaDTO tipoLicenciaDTO = findOne(idTipoLicencia);

		return tipoLicenciaDTO;
	}

	@Override
	public TipoLicenciaDTO buscarTipoLicencia(String tipoLicenciaNombre) {
		Criteria criteria = getCurrentSession().createCriteria(TipoLicenciaDTO.class);
		criteria.add(Restrictions.eq("tipoLNombre", tipoLicenciaNombre));

		TipoLicenciaDTO tipoLicenciaDTO = (TipoLicenciaDTO) criteria.uniqueResult();

		return tipoLicenciaDTO;
	}

	@Override
	public TipoLicenciaDTO buscarTipoLicenciaPorCod(String tipoLicenciaCod) {
		Criteria criteria = getCurrentSession().createCriteria(TipoLicenciaDTO.class);
		criteria.add(Restrictions.eq("tipoLCod", tipoLicenciaCod));
		return (TipoLicenciaDTO) criteria.uniqueResult();
	}
}
