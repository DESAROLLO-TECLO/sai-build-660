angular.module('siidfApp').controller('reasignacionFmCPController', function($scope, fmService, ModalService, showAlert) {
	
	$scope.showIndex={titleInicio:false, titleValidas:false, titleInvalidas:false, titleReasignadas:false, muestraTablaValidas:false,
			butnDown:false};
	$scope.globalData={totalDetect:0, totalValid:0, totalInvalid:0, totalReasignacion:0, currentCP:null, posicionCP:null, hayInvalidas:false,
			deteccionesAll:null, deteccionesValid:[], deteccionesInvalid:[], deteccionesReasignadas:[]};
	$scope.temporalData={detecciones:null, cambiarCP:false};

	//Obtiene todas las detecciones y la cantidad de ellas.
	$scope.getAllDetecciones = function(){
		fmService.buscarAllDeteccionesFm($scope.dispFijoLoteVO.radarArchivoId).success(function(data){
			
			$scope.globalData.deteccionesAll=data;
			console.log($scope.globalData.deteccionesAll);
			$scope.showIndex.titleInicio=true;	
			$scope.formateoDatos();
			$scope.updateDeteccionesValidInvalid();
			
			
		}).error(function(){
			$scope.error = data;
		});	
	};
	$scope.getAllDetecciones();
	
	$scope.formateoDatos= function(){
		//formateamos los datos para su manipulacion
		for(var i in $scope.globalData.deteccionesAll) { 
			$scope.globalData.deteccionesAll[i].hayDisabled=false;
			$scope.globalData.deteccionesAll[i].totalDisabled=0;
			for(var j in $scope.globalData.deteccionesAll[i].detecciones) {
				//Agrego atributos a usar
				$scope.globalData.deteccionesAll[i].detecciones[j].show=false;
				$scope.globalData.deteccionesAll[i].detecciones[j].modified=false;
				$scope.globalData.deteccionesAll[i].detecciones[j].disabled=false;
				$scope.globalData.deteccionesAll[i].detecciones[j].cpNew=null;
				$scope.globalData.deteccionesAll[i].detecciones[j].crNew=null;		
				$scope.globalData.totalDetect+=1;
			}
		}
	}
	
	
	$scope.updateDeteccionesValidInvalid = function(){
		//para prueba
//		$scope.globalData.deteccionesAll[0].detecciones[0].disabled=true;
//		$scope.globalData.hayInvalidas=true;
//		$scope.globalData.deteccionesAll[0].hayDisabled=true;
//		$scope.globalData.deteccionesAll[0].idDeteccion-=1;
//		$scope.globalData.deteccionesAll[0].totalDisabled+=1;
				
		for(var i in $scope.globalData.deteccionesAll) { 
			//cuantas sin centro de reparto
		    if($scope.globalData.deteccionesAll[i].centroReparto==0){
		    	console.log('no tiene cr el cp');
		    	for(var j in $scope.globalData.deteccionesAll[i].detecciones) {
		    		console.log($scope.globalData.deteccionesAll[i].detecciones[j].centroReparto);
		    		if($scope.globalData.deteccionesAll[i].detecciones[j].centroReparto==0){
		    			console.log('no tiene cr la deteccion');
			    		$scope.globalData.deteccionesAll[i].detecciones[j].disabled=true;//desabilitamos deteccion
			    		$scope.globalData.deteccionesAll[i].hayDisabled=true;//ponemos marca en el grupo de CP
			    		$scope.globalData.hayInvalidas=true;//Ponemos marca entodo el JSON para indicar q hay invalidad
			    		$scope.globalData.deteccionesAll[i].idDeteccion-=1;
			    		$scope.globalData.deteccionesAll[i].totalDisabled+=1;
			    		
//			    		$scope.globalData.totalInvalid+=1;
		    		}
		    		
		    	}	    	
		    }
		  //Cuantas deshabilitadas
		    if($scope.globalData.deteccionesAll[i].hayDisabled==true){
		    	console.log('si hay deshabilitadas');
				for(var j in $scope.globalData.deteccionesAll[i].detecciones) {
					if($scope.globalData.deteccionesAll[i].detecciones[j].disabled==true){
						console.log('concide si hay deshabilitadas');
						$scope.globalData.totalInvalid+=1;
					}
				}
			}
		}
		$scope.globalData.totalValid=$scope.globalData.totalDetect-$scope.globalData.totalInvalid;
		
		//Dividimos en 2 arrays de detecciones válidas e inválidas
		for(var i in $scope.globalData.deteccionesAll) {
				
				if($scope.globalData.deteccionesAll[i].hayDisabled==true){
					console.log('si hay invalidas');
					var tempInvalid={idDeteccion:$scope.globalData.deteccionesAll[i].idDeteccion,
							codigoPostal:$scope.globalData.deteccionesAll[i].codigoPostal,
							centroReparto:$scope.globalData.deteccionesAll[i].centroReparto, 
							municipio:$scope.globalData.deteccionesAll[i].municipio, 
							entidadFederativa:$scope.globalData.deteccionesAll[i].entidadFederativa,
							detecciones:[], 
							hayDisabled:$scope.globalData.deteccionesAll[i].hayDisabled, 
							totalDisabled:$scope.globalData.deteccionesAll[i].totalDisabled
							};
									
					for(var j in $scope.globalData.deteccionesAll[i].detecciones) {
						if($scope.globalData.deteccionesAll[i].detecciones[j].disabled==true){
							tempInvalid.detecciones.push($scope.globalData.deteccionesAll[i].detecciones[j]);
						}else{
							tempValid.detecciones.push($scope.globalData.deteccionesAll[i].detecciones[j]);
						}
					}
					if(tempInvalid.detecciones.length>0){
						$scope.globalData.deteccionesInvalid.push(tempInvalid);
					}
					
					
					
				}else{
					var tempValid={idDeteccion:$scope.globalData.deteccionesAll[i].idDeteccion,
							codigoPostal:$scope.globalData.deteccionesAll[i].codigoPostal,
							centroReparto:$scope.globalData.deteccionesAll[i].centroReparto, 
							municipio:$scope.globalData.deteccionesAll[i].municipio, 
							entidadFederativa:$scope.globalData.deteccionesAll[i].entidadFederativa,
							detecciones:[], 
							hayDisabled:$scope.globalData.deteccionesAll[i].hayDisabled, 
							totalDisabled:$scope.globalData.deteccionesAll[i].totalDisabled
							};
					for(var j in $scope.globalData.deteccionesAll[i].detecciones) {
						if($scope.globalData.deteccionesAll[i].detecciones[j].disabled==true){
							tempInvalid.detecciones.push($scope.globalData.deteccionesAll[i].detecciones[j]);
						}else{
							tempValid.detecciones.push($scope.globalData.deteccionesAll[i].detecciones[j]);
						}
					}
					if(tempValid.detecciones.length>0){
						$scope.globalData.deteccionesValid.push(tempValid);
					}
					
				}	
			
		}
		console.log($scope.globalData.deteccionesInvalid);
		console.log($scope.globalData.deteccionesValid);
	}
	
	$scope.cambiarAccion = function(opcionRecp) {
		 switch (opcionRecp) {
		     case 1:
		    	$scope.showIndex.muestraTablaValidas=true;
		    	$scope.showIndex.muestraTablaInvalidas=false;
		    	$scope.showIndex.titleInicio=false;
		    	$scope.showIndex.titleValidas=true;
		    	$scope.showIndex.titleInvalidas=false;
		    	$scope.showIndex.butnDown=true;
		    	$scope.showIndex.titleReasignadas=false;
		        break;
		     case 2:
			    $scope.showIndex.muestraTablaValidas=false;
			    $scope.showIndex.muestraTablaInvalidas=true;
			    $scope.showIndex.titleInicio=false;
			    $scope.showIndex.titleValidas=false;
			    $scope.showIndex.titleInvalidas=true;
			    $scope.showIndex.butnDown=true;
			    $scope.showIndex.titleReasignadas=true;
			    break;
		     case 3:
		    	 if($scope.globalData.totalReasignacion>0){
		    		 showAlert.confirmacion("Desea complementar nuevamente los Centros de Reparto para este archivo",
		    				 $scope.recomplementCentro);
//		    		 $scope.showConfirmacion("Desea complementar nuevamente los Centros de Reparto para este archivo",
//		    				 function(){  $scope.recomplementCentro () });
		    	 }else{
		    		 alert('Para recomplementar Centros de Reparto primero deben reasignarse CP al menos a una detección');
		    	 }
		    	 break;
		     case 4:
		    	 if($scope.globalData.totalReasignacion>0){
		    		 showAlert.confirmacion("Si finalizas la reasignación se perderán los cambios realizados realizados en "+$scope.globalData.totalReasignacion+
		    				 " detecciones. ¿Desea finalizar la reasignación de los Centros de Reparto para este archivo?",
		    				 validardeteccionesCentroReparto);
		    		 
//		    		 $scope.showConfirmacion("Si finalizas la reasignación se perderán los cambios realizados realizados en "+
//		    				 $scope.globalData.totalReasignacion+
//		    				 " detecciones. ¿Desea finalizar la reasignación de los Centros de Reparto para este archivo?",
//		    				 function(){  validardeteccionesCentroReparto() });
			    	 
		    	 }else{
		    		 showAlert.confirmacion("Desea finalizar la reasignación de los Centros de Reparto para este archivo",
		    				 validardeteccionesCentroReparto);
		    		 
//		    		 $scope.showConfirmacion("Desea finalizar la reasignación de los Centros de Reparto para este archivo",
//		    				 function(){  validardeteccionesCentroReparto() });
			    	 
		    	 }
		    	 break;
		    	
		 }
	};
	
	
	
	$scope.loadObjdet = function(codigoPostal, posicion, detecciones, cambiarCP){
		$scope.globalData.currentCP=codigoPostal;
		$scope.globalData.posicionCP=posicion;
		console.log('este cp: '+$scope.globalData.currentCP);
		console.log('esta posicion: '+$scope.globalData.posicionCP);
		$scope.temporalData.detecciones=detecciones;
		$scope.temporalData.cambiarCP=cambiarCP;

		$scope.showDetail();
	};
	
	$scope.showDetail = function(){
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalFmDetailCP.html',
			controller: 'modificaFmDetailCPController',
        	scope: $scope
		}).then(function(modal) {
	        modal.element.modal();
		});
	};
	
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
	
		
	
	$scope.recomplementCentro = function(){
		var ids=[];
		var newcp=[];
		console.log('estas aqui e reocmplementacion');
		for(var i=0; i<$scope.globalData.deteccionesReasignadas.length;i++){
			ids.push($scope.globalData.deteccionesReasignadas[i].idDeteccion);
			newcp.push($scope.globalData.deteccionesReasignadas[i].cpNew);
		}
		fmService.recomplementaCodigoPostal2(ids, newcp).success(function(data){
			if(data != true){
				$scope.showAviso("Hubo un problema al recomplementar al centro de reparto");
				return false;
			}else{
				console.log('este es el id del lote: '+$scope.dispFijoLoteVO.radarArchivoId);
				
				fmService.recomplementaCodigoPostal($scope.dispFijoLoteVO.radarArchivoId).success(function(data){
					if(data != true){
						$scope.showAviso("Hubo un problema al recomplementar al centro de reparto");
						return false;
					}else{				
//						for(var i=0; i<3;i++){
//							$('#modalIdd').remove();
//							$('#modalReasignacionCP2').remove();
//							$('#modalReasignacionCP').remove();
//							$('#modalReasignacionFMCP').remove();
//							$('#confirmacion').remove();
//						}
						$('body').removeClass('modal-open');
						$('.modal-backdrop').remove();
						
					}
					
				}).error(function(){
					$scope.error = data;
				});
			}
			
		}).error(function(){
			$scope.error = data;
		});
		
	};
	
	/**********************************************************************/
	
	
	
	$scope.cambiarAccion2 = function(opcionRecp) {
		 switch (opcionRecp) {
		     case 1:
		    	$scope.showIndex.muestraTabla=true;
//		    	$scope.cleanTextMSJ();
//		    	$scope.listaDeteccionValidaVO = [];
//		    	$scope.menuState.Valid = true;
//		    	$scope.menuState.Invalid = false;
//		    	$scope.menuState.recomplementar = false;
//		    	$scope.menuState.noDeteccionesValidas = false;
//		   	 	$scope.menuState.deteccionesValidas = false;
//		   	 	$scope.disable = false;
		   	 	
//		   	 	$scope.deteccionesCentroRepartoValido($scope.dispFijoLoteVO.radarArchivoId);
//		   	 if($scope.all == false){
//		    	$scope.deteccionesCentroRepartoValido($scope.dispFijoLoteVO.radarArchivoId);
//		   	 }
		         break;
		     case 2:
//		    	$scope.cleanTextMSJ();
////		    	$scope.listaDeteccionValidaVO = [];
//		    	$scope.menuState.Invalid  = true;
//		    	$scope.menuState.Valid = false;
//		    	$scope.menuState.recomplementar = false;
//		    	$scope.menuState.noDeteccionesValidas = false;
//	   	 		$scope.menuState.deteccionesValidas = false;
//	   	 		$scope.disable = false;
//	   	 		$scope.muestraTabla = true;
//	   	 		if($scope.all == false){
//		    	$scope.deteccionesCentroRepartoInvalido($scope.dispFijoLoteVO.radarArchivoId);
//	   	 		}
		    		break;
		     case 3:
		    	 $scope.cleanTextMSJ();
		    	 if($scope.all == false){ 
		    		 
		    		 showAlert.confirmacion("Desea complementar nuevamente los Centros de Reparto para este archivo",
		    				 $scope.recomplementCentro);
		    		 
//		    	 $scope.showConfirmacion("Desea complementar nuevamente los Centros de Reparto para este archivo",function(){  $scope.recomplementCentro () });
		    	 }
		    	 	break;
		     case 4:
		    	 showAlert.confirmacion("Desea finalizar la reasignación de los Centros de Reparto para este archivo",
		    			 validardeteccionesCentroReparto);
		    	 
//		    	 $scope.showConfirmacion("Desea finalizar la reasignación de los Centros de Reparto para este archivo",function(){  validardeteccionesCentroReparto() });
		    	 
		    	 $scope.cleanTextMSJ();
		     	 if($scope.all == false){
		     		showAlert.confirmacion("Desea finalizar la reasignación de los Centros de Reparto para este archivo",
		     				validardeteccionesCentroReparto);
		     		
//		     		$scope.showConfirmacion("Desea finalizar la reasignación de los Centros de Reparto para este archivo",function(){  validardeteccionesCentroReparto() });
		    	 
		     	}
		    	 	break;
		 }

	};

	
	//aqui hay que trabajar
//	$scope.calcNumOfDetecciones = function(){
//		console.log('id lote: '+$scope.dispFijoLoteVO.radarArchivoId);
//		fmService.buscarDeteccionesFmCPValidas($scope.dispFijoLoteVO.radarArchivoId).success(function(data){
//			$scope.validDetect = data;
//			console.log(data);
//			$scope.sumValid = $scope.sumaDetecciones(data);
//			fmService.buscarDeteccionesFmCPInvalidas($scope.dispFijoLoteVO.radarArchivoId).success(function(data2){
//				$scope.notValidDetect = data2;
//				console.log(data2);
//				
//				$scope.sumInValid = $scope.sumaDetecciones(data2);
//				$scope.detectValidas=$scope.sumValid - $scope.sumInValid;
//				$scope.totalDetect=$scope.sumValid + $scope.sumInValid;
//				$scope.showIndexAll=true;
//				console.log($scope.detectValidas+" "+$scope.totalDetect);
//			}).error(function(){
//				$scope.error = data;
//			});
//		}).error(function(){
//			$scope.error = data;
//		});	
//	};
//	$scope.calcNumOfDetecciones();
	
	
	
//	$scope.cleanTextMSJ=function(){
//		$scope.showIndexAll=false;
//		$scope.showMsjValida=false;
//		$scope.showMsjInValida=false;
//	}
	
	
	
	
	$scope.deteccionesCentroRepartoValido = function(radarArchivo){
//		fmService.buscarDeteccionesFmCPValidas(radarArchivo).success(function(data){
//			$scope.listaDeteccionValidaVO = data;
//			$scope.sumValid = $scope.sumaDetecciones(data);
//			$scope.showMsjValida=true;
//		}).error(function(){
//			$scope.error = data;
//		})
	};
	
	$scope.deteccionesCentroRepartoInvalido =  function(radarArchivo){
		fmService.buscarDeteccionesFmCPInvalidas(radarArchivo).success(function(data){
			$scope.listaDeteccionValidaVO = data;
			$scope.sumInValid = $scope.sumaDetecciones(data);
			$scope.showMsjInValida=true;
		}).error(function(){
			$scope.error = data;
		})
	};
	
	function validardeteccionesCentroReparto(){
		$scope.listaDeteccionValidaVO = [];
	   	 $scope.menuState.recomplementar = false;
	   	 $scope.menuState.Invalid = false; 
	   	 $scope.menuState.Valid   = false;
	   	 $scope.menuState.noDeteccionesValidas = false;
	   	 $scope.menuState.deteccionesValidas = false;
	   	 $scope.menuState.recomplementar = false;
	   	 $scope.disable = true;
	   	 $scope.muestraTabla = false;
	   	 $scope.all = true;
	   	fmService.buscarDeteccionesFmCPValidas($scope.dispFijoLoteVO.radarArchivoId).success(function(data){
			$scope.listaDeteccionValidaVO = data;
			var isLleno = isEmpty(data);
			if (isLleno == false) {
				$scope.bandera = true;
				$scope.menuState.deteccionesValidas = true;
	    		codigoPostalReasignado();
	    		
		   	}else{
			$scope.banderaa = false;
			$scope.menuState.noDeteccionesValidas = true;
			$scope.cancelarProceso();
		    	
		   	}
		}).error(function(){
			$scope.error = data;
		})
	};
	
//	$scope.recomplementCentro = function(){
//		 $scope.listaDeteccionValidaVO = [];
//    	 $scope.menuState.Invalid = false; 
//    	 $scope.menuState.Valid   = false;
//    	 $scope.menuState.deteccionesValidas = false;
//    	 $scope.menuState.noDeteccionesValidas = false;
//    	 $scope.menuState.recomplementar = true;
//    	 $scope.disable = true;
//    	 $scope.muestraTabla = false;
//    	 $scope.all = true;
//		fmService.recomplementaCodigoPostal($scope.dispFijoLoteVO.radarArchivoId).success(function(data){
//			if(data != true){
//				$scope.showAviso("Hubo un problema al recomplementar al centro de reparto");
//				return false;
//			}
//			
//		}).error(function(){
//			$scope.error = data;
//		})
//	};
	
	function codigoPostalReasignado(){
		fmService.codigoPostalReasignado($scope.dispFijoLoteVO.radarArchivoId).success(function(data){
			if(data != true){
				$scope.showAviso("Hubo un problema al finalizar la peticion");
				return false;
			}
		}).error(function(){
			$scope.error = data;
		})	
	};
	

	$scope.radarCambiarCP = function(index, objetoCP, accion){
		$scope.dataCP.realCode = objetoCP.idDeteccion;
		$scope.dataCP.idCP = index;
		$scope.dataCP.codigoPost = objetoCP.codigoPostal;
		$scope.dataCP.centroReparto = objetoCP.centroReparto;
		
		if(accion == 'deshabilitar'){
			$scope.optionCP.cancelarCP = true;
			$scope.optionCP.deshabilitarCP = true;
			$scope.optionCP.habilitarCP = false;
			$scope.optionCP.actualCP = false;
			$scope.optionCP.newCP = false;
			
			$scope.confirmDesh = false;
			$scope.confirmHabil = false;
			
			$scope.confirmDeshButton = false;
		}else{
			$scope.optionCP.cancelarCP = true;
			$scope.optionCP.habilitarCP = true;
			$scope.optionCP.deshabilitarCP = false;
			$scope.optionCP.actualCP = true;
			$scope.optionCP.newCP = true;
			
			$scope.confirmHabil = false;
			$scope.confirmDesh = false;
			
			$scope.confirmDeshButton = false;
		}
		
		showDialogChangeCP();
	};
	
	
	
	
	
	/*
	 $scope.radarCambiarCP = function(index, objetoCP, accion){
		$scope.dataCP.realCode = objetoCP.idDeteccion;
		$scope.dataCP.idCP = index;
		$scope.dataCP.placa = objetoCP.placa;
		$scope.dataCP.fecha = objetoCP.fecha;
		$scope.dataCP.codigoPost = objetoCP.codigoPostal;
		
		if(accion == 'deshabilitar'){
			$scope.optionCP.cancelarCP = true;
			$scope.optionCP.deshabilitarCP = true;
			$scope.optionCP.habilitarCP = false;
			$scope.optionCP.actualCP = false;
			$scope.optionCP.newCP = false;
			
			$scope.confirmDesh = false;
			$scope.confirmHabil = false;
			
			$scope.confirmDeshButton = false;
		}else{
			$scope.optionCP.cancelarCP = true;
			$scope.optionCP.habilitarCP = true;
			$scope.optionCP.deshabilitarCP = false;
			$scope.optionCP.actualCP = true;
			$scope.optionCP.newCP = true;
			
			$scope.confirmHabil = false;
			$scope.confirmDesh = false;
			
			$scope.confirmDeshButton = false;
		}
		
		showDialogChangeCP();
	};
	 */
	
	showDialogChangeCP = function(){
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalFmEstatusCP.html',
			controller: 'modificaFmCPController',
        	scope: $scope
		}).then(function(modal) {
	        modal.element.modal();
		});
	};
	
	function isEmpty(obj) {
		
		for ( var key in obj) {
			if (obj.hasOwnProperty(key))
				
			return false;
		}
		return true;
	};
	
	$scope.cancelarProceso = function(){
		motivo = "";
		fmService.cancelaFmLote($scope.dispFijoLoteVO.radarArchivoId,motivo).success(function(data){
		
		}).error(function(){
			$scope.error = data;
		})
	};
		
});