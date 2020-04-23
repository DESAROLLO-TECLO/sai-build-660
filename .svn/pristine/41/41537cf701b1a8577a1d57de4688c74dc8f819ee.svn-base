angular.module("siidfApp").service("infraccionesAnualesService", function($http, config) {
	
	this.consultaAnios = function(){
		return $http.get(config.baseUrl + "/getListaAnios",{});
	};
	
	this.consultaInfraccionesAnual = function (anioBuscar){
		return $http.get(config.baseUrl + "/consultaInfraccionesAnuales", {
			params : {
				"anio" : anioBuscar
			}
		});
	};

	this.consultaInfraccionesTotalAnual = function (anioBuscar){
		return $http.get(config.baseUrl + "/consultaInfraccionesTotalAnual", {
			params : {
				"anio" : anioBuscar
			}
		});
	};
	
	this.descargarExcel = function(nombre){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/DescargarExcelInfraccionesAnuales",
	        params: {"nombre":nombre},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
});