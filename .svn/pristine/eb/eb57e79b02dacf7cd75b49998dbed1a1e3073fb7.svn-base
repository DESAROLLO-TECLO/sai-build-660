angular.module('siidfApp').controller('modificaRADDetailCPController', function($scope, fmService, ModalService, $filter){ 
	$scope.localVariables={totalDetecciones:0, objetoDetecciones:null, thisCP:null, thisposicionCP:null};
	
	
	$scope.loadDeteccion = function(){
		console.log('llegaste aqui ja');
		$scope.localVariables.totalDetecciones=$scope.temporalData.detecciones.length;
		
		$scope.localVariables.thisCP=$scope.globalData.currentCP;
		$scope.localVariables.thisposicionCP=$scope.globalData.posicionCP;
		$scope.localVariables.objetoDetecciones= $scope.temporalData.detecciones;
		console.log('estas detetet');
		console.log($scope.localVariables.objetoDetecciones);
		for(var i in $scope.localVariables.objetoDetecciones){
			$scope.localVariables.objetoDetecciones[i].show=false;
		}
	}
	$scope.loadDeteccion();
	
	//Funcionalidad para el chekeo (selección) de items
	$scope.items=[];
    $scope.valorItem;
    $scope.valorallitems=false;
	
	$scope.fillAllItems = function(valor){		
		$scope.cambiaValor(valor); 
    	$scope.pintaItems();
    };
    
	
    $scope.fillItems = function(item){    
    	$scope.valorallitems=false;
    	document.getElementById("chekid").checked = false;
        $scope.pintaItems();
        
    };
    
    $scope.cambiaValor = function(valor){
    	console.log('estas en cambia valor')
        for (var i=0; i < $scope.localVariables.objetoDetecciones.length; i++) {
        	$scope.localVariables.objetoDetecciones[i].show=valor;
        }
    };
    
    $scope.pintaItems=function(){
        console.log("estas en pinta itemss");
        $scope.items=[];
        for (var i=0; i < $scope.localVariables.objetoDetecciones.length; i++) {
            if( $scope.localVariables.objetoDetecciones[i].show===true){
                $scope.items.push($scope.localVariables.objetoDetecciones[i]);
            }
        }
    };
  //Fin de funcionalidad para el chekeo (selección) de items
    
    $scope.disableDetecciones = function(){
    	console.log('aqui vas a imprimir el objetoo a tratar de as detecciones');
    	console.log($scope.temporalData.detecciones);
    	
    	if($scope.items.length>0){
    		$scope.showConfirmacion("¿Esta seguro de deshabilitar las detecciones seleccionadas?",function(){  $scope.disableDeteccionesCommit () });
    	}else{
    		$scope.showConfirmacion("Debes seleccionar al menos una detección a modificar",function(){ });
	    	 
    	}
    };
    
    $scope.updateDetecciones = function(){
    	console.log('Obtendras invalidas e invalidas');
    	var deteccionesInvalid_temp=[];
    	var deteccionesValid_temp=[];
    	//Dividimos en 2 arrays de detecciones válidas e inválidas
		for(var i in $scope.globalData.deteccionesValid) {
			
			var tempInvalid={idDeteccion:$scope.globalData.deteccionesValid[i].idDeteccion,
					codigoPostal:$scope.globalData.deteccionesValid[i].codigoPostal,
					centroReparto:$scope.globalData.deteccionesValid[i].centroReparto, 
					municipio:$scope.globalData.deteccionesValid[i].municipio, 
					entidadFederativa:$scope.globalData.deteccionesValid[i].entidadFederativa,
					detecciones:[], 
					hayDisabled:$scope.globalData.deteccionesValid[i].hayDisabled, 
					totalDisabled:$scope.globalData.deteccionesValid[i].totalDisabled
					};
			
			var tempValid={idDeteccion:$scope.globalData.deteccionesValid[i].idDeteccion,
					codigoPostal:$scope.globalData.deteccionesValid[i].codigoPostal,
					centroReparto:$scope.globalData.deteccionesValid[i].centroReparto, 
					municipio:$scope.globalData.deteccionesValid[i].municipio, 
					entidadFederativa:$scope.globalData.deteccionesValid[i].entidadFederativa,
					detecciones:[], 
					hayDisabled:$scope.globalData.deteccionesValid[i].hayDisabled, 
					totalDisabled:$scope.globalData.deteccionesValid[i].totalDisabled
					};
				
				if($scope.globalData.deteccionesValid[i].hayDisabled==true){
					
					
									
					for(var j in $scope.globalData.deteccionesValid[i].detecciones) {
						if($scope.globalData.deteccionesValid[i].detecciones[j].disabled==true){
							tempInvalid.detecciones.push($scope.globalData.deteccionesValid[i].detecciones[j]);
						}else{
							tempValid.detecciones.push($scope.globalData.deteccionesValid[i].detecciones[j]);
						}
					}
					if(tempInvalid.detecciones.length>0){
						deteccionesInvalid_temp.push(tempInvalid);
					}
					if(tempValid.detecciones.length>0){
						deteccionesValid_temp.push(tempValid);
					}
					
					
					
				}else{
					
					for(var j in $scope.globalData.deteccionesValid[i].detecciones) {
						if($scope.globalData.deteccionesValid[i].detecciones[j].disabled==true){
							tempInvalid.detecciones.push($scope.globalData.deteccionesValid[i].detecciones[j]);
						}else{
							tempValid.detecciones.push($scope.globalData.deteccionesValid[i].detecciones[j]);
						}
					}
					if(tempInvalid.detecciones.length>0){
						deteccionesInvalid_temp.push(tempInvalid);
					}
					if(tempValid.detecciones.length>0){
						deteccionesValid_temp.push(tempValid);
					}
					
				}	
			
		}
		$scope.globalData.deteccionesValid=deteccionesValid_temp;
		$scope.mergeDeteccionesInvalidas(deteccionesInvalid_temp);
	}
    
    $scope.mergeDeteccionesInvalidas = function(deteccionesInvalidas){
    	console.log(deteccionesInvalidas);
    	console.log('vas a planchar invalidas');
    	for(var i in deteccionesInvalidas) {	
    		console.log('entraste al for i');
    		if($scope.globalData.deteccionesInvalid<=0){
    			$scope.globalData.deteccionesInvalid.push(deteccionesInvalidas[i]);
    		}else{
    			for(var j in $scope.globalData.deteccionesInvalid) {
        			console.log('entraste al for j');
        			console.log('comparamos esto: '+$scope.globalData.deteccionesInvalid[j].codigoPostal+
        					' con esto: '+deteccionesInvalidas[i].codigoPostal);
        			if($scope.globalData.deteccionesInvalid[j].codigoPostal==deteccionesInvalidas[i].codigoPostal){
        				console.log('son igules');
        				for(var k in deteccionesInvalidas[i].detecciones){
        					$scope.globalData.deteccionesInvalid[j].detecciones.push(deteccionesInvalidas[i].detecciones[k]);
        				}        				
        				
        			}else{
        				console.log('NO son igules');
        				console.log('insertamos esto');
        				console.log(deteccionesInvalidas[i]);
        				console.log('fin insertamos esto');
        				$scope.globalData.deteccionesInvalid.push(deteccionesInvalidas[i]);
        				
        			}
        			//Actualizamos cantidad de detecciones
        			console.log('cantidad a asignar:'+$scope.globalData.deteccionesInvalid[j].detecciones.length);
        			console.log('cantidad actual: '+$scope.globalData.deteccionesInvalid[j].idDeteccion);
        			$scope.globalData.deteccionesInvalid[j].totalDisabled=$scope.globalData.deteccionesInvalid[j].detecciones.length;
        			break;
        		}
    			
    		}
    		
    		
    	}
    	console.log($scope.globalData.deteccionesInvalid)
    	console.log($scope.globalData.deteccionesValid)
    	console.log('Terminas de planchar invalidas');
    }
    
    $scope.disableDeteccionesCommit = function(){
    	console.log('harás disableDeteccionesCommit');
    	for(var i in $scope.temporalData.detecciones) {
    		if($scope.temporalData.detecciones[i].show==true){//si seleccionamos este item lo deshabilita
    			//Deshabilitamos la deteccion
    			$scope.localVariables.objetoDetecciones[i].disabled=true;
    			//Ponemos marcas sobre el grupo contenedor y actualizamos sus registroos
    			$scope.globalData.hayInvalidas=true;
    			$scope.globalData.deteccionesValid[$scope.localVariables.thisposicionCP].hayDisabled=true;
    			$scope.globalData.deteccionesValid[$scope.localVariables.thisposicionCP].idDeteccion-=1;
    			$scope.globalData.deteccionesValid[$scope.localVariables.thisposicionCP].totalDisabled+=1;
    			$scope.globalData.totalInvalid+=1;
    		}
    	}
    	$scope.globalData.totalValid=$scope.globalData.totalDetect-$scope.globalData.totalInvalid;
    	$scope.globalData.deteccionesValid[$scope.localVariables.thisposicionCP].detecciones=$scope.temporalData.detecciones;
    	$('#modalReasignacionCP2').remove();
    	ModalService.showModal({
	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
	        controller: 'mensajeModalController',
	        	inputs:{ message: "Se han deshabilitado las detecciones satisfactoriamente"}
	    }).then(function(modal) {
	        modal.element.modal();
	        modal.close.then(function(result) {
	        	
	        }); 
	    });
    	$('#modalReasignacionCP2').remove();
    	$scope.updateDetecciones();
    }
    
    $scope.cambiarCP = function(){
    	if($scope.items.length==0){
    		$scope.showConfirmacion("Debes seleccionar al menos una detección a modificar",function(){ });
    	}else{
    		
			ModalService.showModal({
				templateUrl: 'views/templatemodal/templateModalFmEstatusCP.html',
				controller: 'modificaFmCPControllerDetail',
	        	scope: $scope
			}).then(function(modal) {
		        modal.element.modal();
			});
    	}
    }
	/***********************/
    
    $scope.showDialogChangeCP = function(){
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalFmEstatusCP.html',
			controller: 'modificaFmCPControllerDetail',
        	scope: $scope
		}).then(function(modal) {
	        modal.element.modal();
		});
	};
	
	$scope.cerrar = function(banderaOpcion){
		if(banderaOpcion == 1){
			$scope.deteccionesCentroRepartoValido($scope.dispFijoLoteVO.radarArchivoId);
		}else{
			$scope.deteccionesCentroRepartoInvalido($scope.dispFijoLoteVO.radarArchivoId);
		}
		
		$('.modal-backdrop').remove();
		
	};
	
	$scope.closeModal = function(){
	   $scope.dataCP.newCP=null
	   $scope.formCP.newCP.$pristine();
	}
});