package mx.com.teclo.saicdmx.bitacora.cambios.concesionaria;

import java.util.ArrayList;

import mx.com.teclo.saicdmx.persistencia.vo.remisionadeposito.GruaConceVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitTrBitacConcesionaria {

	ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(GruaConceVO newConcesionariaVO,
			GruaConceVO oldConcesionariaVO);

}
