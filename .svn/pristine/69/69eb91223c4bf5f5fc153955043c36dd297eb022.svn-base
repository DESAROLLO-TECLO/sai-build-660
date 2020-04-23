package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.BancoDTO;

@Repository
public class BancoDAOImpl extends BaseDaoHibernate<BancoDTO> implements BancoDAO {

	@Override
	public BancoDTO buscarBancoPorCod(String bancoCod) {
		Criteria criteria = getCurrentSession().createCriteria(BancoDTO.class);
		criteria.add(Restrictions.eq("bancoCodigo", bancoCod));
		return (BancoDTO) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BancoDTO> busquedaOrdenanda() {
		Criteria criteria = getCurrentSession().createCriteria(BancoDTO.class);
		criteria.addOrder(Order.asc("bancoId"));
		return criteria.list();
	}
}
