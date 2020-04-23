package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.DepositoAdmin_MyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.ModificaCajaUsuarioMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.UsuarioAdminEstatusMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminCajaParametrosVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;


@Service
public class BitSpAdminUsuarioCajaImpl implements BitSpAdminUsuarioCaja {

	private final long componente = 2L;

	@Autowired
	private ModificaCajaUsuarioMyBatisDAO modificaCajaUsuarioMyBatisDAO;
	@Autowired
	private UsuarioAdminEstatusMyBatisDAO usuarioAdminEstatusMyBatisDAO;
	@Autowired
	private DepositoAdmin_MyBatisDAO depositoMyBatisDAO;
	
	
	@Override
	public List<BitacoraCambiosVO> insertCambioUsuarioCaja(AdminCajaParametrosVO adminCajaParametrosVO){
		
		String nombreOficial = usuarioAdminEstatusMyBatisDAO.getNombreOficial(adminCajaParametrosVO.getP_emp_id());
		Long cajaExiste = usuarioAdminEstatusMyBatisDAO.getCajaExiste(adminCajaParametrosVO.getP_emp_id());
	    
		List<BitacoraCambiosVO> listBit = new ArrayList<>();
		char estatus = (adminCajaParametrosVO.getActivaCaja()!=null)?adminCajaParametrosVO.getActivaCaja().charAt(0):(adminCajaParametrosVO.getAsignaCaja()!=null)?adminCajaParametrosVO.getAsignaCaja().charAt(0):' ';

		if(adminCajaParametrosVO.getP_emp_caja_id() > 0){
			// Bitacora al desasociar usuario a la caja
			listBit.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					16L,
					usuarioAdminEstatusMyBatisDAO.getCodCaja(cajaExiste),
					nombreOficial,
					adminCajaParametrosVO.getP_modificado_por(),
					adminCajaParametrosVO.getP_emp_caja_id().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
			
			// Asignación de caja ficticia al usuario si es de perfil HandHeld
			if(estatus == 'E' && adminCajaParametrosVO.getP_perfil_id() == 20L){
				// Generar caja_id y caja_cod de la caja ficticia ...
				String cajaCodCajaFicticia = modificaCajaUsuarioMyBatisDAO.getCajaCodCajaFicticia();
				String cajaIdCajaFicticia = modificaCajaUsuarioMyBatisDAO.getCajaIdCajaFicticia();
				
				//Bitacora al asignar usuario a una caja ficticia ... 
				listBit.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						15L,
						cajaCodCajaFicticia,
						nombreOficial,
						adminCajaParametrosVO.getP_modificado_por(),
						cajaIdCajaFicticia,
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
				listBit.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						12L,
						8L,
						"",
						adminCajaParametrosVO.getP_emp_id().toString(),
						adminCajaParametrosVO.getP_modificado_por(),
						cajaIdCajaFicticia,
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
		}
		
		if(estatus == 'A' || estatus == 'C'){
			
			if(adminCajaParametrosVO.getP_caja_emp_id() > 0){
				Long existecajaEmpl = usuarioAdminEstatusMyBatisDAO.getCajaExiste(adminCajaParametrosVO.getP_caja_emp_id());
				String nombreOficialCajaEmp = usuarioAdminEstatusMyBatisDAO.getNombreOficial(adminCajaParametrosVO.getP_caja_emp_id());
				// Bitacora al desasociar el usuario a la caja ... 
				listBit.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						16L,
						usuarioAdminEstatusMyBatisDAO.getCodCaja(existecajaEmpl),
						nombreOficialCajaEmp,
						adminCajaParametrosVO.getP_modificado_por(),
						adminCajaParametrosVO.getP_caja_id().toString(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));				
						
				// Bitacora al asociar usuario con perfil HH a una caja Ficticia ...
				if(adminCajaParametrosVO.getP_perfil_id() == 20L){
					String cajaCodCajaFicticia = modificaCajaUsuarioMyBatisDAO.getCajaCodCajaFicticia();
					String cajaIdCajaFicticia = modificaCajaUsuarioMyBatisDAO.getCajaIdCajaFicticia();
					
					//Bitacora al asignar usuario a una caja ficticia ... 
					listBit.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
							componente,
							15L,
							cajaCodCajaFicticia,
							nombreOficialCajaEmp,
							adminCajaParametrosVO.getP_modificado_por(),
							cajaIdCajaFicticia,
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));
					
					// genera bitacora al asociar caja ficticia al empleado ...
					listBit.add(new BitacoraCambiosVO(
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
							12L,
							8L,
							"",
							adminCajaParametrosVO.getP_caja_emp_id().toString(),
							adminCajaParametrosVO.getP_modificado_por(),
							cajaIdCajaFicticia,
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));
				}
			}
			
				
			listBit.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					15L,
					usuarioAdminEstatusMyBatisDAO.getCodCaja(adminCajaParametrosVO.getP_caja_id()),
					nombreOficial,
					adminCajaParametrosVO.getP_modificado_por(),
					adminCajaParametrosVO.getP_caja_id().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
					));
			
			
			// Asignar o denegar derechos de cobro ...
			if(adminCajaParametrosVO.getP_perfil_id() == 20L){
				String cajaAutorizadoCobro = modificaCajaUsuarioMyBatisDAO.getEstatusCajaAutCobro(adminCajaParametrosVO.getP_caja_id());
				Integer derechoEmpleadoCobro = modificaCajaUsuarioMyBatisDAO.getDerechoCobro(adminCajaParametrosVO.getP_emp_id());
				
				boolean emplPuedeCobrar = adminCajaParametrosVO.getP_emp_puede_cobrar().equals("S") && (cajaAutorizadoCobro != null && cajaAutorizadoCobro == "1");
				
				if(emplPuedeCobrar){
					
					if(derechoEmpleadoCobro == 0){
						// Bitacora si empleado puede cobrar y no tiene derechos de cobro se le asignan los derechos 
						listBit.add(new BitacoraCambiosVO(
								ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
								componente,
								24L,
								"",
								nombreOficial,
								adminCajaParametrosVO.getP_modificado_por(),
								adminCajaParametrosVO.getP_emp_id().toString(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
								));
					}
				}else{
					if(derechoEmpleadoCobro > 0){
						// Bitacora si empleado no puede cobrar y tiene derechos de cobro se le eliminan los derechos 
						listBit.add(new BitacoraCambiosVO(
								ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
								componente,
								25L,
								"",
								nombreOficial,
								adminCajaParametrosVO.getP_modificado_por(),
								adminCajaParametrosVO.getP_emp_id().toString(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
								));						
					}
				}
				
			}
			
			// Asignar deposito al usuario ...
			// Se le asigna deposito al usuario 
			String nomDepCaja = depositoMyBatisDAO.getDepNombreByCajaId(adminCajaParametrosVO.getP_caja_id());
			String nomDepEmp = depositoMyBatisDAO.getDepNombreOld(adminCajaParametrosVO.getP_emp_id());
			
			if(nomDepEmp != null){
				//Bitacora des-asignación de deposito a usuario ...
				listBit.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						23L,
						nombreOficial,
						nomDepEmp,
						adminCajaParametrosVO.getP_modificado_por(),
						adminCajaParametrosVO.getP_emp_id().toString(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			if(nomDepCaja != null){
				//Bitacora asignación de deposito a usuario ...
				listBit.add(new BitacoraCambiosVO(
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						22L,
						nombreOficial,
						nomDepCaja,
						adminCajaParametrosVO.getP_modificado_por(),
						adminCajaParametrosVO.getP_emp_id().toString(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}
			
			
		}
	    return listBit;
	}
		
}
