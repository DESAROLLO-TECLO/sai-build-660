package mx.com.teclo.saicdmx.negocio.service.caja;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

import org.apache.ibatis.exceptions.PersistenceException;

import mx.com.teclo.saicdmx.persistencia.vo.caja.ProcInformeCorteSPVO;

public interface CajaReporteService {

	/**
	 * @author Kevin Ojeda
	 * RETORNA REPORTE DE CREACIÓN DE CORTE DE CAJA
	 * @param String rutaTotalArchivo
	 * @param ProcInformeCorteSPVO procInformeCorteSPVO
	 * @return ByteArrayOutputStream
	 * @throws FileNotFoundException
	 */
	public ByteArrayOutputStream impresionCorte(String rutaTotalArchivo, ProcInformeCorteSPVO procInformeCorteSPVO)
			throws FileNotFoundException;


	/**
	 * RETORNA IMPRESION DE LOS REGISTROS PERTENCIENTES A LA FUNCION FN_REGLONES_CORTE_PARTIDAS_2_TP CON ARGUMENTOS CAJAID Y NUMCAJA
	 * @author Kevin Ojeda
	 * @param String cajaId
	 * @param String numCaja
	 * @return ByteArrayOutputStream
	 * @throws PersistenceException
	 */
	public ByteArrayOutputStream impresionRenglonesCortePartida(String cajaId, String numCaja, String fecha);


	/**
	 * RETORNA IMPRESION DE LOS REGISTROS PERTENCIENTES A LA FUNCION FN_REGLONES_CORTE_INFRACCION_2_TP CON ARGUMENTOS CAJAID Y NUMCAJA
	 * @author Kevin Ojeda
	 * @param String cajaId
	 * @param String numCaja
	 * @return ByteArrayOutputStream
	 * @throws PersistenceException
	 */
	public ByteArrayOutputStream impresionRenglonesCorteInfraccion(String cajaId, String numCaja, String fecha);


	/**
	 * RETORNA IMPRESION DE LOS REGISTROS PERTENCIENTES A LA FUNCION FN_REGLONES_CORTE_PARTIDAS_TAR CON ARGUMENTOS CAJAID Y NUMCAJA
	 * @author Kevin Ojeda
	 * @param String cajaId
	 * @param String numCaja
	 * @return ByteArrayOutputStream
	 * @throws PersistenceException
	 */
	public ByteArrayOutputStream impresionRenglonesCortePartidaTarjeta(String cajaId, String numCaja, String fecha, String cajaCod);

}
