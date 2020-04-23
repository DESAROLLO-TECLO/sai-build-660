angular.module('siidfApp').controller('altaSalidaVehiculoController', function( $location, $scope, salidaVehiculoService,  $routeParams, ModalService) {
	
	$scope.infracNumM = $routeParams.id;
	$scope.viewMessagePlaca = false;
	$scope.viewMessagePlacaShow = false;
	$scope.validaAccionBusquedaPlaca = true;
	$scope.banderaValidaInfraccionPaga = false;
//	$scope.flagInputFileView = false;
	var opcionAccion = {};
	salidaVehiculoService.setEstatusMomento()
	$scope.file={};
	$scope.files = {};
	$scope.fileObject = {};
	$scope.nombreArchivo = 'Seleccionar evidencias';
 	var inputs = document.querySelectorAll('.inputFileSalidaVeh');
 	$scope.infoBusqueda = {};
 	
	busquedaInfoInfrac = function(){
		salidaVehiculoService.busquedaInfoInfrac($scope.infracNumM).success(function(data) {
			$scope.infoBusqueda = data;
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		
		});		
	};
	

	
	/*VALIDACION DE IMG Y SU RESPECTIVA MUESTRA EN INPUT*/
  
	Array.prototype.forEach.call(inputs, function(input){
		var label = input.nextElementSibling, 
		labelVal = label.innerHTML;
		$scope.flagInputFile = true;
		input.addEventListener('change', function(e){
		$scope.nombreArchivo =  '';
		if($scope.validaAccionBusquedaPlaca){
			$scope.consultarPlacaOficial();
		}
		if (this.files && this.files.length > 1) {
			$scope.flagInputFile = false;
//			$scope.flagInputFileView = $scope.flagInputFile;
			if(this.files.length > 3){
				$scope.showAviso("Se ha excedido el limite de evidencias(3)", 'templateModalError');
				$scope.nombreArchivo = 'Seleccionar evidencias';
				$scope.flagInputFile = true;
			
//				$scope.flagInputFileView = $scope.flagInputFile;
			}else{
				
				$scope.flagInputFile = false;
//				$scope.flagInputFileView = $scope.flagInputFile;
				$scope.nombreArchivo = (this.getAttribute('data-multiple-caption') || '').replace('{count}', this.files.length);
			}
			
		}else{
			$scope.flagInputFile = false;
//			$scope.flagInputFileView = $scope.flagInputFile;
			$scope.nombreArchivo = e.target.value.split('\\').pop();
			
		}if ($scope.nombreArchivo){
				$scope.nombreArchivo;
			}else{
				$scope.nombreArchivo = 'Seleccionar evidencias';
				
			}
		});

		// Firefox bug fix
		input.addEventListener( 'focus', function(){ input.classList.add( 'has-focus' ); });
		input.addEventListener( 'blur', function(){ input.classList.remove( 'has-focus' ); });

	});
	
	/* *************************************************** */
	
	$scope.changeView = function(){
		if($scope.validaAccionBusquedaPlaca){
			$scope.consultarPlacaOficial();
		}
		if($scope.infoBusqueda.filtroValue == 1){
			comboAdjudDestino();
		}else if($scope.infoBusqueda.filtroValue == 2){
			comboFaseCompacta();
			
		}else if($scope.infoBusqueda.filtroValue == 3){
			validaPlaca();
			$scope.viewMessagePlacaShow = false ;
			$scope.viewMessagePlaca = false;
			$scope.infoBusqueda.placaOficial = "";
			$scope.AltaSalidaVeh.placaOficial.$setPristine();
			$scope.validaAccionBusquedaPlaca = false;
		}else if($scope.infoBusqueda.filtroValue == 4){
			$scope.infoBusqueda.folioDocto = "";
		}else if($scope.infoBusqueda.filtroValue == 5){
			comboDepDestino();
		}
	};
		
	filtroTipoSalida = function(){
		salidaVehiculoService.filtroTipoSalida().success(function(data) {
			$scope.valuesFiltro = data;
			$scope.infoBusqueda.filtroValue = $scope.valuesFiltro[0].codigoString;
			comboAdjudDestino();
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		
		});		
	};
	
	comboAdjudDestino = function(){
		salidaVehiculoService.comboAdjudDestino().success(function(data) {
			$scope.valuesAdjudDestino = data;
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
	};
	
	comboFaseCompacta = function(){
		salidaVehiculoService.comboFaseCompacta().success(function(data) {
			$scope.valuesFaseCompactas = data;
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		
		});
	};
	
	validaPlaca = function(){
		salidaVehiculoService.validaPlaca($scope.infoBusqueda.infracNum).success(function(data) {
			$scope.msg = data.pagoInfrac;
			$scope.banderaValidaInfraccionPaga = $scope.msg == "INFRACCIÓN PAGADA" ? false :  true;
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
	};
	
	comboDepDestino = function(){
		salidaVehiculoService.comboDepDestino($scope.infoBusqueda.numDeposito).success(function(data) {
			$scope.valuesDepDestino = data;
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
	}
	
	$scope.validaShowView = function(){
		if($scope.infoBusqueda.placaOficial == undefined && $scope.viewMessagePlacaShow == true){
			$scope.viewMessagePlacaShow = false ;
		} 
		if ($scope.infoBusqueda.placaOficial == undefined && $scope.viewMessagePlaca == true) {
			$scope.viewMessagePlaca = false;
		}
		if($scope.infoBusqueda.placaOficial < $scope.banderaLengthPlacaAct){
			$scope.viewMessagePlacaShow = false;
			$scope.viewMessagePlaca = true; 
			$scope.validMensaje = "La placa oficial no es valida";;
		}
	};
	
	$scope.consultarPlacaOficial = function(){
		if($scope.infoBusqueda.placaOficial == undefined){
			$scope.AltaSalidaVeh.placaOficial.$dirty = true;
		}else{
		salidaVehiculoService.consultarPlacaOficial($scope.infoBusqueda.placaOficial).success(function(data) {
			if(data.empId.length <= 0){
				$scope.viewMessagePlaca = true;
				$scope.viewMessagePlacaShow = false;
				$scope.validaAccionBusquedaNorequerido = true;
				$scope.validMensaje = "La placa oficial no es valida";
			}else{
				$scope.validaAccionBusquedaPlaca = false;
				$scope.viewMessagePlaca = false;
				$scope.viewMessagePlacaShow = true;
				$scope.banderaLengthPlacaAct = $scope.infoBusqueda.placaOficial.length;
				$scope.validMensaje = data;
			}
		}).error(function(data){
			$scope.viewMessagePlaca = true;
			$scope.viewMessagePlacaShow = false;
			$scope.validaAccionBusquedaNorequerido = true;
			$scope.validMensaje = data.message;
//			$scope.showAviso(data.message, 'templateModalAviso');
		});
		}
	};
	$scope.validToAfter = function(){
		
		if($scope.validaAccionBusquedaPlaca){
			$scope.consultarPlacaOficial();
		}
	}
	
	
	$scope.registraSalida = function(){

//		$scope.flagInputFileView = $scope.flagInputFile;|| $scope.flagInputFile
	 if($scope.AltaSalidaVeh.$invalid || $scope.viewMessagePlaca || $scope.validaAccionBusquedaPlaca || $scope.banderaValidaInfraccionPaga ){
			requiredFields();
			if($scope.validaAccionBusquedaPlaca){
				$scope.viewMessagePlaca = true;
				$scope.viewMessagePlacaShow = false;
				$scope.validMensaje = "La placa oficial no es valida";
				$scope.msgVariable = "Es necesario buscar una placa valida";
			}
			if($scope.AltaSalidaVeh.$invalid){
				$scope.msgVariable = "Formulario Incompleto" ;
			}
			if($scope.banderaValidaInfraccionPaga && $scope.validaAccionBusquedaPlaca == false && $scope.AltaSalidaVeh.$invalid == false ){
				$scope.msgVariable = "La infracción no ha sido pagada" ;
			}
			$scope.validaAccionBusquedaPlaca = true;
			$scope.banderaValidaInfraccionPaga = false;
			$scope.showAviso($scope.msgVariable, 'templateModalAviso');
		}else{
			if($scope.infoBusqueda.filtroValue == 3){
				$scope.showConfirmacion("Esta seguro de guardar la salida  por pago con infracción: "+$scope.infoBusqueda.infracNum, function(){ $scope.guardarRegistraSalida() } );
			}else if($scope.infoBusqueda.filtroValue == 4){
				$scope.showConfirmacion("Esta seguro de guardar la salida por oficio con número : "+$scope.infoBusqueda.folioDocto, function(){ $scope.guardarRegistraSalida() } );
			}else{
				buscarValueDestinoOption();
			}
		}
	};
	
	$scope.guardarRegistraSalida = function(){
		$scope.infoBusqueda.nomDepDestino = "";
		if($scope.infoBusqueda.filtroValue == 5){
			$scope.infoBusqueda.nomDepDestino = $.grep($scope.valuesDepDestino, function (option) {
				return option.codigo == $scope.infoBusqueda.depDestino;
			})[0].descripcion; 
		}
		
		var salidaVehiculoVO = {
				 "numinfrac" : $scope.infoBusqueda.infracNum,
				  "numCtrl" : $scope.infoBusqueda.infracNumCtrl,
				  "numPlaca" : $scope.infoBusqueda.placaNum,
				  "numSerie" : $scope.infoBusqueda.numSerie,
				  "numPlacaOficial" : $scope.infoBusqueda.placaOficial,
				  "movTpo" : $scope.infoBusqueda.filtroValue,
				  "idAdjudica" : $scope.infoBusqueda.adjudDestino,
				  "idFase" : $scope.infoBusqueda.faseCompacta,
				  "depDestino" : $scope.infoBusqueda.depDestino,
				  "docSalida" : $scope.infoBusqueda.folioDocto,
				  "idDep" : $scope.infoBusqueda.numDeposito,
				  "numResguardo" : $scope.infoBusqueda.numResguardo,
				  "depNomDestino" : $scope.infoBusqueda.nomDepDestino,
				  "depNomOrigen" : $scope.infoBusqueda.nomDep,
				  "tipoMovimiento" : "S"
			 };
	$scope.tamno = ($scope.files.length == undefined || $scope.files.length < 1) ? 0 : $scope.files.length ;
	salidaVehiculoService.guardarSalida(salidaVehiculoVO, $scope.files, $scope.tamno  ).success(function(data) {
		opcionAccion.accionRecepcion = true
		opcionAccion.accionModal = true
		salidaVehiculoService.setEstatusMomento(opcionAccion);
		if(data.mensaje != null){
			$scope.showAviso(data.mensaje, 'templateModalAviso',function(){ locationMover() } );
		}else{
			$scope.showAviso(data.mensaje, 'templateModalError');
		}
	}).error(function(data){
		$scope.showAviso(data.message, 'templateModalAviso');
	});	
	};
	
/*VALIDACION DE CAMPOS VACIOS*/
	
	requiredFields = function(){
		angular.forEach($scope.AltaSalidaVeh.$error, function (field) {
		      	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            	})
		});
	};
	
	$scope.regresarBusqMod = function(){
		opcionAccion.accionRecepcion = false;
		opcionAccion.accionModal = false;
		salidaVehiculoService.setEstatusMomento(opcionAccion);
		locationMover();
		
	}
	locationMover = function(){
		$location.path('/salidasVehiculoBusqueda');
	}
	
//	**************************   INICIO CARGA DE ARCHIVOS   **********************************
	
	$scope.listenerForImg = function (e, file) {
		var reader = new FileReader();
		reader.readAsDataURL(file[0]);  
		reader.onerror = function (error) {
			$scope.files = {};
			
		};
	};
		
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
	
 
		
	$scope.AS = [{numero : 1}]; 
	$scope.add = function(){
		var addCampos = $scope.AS.length+1;
		$scope.AS.push({'numero' : addCampos});
	}
		
	$scope.remove = function(){
		var removerCampos = $scope.AS.length-1;
		$scope.AS.splice(removerCampos);
	}


	
	
	
		
	

	/*MODALES AVISOS*/
	
	$scope.showConfirmacion = function(messageTo, action){
		ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller: 'mensajeModalController',
	        	inputs:{ message: messageTo}
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		action();
	        	}
	        }); 
	    });
	};
	
	$scope.showAviso = function(messageTo, template, action) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/'+template+'.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	if(result){
	        		action();
	        	}
	        }); 
	                   
	      });
		};
	
		/*VALIDACION DE MENSJES POR TIPO DE SALIDAS QUE SE DESEA GUARDAR*/
		
		buscarValueDestinoOption = function(){
			if($scope.infoBusqueda.filtroValue == 1){
				for (x = 0; x < $scope.valuesAdjudDestino.length; x++){
				      if($scope.valuesAdjudDestino[x].codigoString == $scope.infoBusqueda.adjudDestino){
				    	  $scope.showConfirmacion("Esta seguro de guardar la salida por adjudicación  a: "+$scope.valuesAdjudDestino[x].descripcion,function(){ $scope.guardarRegistraSalida() } );
				      }
					}
				
			}else if($scope.infoBusqueda.filtroValue == 2){
				for(x = 0; x < $scope.valuesFaseCompactas.length; x ++ ){
					if($scope.valuesFaseCompactas[x].codigo == $scope.infoBusqueda.faseCompacta){
						$scope.showConfirmacion("Esta seguro de guardar la salida con fase: "+$scope.valuesFaseCompactas[x].descripcion,function(){ $scope.guardarRegistraSalida() } );
					}
				}
				
			}
			else if($scope.infoBusqueda.filtroValue == 5){
			for (x = 0; x < $scope.valuesDepDestino.length; x++){
			      if($scope.valuesDepDestino[x].codigo == $scope.infoBusqueda.depDestino){
			    	  $scope.showConfirmacion("Esta seguro de guardar la salida por liberación de espacio a: "+$scope.valuesDepDestino[x].descripcion,function(){ $scope.guardarRegistraSalida() } );
			      }
				}
			}
		};
	
	
	busquedaInfoInfrac();
	filtroTipoSalida(); 

});