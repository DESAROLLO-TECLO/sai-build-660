package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

public class InfraccionValidaImagenVO {
	private Long pEmpId;
	private String pInfracnum;
	private String pEstatus;
	private String pResultado;
	private String pMensaje;
	private InfraccionValidaImagenSPVO infraccionValidaImagenSPVO;
	
	public Long getpEmpId() {
		return pEmpId;
	}
	public void setpEmpId(Long pEmpId) {
		this.pEmpId = pEmpId;
	}
	public String getpInfracnum() {
		return pInfracnum;
	}
	public void setpInfracnum(String pInfracnum) {
		this.pInfracnum = pInfracnum;
	}
	public String getpEstatus() {
		return pEstatus;
	}
	public void setpEstatus(String pEstatus) {
		this.pEstatus = pEstatus;
	}
	public String getpResultado() {
		return pResultado;
	}
	public void setpResultado(String pResultado) {
		this.pResultado = pResultado;
	}
	public String getpMensaje() {
		return pMensaje;
	}
	public void setpMensaje(String pMensaje) {
		this.pMensaje = pMensaje;
	}
	public InfraccionValidaImagenSPVO getInfraccionValidaImagenSPVO() {
		return infraccionValidaImagenSPVO;
	}
	public void setInfraccionValidaImagenSPVO(InfraccionValidaImagenSPVO infraccionValidaImagenSPVO) {
		this.infraccionValidaImagenSPVO = infraccionValidaImagenSPVO;
	}
	
	@Override
	public String toString() {
		return "InfraccionValidaImagenVO [pEmpId=" + pEmpId + ", pInfracnum=" + pInfracnum + ", pEstatus=" + pEstatus
				+ ", pResultado=" + pResultado + ", pMensaje=" + pMensaje + ", infraccionValidaImagenSPVO="
				+ infraccionValidaImagenSPVO + "]";
	}
	
	public void generaParametrosParaSP(){
		infraccionValidaImagenSPVO = new InfraccionValidaImagenSPVO(pEmpId,
				pInfracnum,
				pEstatus,
				pResultado,
				pMensaje);
	}
}
