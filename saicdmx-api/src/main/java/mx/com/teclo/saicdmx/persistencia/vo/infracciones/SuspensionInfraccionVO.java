package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

public class SuspensionInfraccionVO {
	
	private String pInfracNum;
	private String pInfracOficio;
	private Long pEmpId;
	private Long modificadoPor;
	private String pResultado;
	private String pMensaje;
	private SuspensionInfraccionSPVO suspensionInfraccionSPVO;
	
	public SuspensionInfraccionVO(){
		
	}
	
	public SuspensionInfraccionVO(String pInfracNum, String pInfracOficio, Long pEmpId, String pResultado,
			String pMensaje) {
		this.pInfracNum = pInfracNum;
		this.pInfracOficio = pInfracOficio;
		this.pEmpId = pEmpId;
		this.pResultado = pResultado;
		this.pMensaje = pMensaje;
	}
	
	public Long getModificadoPor() {
		return modificadoPor;
	}

	public void setModificadoPor(Long modificadoPor) {
		this.modificadoPor = modificadoPor;
	}

	public String getpInfracNum() {
		return pInfracNum;
	}
	public void setpInfracNum(String pInfracNum) {
		this.pInfracNum = pInfracNum;
	}
	public String getpInfracOficio() {
		return pInfracOficio;
	}
	public void setpInfracOficio(String pInfracOficio) {
		this.pInfracOficio = pInfracOficio;
	}
	public Long getpEmpId() {
		return pEmpId;
	}
	public void setpEmpId(Long pEmpId) {
		this.pEmpId = pEmpId;
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
	public SuspensionInfraccionSPVO getSuspensionInfraccionSPVO() {
		return suspensionInfraccionSPVO;
	}
	public void setSuspensionInfraccionSPVO(SuspensionInfraccionSPVO suspensionInfraccionSPVO) {
		this.suspensionInfraccionSPVO = suspensionInfraccionSPVO;
	}
	
	public void generaParametrosParaSP(){
		this.suspensionInfraccionSPVO = new SuspensionInfraccionSPVO(pInfracNum,
				pInfracOficio,
				pEmpId,
				pResultado,
				pMensaje);
	}
}
