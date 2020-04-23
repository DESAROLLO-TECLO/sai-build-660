angular.module('siidfApp').controller('expedienteImpugnacionController', 
function($route,$scope, $filter, infraccion, infraccionService, expedienteInfraccionService, validaExpediente,expedienteService) {

	var index = 0;
	$scope.listaImagenes = [];
	
	$scope.$on('$locationChangeSuccess', function(event, current, previous) {
		  $scope.proximoController = $route.current.$$route.controller;
		  if( $scope.proximoController != 'busquedaInfraccionController'  ){
//			  emptyParams = null;
//			  infraccionService.setInfracExp(emptyParams);
//			  emptyList = [];            
//			  infraccionService.setListaInfraccionesCons(emptyList);
			  
			  emptyList = [];
			  infraccionService.setListaInfraccionesCons(emptyList);
			  emptyParams = null;
			  infraccionService.setInfracExp(emptyParams);
		  }   
	});
	
	$scope.regresarExp = function(){
		window.history.back();
	}
	
	$scope.infraccion = {'infraccionNumero': infraccion};
	$scope.consultaExpediente ={};
	
	$scope.ligasSection = "col-lg-offset-2 col-lg-8";
	$scope.showFotosSection = false;
	
	$scope.fotos = [];
	
	$scope.imagenesIngreso = {};
	$scope.directorioDigA = {};
	$scope.directorioDigR = {};
	$scope.imagenesHandHeld = {};
	$scope.imagenesDigitExped = {}; //Nuevo Modulo Digitalización
	$scope.validaExpediente = validaExpediente === 'true';
	$scope.imgStatus = 'S';
	$scope.mostrarTituloExpedientePago = false;
		
	$scope.showAviso = function(messageTo) {
		ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
		}).then(function(modal) {
	        modal.element.modal();
	    });
	};
	
	$scope.consultaExpediente = function(){
		expedienteInfraccionService.consultaExpediente(infraccion)
		.success(function(data){
			$scope.consultaExpediente = data;
			$scope.error = false;
		})
		.error(function(data){
			$scope.consultaExpediente = {};
			$scope.error = true;
		});
	};
	
	$scope.buscarImagenesIngreso = function(){
		if(!$scope.validaExpediente){ // solo en consulta
			expedienteInfraccionService.buscarImagenesIngreso(infraccion)
			.success(function(data){
				$scope.imagenesIngreso = data;
				$scope.error = false;
			})
			.error(function(data){
				$scope.imagenesIngreso = {};
				$scope.error = true;
			});
		}
	}
	
	$scope.buscarDirectorioDigitalizacion = function(param){
		expedienteInfraccionService.buscarDirectorioDigitalizacion(infraccion, param)
		.success(function(data){
			if(param == 'A')
				$scope.directorioDigA = data;
			else
				if(param == 'R')
					$scope.directorioDigR = data;
				else
					if(param == 'A_VALID')
						$scope.directorioDigValA = data;
					else
						$scope.directorioDigValR = data;
			
			if(param == 'A_VALID'){
				$scope.directorioDigValA = $scope.configuraPathByUnit($scope.directorioDigValA);
			}else if(param == 'R_VALID'){
				$scope.directorioDigValR = $scope.configuraPathByUnit($scope.directorioDigValR);
			}
			$scope.error = false;
			if(data.length > 0 && (param == 'A_VALID' || param == 'R_VALID'))
				$scope.mostrarTituloExpedientePago = true;
			else
				$scope.mostrarTituloExpedientePago = false;
		})
		.error(function(data){
			$scope.directorioDigA = {};
			$scope.directorioDigR = {};
			$scope.error = true;
			$scope.mostrarTituloExpedientePago = false;
		})
	}
	
	$scope.configuraPathByUnit = function(list){
		for (var key in list) {
			if (!list.hasOwnProperty(key)) continue;

			var obj = list[key];
		    
		    if(obj.unidad == "H")
		    	obj.ruta = "http://172.40.20.58/ssp/imgs01/" + obj.path;
		    else if(obj.unidad == "I")
		    		obj.ruta = "http://172.40.20.58/ssp/imgs02/" + obj.path;
		    
		    else if(obj.unidad == "J")
	    			obj.ruta = "http://172.40.20.58/ssp/imgs03/" + obj.path;
		    
		    else if(obj.unidad == "K"){
	    			obj.ruta = "http://172.40.20.58/ssp/fotos/" + obj.path;
	    			obj.ruta1 = "http://172.40.20.58/ssp/imgs_dia" + obj.path;
		    }
		    list[key] = obj;
		}
		return list;
	}
	
	$scope.pru = function(list){
		for (var key in list) {
		    if (!list.hasOwnProperty(key)) continue;

		    var obj = list[key];
		    for (var prop in obj) {
		        if(!obj.hasOwnProperty(prop)) continue;
		        alert(prop + " = " + obj[prop]);
		    }
		}
	}
	
	
	$scope.buscarImagenesHandHeld = function(){
		expedienteInfraccionService.buscarImagenesHandHeld(infraccion)
		.success(function(data){
			$scope.imagenesHandHeld = data;
			$scope.error = false;
						
			creaListaImagenes($scope.imagenesIngreso);
			creaListaImagenes($scope.directorioDigA);
			creaListaImagenes($scope.directorioDigR);
			creaListaImagenes($scope.imagenesHandHeld);			
			$scope.ligasSection= $scope.listaImagenes.length>0?"col-md-6":$scope.ligasSection;
	
		})
		.error(function(data){
			$scope.imagenesHandHeld = {};
			$scope.error = true;
		})
	}
	
	$scope.openfotosSection = function(fotoNombre){
		$scope.buscarImagen(fotoNombre);
	}
	
	$scope.descargarImagen = function(nombreArchivo){
		for ( var it = 0; it < listFotos.length; it++) {
			if(listFotos[it] == nombreArchivo){
				var url = $scope.fotos[it].image.replace(/^data:image\/[^;]+/, 'data:application/octet-stream');
				$scope.save(false, url, nombreArchivo);
			}
		}
		
		
	}
	
	var listFotos = new Array();
	
	$scope.buscarImagen = function(fotoNombre,tipo){
		expedienteInfraccionService.consultaFotoPorNombre(fotoNombre,tipo)
		.success(function(data){
			var img = {
				image: "data:image/JPEG;base64," + data.image,
				nombreArchivo: fotoNombre,
				id:index++
			};
			
			$scope.fotos.push(img);

			listFotos[index - 1] = fotoNombre;
			
			$scope.ligasSection = "col-lg-6";
			$scope.fotosSection = "col-lg-6";
			$scope.showFotosSection = true;
		})
		.error(function(data){
			
		})
	}
	
	$scope.getStatusValidacionImagenes = function(){
		expedienteInfraccionService.getStatusValidacionImagenes(infraccion)
		.success(function(data){
			$scope.validacion = data;
		})
		.error(function(x,y,z){
			if(y == '404')
				$scope.validacion = null;

		})
	}
	
	$scope.setStatusValidacionImagenes = function(){
		$scope.infraccionExpedienteStatus = {};
		$scope.infraccionExpedienteStatus.pInfracnum = infraccion;
		$scope.infraccionExpedienteStatus.pEstatus = $scope.imgStatus;
		
		expedienteInfraccionService.setStatusValidacionImagenes($scope.infraccionExpedienteStatus)
		.success(function(data){
			$scope.infraccionExpedienteStatus = data;
			$scope.showAviso($scope.infraccionExpedienteStatus.pMensaje);
		})
		.error(function(data){
			$scope.infraccionExpedienteStatus = {};
		})
	}
	
	if($scope.validaExpediente){
		$scope.buscarDirectorioDigitalizacion('A_VALID');
		$scope.buscarDirectorioDigitalizacion('R_VALID');
	}else{
		$scope.buscarDirectorioDigitalizacion('A');
		$scope.buscarDirectorioDigitalizacion('R');
	}
	
	/* BUSQUEDA DE DOCUMENTOS DIGITALIZADOS DE NUEVO MODULO DIGITALIZACION*/
	$scope.buscarDigitalizacionExpediente = function(){
		expedienteService.mostrarTodoExpedientes(infraccion)
		.success(function(data) {
			if(data){
				$scope.imagenesDigitExped = data;
				if(data.length > 0)
					$scope.mostrarTituloExpedientePago = true;
				else
					$scope.mostrarTituloExpedientePago = false;
			}
		}).error(function(data) {
			if(data.message){
				$scope.showError(data.message);
			}else{
				$scope.showError(data);
			}
		});	
	};
		/*FIN*/
	
//	DESCARGAR DOCUMENTO EN PDF
	/*
	 * César Gómez
	 */
	$scope.descargarExpediente = function($event, object){
		$event.preventDefault();
		expedienteService.bajarExpedientes(infraccion, object)
			.success(function(data, status, headers) {
				$scope.error = false;
				
				var filename = data.nombreArchivo;
				var extension = data.nombreArchivo.split('.')[1];
					
				var file;
				
				file = $scope.b64toBlob(data.bdPath, "application/pdf");
				
				$scope.save(data.existeEnBD, file, filename);
				
				$scope.error = false;
				
			}).error(function(data) {
				if(data.message){
					$scope.showError(data.message);
				}else{
					$scope.showError(data);
				}
			});
	};
	
	/*
	 * César Gómez
	 */
	$scope.b64toBlob = function(b64Data, contentType, sliceSize) {
		  contentType = contentType || '';
		  sliceSize = sliceSize || 512;

		  var byteCharacters = atob(b64Data);
		  var byteArrays = [];

		  for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
		    var slice = byteCharacters.slice(offset, offset + sliceSize);

		    var byteNumbers = new Array(slice.length);
		    for (var i = 0; i < slice.length; i++) {
		      byteNumbers[i] = slice.charCodeAt(i);
		    }

		    var byteArray = new Uint8Array(byteNumbers);

		    byteArrays.push(byteArray);
		  }

		  var blob = new Blob(byteArrays, {type: contentType});
		  return blob;
	};
	
	/*
	 * César Gómez
	 */
	$scope.save = function(isURL, file, fileName) {
		var url = window.URL || window.webkitURL;
		var a = document.createElement('a');
		if(!isURL){
			a.href = file;
		}else{ 
			a.href = url.createObjectURL(file);
		}
		a.target = '_blank';
		a.download = fileName;
		document.body.appendChild(a);
		a.click();	
	};
	
	/*Crea una lista de imagenes para el carrusel */
	creaListaImagenes = function (lista) {
		
		for (var i = 0; i < lista.length; i++) {
			$scope.listaImagenes.push(lista[i]);
		}
	}
	
	$scope.descargaExpedienteImg = function (ruta,nombreArchivo) {
		
		expedienteInfraccionService.descargaImagenExpediente(ruta+"/"+nombreArchivo)
				.success(function(data){
						
			 var file = new Blob([data], {type:'image/jpeg'});
			 
			 if(file.size==0){
				 $scope.showError("Sin acceso al recurso");
			 }else{		 
				 	if (window.navigator.msSaveOrOpenBlob) // IE10+
				        window.navigator.msSaveOrOpenBlob(file, nombreArchivo);
				 	else { // Others
				        var a = document.createElement("a"),
				        url = URL.createObjectURL(file);
				        a.href = url;
				        a.download = nombreArchivo;
				        document.body.appendChild(a);
				        a.click();
				        setTimeout(function() {
				            document.body.removeChild(a);
				            window.URL.revokeObjectURL(url);  
				        }, 0); 
				    }
			  } 
			})
		.error(function(data){
			
	   })		
	 }
	
	 $scope.showError = function(messageTo) {
		    ModalService.showModal({
		    	  templateUrl: 'views/templatemodal/templateModalError.html',
		          controller: 'mensajeModalController',
		          inputs:{ message: messageTo}
		    	}).then(function(modal) {
		        	modal.element.modal();
		        });
		    };
		    
    consultaExpedienteDetalle = function(){
		infraccionService.buscarInfraccionDetallada(infraccion)
		.success(function(data){
			$scope.infraccionDetalle = data;
			$scope.error = false;
		})
		.error(function(data){
			$scope.infraccionDetalle = {};
			$scope.error = true;
		});
	};	  
	
	
	consultaExpedienteDetalle();
	$scope.consultaExpediente();
	$scope.buscarImagenesIngreso();
	$scope.buscarImagenesHandHeld();
	$scope.buscarDigitalizacionExpediente();
	
});
