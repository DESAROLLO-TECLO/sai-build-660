package mx.com.teclo.saicdmx.bitacora.cambios.concesionaria;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.GruaConceVO;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitTrBitacConcesionariaImpl implements BitTrBitacConcesionaria {

	private ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs;
	@Override
	public ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(GruaConceVO newConcesionariaVO,
			GruaConceVO oldConcesionariaVO) {
		
		bitacoraCambiosVOs = new ArrayList<>();
		
		if(!ComparaUtils.comparaCadenas(oldConcesionariaVO.getConseCod(), newConcesionariaVO.getConseCod())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(15L);
			bitacoraCambiosVO.setConceptoId(2L);
			bitacoraCambiosVO.setValorOriginal(oldConcesionariaVO.getConseCod() != null ? oldConcesionariaVO.getConseCod() : "" );
			bitacoraCambiosVO.setValorFinal(newConcesionariaVO.getConseCod() != null ? newConcesionariaVO.getConseCod(): "");
			bitacoraCambiosVO.setCreadoPor(
					newConcesionariaVO.getModificadoPor() != 0L ? newConcesionariaVO.getModificadoPor()  : 0L );
			bitacoraCambiosVO.setIdRegistro(newConcesionariaVO.getConseId() != null ? newConcesionariaVO.getConseId().toString(): "");
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if(!ComparaUtils.comparaCadenas(oldConcesionariaVO.getConse_prefijo(), newConcesionariaVO.getConse_prefijo())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(15L);
			bitacoraCambiosVO.setConceptoId(3L);
			bitacoraCambiosVO.setValorOriginal(oldConcesionariaVO.getConse_prefijo() != null ? oldConcesionariaVO.getConse_prefijo() : "" );
			bitacoraCambiosVO.setValorFinal(newConcesionariaVO.getConse_prefijo() != null ? newConcesionariaVO.getConse_prefijo(): "");
			bitacoraCambiosVO.setCreadoPor(
					newConcesionariaVO.getModificadoPor() != 0L ? newConcesionariaVO.getModificadoPor()  : 0L );
			bitacoraCambiosVO.setIdRegistro(newConcesionariaVO.getConseId() != null ? newConcesionariaVO.getConseId().toString(): "");
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if(!ComparaUtils.comparaCadenas(oldConcesionariaVO.getConseArrastre(), newConcesionariaVO.getConseArrastre())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(15L);
			bitacoraCambiosVO.setConceptoId(4L);
			bitacoraCambiosVO.setValorOriginal(oldConcesionariaVO.getConseArrastre() != null ? oldConcesionariaVO.getConseArrastre().toString() : "" );
			bitacoraCambiosVO.setValorFinal(newConcesionariaVO.getConseArrastre() != null ? newConcesionariaVO.getConseArrastre().toString(): "");
			bitacoraCambiosVO.setCreadoPor(
					newConcesionariaVO.getModificadoPor() != 0L ? newConcesionariaVO.getModificadoPor()  : 0L );
			bitacoraCambiosVO.setIdRegistro(newConcesionariaVO.getConseId() != null ? newConcesionariaVO.getConseId().toString(): "");
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if(!ComparaUtils.comparaCadenas(oldConcesionariaVO.getConseNombre(), newConcesionariaVO.getConseNombre())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(15L);
			bitacoraCambiosVO.setConceptoId(5L);
			bitacoraCambiosVO.setValorOriginal(oldConcesionariaVO.getConseNombre() != null ? oldConcesionariaVO.getConseNombre().toString() : "" );
			bitacoraCambiosVO.setValorFinal(newConcesionariaVO.getConseNombre() != null ? newConcesionariaVO.getConseNombre().toString(): "");
			bitacoraCambiosVO.setCreadoPor(
					newConcesionariaVO.getModificadoPor() != 0L ? newConcesionariaVO.getModificadoPor()  : 0L );
			bitacoraCambiosVO.setIdRegistro(newConcesionariaVO.getConseId() != null ? newConcesionariaVO.getConseId().toString(): "");
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if(!ComparaUtils.comparaCadenas(oldConcesionariaVO.getConseStatus(), newConcesionariaVO.getConseStatus())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(15L);
			bitacoraCambiosVO.setConceptoId(6L);
			bitacoraCambiosVO.setValorOriginal(oldConcesionariaVO.getConseStatus() != null ? oldConcesionariaVO.getConseStatus().toString() : "" );
			bitacoraCambiosVO.setValorFinal(newConcesionariaVO.getConseStatus() != null ? newConcesionariaVO.getConseStatus().toString(): "");
			bitacoraCambiosVO.setCreadoPor(
					newConcesionariaVO.getModificadoPor() != 0L ? newConcesionariaVO.getModificadoPor()  : 0L );
			bitacoraCambiosVO.setIdRegistro(newConcesionariaVO.getConseId() != null ? newConcesionariaVO.getConseId().toString(): "");
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if( ((ComparaUtils.cadenaNvl(oldConcesionariaVO.getConse_prefijo()).length() == 0) &&
			(ComparaUtils.cadenaNvl(newConcesionariaVO.getConse_prefijo()).length() != 0)) 
				||
			((ComparaUtils.cadenaNvl(newConcesionariaVO.getConse_prefijo()).length() == 0) &&
			(ComparaUtils.cadenaNvl(oldConcesionariaVO.getConse_prefijo()).length() != 0))	
			) 
		{
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(15L);
			bitacoraCambiosVO.setConceptoId(2L);
			bitacoraCambiosVO.setValorOriginal(oldConcesionariaVO.getConseStatus() != null ? oldConcesionariaVO.getConseStatus().toString() : "" );
			bitacoraCambiosVO.setValorFinal(newConcesionariaVO.getConseStatus() != null ? newConcesionariaVO.getConseStatus().toString(): "");
			bitacoraCambiosVO.setCreadoPor(
					newConcesionariaVO.getModificadoPor() != 0L ? newConcesionariaVO.getModificadoPor()  : 0L );
			bitacoraCambiosVO.setIdRegistro(newConcesionariaVO.getConseId() != null ? newConcesionariaVO.getConseId().toString(): "");
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
//		if :old.CONSE_COD <> :new.CONSE_COD then
//	    insert into bitacora_cambios (componente_id, concepto_id, valor_original, valor_final,
//	        fecha_cambio, modificado_por, id_registro, origen)
//	     values (15, 2,:old.CONSE_COD, :new.CONSE_COD, sysdate, :new.modificado_por, :new.CONSE_ID, 'W');
//	  end if;
//	 if :old.CONSE_PREFIJO <> :new.CONSE_PREFIJO then
//	    insert into bitacora_cambios (componente_id, concepto_id, valor_original, valor_final,
//	        fecha_cambio, modificado_por, id_registro, origen)
//	     values (15, 3,:old.CONSE_PREFIJO, :new.CONSE_PREFIJO, sysdate, :new.modificado_por, :new.CONSE_ID, 'W');
//	  end if;
//	 if :old.CONSE_ARRASTRE <> :new.CONSE_ARRASTRE then
//	    insert into bitacora_cambios (componente_id, concepto_id, valor_original, valor_final,
//	        fecha_cambio, modificado_por, id_registro, origen)
//	     values (15, 4,:old.CONSE_ARRASTRE, :new.CONSE_ARRASTRE, sysdate, :new.modificado_por, :new.CONSE_ID, 'W');
//	  end if;
//	 if :old.CONSE_NOMBRE <> :new.CONSE_NOMBRE then
//	    insert into bitacora_cambios (componente_id, concepto_id, valor_original, valor_final,
//	        fecha_cambio, modificado_por, id_registro, origen)
//	     values (15, 5,:old.CONSE_NOMBRE, :new.CONSE_NOMBRE, sysdate, :new.modificado_por, :new.CONSE_ID, 'W');
//	  end if;
//	 if :old.CONSE_STATUS <> :new.CONSE_STATUS then
//	    insert into bitacora_cambios (componente_id, concepto_id, valor_original, valor_final,
//	        fecha_cambio, modificado_por, id_registro, origen)
//	     values (15, 6,:old.CONSE_STATUS, :new.CONSE_STATUS, sysdate, :new.modificado_por, :new.CONSE_ID, 'W');
//	  end if;
	  

//	 if (nvl(length(:old.CONSE_PREFIJO),0) = 0 AND nvl(length(:new.CONSE_PREFIJO),0) <> 0) or
//	 	(nvl(length(:new.CONSE_PREFIJO),0) = 0 AND nvl(length(:old.CONSE_PREFIJO),0) <> 0) then
//	    insert into bitacora_cambios (componente_id, concepto_id, valor_original, valor_final,
//	        fecha_cambio, modificado_por, id_registro, origen)
//	     values (15, 2,:old.CONSE_PREFIJO, :new.CONSE_PREFIJO, sysdate, :new.modificado_por,:new.CONSE_ID, 'W');
//	  end if;

		return bitacoraCambiosVOs;
	
	}

}
