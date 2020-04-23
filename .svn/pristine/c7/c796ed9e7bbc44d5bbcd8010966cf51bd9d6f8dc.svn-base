package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.DetallePagosDTO;

public interface DetallePagoDAO extends BaseDao<DetallePagosDTO>{
	/**
	 * Obtiene la sumatoria de las columnas de pagValor 
	 * @param   Long cajaId , numero id de la caja
	 * @param String pagId , numero identificador del pago
	 * @return  Double 
	 */
	public Double getSumPagValorDetallePago(Long cajaId,String pagId);
	
	public List<DetallePagosDTO> getDetallePagoDTO(String pagId,CajaDTO cajaId);
	
	public void updateDetallePago(DetallePagosDTO detPagoDTO);
}
