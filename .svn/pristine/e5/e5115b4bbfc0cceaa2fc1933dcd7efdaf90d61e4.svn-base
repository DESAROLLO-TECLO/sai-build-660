package mx.com.teclo.saicdmx.negocio.service.pagos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos.DigitalizacionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.CatalogoExpedienteVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.TipoExpedienteVO;

@Service
public class DigitalizacionServiceImpl implements DigitalizacionService {

	@Autowired
	DigitalizacionMyBatisDAO digitalizacionMBDAO;
	
	@Override
	public Integer corroborarNumeroInfraccion(String infracNum) {
		return digitalizacionMBDAO.corroborarNumeroInfraccion(infracNum);
	}
	
	@Override
	public List<CatalogoExpedienteVO> obtenerCatalogoExpediente() {
		return digitalizacionMBDAO.obtenerCatalogoExpediente();
	}

	@Override
	public List<TipoExpedienteVO> obtenerCapturasExpediente(String infracNum) {
		return digitalizacionMBDAO.obtenerCapturasExpediente(infracNum);
	}
	
	@Override
	public String obtenerRutaLocal(String infracNum, Long idArchivo, String tipo){
		return digitalizacionMBDAO.obtenerRutaLocalExpediente(infracNum, idArchivo, tipo);
	}
	
	public byte[] buscarCapturaExpedientePorTipo(String infracNum, String tipo){
		return digitalizacionMBDAO.buscarCapturaExpedientePorTipo(infracNum, tipo);
	}

	@Override
	public String [] obtenerInfraccionExpediente(String infracNum, String valorParametro, Long empPlaca) {
		
		String data="", nci = "", adeudos = "";
		
		if (valorParametro.equals("INFRACCION")) {
			
			data = digitalizacionMBDAO.consultaExpedienteInfraccion(infracNum);
			
		} else if (valorParametro.equals("PLACA")) {
			
			data = digitalizacionMBDAO.consultaExpedientePlaca(infracNum);
			
		} else if (valorParametro.equals("IMPRESA")) {
			
			data = digitalizacionMBDAO.consultaExpedienteImpresa(infracNum);
			
		} else if (valorParametro.equals("NCI")) {
			
			data = digitalizacionMBDAO.consultaExpedienteNCI(infracNum);
			
		}
		
		if(data != null){
			String [] aux = new String [4];
			
			String[] info = data.split("\\|");
			
			nci = info[1];
			
			Integer conteo = digitalizacionMBDAO.consultaInfraccionesAsociadas(nci, empPlaca);
			
			adeudos = String.valueOf(conteo);
			
			aux[0] = info[0];
			aux[1] = nci;
			aux[2] = adeudos;
			aux[3] = info[2];
			
			return aux;
		}else{
			return null;
		}
		
	}
	
	@Override
	public String obtenerCatDocumento(String infracNum, String tipo) {
		return digitalizacionMBDAO.obtenerCatDocumento(infracNum, tipo);
	}

}
