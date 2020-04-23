package mx.com.teclo.saicdmx.bitacora.cambios.impugnacion;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.persistencia.mybatis.dao.impugnacion.ImpugnacionMyBatisDAO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.ImpugnacionParametrosVO;
import mx.com.teclo.saicdmx.persistencia.vo.impugnacion.InfraccionImpugnacionInfracsVO;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;
import mx.com.teclomexicana.arquitectura.ortogonales.service.bitacora.BitacoraCambiosService;

@Service
public class BitSpImpugnacionInfracsImpl implements BitSpImpugnacionInfracs {
	@Autowired
	private ImpugnacionMyBatisDAO impugnacionMyBatisDAO;
	@Autowired
	private BitacoraCambiosService bitacoraCambiosService;
	@Autowired
	private BitTrBitacImpugnaInfracs bitTrBitacImpugnaInfracs;
	
		@Override
		public void updateImpugnacionSpInfrac(ImpugnacionParametrosVO impugnacionVO, InfraccionImpugnacionInfracsVO oldInfraccionImpugnacionInfracsVO) {
			InfraccionImpugnacionInfracsVO newInfraccionImpugnacionInfracsVO = new InfraccionImpugnacionInfracsVO();
			ImpugnacionParametrosVO newimpugnacionParametrosVO = impugnacionVO;
			
			ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs = new ArrayList<BitacoraCambiosVO>();
			
			if(impugnacionVO.getTipo().equals("1")&& !impugnacionVO.getMensaje().equals("La infracción ya existe")){
					BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
					bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
					bitacoraCambiosVO.setComponenteId(7L);
					bitacoraCambiosVO.setConceptoId(3L);
					bitacoraCambiosVO.setValorOriginal(newimpugnacionParametrosVO.getResultado());
					bitacoraCambiosVO.setValorFinal(newimpugnacionParametrosVO.getEstatus());
					bitacoraCambiosVO.setCreadoPor(newimpugnacionParametrosVO.getEmpleadoId());
					bitacoraCambiosVO.setIdRegistro(newimpugnacionParametrosVO.getReferencia());
					bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
					bitacoraCambiosVOs.add(bitacoraCambiosVO);
					bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
				}
				if(impugnacionVO.getTipo().equals("2") ){ 
					newInfraccionImpugnacionInfracsVO.setEstatus(newimpugnacionParametrosVO.getEstatus());
					newInfraccionImpugnacionInfracsVO.setModificadoPor(newimpugnacionParametrosVO.getEmpleadoId());
					newInfraccionImpugnacionInfracsVO.setInfracNum(newimpugnacionParametrosVO.getInfraccNum());
					oldInfraccionImpugnacionInfracsVO.setValido(null);
					bitacoraCambiosVOs.addAll(bitTrBitacImpugnaInfracs.compararInfracImpugnacion(newInfraccionImpugnacionInfracsVO, oldInfraccionImpugnacionInfracsVO));
					bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
				}
				if(impugnacionVO.getTipo().equals("3") ){ 
					newInfraccionImpugnacionInfracsVO.setValido(0);
					newInfraccionImpugnacionInfracsVO.setModificadoPor(newimpugnacionParametrosVO.getEmpleadoId());
					newInfraccionImpugnacionInfracsVO.setInfracNum(newimpugnacionParametrosVO.getInfraccNum());
					newInfraccionImpugnacionInfracsVO.setMotivoInvalido(newimpugnacionParametrosVO.getEstatus());
					oldInfraccionImpugnacionInfracsVO.setEstatus(null);
					bitacoraCambiosVOs.addAll(bitTrBitacImpugnaInfracs.compararInfracImpugnacion(newInfraccionImpugnacionInfracsVO, oldInfraccionImpugnacionInfracsVO));
					bitacoraCambiosService.guardarListaBitacoraCambios(bitacoraCambiosVOs);
				}
			
		}
}
