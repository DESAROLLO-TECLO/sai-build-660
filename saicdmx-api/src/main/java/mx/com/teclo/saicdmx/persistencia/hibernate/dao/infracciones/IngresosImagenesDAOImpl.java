package mx.com.teclo.saicdmx.persistencia.hibernate.dao.infracciones;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.IngresosImagenesDTO;
@Repository
public class IngresosImagenesDAOImpl extends BaseDaoHibernate<IngresosImagenesDTO> implements IngresosImagenesDAO {

	@Override
	public IngresosImagenesDTO buscaFotoPorNombreArchivo(String nombre) {
		Criteria criteria = getCurrentSession().createCriteria(IngresosImagenesDTO.class);
		criteria.add(Restrictions.eq("nombreArchivo",nombre));
		return  (IngresosImagenesDTO) criteria.uniqueResult();
	}

}
