package mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatMedioSolicitudDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CatTipoTramiteDTO;


@Repository
public class CatMedioSolicitudDAOImpl extends BaseDaoHibernate<CatMedioSolicitudDTO> implements CatMedioSolicitudDAO {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<CatMedioSolicitudDTO> catalogoMedioSolicitud() {
		
			Criteria criteria = getCurrentSession().createCriteria(CatMedioSolicitudDTO.class);
			criteria.add(Restrictions.eq("stActivo", 1));
			return criteria.list();
		}

	
	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub

	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub

	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeSession() {
		// TODO Auto-generated method stub

	}

	@Override
	public CatMedioSolicitudDTO findOne(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatMedioSolicitudDTO> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatMedioSolicitudDTO> findAll(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Serializable save(CatMedioSolicitudDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatMedioSolicitudDTO update(CatMedioSolicitudDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CatMedioSolicitudDTO saveOrUpdate(CatMedioSolicitudDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(CatMedioSolicitudDTO entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String desencriptarCampo(String valorCampo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String encriptarCampo(String valorCampo) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
