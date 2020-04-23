angular.module('siidfApp').controller('modificaFmCPControllerDetail', function($scope, fmService, radaresProcesoService, ModalService) { 
	 $scope.show={btnAceptar:false, btnCambiaCP:true, btnCancelarCP:true};
	 $scope.thisNewCP=null;
	 $scope.sindatonewcp=false;
	 
	$scope.cambiaFmCP = function(){	
		var go=false;
		$scope.sindatonewcp=false;
		if($scope.thisNewCP!=null){//Validamos campo no  vacio
			if($scope.thisNewCP.trim()!=''){
				go=true;
			}
		}	
		if(go){
			$scope.showConfirmacion("¿Esta seguro de cambiar el código postal?",function(){
				$scope.aplicarCambios();
			});	
		}else{
			$scope.sindatonewcp=true;
		}
	};
	$scope.resetData= function(){
		document.getElementById("newCP").disabled = false;
		$scope.show.btnAceptar=false;
		$scope.show.btnCambiaCP=true;
		$scope.thisNewCP=null;
	}
	$scope.aplicarCambios = function(){
			var ids=[];
			console.log('este codigo postal actual: '+$scope.localVariables.thisCP);
			for(var i in $scope.globalData.deteccionesInvalid) {
				if($scope.globalData.deteccionesInvalid[i].codigoPostal==$scope.localVariables.thisCP){
					console.log('aqui esta el cp que buscas'+$scope.globalData.deteccionesInvalid[i].codigoPostal);
					var eliminar=[];
					for(var j in $scope.globalData.deteccionesInvalid[i].detecciones) {
						console.log('hay deteccion '+j);
						if($scope.globalData.deteccionesInvalid[i].detecciones[j].show==true){//Si estan chequeados
							
							ids.push($scope.globalData.deteccionesInvalid[i].detecciones[j].idDeteccion);
							
							
//							$scope.globalData.deteccionesInvalid[i].detecciones[j].modified=true;
//							$scope.globalData.deteccionesInvalid[i].detecciones[j].cpNew=document.getElementById("newCP").value;
//							$scope.globalData.deteccionesReasignadas.push($scope.globalData.deteccionesInvalid[i].detecciones[j]);
//							$scope.globalData.totalReasignacion++;
//							$scope.localVariables.totalDetecciones--;
//							$scope.globalData.deteccionesInvalid[i].totalDisabled--;
//							eliminar.push($scope.globalData.deteccionesInvalid[i].detecciones[j].codigoPostal);
						}
					}
//					for(var j in eliminar){
//						var index=eliminar.indexOf(eliminar[j]);
//						console.log('eliminas: '+index);
//						$scope.globalData.deteccionesInvalid[i].detecciones.splice(index,1);
//						$scope.globalData.totalInvalid--;
//					}
//					if($scope.globalData.deteccionesInvalid[i].detecciones.length<=0){
//						$scope.globalData.deteccionesInvalid.splice(i,1);
//						console.log('vas a cerrar la modal');
//						$('#modalReasignacionCP2').remove();
//					}
					
				}
			}
//			console.log('detecciones reasignadas');
//			console.log($scope.globalData.deteccionesReasignadas);
			console.log('se van a cambiar los cp de las siguientes detecciones:');
			console.log(ids);
			radaresProcesoService.modificaThisCP(radarArchivo,$scope.thisNewCP, ids).success(function(data){
				if(data){
					
					console.log('se van a cambiar los cp de las siguientes detecciones:');
					console.log(ids);
					$('#modalIdd').remove();
					$('#modalReasignacionCP2').remove();
					$('#modalReasignacionCP').remove();
					$('#modalReasignacionFMCP').remove();
					$('#confirmacion').remove();
					$('body').removeClass('modal-open');
					$('.modal-backdrop').remove();
					
//					$scope.showIndex.titleInicio=false;
//					$scope.showIndex.titleValidas=false;
//					$scope.showIndex.titleInvalidas=false;
//					$scope.showIndex.titleReasignadas=false;
//					$scope.showIndex.muestraTablaValidas=false;
//					$scope.showIndex.butnDown=false;
//					
//					$scope.globalData.totalDetect=0;
//					$scope.globalData.totalValid=0;
//					$scope.globalData.totalInvalid=0;
//					$scope.globalData.totalReasignacion=0;
//					$scope.globalData.currentCP=null;
//					$scope.globalData.posicionCP=null;
//					$scope.globalData.hayInvalidas=false;
//					$scope.globalData.deteccionesAll=null;
//					$scope.globalData.deteccionesValid=[];
//					$scope.globalData.deteccionesInvalid=[];
//					$scope.globalData.deteccionesReasignadas=[];
//					
//					$scope.temporalData.detecciones=null;
//					$scope.temporalData.cambiarCP=false;
//					$scope.getAllDetecciones();
//					alert('se cambiaron bien');
					
					$scope.stopUpdate();
					ModalService.showModal({
						templateUrl: 'views/templatemodal/templateModalReasignacionCP.html',
			        	controller: 'reasignacionCPController',
			        	scope: $scope
					}).then(function(modal) {
				        modal.element.modal();
				       
					});
				}
				
			}).error(function(){
				$scope.error = data;
			});
		
	}
	
	
	$scope.cerrar = function(banderaOpcion){
		if(banderaOpcion == 1){
			$scope.deteccionesCentroRepartoValido($scope.dispFijoLoteVO.radarArchivoId);
		}else{
			$scope.deteccionesCentroRepartoInvalido($scope.dispFijoLoteVO.radarArchivoId);
		}
		
		$('.modal-backdrop').remove();
		
	};
	
//	$scope.closeModal = function(){
//	   $scope.dataCP.newCP=null
//	   $scope.formCP.newCP.$pristine();
//	}
});