package mx.com.teclo.ms.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.ms.persistencia.dto.TipoFechasDTO;
import mx.com.teclo.ms.persistencia.hibernate.dao.CatalogosMsDAO;
import mx.com.teclo.ms.persistencia.mybatis.dao.CatalogoMsMyBatisDAO;
import mx.com.teclo.ms.persistencia.vo.FechasIniFinVO;
import mx.com.teclo.ms.persistencia.vo.TipoFechaVO;

@Service
public class CatalogoMsServiceImpl implements CatalogoMsService {

	@Autowired
	private CatalogosMsDAO catalogosDAO;
	
	@Autowired
	private CatalogoMsMyBatisDAO catalogoMyBatisDAO;

	@Override
	@Transactional
	public List<TipoFechaVO> getCatalogoTipoFechasAll() {
		List<TipoFechasDTO> listTipoFechasDTO = catalogosDAO.getCatalogoTipoFechasAll();
		List<TipoFechaVO> listTipoFechaVO = new ArrayList<TipoFechaVO>();
		FechasIniFinVO fechas = new FechasIniFinVO();
		for(TipoFechasDTO dto : listTipoFechasDTO) {
			TipoFechaVO tipoFechaVO = new TipoFechaVO();
			if(dto.getTxTipoFecha()!=null)
				fechas = catalogoMyBatisDAO.getFechaInicialFinal(dto.getTxTipoFecha());
			else
				fechas = new FechasIniFinVO();
			tipoFechaVO.setIdTipoFecha(dto.getIdTipoFecha());
			tipoFechaVO.setNbTipoFecha(dto.getNbTipoFecha());
			tipoFechaVO.setNuOrden(dto.getNuOrden());
			tipoFechaVO.setFechaInicial(fechas.getFechaInicial());
			tipoFechaVO.setFechaFin(fechas.getFechaFinal());
			listTipoFechaVO.add(tipoFechaVO);
		}
		return listTipoFechaVO;
	}

	@Transactional
	@Override
	public List<TipoFechaVO> getCatalogoTipoFechasOp(Long[] opciones) {
		List<TipoFechasDTO> listTipoFechasDTO = catalogosDAO.getCatalogoTipoFechasOpcion(opciones);
		List<TipoFechaVO> listTipoFechaVO = new ArrayList<TipoFechaVO>();
		FechasIniFinVO fechas = new FechasIniFinVO();
		for(TipoFechasDTO dto : listTipoFechasDTO) {
			TipoFechaVO tipoFechaVO = new TipoFechaVO();
			if(dto.getTxTipoFecha()!=null)
				fechas = catalogoMyBatisDAO.getFechaInicialFinal(dto.getTxTipoFecha());
			else
				fechas = new FechasIniFinVO();
			tipoFechaVO.setIdTipoFecha(dto.getIdTipoFecha());
			tipoFechaVO.setNbTipoFecha(dto.getNbTipoFecha());
			tipoFechaVO.setNuOrden(dto.getNuOrden());
			tipoFechaVO.setFechaFin(fechas.getFechaInicial());
			tipoFechaVO.setFechaFin(fechas.getFechaFinal());
			listTipoFechaVO.add(tipoFechaVO);
		}
		return listTipoFechaVO;
	}
}
