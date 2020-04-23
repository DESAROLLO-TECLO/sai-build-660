package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.FechasIngresosDTO;

@Repository
public class FechasIngresosDAOImpl extends BaseDaoHibernate<FechasIngresosDTO> implements FechasIngresosDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<FechasIngresosDTO> obtenerFecha(Date fechaIngreso) {

		Criteria criteria = getCurrentSession().createCriteria(FechasIngresosDTO.class);
		criteria.add(Restrictions.eq("fechaIngreso", fechaIngreso));

		return criteria.list();
	}

	@Override
	public void guardarFechaIngreso(FechasIngresosDTO fechasIngresosDTO) {
		// TODO Auto-generated method stub

		this.save(fechasIngresosDTO);

	}

}
