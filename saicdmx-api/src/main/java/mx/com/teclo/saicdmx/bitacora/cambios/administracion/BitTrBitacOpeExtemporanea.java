package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.text.ParseException;
import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.administracion.OperacionExtDTO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitTrBitacOpeExtemporanea {
	
	ArrayList<BitacoraCambiosVO> obtenerParametrosBitacora(OperacionExtDTO newDTO, OperacionExtDTO oldDTO,Long idUsuario) throws ParseException;


}
