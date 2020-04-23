package mx.com.teclo.saicdmx.bitacora.cambios.infraccionesDigitalizacion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.desencripta.BitDesencriptar;
import mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura.RespuestaWSReasignaLineaCapturaVO;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitTrBitUpInfracDigImpl implements BitTrBitUpInfracDig {

	private ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs;
	@Autowired
	private BitDesencriptar bitDescriptar;

	@Override
	public ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(RespuestaWSReasignaLineaCapturaVO newInfraccionVO,
			RespuestaWSReasignaLineaCapturaVO oldInfraccionVO) throws ParseException {
		bitacoraCambiosVOs = new ArrayList<>();

		if(oldInfraccionVO.getInfracRadarVO() != null){
			if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getVehiculoPlaca(), newInfraccionVO.getInfracRadarVO().getVehiculoPlaca())) {
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(5L);
				bitacoraCambiosVO.setConceptoId(2L);
				bitacoraCambiosVO.setValorOriginal(
						oldInfraccionVO.getInfracRadarVO().getVehiculoPlaca() != null ? oldInfraccionVO.getInfracRadarVO().getVehiculoPlaca() : "");
				bitacoraCambiosVO.setValorFinal(
						newInfraccionVO.getInfracRadarVO().getVehiculoPlaca() != null ? newInfraccionVO.getInfracRadarVO().getVehiculoPlaca() : "");
				bitacoraCambiosVO.setCreadoPor(
						newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
								: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO);
		   }
		

			if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getImpresa(), newInfraccionVO.getInfracRadarVO().getImpresa())) {
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(5L);
				bitacoraCambiosVO.setConceptoId(3L);
				bitacoraCambiosVO
						.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getImpresa() != null ? oldInfraccionVO.getInfracRadarVO().getImpresa() : "");
				bitacoraCambiosVO.setValorFinal(
						newInfraccionVO.getInfracRadarVO().getImpresa() != null ? newInfraccionVO.getInfracRadarVO().getImpresa() : "");
				bitacoraCambiosVO.setCreadoPor(
						newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
								: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO);
	
			}

			if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getArticuloId(), newInfraccionVO.getInfracRadarVO().getArticuloId())) {
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(5L);
				bitacoraCambiosVO.setConceptoId(4L);
				bitacoraCambiosVO
						.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getArticuloId() != null ? oldInfraccionVO.getInfracRadarVO().getArticuloId() : "");
				bitacoraCambiosVO.setValorFinal(
						newInfraccionVO.getInfracRadarVO().getArticuloId() != null ? newInfraccionVO.getInfracRadarVO().getArticuloId().toString() : "");
				bitacoraCambiosVO.setCreadoPor(
						newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
								: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO);
	
			}

		if (oldInfraccionVO.getInfracRadarVO().getFecha() != null || newInfraccionVO.getInfracRadarVO().getFecha() != null) {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			DateFormat DateTimeStampNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			Date dateOldFecha = formatter.parse(oldInfraccionVO.getInfracRadarVO().getFecha());
			Date date = formatter.parse(newInfraccionVO.getInfracRadarVO().getFecha());
			String dateNewFechaAuto = DateTimeStampNew.format(date);
			String dateOldFechaAuto = DateTimeStampNew.format(dateOldFecha);
			String dateOldFechaFormat = formatter.format(dateOldFecha);
			String dateNewFechaFormat = formatter.format(date);

			if (!ComparaUtils.comparaCadenas(dateNewFechaAuto, dateOldFechaAuto)) {
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(5L);
				bitacoraCambiosVO.setConceptoId(5L);
				bitacoraCambiosVO.setValorOriginal(dateOldFechaFormat);
				bitacoraCambiosVO.setValorFinal(dateNewFechaFormat);
				bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getUsuario() != null
						? Long.parseLong(newInfraccionVO.getUsuario())
						: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO);

			}
		}
	
		// -- para los casos de que valor inicial o final sea null
		// infracLicencia y tarjeta circulacion utilizan encripctacion
		// PKG_ENCRIPCION.ENCRIPTA()
		String infraccLicencia = oldInfraccionVO.getInfracRadarVO().getInfractorLicencia() == null ? ""
				: oldInfraccionVO.getInfracRadarVO().getInfractorLicencia();
		String infraccLicencianew = newInfraccionVO.getInfracRadarVO().getInfractorLicencia() == null ? ""
				: newInfraccionVO.getInfracRadarVO().getInfractorLicencia();
		String infracLicenciaNew = "";
		String infracLicenciaOld = "";
		if (infraccLicencianew != "") {
			infracLicenciaNew = bitDescriptar.getDesencriptarValue(infraccLicencianew);
			infracLicenciaOld = bitDescriptar.getDesencriptarValue(infraccLicencia);
			if (infracLicenciaOld.length() != infracLicenciaNew.length()) {
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(5L);
				bitacoraCambiosVO.setConceptoId(6L);
				bitacoraCambiosVO.setValorOriginal(infracLicenciaOld);
				bitacoraCambiosVO.setValorFinal(infracLicenciaNew);
				bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getUsuario() != null
						? Long.parseLong(newInfraccionVO.getUsuario())
						: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO);

			}
		}

		if ((infracLicenciaOld.length() == infracLicenciaNew.length())
				&& (!infracLicenciaOld.equals(infracLicenciaNew))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(6L);
			bitacoraCambiosVO.setValorOriginal(infracLicenciaOld);
			bitacoraCambiosVO.setValorFinal(infracLicenciaNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		String tarjetaCirculacionOld = oldInfraccionVO.getInfracRadarVO().getTarjetaCirculacion() == null ? ""
				: oldInfraccionVO.getInfracRadarVO().getTarjetaCirculacion();
		String tarjetaCirculacionNew = newInfraccionVO.getInfracRadarVO().getTarjetaCirculacion() == null ? ""
				: newInfraccionVO.getInfracRadarVO().getTarjetaCirculacion();
		String tarjetaCircNew = "", tarjetaCircOld = "";
		if (infraccLicencianew != "") {
			tarjetaCircNew = bitDescriptar.getDesencriptarValue(tarjetaCirculacionNew);
			tarjetaCircOld = bitDescriptar.getDesencriptarValue(tarjetaCirculacionOld);
			if (tarjetaCircOld.length() != tarjetaCircNew.length()) {
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(5L);
				bitacoraCambiosVO.setConceptoId(7L);
				bitacoraCambiosVO.setValorOriginal(tarjetaCircOld);
				bitacoraCambiosVO.setValorFinal(tarjetaCircNew);
				bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getUsuario() != null
						? Long.parseLong(newInfraccionVO.getUsuario())
						: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO);

			}
		}
		if ((tarjetaCircOld.length() == tarjetaCircNew.length()) && (!tarjetaCircOld.equals(tarjetaCircNew))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(7L);
			bitacoraCambiosVO.setValorOriginal(tarjetaCircOld);
			bitacoraCambiosVO.setValorFinal(tarjetaCircNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfracCalle(),
				newInfraccionVO.getInfracRadarVO().getInfracCalle())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(8L);
			bitacoraCambiosVO
					.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getInfracCalle() != null ? oldInfraccionVO.getInfracRadarVO().getInfracCalle() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfracCalle() != null ? newInfraccionVO.getInfracRadarVO().getInfracCalle()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		 }

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfracEntreCalle(),
				newInfraccionVO.getInfracRadarVO().getInfracEntreCalle())) {

			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(9L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfracEntreCalle() != null ? oldInfraccionVO.getInfracRadarVO().getInfracEntreCalle() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfracEntreCalle() != null ? newInfraccionVO.getInfracRadarVO().getInfracEntreCalle()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getVehiculoMarcaId(), newInfraccionVO.getInfracRadarVO().getVehiculoMarcaId())) {

			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(10L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getVehiculoMarcaId() != null ? oldInfraccionVO.getInfracRadarVO().getVehiculoMarcaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getVehiculoMarcaId() != null ? newInfraccionVO.getInfracRadarVO().getVehiculoMarcaId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getVehiculoModeloId(), newInfraccionVO.getInfracRadarVO().getVehiculoModeloId())) {

			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(11L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getVehiculoModeloId() != null ? oldInfraccionVO.getInfracRadarVO().getVehiculoModeloId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getVehiculoModeloId() != null ? newInfraccionVO.getInfracRadarVO().getVehiculoModeloId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getVehiculoColorId(), newInfraccionVO.getInfracRadarVO().getVehiculoColorId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(12L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getVehiculoColorId() != null ? oldInfraccionVO.getInfracRadarVO().getVehiculoColorId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getVehiculoColorId() != null ? newInfraccionVO.getInfracRadarVO().getVehiculoColorId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorNombre(),
				newInfraccionVO.getInfracRadarVO().getInfractorNombre())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(13L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfractorNombre() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorNombre()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfractorNombre() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorNombre()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfractorNombre(), newInfraccionVO.getInfracRadarVO().getInfractorNombre()))
				&& (ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorNombre(),
						newInfraccionVO.getInfracRadarVO().getInfractorNombre()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(13L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfractorNombre() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorNombre()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfractorNombre() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorNombre()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorApePaterno(),
				newInfraccionVO.getInfracRadarVO().getInfractorApePaterno())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(14L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfractorApePaterno() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorApePaterno()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfractorApePaterno() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorApePaterno()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}
		
		if ((!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfractorApePaterno(),
				newInfraccionVO.getInfracRadarVO().getInfractorApePaterno()))
				&& (ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorApePaterno(), newInfraccionVO.getInfracRadarVO().getInfractorApePaterno())
						)) {

			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(14L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfractorApePaterno() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorApePaterno()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfractorApePaterno() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorApePaterno()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorApeMaterno(),
				newInfraccionVO.getInfracRadarVO().getInfractorApeMaterno())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(15L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfractorApeMaterno() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorApeMaterno()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfractorApeMaterno() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorApeMaterno()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		  }

		if ((!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfractorApeMaterno(),
				newInfraccionVO.getInfracRadarVO().getInfractorApeMaterno()))
				&& (ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorApeMaterno(), newInfraccionVO.getInfracRadarVO().getInfractorApeMaterno()
						))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(15L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfractorApeMaterno() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorApeMaterno()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfractorApeMaterno() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorApeMaterno()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		// --**********************************************************************************************************************

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfracSecId(), newInfraccionVO.getInfracRadarVO().getInfracSecId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(20L);
			bitacoraCambiosVO
					.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getInfracSecId() != null ? oldInfraccionVO.getInfracRadarVO().getInfracSecId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfracSecId() != null ? newInfraccionVO.getInfracRadarVO().getInfracSecId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}
	    

		 if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfracUniterrId(), newInfraccionVO.getInfracRadarVO().getInfracUniterrId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(21L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfracUniterrId() != null ? oldInfraccionVO.getInfracRadarVO().getInfracUniterrId() : "");
			bitacoraCambiosVO
					.setValorFinal(newInfraccionVO.getInfracRadarVO().getInfracUniterrId() != null ? newInfraccionVO.getInfracRadarVO().getInfracUniterrId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		 }
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorCalle(),
				newInfraccionVO.getInfracRadarVO().getInfractorCalle())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(22L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getInfractorCalle() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorCalle())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfracRadarVO().getInfractorCalle() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorCalle())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorCalle(),
				newInfraccionVO.getInfracRadarVO().getInfractorCalle()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfractorCalle(),
						newInfraccionVO.getInfracRadarVO().getInfractorCalle()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(22L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getInfractorCalle() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorCalle())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfracRadarVO().getInfractorCalle() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorCalle())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		// tiene que encriptarse ambas old y new
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorNumExt(),
				newInfraccionVO.getInfracRadarVO().getInfractorNumExt())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(23L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getInfractorNumExt() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorNumExt())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfracRadarVO().getInfractorNumExt() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorNumExt())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorNumExt(),
				newInfraccionVO.getInfracRadarVO().getInfractorNumExt()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfractorNumExt(),
						newInfraccionVO.getInfracRadarVO().getInfractorNumExt()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(23L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getInfractorNumExt() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorNumExt())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfracRadarVO().getInfractorNumExt() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorNumExt())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorNumInt(),
				newInfraccionVO.getInfracRadarVO().getInfractorNumInt())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(24L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getInfractorNumInt() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorNumInt())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfracRadarVO().getInfractorNumInt() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorNumInt())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorNumInt(),
				newInfraccionVO.getInfracRadarVO().getInfractorNumInt()))
				&& (!ComparaUtils.comparaCadenas(newInfraccionVO.getInfracRadarVO().getInfractorNumInt(),
						oldInfraccionVO.getInfracRadarVO().getInfractorNumInt()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(24L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getInfractorNumInt() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorNumInt())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfracRadarVO().getInfractorNumInt() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorNumInt())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorColonia(),
				newInfraccionVO.getInfracRadarVO().getInfractorColonia())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(25L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getInfractorColonia() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorColonia())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfracRadarVO().getInfractorColonia() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorColonia())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if ((ComparaUtils.comparaCadenasLength(newInfraccionVO.getInfracRadarVO().getInfractorColonia(),
				oldInfraccionVO.getInfracRadarVO().getInfractorColonia()))
				&& (!ComparaUtils.comparaCadenas(newInfraccionVO.getInfracRadarVO().getInfractorColonia(),
						oldInfraccionVO.getInfracRadarVO().getInfractorColonia()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(25L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getInfractorColonia() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfracRadarVO().getInfractorColonia())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfracRadarVO().getInfractorColonia() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfracRadarVO().getInfractorColonia())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfractorEdoId(), newInfraccionVO.getInfracRadarVO().getInfractorEdoId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(26L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfractorEdoId() != null ? oldInfraccionVO.getInfracRadarVO().getInfractorEdoId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfractorEdoId() != null ? newInfraccionVO.getInfracRadarVO().getInfractorEdoId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfractorDelegId(),
				newInfraccionVO.getInfracRadarVO().getInfractorDelegId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(27L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfractorDelegId() != null ? oldInfraccionVO.getInfracRadarVO().getInfractorDelegId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfractorDelegId() != null ? newInfraccionVO.getInfracRadarVO().getInfractorDelegId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getPlacaExpedidaId(),
				(newInfraccionVO.getInfracRadarVO().getPlacaExpedidaId()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(28L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getPlacaExpedidaId() != null ? oldInfraccionVO.getInfracRadarVO().getPlacaExpedidaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getPlacaExpedidaId() != null ? newInfraccionVO.getInfracRadarVO().getPlacaExpedidaId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getPlacaExpedidaId(),
				(newInfraccionVO.getInfracRadarVO().getPlacaExpedidaId())))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getPlacaExpedidaId(),
						newInfraccionVO.getInfracRadarVO().getPlacaExpedidaId()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(28L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getPlacaExpedidaId() != null ? oldInfraccionVO.getInfracRadarVO().getPlacaExpedidaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getPlacaExpedidaId() != null ? newInfraccionVO.getInfracRadarVO().getPlacaExpedidaId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getLicenciaTipoId(), newInfraccionVO.getInfracRadarVO().getLicenciaTipoId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(29L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getLicenciaTipoId() != null ? oldInfraccionVO.getInfracRadarVO().getLicenciaTipoId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getLicenciaTipoId() != null ? newInfraccionVO.getInfracRadarVO().getLicenciaTipoId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getLicExpedidaId(),
				(newInfraccionVO.getInfracRadarVO().getLicExpedidaId()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(30L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getLicExpedidaId() != null ? oldInfraccionVO.getInfracRadarVO().getLicExpedidaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getLicExpedidaId() != null ? newInfraccionVO.getInfracRadarVO().getLicExpedidaId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getLicExpedidaId(),
				(newInfraccionVO.getInfracRadarVO().getLicExpedidaId())))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getLicExpedidaId(),
						newInfraccionVO.getInfracRadarVO().getLicExpedidaId()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(30L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getLicExpedidaId() != null ? oldInfraccionVO.getInfracRadarVO().getLicExpedidaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getLicExpedidaId() != null ? newInfraccionVO.getInfracRadarVO().getLicExpedidaId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorRfc(), newInfraccionVO.getInfracRadarVO().getInfractorRfc())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(31L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfractorRfc() != null ? oldInfraccionVO.getInfracRadarVO().getInfractorRfc() : "");
			bitacoraCambiosVO
					.setValorFinal(newInfraccionVO.getInfracRadarVO().getInfractorRfc() != null ? newInfraccionVO.getInfracRadarVO().getInfractorRfc() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfractorRfc(), newInfraccionVO.getInfracRadarVO().getInfractorRfc()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfractorRfc(),
						newInfraccionVO.getInfracRadarVO().getInfractorRfc()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(31L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfractorRfc() != null ? oldInfraccionVO.getInfracRadarVO().getInfractorRfc() : "");
			bitacoraCambiosVO
					.setValorFinal(newInfraccionVO.getInfracRadarVO().getInfractorRfc() != null ? newInfraccionVO.getInfracRadarVO().getInfractorRfc() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfracLServPublico(),
				newInfraccionVO.getInfracRadarVO().getInfracLServPublico())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(32L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfracLServPublico() != null ? oldInfraccionVO.getInfracRadarVO().getInfracLServPublico() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfracLServPublico() != null ? newInfraccionVO.getInfracRadarVO().getInfracLServPublico()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfracLServPublico(),
				newInfraccionVO.getInfracRadarVO().getInfracLServPublico()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfracLServPublico(),
						newInfraccionVO.getInfracRadarVO().getInfracLServPublico()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(32L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfracLServPublico() != null ? oldInfraccionVO.getInfracRadarVO().getInfracLServPublico() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfracLServPublico() != null ? newInfraccionVO.getInfracRadarVO().getInfracLServPublico()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getVehiculoOrigen(), newInfraccionVO.getInfracRadarVO().getVehiculoOrigen())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(33L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getVehiculoOrigen() != null ? oldInfraccionVO.getInfracRadarVO().getVehiculoOrigen() : "");
			bitacoraCambiosVO
					.setValorFinal(newInfraccionVO.getInfracRadarVO().getVehiculoOrigen() != null ? newInfraccionVO.getInfracRadarVO().getVehiculoOrigen() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getVehiculoOrigen(), newInfraccionVO.getInfracRadarVO().getVehiculoOrigen()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getVehiculoOrigen(),
						newInfraccionVO.getInfracRadarVO().getVehiculoOrigen()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(33L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getVehiculoOrigen() != null ? oldInfraccionVO.getInfracRadarVO().getVehiculoOrigen() : "");
			bitacoraCambiosVO
					.setValorFinal(newInfraccionVO.getInfracRadarVO().getVehiculoOrigen() != null ? newInfraccionVO.getInfracRadarVO().getVehiculoOrigen() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(newInfraccionVO.getInfracRadarVO().getInfracYCalle(),
				oldInfraccionVO.getInfracRadarVO().getInfracYCalle())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(34L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfracYCalle() != null ? oldInfraccionVO.getInfracRadarVO().getInfracYCalle() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfracYCalle() != null ? newInfraccionVO.getInfracRadarVO().getInfracYCalle()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}
		if ((ComparaUtils.comparaCadenasLength(newInfraccionVO.getInfracRadarVO().getInfracYCalle(),
				oldInfraccionVO.getInfracRadarVO().getInfracYCalle()))
				&& (!ComparaUtils.comparaCadenas(newInfraccionVO.getInfracRadarVO().getInfracYCalle(),
						oldInfraccionVO.getInfracRadarVO().getInfracYCalle()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(34L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfracYCalle() != null ? oldInfraccionVO.getInfracRadarVO().getInfracYCalle() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfracYCalle() != null ? newInfraccionVO.getInfracRadarVO().getInfracYCalle()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfracColonia(),
				newInfraccionVO.getInfracRadarVO().getInfracColonia())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(35L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfracColonia() != null ? oldInfraccionVO.getInfracRadarVO().getInfracColonia() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfracColonia() != null ? newInfraccionVO.getInfracRadarVO().getInfracColonia() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfracColonia(),
				newInfraccionVO.getInfracRadarVO().getInfracColonia()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfracColonia(),
						newInfraccionVO.getInfracRadarVO().getInfracColonia()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(35L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfracColonia() != null ? oldInfraccionVO.getInfracRadarVO().getInfracColonia() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfracColonia() != null ? newInfraccionVO.getInfracRadarVO().getInfracColonia() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfracDelegacionId(),
				newInfraccionVO.getInfracRadarVO().getInfracDelegacionId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(36L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfracDelegacionId() != null ? oldInfraccionVO.getInfracRadarVO().getInfracDelegacionId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfracDelegacionId() != null ? newInfraccionVO.getInfracRadarVO().getInfracDelegacionId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfracObservacion(),
				newInfraccionVO.getInfracRadarVO().getInfracObservacion())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(37L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfracObservacion() != null ? oldInfraccionVO.getInfracRadarVO().getInfracObservacion() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfracObservacion() != null ? newInfraccionVO.getInfracRadarVO().getInfracObservacion() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getInfracObservacion(),
				newInfraccionVO.getInfracRadarVO().getInfracObservacion()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfracObservacion(),
						newInfraccionVO.getInfracRadarVO().getInfracObservacion()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(37L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getInfracObservacion() != null ? oldInfraccionVO.getInfracRadarVO().getInfracObservacion() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getInfracObservacion() != null ? newInfraccionVO.getInfracRadarVO().getInfracObservacion() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		String infracLeyTransporteOld = oldInfraccionVO.getInfracRadarVO().getInfracLeyTransp() == null ? ""
				: oldInfraccionVO.getInfracRadarVO().getInfracLeyTransp();
		String infracLeyTransporteNew = newInfraccionVO.getInfracRadarVO().getInfracLeyTransp() == null ? ""
				: newInfraccionVO.getInfracRadarVO().getInfracLeyTransp();

		if (infracLeyTransporteNew.length() != infracLeyTransporteOld.length()) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(38L);
			bitacoraCambiosVO.setValorOriginal(infracLeyTransporteOld);
			bitacoraCambiosVO.setValorFinal(infracLeyTransporteNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}
		if ((infracLeyTransporteNew.length() == infracLeyTransporteOld.length())
				&& (!infracLeyTransporteNew.equals(infracLeyTransporteOld))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(38L);
			bitacoraCambiosVO.setValorOriginal(infracLeyTransporteOld);
			bitacoraCambiosVO.setValorFinal(infracLeyTransporteNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

	
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getResponsableVehId(), newInfraccionVO.getInfracRadarVO().getResponsableVehId())){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(39L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfracRadarVO().getResponsableVehId() != null ? oldInfraccionVO.getInfracRadarVO().getResponsableVehId() : "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfracRadarVO().getResponsableVehId() != null ? newInfraccionVO.getInfracRadarVO().getResponsableVehId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		// falta conseguir el id del deposito
		// String depIdOld = oldInfraccionVO.getInfracRadarVO().getDeposito() == null ? "" :
		// oldInfraccionVO.getInfracRadarVO().getDeposito();
		// String pDepIdnew = newInfraccionVO.getInfracRadarVO().getP_dep_id() == null ? ""
		// : newInfraccionVO.getInfracRadarVO().getP_dep_id().toString();
		//
		// if (!depIdOld.equals(pDepIdnew)) {
		// BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		// bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		// bitacoraCambiosVO.setComponenteId(5L);
		// bitacoraCambiosVO.setConceptoId(40L);
		// bitacoraCambiosVO.setValorOriginal(depIdOld);
		// bitacoraCambiosVO.setValorFinal(pDepIdnew);
		// bitacoraCambiosVO.setCreadoPor(
		// newInfraccionVO.getUsuario() != null ?
		// Long.parseLong(newInfraccionVO.getUsuario())
		// : 0L);
		// bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0,
		// 11));
		// bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		// bitacoraCambiosVOs.add(bitacoraCambiosVO);
		//
		// }

		// id deposito
		String vTipoOld = oldInfraccionVO.getInfracRadarVO().getTipoVehiculoId() == null ? "" : oldInfraccionVO.getInfracRadarVO().getTipoVehiculoId();
		String pvTipoId = newInfraccionVO.getInfracRadarVO().getTipoVehiculoId() == null ? "" : newInfraccionVO.getInfracRadarVO().getTipoVehiculoId().toString();
		if (!vTipoOld.equals(pvTipoId)) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(41L);
			bitacoraCambiosVO.setValorOriginal(vTipoOld);
			bitacoraCambiosVO.setValorFinal(pvTipoId);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		// -- para los casos de que valor inicial o final sea null
		String infracArrastreOld = oldInfraccionVO.getInfracRadarVO().getInfracArrastre() == null ? ""
				: oldInfraccionVO.getInfracRadarVO().getInfracArrastre();
		String infracArrastreNew = newInfraccionVO.getInfracRadarVO().getInfracArrastre() == null ? ""
				: newInfraccionVO.getInfracRadarVO().getInfracArrastre();
		if (infracArrastreOld.length() != infracArrastreNew.length()) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(42L);
			bitacoraCambiosVO.setValorOriginal(infracArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}
		if (!infracArrastreOld.equals(infracArrastreNew)) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(42L);
			bitacoraCambiosVO.setValorOriginal(infracArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((infracArrastreOld.length() == infracArrastreNew.length())
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfractorLicencia(),newInfraccionVO.getInfracRadarVO().getInfractorLicencia()))) {

			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(42L);
			bitacoraCambiosVO.setValorOriginal(infracArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		String infracTipoArrastreOld = oldInfraccionVO.getInfracRadarVO().getInfracTipoArrastre() == null ? ""
				: oldInfraccionVO.getInfracRadarVO().getInfracTipoArrastre();
		String infracTipoArrastreNew = newInfraccionVO.getInfracRadarVO().getInfracTipoArrastre() == null ? ""
				: newInfraccionVO.getInfracRadarVO().getInfracTipoArrastre();
		if (infracTipoArrastreOld.length() != infracTipoArrastreNew.length()) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(43L);
			bitacoraCambiosVO.setValorOriginal(infracTipoArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracTipoArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}
		if ((infracTipoArrastreOld.length() == infracTipoArrastreNew.length())
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getInfractorLicencia(),newInfraccionVO.getInfracRadarVO().getInfractorLicencia()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(43L);
			bitacoraCambiosVO.setValorOriginal(infracTipoArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracTipoArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if (!infracTipoArrastreOld.equals(infracTipoArrastreNew)) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(43L);
			bitacoraCambiosVO.setValorOriginal(infracTipoArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracTipoArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		// fecha autorizacion
		// SE NECESITA OBTENER EL registro perteneciente a fecha autorizacion y esta se
		// trae de un store procedure V_SSP_INFRAC_CONS_GRAL_F
		if (oldInfraccionVO.getInfracRadarVO().getFechaAutoriza() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat realsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			String fechaAutorizaOlds = oldInfraccionVO.getInfracRadarVO().getFechaAutoriza() == null ? ""
					: oldInfraccionVO.getInfracRadarVO().getFechaAutoriza().toString();
			String fechaAutorizaNew = realsdf.format(new Date());
			String dateFechaAutorizaNew = sdf.format(new Date());
			fechaAutorizaOlds = realsdf.format(oldInfraccionVO.getInfracRadarVO().getFechaAutoriza());
			String dateFechaAutorizaOld = sdf.format(oldInfraccionVO.getInfracRadarVO().getFechaAutoriza());

			if (!fechaAutorizaOlds.equals(fechaAutorizaNew)) {
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(5L);
				bitacoraCambiosVO.setConceptoId(45L);
				bitacoraCambiosVO.setValorOriginal(dateFechaAutorizaOld);
				bitacoraCambiosVO.setValorFinal(dateFechaAutorizaNew);
				bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getUsuario() != null
						? Long.parseLong(newInfraccionVO.getUsuario())
						: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO);

			}
		}

		// se debe obtener la observacion en VO
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getMotivoCambio(),
				newInfraccionVO.getInfracRadarVO().getMotivoCambio())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(46L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getMotivoCambio() != null ? oldInfraccionVO.getInfracRadarVO().getMotivoCambio() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getMotivoCambio() != null ? newInfraccionVO.getInfracRadarVO().getMotivoCambio() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracRadarVO().getMotivoCambio(), newInfraccionVO.getInfracRadarVO().getMotivoCambio()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracRadarVO().getMotivacion(),
						newInfraccionVO.getInfracRadarVO().getMotivoCambio()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(46L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracRadarVO().getMotivoCambio() != null ? oldInfraccionVO.getInfracRadarVO().getMotivoCambio() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracRadarVO().getMotivoCambio() != null ? newInfraccionVO.getInfracRadarVO().getMotivoCambio() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario())
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}
	  if (!ComparaUtils.comparaCadenas(newInfraccionVO.getInfracRadarVO().getInfracConDireccion(),oldInfraccionVO.getInfracRadarVO().getInfracConDireccion())) {
		  BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		  bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		  bitacoraCambiosVO.setComponenteId(5L);
		  bitacoraCambiosVO.setConceptoId(52L);
		  bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldInfraccionVO.getInfracRadarVO().getInfracConDireccion()));
		  bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newInfraccionVO.getInfracRadarVO().getInfracConDireccion()));
		  bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario()):0L);
		  bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
		  bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		  bitacoraCambiosVOs.add(bitacoraCambiosVO);
	  }
	  if (!ComparaUtils.comparaCadenas(newInfraccionVO.getInfracRadarVO().getInfracFrenteAlNum(),oldInfraccionVO.getInfracRadarVO().getInfracFrenteAlNum())) {
		  BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		  bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		  bitacoraCambiosVO.setComponenteId(5L);
		  bitacoraCambiosVO.setConceptoId(53L);
		  bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldInfraccionVO.getInfracRadarVO().getInfracFrenteAlNum()));
		  bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newInfraccionVO.getInfracRadarVO().getInfracFrenteAlNum()));
		  bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario()):0L);
		  bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
		  bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		  bitacoraCambiosVOs.add(bitacoraCambiosVO);
	  }
	  if (!ComparaUtils.comparaCadenas(newInfraccionVO.getInfracRadarVO().getInfracObserveQueDesc(),oldInfraccionVO.getInfracRadarVO().getInfracObserveQueDesc())) {
		  BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		  bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		  bitacoraCambiosVO.setComponenteId(5L);
		  bitacoraCambiosVO.setConceptoId(54L);
		  bitacoraCambiosVO.setValorOriginal(ComparaUtils.cadenaNvl(oldInfraccionVO.getInfracRadarVO().getInfracObserveQueDesc()));
		  bitacoraCambiosVO.setValorFinal(ComparaUtils.cadenaNvl(newInfraccionVO.getInfracRadarVO().getInfracObserveQueDesc()));
		  bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario()):0L);
		  bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
		  bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		  bitacoraCambiosVOs.add(bitacoraCambiosVO);
	  }
	}
		// -- autorizado por siempre se debe guardar; En el trigger de Digitalizacion compara el que modifica sea diferente al anterior 
 
	  //if (!ComparaUtils.comparaCadenas(newInfraccionVO.getUsuario(),oldInfraccionVO.getUsuario())) {
		  BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		  bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		  bitacoraCambiosVO.setComponenteId(5L);
		  bitacoraCambiosVO.setConceptoId(44L);
		  bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getAutorizaId() != null ? oldInfraccionVO.getAutorizaId() : "");
		  bitacoraCambiosVO.setValorFinal(newInfraccionVO.getAutorizaId() != null ? newInfraccionVO.getAutorizaId() : "");
		  bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getUsuario() != null ? Long.parseLong(newInfraccionVO.getUsuario()) : 0L);
		  bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().length()<11?newInfraccionVO.getInfracRadarVO().getNumeroControlInterno():newInfraccionVO.getInfracRadarVO().getNumeroControlInterno().substring(0, 11));
		  bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		  bitacoraCambiosVOs.add(bitacoraCambiosVO);
	  //}
	  
	  return bitacoraCambiosVOs;
	}
}