angular.module("siidfApp").service("radarArchivoProcesadosService", function ($http, config) {	
	
	this.comboTipoArchivo = function(){

		return $http.get(config.baseUrl + "/getComboTipoArchivo");	
	};
	this.comboTipoFecha = function(){

		return $http.get(config.baseUrl + "/getComboFechasBusqueda");	
	};
	
	this.comboTipoEstatus = function(){
		
		return $http.get(config.baseUrl + "/getComboArchivoEstatus");	
	};
	
	/*
	 * consulta archivo Origen
	 */
	
	this.comboTipoEstatusProcesoArchivo = function(){
	return $http.get(config.baseUrl + "/consultaCatalogoEstatus",{});
	};
	
	this.comboOrigenProceso = function(){
		return $http.get(config.baseUrl + "/getComboOrigenProceso");
	};
	
	this.comboTipoRadar = function(tipoRadar){
		return $http.get(config.baseUrl + "/getComboTipoRadar", {params:{"tipoArchivo":tipoRadar}});
	};
	
	this.consulta = function (origenProceso, tipoProceso, tipoDeteccion, tipoArchivo, tipoPersona, tipoFecha, startDate, endDate) {
		
		return $http.get(config.baseUrl + "/buscarArchivosProcesados", 
				{params:{
					"origenProceso": origenProceso, 
					"tipoProceso": tipoProceso, 
					"tipoDeteccion": tipoDeteccion, 
					"tipoArchivo": tipoArchivo, 
					"tipoPersona": tipoPersona, 
					"tipoFecha": tipoFecha,
					"fechaInicio": startDate,
					"fechaFin": endDate}});
	};
	
	this.consultaAll = function (tipoArchivo, origenProceso) {

		return $http.get(config.baseUrl + "/buscarArchivosProcesadosAll", 
				{params:{"tipoArchivo": tipoArchivo, "origenProceso": origenProceso}});
	};
	
	
	 ///Consulta Archivo Origen Manuel
	
	
	
	this.consultaArchivoOrigenTodo = function () {
		return $http.get(config.baseUrl + "/consultaArchivoOrigenTodo", {
			
		});
	}
	
	
	
	
	
	this.consultaArchivoOrigen = function (switchRangoFecha, periodoFecha, fechaInicio, fechaFin, estatusProceso,tipoArchivo) {
		return $http({
			method : 'POST',
			url : config.baseUrl + "/consultaArchivoOrigen",
			params : {
				"switchRangoFecha"	: switchRangoFecha, 
				"periodoFecha"		: periodoFecha,
				"fechaInicio"		: fechaInicio,
				"fechaFin"			: fechaFin,  
				"estatusproceso"	: estatusProceso,
				"tipoArchivo"		: tipoArchivo,
			}
		});
	
	}
	
	
	//Buscar Detecciones por ID
	
	this.buscarArchivoDeteccion = function (idArchivo) {
		//this.infracModified = false;
		return $http.get(config.baseUrl + "/consultaArchivoDetecciones", {
			params:{
				"idArchivo" : idArchivo
			}
		});
	};
	
	
	//Buscar Detecciones por ID para archivos
	
	this.cancelaArchivoOrigen = function (idArchivo) {
		//this.infracModified = false;
		return $http.get(config.baseUrl + "/cancelaArchivoOrigen", {
			params:{
				"idArchivo" : idArchivo
			}
		});
	};
	
	
	
//Combo Archvio Estatus
	this.comboTipoEstatusArchivo = function(){
		
		return $http.get(config.baseUrl + "/getComboArchivoEstatusProceso");	
	};
	
/// Combo Tipo de archivo
this.comboTipoArchivoOrigen = function(){
		
		return $http.get(config.baseUrl + "/getComboArchivoOrigenTipo");	
	};
	
//Descarga excel de detecciones
	
	this.obtenerReportExcel = function(idArchivo) {
		return $http({
			method : 'GET',
			url : config.baseUrl + "/reporteArchivoDetecciones",
			
		params:{"idArchivo":idArchivo},

			dataType : "json",
			header : {
				"Content-type" : "application/json",
				"Accept" : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType : 'arraybuffer'
		});
	}
	
	//Obtiene Reporte excel origen
	this.obtenerReportExcelOrigen = function(opcion,busqueda,switchRangoFecha, periodoFecha, fechaInicio, fechaFin, estatusProceso,tipoArchivo) {
		return $http({
			method : 'POST',
			url : config.baseUrl + "/reporteArchivoOrigenExcel",
			params : {
				"opcion"	: opcion, 
				"busqueda"	: busqueda, 
				"switchRangoFecha"	: switchRangoFecha, 
				"periodoFecha"		: periodoFecha,
				"fechaInicio"		: fechaInicio,
				"fechaFin"			: fechaFin,  
				"estatusproceso"	: estatusProceso,
				"tipoArchivo"		: tipoArchivo,
			},

			dataType : "json",
			header : {
				"Content-type" : "application/json",
				"Accept" : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
			},
			responseType : 'arraybuffer'
		});
	}
	
	
	
	
//Secarga archivo txt Original	
	this.descargaArchivoOrigen = function (idArchivo) {
		   return $http.get(config.baseUrl + "/descargaArchivoOrigen",
				   {params:{"idArchivo": idArchivo}});
		   
		   
		   return $http({
		        method: 'GET',
		        url: config.baseUrl + "/descargaArchivoOrigen",
		        params:{"id": idArchivo},
		        dataType: "json",
		        header :{ "Content-type": "application/json"},
		        responseType: 'arraybuffer'
		    });
		   
	};
	
	
	
	
	
	
	this.confirmarLiberarArchivo = function (archivoId){
	
		return $http.get(config.baseUrl + "/liberarArchivo", 
				{params:{"archivoId": archivoId}});

	};
	
	
	this.generarArchivoZIP = function (archivoId, tipoZIP){
		
		return $http.get(config.baseUrl + "/generarArchivoZIP", 
				{params:{"archivoId": archivoId, "tipoZIP":tipoZIP}});

	};
	

	this.descargarArchivos = function(archivoId, tipoZIP){
		 return $http({     
		        method: 'GET',
		        url: config.baseUrl + "/descargarArchivosZIP",
		        params: {"archivoId": archivoId, "tipoZIP":tipoZIP},
		        dataType: "json",
		        header :{ "Content-type": "application/octet-stream",
		        			"Accept"    : "zip"
		        },
		        responseType: 'arraybuffer'
		    });
	};
	
	
	this.complementarArchivo = function (archivoId){
		
		return $http.get(config.baseUrl + "/complementarArchivo", 
				{params:{"archivoId": archivoId}});

	};
	
	/*UTILITIES*/
	this.download = function(data, headers) {
		var arr = data;
        var byteArray = new Uint8Array(arr);
        var a = window.document.createElement('a');
        a.href = window.URL.createObjectURL(
    		new Blob([byteArray], { type: 'application/octet-stream' })
        );
        a.download = headers('filename');
        document.body.appendChild(a);
        a.click();
        document.body.removeChild(a);
	}

	this.enviarArchivo = function(archivoId){
		return $http.get(config.baseUrl + "/enviarArchivo", 
				{params:{
					"archivoId": archivoId
					}});
	}
	
});


