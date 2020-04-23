package mx.com.teclo.saicdmx.bitacora.cambios.parteinformativo;

import mx.com.teclo.saicdmx.persistencia.vo.parteinformativo.ParteInformativoInfracsVO;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

public interface BitSpParteInfoInfracs {
	
	BitacoraCambiosVO guardarCambiosAdicionInfrac(ParteInformativoInfracsVO newParteInfVO);
	
	BitacoraCambiosVO guardarCambiosRemoverInfrac(ParteInformativoInfracsVO oldParteInfVO);

	BitacoraCambiosVO guardarNuevaInfraccionBoletaSancion(ParteInformativoInfracsVO oldParteInfVO);
}
