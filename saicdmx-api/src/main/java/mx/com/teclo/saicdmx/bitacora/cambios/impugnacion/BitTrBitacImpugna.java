package mx.com.teclo.saicdmx.bitacora.cambios.impugnacion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionParametrosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitTrBitacImpugna {

	ArrayList<BitacoraCambiosVO> compararInfracImpugnacion(ImpugnacionParametrosVO newImpugnacionVO,
			ImpugnacionParametrosVO oldImpugnacionVO);

}
