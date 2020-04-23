package mx.com.teclo.saicdmx.negocio.service.pagos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.pagos.CatalogoExpedienteVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.TipoExpedienteVO;


public interface DigitalizacionService {

	String [] obtenerInfraccionExpediente(String infracNum, String valorParametro, Long empPlaca);
	
	Integer corroborarNumeroInfraccion(String infracNum);
	
	List<CatalogoExpedienteVO> obtenerCatalogoExpediente();
	
	List<TipoExpedienteVO>obtenerCapturasExpediente(String infracNum);
	
	String obtenerRutaLocal(String infracNum, Long idArchivo, String tipo);
	
	byte[] buscarCapturaExpedientePorTipo(String infracNum, String tipo);
	
	String obtenerCatDocumento(String infracNum, String tipo);
}
