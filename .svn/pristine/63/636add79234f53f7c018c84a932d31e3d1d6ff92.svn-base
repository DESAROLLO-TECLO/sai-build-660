package mx.com.teclo.saicdmx.persistencia.hibernate.dto.pagos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.catalogos.CajaDTO;

@Entity
@Table(name = "TRANSACCIONES")
public class TransaccionDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private TransacionPrimaryKeyDTO transaccionId;
	
	@Column(name = "TRAN_IMPORTE")
	private Long tranImporte;
	@Column(name = "INFRAC_NUM")
	private String infracNum;
	@Column(name = "BAN_ID")
	private Long banId;
	@Column(name = "BAN_NOMBRE")
	private String banNombre;
	@Column(name = "TRAN_ORDEN")
	private String tranOrden;
	@Column(name = "TRAN_TARJETA")
	private String tranTarjeta;
	@Column(name = "TRAN_NOMBRE")
	private String tranNombre;
	@Column(name = "TRAN_RESPUESTA")
	private String tranRespuesta;
	@Column(name = "TRAN_NUM_AUTORIZA")
	private String tranNumAutoriza;
	@Column(name = "TRAN_NOM_AFIL")
	private String tranNomAfil;
	@Column(name = "TRAN_BAN_AFIL")
	private String tranBanAfil;
	@Column(name = "TRAN_FECHA")
	private Date tranFecha;
	@Column(name = "TRAN_REFERENCIA")
	private String tranReferencia;
	@Column(name = "TRAN_STATUS")
	private String tranStatus;
	@Column(name = "TRAN_FECH_MOD")
	private Date tranFech_mod;
	@Column(name = "TRAN_LINEA_BANCO")
	private String tranLineaBanco;

	

	public TransacionPrimaryKeyDTO getTransaccionId() {
		return transaccionId;
	}

	public void setTransaccionId(TransacionPrimaryKeyDTO transaccionId) {
		this.transaccionId = transaccionId;
	}

	public Long getTranImporte() {
		return tranImporte;
	}

	public void setTranImporte(Long tranImporte) {
		this.tranImporte = tranImporte;
	}

	public String getInfracNum() {
		return infracNum;
	}

	public void setInfracNum(String infracNum) {
		this.infracNum = infracNum;
	}

	public Long getBanId() {
		return banId;
	}

	public void setBanId(Long banId) {
		this.banId = banId;
	}

	public String getBanNombre() {
		return banNombre;
	}

	public void setBanNombre(String banNombre) {
		this.banNombre = banNombre;
	}

	public String getTranOrden() {
		return tranOrden;
	}

	public void setTranOrden(String tranOrden) {
		this.tranOrden = tranOrden;
	}

	public String getTranTarjeta() {
		return tranTarjeta;
	}

	public void setTranTarjeta(String tranTarjeta) {
		this.tranTarjeta = tranTarjeta;
	}

	public String getTranNombre() {
		return tranNombre;
	}

	public void setTranNombre(String tranNombre) {
		this.tranNombre = tranNombre;
	}

	public String getTranRespuesta() {
		return tranRespuesta;
	}

	public void setTranRespuesta(String tranRespuesta) {
		this.tranRespuesta = tranRespuesta;
	}

	public String getTranNumAutoriza() {
		return tranNumAutoriza;
	}

	public void setTranNumAutoriza(String tranNumAutoriza) {
		this.tranNumAutoriza = tranNumAutoriza;
	}

	public String getTranNomAfil() {
		return tranNomAfil;
	}

	public void setTranNomAfil(String tranNomAfil) {
		this.tranNomAfil = tranNomAfil;
	}

	public String getTranBanAfil() {
		return tranBanAfil;
	}

	public void setTranBanAfil(String tranBanAfil) {
		this.tranBanAfil = tranBanAfil;
	}

	public Date getTranFecha() {
		return tranFecha;
	}

	public void setTranFecha(Date tranFecha) {
		this.tranFecha = tranFecha;
	}

	public String getTranReferencia() {
		return tranReferencia;
	}

	public void setTranReferencia(String tranReferencia) {
		this.tranReferencia = tranReferencia;
	}

	public String getTranStatus() {
		return tranStatus;
	}

	public void setTranStatus(String tranStatus) {
		this.tranStatus = tranStatus;
	}

	public Date getTranFech_mod() {
		return tranFech_mod;
	}

	public void setTranFech_mod(Date tranFech_mod) {
		this.tranFech_mod = tranFech_mod;
	}

	public String getTranLineaBanco() {
		return tranLineaBanco;
	}

	public void setTranLineaBanco(String tranLineaBanco) {
		this.tranLineaBanco = tranLineaBanco;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
