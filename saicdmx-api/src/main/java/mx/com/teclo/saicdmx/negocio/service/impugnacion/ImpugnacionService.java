package mx.com.teclo.saicdmx.negocio.service.impugnacion;

import java.text.ParseException;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionImpugnacionDTO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionAltaVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionParametrosVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.VConsulaCanceladasDetalleVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.VConsultaCanceladas;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.VImpugnacionConsultaVO;

/**
 * 
 * @author javier07
 *
 */
public interface ImpugnacionService {

	/**
	 * 
	 * @param tipoBusqueda
	 * @param valor
	 * @return List<InfraccionImpugnacionDTO>
	 */
	List<InfraccionImpugnacionDTO> buscarImpugnaciones(String tipoBusqueda, String valor);

	/**
	 * 
	 * @param id
	 * @return List<VImpugnacionConsultaVO>
	 */
    List<VImpugnacionConsultaVO> buscarInfraccionImpugna(String id);
    /**
     * 
     * @param impugnacionVO
     */
    void actualizarInformacion(ImpugnacionParametrosVO impugnacionVO);
    /**
     * 
     * @param id
     * @return List<InfraccionImpugnacionDTO>
     */
    List<InfraccionImpugnacionDTO> buscarPorId(String id);
    /**
     * 
     * @param impugnacionVO
     * @return
     * @throws ParseException 
     */
    ImpugnacionParametrosVO modificaInfracciones(ImpugnacionParametrosVO impugnacionVO) throws ParseException;
    /**
     * 
     * @param impugnacionVO
     * @return ImpugnacionAltaVO
     */
    ImpugnacionAltaVO altaImpugnacion(ImpugnacionAltaVO impugnacionVO);
    /**
     * 
     * @param tipoBusqueda
     * @param valor
     * @return VConsultaCanceladas
     */
    List<VConsultaCanceladas> obtenerCanceladas(String tipoBusqueda, String valor);

    /**
     * 
     * @param infraccion
     * @return VConsulaCanceladasDetalleVO
     */
    VConsulaCanceladasDetalleVO obtenerVDetalleCanceladas(String infraccion);

    
}
