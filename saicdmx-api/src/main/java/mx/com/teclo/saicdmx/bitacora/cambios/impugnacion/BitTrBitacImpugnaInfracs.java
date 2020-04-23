package mx.com.teclo.saicdmx.bitacora.cambios.impugnacion;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.InfraccionImpugnacionInfracsVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitTrBitacImpugnaInfracs {
	
	public ArrayList<BitacoraCambiosVO> compararInfracImpugnacion (InfraccionImpugnacionInfracsVO newImpugnacionVO,InfraccionImpugnacionInfracsVO oldImpugnacionVO);
}
