package mx.com.teclo.saicdmx.negocio.service.pagos;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.pagos.CentroPagosMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.CentroPagosVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.CentroPagosValidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.TotalesCentroPagosVO;
import mx.com.teclo.saicdmx.util.enums.pagos.EnumTipoPagoCtroPagosValida;

/**
 * Copyright (c) 2016, Teclo Mexicana. 
 * Descripción 					: CentroDePagosServiceImpl
 * Historial de Modificaciones 	: 
 * Descripcion del Cambio 		: Creacion
 * @author 						: fjmb
 * @version 					: 1.0 
 * Fecha 						: 01/Diciembre/2016
 */

@Service
public class CentroDePagosServiceImpl implements CentroDePagosService {

	private static final Logger logger = LoggerFactory.getLogger(CentroDePagosServiceImpl.class);

	
	@Autowired
	private CentroPagosMyBatisDAO centroPagosMyBatisDAO;
	private CentroPagosVO centroPagosVO;

	@Override
	public CentroPagosVO totalCentroPagosPorTipoYFecha(String fechaInicio, String fechaFin) {

		List<TotalesCentroPagosVO> totalFallasCentroDePago = centroPagosMyBatisDAO
				.totalCentroPagosPorTipoYFecha(fechaInicio, fechaFin);
		int sumaFallas = 0;
		centroPagosVO = new CentroPagosVO();
		for (TotalesCentroPagosVO totalesCentroPagosVO : totalFallasCentroDePago) {
			sumaFallas += totalesCentroPagosVO.getCantidad();
			if (totalesCentroPagosVO.getConceptoPago()
					.equals(EnumTipoPagoCtroPagosValida.WEB_COMPLETO.getTipoPago().toString()))
				centroPagosVO.setWebCompleto(totalesCentroPagosVO.getCantidad());

			if (totalesCentroPagosVO.getConceptoPago()
					.equals(EnumTipoPagoCtroPagosValida.WEB_INCOMPLETO.getTipoPago().toString()))
				centroPagosVO.setWebIncompleto(totalesCentroPagosVO.getCantidad());

			if (totalesCentroPagosVO.getConceptoPago()
					.equals(EnumTipoPagoCtroPagosValida.HH_COMPLETO.getTipoPago().toString()))
				centroPagosVO.setHhCompleto(totalesCentroPagosVO.getCantidad());

			if (totalesCentroPagosVO.getConceptoPago()
					.equals(EnumTipoPagoCtroPagosValida.HH_INCOMPLETO.getTipoPago().toString()))
				centroPagosVO.setHhIncompleto(totalesCentroPagosVO.getCantidad());

		}
		centroPagosVO.setTotal(sumaFallas);

		return centroPagosVO;
	}

	@Override
	public String ultimaConsultaCentroPagos() {
		return centroPagosMyBatisDAO.ultimaConsultaCentroPagos();
	}

	@Override
	public List<CentroPagosVO> consultaTotalesRangoFecha(String fechaInicio, String fechaFin) {
		return centroPagosMyBatisDAO.consultaTotalesRangoFecha(fechaInicio, fechaFin);
	}

	public List<CentroPagosValidaVO> getCtroPagosValidaInCompletosPorParametros(String fechaInicial, String fechaFinal,
			String tipoPago) {

		List<CentroPagosValidaVO> lista = null;
		if (!fechaInicial.isEmpty() && !fechaFinal.isEmpty()) {

			if (!tipoPago.isEmpty()) {
				lista = centroPagosMyBatisDAO.getAllCtroPagosValidaInCorrectosPorPeriodoTipo(fechaInicial, fechaFinal,
						tipoPago);
			} else {
				lista = centroPagosMyBatisDAO.getAllCtroPagosValidaInCorrectosPorPeriodo(fechaInicial, fechaFinal);
			}

		} else if (!tipoPago.isEmpty()) {
			lista = centroPagosMyBatisDAO.getAllCtroPagosValidaInCorrectosPorFechaTipoPago(fechaInicial, tipoPago);
		}

		return lista;
	}

	public List<CentroPagosValidaVO> getCtroPagosValidaCompletosPorParametros(String fechaInicial, String fechaFinal,
			String tipoPago) {

		List<CentroPagosValidaVO> lista = new ArrayList<CentroPagosValidaVO>();
		if (!fechaInicial.isEmpty() && !fechaFinal.isEmpty()) {

			if (!tipoPago.isEmpty()) {
				lista = centroPagosMyBatisDAO.getAllCtroPagosValidaCorrectosPorPeriodoTipoPago(fechaInicial, fechaFinal,
						tipoPago);
			} else {
				lista = centroPagosMyBatisDAO.getAllCtroPagosValidaCorrectosPorPeriodo(fechaInicial, fechaFinal);
			}

		} else if (!tipoPago.isEmpty()) {
			lista = centroPagosMyBatisDAO.getAllCtroPagosValidaCorrectosPorFechaTipoPago(fechaInicial, tipoPago);
		}

		return lista;
	}

}
