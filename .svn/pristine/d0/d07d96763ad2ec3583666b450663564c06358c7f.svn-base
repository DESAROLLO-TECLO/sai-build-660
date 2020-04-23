package mx.com.teclo.saicdmx.negocio.service.estadistica;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDaoHibernate;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.estadistica.TipoInfraccionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.estadistica.TipoInfraccionDTO;

@Service
public class TipoInfraccionServiceImpl implements TipoInfraccionService{
 
	@Autowired
	private TipoInfraccionDAO tipoInfraccionDAO;
	
	@Transactional
	public List<TipoInfraccionDTO> tipoInfracciones() {
		return tipoInfraccionDAO.tipoInfracciones();
	}

	@Transactional
	@Override
	public String getVistaPorTipo(String infracNum) {
		if(infracNum==null || infracNum.length()<2)
			infracNum = "00";
		return tipoInfraccionDAO.getVistaPorTipo(infracNum);
	}
}
