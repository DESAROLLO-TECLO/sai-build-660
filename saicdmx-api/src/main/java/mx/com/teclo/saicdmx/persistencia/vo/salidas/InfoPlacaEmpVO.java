package mx.com.teclo.saicdmx.persistencia.vo.salidas;

public class InfoPlacaEmpVO {

	  private String  empId;  
	  private String  placaEmp;
	  private String  nomComp;
	  private String  apellidoPaterno;
	  private String  apellidoMaterno;
	  private String  NombreEmp;
	  private String nomAgrupamiento;
	  private String nomSector;
	  private String perfil;
	  
	  
	  
	public String getNomAgrupamiento() {
		return nomAgrupamiento;
	}
	public void setNomAgrupamiento(String nomAgrupamiento) {
		this.nomAgrupamiento = nomAgrupamiento;
	}
	public String getNomSector() {
		return nomSector;
	}
	public void setNomSector(String nomSector) {
		this.nomSector = nomSector;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getPlacaEmp() {
		return placaEmp;
	}
	public void setPlacaEmp(String placaEmp) {
		this.placaEmp = placaEmp;
	}
	public String getNomComp() {
		return nomComp;
	}
	public void setNomComp(String nomComp) {
		this.nomComp = nomComp;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getNombreEmp() {
		return NombreEmp;
	}
	public void setNombreEmp(String nombreEmp) {
		NombreEmp = nombreEmp;
	}
	  
	  
}
