angular.module("siidfApp").service("infraccionService", function ($http, config) {	

	var listaInfraccionesCons = [];
	var infracModified = null;
	var infracDepModified = null;
	var infracExp = null;
	var infracMarc = null;
	var rowsperpage;

	this.setListaInfraccionesCons = function(lista) {
		listaInfraccionesCons = lista;
	};
	this.getListaInfraccionesCons = function() {
		return listaInfraccionesCons;
	}
	this.getInfracModified = function(){
		return infracModified;
	}
	this.setInfracModified = function(infrac){
		infracModified = infrac;
	}
	this.getInfracDepModified = function(){
		return infracDepModified;
	}
	this.setInfracDepModified = function(infrac){
		infracDepModified = infrac;
	}
	this.getInfracExp = function(){
		return infracExp;
	}
	this.setInfracExp = function(infrac){
		infracExp = infrac;
	}
	this.getInfracMarc = function(){
		return infracMarc;
	}
	this.setInfracMarc = function(infrac){
		infracMarc = infrac;
	}
	this.getRowsPerPage = function(){
		return rowsperpage;
	}
	this.setRowsPerPage = function(rows){
		rowsperpage =  rows;
	}
	
	this.buscarInfracciones = function (tipoBusqueda, valor) {//R
		return $http.get(config.baseUrl + "/infracciones", {
			params:{
				"tipoBusqueda": tipoBusqueda,
				"valor": valor
				}
		});
	};
	
	this.buscarInfraccionDetallada = function (id) {
		this.infracModified = false;
		return $http.get(config.baseUrl + "/infraccionAllData", {
			params:{
				"id" : id
			}
		});
	};
	
	this.buscarTipoFiltroParaInfracciones = function () {
		return $http.get(config.baseUrl + "/infraccionesTipoBusqueda");
	};
	
	this.modificaEnDeposito = function (mod) {
		return $http.post(config.baseUrl + "/modificaInfraccionEnDeposito", mod); 
	};
	
	this.marcadoInfraccion = function (marc) {
		return $http.post(config.baseUrl + "/suspInfraccion", marc); 
	};
	
	this.countInfracDigTodDiaPorStatus = function (status) {//R
		return $http.get(config.baseUrl + "/countInfracDigTodDiaPorStatus", {
			params:{
				"status": status
				}
		});
	};
	
	this.getInfraccionesDigTodoDiaPorStatus = function (status) {//R
		return $http.get(config.baseUrl + "/infraccionesDigitalesTodoDiaPorStatus", {
			params:{
				"status": status
				}
		});
	};
	
	this.cargaDigWeb = function (carga) {
		return $http.post(config.baseUrl + "/cargaDigWeb", carga); 
	};

	this.buscarExcepcionesEnArticulos = function (articulo) {
		return $http.get(config.baseUrl + "/buscarExcepcionesEnArticulos", {
			params:{
				"articulo": articulo
				}
		});
	};
	
	this.reporteConsulta = function(nci){
		return $http({
			method : 'GET',
			url : config.baseUrl + "/reporteConsultaInfraccion"
			,params:{
				"nci" : nci
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