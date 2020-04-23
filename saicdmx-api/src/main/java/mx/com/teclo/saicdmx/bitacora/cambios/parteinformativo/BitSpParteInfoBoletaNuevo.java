package mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo;

import java.text.ParseException;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoBoletaSancionNuevaVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpParteInfoBoletaNuevo {

	public BitacoraCambiosVO cambiosBitacoraCrearBoleta(ParteInformativoBoletaSancionNuevaVO boletaVO) throws ParseException;;

}
