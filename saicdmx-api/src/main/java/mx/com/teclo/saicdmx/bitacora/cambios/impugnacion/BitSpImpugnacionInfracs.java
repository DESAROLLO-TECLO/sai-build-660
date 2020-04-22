package mx.com.teclo.saicdmx.bitacora.cambios.impugnacion;

import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionParametrosVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.InfraccionImpugnacionInfracsVO;

public interface BitSpImpugnacionInfracs {

	void updateImpugnacionSpInfrac(ImpugnacionParametrosVO impugnacionVO, InfraccionImpugnacionInfracsVO oldInfraccionImpugnacionInfracsVO);

}
