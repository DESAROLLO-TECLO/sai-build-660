package mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionModificacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitTrBitacPInfDocsImpl implements BitTrBitacPInfDocs {
	
	@SuppressWarnings("finally")
	@Override
	public List<BitacoraCambiosVO> guardarCambiosModifica(ParteInformativoCDocsVO oldParteInfVO, ParteInformativoCDocsVO newParteInfVO) throws ParseException{
		// TODO Auto-generated method stub
		
		//Concepto a aplicar para la bitacora
		final long concepto = 6L;
		
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		
		//Filtro a aplicar a la bitacora
		filtro.add(new BitComponente(7L, "getNoConsecutivo"));
		filtro.add(new BitComponente(8L, "getFecha"));
		filtro.add(new BitComponente(9L, "getOficialPlaca"));
		filtro.add(new BitComponente(10L, "getOficialAoper"));
		filtro.add(new BitComponente(11L, "getDocIfe"));
		filtro.add(new BitComponente(12L, "getDocTarjCirc"));
		filtro.add(new BitComponente(13L, "getDocLicencia"));
		filtro.add(new BitComponente(14L, "getDocVerific"));
		filtro.add(new BitComponente(15L, "getDocIfeNombre"));
		filtro.add(new BitComponente(16L, "getDocTarjCircNombre"));
		filtro.add(new BitComponente(17L, "getDocLicenciaNombre"));
		filtro.add(new BitComponente(18L, "getDocVerificNombre"));
		filtro.add(new BitComponente(19L, "getObservacion"));
		filtro.add(new BitComponente(20L, "getFechaEntrega"));
		filtro.add(new BitComponente(21L, "getDocOtros"));
		filtro.add(new BitComponente(22L, "getDocOtrosNombre"));
		
		List<BitacoraCambiosVO> lista = new ArrayList<BitacoraCambiosVO>();
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(newParteInfVO, oldParteInfVO, ParteInformativoCDocsVO.class, BitComponente.convertToList(filtro));
		
		try{
			for (Method m : listaCambios)
			{
				String oldVal = m.invoke(oldParteInfVO) != null ? m.invoke(oldParteInfVO).toString() : "";
				String newVal = m.invoke(newParteInfVO) != null ? m.invoke(newParteInfVO).toString() : ""; 
				
				lista.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					concepto,
					BitComponente.findByParametro(filtro, m.getName()).getComponente(),
					oldVal,
					newVal,
					newParteInfVO.getModificadoPor() != null ? newParteInfVO.getModificadoPor() : 0L,
					newParteInfVO.getIdPi().toString(),
					"",
					ParametrosBitacoraEnum.ORIGEN_W.getParametro()
				));
			}
		}catch(InvocationTargetException | IllegalAccessException e){
			e.printStackTrace();
		}finally{
			return lista;
		}
	}

	@Override
	public BitacoraCambiosVO guardarCambiosNoConsecutivo(ParteInformativoBoletaSancionModificacionVO parametrosVO,
			String originalNoConsecutivo) {
		
		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(6L); 
		c.setConceptoId(23L); 
		c.setValorOriginal(originalNoConsecutivo); 
		c.setValorFinal(parametrosVO.getNoConsecutivo());
		c.setCreadoPor(parametrosVO.getModificadoPor()); 
		c.setIdRegistro(parametrosVO.getId().toString()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
	}

	@Override
	public BitacoraCambiosVO guardarCambiosFechaSello(ParteInformativoBoletaSancionModificacionVO parametrosVO,
			String originalFechaSello) {
		
		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(6L); 
		c.setConceptoId(24L); 
		c.setValorOriginal(originalFechaSello); 
		c.setValorFinal(parametrosVO.getFecha());
		c.setCreadoPor(parametrosVO.getModificadoPor()); 
		c.setIdRegistro(parametrosVO.getId().toString()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
	}

	@Override
	public BitacoraCambiosVO guardarCambiosPlacaOficial(ParteInformativoBoletaSancionModificacionVO parametrosVO,
			String originalPlacaOficial) {

		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(6L); 
		c.setConceptoId(25L); 
		c.setValorOriginal(originalPlacaOficial); 
		c.setValorFinal(parametrosVO.getOficialPlaca());
		c.setCreadoPor(parametrosVO.getModificadoPor()); 
		c.setIdRegistro(parametrosVO.getId().toString()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
	}

	@Override
	public BitacoraCambiosVO guardarCambiosAreaOperativa(ParteInformativoBoletaSancionModificacionVO parametrosVO,
			String originalAreaOperativa) {
		
		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(6L); 
		c.setConceptoId(26L); 
		c.setValorOriginal(originalAreaOperativa); 
		c.setValorFinal(parametrosVO.getOficialAoper());
		c.setCreadoPor(parametrosVO.getModificadoPor()); 
		c.setIdRegistro(parametrosVO.getId().toString()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
		
	}

	@Override
	public BitacoraCambiosVO guardarCambioSituacion(ParteInformativoBoletaSancionModificacionVO parametrosVO,
			String originalSituacion) {
		
		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(6L); 
		c.setConceptoId(27L); 
		c.setValorOriginal(originalSituacion); 
		c.setValorFinal(parametrosVO.getSituacionSelect());
		c.setCreadoPor(parametrosVO.getModificadoPor()); 
		c.setIdRegistro(parametrosVO.getId().toString()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
	}

	@Override
	public BitacoraCambiosVO guardarCambiosAddInfraccs(ParteInformativoBoletaSancionModificacionVO parametrosVO,String numInfraccion) {
			
		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(6L); 
		c.setConceptoId(4L); 
		c.setValorOriginal(null); 
		c.setValorFinal(numInfraccion);
		c.setCreadoPor(parametrosVO.getModificadoPor()); 
		c.setIdRegistro(parametrosVO.getId().toString()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
	}

	@Override
	public BitacoraCambiosVO guardarCambiosDeleteInfraccs(
			ParteInformativoBoletaSancionModificacionVO parametrosVO,String numInfraccion) {

		BitacoraCambiosVO c = new BitacoraCambiosVO();
		c.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		c.setComponenteId(6L); 
		c.setConceptoId(6L); 
		c.setValorOriginal(numInfraccion); 
		c.setValorFinal(numInfraccion);
		c.setCreadoPor(parametrosVO.getModificadoPor()); 
		c.setIdRegistro(parametrosVO.getId().toString()); 
		c.setIdRegistroDescripcion(""); 
		c.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		return c;
		
	}

	
	
}
	

//class BitComponente{
//	private long componente;
//	private String parametro;
//	
//	public BitComponente(long componente, String parametro){
//		this.componente = componente;
//		this.parametro = parametro;
//	}
//	
//	public BitComponente(){
//		this(0, "");
//	}
//	
//	public long getComponente()
//	{return componente;}
//	
//	public String getParametro()
//	{return parametro;}
//	
//	static public List<String> convertToList(List<BitComponente> lista)
//	{
//		List<String> list = new ArrayList<String>();
//		for(BitComponente comp : lista)
//		{
//			list.add(comp.getParametro());
//		}
//		return list;
//	}
//	
//	static public BitComponente findByParametro(List<BitComponente> lista, String parametro)
//	{
//		for(BitComponente comp : lista)
//		{
//			if(comp.getParametro().equals(parametro))
//				return comp;
//		}
//		return null;
//	}
//}
