package mx.com.teclo.saicdmx.bitacora.cambios.administracion;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.administracion.AdminDepositoVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpAdminUsuarioDeposito {

	List<BitacoraCambiosVO> insertBitacNuevoUsuario(AdminDepositoVO adminDepositoVO);

}
