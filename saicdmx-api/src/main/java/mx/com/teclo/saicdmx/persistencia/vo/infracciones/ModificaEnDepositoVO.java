package mx.com.teclo.saicdmx.persistencia.vo.infracciones;

import java.io.Serializable;

public class ModificaEnDepositoVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5937087152257364354L;
	
	private String pInfracNumCtrl;       
	private String pInfracConPlaca; 		
	private String pInfracPlaca;     		
	private String pInfracArrastre; 		
	private String pInfracTipoArrastre; 
	private Long pVehiculoTipo;
	private Long pArticuloId;	
	private String pMotivoCambio;			
	private Long pModificadoPor;        
	private Long pAutorizadoPor;				 
	private String pResultado;      		
	private String pMensaje;
	private ModificaEnDepositoSPVO modificaEnDepositoSPVO;
	
	public String getpInfracNumCtrl() {
		return pInfracNumCtrl;
	}
	public void setpInfracNumCtrl(String pInfracNumCtrl) {
		this.pInfracNumCtrl = pInfracNumCtrl;
	}
	public String getpInfracConPlaca() {
		return pInfracConPlaca;
	}
	public void setpInfracConPlaca(String pInfracConPlaca) {
		this.pInfracConPlaca = pInfracConPlaca;
	}
	public String getpInfracPlaca() {
		return pInfracPlaca;
	}
	public void setpInfracPlaca(String pInfracPlaca) {
		this.pInfracPlaca = pInfracPlaca;
	}
	public String getpInfracArrastre() {
		return pInfracArrastre;
	}
	public void setpInfracArrastre(String pInfracArrastre) {
		this.pInfracArrastre = pInfracArrastre;
	}
	public String getpInfracTipoArrastre() {
		return pInfracTipoArrastre;
	}
	public void setpInfracTipoArrastre(String pInfracTipoArrastre) {
		this.pInfracTipoArrastre = pInfracTipoArrastre;
	}
	public Long getpVehiculoTipo() {
		return pVehiculoTipo;
	}
	public void setpVehiculoTipo(Long pVehiculoTipo) {
		this.pVehiculoTipo = pVehiculoTipo;
	}
	public Long getpArticuloId() {
		return pArticuloId;
	}
	public void setpArticuloId(Long pArticuloId) {
		this.pArticuloId = pArticuloId;
	}
	public String getpMotivoCambio() {
		return pMotivoCambio;
	}
	public void setpMotivoCambio(String pMotivoCambio) {
		this.pMotivoCambio = pMotivoCambio;
	}
	public Long getpModificadoPor() {
		return pModificadoPor;
	}
	public void setpModificadoPor(Long pModificadoPor) {
		this.pModificadoPor = pModificadoPor;
	}
	public Long getpAutorizadoPor() {
		return pAutorizadoPor;
	}
	public void setpAutorizadoPor(Long pAutorizadoPor) {
		this.pAutorizadoPor = pAutorizadoPor;
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
	public ModificaEnDepositoSPVO getModificaEnDepositoSPVO() {
		return modificaEnDepositoSPVO;
	}
	public void setModificaEnDepositoSPVO(ModificaEnDepositoSPVO modificaEnDepositoSPVO) {
		this.modificaEnDepositoSPVO = modificaEnDepositoSPVO;
	}
	public void generaParametrosParaSP(){
		this.modificaEnDepositoSPVO =
			new ModificaEnDepositoSPVO(pInfracNumCtrl,
				pInfracConPlaca,
				pInfracPlaca,
				pInfracArrastre,
				pInfracTipoArrastre,
				pVehiculoTipo,
				pArticuloId,
				pMotivoCambio,
				pModificadoPor,
				pAutorizadoPor,
				pResultado,
				pMensaje);
	}
}