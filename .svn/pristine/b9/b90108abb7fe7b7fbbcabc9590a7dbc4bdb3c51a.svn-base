package mx.com.teclo.saicdmx.negocio.service.impugnacion;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.bitacora.cambios.impugnacion.BitSpImpugnacionInfracs;
import mx.com.teclo.saicdmx.bitacora.cambios.impugnacion.BitSpImpugnacionNuevo;
import mx.com.teclo.saicdmx.bitacora.cambios.impugnacion.BitTrBitacImpugna;
import mx.com.teclo.saicdmx.bitacora.cambios.infracciones.BitTrBitUpInfrac;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.impugnacion.ImpugnacionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones.InfraccionImpugnacionDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.impugnacion.ImpugnacionCanceladasDetalleMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.impugnacion.ImpugnacionCanceladasMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.impugnacion.ImpugnacionInfraccMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.impugnacion.ImpugnacionModificaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.impugnacion.ImpugnacionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.impugnacion.ImpugnacionNuevaMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.infracciones.InfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionAltaVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionParametrosVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.InfraccionImpugnacionInfracsVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.VConsulaCanceladasDetalleVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.VConsultaCanceladas;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.VImpugnacionConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VSSPInfracConsGralFVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclo.saicdmx.util.enumerados.ImpugnacionDetalleEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

/**
 * 
 * @author javier07
 *
 */
@Service
public class ImpugnacionServiceImpl implements ImpugnacionService {

	@Autowired
	private ImpugnacionDAO impugnacionDAO;
	@Autowired
	private ImpugnacionMyBatisDAO impugnacionMyBatisDAO;
	@Autowired
	private ImpugnacionModificaMyBatisDAO impugnacionModificaMyBatisDAO;
	@Autowired
	private ImpugnacionInfraccMyBatisDAO infraccionMyBatisDAO;
	@Autowired
	private ImpugnacionNuevaMyBatisDAO altaImpugnacionMyBatisDAO;
	@Autowired
	private ImpugnacionCanceladasMyBatisDAO canceladasMyBatisDAO;
	@Autowired
	private ImpugnacionCanceladasDetalleMyBatisDAO canceladasDetalleMyBatisDAO;
	@Autowired
	private BitacoraCambiosService bitacoraCambiosService;
	@Autowired
	private BitTrBitacImpugna bitTrBitacImpugna;
	@Autowired
	private BitTrBitUpInfrac bitTrBitUpInfrac;
	@Autowired
	private InfraccionMyBatisDAO infraccionDAO;
	@Autowired
	private BitSpImpugnacionInfracs bitSpImpugnacionInfracs;
	@Autowired
	private BitSpImpugnacionNuevo bitSpImpugnacionNuevo;
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<InfraccionImpugnacionDTO> buscarImpugnaciones(String tipoBusqueda, String valor) {
		
		return impugnacionDAO.buscarImppugnacion(tipoBusqueda, valor);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VImpugnacionConsultaVO> buscarInfraccionImpugna(String id) {
		
		return impugnacionMyBatisDAO.obtieneInfraccionImpugna(id);
	}

	/**
	 * {@inheritDoc}
	 * @author dagoberto
	 * SP_IMPUGNACION_MOD
	 */
	@Override
	@Transactional
	public void actualizarInformacion(ImpugnacionParametrosVO impugnacionVO) {

		ImpugnacionParametrosVO newImpugnacionVO = impugnacionVO;//nuevo
		ImpugnacionParametrosVO oldImpugnacionVO = new ImpugnacionParametrosVO();//old
		InfraccionImpugnacionDTO oldInfraccionImpugnacionDTO = impugnacionDAO.findOne(impugnacionVO.getImpugnacionId());
		ResponseConverter.copiarPropriedades(oldImpugnacionVO, oldInfraccionImpugnacionDTO);
		SimpleDateFormat realsdf = new SimpleDateFormat("dd/MM/yyyy");
		oldImpugnacionVO.setFechaRecepcion(realsdf.format(oldInfraccionImpugnacionDTO.getFechaRecepcion()));
		newImpugnacionVO.setFechaRecepcion(impugnacionVO.getFechaRecepcion());
     	impugnacionModificaMyBatisDAO.actualizaImpugnaInfo(impugnacionVO);
     	if(!impugnacionVO.getResultado().equals("-1")){
	     	ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs=new ArrayList<>();
			bitacoraCambiosVOs = bitTrBitacImpugna.compararInfracImpugnacion(newImpugnacionVO, oldImpugnacionVO);
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
     	}
     	
     	System.out.println("---- Resultado ----- : " +impugnacionVO.getMensaje());
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public List<InfraccionImpugnacionDTO> buscarPorId(String id) {
		List<InfraccionImpugnacionDTO> listaDTO = new ArrayList<InfraccionImpugnacionDTO>();
		listaDTO.add(impugnacionDAO.findOne(Long.parseLong(id)));		
		return listaDTO;
	}


	/**
	 * {@inheritDoc}
	 * @throws ParseException 
	 * Metodo que corresponde a SP_IMPUGNACION_INFRACS
	 */
	@Transactional
	@Override
	public ImpugnacionParametrosVO modificaInfracciones(ImpugnacionParametrosVO impugnacionVO) throws ParseException {
		Integer modificadoPor;
		String infracNumCtrl;
		try {
			modificadoPor = infraccionDAO.getModificadoPor(impugnacionVO.getInfraccNum());
		}
		catch (Exception e) {
			modificadoPor = null;
		}
		try {
			infracNumCtrl = infraccionDAO.getInfracNumCtrl(impugnacionVO.getInfraccNum());
		}catch (Exception e) {
			infracNumCtrl = null;
		}
		@SuppressWarnings("unused")
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<BitacoraCambiosVO>();
		if(modificadoPor!=null && infracNumCtrl!=null){
			AltaInfraccionSPVO newAltaInfraccionSPVO = new AltaInfraccionSPVO();
			VSSPInfracConsGralFVO oldAltaInfraccionSPVO = new VSSPInfracConsGralFVO();
			newAltaInfraccionSPVO.setP_modificado_por(impugnacionVO.getEmpleadoId().intValue());
			newAltaInfraccionSPVO.setP_infrac_num_ctrl(infracNumCtrl);
			oldAltaInfraccionSPVO.setOficialPlaca(modificadoPor.toString()); 
			bitacoraCambiosVOs = bitTrBitUpInfrac.guardarCambiosBitacora(newAltaInfraccionSPVO, oldAltaInfraccionSPVO);
		}
		InfraccionImpugnacionInfracsVO oldInfraccionImpugnacionInfracsVO = impugnacionMyBatisDAO.obtieneInfraccionImpugnaInfrac(impugnacionVO.getInfraccNum(),impugnacionVO.getImpugnacionId().toString());
		infraccionMyBatisDAO.callSPImpugnaInfracc(impugnacionVO);
		if(!impugnacionVO.getResultado().equals("-1")){
			bitSpImpugnacionInfracs.updateImpugnacionSpInfrac(impugnacionVO,oldInfraccionImpugnacionInfracsVO);	
		}
		
		
        return impugnacionVO;
	}

	/**
	 * {@inheritDoc}
	 * @author dagoberto
	 * @SP_IMPUGNACION_NUEVO
	 */
	@Override
	public ImpugnacionAltaVO altaImpugnacion(ImpugnacionAltaVO impugnacionVO) {
		altaImpugnacionMyBatisDAO.nuevaImpugnacion(impugnacionVO);		
		if(!impugnacionVO.getResultado().equals("-1")){
			bitSpImpugnacionNuevo.altaImpugnacion(impugnacionVO);
		}		
		return impugnacionVO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<VConsultaCanceladas>  obtenerCanceladas(String tipoBusqueda, String valor) {
		
		List<VConsultaCanceladas> listaCanceladasVO = new ArrayList<VConsultaCanceladas>();
		
		switch (tipoBusqueda) {
		case "PLACA":
			listaCanceladasVO = canceladasMyBatisDAO.obtieneCanceladasPlaca(valor);
			break;
		case "IMPRESA":
			listaCanceladasVO = canceladasMyBatisDAO.obtieneCanceladasLicencia(valor);
			break;
		case "INFRAC":
			listaCanceladasVO = canceladasMyBatisDAO.obtieneCanceladasInfraccion(valor);
			break;
		case "LICENCIA":
			listaCanceladasVO = canceladasMyBatisDAO.obtieneCanceladasPreimpresa(valor);
			break;			
		default:
			break;
		}
		return listaCanceladasVO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public VConsulaCanceladasDetalleVO obtenerVDetalleCanceladas(String infraccion) {
		
		VConsulaCanceladasDetalleVO consulaCanceladasDetalleVO = null;
		consulaCanceladasDetalleVO =  canceladasDetalleMyBatisDAO.obtieneVConsultaCanceladas(infraccion);		
		consulaCanceladasDetalleVO.setEstado(ImpugnacionDetalleEnum.ESTADO.getEstado());
		return consulaCanceladasDetalleVO;
	}
	
	

}
