package mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias;

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDTO;

@Repository
public class GarantiasMasivaDAOImpl extends BaseDaoHibernate<GarantiaDTO> implements GarantiasMasivaDAO{

	@Override
	public List<GarantiaDTO> getGarantiasMasivaSinProcesar(Long empId) {
		// TODO Auto-generated method stub
		return null;
	}

}
