angular.module('siidfApp').controller('busquedaDetalleInfraccionController', function($scope, $filter, $route, $location, infraccion, infraccionService, expedienteInfraccionService) {
	
	$scope.infraccion = infraccion.data;
	
	$scope.listaImagenes = [];
	$scope.consultaExpediente ={};
	$scope.imagenesIngreso = {};
	$scope.imagenesHandHeld = {};
	$scope.fotos = [];
	
	$scope.transLeyDelTransp = function(arg){
		if(arg == 'S')
			return 'SI';
		return 'NO';
	}
	
	$scope.imprimir = function(){
		infraccionService.reporteConsulta($scope.infraccion.nci)
		.success(function(data,status,headers){
			var filename = headers('filename');
			var contentType = headers('content-type');
			var file = new Blob([ data ], {
				type : 'application/pdf;base64,'
			});
			infraccionService.downloadfile(file, filename);
		})
		.error(function(data){
			$scope.showError("Imposible descargar reporte. Intententelo más tarde.")
		})
	}
	
	$scope.$on('$locationChangeSuccess', function(event, current, previous) {
		  $scope.proximoController = $route.current.$$route.controller;
		  if($scope.proximoController != 'busquedaInfraccionController'  ){
			  emptyList = [];
			  infraccionService.setListaInfraccionesCons(emptyList);
			  emptyParams = null;
			  infraccionService.setInfracExp(emptyParams);
		  }
	});
	
	$scope.regresarBusq = function(){
		window.history.back();
	}
	
	
	/*Crea una lista de imagenes para el carrusel */
	creaListaImagenes = function (lista,tipo) {
		for (var i = 0; i < lista.length; i++) {
			$scope.listaImagenes.push([lista[i],tipo]);
		}
	}
	
	$scope.buscarImagen = function(fotoNombre,tipo,cattipo){
		expedienteInfraccionService.consultaFotoPorNombre(fotoNombre,tipo)
		.success(function(data){
			var img = {
				image: "data:image/JPEG;base64," + data.image,
				nombreArchivo: fotoNombre,
			};
			
			$scope.fotos.push(img);
			$scope.listaImagenes.push([img,cattipo]);
			//alert('fotos.length: '+$scope.fotos.length);
		})
		.error(function(data){
			
		})
	}
	
	$scope.consultaExpedienteIMG = function(){
		expedienteInfraccionService.consultaExpediente($scope.infraccion.infraccionNumero)
		.success(function(data){
			$scope.consultaExpediente = data;
			$scope.error = false;
			$scope.consultaImgExpediente();
		})
		.error(function(data){
			$scope.consultaExpediente = {};
			$scope.error = true;
		});
	};
	
	$scope.consultaImgExpediente = function(){
		for(var f = 0; f < $scope.consultaExpediente.length; f++){
			$scope.buscarImagen($scope.consultaExpediente[f].nombreArchivo,$scope.consultaExpediente[f].tipo,'Infraccion');
//			alert('nombreArchivo: '+$scope.consultaExpediente[f].nombreArchivo+', tipo: '+$scope.consultaExpediente[f].tipo);
		}
		var x = $scope.listaImagenes.length;
		
		//creaListaImagenes($scope.fotos,'Infraccion');
//			$scope.buscarImagen($scope.consultaExpediente[i].nombreArchivo,$scope.consultaExpediente[i].tipo);
//			alert('nombreArchivo: '+$scope.consultaExpediente[i].nombreArchivo+', tipo: '+$scope.consultaExpediente[i].tipo);
		
//		alert('consultaExpediente.length: '+$scope.consultaExpediente.length+', fotos.length: '+$scope.fotos.length);
//		creaListaImagenes($scope.fotos,'Infraccion');
	}
	
	$scope.buscarImagenesIngreso = function(){
		expedienteInfraccionService.buscarImagenesIngreso($scope.infraccion.infraccionNumero)
		.success(function(data){
			$scope.imagenesIngreso = data;
			$scope.error = false;
		})
		.error(function(data){
			$scope.imagenesIngreso = {};
			$scope.error = true;
		});
	}
	
	$scope.buscarImagenesHandHeld = function(){
		expedienteInfraccionService.buscarImagenesHandHeld($scope.infraccion.infraccionNumero)
		.success(function(data){
			$scope.imagenesHandHeld = data;
			$scope.error = false;
			
			//creaListaImagenes($scope.imagenesIngreso);
			//creaListaImagenes($scope.directorioDigA);
			//creaListaImagenes($scope.directorioDigR);
			//alert('consultaExpediente.length: '+$scope.consultaExpediente.length+', fotos.length: '+$scope.fotos.length);
			creaListaImagenes($scope.imagenesHandHeld,'HandHeld');
		})
		.error(function(data){
			$scope.imagenesHandHeld = {};
			$scope.error = true;
		})
	}
	
//	$scope.verExpediente = function(){
//		var url = '/consultaExpediente/'+$scope.infraccion.infraccionNumero+'/false';
//		$location.path(url);
//	}
	$scope.verExpediente = function(){
		$location.path('/consultaExpediente/'+$scope.infraccion.infraccionNumero+'/false');
	}
	
	$scope.consultaExpedienteIMG();
	//$scope.buscarImagenesIngreso();
	$scope.buscarImagenesHandHeld();
	//$scope.consultaImgExpediente();
	//$scope.buscarDigitalizacionExpediente();
});