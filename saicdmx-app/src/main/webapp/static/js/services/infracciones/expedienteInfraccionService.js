angular.module("siidfApp").service("expedienteInfraccionService", function($http, config){
	
	this.consultaExpediente = function(infraccionNumero){
		return $http.get(config.baseUrl + "/infraccionExpediente",{ 
			params : {
				'valor' : infraccionNumero
			}
		});
	}
	
	this.buscarImagenesIngreso = function (infraccionNumero){
		return $http.get(config.baseUrl + "/imagenesIngreso",{
			params : {
				'valor' : infraccionNumero
			}
		})
	}
	
	this.buscarDirectorioDigitalizacion = function (infraccionNumero, anvRev){
		return $http.get(config.baseUrl + "/directorioDigitalizacion",{
			params : {
				'valor' : infraccionNumero,
				'anvRev' : anvRev
			}
		})
	}
	
	this.buscarImagenesHandHeld = function (infraccionNumero){
		return $http.get(config.baseUrl + "/imagenesHandHeld",{
			params : {
				'valor' : infraccionNumero
			}
		})
	}
	
	this.consultaFotoPorNombre = function (nombre,tipo){
		if(tipo=="INFRACCION"){
			return $http.get(config.baseUrl + "/foto",{
				params : {
					'nombre' : nombre
				}
			});
		}else{
			return $http.get(config.baseUrl + "/imagenIngreso",{
				params : {
					'nombre' : nombre
				}
			});
		}
	}
		
	this.getStatusValidacionImagenes = function (infrac){
		return $http.get(config.baseUrl + "/validaImagen",{
			params : {
				'infracNum' : infrac
			}
		})
	}
	
	this.setStatusValidacionImagenes = function (infExp) {
		return $http.post(config.baseUrl + "/spValidaImagenesExpediente", infExp); 
	};
	
	this.descargaImagenExpediente = function (url){
		return $http.get(config.baseUrl + "/descargaImagenExpediente",{
			params : {
				'url' : url
			}
		})
	}
		
	this.descargaImagenExpediente = function(ruta){
		return $http({
			method : 'GET',
			url : config.baseUrl + "/descargaImagenExpediente",
			params:{
				"ruta" : ruta
			},
		    dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "jpg"
	        },
	        responseType: 'arraybuffer'
	    });
	};	
	
	this.generaReporteExpediente = function(infracNum, articulo, motivacion, listaImgRepExp){
		//var jsonInfraccion = angular.toJson(infraccion);
		var jsonlistaImgRepExp = angular.toJson(listaImgRepExp);
		
		return $http({
			method : 'GET',
			url : config.baseUrl + "/reporteExpedienteClasico",
			params:{
				"infracNum" : infracNum,
				"articulo" : articulo,
				"motivacion" : motivacion,
				"listaImgRepExp" : jsonlistaImgRepExp
			},
			dataType: "json",
	    	header :{ "Content-type": "application/json",
	    			"Accept"    : "pdf"
	        },
	        responseType: 'arraybuffer'
	    });
	}
	
	this.downloadfile = function(file, fileName) {
		var url = window.URL || window.webkitURL;
		var blobUrl = url.createObjectURL(file);
		var a = document.createElement('a');
		a.href = blobUrl;
		a.target = '_blank';
		a.download = fileName;
		document.body.appendChild(a);
		a.click();
	}
});