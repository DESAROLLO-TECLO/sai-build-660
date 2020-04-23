package mx.com.teclo.saicdmx.negocio.service.lineadecaptura;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ProtocolException;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.ConsultaInfraccionForReasignacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.DetalleDeReasignacionesInfraccionVO;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;
import mx.com.teclomexicana.arquitectura.ortogonales.exception.BusinessException;

/**
 * 
 * @author Jesus Gutierrez
 *
 */
public interface ReasignacionLCService {
	
	/***
	 * Actualiza la fecha de emision de la infraccion radar
	 * @param folio
	 * @param fechareasignacion
	 * @param usuario
	 * @throws ParseException 
	 */
	public void UpdateFechaEmParaLC(String folio, String fechareasignacion, Long usuario) throws ParseException;

	/***
	 * 
	 * @param folio
	 * @return Un objeto de ConsultaInfraccionForReasignacionVO con datos generales de la infraccion radar
	 */
	public ConsultaInfraccionForReasignacionVO buscaInfraccionRadarByFolio(String folio, String isFolioRadar);
	
	/***
	 * 
	 * @param folio
	 * @param estatus
	 * @return Una lista de las resignaciones que se han hecho a la infraccion radar
	 */
	public List<DetalleDeReasignacionesInfraccionVO> consultaDetalleReasignacionesByInfraccion (String folio, Integer estatus);
	
	/***
	 * 
	 * @param parametrosVO
	 * @param rutaTotalArchivo
	 * @param rutaTotalImagen
	 * @return Un archivo PDF en forma de arreglo de bytes
	 * @throws FileNotFoundException
	 */
	public ByteArrayOutputStream generaReporteFUTPDF(DetalleDeReasignacionesInfraccionVO parametrosVO, String rutaTotalArchivo, String rutaTotalImagen) throws FileNotFoundException;
	
	/***
	 * 
	 * @param objectVO
	 * @param descuento
	 * @param usuario
	 * @return Un objeto con los valores de respuesta del WS de finanzas
	 * @throws BusinessException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws ProtocolException 
	 * @throws ParseException 
	 */
	public RespuestaWSReasignaLineaCapturaVO ReasignarLineaDeCaptura(ConsultaInfraccionForReasignacionVO objectVO,
			Integer descuento, Long usuario, String isFolioRadar) throws ProtocolException, IOException, ParserConfigurationException, SAXException, ParseException;
}
