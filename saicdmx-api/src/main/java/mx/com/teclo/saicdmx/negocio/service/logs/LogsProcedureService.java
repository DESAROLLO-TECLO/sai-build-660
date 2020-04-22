package mx.com.teclo.saicdmx.negocio.service.logs;

import java.text.ParseException;

import mx.com.teclo.saicdmx.persistencia.hibernate.dto.bitacora.BitacoraCambiosDTO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.LogsVO;
import mx.com.teclo.saicdmx.persistencia.vo.logs.ResultadoProcedureVO;

public interface LogsProcedureService {
	
	/**
     * Agrega el permiso de acceso a un <b>Perfil sobre un Log</b>.
     *
     * @param logId log al que se le asociara un perfil
     * @param perfilId perfil a asignar
     */
   void agregarPerfilALog(Long logId, Long perfilId, Long usuarioId);

    /**
     * Elimina el permiso de acceso de un <b>Perfil sobre un Log</b>.
     *
     * @param logId log al que se le asociara un perfil
     * @param perfilId perfil a asignar
     */
    void eliminarPerfilALog(Long logId, Long perfilId, Long usuarioId);

    /**
     * Habilita o deshabilita <b>Consultas a Logs</b> dependiendo del par�metro
     * especificado.
     *
     * @param logId Log al que se le cambiar� el estatus
     * @param accion A:Activo D:Desactivado
     */
     void cambioDeEstatus(Long logId, String accion); 

    /**
     * Inserta en bitacora cambios.
     *
     * @param parametros Map que contiene todos los parametros
     */
     void insertaBitacoraCambios(BitacoraCambiosDTO bitacoraCambiosDTO);
    /**
     * Crea o modifica un <b>Registro de Log</b>.
     *
     * @param log Bean con los parametros a modificar o crear.
     * @param operacion A:Alta M:Modificar
     *
     * @return Regresa un valor de tipo<b>ResultadoProcedureVO</b>.
     * @throws ParseException 
     */
     LogsVO crudLog(LogsVO log, String operacion) throws ParseException;
}
