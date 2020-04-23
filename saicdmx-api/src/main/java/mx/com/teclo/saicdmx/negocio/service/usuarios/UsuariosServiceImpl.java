package mx.com.teclo.saicdmx.negocio.service.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.teclo.saicdmx.persistencia.hibernate.dao.usuarios.UsuariosDAO;
import mx.com.teclo.saicdmx.persistencia.hibernate.dto.usuarios.UsuariosDTO;

@Service
public class UsuariosServiceImpl implements UsuariosService{

	@Autowired
	private UsuariosDAO usuarioDAO;
	
	@Override
	@Transactional
	public UsuariosDTO buscaUsuarioById(Long Id) {
		UsuariosDTO usuarioDTO = usuarioDAO.findOne(Id);
		return usuarioDTO;
	}

}
