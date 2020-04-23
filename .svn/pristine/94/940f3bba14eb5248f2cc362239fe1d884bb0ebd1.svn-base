package mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo;

import java.text.ParseException;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionModificacionVO;
import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitTrBitacPInfDocs {
	
	List<BitacoraCambiosVO> guardarCambiosModifica(ParteInformativoCDocsVO oldParteInfVO, ParteInformativoCDocsVO newParteInfVO) throws ParseException;

	BitacoraCambiosVO guardarCambiosNoConsecutivo(ParteInformativoBoletaSancionModificacionVO parametrosVO,String originalNoConsecutivo);
	
	BitacoraCambiosVO guardarCambiosFechaSello(ParteInformativoBoletaSancionModificacionVO parametrosVO,String originalFechaSello);
	
	BitacoraCambiosVO guardarCambiosPlacaOficial(ParteInformativoBoletaSancionModificacionVO parametrosVO,String originalPlacaOficial);

	BitacoraCambiosVO guardarCambiosAreaOperativa(ParteInformativoBoletaSancionModificacionVO parametrosVO,String originalAreaOperativa);
	
	BitacoraCambiosVO guardarCambioSituacion(ParteInformativoBoletaSancionModificacionVO parametrosVO,String originalSituacion);
	
	BitacoraCambiosVO guardarCambiosAddInfraccs(ParteInformativoBoletaSancionModificacionVO parametrosVO,String numInfraccion);
	
	BitacoraCambiosVO guardarCambiosDeleteInfraccs(ParteInformativoBoletaSancionModificacionVO parametrosVO,String numInfraccion);


	
}
