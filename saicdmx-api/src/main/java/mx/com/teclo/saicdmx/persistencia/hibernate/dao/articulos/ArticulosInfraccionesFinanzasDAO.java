package mx.com.teclo.saicdmx.persistencia.hibernate.dao.articulos;

import java.util.Date;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.dao.comun.BaseDao;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticulosInfraccionesFinanzasDTO;

public interface ArticulosInfraccionesFinanzasDAO extends BaseDao<ArticulosInfraccionesFinanzasDTO>{

	public List<ArticulosInfraccionesFinanzasDTO> buscarArticulosActivosPorFecha(Date fecha);

	public List<ArticulosInfraccionesFinanzasDTO> buscarFraccionesActivasPorFechaYArticuloNumero(Date fecha, Long articuloNumero);

	public List<ArticulosInfraccionesFinanzasDTO> buscarIncisoParrafoActivosPorFechaArticuloNumeroYFraccion(Date fecha,
			Long articuloNumero, Long fraccion);
	
	public List<ArticulosInfraccionesFinanzasDTO> buscarArticulosInfraccionesFinanzasPorArticulo(Long articuloId);
	
	public ArticulosInfraccionesFinanzasDTO buscarArticulosInfraccionesFinanzasPorId(Long id);

}
