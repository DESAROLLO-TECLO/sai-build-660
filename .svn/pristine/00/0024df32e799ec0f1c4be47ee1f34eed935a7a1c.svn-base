package mx.com.teclo.saicdmx.bitacora.cambios.impugnacion;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionParametrosVO;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitTrBitacImpugnaImpl implements BitTrBitacImpugna {
	
	public ArrayList<BitacoraCambiosVO> compararInfracImpugnacion (ImpugnacionParametrosVO newImpugnacionVO,ImpugnacionParametrosVO oldImpugnacionVO){
		ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<>();
		//insert into bitacora_cambios (componente_id, concepto_id, valor_original, valor_final,
		//fecha_cambio, modificado_por, id_registro, origen)
		//values (7, 4,:old.impugnacion_oficio_jur, :new.impugnacion_oficio_jur, sysdate,
		//:new.modificado_por, :new.impugnacion_id, 'W');
		if(!ComparaUtils.comparaCadenas(oldImpugnacionVO.getImpugnacionOficioJur(), newImpugnacionVO.getImpugnacionOficioJur())){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(7L);
			bitacoraCambiosVO.setConceptoId(4L);
			bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldImpugnacionVO.getImpugnacionOficioJur()));
			bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newImpugnacionVO.getImpugnacionOficioJur()));
			//bitacoraCambiosVO.setFechaCreacion(new Date());
			bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadorPor());
			bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getImpugnacionId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		//if :old.impugnacion_juicio <> :new.impugnacion_juicio then
		//insert into bitacora_cambios (componente_id, concepto_id, valor_original, valor_final,
		//fecha_cambio, modificado_por, id_registro, origen)
		//values (7, 5,:old.impugnacion_juicio, :new.impugnacion_juicio, sysdate, :new.modificado_por, :new.impugnacion_id, 'W');
		//end if;
		if(!ComparaUtils.comparaCadenas(oldImpugnacionVO.getImpugnacionJuicio(), newImpugnacionVO.getImpugnacionJuicio())){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(7L);
			bitacoraCambiosVO.setConceptoId(5L);
			bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldImpugnacionVO.getImpugnacionJuicio()));
			bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newImpugnacionVO.getImpugnacionJuicio()));
			//bitacoraCambiosVO.setFechaCreacion(new Date());
			bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadorPor());
			bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getImpugnacionId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		//if :old.actor_ape_paterno <> :new.actor_ape_paterno  then
		//insert into bitacora_cambios (componente_id, concepto_id, valor_original, valor_final,
		//fecha_cambio, modificado_por, id_registro, origen)
		//values (7, 6,:old.actor_ape_paterno, :new.actor_ape_paterno, sysdate, :new.modificado_por,:new.impugnacion_id, 'W');
		//end if;
		if(!ComparaUtils.comparaCadenas(oldImpugnacionVO.getActorApellidoPaterno(), newImpugnacionVO.getActorApellidoPaterno())){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(7L);
			bitacoraCambiosVO.setConceptoId(6L);
			bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldImpugnacionVO.getActorApellidoPaterno()));
			bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newImpugnacionVO.getActorApellidoPaterno()));
			//bitacoraCambiosVO.setFechaCreacion(new Date());
			bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadorPor());
			bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getImpugnacionId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);	
		}
		if(!ComparaUtils.comparaCadenas(oldImpugnacionVO.getActorApellidoMaterno(), newImpugnacionVO.getActorApellidoMaterno())){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(7L);
			bitacoraCambiosVO.setConceptoId(7L);
			bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldImpugnacionVO.getActorApellidoMaterno()));
			bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newImpugnacionVO.getActorApellidoMaterno()));
			//bitacoraCambiosVO.setFechaCreacion(new Date());
			bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadorPor());
			bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getImpugnacionId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);			
		}
		if(!ComparaUtils.comparaCadenas(oldImpugnacionVO.getActorNombre(), newImpugnacionVO.getActorNombre())){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(7L);
			bitacoraCambiosVO.setConceptoId(8L);
			bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldImpugnacionVO.getActorNombre()));
			bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newImpugnacionVO.getActorNombre()));
			//bitacoraCambiosVO.setFechaCreacion(new Date());
			bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadorPor());
			bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getImpugnacionId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);			
		}
		if(!ComparaUtils.comparaCadenas(oldImpugnacionVO.getFechaRecepcion(), newImpugnacionVO.getFechaRecepcion())){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(7L);
			bitacoraCambiosVO.setConceptoId(9L);
			bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldImpugnacionVO.getFechaRecepcion()));
			bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newImpugnacionVO.getFechaRecepcion()));
			//bitacoraCambiosVO.setFechaCreacion(new Date());
			bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadorPor());
			bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getImpugnacionId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);			
		}
		if(!ComparaUtils.comparaCadenas(oldImpugnacionVO.getImpugnacionConstanciaCump(), newImpugnacionVO.getImpugnacionConstanciaCump())){
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(7L);
				bitacoraCambiosVO.setConceptoId(10L);
				bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldImpugnacionVO.getImpugnacionConstanciaCump()));
				bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newImpugnacionVO.getImpugnacionConstanciaCump()));
				//bitacoraCambiosVO.setFechaCreacion(new Date());
				bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadorPor());
				bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getImpugnacionId().toString());
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO);			
		}
		// Para los casos de que valor inicial o final sea null 
		if(ComparaUtils.cadenaNvl(oldImpugnacionVO.getActorApellidoPaterno())==null && ComparaUtils.cadenaNvl(newImpugnacionVO.getActorApellidoPaterno())!=null
		 || ComparaUtils.cadenaNvl(newImpugnacionVO.getActorApellidoPaterno())==null && ComparaUtils.cadenaNvl(oldImpugnacionVO.getActorApellidoPaterno())!=null){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(7L);
			bitacoraCambiosVO.setConceptoId(6L);
			bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldImpugnacionVO.getActorApellidoPaterno()));
			bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newImpugnacionVO.getActorApellidoPaterno()));
			//bitacoraCambiosVO.setFechaCreacion(new Date());
			bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadorPor());
			bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getImpugnacionId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);	
		}
		if(ComparaUtils.cadenaNvl(oldImpugnacionVO.getActorApellidoMaterno())==null && ComparaUtils.cadenaNvl(newImpugnacionVO.getActorApellidoMaterno())!=null
		 || ComparaUtils.cadenaNvl(newImpugnacionVO.getActorApellidoMaterno())==null && ComparaUtils.cadenaNvl(oldImpugnacionVO.getActorApellidoMaterno())!=null){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(7L);
			bitacoraCambiosVO.setConceptoId(7L);
			bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldImpugnacionVO.getActorApellidoMaterno()));
			bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newImpugnacionVO.getActorApellidoMaterno()));
			//bitacoraCambiosVO.setFechaCreacion(new Date());
			bitacoraCambiosVO.setCreadoPor(newImpugnacionVO.getModificadorPor());
			bitacoraCambiosVO.setIdRegistro(newImpugnacionVO.getImpugnacionId().toString());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);			
		}
			return bitacoraCambiosVOs;
	}
}
