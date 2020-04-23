package mx.com.teclo.saicdmx.persistencia.hibernate.dto.estadistica;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TSAI001C_TIPO_INFRACCION")
public class TipoInfraccionDTO implements Serializable {
     
	/**
	 * 
	 */
	private static final long serialVersionUID = -6239579754285843075L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_TIPO_INFRACCION", unique = true, nullable = false)
	private Long idTipoInfraccion;
	@Column(name = "CD_TIPO_INFRACCION")
	private String cdTipoInfraccion;
	@Column(name = "NB_TIPO_INFRACCION")
	private String nbTipoInfraccion;
	@Column(name = "TX_DESC_INFRACCION")
	private String TxDescInfraccion; 
	@Column(name = "NB_DISPOSITIVO")
	private String nbDispositivo;
	@Column(name = "NB_TABLA_DESTINO")
	private String nbTablaDestino;
	@Column(name = "ST_ACTIVO")
	private int stActivo; 
	@Column(name = "ID_USR_CREACION")
	private Long idUsrCreacion; 
	@Column(name = "FH_CREACION")
	private Date fhCreacion;
	@Column(name = "ID_USR_MODIFICA")
	private int idUsrModifica; 
	@Column(name = "FH_MODIFICACION")
	private Date fhModificacion;
	@Column(name = "ST_MAPA")
	private int stMapa;
	@Column(name = "NB_VISTA_CONSULTA_LC")
	private String nbVitaLC;
	
	public Long getIdTipoInfraccion() {
		return idTipoInfraccion;
	}
	public void setIdTipoInfraccion(Long idTipoInfraccion) {
		this.idTipoInfraccion = idTipoInfraccion;
	}
	public String getCdTipoInfraccion() {
		return cdTipoInfraccion;
	}
	public void setCdTipoInfraccion(String cdTipoInfraccion) {
		this.cdTipoInfraccion = cdTipoInfraccion;
	}
	public String getNbTipoInfraccion() {
		return nbTipoInfraccion;
	}
	public void setNbTipoInfraccion(String nbTipoInfraccion) {
		this.nbTipoInfraccion = nbTipoInfraccion;
	}
	public String getTxDescInfraccion() {
		return TxDescInfraccion;
	}
	public void setTxDescInfraccion(String txDescInfraccion) {
		TxDescInfraccion = txDescInfraccion;
	}
	public String getNbDispositivo() {
		return nbDispositivo;
	}
	public void setNbDispositivo(String nbDispositivo) {
		this.nbDispositivo = nbDispositivo;
	}
	public String getNbTablaDestino() {
		return nbTablaDestino;
	}
	public void setNbTablaDestino(String nbTablaDestino) {
		this.nbTablaDestino = nbTablaDestino;
	}
	public int getStActivo() {
		return stActivo;
	}
	public void setStActivo(int stActivo) {
		this.stActivo = stActivo;
	}
	public Long getIdUsrCreacion() {
		return idUsrCreacion;
	}
	public void setIdUsrCreacion(Long idUsrCreacion) {
		this.idUsrCreacion = idUsrCreacion;
	}
	public Date getFhCreacion() {
		return fhCreacion;
	}
	public void setFhCreacion(Date fhCreacion) {
		this.fhCreacion = fhCreacion;
	}
	public int getIdUsrModifica() {
		return idUsrModifica;
	}
	public void setIdUsrModifica(int idUsrModifica) {
		this.idUsrModifica = idUsrModifica;
	}
	public Date getFhModificacion() {
		return fhModificacion;
	}
	public void setFhModificacion(Date fhModificacion) {
		this.fhModificacion = fhModificacion;
	}
	public int getStMapa() {
		return stMapa;
	}
	public void setStMapa(int stMapa) {
		this.stMapa = stMapa;
	}
	public String getNbVitaLC() {
		return nbVitaLC;
	}
	public void setNbVitaLC(String nbVitaLC) {
		this.nbVitaLC = nbVitaLC;
	}
}
