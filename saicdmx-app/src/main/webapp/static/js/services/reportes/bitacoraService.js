angular.module("siidfApp").service('bitacoraService', function($http, config) {
	
	this.obtenerComponentes = function(){
		return $http.get(config.baseUrl + "/consultaComponentesBitacora");
	};
	
	this.obtenerConceptos = function(componenteId){
		return $http.get(config.baseUrl + "/consultaConceptosBitacora", {
			params : {
				"componenteId" : componenteId
			}
		});
	};
	
	this.consultaBitacoraCambios=function(parametrosBusquedaV){
		parametrosBusquedaVO={
				conponenteId : parametrosBusquedaV.conponente,
				conceptosId : parametrosBusquedaV.conceptos,
				fechaInicio: parametrosBusquedaV.fechaInicio,
				fehaFin : parametrosBusquedaV.fehaFin,
				componente:parametrosBusquedaV.componenteNombre,
				concepto:parametrosBusquedaV.conceptoNombre
		}
		return $http.post(config.baseUrl + "/consultaBitacoraCambios",parametrosBusquedaVO);
	};
	
	this.consultaBitacoraCambiosPorId=function(cambioId){
		return $http.get(config.baseUrl + "/consultaBitacoraCambiosPorIdCambio",{
			params : {
				"cambioId" : cambioId
			}
		});
	};
	
	
	
/*Metodos del controller de BitacoraAnterior */
	this.obtenerComponentesAnterior = function(){
		return $http.get(config.baseUrl + "/ListaComponentesBitacoraAnterior");
	};
		
	
	this.obtenerConceptosAnteriores = function(componenteId){
		return $http.get(config.baseUrl + "/ListaConceptosAnteriores", {
			params : {
				"componenteId" : componenteId
			}
		});
	};
	
	this.descargarExcel = function(nombre){
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/DescargarExcelBitacora",
	        params: {"nombre":nombre},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	this.consultaBitacoraCambiosAnteriores=function(parametrosBusquedaV){
		parametrosBusquedaVO={
				conponenteId : parametrosBusquedaV.conponente,
				conceptosId : parametrosBusquedaV.conceptos,
				fechaInicio: parametrosBusquedaV.fechaInicio,
				fehaFin : parametrosBusquedaV.fehaFin,
				componente:parametrosBusquedaV.componenteNombre,
				concepto:parametrosBusquedaV.conceptoNombre
		}
		return $http.post(config.baseUrl + "/consultaBitacoraCambiosAnteriores",parametrosBusquedaVO);
	};

});