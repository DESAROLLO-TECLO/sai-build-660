package mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarCatalogo.RadarCatalogosMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarCatConsultaCPVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpRadarCatCrImpl implements BitSpRadarCatCr{
    
	@Autowired
	RadarCatalogosMyBatisDAO RadarCatalogos;
	
	@Override
	public BitacoraCambiosVO guardarNewCR(RadarCatConsultaCPVO newCRVO){
		
		String idArchivo = RadarCatalogos.getArchivoId();
		//Concepto a aplicar para la bitacora
		final long componente = 25L;
		final long concepto = 1L;
		
		return new BitacoraCambiosVO(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
				componente,
				concepto,
				"",
				"NUEVO CR",
				newCRVO.getCatCPModificadoPor(),
				idArchivo,
				"",
				ParametrosBitacoraEnum.ORIGEN_W.getParametro()
			);
	}
	
	@SuppressWarnings("finally")
	@Override
	public List<BitacoraCambiosVO> guardarCambiosBitacoraUdateCP(RadarCatConsultaCPVO convertVOEdit,
			RadarCatConsultaCPVO convertVOBd) throws ParseException{
		
		final long componente = 25L;//COMPONENTEr para la bitácora
		
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		
		filtro.add(new BitComponente(6L, "getCatCPEstatus"));//Filtro a aplicar a la bitácora
		filtro.add(new BitComponente(3L, "getCatCPAsentamiento"));
		filtro.add(new BitComponente(9L, "getCatCPAsentamientoCR"));
		filtro.add(new BitComponente(4L, "getCatCPAsentamientoId"));
		filtro.add(new BitComponente(7L, "getCatCPAsentamientoTipo"));
		filtro.add(new BitComponente(8L, "getCatCPAsentamientoTipoClave"));
		filtro.add(new BitComponente(10L, "getCatCPCiudad"));
		filtro.add(new BitComponente(5L, "getCatCPZona"));
		filtro.add(new BitComponente(11L, "getCatCPCiudadId"));
		filtro.add(new BitComponente(12L, "getCatCPEstado"));
		filtro.add(new BitComponente(13L, "getCatCPMunicipio"));
		
		List<BitacoraCambiosVO> lista = new ArrayList<BitacoraCambiosVO>();
		
		List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(convertVOEdit, convertVOBd, RadarCatConsultaCPVO.class, BitComponente.convertToList(filtro));
		
		try{
			for (Method m : listaCambios)
			{
				String oldVal = m.invoke(convertVOBd) != null ? m.invoke(convertVOBd).toString() : "";
				String newVal = m.invoke(convertVOEdit) != null ? m.invoke(convertVOEdit).toString() : ""; 
				
				lista.add(new BitacoraCambiosVO(
					ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
					componente,
					BitComponente.findByParametro(filtro, m.getName()).getComponente(),
					oldVal,
					newVal,
					convertVOEdit.getCatCPModificadoPor() != null ? convertVOEdit.getCatCPModificadoPor() : 0L,
				    convertVOEdit.getCatCPId().toString(),
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
	
}

