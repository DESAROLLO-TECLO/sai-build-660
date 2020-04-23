package mx.com.teclo.saicdmx.persistencia.vo.lineadecaptura;

import java.math.BigDecimal;

public class ConsultaLoteLCMVO {
	private String infrac_num;
	private String linea_captura;
	private String emp_nombre;
	private String emp_ape_paterno;
	private String emp_ape_materno;
	private String emp_placa;
	private Long numero_dias;
	private String fecha_reasignacion;
	private String vigencia;
	private Long salario_minimo;
	private BigDecimal importe;
	private BigDecimal recargos;
	private BigDecimal descuento;
	private BigDecimal total;
	private String estatus_resultado;
	private String fecha_creacion;
	
	public String getInfrac_num() {
		return infrac_num;
	}
	public void setInfrac_num(String infrac_num) {
		this.infrac_num = infrac_num;
	}
	public String getLinea_captura() {
		return linea_captura;
	}
	public void setLinea_captura(String linea_captura) {
		this.linea_captura = linea_captura;
	}
	public String getEmp_nombre() {
		return emp_nombre;
	}
	public void setEmp_nombre(String emp_nombre) {
		this.emp_nombre = emp_nombre;
	}
	public String getEmp_ape_paterno() {
		return emp_ape_paterno;
	}
	public void setEmp_ape_paterno(String emp_ape_paterno) {
		this.emp_ape_paterno = emp_ape_paterno;
	}
	public String getEmp_ape_materno() {
		return emp_ape_materno;
	}
	public void setEmp_ape_materno(String emp_ape_materno) {
		this.emp_ape_materno = emp_ape_materno;
	}
	public String getNombreCompleto(){
		return emp_nombre+" "+emp_ape_paterno+" "+emp_ape_materno;
	}
	public String getEmp_placa() {
		return emp_placa;
	}
	public void setEmp_placa(String emp_placa) {
		this.emp_placa = emp_placa;
	}
	public Long getNumero_dias() {
		return numero_dias;
	}
	public void setNumero_dias(Long numero_dias) {
		this.numero_dias = numero_dias;
	}
	public String getFecha_reasignacion() {
		return fecha_reasignacion;
	}
	public void setFecha_reasignacion(String fecha_reasignacion) {
		this.fecha_reasignacion = fecha_reasignacion;
	}
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	public Long getSalario_minimo() {
		return salario_minimo;
	}
	public void setSalario_minimo(Long salario_minimo) {
		this.salario_minimo = salario_minimo;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getRecargos() {
		return recargos;
	}
	public void setRecargos(BigDecimal recargos) {
		this.recargos = recargos;
	}
	public BigDecimal getDescuento() {
		return descuento;
	}
	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getEstatus_resultado() {
		return estatus_resultado;
	}
	public void setEstatus_resultado(String estatus_resultado) {
		this.estatus_resultado = estatus_resultado;
	}
	public String getFecha_creacion() {
		return fecha_creacion;
	}
	public void setFecha_creacion(String fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}
}
