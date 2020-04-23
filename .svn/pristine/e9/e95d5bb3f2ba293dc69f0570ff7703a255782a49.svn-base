package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.ModificaCajaUsuarioMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.ModificaUsuarioMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.mybatis.dao.administracion.UsuarioAdminEstatusMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.administracion.UsuarioAdminVO;
import mx.com.teclo.saicdmx.persistencia.vo.certificados.EmpleadoVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpAdminUsuarioModifImpl implements BitSpAdminUsuarioModif{
	private final Long componente = 2L;
	
	@Value("${app.config.codigo}")
	private String codeApplication;
	@Autowired
	private ModificaUsuarioMyBatisDAO modificaUsuarioMyBatisDAO;	
	@Autowired
	ModificaCajaUsuarioMyBatisDAO modificaCajaUsuarioMyBatisDAO;
	@Autowired
	UsuarioAdminEstatusMyBatisDAO usuarioAdminEstatusMyBatisDAO;
	
	@Override
	public List<BitacoraCambiosVO> bitacoraAdminModifUsuario(UsuarioAdminVO usuarioAdminVO, EmpleadoVO voEmplOld){
		List<BitacoraCambiosVO> bitCambios = new ArrayList<>();
		
		// En store procedure existe un bitacoreo de Nombre completo si este es cambiado, en algun dato //
		// pero nunca se ejecutara porque se realiza las comparaciones despues de ser actualizada y con datos nuevos... //
		
//		if(!usuarioAdminVO.getEmp_nombre().trim().equals(voEmplOld.getEmpNombre().trim())
//				|| !usuarioAdminVO.getEmp_ape_paterno().trim().equals(voEmplOld.getEmpApePaterno().trim())
//				|| !usuarioAdminVO.getEmp_ape_materno().trim().equals(voEmplOld.getEmpApeMaterno().trim())){
//			bitCambios.add(new BitacoraCambiosVO( // 147
//					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
//					componente,
//					3L,
//					usuarioAdminVO.getEmp_placa() + " - " + voEmplOld.getEmpNombre().trim() + " " + voEmplOld.getEmpApePaterno().trim() + " " + voEmplOld.getEmpApeMaterno().trim(),
//					usuarioAdminVO.getEmp_nombre().trim() + " " + usuarioAdminVO.getEmp_ape_paterno().trim() + " " + usuarioAdminVO.getEmp_ape_materno().trim(),
//					usuarioAdminVO.getModificadoPor(),
//					usuarioAdminVO.getEmp_id().toString(),
//					"",
//					ParametrosBitacoraEnum.ORIGEN_W.getParametro())
//			);
//		}

		boolean emplPuedeCobrar = usuarioAdminVO.getCobroNuevo().equals("S");
		if(usuarioAdminVO.getCaja_id() > 0){
//			String cajaAutorizadoCobro = modificaCajaUsuarioMyBatisDAO.getEstatusCajaAutCobro(usuarioAdminVO.getCaja_id());
			emplPuedeCobrar =  emplPuedeCobrar && usuarioAdminVO.getAutorizada_p_cobro().equals("1");
		}
		Integer derechoEmpleadoCobro = modificaCajaUsuarioMyBatisDAO.getDerechoCobro(usuarioAdminVO.getEmp_id());
		int existePerfilUsuario = modificaUsuarioMyBatisDAO.existeRegistroPerfilUsuario(usuarioAdminVO.getEmp_id(), codeApplication);// id codigo de app
		String nombreOficial = usuarioAdminEstatusMyBatisDAO.getNombreOficial(usuarioAdminVO.getEmp_id());
		
//		Long cajaExiste = usuarioAdminEstatusMyBatisDAO.getCajaExiste(usuarioAdminVO.getEmp_id());
		if(existePerfilUsuario != 0){  // 197
			
			
			if(!emplPuedeCobrar){ // 212 
				
				if(derechoEmpleadoCobro > 0){
					bitCambios.add(new BitacoraCambiosVO( //220
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
							componente,
							25L,
							"",
							usuarioAdminVO.getEmp_nombre().trim() + " " + usuarioAdminVO.getEmp_ape_paterno().trim() + " " + usuarioAdminVO.getEmp_ape_materno().trim(),
							usuarioAdminVO.getModificadoPor(),
							usuarioAdminVO.getEmp_id().toString(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));						
				}
				
			}else{
				
				if(derechoEmpleadoCobro == 0){
					bitCambios.add(new BitacoraCambiosVO(  //253
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
							componente,
							24L,
							"",
							usuarioAdminVO.getEmp_nombre().trim() + " " + usuarioAdminVO.getEmp_ape_paterno().trim() + " " + usuarioAdminVO.getEmp_ape_materno().trim(),
							usuarioAdminVO.getModificadoPor(),
							usuarioAdminVO.getEmp_id().toString(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));
				}	
			}
			
			if(usuarioAdminVO.getPerfilWebNuevo().equals("0")){ //272
				bitCambios.add(new BitacoraCambiosVO( //283
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						4L,
						modificaUsuarioMyBatisDAO.existeNombrePerfil(usuarioAdminVO.getPerfil_id()),
						"",
						usuarioAdminVO.getModificadoPor(),
						usuarioAdminVO.getEmp_id().toString(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
			}else if(!usuarioAdminVO.getPerfil_id().equals(usuarioAdminVO.getPerfilWebNuevo().toString())){ //301 && 331 aqui debe de ser diferente al cambiar perfiles
				
				bitCambios.add(new BitacoraCambiosVO( //309
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
							componente,
							4L,
							modificaUsuarioMyBatisDAO.existeNombrePerfil(usuarioAdminVO.getPerfil_id()),
							modificaUsuarioMyBatisDAO.existeNombrePerfil(usuarioAdminVO.getPerfilWebNuevo().toString()),
							usuarioAdminVO.getModificadoPor(),
							usuarioAdminVO.getEmp_id().toString(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));
				
				if(usuarioAdminVO.getCaja_id() > 0){
						// Bitacora al desasociar usuario a la caja
						bitCambios.add(new BitacoraCambiosVO( // 368
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
							componente,
							16L,
							usuarioAdminVO.getCaja_cod(),
							nombreOficial,
							usuarioAdminVO.getModificadoPor(),
							usuarioAdminVO.getCaja_id().toString(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));
				}
				
			}
			
		}// end 197
		else if(existePerfilUsuario == 0){ // 399
			if(!usuarioAdminVO.getPerfilWebNuevo().equals("0")){//401
				bitCambios.add(new BitacoraCambiosVO( //408
						ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
						componente,
						4L,
						modificaUsuarioMyBatisDAO.existeNombrePerfil(usuarioAdminVO.getPerfil_id()),
						modificaUsuarioMyBatisDAO.existeNombrePerfil(usuarioAdminVO.getPerfilWebNuevo().toString()),
						usuarioAdminVO.getModificadoPor(),
						usuarioAdminVO.getEmp_id().toString(),
						"",
						ParametrosBitacoraEnum.ORIGEN_W.getParametro()
						));
				
				if(emplPuedeCobrar){ // 415
					
					if(derechoEmpleadoCobro == 0){ //420
						bitCambios.add(new BitacoraCambiosVO(  //436
								ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
								componente,
								24L,
								"",
								usuarioAdminVO.getEmp_nombre().trim() + " " + usuarioAdminVO.getEmp_ape_paterno().trim() + " " + usuarioAdminVO.getEmp_ape_materno().trim(),
								usuarioAdminVO.getModificadoPor(),
								usuarioAdminVO.getEmp_id().toString(),
								"",
								ParametrosBitacoraEnum.ORIGEN_W.getParametro()
								));
					}	
				} // end 415
					
				if(usuarioAdminVO.getCaja_id() > 0){ // 452
					bitCambios.add(new BitacoraCambiosVO( // 468
							ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
							componente,
							16L,
							usuarioAdminVO.getCaja_cod(),
							nombreOficial,
							usuarioAdminVO.getModificadoPor(),
							usuarioAdminVO.getCaja_id().toString(),
							"",
							ParametrosBitacoraEnum.ORIGEN_W.getParametro()
							));
				}
			}//end 401
			
		}// end 399
		
		return bitCambios;
	}
}
