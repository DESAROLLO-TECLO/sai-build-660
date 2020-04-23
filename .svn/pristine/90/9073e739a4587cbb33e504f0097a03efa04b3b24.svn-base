angular.module("siidfApp").service("expedienteService", function ($http, config) {	
	
	this.consultaInfraccionesDeposito = function(tipoParametro, valorParametro){
		return $http.get(config.baseUrl + "/consultaInfraccionparaExpediente", {
				params:{"tipoParametro":tipoParametro , "valorParametro":valorParametro}});
	}
	
	this.buscarCatalogoExpedientes = function(infracId){
		return $http.get(config.baseUrl + "/obtenerExpediente", {params:{"infracNum":infracId}});
	};
	
//	this.capturarExpedientes = function(infracNum, tipo, base64) {
//		return $http.get(config.baseUrl + "/capturaExpediente", 
//				{params:{"infracNum":infracNum, "tipo":tipo, "archivo":base64}});
//	};
	
	this.capturarExpedientes = function(expedienteVO) {
		return $http.post(config.baseUrl + "/capturaExpediente", expedienteVO);
	};
	
	this.borrarExpedientes = function (infracNum, tipo) {
		return $http.get(config.baseUrl + "/eliminarExpediente", 
				{params:{"infracNum":infracNum, "tipo":tipo}});
	};	
	
	this.mostrarExpedientes = function (infracNum, tipo) {
		return $http.get(config.baseUrl + "/mostrarExpediente", 
				{params:{"infracNum":infracNum, "tipo":tipo}});
	};	
	
	this.mostrarTodoExpedientes = function (infracNum) {
		return $http.get(config.baseUrl + "/mostrarTodoExpediente", 
				{params:{"infracNum":infracNum}});
	};	
	
	this.bajarExpedientes = function (infracNum, tipo) {
		return $http({     
	        method: 'GET',
	        url: config.baseUrl + "/bajarExpediente",
	        params: {"infracNum":infracNum, "tipo":tipo},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "jpg"
	        },
	        responseType: ''
	    });
	};	
	
	/*this.saveImpugna = function (impugnacionVO) {
		return $http.post(config.baseUrl + "/saveImpugnacion",impugnacionVO);
		
	};	
	
	this.obtenerInfracciones = function (id) {
		return $http.get(config.baseUrl + "/infraccionImpugna",
				{params:{ "id": id}});
	};*/
});