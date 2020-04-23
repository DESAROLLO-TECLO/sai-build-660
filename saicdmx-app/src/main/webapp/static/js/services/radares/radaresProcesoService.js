angular.module("siidfApp").service("radaresProcesoService", function ($http, config) {
	
	this.buscarRadarArchivoEnProceso = function(){
		return $http.get(config.baseUrl + "/buscarRadarArchivoEnProceso",{});
	};
	
	this.buscaEststusRadarArchivoEnProceso = function (radarArchivoId){
		return $http.get(config.baseUrl + "/buscaEststusRadarArchivoEnProceso",{params : {"radarArchivoId": radarArchivoId}});
	};
	
	this.cancelaLoteEnProceso = function (radarArchivoId){
		return $http.get(config.baseUrl + "/cancelaLoteEnProceso",{params : {"radarArchivoId": radarArchivoId}});
	};
	
	this.listaDeteccionRepartoValida = function (radarArchivoId){
		return $http.get(config.baseUrl + "/listaDeteccionValidas",{params : {"radarArchivoId": radarArchivoId}});

	};
	
	this.buscarAllDeteccionesRAD = function(lote){
		return $http.get(config.baseUrl + "/buscarAllDeteccionesRAD",
				{params:{"lote": lote}});
	}
	
	this.listaDeteccionRepartoInvalida = function (radarArchivoId){
		return $http.get(config.baseUrl + "/listaDeteccionInvalidas",{params : {"radarArchivoId": radarArchivoId}});

	};
	
	this.recomplementCentroRepart = function (radarArchivoId){
		return $http.get(config.baseUrl + "/recomplementCentroRepart",{params : {"radarArchivoId": radarArchivoId}});
	};
	this.recomplementaCodigoPostal2 = function(ids,newcp){
		return $http.get(config.baseUrl + "/complementacionRADDeCP",
				{params:{"ids": ids, "newcp":newcp}});
	}
	
	this.codigoPostalReasignado = function (radarArchivoId){
		return $http.get(config.baseUrl + "/codigoPostalReasignado",{params : {"radarArchivoId": radarArchivoId}});
	};
	this.codigoPostalReasignado2 = function (radarArchivoId){
		return $http.get(config.baseUrl + "/codigoPostalReasignado2",{params : {"radarArchivoId": radarArchivoId}});
	};
	
	this.cancelarProceso = function (radarArchivoId, motivo){
		return $http.get(config.baseUrl + "/cancelarProceso",{params : {"lote": radarArchivoId, "motivo": motivo}});
	};
	
	this.modificaThisCP = function (radarArchivoId, Cp, ids){
		return $http.get(config.baseUrl + "/cambiaThisCP",{params : {"radarArchivoId": radarArchivoId, "Cp": Cp, "ids": ids}});
	}
	
	this.radarCambiaCpDesh = function (idCP, radarArchivoId){
		return $http.get(config.baseUrl + "/radarCambiaCpDesh",{params : {"idCP": idCP, "radarArchivoId": radarArchivoId }});
	};
	
	this.radarCambiaCpHabil = function (idCP, CP, radarArchivoId){
		return $http.get(config.baseUrl + "/radarCambiaCpHabil",{params : {"idCP": idCP, "CP": CP, "radarArchivoId": radarArchivoId }});
	};
	
	this.asignarLineasCapt = function (radarArchivoId){
		return $http.get(config.baseUrl + "/asignarLineasCapt",{params : {"radarArchivoId": radarArchivoId }});
	};
	
	this.actualizaCertificadoValido = function(radarArchivoId,empId,placaOficial,empRFC,pwd){
		return $http.get(config.baseUrl + "/actualizaCertificadoValido",{
			params : {
				"radarArchivoId": radarArchivoId,
				"empId": empId,
				"placaOficial": placaOficial,
				"empRFC": empRFC,
				"pwd": pwd
			}
		});
	}
	
	this.vigenciaUsuario = function(placaOficial){
		return $http.get(config.baseUrl + "/validarUsuarioActivo",{params : {"placaOficial": placaOficial }});
	}
	
	this.validaPassCert = function(placaOficial, pwd){
		return $http.get(config.baseUrl + "/radarValidaPassCert",{
			params : {
				"placaOficial": placaOficial,
				"pwd": pwd
			}
		});
	}
});