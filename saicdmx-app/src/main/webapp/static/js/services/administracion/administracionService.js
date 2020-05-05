angular.module("siidfApp").service("administracionService", function($http, config) {
 
	/* USUARIOS */
	
	var listaUsuarios = [];
	var listaParams = {};
	var listaPerfiles = [];
	var listaPermisos = [];
	var estatus = null;
	var rowsperpage = 0;
	
	this.setListaPermisos = function(lista){
		listaPermisos = lista;
	}
	
	this.getListaPermisos = function(){
		return listaPermisos;
	}
	
	this.setRowsPerPage = function(rows) {
		rowsperpage = rows;
	};
	
	this.getRowsPerPage = function() {
		return rowsperpage;
	}
	
	this.setListaPerfiles = function(lista) {
		listaPerfiles = lista;
	};
	
	this.getListaPerfiles = function() {
		return listaPerfiles;
	}
	
	this.setListaUsuarios = function(lista) {
		listaUsuarios = lista;
	};
	
	this.getListaUsuarios = function() {
		return listaUsuarios;
	}
	
	this.setListaParams = function(listaP) {
		listaParams = listaP;
	};
	
	this.getListaParams = function() {
		return listaParams;
	}
	
	this.getStatus = function() {
		return status;
	}
	
	this.setStatus = function(estado) {
		status = estado;
	};
	
	this.cambiarClave = function (clave, nueva, repetir){
		return $http.get(config.baseUrl + "/administracionController/cambiarClave",
				{params:{"clave": clave, "nueva": nueva, "repetir": repetir}});
	};
	
	this.buscarUsuarios = function (tipoBusqueda, valor, isAdmin, codigo, isConsulta) {
		return $http.get(config.baseUrl + "/administracionController/buscarUsuarios", 
					{params:{"tipoBusqueda": tipoBusqueda,"valor": valor, "isAdmin" : isAdmin, "codigo" : codigo, "isConsulta": isConsulta}});
	};
		
	this.actualizarInformacion = function (usuarioVO) {
		return $http.put(config.baseUrl + "/administracionController/actualizaInformacion",usuarioVO);
	};	
	this.altaUsuario = function (usuarioVO) {
		return $http.put(config.baseUrl + "/administracionController/altaUsuario",usuarioVO);
	};	
	
	this.obtenerRegiones = function () {
		return $http.get(config.baseUrl + "/administracionController/obtenerRegiones");
	};			
	this.obtenerRegionesUPC = function () {
			return $http.get(config.baseUrl + "/administracionController/obtenerRegionesUPC");
	};	
	
	this.adscripcionAlta = function (adscripcionVO) {
		return $http.post(config.baseUrl + "/administracionController/adscripcionAlta",adscripcionVO);
	};	
	
	this.obtenerDepositos = function () {
		return $http.get(config.baseUrl + "/administracionController/obtenerDepositos");
	};
	
	this.modificaDeposito = function (deposito) {
		return $http.post(config.baseUrl + "/administracionController/modificaDeposito",deposito,{contentType: {'Accept' : 'application/json'}});
	};
	this.actualizarEstatus = function (usuarioEstatusVO) {
		return $http.post(config.baseUrl + "/administracionController/actualizarEstatus",usuarioEstatusVO);
	};
	
	this.consultaCaja = function (numCaja) {
		return $http.get(config.baseUrl + "/administracionController/consultaCaja", 
					{params:{"numCaja": numCaja}});
	};
	
	this.actualizaCaja = function (cajaVO) {
		return $http.post(config.baseUrl + "/administracionController/actualizaCaja",cajaVO);
	};
	
	this.buscaAutorizacion = function(concepto, placa){
		return $http.get(config.baseUrl + "/administracionController/buscaAutorizacion", 
				{params:{"concepto": concepto, "placa": placa}});
	}

	/*PERFILES WEB*/

	this.ejecutaSoporteOperacion = function(soporteOperacionVO){
		return $http.post(config.baseUrl + "/administracionController/ejecutaSoporteOperacion",soporteOperacionVO);
	}

	this.obtenerPerfilesTodos = function () {
		return $http.get(config.baseUrl + "/administracionController/obtenerAllPerfiles");
	};
	
	this.obtenerPerfilesPorUsuarioPerfil = function(codigo){
		return $http.get(config.baseUrl + "/administracionController/obtenerPerfilesPorUsuarioPerfil", 
				{params:{"codigo": codigo}});
	}

	this.buscaIngresoPorInfraccion = function(infraccion){
		return $http.get(config.baseUrl + "/administracionController/buscaIngresoPorInfraccion", 
				{params:{"infraccion": infraccion}});
	}
	
	this.buscaIngresoDetallePorInfraccion  = function(infraccion){
		return $http.get(config.baseUrl + "/administracionController/buscaIngresoDetallePorInfraccion", 
				{params:{"infraccion": infraccion}});
	}
	
	this.buscaEmbargoPorPlaca = function(placa){
		return $http.get(config.baseUrl + "/administracionController/buscaEmbargoPorPlaca", 
				{params:{"placa": placa}});
	}
	
	this.buscaPagoDeInfraccion = function(infraccion){
		return $http.get(config.baseUrl + "/administracionController/buscaPagoDeInfraccion", 
				{params:{"infraccion": infraccion}});
	}
	
	this.buscaEmbargos = function(tipoBusqueda, valor){
		return $http.get(config.baseUrl + "/administracionController/buscaEmbargos", 
				{params:{"tipoBusqueda": tipoBusqueda, 
						 "valor": valor}});
	}

	this.obtenerListaMenus = function (tipoBusqueda, valor) {
		return $http.get(config.baseUrl + "/administracionController/obtenerListaMenus", 
					{params:{"tipoBusqueda": tipoBusqueda,"valor": valor}});
		};
	
	this.crudPerfiles = function (parametros) {
		return $http.post(config.baseUrl + "/administracionController/crudPerfiles",parametros,
				 {contentType: {'Accept' : 'application/json'}});
	};
	
	/*this.crudPerfiles = function (parametros) {
	 	  return $http({
      method: 'POST',
      url: config.baseUrl + "/administracionController/crudPerfiles"parametros
      params:  parametros ,
      dataType: "json",
      contentType: {'Accept' : 'application/json'}
	 	 });
	};
	*/
	
	

	this.buscarCaja = function (tipoBusqueda, valor) {
		return $http.get(config.baseUrl + "/administracionController/buscarCajaExtemporaneas", {params:{"tipoBusqueda": tipoBusqueda,"valor": valor}});
	};	

	this.cajaByCajaCodEmpPlacaDepId = function (cajaCod, empPlaca, depId) {
		return $http.get(config.baseUrl + "/administracionController/cajaByCajaCodEmpPlacaDepId", {
			params : {
				"cajaCod" : cajaCod,
				"empPlaca": empPlaca,
				"depId" : depId
			}
		});
	};
	
	this.habilitarExtemporanea = function(datosHabilitar) {
		return $http.post(config.baseUrl + "/administracionController/habilitarExtemporanea", datosHabilitar);
	};

	this.getInfoForModaCaja = function (caja) {
		return $http.get(config.baseUrl + "/administracionController/foliosByCaja", {
			params : {
				"caja" : caja
			}
		});
	};
	
	this.desabilitarExtemporanea = function(datosDesabilitar) {
		return $http.post(config.baseUrl + "/administracionController/desabilitarExtemporanea", datosDesabilitar);
	};
	
	this.buscarCajasDesactivar = function() {
		return $http.get(config.baseUrl + "/administracionController/buscarCajaExtemporaneasDesactivar");
	};
	
	this.buscarUsuarioHH = function(placa){
		return $http.get(config.baseUrl + "/administracionController/buscarUsuarioHH", {params:{"placa": placa}});
	}
	
	this.buscarFoliosUsuario = function(empleadoId){
		return $http.get(config.baseUrl + "/administracionController/buscarFoliosUsuario", {params:{"empleadoId": empleadoId}});
	}
});
