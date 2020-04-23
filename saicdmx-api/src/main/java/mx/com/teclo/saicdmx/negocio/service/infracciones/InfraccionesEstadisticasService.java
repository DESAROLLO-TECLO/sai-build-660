package mx.com.teclo.saicdmx.negocio.service.infracciones;


import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.infracciones.DepositosEstadisticasVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.EntradaSalidaDepositosGraficaVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesEstadisticasVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionesEstadisticasporDispositivoVO;
public interface InfraccionesEstadisticasService {

	List<InfraccionesEstadisticasVO> infraccionesEstadisticas(String fechaInicio, String fechaFin);
	
	List<InfraccionesEstadisticasVO> infraccionesEstadisticasAños(String fechaInicio, String fechaFin);
	
	List<InfraccionesEstadisticasVO> infraccionesCreadasPagadasEstatico();

	List<InfraccionesEstadisticasporDispositivoVO> infraccionesporDispositivo(String fechaInicio, String fechaFin);

	List<InfraccionesEstadisticasporDispositivoVO> infraccionesporDispositivoEstatico();
	
	List<DepositosEstadisticasVO> depositosEstadisticas();
	
	List<EntradaSalidaDepositosGraficaVO> entradaSalidaDepositosGrafica(String fechaInicio,String fechaFin);
	
	List<EntradaSalidaDepositosGraficaVO> entradaSalidaDepositosGraficaFechas(String fechaInicio,String fechaFin);
}
