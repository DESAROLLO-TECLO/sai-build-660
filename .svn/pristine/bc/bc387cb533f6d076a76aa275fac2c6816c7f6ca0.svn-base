angular.module("siidfApp").service("infraccionArticuloTotalService", function($http, config) {
	
	this.consultaInfraccionesArticulo=function(parametrosBusquedaV){
		parametrosBusquedaVO={
				id: parametrosBusquedaV.id,
				articulo:parametrosBusquedaV.articulo,
				fechaInicio: parametrosBusquedaV.fechaInicio,
				fechaFin : parametrosBusquedaV.fehaFin
		}
		return $http.post(config.baseUrl + "/consultaInfraccionesArticuloTotal",parametrosBusquedaVO);
	};
	
	
	this.descargarExcel = function(nombre){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/descargarExcelTotalporArticulo",
	        params: {"nombre":nombre},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
});