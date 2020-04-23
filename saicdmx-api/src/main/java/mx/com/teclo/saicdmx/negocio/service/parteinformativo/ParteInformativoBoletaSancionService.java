package mx.com.teclo.saicdmx.negocio.service.parteinformativo;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionModificacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionNuevaVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsPorBolsVO;

public interface ParteInformativoBoletaSancionService {

	/***
	 * @author Jesus Gutierrez
	 * @param tipoBusqueda
	 * @param valor
	 * @return Lista de boletas
	 * @throws
	 */
	public List<ParteInformativoBoletaSancionConsultaVO> buscarPIBoletas(Integer tipoBusqueda, String valor);
	
	/***
	 * @author Jesus Gutierrez
	 * @param valor
	 * @return Un Objeto de Boleta para su modificacion
	 * @throws
	 */
	public ParteInformativoBoletaSancionModificacionVO buscarBoletaPorId(Long valor);
	
	/***
	 * @author Jesus Gutierrez
	 * @param boletaVO
	 * @param addInfracciones
	 * @param deleteInfracciones
	 * @return Un objeto de tipo ParteInformativoBoletaSancionModificacionVO con el resultado y mensaje del SP
	 */
	public ParteInformativoBoletaSancionModificacionVO modificacionBoleta(
			ParteInformativoBoletaSancionModificacionVO boletaVO,
			List<ParteInformativoInfracsPorBolsVO> addInfracciones, List<ParteInformativoInfracsPorBolsVO>deleteInfracciones);
	
	/***
	 * @author Jesus Gutierrez
	 * @param boletaVO
	 * @param addInfracciones
	 * @return
	 */
	public ParteInformativoBoletaSancionNuevaVO crearBoleta(ParteInformativoBoletaSancionNuevaVO boletaVO,
			List<ParteInformativoInfracsPorBolsVO> addInfracciones);
}
