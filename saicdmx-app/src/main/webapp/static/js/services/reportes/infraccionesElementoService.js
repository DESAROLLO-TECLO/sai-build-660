angular.module("siidfApp").service("infraccionesElementoService", function($http, config) {
	
	/*Metodo que trae el total de infracciones por elemnto */
	this.consultaInfraccionesEmpleado = function (parametrosBusquedaV){
		parametrosBusquedaVO={ //placasOficiales,idPlacasOficiales
				placasOficiales: parametrosBusquedaV.placasOfiales,
				fechaInicio: parametrosBusquedaV.fechaInicio,
				fechaFin : parametrosBusquedaV.fehaFin,
				idPlacasOficiales : parametrosBusquedaV.idPlacasOficiales
		}
		return $http.post(config.baseUrl + "/consultaInfraccionesporElemento",parametrosBusquedaVO);
	};
	
	/*Metodo para descargar el excel*/
	this.descargarExcelEmpleado = function(nombre){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/descargarExcelInfraccionesporElemento",
	        params: {"nombre":nombre},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
});