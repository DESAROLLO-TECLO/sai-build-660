package mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo;

import java.text.ParseException;

import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionNuevaVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitSpParteInfoBoletaNuevoImpl implements BitSpParteInfoBoletaNuevo{

	@Override
	public BitacoraCambiosVO cambiosBitacoraCrearBoleta(ParteInformativoBoletaSancionNuevaVO boletaVO) throws ParseException{
		
		BitacoraCambiosVO bitCambio = new BitacoraCambiosVO();
		
		bitCambio.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitCambio.setComponenteId(6L); 
		bitCambio.setConceptoId(2L); 
		bitCambio.setValorOriginal(""); 
		bitCambio.setValorFinal(boletaVO.getNoConsecutivo());
		bitCambio.setCreadoPor(boletaVO.getCreadoPor()); 
		bitCambio.setIdRegistro(boletaVO.getId()); 
		bitCambio.setIdRegistroDescripcion(""); 
		bitCambio.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		
		return bitCambio;
	}
}
