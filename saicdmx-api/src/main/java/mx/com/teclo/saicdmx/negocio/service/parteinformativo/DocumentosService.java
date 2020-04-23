package mx.com.teclo.saicdmx.negocio.service.parteinformativo;

import java.text.ParseException;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsNuevoVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsPorDocsVO;

public interface DocumentosService {

	/***
	 * 
	 * @param tipoBusqueda
	 * @param valor
	 * @return
	 */
	public List<ParteInformativoCDocsConsultaVO> buscarPIDocumentos(Integer tipoBusqueda, String valor);
	
	/***
	 * 
	 * @param valor
	 * @return
	 */
	public ParteInformativoCDocsVO buscarDocumentoPorId(long valor);
	
	/***
	 * 
	 * @param parteInformativoCDocsVO
	 * @return
	 * @throws ParseException 
	 */
	public ParteInformativoCDocsVO modificacionDocumento(ParteInformativoCDocsVO parteInformativoCDocsVO, List<ParteInformativoInfracsPorDocsVO> addInfracciones, List<ParteInformativoInfracsPorDocsVO> deleteInfracciones) throws ParseException;

	/***
	 * 
	 * @param parteInformativoCDocsNuevoVO
	 * @param addInfracciones
	 * @param deleteInfracciones
	 * @return
	 */
	public ParteInformativoCDocsNuevoVO crearDocumento(ParteInformativoCDocsNuevoVO parteInformativoCDocsNuevoVO, List<ParteInformativoInfracsPorDocsVO> addInfracciones);
}
