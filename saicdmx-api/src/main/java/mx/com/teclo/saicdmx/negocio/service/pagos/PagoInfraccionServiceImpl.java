package mx.com.teclo.saicdmx.negocio.service.pagos;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.cambios.cajas.BitTrBitacCajas;
import mx.com.teclo.saicdmx.bitacora.cambios.pagos.BitTrgPagosBitNuevo;
import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CajaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.PagosFinanzasDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos.PagoInfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.caja.VCajaConsultaVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.DatosPagoVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

/**
 *  Copyright (c) 2016, Teclo Mexicana. 
 *  
 *  Descripcion					: PagoInfraccionServiceImpl
 *  Historial de Modificaciones	: 
 *  Descripcion del Cambio 		: Creacion
 *  @author 					: fjmb
 *  @version 					: 1.0 
 *  Fecha 						: 05/Octubre/2016
 */

@Service
public class PagoInfraccionServiceImpl implements PagoInfraccionService {
 
	@Autowired
    @Qualifier("pagoEfectivo")
	private PagoService pagoEfectivo;
	@Autowired
	@Qualifier("pagoTarjeta")
	private PagoService pagoTarjeta;
	@Autowired
	@Qualifier("pagoDocumento")
	private PagoService pagoDocumento;
	@Autowired
	@Qualifier("pagoActaAdministrativa")
	private PagoService pagoActaAdministrativa;
	
	@Autowired
	private PagoInfraccionMyBatisDAO pagoInfraccionMyBatisDAO;
	@Autowired
	private BitacoraCambiosService bitacoraCambiosService;
	@Autowired
	private UsuarioFirmadoService 	usuarioFirmadoService;
	@Autowired
	private CajaDAO cajaDAO;
	
	//private CajaDTO cajaDTO;
	@Autowired
	private BitTrBitacCajas bitTrBitacCajas;
	@Autowired
	private BitTrgPagosBitNuevo bitTrgPagosBitNuevo;
	
	@Autowired
	private PagosFinanzasDAO pagosFinanzasDAO;
	
	@Override
	public PagoVO realizarPagoEfectivo(DatosPagoVO datosPagoVO) throws ParseException {
// 		return  pagoEfectivo.pagar(datosPagoVO);
		VCajaConsultaVO newVCajaConsultaVO = new VCajaConsultaVO();
		VCajaConsultaVO oldVCajaConsultaVO = new VCajaConsultaVO();
		CajaDTO cajaDTO = new CajaDTO();
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		oldVCajaConsultaVO.setCajaNumPago(pagoInfraccionMyBatisDAO.sqlCajaNumPagoOld(cajaDTO.getCajaId().toString()));
		datosPagoVO.setCajaId(cajaDTO.getCajaId().toString());
		PagoVO pagoVO = pagoEfectivo.pagar(datosPagoVO); 
		newVCajaConsultaVO.setCajaNumPago(pagoInfraccionMyBatisDAO.sqlCajaNumPagoOld(cajaDTO.getCajaId().toString()));
		newVCajaConsultaVO.setCajaIdD(pagoVO.getCajaId());
		newVCajaConsultaVO.setModificadoPor(pagoVO.getUsuario());
		if(!pagoVO.getResultado().equals("-1")){
			ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
			
			bitacoraCambiosVOs.addAll(bitTrgPagosBitNuevo.guardarCambiosBitacora(pagoVO));
			String tipo = datosPagoVO.getInfracNum().substring(0, 2);
			if(tipo.equals("01")||tipo.equals("02")||tipo.equals("04")||tipo.equals("05")||tipo.equals("06")){ // Se evalua el tipo de infraccion
				bitacoraCambiosVOs.add(bitCambioAutorizacion(datosPagoVO));
			}
			bitacoraCambiosVOs.addAll(bitTrBitacCajas.guardarCambiosBitacora(newVCajaConsultaVO, oldVCajaConsultaVO));
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
			
		}
 		return  pagoVO;
	}
	@Override
	public PagoVO realizarPagoTarjeta(DatosPagoVO datosPagoVO) throws ParseException {
// 		return pagoTarjeta.pagar(datosPagoVO);
		VCajaConsultaVO newVCajaConsultaVO = new VCajaConsultaVO();
		VCajaConsultaVO oldVCajaConsultaVO = new VCajaConsultaVO();
		CajaDTO cajaDTO = new CajaDTO();
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		oldVCajaConsultaVO.setCajaNumPago(pagoInfraccionMyBatisDAO.sqlCajaNumPagoOld(cajaDTO.getCajaId().toString()));
		datosPagoVO.setCajaId(cajaDTO.getCajaId().toString());
		PagoVO pagoVO = pagoTarjeta.pagar(datosPagoVO); 
		newVCajaConsultaVO.setCajaNumPago(pagoInfraccionMyBatisDAO.sqlCajaNumPagoOld(cajaDTO.getCajaId().toString()));
		newVCajaConsultaVO.setCajaIdD(pagoVO.getCajaId());
		newVCajaConsultaVO.setModificadoPor(pagoVO.getUsuario());
		if(!pagoVO.getResultado().equals("-1")){
			ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
			
			bitacoraCambiosVOs.addAll(bitTrgPagosBitNuevo.guardarCambiosBitacora(pagoVO));
			String tipo = datosPagoVO.getInfracNum().substring(0, 2);
			if(tipo.equals("01")||tipo.equals("02")||tipo.equals("04")||tipo.equals("05")||tipo.equals("06")){ // Se evalua el tipo de infraccion
				bitacoraCambiosVOs.add(bitCambioAutorizacion(datosPagoVO));
			}
			bitacoraCambiosVOs.addAll(bitTrBitacCajas.guardarCambiosBitacora(newVCajaConsultaVO, oldVCajaConsultaVO));
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
			
		}
 		return  pagoVO;
	}
	@Override
	public PagoVO realizarPagoDocumento(DatosPagoVO datosPagoVO) throws ParseException {
// 		return pagoDocumento.pagar(datosPagoVO);	
		VCajaConsultaVO newVCajaConsultaVO = new VCajaConsultaVO();
		VCajaConsultaVO oldVCajaConsultaVO = new VCajaConsultaVO();
		CajaDTO cajaDTO = new CajaDTO();
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		oldVCajaConsultaVO.setCajaNumPago(pagoInfraccionMyBatisDAO.sqlCajaNumPagoOld(cajaDTO.getCajaId().toString()));
		datosPagoVO.setCajaId(cajaDTO.getCajaId().toString());
		PagoVO pagoVO = pagoDocumento.pagar(datosPagoVO); 
		newVCajaConsultaVO.setCajaNumPago(pagoInfraccionMyBatisDAO.sqlCajaNumPagoOld(cajaDTO.getCajaId().toString()));
		newVCajaConsultaVO.setCajaIdD(pagoVO.getCajaId());
		newVCajaConsultaVO.setModificadoPor(pagoVO.getUsuario());
		
		if(!pagoVO.getResultado().equals("-1")){
			ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
			if(!pagoVO.getFormaPago().equals("99")){ // Si el pago es solo con documento se bitacorea un registro que es el modificadoPor 
				bitacoraCambiosVOs.addAll(bitTrgPagosBitNuevo.guardarCambiosBitacora(pagoVO));
				bitacoraCambiosVOs.addAll(bitTrBitacCajas.guardarCambiosBitacora(newVCajaConsultaVO, oldVCajaConsultaVO));
			}
			String tipo = datosPagoVO.getInfracNum().substring(0, 2);
			if(tipo.equals("01")||tipo.equals("02")||tipo.equals("04")||tipo.equals("05")||tipo.equals("06")){ // Se evalua el tipo de infraccion
				bitacoraCambiosVOs.add(bitCambioAutorizacion(datosPagoVO));
			}
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
			
		}
 		return  pagoVO;
	}
	@Override
	public PagoVO realizarPagoActaAdministrativa(DatosPagoVO datosPagoVO) throws ParseException {
// 		return pagoActaAdministrativa.pagar(datosPagoVO);
		VCajaConsultaVO newVCajaConsultaVO = new VCajaConsultaVO();
		VCajaConsultaVO oldVCajaConsultaVO = new VCajaConsultaVO();
		CajaDTO cajaDTO = new CajaDTO();
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
		oldVCajaConsultaVO.setCajaNumPago(pagoInfraccionMyBatisDAO.sqlCajaNumPagoOld(cajaDTO.getCajaId().toString()));
		datosPagoVO.setCajaId(cajaDTO.getCajaId().toString());
		PagoVO pagoVO = pagoActaAdministrativa.pagar(datosPagoVO); 
		newVCajaConsultaVO.setCajaNumPago(pagoInfraccionMyBatisDAO.sqlCajaNumPagoOld(cajaDTO.getCajaId().toString()));
		newVCajaConsultaVO.setCajaIdD(pagoVO.getCajaId());
		newVCajaConsultaVO.setModificadoPor(pagoVO.getUsuario());
		if(!pagoVO.getResultado().equals("-1")){
			ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
			
			bitacoraCambiosVOs.addAll(bitTrgPagosBitNuevo.guardarCambiosBitacora(pagoVO));
			bitacoraCambiosVOs.add(bitCambioAutorizacion(datosPagoVO));

			bitacoraCambiosVOs.addAll(bitTrBitacCajas.guardarCambiosBitacora(newVCajaConsultaVO, oldVCajaConsultaVO));
			bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
			
		}
 		return  pagoVO;
	}
	
	
	/* Bitacora de cambio, generar bitacora de autorización al realizar cambios en la tabla de infracciones... */
	private BitacoraCambiosVO bitCambioAutorizacion(DatosPagoVO datosPagoVO){
		BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVO.setComponenteId(5L);
		bitacoraCambiosVO.setConceptoId(44L);
		bitacoraCambiosVO.setValorOriginal(null);
		bitacoraCambiosVO.setValorFinal(null);
		bitacoraCambiosVO.setCreadoPor(Long.parseLong(pagoInfraccionMyBatisDAO.sqlUsuarioOld(datosPagoVO.getInfracNum())));
		bitacoraCambiosVO.setIdRegistro(datosPagoVO.getInfracNum());
		bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return bitacoraCambiosVO;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public BigDecimal buscarActaConPagoEnLinea(String numInfraccion) {
		return  pagosFinanzasDAO.getPagoEnLinea(numInfraccion);
	}
 
}
