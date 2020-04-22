package mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoCDocsNuevoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpParteInformativoNuevo {
	List<BitacoraCambiosVO> guardarCambiosCrear(ParteInformativoCDocsNuevoVO newPICDNVO);
}
