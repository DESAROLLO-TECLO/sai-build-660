package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.text.ParseException;
import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.logs.WebLogsDTO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitTrBitacLogs {

	BitacoraCambiosVO insertBitacEstatusWebLogs(WebLogsDTO perfilLogDTO, Long usuarioId);
	ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(LogsVO oldLog, LogsVO newLog) throws ParseException;

}
