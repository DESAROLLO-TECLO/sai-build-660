package mx.com.teclo.saicdmx.bitacora.desencripta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.infracciones.InfraccionMyBatisDAO;
@Service
public class BitDesencriptarImpl implements BitDesencriptar {

	@Autowired
	private InfraccionMyBatisDAO infracDAO;
	@Override
	public String getDesencriptarValue(String valor) {
	return infracDAO.getDesencriptarValor(valor);
	}

}
