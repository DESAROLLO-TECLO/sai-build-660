package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.text.ParseException;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpAdminLogs {

	List<BitacoraCambiosVO> bitSpAdminLogsMethod(LogsVO usuarioAdminVO, LogsVO oldLog) throws ParseException;

}
