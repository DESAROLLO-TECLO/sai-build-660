angular.module('siidfApp').service("evaluacionesService", function ($http, config) {
	
	this.consultaEvaluaciones = function(tipoBusqueda, valorBusqueda, startDate, endDate){
		return $http.get(config.baseUrl + "/getEvaluaciones", { 
				params : {
					"tipoBusqueda" : tipoBusqueda, 
					"valor" : valorBusqueda, 
					"fhIni" : startDate, 
					"fhFin" : endDate
				}
		}
	)};
	
	this.consultaEvaluacionUsuarios = function(idEvaluacion){
		return $http.get(config.baseUrl + "/evaluacionUsuarios", {
			params : {
				"idEvaluacion" : idEvaluacion
			}
		}
	)};
	
	this.excelEvaluaciones = function(tipoBusqueda, valorBusqueda, startDate, endDate){
		return $http({
			method: 'GET',
	        url: config.baseUrl + "/evaluacionesReporte",
	        params : {
				"tipoBusqueda" : tipoBusqueda, 
				"valor" : valorBusqueda, 
				"fhIni" : startDate, 
				"fhFin" : endDate
			},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
		
	};
	
	this.excelEvaluacionUsuarios = function(idEvaluacion){
		return $http({
			method: 'GET',
	        url: config.baseUrl + "/evaluacionUsuariosReporte",
	        params: {"idEvaluacion": idEvaluacion},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
		
	};
	
	this.downloadfile = function(file, fileName) {
        var url = window.URL || window.webkitURL;
        var blobUrl = url.createObjectURL(file);
        var a = document.createElement('a');
        a.href = blobUrl;
        a.target = '_blank';
        a.download = fileName;
        document.body.appendChild(a);
        $timeout(function() {
            a.click();
        }, 100);
    }
});