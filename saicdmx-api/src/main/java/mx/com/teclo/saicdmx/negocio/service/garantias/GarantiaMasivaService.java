package mx.com.teclo.saicdmx.negocio.service.garantias;

import java.util.List;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.garantias.GarantiaProcesoDTO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.GarantiaIdMasivasVO;
import mx.com.teclo.saicdmx.persistencia.vo.garantias.VSSPGarantiaConsGralFVO;

public interface GarantiaMasivaService {
	
	List<VSSPGarantiaConsGralFVO> buscarGarantiasMasivaSinProcesar(String placaOfical, boolean op, Integer idQuery);
	
	List<VSSPGarantiaConsGralFVO> buscarGarantiasMasivaFech(String placaOficial, boolean op, String starDate, String endDate);
	
	public GarantiaIdMasivasVO recepcionMasivaGarantias(GarantiaIdMasivasVO garantiaIdMasivasVO, GarantiaProcesoDTO proceso);

}
