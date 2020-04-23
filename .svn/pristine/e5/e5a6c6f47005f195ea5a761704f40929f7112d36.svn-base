package mx.com.teclo.saicdmx.bitacora.cambios.infraccionesRadar;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.radarCatalogo.RadarCatalogosMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.radares.RadarCatConsultaUTVO;
import mx.com.teclo.saicdmx.util.comun.BitComponente;
import mx.com.teclo.saicdmx.util.comun.ComparaObjetoUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpRadarCatUtMImpl implements BitSpRadarCatUtM{

	@Autowired
	RadarCatalogosMyBatisDAO RadarCatalogos;
	
	@Override
	public BitacoraCambiosVO guardarNewUTBit(RadarCatConsultaUTVO newUTVO) {
		
		String idArchivo = RadarCatalogos.getArchivoIdUT();
		//Concepto a aplicar para la bitacora
		final long componente = 24L;
		final long concepto = 1L;
		
		return new BitacoraCambiosVO(
				ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro(), 
				componente,
				concepto,
				"",
				"NUEVA UT",
				newUTVO.getCatUTModificadoPor(),
				idArchivo,
				"",
				ParametrosBitacoraEnum.ORIGEN_W.getParametro()
			);
	}

	@SuppressWarnings("finally")
	public List<BitacoraCambiosVO> guardarCambiosBitacoraUdateUT(RadarCatConsultaUTVO convertVOEdit,
			RadarCatConsultaUTVO convertVOBd) throws ParseException {
		
        final long componente = 24L;//COMPONENTEr para la bitácora
		
		List<BitComponente> filtro = new ArrayList<BitComponente>();
		
		filtro.add(new BitComponente(7L, "getCatUTEstatus"));//Filtro a aplicar a la bitácora
		filtro.add(new BitComponente(8L, "getCatUTCodigo"));
		filtro.add(new BitComponente(2L, "getCatUTCalle"));
		filtro.add(new BitComponente(3L, "getCatUTEntreCalle"));
		filtro.add(new BitComponente(4L, "getCatUTYCalle"));
		filtro.add(new BitComponente(9L, "getCatUTColonia"));
		filtro.add(new BitComponente(10L, "getCatUTSentido"));
		
		List<BitacoraCambiosVO> lista = new ArrayList<BitacoraCambiosVO>();
		
        List<Method> listaCambios = ComparaObjetoUtils.compararObjetos(convertVOEdit, convertVOBd, RadarCatConsultaUTVO.class, BitComponente.convertToList(filtro));
		
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
					convertVOEdit.getCatUTModificadoPor() != null ? convertVOEdit.getCatUTModificadoPor() : 0L,
				    convertVOEdit.getCatUTId().toString(),
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
