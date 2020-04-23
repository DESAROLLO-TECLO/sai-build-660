package mx.com.teclo.saicdmx.negocio.service.articulos;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.articulos.ArticuloDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.articulos.ArticulosInfraccionesFinanzasDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.articulos.SancionesArticulosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticuloDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.ArticulosInfraccionesFinanzasDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.articulos.SancionesArticulosDTO;

@Service
public class ArticulosServiceImpl implements ArticulosService{
	
	@Autowired
	private ArticuloDAO articuloDAO;
	
	@Autowired
	private SancionesArticulosDAO sancionesArticuloDAO;
	
	@Autowired
	private ArticulosInfraccionesFinanzasDAO articulosInfraccionesFinanzasDAO;
	
	@Override
	@Transactional
	public List<ArticuloDTO> buscarArticulosInfracciones() {
		return articuloDAO.buscarNumerosArticulosInfracciones();
	}
	
	@Override
	@Transactional
	public List<ArticulosInfraccionesFinanzasDTO> buscarArticulosActivosPorFecha(Date fecha) {
		return articulosInfraccionesFinanzasDAO.buscarArticulosActivosPorFecha(fecha);
	}
	
	@Override
	@Transactional
	public List<ArticulosInfraccionesFinanzasDTO> buscarFraccionesActivasPorFechaYArticuloNumero(Date fecha, Long articuloNumero) {
		return articulosInfraccionesFinanzasDAO.buscarFraccionesActivasPorFechaYArticuloNumero(fecha, articuloNumero);
	}
	
	@Override
	@Transactional
	public List<ArticulosInfraccionesFinanzasDTO> buscarIncisoParrafoActivosPorFechaArticuloNumeroYFraccion
	(Date fecha, Long articuloNumero, Long fraccion) {
		return articulosInfraccionesFinanzasDAO.buscarIncisoParrafoActivosPorFechaArticuloNumeroYFraccion
				(fecha, articuloNumero, fraccion);
	}

	@Override
	@Transactional
	public List<ArticuloDTO> buscaristaFraccionParrafoIncisoArticulo(String numeroArticulo) {
		return articuloDAO.buscarFraccionesArticulo(numeroArticulo);
	}
	
	@Override
	@Transactional
	public List<SancionesArticulosDTO> buscarSancionesPorArticulo(Long idArticulo) {
		return sancionesArticuloDAO.buscarSancionesPorArticulo(idArticulo);
	}
}
