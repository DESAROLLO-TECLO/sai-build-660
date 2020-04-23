angular.module("siidfApp").service("logsService", function ($http, config) {	
		
		
	this.consultaActivos = function () {
	   return $http.get(config.baseUrl + "/logsController/consultaActivos");
	};
	
	this.consultaPorId = function (id) {
		   return $http.get(config.baseUrl + "/logsController/consultaPorId",
				   {params:{"id": id}});
		   
	};
	
	this.getPerfilesActivosPorLog = function (id) {
		   return $http.get(config.baseUrl + "/logsController/consultaPerfilesActivos",
				   {params:{"id": id}});
		   
	};
	this.getPerfilesNoActivosPorLog = function (id) {
		   return $http.get(config.baseUrl + "/logsController/consultaPerfilesNoAsignados",
				   {params:{"id": id}});
		   
	};
	
	this.agregarPerfil = function (logVO) {
		   return $http.post(config.baseUrl + "/logsController/agregarPerfil",logVO);
		   
	};
	this.eliminarPerfil = function (logVO) {
		   return $http.post(config.baseUrl + "/logsController/eliminarPerfil",logVO);
		   
	};
	this.crear = function (logVO) {
		   return $http.post(config.baseUrl + "/logsController/crear",logVO);
		   
	};
	this.actualizar = function (logVO) {
		   return $http.post(config.baseUrl + "/logsController/actualizar",logVO);
		   
	};	
	this.cambiarEstatus = function (id,accion) {
		   return $http.get(config.baseUrl + "/logsController/cambiarEstatus",
				   {params:{"id": id, "accion": accion}});
		   
	};
	this.consultaPorPerfil = function () {
		   return $http.get(config.baseUrl + "/logsController/consultaPorPerfil");
		   
	};
	this.consultaArchivosPorLog = function (id) {
		   return $http.get(config.baseUrl + "/logsController/consultaArchivosPorLog",
				   {params:{"id": id}});
		   
	};
	this.descargaArchivo = function (id,nombre) {
		   return $http.get(config.baseUrl + "/logsController/descargaArchivo",
				   {params:{"id": id, "nombre": nombre}});
		   
		   
		   return $http({
		        method: 'GET',
		        url: config.baseUrl + "/logsController/descargaArchivo",
		        params:{"id": id, "nombre": nombre},
		        dataType: "json",
		        header :{ "Content-type": "application/json"},
		        responseType: 'arraybuffer'
		    });
		   
	};
	
	

});
