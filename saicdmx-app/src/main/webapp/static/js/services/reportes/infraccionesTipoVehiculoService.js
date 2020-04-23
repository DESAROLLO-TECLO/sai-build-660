angular.module("siidfApp").service("infraccionesTipoVehiculoService", function($http, config) {
	
	this.consultaInfraccionesTipoVehiculo = function (fechasVO){
		return $http.get(config.baseUrl + "/consultaInfraccionesTipoVehiculo", {
			params:{"fechaInicio": fechasVO.fechaInicio,
				    "fechaFin": fechasVO.fechaFin
				    }});
		};
		
		/*Metodo para Generar Reporte Excel */
		this.descargarExcel = function(nombre){
			return $http({
		        method: 'GET',
		        url: config.baseUrl + "/descargarInfraccionesTipoVehiculo",
		        params: {"nombre":nombre},
		        dataType: "json",
		        header :{ "Content-type": "application/json",
		        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
		        },
		        responseType: 'arraybuffer'
		    });
		}
	
});