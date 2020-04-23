package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CausaIngresoDTO;

@Repository
public class CausaIngresoDAOImpl extends BaseDaoHibernate<CausaIngresoDTO> implements CausaIngresoDAO {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(CausaIngresoDAOImpl.class);
	private String estatusActivo = "A";

	@Override
	public CausaIngresoDTO buscarCausaIngreso(String status, String codigo) {
		return (CausaIngresoDTO) getCurrentSession().createCriteria(CausaIngresoDTO.class)
				.add(Restrictions.eq("statusCausaIngreso", status)).add(Restrictions.eq("codigoCausaIngreso", codigo))
				.uniqueResult();
	}

	@Override
	public CausaIngresoDTO buscarCausaIngreso(Long idCausa) {
		CausaIngresoDTO causaDTO = findOne(idCausa);
		return causaDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CausaIngresoDTO> findAll() {
		return getCurrentSession().createCriteria(CausaIngresoDTO.class)
				.add(Restrictions.eq("statusCausaIngreso", this.estatusActivo)).list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CausaIngresoDTO> buscarTodos() {
		return getCurrentSession().createCriteria(CausaIngresoDTO.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CausaIngresoDTO> buscarCausaIngresoDiferentesAlId(Long... idCausaIngreso) {
		return getCurrentSession().createCriteria(CausaIngresoDTO.class)
				.add(Restrictions.eq("statusCausaIngreso", this.estatusActivo))
				.add(Restrictions.not(Restrictions.in("idCausaIngreso", idCausaIngreso))).list();
	}

	@Override
	public CausaIngresoDTO buscarCausaIngreso(String causaCod) {
		Criteria criteria = getCurrentSession().createCriteria(CausaIngresoDTO.class);
		criteria.add(Restrictions.eq("codigoCausaIngreso", causaCod));
		return (CausaIngresoDTO) criteria.uniqueResult();
	}
}
