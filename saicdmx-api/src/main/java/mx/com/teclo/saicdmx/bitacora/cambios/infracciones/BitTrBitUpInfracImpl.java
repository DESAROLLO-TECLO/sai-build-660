package mx.com.teclo.saicdmx.bitacora.cambios.infracciones;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.teclo.saicdmx.bitacora.desencripta.BitDesencriptar;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.AltaInfraccionSPVO;
import mx.com.teclo.saicdmx.persistencia.vo.infracciones.VSSPInfracConsGralFVO;
import mx.com.teclo.saicdmx.util.comun.ComparaUtils;
import mx.com.teclo.saicdmx.util.enumerados.ParametrosBitacoraEnum;
import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.vo.BitacoraCambiosVO;

@Service
public class BitTrBitUpInfracImpl implements BitTrBitUpInfrac {
	
	private ArrayList<BitacoraCambiosVO> bitacoraCambiosVOs;
	@Autowired
	private BitDesencriptar bitDescriptar;

	@Override
	public ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(AltaInfraccionSPVO newInfraccionVO,
			VSSPInfracConsGralFVO oldInfraccionVO) throws ParseException {
		bitacoraCambiosVOs = new ArrayList<>();

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getVehiculoPlaca(), newInfraccionVO.getP_infrac_placa())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(2L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getVehiculoPlaca() != null ? oldInfraccionVO.getVehiculoPlaca() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_placa() != null ? newInfraccionVO.getP_infrac_placa() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getImpresa(), newInfraccionVO.getP_infrac_impresa())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(3L);
			bitacoraCambiosVO
					.setValorOriginal(oldInfraccionVO.getImpresa() != null ? oldInfraccionVO.getImpresa() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_impresa() != null ? newInfraccionVO.getP_infrac_impresa() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getArticuloId(), newInfraccionVO.getP_art_id())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(4L);
			bitacoraCambiosVO
					.setValorOriginal(oldInfraccionVO.getArticuloId() != null ? oldInfraccionVO.getArticuloId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_art_id() != null ? newInfraccionVO.getP_art_id().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (oldInfraccionVO.getFecha() != null && newInfraccionVO.getP_m_fecha() != null) {
			DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			DateFormat DateTimeStampNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			Date dateOldFecha = formatter.parse(oldInfraccionVO.getFecha());
			Date date = formatter.parse(newInfraccionVO.getP_m_fecha());
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
				bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getP_modificado_por() != null
						? newInfraccionVO.getP_modificado_por().longValue()
						: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO);

			}
		}

		// -- para los casos de que valor inicial o final sea null
		// infracLicencia y tarjeta circulacion utilizan encripctacion
		// PKG_ENCRIPCION.ENCRIPTA()
		String infraccLicencia = oldInfraccionVO.getInfractorLicencia() == null ? ""
				: oldInfraccionVO.getInfractorLicencia();
		String infraccLicencianew = newInfraccionVO.getP_infrac_licencia() == null ? ""
				: newInfraccionVO.getP_infrac_licencia();
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
				bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getP_modificado_por() != null
						? newInfraccionVO.getP_modificado_por().longValue()
						: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
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
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		String tarjetaCirculacionOld = oldInfraccionVO.getTarjetaCirculacion() == null ? ""
				: oldInfraccionVO.getTarjetaCirculacion();
		String tarjetaCirculacionNew = newInfraccionVO.getP_vtarjeta_circulacion() == null ? ""
				: newInfraccionVO.getP_vtarjeta_circulacion();
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
				bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getP_modificado_por() != null
						? newInfraccionVO.getP_modificado_por().longValue()
						: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
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
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracCalle(),
				newInfraccionVO.getP_infrac_m_en_la_calle())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(8L);
			bitacoraCambiosVO
					.setValorOriginal(oldInfraccionVO.getInfracCalle() != null ? oldInfraccionVO.getInfracCalle() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_m_en_la_calle() != null ? newInfraccionVO.getP_infrac_m_en_la_calle()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracEntreCalle(),
				newInfraccionVO.getP_infrac_m_entre_calle())) {

			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(9L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracEntreCalle() != null ? oldInfraccionVO.getInfracEntreCalle() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_m_entre_calle() != null ? newInfraccionVO.getP_infrac_m_entre_calle()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getVehiculoMarcaId(), newInfraccionVO.getP_vmar_id())) {

			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(10L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getVehiculoMarcaId() != null ? oldInfraccionVO.getVehiculoMarcaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_vmar_id() != null ? newInfraccionVO.getP_vmar_id().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getVehiculoModeloId(), newInfraccionVO.getP_vmod_id())) {

			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(11L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getVehiculoModeloId() != null ? oldInfraccionVO.getVehiculoModeloId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_vmod_id() != null ? newInfraccionVO.getP_vmod_id().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getVehiculoColorId(), newInfraccionVO.getP_vcolor_id())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(12L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getVehiculoColorId() != null ? oldInfraccionVO.getVehiculoColorId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_vcolor_id() != null ? newInfraccionVO.getP_vcolor_id().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorNombre(),
				newInfraccionVO.getP_infrac_i_nombre())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(13L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorNombre() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorNombre()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_i_nombre() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_nombre()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorNombre(), newInfraccionVO.getP_infrac_i_nombre()))
				&& (ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorNombre(),
						newInfraccionVO.getP_infrac_i_nombre()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(13L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorNombre() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorNombre()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_i_nombre() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_nombre()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorApePaterno(),
				newInfraccionVO.getP_infrac_i_paterno())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(14L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorApePaterno() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorApePaterno()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_i_paterno() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_paterno()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorApePaterno(),
				newInfraccionVO.getP_infrac_i_paterno()))
				&& (ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorApePaterno(), newInfraccionVO.getP_infrac_i_paterno())
						)) {

			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(14L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorApePaterno() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorApePaterno()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_i_paterno() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_paterno()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorApeMaterno(),
				newInfraccionVO.getP_infrac_i_materno())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(15L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorApeMaterno() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorApeMaterno()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_i_materno() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_materno()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorApeMaterno(),
				newInfraccionVO.getP_infrac_i_materno()))
				&& (ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorApeMaterno(), newInfraccionVO.getP_infrac_i_materno()
						))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(15L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorApeMaterno() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorApeMaterno()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_i_materno() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_materno()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		// --**********************************************************************************************************************

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracSecId(), newInfraccionVO.getP_sec_id())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(20L);
			bitacoraCambiosVO
					.setValorOriginal(oldInfraccionVO.getInfracSecId() != null ? oldInfraccionVO.getInfracSecId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_sec_id() != null ? newInfraccionVO.getP_sec_id().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracUniterrId(), newInfraccionVO.getP_ut_id())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(21L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracUniterrId() != null ? oldInfraccionVO.getInfracUniterrId() : "");
			bitacoraCambiosVO
					.setValorFinal(newInfraccionVO.getP_ut_id() != null ? newInfraccionVO.getP_ut_id().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorCalle(),
				newInfraccionVO.getP_infrac_i_calle())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(22L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorCalle() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorCalle())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getP_infrac_i_calle() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_calle())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorCalle(),
				newInfraccionVO.getP_infrac_i_calle()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorCalle(),
						newInfraccionVO.getP_infrac_i_calle()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(22L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorCalle() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorCalle())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getP_infrac_i_calle() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_calle())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		// tiene que encriptarse ambas old y new
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorNumExt(),
				newInfraccionVO.getP_infrac_i_num_ext())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(23L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorNumExt() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorNumExt())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getP_infrac_i_num_ext() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_num_ext())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorNumExt(),
				newInfraccionVO.getP_infrac_i_num_ext()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorNumExt(),
						newInfraccionVO.getP_infrac_i_num_ext()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(23L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorNumExt() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorNumExt())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getP_infrac_i_num_ext() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_num_ext())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorNumInt(),
				newInfraccionVO.getP_infrac_i_num_int())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(24L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorNumInt() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorNumInt())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getP_infrac_i_num_int() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_num_int())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorNumInt(),
				newInfraccionVO.getP_infrac_i_num_int()))
				&& (!ComparaUtils.comparaCadenas(newInfraccionVO.getP_infrac_i_num_int(),
						oldInfraccionVO.getInfractorNumInt()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(24L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorNumInt() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorNumInt())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getP_infrac_i_num_int() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_num_int())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorColonia(),
				newInfraccionVO.getP_infrac_i_colonia())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(25L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorColonia() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorColonia())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getP_infrac_i_colonia() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_colonia())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if ((ComparaUtils.comparaCadenasLength(newInfraccionVO.getP_infrac_i_colonia(),
				oldInfraccionVO.getInfractorColonia()))
				&& (!ComparaUtils.comparaCadenas(newInfraccionVO.getP_infrac_i_colonia(),
						oldInfraccionVO.getInfractorColonia()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(25L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorColonia() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorColonia())
					: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getP_infrac_i_colonia() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_i_colonia())
					: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorEdoId(), newInfraccionVO.getP_infrac_i_edo_id())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(26L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorEdoId() != null ? oldInfraccionVO.getInfractorEdoId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_i_edo_id() != null ? newInfraccionVO.getP_infrac_i_edo_id().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorDelegId(),
				newInfraccionVO.getP_infrac_i_del_id())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(27L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorDelegId() != null ? oldInfraccionVO.getInfractorDelegId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_i_del_id() != null ? newInfraccionVO.getP_infrac_i_del_id().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getPlacaExpedidaId(),
				ComparaUtils.LongNvl(newInfraccionVO.getP_infrac_placa_edo()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(28L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getPlacaExpedidaId() != null ? oldInfraccionVO.getPlacaExpedidaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_placa_edo() != null ? newInfraccionVO.getP_infrac_placa_edo().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getPlacaExpedidaId(),
				ComparaUtils.LongNvl(newInfraccionVO.getP_infrac_placa_edo())))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getPlacaExpedidaId(),
						newInfraccionVO.getP_infrac_placa_edo()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(28L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getPlacaExpedidaId() != null ? oldInfraccionVO.getPlacaExpedidaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_placa_edo() != null ? newInfraccionVO.getP_infrac_placa_edo().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getLicenciaTipoId(), newInfraccionVO.getP_tipo_l_id())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(29L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getLicenciaTipoId() != null ? oldInfraccionVO.getLicenciaTipoId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_tipo_l_id() != null ? newInfraccionVO.getP_tipo_l_id().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getLicExpedidaId(),
				ComparaUtils.LongNvl(newInfraccionVO.getP_infrac_lic_edo()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(30L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getLicExpedidaId() != null ? oldInfraccionVO.getLicExpedidaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_lic_edo() != null ? newInfraccionVO.getP_infrac_lic_edo().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getLicExpedidaId(),
				ComparaUtils.LongNvl(newInfraccionVO.getP_infrac_lic_edo())))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getLicExpedidaId(),
						newInfraccionVO.getP_infrac_lic_edo()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(30L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getLicExpedidaId() != null ? oldInfraccionVO.getLicExpedidaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_lic_edo() != null ? newInfraccionVO.getP_infrac_lic_edo().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorRfc(), newInfraccionVO.getP_infrac_rfc())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(31L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorRfc() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorRfc()) : "");
			bitacoraCambiosVO
					.setValorFinal(newInfraccionVO.getP_infrac_rfc() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_rfc()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorRfc(), newInfraccionVO.getP_infrac_rfc()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorRfc(),
						newInfraccionVO.getP_infrac_rfc()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(31L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorRfc() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorRfc()) : "");
			bitacoraCambiosVO
					.setValorFinal(newInfraccionVO.getP_infrac_rfc() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getP_infrac_rfc()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracLServPublico(),
				newInfraccionVO.getP_infrac_l_serv_publico())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(32L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracLServPublico() != null ? oldInfraccionVO.getInfracLServPublico() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_l_serv_publico() != null ? newInfraccionVO.getP_infrac_l_serv_publico()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracLServPublico(),
				newInfraccionVO.getP_infrac_l_serv_publico()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracLServPublico(),
						newInfraccionVO.getP_infrac_l_serv_publico()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(32L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracLServPublico() != null ? oldInfraccionVO.getInfracLServPublico() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_l_serv_publico() != null ? newInfraccionVO.getP_infrac_l_serv_publico()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getVehiculoOrigen(), newInfraccionVO.getP_vorigen())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(33L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getVehiculoOrigen() != null ? oldInfraccionVO.getVehiculoOrigen() : "");
			bitacoraCambiosVO
					.setValorFinal(newInfraccionVO.getP_vorigen() != null ? newInfraccionVO.getP_vorigen() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getVehiculoOrigen(), newInfraccionVO.getP_vorigen()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getVehiculoOrigen(),
						newInfraccionVO.getP_vorigen()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(33L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getVehiculoOrigen() != null ? oldInfraccionVO.getVehiculoOrigen() : "");
			bitacoraCambiosVO
					.setValorFinal(newInfraccionVO.getP_vorigen() != null ? newInfraccionVO.getP_vorigen() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(newInfraccionVO.getP_infrac_m_y_la_calle(),
				oldInfraccionVO.getInfracYCalle())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(34L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracYCalle() != null ? oldInfraccionVO.getInfracYCalle() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_m_y_la_calle() != null ? newInfraccionVO.getP_infrac_m_y_la_calle()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}
		if ((ComparaUtils.comparaCadenasLength(newInfraccionVO.getP_infrac_m_y_la_calle(),
				oldInfraccionVO.getInfracYCalle()))
				&& (!ComparaUtils.comparaCadenas(newInfraccionVO.getP_infrac_m_y_la_calle(),
						oldInfraccionVO.getInfracYCalle()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(34L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracYCalle() != null ? oldInfraccionVO.getInfracYCalle() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_m_y_la_calle() != null ? newInfraccionVO.getP_infrac_m_y_la_calle()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracColonia(),
				newInfraccionVO.getP_infrac_m_colonia())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(35L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracColonia() != null ? oldInfraccionVO.getInfracColonia() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_m_colonia() != null ? newInfraccionVO.getP_infrac_m_colonia() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracColonia(),
				newInfraccionVO.getP_infrac_m_colonia()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracColonia(),
						newInfraccionVO.getP_infrac_m_colonia()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(35L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracColonia() != null ? oldInfraccionVO.getInfracColonia() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_m_colonia() != null ? newInfraccionVO.getP_infrac_m_colonia() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracDelegacionId(),
				newInfraccionVO.getP_infrac_m_del_id())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(36L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracDelegacionId() != null ? oldInfraccionVO.getInfracDelegacionId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_m_del_id() != null ? newInfraccionVO.getP_infrac_m_del_id().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracObservacion(),
				newInfraccionVO.getP_infrac_observacion())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(37L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracObservacion() != null ? oldInfraccionVO.getInfracObservacion() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_observacion() != null ? newInfraccionVO.getP_infrac_observacion() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracObservacion(),
				newInfraccionVO.getP_infrac_observacion()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracObservacion(),
						newInfraccionVO.getP_infrac_observacion()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(37L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracObservacion() != null ? oldInfraccionVO.getInfracObservacion() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_observacion() != null ? newInfraccionVO.getP_infrac_observacion() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		String infracLeyTransporteOld = oldInfraccionVO.getInfracLeyTransp() == null ? ""
				: oldInfraccionVO.getInfracLeyTransp();
		String infracLeyTransporteNew = newInfraccionVO.getP_infrac_ley_transporte() == null ? ""
				: newInfraccionVO.getP_infrac_ley_transporte();

		if (infracLeyTransporteNew.length() != infracLeyTransporteOld.length()) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(38L);
			bitacoraCambiosVO.setValorOriginal(infracLeyTransporteOld);
			bitacoraCambiosVO.setValorFinal(infracLeyTransporteNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
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
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

	
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getResponsableVehId(), newInfraccionVO.getP_r_veh_id())){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(39L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getResponsableVehId() != null ? oldInfraccionVO.getResponsableVehId() : "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getP_r_veh_id() != null ? newInfraccionVO.getP_r_veh_id().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		// falta conseguir el id del deposito
//		 String depIdOld = oldInfraccionVO.getIdDeposito() == null ? "" :
//		 oldInfraccionVO.getDeposito();
//		 String pDepIdnew = newInfraccionVO.getP_dep_id() == null ? ""
//		 : newInfraccionVO.getP_dep_id().toString();
		
		 if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getIdDeposito(),newInfraccionVO.getP_dep_id())) {
		 BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
		 bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		 bitacoraCambiosVO.setComponenteId(5L);
		 bitacoraCambiosVO.setConceptoId(40L);
		 bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getIdDeposito() == null ? "" :
			 oldInfraccionVO.getIdDeposito());
		 bitacoraCambiosVO.setValorFinal(newInfraccionVO.getP_dep_id() == null ? ""
				 : newInfraccionVO.getP_dep_id().toString());
		 bitacoraCambiosVO.setCreadoPor(
		 newInfraccionVO.getP_modificado_por() != null ?
		 newInfraccionVO.getP_modificado_por().longValue()
		 : 0L);
		 bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0,
		 11));
		 bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		 bitacoraCambiosVOs.add(bitacoraCambiosVO);
		
		 }

		// id deposito
		String vTipoOld = oldInfraccionVO.getTipoVehiculoId() == null ? "" : oldInfraccionVO.getTipoVehiculoId();
		String pvTipoId = newInfraccionVO.getP_vtipo_id() == null ? "" : newInfraccionVO.getP_vtipo_id().toString();
		if (!vTipoOld.equals(pvTipoId)) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(41L);
			bitacoraCambiosVO.setValorOriginal(vTipoOld);
			bitacoraCambiosVO.setValorFinal(pvTipoId);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		// -- para los casos de que valor inicial o final sea null
		String infracArrastreOld = oldInfraccionVO.getInfracArrastre() == null ? ""
				: oldInfraccionVO.getInfracArrastre();
		String infracArrastreNew = newInfraccionVO.getP_infrac_arrastre() == null ? ""
				: newInfraccionVO.getP_infrac_arrastre();
		if (infracArrastreOld.length() != infracArrastreNew.length()) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(42L);
			bitacoraCambiosVO.setValorOriginal(infracArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
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
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((infracArrastreOld.length() == infracArrastreNew.length())
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorLicencia(),newInfraccionVO.getP_infrac_licencia()))) {

			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(42L);
			bitacoraCambiosVO.setValorOriginal(infracArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		String infracTipoArrastreOld = oldInfraccionVO.getInfracTipoArrastre() == null ? ""
				: oldInfraccionVO.getInfracTipoArrastre();
		String infracTipoArrastreNew = newInfraccionVO.getP_infrac_tipo_arrastre() == null ? ""
				: newInfraccionVO.getP_infrac_tipo_arrastre();
		
		if (infracTipoArrastreOld.length() != infracTipoArrastreNew.length()) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(43L);
			bitacoraCambiosVO.setValorOriginal(infracTipoArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracTipoArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}
		else if ((infracTipoArrastreOld.length() == infracTipoArrastreNew.length())
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorLicencia(),newInfraccionVO.getP_infrac_licencia()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(43L);
			bitacoraCambiosVO.setValorOriginal(infracTipoArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracTipoArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		else if (!infracTipoArrastreOld.equals(infracTipoArrastreNew)) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(43L);
			bitacoraCambiosVO.setValorOriginal(infracTipoArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracTipoArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// -- autorizado por siempre se debe guardar

		// quien autoriza se debe obtener del con vo
		BitacoraCambiosVO bitacoraCambiosVOAut = new BitacoraCambiosVO();
		bitacoraCambiosVOAut.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVOAut.setComponenteId(5L);
		bitacoraCambiosVOAut.setConceptoId(44L);
		bitacoraCambiosVOAut
				.setValorOriginal(oldInfraccionVO.getAutorizaId() != null ? oldInfraccionVO.getAutorizaId() : "");
//		bitacoraCambiosVO.setValorFinal(
//				newInfraccionVO.getAutorizaId() != null ? newInfraccionVO.getAutorizaId() : "");
		bitacoraCambiosVOAut.setValorFinal(
				newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().toString() : "");
		bitacoraCambiosVOAut.setCreadoPor(
				newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue() : 0L);
		bitacoraCambiosVOAut.setIdRegistro(
				newInfraccionVO.getP_infrac_num_ctrl().length() < 11 ? newInfraccionVO.getP_infrac_num_ctrl()
						: newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
		bitacoraCambiosVOAut.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		bitacoraCambiosVOs.add(bitacoraCambiosVOAut);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		// fecha autorizacion
		// SE NECESITA OBTENER EL registro perteneciente a fecha autorizacion y esta se
		// trae de un store procedure V_SSP_INFRAC_CONS_GRAL_F
		if (oldInfraccionVO.getFechaAutoriza() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat realsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			String fechaAutorizaOlds = oldInfraccionVO.getFechaAutoriza() == null ? ""
					: oldInfraccionVO.getFechaAutoriza().toString();
			String fechaAutorizaNew = realsdf.format(new Date());
			String dateFechaAutorizaNew = sdf.format(new Date());
			fechaAutorizaOlds = realsdf.format(oldInfraccionVO.getFechaAutoriza());
			String dateFechaAutorizaOld = sdf.format(oldInfraccionVO.getFechaAutoriza());

			if (!fechaAutorizaOlds.equals(fechaAutorizaNew)) {
				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
				bitacoraCambiosVO.setComponenteId(5L);
				bitacoraCambiosVO.setConceptoId(45L);
				bitacoraCambiosVO.setValorOriginal(dateFechaAutorizaOld);
				bitacoraCambiosVO.setValorFinal(dateFechaAutorizaNew);
				bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getP_modificado_por() != null
						? newInfraccionVO.getP_modificado_por().longValue()
						: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
				bitacoraCambiosVOs.add(bitacoraCambiosVO);

			}
		}

		// se debe obtener la observacion en VO
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getMotivoCambio(),
				newInfraccionVO.getP_motivo_cambio())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(46L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getMotivoCambio() != null ? oldInfraccionVO.getMotivoCambio() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_motivo_cambio() != null ? newInfraccionVO.getP_motivo_cambio() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}

		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getMotivoCambio(), newInfraccionVO.getP_motivo_cambio()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getMotivoCambio(),
						newInfraccionVO.getP_motivo_cambio()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(46L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getMotivoCambio() != null ? oldInfraccionVO.getMotivoCambio() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_motivo_cambio() != null ? newInfraccionVO.getP_motivo_cambio() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);

		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracConDireccion(), newInfraccionVO.getP_infrac_con_direccion())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(52L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracConDireccion() != null ? oldInfraccionVO.getInfracConDireccion() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_con_direccion() != null ? newInfraccionVO.getP_infrac_con_direccion() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracFrenteAlNum(), newInfraccionVO.getP_infrac_frente_al_num())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(53L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracFrenteAlNum() != null ? oldInfraccionVO.getInfracFrenteAlNum() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_infrac_frente_al_num() != null ? newInfraccionVO.getP_infrac_frente_al_num() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}

		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracObserveQueId(), (newInfraccionVO.getP_observe_que() != null) ? newInfraccionVO.getP_observe_que().toString(): null)) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(54L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracObserveQueId() != null ? oldInfraccionVO.getInfracObserveQueId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getP_observe_que() != null ? newInfraccionVO.getP_observe_que().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getP_modificado_por() != null ? newInfraccionVO.getP_modificado_por().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getP_infrac_num_ctrl().substring(0, 11));
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}


		return bitacoraCambiosVOs;

	}

	
	
	
	/*  Metodo para realizar la comparacion del objeto viejo con el actual y generar bitacora de cambios ...   */

	@Override
	public ArrayList<BitacoraCambiosVO> guardarCambiosBitacora(VSSPInfracConsGralFVO newInfraccionVO, VSSPInfracConsGralFVO oldInfraccionVO) throws ParseException {
		bitacoraCambiosVOs = new ArrayList<>();
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getVehiculoPlaca(), newInfraccionVO.getVehiculoPlaca())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(2L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getVehiculoPlaca() != null ? oldInfraccionVO.getVehiculoPlaca() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getVehiculoPlaca() != null ? newInfraccionVO.getVehiculoPlaca() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getImpresa(), newInfraccionVO.getImpresa())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(3L);
			bitacoraCambiosVO
			.setValorOriginal(oldInfraccionVO.getImpresa() != null ? oldInfraccionVO.getImpresa() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getImpresa() != null ? newInfraccionVO.getImpresa() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getArticuloId(), newInfraccionVO.getArticuloId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(4L);
			bitacoraCambiosVO
			.setValorOriginal(oldInfraccionVO.getArticuloId() != null ? oldInfraccionVO.getArticuloId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getArticuloId() != null ? newInfraccionVO.getArticuloId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getFecha() , newInfraccionVO.getFecha())) { 
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			DateFormat DateTimeStampNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			String fechaOld = null, fechaNew = null; 
			if(oldInfraccionVO.getFecha() != null){
				Date dateOld = DateTimeStampNew.parse(oldInfraccionVO.getFecha());
				fechaOld = formatter.format(dateOld);
			}
			if(newInfraccionVO.getFecha() != null){
				Date dateNew = DateTimeStampNew.parse(newInfraccionVO.getFecha());
				fechaNew = formatter.format(dateNew);
			}
			
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(5L);
			bitacoraCambiosVO.setValorOriginal(fechaOld);
			bitacoraCambiosVO.setValorFinal(fechaNew);
			bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getModificadoPor() != null
					? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
//		if (oldInfraccionVO.getFecha() != null && newInfraccionVO.getFecha() != null) {
//			DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
//			DateFormat DateTimeStampNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//			Date dateOldFecha = formatter.parse(oldInfraccionVO.getFecha());
//			Date date = formatter.parse(newInfraccionVO.getFecha());
//			String dateNewFechaAuto = DateTimeStampNew.format(date);
//			String dateOldFechaAuto = DateTimeStampNew.format(dateOldFecha);
//			String dateOldFechaFormat = formatter.format(dateOldFecha);
//			String dateNewFechaFormat = formatter.format(date);
//			
//			if (!ComparaUtils.comparaCadenas(dateNewFechaAuto, dateOldFechaAuto)) {
//				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
//				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
//				bitacoraCambiosVO.setComponenteId(5L);
//				bitacoraCambiosVO.setConceptoId(5L);
//				bitacoraCambiosVO.setValorOriginal(dateOldFechaFormat);
//				bitacoraCambiosVO.setValorFinal(dateNewFechaFormat);
//				bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getModificadoPor() != null
//						? newInfraccionVO.getModificadoPor().longValue()
//								: 0L);
//				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
//				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
//				bitacoraCambiosVOs.add(bitacoraCambiosVO);
//				
//			}
//		}
		
		// -- para los casos de que valor inicial o final sea null
		// infracLicencia y tarjeta circulacion utilizan encripctacion
		// PKG_ENCRIPCION.ENCRIPTA()
		String infraccLicencia = oldInfraccionVO.getInfractorLicencia() == null ? ""
				: oldInfraccionVO.getInfractorLicencia();
		String infraccLicencianew = newInfraccionVO.getInfractorLicencia() == null ? ""
				: newInfraccionVO.getInfractorLicencia();
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
				bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getModificadoPor() != null
						? newInfraccionVO.getModificadoPor().longValue()
								: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
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
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		String tarjetaCirculacionOld = oldInfraccionVO.getTarjetaCirculacion() == null ? ""
				: oldInfraccionVO.getTarjetaCirculacion();
		String tarjetaCirculacionNew = newInfraccionVO.getTarjetaCirculacion() == null ? ""
				: newInfraccionVO.getTarjetaCirculacion();
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
				bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getModificadoPor() != null
						? newInfraccionVO.getModificadoPor().longValue()
								: 0L);
				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
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
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracCalle(),
				newInfraccionVO.getInfracCalle())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(8L);
			bitacoraCambiosVO
			.setValorOriginal(oldInfraccionVO.getInfracCalle() != null ? oldInfraccionVO.getInfracCalle() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracCalle() != null ? newInfraccionVO.getInfracCalle()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracEntreCalle(),
				newInfraccionVO.getInfracEntreCalle())) {
			
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(9L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracEntreCalle() != null ? oldInfraccionVO.getInfracEntreCalle() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracEntreCalle() != null ? newInfraccionVO.getInfracEntreCalle()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getVehiculoMarcaId(), newInfraccionVO.getVehiculoMarcaId())) {
			
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(10L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getVehiculoMarcaId() != null ? oldInfraccionVO.getVehiculoMarcaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getVehiculoMarcaId() != null ? newInfraccionVO.getVehiculoMarcaId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getVehiculoModeloId(), newInfraccionVO.getVehiculoModeloId())) {
			
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(11L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getVehiculoModeloId() != null ? oldInfraccionVO.getVehiculoModeloId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getVehiculoModeloId() != null ? newInfraccionVO.getVehiculoModeloId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getVehiculoColorId(), newInfraccionVO.getVehiculoColorId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(12L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getVehiculoColorId() != null ? oldInfraccionVO.getVehiculoColorId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getVehiculoColorId() != null ? newInfraccionVO.getVehiculoColorId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorNombre(),
				newInfraccionVO.getInfractorNombre())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(13L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorNombre() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorNombre()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfractorNombre() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorNombre()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if ((!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorNombre(), newInfraccionVO.getInfractorNombre()))
				&& (ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorNombre(),
						newInfraccionVO.getInfractorNombre()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(13L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorNombre() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorNombre()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfractorNombre() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorNombre()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorApePaterno(),
				newInfraccionVO.getInfractorApePaterno())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(14L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorApePaterno() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorApePaterno()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfractorApePaterno() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorApePaterno()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if ((!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorApePaterno(),
				newInfraccionVO.getInfractorApePaterno()))
				&& (ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorApePaterno(), newInfraccionVO.getInfractorApePaterno())
						)) {
			
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(14L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorApePaterno() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorApePaterno()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfractorApePaterno() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorApePaterno()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorApeMaterno(),
				newInfraccionVO.getInfractorApeMaterno())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(15L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorApeMaterno() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorApeMaterno()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfractorApeMaterno() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorApeMaterno()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if ((!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorApeMaterno(),
				newInfraccionVO.getInfractorApeMaterno()))
				&& (ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorApeMaterno(), newInfraccionVO.getInfractorApeMaterno()
						))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(15L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorApeMaterno() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorApeMaterno()) : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfractorApeMaterno() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorApeMaterno()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		// --**********************************************************************************************************************
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracSecId(), newInfraccionVO.getInfracSecId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(20L);
			bitacoraCambiosVO
			.setValorOriginal(oldInfraccionVO.getInfracSecId() != null ? oldInfraccionVO.getInfracSecId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracSecId() != null ? newInfraccionVO.getInfracSecId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracUniterrId(), newInfraccionVO.getInfracUniterrId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(21L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracUniterrId() != null ? oldInfraccionVO.getInfracUniterrId() : "");
			bitacoraCambiosVO
			.setValorFinal(newInfraccionVO.getInfracUniterrId() != null ? newInfraccionVO.getInfracUniterrId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorCalle(),
				newInfraccionVO.getInfractorCalle())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(22L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorCalle() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorCalle())
							: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfractorCalle() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorCalle())
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorCalle(),
				newInfraccionVO.getInfractorCalle()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorCalle(),
						newInfraccionVO.getInfractorCalle()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(22L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorCalle() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorCalle())
							: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfractorCalle() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorCalle())
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		// tiene que encriptarse ambas old y new
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorNumExt(),
				newInfraccionVO.getInfractorNumExt())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(23L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorNumExt() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorNumExt())
							: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfractorNumExt() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorNumExt())
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorNumExt(),
				newInfraccionVO.getInfractorNumExt()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorNumExt(),
						newInfraccionVO.getInfractorNumExt()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(23L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorNumExt() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorNumExt())
							: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfractorNumExt() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorNumExt())
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorNumInt(),
				newInfraccionVO.getInfractorNumInt())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(24L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorNumInt() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorNumInt())
							: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfractorNumInt() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorNumInt())
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorNumInt(),
				newInfraccionVO.getInfractorNumInt()))
				&& (!ComparaUtils.comparaCadenas(newInfraccionVO.getInfractorNumInt(),
						oldInfraccionVO.getInfractorNumInt()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(24L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorNumInt() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorNumInt())
							: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfractorNumInt() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorNumInt())
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorColonia(),
				newInfraccionVO.getInfractorColonia())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(25L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorColonia() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorColonia())
							: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfractorColonia() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorColonia())
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if ((ComparaUtils.comparaCadenasLength(newInfraccionVO.getInfractorColonia(),
				oldInfraccionVO.getInfractorColonia()))
				&& (!ComparaUtils.comparaCadenas(newInfraccionVO.getInfractorColonia(),
						oldInfraccionVO.getInfractorColonia()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(25L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getInfractorColonia() != null
					? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorColonia())
							: "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getInfractorColonia() != null
					? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorColonia())
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorEdoId(), newInfraccionVO.getInfractorEdoId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(26L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorEdoId() != null ? oldInfraccionVO.getInfractorEdoId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfractorEdoId() != null ? newInfraccionVO.getInfractorEdoId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorDelegId(),
				newInfraccionVO.getInfractorDelegId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(27L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorDelegId() != null ? oldInfraccionVO.getInfractorDelegId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfractorDelegId() != null ? newInfraccionVO.getInfractorDelegId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getPlacaExpedidaId(),newInfraccionVO.getPlacaExpedidaId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(28L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getPlacaExpedidaId() != null ? oldInfraccionVO.getPlacaExpedidaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getPlacaExpedidaId() != null ? newInfraccionVO.getPlacaExpedidaId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getPlacaExpedidaId(),newInfraccionVO.getPlacaExpedidaId()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getPlacaExpedidaId(),
						newInfraccionVO.getPlacaExpedidaId()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(28L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getPlacaExpedidaId() != null ? oldInfraccionVO.getPlacaExpedidaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getPlacaExpedidaId() != null ? newInfraccionVO.getPlacaExpedidaId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getLicenciaTipoId(), newInfraccionVO.getLicenciaTipoId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(29L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getLicenciaTipoId() != null ? oldInfraccionVO.getLicenciaTipoId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getLicenciaTipoId() != null ? newInfraccionVO.getLicenciaTipoId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getLicExpedidaId(),newInfraccionVO.getLicExpedidaId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(30L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getLicExpedidaId() != null ? oldInfraccionVO.getLicExpedidaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getLicExpedidaId() != null ? newInfraccionVO.getLicExpedidaId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getLicExpedidaId(),newInfraccionVO.getLicExpedidaId()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getLicExpedidaId(),
						newInfraccionVO.getLicExpedidaId()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(30L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getLicExpedidaId() != null ? oldInfraccionVO.getLicExpedidaId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getLicExpedidaId() != null ? newInfraccionVO.getLicExpedidaId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorRfc(), newInfraccionVO.getInfractorRfc())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(31L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorRfc() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorRfc()) : "");
			bitacoraCambiosVO
			.setValorFinal(newInfraccionVO.getInfractorRfc() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorRfc()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfractorRfc(), newInfraccionVO.getInfractorRfc()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorRfc(),
						newInfraccionVO.getInfractorRfc()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(31L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfractorRfc() != null ? bitDescriptar.getDesencriptarValue(oldInfraccionVO.getInfractorRfc()) : "");
			bitacoraCambiosVO
			.setValorFinal(newInfraccionVO.getInfractorRfc() != null ? bitDescriptar.getDesencriptarValue(newInfraccionVO.getInfractorRfc()) : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracLServPublico(),
				newInfraccionVO.getInfracLServPublico())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(32L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracLServPublico() != null ? oldInfraccionVO.getInfracLServPublico() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracLServPublico() != null ? newInfraccionVO.getInfracLServPublico()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracLServPublico(),
				newInfraccionVO.getInfracLServPublico()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracLServPublico(),
						newInfraccionVO.getInfracLServPublico()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(32L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracLServPublico() != null ? oldInfraccionVO.getInfracLServPublico() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracLServPublico() != null ? newInfraccionVO.getInfracLServPublico()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getVehiculoOrigen(), newInfraccionVO.getVehiculoOrigen())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(33L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getVehiculoOrigen() != null ? oldInfraccionVO.getVehiculoOrigen() : "");
			bitacoraCambiosVO
			.setValorFinal(newInfraccionVO.getVehiculoOrigen() != null ? newInfraccionVO.getVehiculoOrigen() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getVehiculoOrigen(), newInfraccionVO.getVehiculoOrigen()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getVehiculoOrigen(),
						newInfraccionVO.getVehiculoOrigen()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(33L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getVehiculoOrigen() != null ? oldInfraccionVO.getVehiculoOrigen() : "");
			bitacoraCambiosVO
			.setValorFinal(newInfraccionVO.getVehiculoOrigen() != null ? newInfraccionVO.getVehiculoOrigen() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(newInfraccionVO.getInfracYCalle(),
				oldInfraccionVO.getInfracYCalle())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(34L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracYCalle() != null ? oldInfraccionVO.getInfracYCalle() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracYCalle() != null ? newInfraccionVO.getInfracYCalle()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		if ((ComparaUtils.comparaCadenasLength(newInfraccionVO.getInfracYCalle(),
				oldInfraccionVO.getInfracYCalle()))
				&& (!ComparaUtils.comparaCadenas(newInfraccionVO.getInfracYCalle(),
						oldInfraccionVO.getInfracYCalle()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(34L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracYCalle() != null ? oldInfraccionVO.getInfracYCalle() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracYCalle() != null ? newInfraccionVO.getInfracYCalle()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracColonia(),
				newInfraccionVO.getInfracColonia())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(35L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracColonia() != null ? oldInfraccionVO.getInfracColonia() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracColonia() != null ? newInfraccionVO.getInfracColonia() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracColonia(),
				newInfraccionVO.getInfracColonia()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracColonia(),
						newInfraccionVO.getInfracColonia()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(35L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracColonia() != null ? oldInfraccionVO.getInfracColonia() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracColonia() != null ? newInfraccionVO.getInfracColonia() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracDelegacionId(),
				newInfraccionVO.getInfracDelegacionId())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(36L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracDelegacionId() != null ? oldInfraccionVO.getInfracDelegacionId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracDelegacionId() != null ? newInfraccionVO.getInfracDelegacionId().toString()
							: "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracObservacion(),
				newInfraccionVO.getInfracObservacion())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(37L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracObservacion() != null ? oldInfraccionVO.getInfracObservacion() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracObservacion() != null ? newInfraccionVO.getInfracObservacion() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getInfracObservacion(),
				newInfraccionVO.getInfracObservacion()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracObservacion(),
						newInfraccionVO.getInfracObservacion()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(37L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracObservacion() != null ? oldInfraccionVO.getInfracObservacion() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracObservacion() != null ? newInfraccionVO.getInfracObservacion() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		String infracLeyTransporteOld = oldInfraccionVO.getInfracLeyTransp() == null ? ""
				: oldInfraccionVO.getInfracLeyTransp();
		String infracLeyTransporteNew = newInfraccionVO.getInfracLeyTransp() == null ? ""
				: newInfraccionVO.getInfracLeyTransp();
		
		if (infracLeyTransporteNew.length() != infracLeyTransporteOld.length()) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(38L);
			bitacoraCambiosVO.setValorOriginal(infracLeyTransporteOld);
			bitacoraCambiosVO.setValorFinal(infracLeyTransporteNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
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
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getResponsableVehId(), newInfraccionVO.getResponsableVehId())){
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(39L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getResponsableVehId() != null ? oldInfraccionVO.getResponsableVehId() : "");
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getResponsableVehId() != null ? newInfraccionVO.getResponsableVehId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		// falta conseguir el id del deposito
//		 String depIdOld = oldInfraccionVO.getIdDeposito() == null ? "" :
//		 oldInfraccionVO.getDeposito();
//		 String pDepIdnew = newInfraccionVO.getP_dep_id() == null ? ""
//		 : newInfraccionVO.getP_dep_id().toString();
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getIdDeposito(),newInfraccionVO.getIdDeposito())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(40L);
			bitacoraCambiosVO.setValorOriginal(oldInfraccionVO.getIdDeposito() == null ? "" :
				oldInfraccionVO.getIdDeposito());
			bitacoraCambiosVO.setValorFinal(newInfraccionVO.getIdDeposito() == null ? ""
					: newInfraccionVO.getIdDeposito().toString());
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ?
							newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		// id deposito
		String vTipoOld = oldInfraccionVO.getTipoVehiculoId() == null ? "" : oldInfraccionVO.getTipoVehiculoId();
		String pvTipoId = newInfraccionVO.getTipoVehiculoId() == null ? "" : newInfraccionVO.getTipoVehiculoId().toString();
		if (!vTipoOld.equals(pvTipoId)) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(41L);
			bitacoraCambiosVO.setValorOriginal(vTipoOld);
			bitacoraCambiosVO.setValorFinal(pvTipoId);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		// -- para los casos de que valor inicial o final sea null
		String infracArrastreOld = oldInfraccionVO.getInfracArrastre() == null ? ""
				: oldInfraccionVO.getInfracArrastre();
		String infracArrastreNew = newInfraccionVO.getInfracArrastre() == null ? ""
				: newInfraccionVO.getInfracArrastre();
		if (infracArrastreOld.length() != infracArrastreNew.length()) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(42L);
			bitacoraCambiosVO.setValorOriginal(infracArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
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
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if ((infracArrastreOld.length() == infracArrastreNew.length())
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorLicencia(),newInfraccionVO.getInfractorLicencia()))) {
			
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(42L);
			bitacoraCambiosVO.setValorOriginal(infracArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		String infracTipoArrastreOld = oldInfraccionVO.getInfracTipoArrastre() == null ? ""
				: oldInfraccionVO.getInfracTipoArrastre();
		String infracTipoArrastreNew = newInfraccionVO.getInfracTipoArrastre() == null ? ""
				: newInfraccionVO.getInfracTipoArrastre();
		
		if (infracTipoArrastreOld.length() != infracTipoArrastreNew.length()) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(43L);
			bitacoraCambiosVO.setValorOriginal(infracTipoArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracTipoArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		else if ((infracTipoArrastreOld.length() == infracTipoArrastreNew.length())
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfractorLicencia(),newInfraccionVO.getInfractorLicencia()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(43L);
			bitacoraCambiosVO.setValorOriginal(infracTipoArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracTipoArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		else if (!infracTipoArrastreOld.equals(infracTipoArrastreNew)) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(43L);
			bitacoraCambiosVO.setValorOriginal(infracTipoArrastreOld);
			bitacoraCambiosVO.setValorFinal(infracTipoArrastreNew);
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// -- autorizado por siempre se debe guardar
		
		// quien autoriza se debe obtener del con vo
		BitacoraCambiosVO bitacoraCambiosVOAut = new BitacoraCambiosVO();
		bitacoraCambiosVOAut.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
		bitacoraCambiosVOAut.setComponenteId(5L);
		bitacoraCambiosVOAut.setConceptoId(44L);
		bitacoraCambiosVOAut
		.setValorOriginal(oldInfraccionVO.getAutorizaId() != null ? oldInfraccionVO.getAutorizaId() : "");
//		bitacoraCambiosVO.setValorFinal(
//				newInfraccionVO.getAutorizaId() != null ? newInfraccionVO.getAutorizaId() : "");
		bitacoraCambiosVOAut.setValorFinal(newInfraccionVO.getAutorizaId());
		bitacoraCambiosVOAut.setCreadoPor(
				newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue() : 0L);
		bitacoraCambiosVOAut.setIdRegistro(newInfraccionVO.getInfraccionNumero());
		bitacoraCambiosVOAut.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
		bitacoraCambiosVOs.add(bitacoraCambiosVOAut);

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		// fecha autorizacion
		// SE NECESITA OBTENER EL registro perteneciente a fecha autorizacion y esta se
		// trae de un store procedure V_SSP_INFRAC_CONS_GRAL_F
//		if (oldInfraccionVO.getFechaAutoriza() != null) {
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
//			SimpleDateFormat realsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
//			String fechaAutorizaOlds = oldInfraccionVO.getFechaAutoriza() == null ? ""
//					: oldInfraccionVO.getFechaAutoriza().toString();
//			String fechaAutorizaNew = realsdf.format(new Date());
//			String dateFechaAutorizaNew = sdf.format(new Date());
//			fechaAutorizaOlds = realsdf.format(oldInfraccionVO.getFechaAutoriza());
//			String dateFechaAutorizaOld = sdf.format(oldInfraccionVO.getFechaAutoriza());
//			
//			if (!fechaAutorizaOlds.equals(fechaAutorizaNew)) {
//				BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
//				bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
//				bitacoraCambiosVO.setComponenteId(5L);
//				bitacoraCambiosVO.setConceptoId(45L);
//				bitacoraCambiosVO.setValorOriginal(dateFechaAutorizaOld);
//				bitacoraCambiosVO.setValorFinal(dateFechaAutorizaNew);
//				bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getModificadoPor() != null
//						? newInfraccionVO.getModificadoPor().longValue()
//								: 0L);
//				bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
//				bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
//				bitacoraCambiosVOs.add(bitacoraCambiosVO);
//				
//			}
//		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getFechaAutoriza() != null ? oldInfraccionVO.getFechaAutoriza().toString(): null,
				newInfraccionVO.getFechaAutoriza()!= null ? newInfraccionVO.getFechaAutoriza().toString() : null )) { 
			
			DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
//			DateFormat DateTimeStampNew = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
			String fechaOld = null, fechaNew = null; 
			if(oldInfraccionVO.getFecha() != null)
				fechaOld = formatter.format(oldInfraccionVO.getFecha());
			if(newInfraccionVO.getFecha() != null)
				fechaNew = formatter.format(newInfraccionVO.getFecha());
			
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(45L);
			bitacoraCambiosVO.setValorOriginal(fechaOld);
			bitacoraCambiosVO.setValorFinal(fechaNew);
			bitacoraCambiosVO.setCreadoPor(newInfraccionVO.getModificadoPor() != null
					? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		// se debe obtener la observacion en VO
		if (!ComparaUtils.comparaCadenasLength(oldInfraccionVO.getMotivoCambio(),
				newInfraccionVO.getMotivoCambio())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(46L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getMotivoCambio() != null ? oldInfraccionVO.getMotivoCambio() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getMotivoCambio() != null ? newInfraccionVO.getMotivoCambio() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if ((ComparaUtils.comparaCadenasLength(oldInfraccionVO.getMotivoCambio(), newInfraccionVO.getMotivoCambio()))
				&& (!ComparaUtils.comparaCadenas(oldInfraccionVO.getMotivoCambio(),
						newInfraccionVO.getMotivoCambio()))) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(46L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getMotivoCambio() != null ? oldInfraccionVO.getMotivoCambio() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getMotivoCambio() != null ? newInfraccionVO.getMotivoCambio() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracConDireccion(), newInfraccionVO.getInfracConDireccion())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(52L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracConDireccion() != null ? oldInfraccionVO.getInfracConDireccion() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracConDireccion() != null ? newInfraccionVO.getInfracConDireccion() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracFrenteAlNum(), newInfraccionVO.getInfracFrenteAlNum())) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(53L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracFrenteAlNum() != null ? oldInfraccionVO.getInfracFrenteAlNum() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracFrenteAlNum() != null ? newInfraccionVO.getInfracFrenteAlNum() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
		}
		
		if (!ComparaUtils.comparaCadenas(oldInfraccionVO.getInfracObserveQueId(), (newInfraccionVO.getInfracObserveQueId() != null) ? newInfraccionVO.getInfracObserveQueId().toString(): null)) {
			BitacoraCambiosVO bitacoraCambiosVO = new BitacoraCambiosVO();
			bitacoraCambiosVO.setTable(ParametrosBitacoraEnum.TAI_BITACORA_CAMBIOS.getParametro());
			bitacoraCambiosVO.setComponenteId(5L);
			bitacoraCambiosVO.setConceptoId(54L);
			bitacoraCambiosVO.setValorOriginal(
					oldInfraccionVO.getInfracObserveQueId() != null ? oldInfraccionVO.getInfracObserveQueId() : "");
			bitacoraCambiosVO.setValorFinal(
					newInfraccionVO.getInfracObserveQueId() != null ? newInfraccionVO.getInfracObserveQueId().toString() : "");
			bitacoraCambiosVO.setCreadoPor(
					newInfraccionVO.getModificadoPor() != null ? newInfraccionVO.getModificadoPor().longValue()
							: 0L);
			bitacoraCambiosVO.setIdRegistro(newInfraccionVO.getInfraccionNumero());
			bitacoraCambiosVO.setOrigen(ParametrosBitacoraEnum.ORIGEN_W.getParametro());
			bitacoraCambiosVOs.add(bitacoraCambiosVO);
			
		}
		
		
		return bitacoraCambiosVOs;
		
	}

}
