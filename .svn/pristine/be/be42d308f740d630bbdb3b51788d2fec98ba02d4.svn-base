angular.module("siidfApp").service("radaresService", function ($http, config) {	
	
	this.buscaArchivoEnProceso = function () {
		return $http.get(config.baseUrl + "/buscaArchivoEnProceso",{});
	};
	
	this.buscaCatArchivoTipoActivos = function () {
		return $http.get(config.baseUrl + "/buscaCatArchivoTipoActivos",{});
	};
	
	this.obtenerAnioSalarioMinimo = function () {
		return $http.get(config.baseUrl + "/obtenerAnioSalarioMinimo",{});
	};
	
	this.cargaArchivoComplementacion= function(archivoVO, file){
		var fd = new FormData();
        fd.append("archivoVO", angular.toJson(archivoVO));
	    fd.append("file", file);
	    
	    return $http({
	        method: 'POST',
	        url: config.baseUrl + "/cargaArchivoComplementacion",
	        headers: {'Content-Type': undefined},
	        data: fd,
	        transformRequest: angular.identity
	    });
	};
	
	this.descargarExcelErrores= function(errores){
		console.log('aqui van errores');
		
		var data=[];
//		console.log(error);
//		console.log('Tamaño es: '+errores.length);
		for(var i=0;i<errores.length;i++){
			
			var error={ 
					apellidomat:"",apellidopat:"",
					artid:"",calle:"",
					colonia:"",cp:"",
					delegacion:"",entidad:"",
					fecha:"",hora:"",
					marca:"",modelo:"",
					nombre:"",numext:"", 
					numint:"",nummotor:"", 
					numserie:"",oficialplaca:"",
					origenplaca:"",placa:"",
					submar:"",tdskuid:"",
					telefono:"",tipodeteccion:"", 
					telefono:"",velocidaddetectada:"",
					ut:"",
				};
			
			if(errores[i].APELLIDOMAT=== undefined){
			}else{
				error.apellidomat=errores[i].APELLIDOMAT;
			}
			
			if(errores[i].APELLIDOPAT=== undefined){
			}else{
				error.apellidopat=errores[i].APELLIDOPAT;
			}
			
			if(errores[i].VELOCIDADDETECTADA=== undefined){
			}else{		
				error.velocidaddetectada=errores[i].VELOCIDADDETECTADA;
			}
			
			if(errores[i].CALLE=== undefined){
			}else{
				error.calle=errores[i].CALLE;
			}
			
			if(errores[i].COLONIA=== undefined){
			}else{
				error.colonia=errores[i].COLONIA;
			}
			
			if(errores[i].CP=== undefined){
			}else{
				error.cp=errores[i].CP; 
			}
			
			if(errores[i].DELEGACION=== undefined){
			}else{
				error.delegacion=errores[i].DELEGACION;
			}
			
			if(errores[i].ENTIDAD=== undefined){
			}else{
				error.entidad=errores[i].ENTIDAD; 
			}
			
			if(errores[i].FECHA=== undefined){
			}else{
				error.fecha=errores[i].FECHA;
			}
			
			if(errores[i].HORA=== undefined){
			}else{
				error.hora=errores[i].HORA;
			}
			
			if(errores[i].MARCA=== undefined){
			}else{
				error.marca=errores[i].MARCA;
			}
			
			if(errores[i].MODELO=== undefined){
			}else{
				error.modelo=errores[i].MODELO;
			}
			
			if(errores[i].NOMBRE=== undefined){
			}else{
				error.nombre=errores[i].NOMBRE;
			}
			
			if(errores[i].NUMEXT=== undefined){
			}else{
				error.numext=errores[i].NUMEXT;
			}
			
			if(errores[i].NUMINT=== undefined){
			}else{
				error.numint=errores[i].NUMINT;
			}
			
			if(errores[i].NUMMOTOR=== undefined){
			}else{
				error.nummotor=errores[i].NUMMOTOR;
			}
			
			if(errores[i].NUMSERIE=== undefined){
			}else{
				error.numserie=errores[i].NUMSERIE;
			}
			
			if(errores[i].OFICIALPLACA=== undefined){
			}else{
				error.oficialplaca=errores[i].OFICIALPLACA;
			}
			
			if(errores[i].ORIGENPLACA=== undefined){
			}else{
				error.origenplaca=errores[i].ORIGENPLACA;
			}
			
			if(errores[i].PLACA=== undefined){
			}else{
				error.placa=errores[i].PLACA; 
			}
			
			if(errores[i].SUBMAR=== undefined){
			}else{
				error.submar=errores[i].SUBMAR; 
			}
			
			if(errores[i].TDSKUID=== undefined){
			}else{
				error.tdskuid=errores[i].TDSKUID;
			}
			
			if(errores[i].TELEFONO=== undefined){
			}else{
				error.telefono=errores[i].TELEFONO;
			}
			
			if(errores[i].TIPODETECCION=== undefined){
			}else{
				error.tipodeteccion=errores[i].TIPODETECCION;
			}
			
			if(errores[i].ARTID=== undefined){
			}else{
				error.artid=errores[i].ARTID; 
			}
			
			if(errores[i].UT=== undefined){
			}else{
				error.ut=errores[i].UT;
			}
			
			data.push(error);
//			console.log(error);
		}
		console.log("hasta aqui bien")
		var json={'deteccionesErroeas':data};
		console.log("hasta aqui bien2")
		console.log(json);
		console.log(config.baseUrl + "/generarArchivoZIP_RAD_ERR");
		
		
		json = angular.toJson(json);
	
//        return $http.post(config.baseUrl + "/generarArchivoZIP_RAD_ERR",json
//        		);

		
		return $http({
	        method: 'POST',
	        url: config.baseUrl + "/generarArchivoZIP_RAD_ERR" ,
	        data: json ,
	        dataType: "json",
	        header :{ "Content-type": "application/json",
	        			"Accept"    : "vnd.openxmlformats-officedocument.spreadsheetml.sheet"
	        },
	        responseType: 'arraybuffer'
	    });
//		console.log("hasta aqui bien4")
	
	
        return $http.post(config.baseUrl + "/obtenerDeteccionesCanceladas",json);

	
	
	}
	
	this.getDeteccionesCanceladas = function (parametrosVO) {        
        var json =   angular.toJson(parametrosVO);
        return $http.post(config.baseUrl + "/obtenerDeteccionesCanceladas",json);
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