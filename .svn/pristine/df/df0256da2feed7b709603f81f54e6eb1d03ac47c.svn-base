package mx.com.teclo.saicdmx.negocio.service.pagos;

import org.springframework.beans.factory.annotation.Autowired;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.catalogos.CajaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos.PagoInfraccionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.PagoVO;

/**
 * Copyright (c) 2016, Teclo Mexicana. 
 * 
 * Descripcion					: AbstractPagoService
 * Historial de Modificaciones	: 
 * Descripcion del Cambio 		: Creacion
 * @author 						: fjmb
 * @version 					: 1.0 
 * Fecha 						: 05/Octubre/2016
 */
 
public abstract class  AbstractPagoService implements PagoService {
	

	@Autowired
	private UsuarioFirmadoService 	usuarioFirmadoService;
	@Autowired
	private CajaDAO cajaDAO;
	@Autowired
	private PagoInfraccionMyBatisDAO pagoInfraccionMyBatisDAO;
	
	private CajaDTO cajaDTO;
	
	public void consultarCaja(String nci){
		cajaDTO = new CajaDTO();
		cajaDTO = cajaDAO.buscarCajaEmpleado(usuarioFirmadoService.getUsuarioFirmadoVO().getId());
	}
	
	public PagoVO consultarDetallePago(String nci, Long empCaja){
		return pagoInfraccionMyBatisDAO.consultarDetallePago(nci, empCaja);
	}

	public CajaDTO getCajaDTO() {
		return cajaDTO;
	}

	public void setCajaDTO(CajaDTO cajaDTO) {
		this.cajaDTO = cajaDTO;
	}

	public UsuarioFirmadoService getUsuarioFirmadoService() {
		return usuarioFirmadoService;
	}

	public void setUsuarioFirmadoService(UsuarioFirmadoService usuarioFirmadoService) {
		this.usuarioFirmadoService = usuarioFirmadoService;
	}

	public PagoInfraccionMyBatisDAO getPagoInfraccionMyBatisDAO() {
		return pagoInfraccionMyBatisDAO;
	}

	public void setPagoInfraccionMyBatisDAO(PagoInfraccionMyBatisDAO pagoInfraccionMyBatisDAO) {
		this.pagoInfraccionMyBatisDAO = pagoInfraccionMyBatisDAO;
	};
	
	
	
}
