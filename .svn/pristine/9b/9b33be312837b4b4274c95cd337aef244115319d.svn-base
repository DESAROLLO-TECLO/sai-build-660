angular.module("siidfApp").service("empleadosService", function ($http, config) {	
	this.validarPerfil = function (screen) {
		return $http.get(config.baseUrl + "/validarPerfilEmpleado", 
				{params:{"screen": screen}});
	};
	
	this.validarPerfilUsuarios = function(){
		return $http.get(config.baseUrl + "/validarPerfilParaUsuarios");
	};
	
	this.validarPerfilCertificados = function(){
		return $http.get(config.baseUrl + "/validarPerfilParaCertificadosSAT");
	}
});