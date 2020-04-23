package mx.com.teclo.saicdmx.negocio.service.pagos;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.TransaccionesCanceladasDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.pagos.TransaccionesValidacionDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos.TransaccionesCanceladasDTO;
import mx.com.teclo.saicdmx.persistencia.vo.pagos.TransaccionesCanceladasVO;
import mx.com.teclo.saicdmx.util.comun.ResponseConverter;
import mx.com.teclo.saicdmx.util.comun.RutinasTiempoImpl;

@Service
public class ConsultaTransccionCanceladaServiceImpl implements ConsultaTransccionCanceladaService{
	
	@Autowired
	private TransaccionesCanceladasDAO transaccionesCanceladasDAO;
	
	@Autowired
	private TransaccionesValidacionDAO transaccionValidacion;
	
	private RutinasTiempoImpl rutinasTiempo=new RutinasTiempoImpl();
	
	@Transactional
	public List<TransaccionesCanceladasVO> consultaTransaccionesCanceladas(){
		List<TransaccionesCanceladasDTO> listaCanceladasDTO=transaccionesCanceladasDAO.consultaAllTransaccionesCanceladas();
		transaccionesCanceladasDAO.clear();
		return getListaProcesadaFechaValidacion(listaCanceladasDTO);
	}
	
	@Transactional
	public List<TransaccionesCanceladasVO> consultaTransaccionesCanceladasInfraccion(String numInfraccion){
		List<TransaccionesCanceladasDTO> listaCanceladasDTO=transaccionesCanceladasDAO.consultaTransaccionesCanceladasInfraccion(numInfraccion);
		transaccionesCanceladasDAO.clear();
		return getListaProcesadaFechaValidacion(listaCanceladasDTO);
	}
	
	@Transactional
	public List<TransaccionesCanceladasVO> consultaTransaccionesCanceladasNumOperacion(String numOperacion){
		List<TransaccionesCanceladasDTO> listaCanceladasDTO=transaccionesCanceladasDAO.consultaTransaccionesCanceladasNumOperacion(numOperacion);
		transaccionesCanceladasDAO.clear();
		return getListaProcesadaFechaValidacion(listaCanceladasDTO);
	}
	
	@Transactional
	public List<TransaccionesCanceladasVO> consultaTransaccionesCanceladasReferencia(String numReferencia){
		List<TransaccionesCanceladasDTO> listaCanceladasDTO=transaccionesCanceladasDAO.consultaTransaccionesCanceladasReferencia(numReferencia);
		transaccionesCanceladasDAO.clear();
		return getListaProcesadaFechaValidacion(listaCanceladasDTO);
	}
	
	@Transactional
	public List<TransaccionesCanceladasVO> consultaTransaccionesCanceladasFechaInico(String fechaInicio){
		List<TransaccionesCanceladasDTO> listaCanceladasDTO=transaccionesCanceladasDAO.consultaTransaccionesCanceladasFechaInico(fechaInicio);
		transaccionesCanceladasDAO.clear();
		return getListaProcesadaFechaValidacion(listaCanceladasDTO);
	}
	
	@Transactional
	public List<TransaccionesCanceladasVO> consultaTransaccionesCanceladasFechasFin(String fechaFin){
		List<TransaccionesCanceladasDTO> listaCanceladasDTO=transaccionesCanceladasDAO.consultaTransaccionesCanceladasFechasFin(fechaFin);
		transaccionesCanceladasDAO.clear();
		return getListaProcesadaFechaValidacion(listaCanceladasDTO);
	}
	
	@Transactional
	public List<TransaccionesCanceladasVO> consultaTransaccionesCanceladasFechas(String fechaInicio,String fechaFin){
		List<TransaccionesCanceladasDTO> listaCanceladasDTO=transaccionesCanceladasDAO.consultaTransaccionesCanceladasFechas(fechaInicio, fechaFin);
		transaccionesCanceladasDAO.clear();
		return getListaProcesadaFechaValidacion(listaCanceladasDTO);
	}
	
	public List<TransaccionesCanceladasVO> getListaProcesadaFechaValidacion(List<TransaccionesCanceladasDTO> listaCanceladasDTO){
		List<TransaccionesCanceladasVO> listaCanceladasVO=new ArrayList<>();
		List<TransaccionesCanceladasVO> listaValidadaCanceladasVO=new ArrayList<>();
		if(!listaCanceladasDTO.isEmpty()){
			listaCanceladasVO=ResponseConverter.converterLista(new ArrayList<>(),listaCanceladasDTO,TransaccionesCanceladasVO.class);
		}
		
		for(TransaccionesCanceladasVO canceladasVO:listaCanceladasVO){
			String fechaValidacion=transaccionValidacion.fechaValidacion(canceladasVO.getTranId(), "CANCELACION", 0L);
			canceladasVO.setFechaValidacion(fechaValidacion==null ? "" : fechaValidacion);
			listaValidadaCanceladasVO.add(canceladasVO);
		}
		
		return listaValidadaCanceladasVO;
	}
}
