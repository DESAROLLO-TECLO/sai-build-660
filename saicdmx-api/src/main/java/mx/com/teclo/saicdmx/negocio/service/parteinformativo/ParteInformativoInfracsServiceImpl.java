package mx.com.teclo.saicdmx.negocio.service.parteinformativo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.parteinformativo.ParteInformativoBoletaInfracsDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.parteinformativo.ParteInformativoBoletaInfracsDTO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.parteinformativo.ParteInformativoInfracsMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsPorBolsVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsPorDocsVO;

@Service
public class ParteInformativoInfracsServiceImpl implements ParteInformativoInfracsService{

	/*@Autowired
	private ParteInformativoCDocsInfracsDAO parteInformativoCDocsInfracsDAO;*/
	
	@Autowired
	private ParteInformativoBoletaInfracsDAO parteInformativoBoletaInfracsDAO;
	
	@Autowired
	private ParteInformativoInfracsMyBatisDAO parteInformativoInfracsMyBatisDAO;
	
	/*@Override
	@Transactional(readOnly=true)
	public List<ParteInformativoCDocsInfracsDTO> buscarInfraccionesPorDocumento(Long documentoId) {
		return parteInformativoCDocsInfracsDAO.buscarInfraccionesPorDocumento(documentoId);
	}*/
	
	@Override
	@Transactional(readOnly=true)
	public List<ParteInformativoInfracsPorBolsVO> buscarInfraccionesPorBoleta(Long boletaId) {	
		List<ParteInformativoBoletaInfracsDTO> listaInfracsDTO = new ArrayList<ParteInformativoBoletaInfracsDTO>();
		List<ParteInformativoInfracsPorBolsVO> listaInfracsVO = new ArrayList<ParteInformativoInfracsPorBolsVO>();
		listaInfracsDTO=parteInformativoBoletaInfracsDAO.buscarInfraccionesPorBoleta(boletaId);
		for (ParteInformativoBoletaInfracsDTO p : listaInfracsDTO) {
			ParteInformativoInfracsPorBolsVO pVO= new ParteInformativoInfracsPorBolsVO();
			pVO.setInfracNum(p.getParteInformativoBoletaInfracsPK().getInfracNum());
			listaInfracsVO.add(pVO);
		}
		return listaInfracsVO;
	}

	@Override
	public List<ParteInformativoInfracsPorDocsVO> buscarInfraccionesPorDocumento(Long documentoId) {
		return parteInformativoInfracsMyBatisDAO.infraccionesPorDocumento(documentoId);
	}
}
