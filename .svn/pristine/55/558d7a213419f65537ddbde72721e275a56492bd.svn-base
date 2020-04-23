package mx.com.teclo.saicdmx.persistencia.hibernate.dto.infracciones;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INVENTARIO_DEPOSITOS")
public class InventarioDepositosDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "DEP_ID")
	private Long depositoId;
	@Column(name = "PROG_ID")
	private Long programaId;
	@Column(name = "DEP_FECHA")
	private Date depositoFecha;
    @Column(name = "DEP_ANTERIOR")
	private Long depositoAnterior;
    @Column (name = "DEP_ENTRADAS")
	private Long depositoEntradas;
	@Column (name = "DEP_SALIDAS")
    private Long depositoSalidas;
	@Column (name = "DEP_CUSTODIA")
	private Long depositoCustodia;
	
	public Long getDepositoId() {
		return depositoId;
	}
	public void setDepositoId(Long depositoId) {
		this.depositoId = depositoId;
	}
	public Long getProgramaId() {
		return programaId;
	}
	public void setProgramaId(Long programaId) {
		this.programaId = programaId;
	}
	public Date getDepositoFecha() {
		return depositoFecha;
	}
	public void setDepositoFecha(Date depositoFecha) {
		this.depositoFecha = depositoFecha;
	}
	public Long getDepositoAnterior() {
		return depositoAnterior;
	}
	public void setDepositoAnterior(Long depositoAnterior) {
		this.depositoAnterior = depositoAnterior;
	}
	public Long getDepositoEntradas() {
		return depositoEntradas;
	}
	public void setDepositoEntradas(Long depositoEntradas) {
		this.depositoEntradas = depositoEntradas;
	}
	public Long getDepositoSalidas() {
		return depositoSalidas;
	}
	public void setDepositoSalidas(Long depositoSalidas) {
		this.depositoSalidas = depositoSalidas;
	}
	public Long getDepositoCustodia() {
		return depositoCustodia;
	}
	public void setDepositoCustodia(Long depositoCustodia) {
		this.depositoCustodia = depositoCustodia;
	}

	

}
