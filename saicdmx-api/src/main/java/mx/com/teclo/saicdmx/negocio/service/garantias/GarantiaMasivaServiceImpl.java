package mx.com.teclo.saicdmx.negocio.service.garantias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.negocio.service.comun.UsuarioFirmadoService;
import mx.com.teclo.saicdmx.negocio.service.empleado.EmpleadoService;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias.GarantiasEstatusProcesoDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dao.garantias.GarantiasMasivaDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.empleados.EmpleadosDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaEstatusProcesoDTO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaProcesoDTO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.FechasGarantiasVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.GarantiaIdMasivasVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsGralFVO;
import mx.com.teclo.saicdmx.util.enums.EnumGarantiasProceso;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.garantias.GarantiaMasivaMyBatisDAO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.UsuarioFirmadoVO;

@Service
public class GarantiaMasivaServiceImpl implements GarantiaMasivaService {
	
	@Autowired
	private GarantiaMasivaMyBatisDAO garantiaMasivaMBDAO;
	
	@Autowired
	private GarantiasMasivaDAO garantiasMasivaDAO;
	
	@Autowired
	private GarantiasEstatusProcesoDAO garantiaEstatusProcesoDAO;
	
	@Autowired
	private GarantiaService garantiaService;
	
	@Autowired
	private UsuarioFirmadoService usuarioFirmadoService;
	
	@Autowired
	private EmpleadoService empleadoService;
	
	
	@Override
	public List<VSSPGarantiaConsGralFVO> buscarGarantiasMasivaSinProcesar(String placaOficial, boolean op,
			Integer idTipoFecha) {
		List<VSSPGarantiaConsGralFVO> listaGarantiaMasivas = new ArrayList<VSSPGarantiaConsGralFVO>();
		FechasGarantiasVO fechasGarantias = new FechasGarantiasVO();
		String txtQuery = idTipoFecha!=null ? garantiaMasivaMBDAO.getQueryCalcularFechas(idTipoFecha): null;
		try {
			if (op) {//Opcion para buscar por todo
				
					if(txtQuery != null && !txtQuery.equals("")){
						//Opcion en caso de establecer un periodo predefinido, ejemplo: hoy,ayer
						fechasGarantias = garantiaMasivaMBDAO.fechasInicialFinal(txtQuery);
						listaGarantiaMasivas = garantiaMasivaMBDAO.buscarGarantiasSinProcesarWithFech(placaOficial,
								EnumGarantiasProceso.CREADA.getProcesoID(), fechasGarantias.getFechaInicial(),
								fechasGarantias.getFechaFinal());
					}else{
						//Opcion en caso de buscar SIN periodo
						listaGarantiaMasivas = garantiaMasivaMBDAO.buscarGarantiasSinProcesar(placaOficial,
								EnumGarantiasProceso.CREADA.getProcesoID());
					}
			} else {//Opcion para buscar SIN promesas de pago
				
				if(txtQuery != null && !txtQuery.equals("")){
					//Opcion en caso de establecer un periodo predefinido, ejemplo: hoy,ayer
					fechasGarantias = garantiaMasivaMBDAO.fechasInicialFinal(txtQuery);
					listaGarantiaMasivas = garantiaMasivaMBDAO.buscarGarantiasSinProcesarWithFechSP(placaOficial,
							EnumGarantiasProceso.CREADA.getProcesoID(), fechasGarantias.getFechaInicial(),
							fechasGarantias.getFechaFinal());
					}else{
						//Opcion en caso de buscar SIN periodo
					listaGarantiaMasivas = garantiaMasivaMBDAO.buscarGarantiasSinProcesarOp(placaOficial,
							EnumGarantiasProceso.CREADA.getProcesoID());
				}
			}

		} catch (Exception e) {
			listaGarantiaMasivas = null;
		}
		return listaGarantiaMasivas;
	}
	
	@Transactional
	@Override
	public GarantiaIdMasivasVO recepcionMasivaGarantias(GarantiaIdMasivasVO garantiaIdMasivasVO , GarantiaProcesoDTO proceso) {
		
		GarantiaDTO garantiaDTO = new GarantiaDTO();
		
			
		
		UsuarioFirmadoVO usuarioFirmadoVO = usuarioFirmadoService.getUsuarioFirmadoVO();
		EmpleadosDTO empFirmado = empleadoService.getEmpleadoById(usuarioFirmadoVO.getId());
		Long nextSequence= null;
		
		if(garantiaIdMasivasVO.getIdMasivas().size()==1){
			 nextSequence=(long) 0;
		}
		else{
			
			nextSequence=  garantiaMasivaMBDAO.buscarInfracGarantia();
		}
		garantiaIdMasivasVO.setIdLote(nextSequence);
		
		
		for(int i=0; i<garantiaIdMasivasVO.getIdMasivas().size(); i++){
			
			garantiaDTO = garantiaService.getGarantiaById(garantiaIdMasivasVO.getIdMasivas().get(i));
		
			GarantiaEstatusProcesoDTO procesoEstatus = new GarantiaEstatusProcesoDTO();

			procesoEstatus.setCreadoPor(empFirmado.getEmpId());
			procesoEstatus.setFechaCreacion(new Date());
			procesoEstatus.setGarantiaId(garantiaDTO.getGarantiaId());
			procesoEstatus.setProcesoId(EnumGarantiasProceso.REVISADA.getProcesoID());
			procesoEstatus.setObservaciones(garantiaIdMasivasVO.getObservaciones());
			procesoEstatus.setModificadoPor(empFirmado.getEmpId());
			procesoEstatus.setUltimaModificacion(new Date());
			garantiaEstatusProcesoDAO.save(procesoEstatus);
			
			
			
			garantiaDTO.setModificadoPor(empFirmado.getEmpId());
			garantiaDTO.setGarantiaProcesoDTO(proceso);
			garantiaDTO.setRecibida(garantiaIdMasivasVO.getRecibir());
			garantiaDTO.setUltimaModificacion(new Date());
			garantiaDTO.setIdLote(nextSequence);
			garantiasMasivaDAO.update(garantiaDTO);
			
		}
		
		return garantiaIdMasivasVO;
	}
	@Override
	public List<VSSPGarantiaConsGralFVO> buscarGarantiasMasivaFech(String placaOficial, boolean op, String starDate,
			String endDate) {
		// TODO Auto-generated method stub

		List<VSSPGarantiaConsGralFVO> listaGarantiasFech = new ArrayList<VSSPGarantiaConsGralFVO>();
		
		try {
			if(op){//Opcion para buscar por todo
				listaGarantiasFech=garantiaMasivaMBDAO.buscarGarantiaSinProcesarFech(placaOficial, 
						EnumGarantiasProceso.CREADA.getProcesoID(), starDate, endDate);
			}else{ //Opcion para buscar SIN promesas de pago
				listaGarantiasFech=garantiaMasivaMBDAO.buscarGarantiaSinProcesarFechSP(placaOficial, 
						EnumGarantiasProceso.CREADA.getProcesoID(), starDate, endDate);
			}
			
		} catch (Exception e) {

			listaGarantiasFech = null;
			
		}
		
		
		return listaGarantiasFech;
	}
	


}
