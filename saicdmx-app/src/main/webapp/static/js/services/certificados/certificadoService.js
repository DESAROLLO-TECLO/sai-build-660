angular.module("siidfApp").service("certificadoService", function ($http, config) {	
	
	var listaCertificados = {};
	var listaCertificadosConsulta = {};
	
	this.setListaCertificados = function(lista) {
		listaCertificados = lista;
	};
	
	this.getListaCertificados = function() {
		return listaCertificados;
	}

	this.setListaCertificadosConsulta = function(listaC) {
		listaCertificadosConsulta = listaC;
	};
	
	this.getListaCertificadosConsulta = function() {
		return listaCertificadosConsulta;
	}
	
	
	this.buscarPerfiles = function (codigo) {
		return $http.get(config.baseUrl + "/certificadosController/certificados/buscarPerfiles",
				{params:{"codigo": codigo}});
	};
	 
	this.buscarUsuarios = function (tipoBusqueda,tipoBusqueda2, valor) {
		return $http.get(config.baseUrl + "/certificadosController/certificados/buscarUsuarios", 
					{params:{"tipoBusqueda": tipoBusqueda, "tipoBusqueda2": tipoBusqueda2,"valor": valor}});
		};

	this.saveCertificado = function (files,placa,pwd) {

		var fd = new FormData();
	    fd.append("placa", angular.toJson(placa));
	    fd.append("pwd", angular.toJson(pwd));
	 
		for (var i = 0 ; i < files.length ; i ++){
			console.log(files[i]);
	    	   fd.append('files', files[i]);
	    	}
		    
			 return $http({
			        method: 'POST',
			        url: config.baseUrl + "/certificadosController/certificados/save",
			        headers	:{'Content-type':undefined},
					data: fd,
					transformRequest : angular.identy
			    });
			 
		};	
		
		
	this.actualizaRFC = function (placa, rfc) {
		return $http.get(config.baseUrl + "/certificadosController/certificados/rfc", 
			{params:{"placaOficial": placa, "rfc": rfc}});
			};	
	
	this.validaExisteCert = function (placa) {	
		return $http.get(config.baseUrl + "/certificadosController/certificados/placaOficial",
				{params:{"placaOficial": placa}});
		
	};
			
	this.validaPwd = function (placa,pwd) {	
		return $http.get(config.baseUrl + "/certificadosController/certificados/valida",
				{params:{"placaOficial": placa,"pwd": pwd}});
		
	};	
	
	this.updateFiles = function (files,placa,pwd) {
	
		var fd = new FormData();
	    fd.append("placa", angular.toJson(placa));
	    fd.append("pwd", angular.toJson(pwd));
	 
		for (var i = 0 ; i < files.length ; i ++){
			console.log(files[i]);
	    	   fd.append('files', files[i]);
	    	}

		 return $http({
		        method: 'POST',
		        url: config.baseUrl + "/certificadosController/certificados/update",
		        headers	:{'Content-type':undefined},
				data: fd,
				transformRequest : angular.identy
		    });
		 
	};	
	
	this.buscarPorFechas = function (tipoBusqueda,validado,paramBusqueda,fechaInicio,fechaFin) {	
		return $http.get(config.baseUrl + "/certificadosController/certificados/buscarPorFechas",
		   {params:{"tipoBusqueda": tipoBusqueda,
					"validado": validado,
					"paramBusqueda": paramBusqueda,
					"fechaInicio": fechaInicio,
					"fechaFin": fechaFin}});
		
	};	
	

});
