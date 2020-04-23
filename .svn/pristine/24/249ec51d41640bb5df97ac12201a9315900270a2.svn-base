package mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.DetalleCargosDTO;

public interface DetalleCargosDAO extends BaseDao<DetalleCargosDTO>{
	
	/**
	 * Obtiene la sumatoria de las columnas de pagValor 
	 * @param   Long cajaId , numero id de la caja
	 * @param   String pagId , numero id del pago
	 * @return  Double 
	 */
	public Double getSumPagValorDetalleCargos(Long cajaId,String pagId);

	public List<DetalleCargosDTO> getDetalleCargoDTO(String pagId,CajaDTO cajaId);
	
	public void updateDetalleCargos(DetalleCargosDTO detCargosDTO);
}
