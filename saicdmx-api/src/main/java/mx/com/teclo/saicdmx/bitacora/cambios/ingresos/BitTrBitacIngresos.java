package mx.com.teclo.saicdmx.bitacora.cambios.ingresos;

import java.text.ParseException;
import java.util.List;

import mx.com.teclo.saicdmx.persistencia.vo.ingresos.IngresosVO;
import mx.com.teclo.saicdmx.persistencia.vo.liberacionvehiculo.ProcesoDeSalidaVO;
import mx.com.teclo.saicdmx.persistencia.vo.salidas.GuardarSalidaVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitTrBitacIngresos {

	List<BitacoraCambiosVO> guardarCambiosModifica(ProcesoDeSalidaVO original, ProcesoDeSalidaVO salidaVO) throws ParseException;

	List<BitacoraCambiosVO> guardarNuevoSalidas(GuardarSalidaVO orginalVO, GuardarSalidaVO nuevoVO) throws ParseException;

	List<BitacoraCambiosVO> generarBitacoraIngresos(IngresosVO oldIngresos, IngresosVO newIngresos)
			throws ParseException;

}
