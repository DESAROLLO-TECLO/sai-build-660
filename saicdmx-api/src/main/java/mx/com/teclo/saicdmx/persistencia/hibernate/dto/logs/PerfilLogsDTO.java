package mx.com.teclo.saicdmx.persistencia.hibernate.dto.logs;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.com.teclomexicana.arquitectura.ortogonales.seguridad.dto.PerfilDTO;

@Entity
@Table(name="PERFIL_LOGS")
public class PerfilLogsDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8712894809770168539L;

	/**
	 * 
	 */
	
	@Id
    @GeneratedValue
    @Column(name = "PERFIL_LOGS_ID")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "PERFIL_ID")
	private PerfilDTO perfilDTO;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "LOG_ID")
	private WebLogsDTO webLogsDTO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PerfilDTO getPerfilDTO() {
		return perfilDTO;
	}

	public void setPerfilDTO(PerfilDTO perfilDTO) {
		this.perfilDTO = perfilDTO;
	}

	public WebLogsDTO getWebLogsDTO() {
		return webLogsDTO;
	}

	public void setWebLogsDTO(WebLogsDTO webLogsDTO) {
		this.webLogsDTO = webLogsDTO;
	}
	
	
	
	

}
