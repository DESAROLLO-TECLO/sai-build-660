angular.module("siidfApp").service("totalInfraccionesMensualService", function($http, config) {
	
	this.consultaInfracciones = function (fechaInicio,fechaFin){
		return $http.get(config.baseUrl + "/consultaTotalInfraccMensuales",{
			params:{"fechaInicio":fechaInicio,
				    "fechaFin":fechaFin}
		});
	};
	
	this.descargarExcel = function(nombre){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/descargarExcelInfraccionesMensualesTotal",
	        params: {"nombre":nombre},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	/*this.ConsultaEntroDeposito = function(NoInfraccion,NomDeposito,causaIngreso,tipoIngreso){
			return $http.get(config.baseUrl + "/EntradaDeposito", {
				params:{"NoInfraccion": NoInfraccion,
					    "NomDeposito":NomDeposito,
					    "causaIngreso":causaIngreso,
					    "tipoIngreso":tipoIngreso}});
			};*/
});//fin Service