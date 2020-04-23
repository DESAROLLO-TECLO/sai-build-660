package mx.com.teclo.saicdmx.persistencia.hibernate.dao.placas;


import java.util.List;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.placas.PlacasDTO;

@Repository
public class PlacaDAOImpl extends BaseDaoHibernate<PlacasDTO> implements PlacaDAO  {



	@Override
	public PlacasDTO actualizarPlaca(String placaCodigo) {
		PlacasDTO placaDTO = null;
		Criteria criteria = getCurrentSession().createCriteria(PlacasDTO.class);
		//criteria.add(Restrictions.eq("empPlaca", placaOficial));
		placaDTO = (PlacasDTO) criteria.uniqueResult();
		placaDTO.setPlacaCodigo(placaCodigo);
		getCurrentSession().saveOrUpdate(placaDTO);
		
		return placaDTO;
	}

	@Override
	public PlacasDTO altaPlaca(String placaCodigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlacasDTO> consultaPlaca(String placaCodigo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlacasDTO find(Long placaID) {
		// TODO Auto-generated method stub
		return null;
	}

}
