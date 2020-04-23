package mx.com.teclo.saicdmx.negocio.service.pagos;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.ingresos.IngresosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.InfraccionDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.InfraccionPorPagarVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagadoVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.ConsultaTrasladoVO;

/**
 * Copyright (c) 2016, Teclo Mexicana. 
 * 
 * Descripcion					: PagoInfraccionService
 * Historial de Modificaciones	: 
 * Descripcion del Cambio 		: Creacion
 * @author 						: fjmb
 * @version 					: 1.0 
 * Fecha 						: 05/Octubre/2016
 */
 
public interface ConsultaInfraccionService {
	
	/**
	 *  Obtiene una lista de entidades con diferentes tipos de pago.
	 *  @param String  parametro
     *  @param String valor
	 *  @return List<FilterValuesVO>  Lista de entidades.
	 */
	public List<InfraccionDepositoVO> obtenerInfracciones(String parametro, String valor );
 
	/**
	 *  Obtiene una lista de entidades con diferentes tipos de pago.
	 *  @param String  parametro
     *  @param String valor
	 *  @return List<FilterValuesVO>  Lista de entidades.
	 */
	public List<InfraccionDepositoVO> obtenerInfraccionesActa(String parametro, String valor );
	
	/**
	 * Obtiene una lista de entidades infracciones por pagar .
	 * @param String  nci
     * @param String noInfraccion
	 * @return List<InfraccionPorPagarVO>  Lista de entidades infracciones por pagar.
	 */
	public List<InfraccionPorPagarVO> obtenerInfraccionesPorPagar(String nci, String noInfraccion );
	
	/**
	 * Obtiene una lista de entidades con infracciones pagadas en el dia.
	 * @param String  parametro
     * @param String valor
     * @param fecha
	 * @return List<InfraccionPorPagarVO>  Lista de entidades.
	 */
	public List<InfraccionPorPagarVO> obtenerInfraccionesPagadasPorDia( String parametro, String valor, String fecha );
	
	/**
	 * Obtiene una lista de entidades de infracciones pagadas.
	 * @param String  parametro
     * @param String valor
	 * @return List<FilterValuesVO>  Lista de entidades.
	 */
	public List<PagadoVO> obtenerInfraccionesPagadas( String parametro, String valor );
 
	/**
	 * Obtiene una lista de entidades con diferentes Tipos de Búsqueda.
	 * @return List<FilterValuesVO>  Lista de entidades.
	 */
	public List<FilterValuesVO> obtenerTipoBusquedaInfraccion ();
	
	/**
	 * Obtiene una lista de entidades con diferentes Tipos de Búsqueda par el Pago con Acta Administrativa.
	 * @return List<FilterValuesVO>  Lista de entidades.
	 */
	public List<FilterValuesVO> obtenerTipoBusquedaInfraccionActa ();
	
	
	/**
	 * Obtiene una lista de entidades con diferentes Tipos de Pago.
	 * @return List<FilterValuesVO>  Lista de entidades.
	 */
	public List<FilterValuesVO> obtenerTiposPagoInfraccion ();
	
	/**
	 * Obtiene una lista de entidades con diferentes Entidades de Pago.
	 * @return List<FilterValuesVO>  Lista de entidades.
	 */
	public List<FilterValuesVO> obtenerEntidadesPagoInfraccion ();
	
	/**
	 * Obtiene una lista de los tipos de busqueda para Transacciones
	 * @return List<FilterValuesVO>  Lista de entidades.
	 */
	public List<FilterValuesVO> obtenerTipoBusquedaTransacciones ();
	
	/**
	 * Obtiene una lista de entidades con diferentes Entidades de Pago.
	 * @return List<FilterValuesVO>  Lista de entidades.
	 */
	public boolean obtenerPerfilCajero (String numPlaca);

	/**
	 * Obtiene el token  para configuracion de terminal.
	 * @return String  Token obtenida de bd.
	 */
	public String obtenerTokenMit();
	
	public IngresosDTO validarTraslado(String tipoParametro, String valorParametro, boolean b);

	public ConsultaTrasladoVO buscaTrasladoParaIngreso(String tipoParam,String valor, boolean isIngreso);

	public boolean validaDestinoTraslado(String depDestino);
}
