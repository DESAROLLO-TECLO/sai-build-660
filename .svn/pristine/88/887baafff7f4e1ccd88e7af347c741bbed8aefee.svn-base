package mx.com.teclo.saicdmx.negocio.service.infracciones;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionDigitalTodoDiaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionRadarDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionesImagenesDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.IngresosImagenesDTO;
import mx.com.teclo.saicdmx.persistencia.vo.articulos.ArticuloVO;
import mx.com.teclo.saicdmx.persistencia.vo.comuns.FilterValuesVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.CargaDigitalizacionWebVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.CountVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.InfraccionAllDataVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.ModificaEnDepositoSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.ModificaEnDepositoVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.SuspensionInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VInfraccionesValidaImagenVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VSSPInfracConsGralFVO;

public interface InfraccionService {
  
  /**
   * RETORNA LA LISTA DE LOS TIPOS DE FILTRO PARA BUSQUEDA DE INFRACCIONES
   * @author Kevin
   * @return List<FilterValuesVO>
   */
  public List<FilterValuesVO> buscarTiposFitroParaBusquedaInfracciones();
  
  public List<VSSPInfracConsGralFVO> buscarInfracciones(String valor, String tipoBusqueda);

  public AltaInfraccionSPVO crearInfraccion(AltaInfraccionSPVO altaInfraccionSPVO);

  /**
   * RETORNA DETALLE COMPLETO DE UNA INFRACCION, BUSCADA POR EL CAMPO INFRAC_NUM
   * @author Kevin Ojeda
   * @param String Valor
   * @return VSSPInfracConsGralFVO
   */
  public VSSPInfracConsGralFVO buscarInfraccionesAllDataByInfracNum(String valor);

  /**
   * RETORNA UN REGISTRO DE LA TABLA InfraccionesImagenesDTO FILTRADO POR EL CAMPO NOMBRE_ARCHIVO
   * @author Kevin Ojeda
   * @param nombre
   * @return List<InfraccionesImagenesDTO>
   */
  public InfraccionesImagenesDTO buscaFotoPorNombreArchivo(String nombre);
  
  /**
   * MODIFICA UNA INFRACCION
   * @author Kevin Ojeda
   * @param AltaInfraccionSPVO modificaInfraccionSPVO
   * @return AltaInfraccionSPVO-
 * @throws ParseException 
   * @throws PersistenceException
   */
  public AltaInfraccionSPVO modificaInfraccion(AltaInfraccionSPVO altaInfraccionSPVO) throws ParseException;

  
  public InfraccionRadarDTO buscarInfraccionPorFolio(String folio);

  /**
	 * Ejecuta SP de modificacion en Deposito
	 * @author Kevin Ojeda
	 * @param modificaEnDepositoSPVO
	 * @return ModificaEnDepositoSPVO modificaEnDepositoSPVO
 * @throws ParseException 
	 * @throws PersistenceException
	 */
  public ModificaEnDepositoSPVO modificaInfraccionEnDeposito(ModificaEnDepositoVO modDepVO, Long modificadoPor) throws ParseException;

  /**
	 * Ejecuta SP de suspension de infraccion
	 * @author Kevin Ojeda
	 * @param SuspensionInfraccionSPVO
	 * @return SuspensionInfraccionSPVO suspensionInfraccionSPVO
 * @throws ParseException 
	 * @throws PersistenceException
	 */
  public SuspensionInfraccionVO suspensionInfraccion(SuspensionInfraccionVO suspensionInfraccionVO, Long emp_id) throws ParseException;

  /**
	 * Retorna un listado de registros de la tabla INFRACCIONES_DIGITAL_TODO_DIA filtrados por status
	 * @author Kevin Ojeda
	 * @param String status
	 * @return InfraccionDigitalTodoDiaDTO
	 */
  public List<InfraccionDigitalTodoDiaDTO> buscarInfraccionDigitalTodoDiaDTOPorEstatus(String status);

  /**
	 * Retorna el total del listado de registros de la tabla INFRACCIONES_DIGITAL_TODO_DIA filtrados por status
	 * @author Kevin Ojeda
	 * @param String status
	 * @return InfraccionDigitalTodoDiaDTO
	 */
  public CountVO countByStatus(String status);
  
  /**
	 * Ejecuta SP de carga de digitalización de infraccion
	 * @author Kevin Ojeda
	 * @param SuspensionInfraccionSPVO
	 * @return SuspensionInfraccionSPVO suspensionInfraccionSPVO
	 * @throws PersistenceException
	 */
  public CargaDigitalizacionWebVO CargaDigitalizacionWeb(CargaDigitalizacionWebVO cargaDigitalizacionWebVO, Long emp_id);

  /**
   * Retorna un objeto con los catalogos necesarios para dar de alta una infraccion
   * @author Kevin Ojeda
   * @return InfraccionAllDataVO
   */
  public InfraccionAllDataVO gatherAllData();

  /**
	 * RETORNA UN REGISTRO DE LA VISTA V_INFRACCIONES_VALIDA_IMAGEN FILTRADO POR INFRAC_NUM
	 * @author Kevin Ojeda
	 * @param vInfraccionesValidaImagenVO
	 * @return List<VInfraccionesValidaImagenVO>
	 * @throws PersistenceException
	 */
  public VInfraccionesValidaImagenVO vInfraccionesValidaImagen(String infracNum);

  
  /**
	 * RETORNA UN REGISTRO DE LA PLACA
	 * @author Teclo
	 * @param Infranum
	 * @return String 
	 * @throws PersistenceException
	 */
  
  
  public String getPlacaInfraccion(String infraNum, String parametro);
  
  
  
  
  
  /**
   * RETORNA REPORTE DE CONSULTA DE INFRACCIÓN
   * @author Kevin Ojeda
   * @param String nci
   * @return ByteArrayOutputStream
   */
  public ByteArrayOutputStream generaReporteConsulta(String nci);

  public IngresosImagenesDTO buscaFotoPorNombreArchivoIngreso(String nombre);

  public List<ArticuloVO> buscarExcepcionesEnArticulos(String articulo);
  
  public void consultaExisteExpedienteInfraccion(List<VSSPInfracConsGralFVO> listaInfracciones);
}
