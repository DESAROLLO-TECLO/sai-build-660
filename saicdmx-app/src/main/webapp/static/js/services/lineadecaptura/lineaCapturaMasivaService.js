angular.module("siidfApp").service("lineaCapturaMasivaService", function ($http, config) {

	/*Valida Reasignaciones Pendientes*/
	this.validarReasignacionesPendientes = function () {
		return $http.get(config.baseUrl + "/validarReasignacionesPendientes");
	};
	
	/*Carga Lote de Folios*/
	this.cargarFolioInfracciones = function (file, fEmision, tipoDescuento) {
		var fd = new FormData();
	    //fd.append("placa", angular.toJson(placa));
	    //fd.append("pwd", angular.toJson(pwd));
	    fd.append('file', file);
	    
		/*for (var i = 0 ; i < files.length ; i ++){
			console.log(files[i]);
	    	   fd.append('files', files[i]);
	    }*/
	    
		return $http({
			method: 'POST',
			url: config.baseUrl + "/cargaArchivoReasignacion",
			headers	:{'Content-type':undefined},
			data: fd,
			params: {
					"fEmision"	:	fEmision,
				"tipoDescuento"	:	tipoDescuento
			},
			transformRequest : angular.identy
		});
	};
	
	/*Reasignar lote de folios*/
	this.reasignarLote = function () {
		return $http.get(config.baseUrl + "/reasignarLoteFolio");
	};
	
	/*Cancelar lote de folios*/
	this.cancelarLote = function () {
		return $http.get(config.baseUrl + "/cancelarLoteFolio");
	};
	
	/*Consultar lotes de folios*/
	this.consultarLotes = function (fechaInicio, fechaFin, cbCampoBusqueda, idLote, nameLote, cbTipoFecha, cbEstatusLotes) {
		return $http.get(config.baseUrl + "/consultarLotes", 
				{params:{
					  "fechaInicio"   : fechaInicio, 
					   "fechaFin"     : fechaFin,
					"cbCampoBusqueda" : cbCampoBusqueda,
					    "idLote"      : idLote,
					   "nameLote"     : nameLote,
					  "cbTipoFecha"   : cbTipoFecha,
					"cbEstatusLotes"  : cbEstatusLotes
				}});
	};
	
	/*Descargar reporte del lote*/
	this.descargarLoteReporte = function (lote) {
		return $http({
	        method: 'GET',
	        url: config.baseUrl + "/descargaReporteLote",
	        params: {"lote":lote},
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
	};
	
	/*UTILITIES*/
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