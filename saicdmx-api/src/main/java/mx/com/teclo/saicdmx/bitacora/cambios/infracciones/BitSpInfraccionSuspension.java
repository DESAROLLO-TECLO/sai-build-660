package mx.com.teclo.saicdmx.bitacora.cambios.infracciones;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.infracciones.SuspensionInfraccionSPVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpInfraccionSuspension {

	void bitSuspensionInfraccion(Long emp_id, SuspensionInfraccionSPVO suspensionInfraccionSPVO, ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs);

	void bitSuspensionInfraccionsp(Long emp_id, SuspensionInfraccionSPVO suspensionInfraccionSPVO);
}
