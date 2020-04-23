package mx.com.teclo.saicdmx.persistencia.vo.atencionciudadana;

import java.io.Serializable;



public class TipoDocumentoVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8122740874203726246L;
	
		private Long idDocumento;
		private String cdDocumento;
		private String nbDocumento;
		private String txDescripcion;
		public Long getIdDocumento() {
			return idDocumento;
		}
		public void setIdDocumento(Long idDocumento) {
			this.idDocumento = idDocumento;
		}
		public String getCdDocumento() {
			return cdDocumento;
		}
		public void setCdDocumento(String cdDocumento) {
			this.cdDocumento = cdDocumento;
		}
		public String getNbDocumento() {
			return nbDocumento;
		}
		public void setNbDocumento(String nbDocumento) {
			this.nbDocumento = nbDocumento;
		}
		public String getTxDescripcion() {
			return txDescripcion;
		}
		public void setTxDescripcion(String txDescripcion) {
			this.txDescripcion = txDescripcion;
		}
		
		

}
