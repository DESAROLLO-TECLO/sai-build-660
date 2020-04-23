angular.module("siidfApp").service("consultaBloqueohhService", function($http, config) {
 
	this.obtenerTipoBloqueo = function(){
		return $http.get(config.baseUrl + "/handhelds/tipoBloqueo");
	}

	this.obtenerTipoEstatus = function(){
		return $http.get(config.baseUrl + "/handhelds/estatushh");
	}
	
	this.consultaInformacionHh = function(bloqueohhVO){
 
 		return $http.get(config.baseUrl + "/handhelds",
		{
			params : {
			"estatusBloqueo":  bloqueohhVO.estatusBloqueo  != "" || bloqueohhVO.estatusBloqueo  == 0  ? bloqueohhVO.estatusBloqueo  : "null"  , 
			"placaOficial"	:  bloqueohhVO.placaOficial != ""  ?  bloqueohhVO.placaOficial.toLowerCase()  : "null"  , 
			"tipoBloqueo"	:  bloqueohhVO.tipoBloqueo  != ""  ? bloqueohhVO.tipoBloqueo  : "null" , 
			"numeroSeriehh"	:  bloqueohhVO.numeroSeriehh != ""  ? bloqueohhVO.numeroSeriehh  : "null"  , 
			"fechaInicio"	:  bloqueohhVO.fechaInicio  != ""  ? bloqueohhVO.fechaInicio  : "null"  , 
			"fechaFin"		:  bloqueohhVO.fechaFin != ""  ? bloqueohhVO.fechaFin  : "null" 
			}
		
 		});
	}
	
	this.obtenerReporteExcel = function(){
		
	    return $http({
	        method: 'GET',
	        url: config.baseUrl + "/handhelds/reporte",
	        params: {},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
 	}
	
});
