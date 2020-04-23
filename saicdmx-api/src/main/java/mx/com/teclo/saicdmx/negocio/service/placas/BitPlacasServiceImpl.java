package mx.com.teclo.saicdmx.negocio.service.placas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.placas.BitPlacasDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.placas.PlacaDAO;
import mx.com.teclo.saicdmx.persistencia.vo.placas.BitPlacasVO;
import mx.com.teclo.saicdmx.persistencia.vo.placas.PlacasVO;

@Service
public class BitPlacasServiceImpl implements BitPlacasService {

	@Autowired
	private BitPlacasDAO bitPlacasDAO;
	
	@Transactional
	@Override
	public void guardarBitPlacas(BitPlacasVO bitPlacasVO) {
		Object obj = bitPlacasDAO.save(bitPlacasVO.getBitPlacasDTO());

	}

}
