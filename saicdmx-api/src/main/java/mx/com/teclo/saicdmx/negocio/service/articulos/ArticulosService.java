package mx.com.teclo.saicdmx.negocio.service.articulos;

import java.util.Date;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticuloDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticulosInfraccionesFinanzasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.SancionesArticulosDTO;

public interface ArticulosService {
	
	/**
	 * RETORNA ARTICULOS CON ID MAYOR A 1200 Y ESTATUS 'A'
	 * @author KEVIN 
	 * @param
	 * @return  List<ArticuloDTO>
	 */
	public List<ArticuloDTO> buscarArticulosInfracciones();

	/**
	 * RETORNA LISTA DE 'ArticulosInfraccionesFinanzasDTO' CON RANGO DE FECHA EN BASE AL PARAMETRO
	 * @author KEVIN
	 * @param Date fecha
	 * @return List<ArticulosInfraccionesFinanzasDTO>
	 */
	public List<ArticulosInfraccionesFinanzasDTO> buscarArticulosActivosPorFecha(Date fecha);

	/**
	 * RETORNA LISTA DE 'ArticulosInfraccionesFinanzasDTO' CON RANGO DE FECHA EN BASE AL PARAMETRO
	 * Y LA FECHA
	 * @author KEVI
	 * @param Date fecha
	 * @param Long articuloNumero
	 * @return List<ArticulosInfraccionesFinanzasDTO>
	 */
	public List<ArticulosInfraccionesFinanzasDTO> buscarFraccionesActivasPorFechaYArticuloNumero(Date fecha,
			Long articuloNumero);

	/**
	 * 
	 * @param fecha
	 * @param articuloNumero
	 * @param fraccion
	 * @return
	 */
	public List<ArticulosInfraccionesFinanzasDTO> buscarIncisoParrafoActivosPorFechaArticuloNumeroYFraccion(Date fecha,
			Long articuloNumero, Long fraccion);

	/**
	 * 
	 * @param numeroArticulo
	 * @return
	 */
	public List<ArticuloDTO> buscaristaFraccionParrafoIncisoArticulo(String numeroArticulo);

	/**
	 * 
	 * @param idArticulo
	 * @return
	 */
	public List<SancionesArticulosDTO> buscarSancionesPorArticulo(Long idArticulo);

}
