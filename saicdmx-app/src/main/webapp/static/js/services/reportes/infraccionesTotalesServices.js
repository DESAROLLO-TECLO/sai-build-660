angular.module("siidfApp").service("infraccionesTotalesServices", function($http, config) {
	
	/* Metodos para infracciones mensuales */
	this.Depositos = function (){
		return $http.get(config.baseUrl + "/GetListaDepositos",{});
	};
	
	this.AniosInfracciones = function(){
		return $http.get(config.baseUrl + "/GetListaAnios",{});
	};
	
	this.consultaInfraccionesMensuales = function (parametrosBusquedaV){
		parametrosBusquedaVO={
				deposito: parametrosBusquedaV.depositoVO,
				anio: parametrosBusquedaV.anioVO,
				mes : parametrosBusquedaV.mesVO
		   }
		return $http.post(config.baseUrl + "/consultaInfraccionesMensuales",parametrosBusquedaVO);
	};
	
	this.descargarExcel = function(nombre){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/DescargarExcelTotalInfracMensuales",
	        params: {"nombre":nombre},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
});