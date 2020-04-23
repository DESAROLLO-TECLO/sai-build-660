angular.module('siidfApp').controller('reasignacionCPController', function($scope, radaresProcesoService, ModalService, showAlert, $filter) {
	
	$scope.showIndex={
		titleInicio:false, 
		titleValidas:false, 
		titleInvalidas:false, 
		titleReasignadas:false,
		muestraTablaValidas:false, 
		tablasValidasInvalidas:true, 
		tablasDetail:false, 
		vistaCambiaCP:false,
		butnDown:false
	};
	
	$scope.globalData={
		totalDetect:0, 
		totalValid:0, 
		totalInvalid:0, 
		totalReasignacion:0, 
		currentCP:null, 
		posicionCP:null, 
		hayInvalidas:false,
		deteccionesAll:null, 
		deteccionesValid:[], 
		deteccionesInvalid:[],
		deteccionesReasignadas:[]
	};
	
	$scope.temporalData={
		detecciones:null,
		cambiarCP:false
	};
	
	//Obtiene todas las detecciones y la cantidad de ellas. 
	$scope.getAllDetecciones = function(){
		radaresProcesoService.buscarAllDeteccionesRAD(radarArchivo).success(function(dataDet){
			$scope.globalData.deteccionesAll = dataDet;
//			console.log($scope.globalData.deteccionesAll);
			$scope.showIndex.titleInicio=true;	
			$scope.formateoDatos();
			$scope.updateDeteccionesValidInvalid();
		}).error(function(){
			$scope.error = data;
		});	
	};
	
	$scope.getAllDetecciones();
	
	var contador=0;
	$scope.formateoDatos = function(){
		//formateamos los datos para su manipulacion
		for(var i in $scope.globalData.deteccionesAll) { 
			if(!isNaN(i)){
				$scope.globalData.deteccionesAll[i].hayDisabled = false;
				$scope.globalData.deteccionesAll[i].totalDisabled = 0;
				for(var j in $scope.globalData.deteccionesAll[i].detecciones) {
					if(!isNaN(j)){
						//Agrego atributos a usar
						$scope.globalData.deteccionesAll[i].detecciones[j].show = false;
						$scope.globalData.deteccionesAll[i].detecciones[j].modified = false;
						$scope.globalData.deteccionesAll[i].detecciones[j].disabled = false;
						$scope.globalData.deteccionesAll[i].detecciones[j].cpNew = null;
						$scope.globalData.deteccionesAll[i].detecciones[j].crNew = null;		
						$scope.globalData.totalDetect += 1;
					}
				}
				contador = contador + $scope.globalData.deteccionesAll[i].detecciones.length;
//				console.log('total: '+$scope.globalData.deteccionesAll[i].detecciones.length);
//				console.log(contador);
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
			if(isNaN(i)) break;
			//cuantas sin centro de reparto
//		    if($scope.globalData.deteccionesAll[i].centroReparto==0){
//		    	console.log('no tiene cr el cp');
			for(var j in $scope.globalData.deteccionesAll[i].detecciones) {
				if(isNaN(j)) break;
//		    		console.log($scope.globalData.deteccionesAll[i].detecciones[j].centroReparto);
				if($scope.globalData.deteccionesAll[i].detecciones[j].centroReparto == 0){
//		    			console.log('no tiene cr la deteccion');
					$scope.globalData.deteccionesAll[i].detecciones[j].disabled = true;//desabilitamos deteccion
					$scope.globalData.deteccionesAll[i].hayDisabled = true;//ponemos marca en el grupo de CP
					$scope.globalData.hayInvalidas = true;//Ponemos marca en todo el JSON para indicar q hay invalidad
//			    	$scope.globalData.deteccionesAll[i].idDeteccion-=1;
					$scope.globalData.deteccionesAll[i].totalDisabled += 1;
//			    	$scope.globalData.totalInvalid+=1;
				}
			}	    	
//		}
			//Cuantas deshabilitadas
			if($scope.globalData.deteccionesAll[i].hayDisabled==true){
//		    	console.log('si hay deshabilitadas');
				for(var j in $scope.globalData.deteccionesAll[i].detecciones) {
					if(isNaN(j)) break;
					if($scope.globalData.deteccionesAll[i].detecciones[j].disabled==true){
//						console.log('concide si hay deshabilitadas');
						$scope.globalData.totalInvalid+=1;
					}
				}
			}
		}
		
		$scope.globalData.totalValid = $scope.globalData.totalDetect - $scope.globalData.totalInvalid;
		
		//Dividimos en 2 arrays de detecciones válidas e inválidas
		for(var i in $scope.globalData.deteccionesAll) {
			if(isNaN(i)) break;
			if($scope.globalData.deteccionesAll[i].hayDisabled==true){
//					console.log('si hay invalidas');
				var tempInvalid = {
					idDeteccion:$scope.globalData.deteccionesAll[i].idDeteccion, 
					codigoPostal:$scope.globalData.deteccionesAll[i].codigoPostal, 
					centroReparto:$scope.globalData.deteccionesAll[i].centroReparto, 
					municipio:$scope.globalData.deteccionesAll[i].municipio, 
					entidadFederativa:$scope.globalData.deteccionesAll[i].entidadFederativa, 
					detecciones:[], 
					hayDisabled:$scope.globalData.deteccionesAll[i].hayDisabled, 
					totalDisabled:$scope.globalData.deteccionesAll[i].totalDisabled
				};
									
				for(var j in $scope.globalData.deteccionesAll[i].detecciones) {
					if(isNaN(j)) break;
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
				var tempValid = {
					idDeteccion:$scope.globalData.deteccionesAll[i].idDeteccion,
					codigoPostal:$scope.globalData.deteccionesAll[i].codigoPostal,
					centroReparto:$scope.globalData.deteccionesAll[i].centroReparto, 
					municipio:$scope.globalData.deteccionesAll[i].municipio, 
					entidadFederativa:$scope.globalData.deteccionesAll[i].entidadFederativa,
					detecciones:[], 
					hayDisabled:$scope.globalData.deteccionesAll[i].hayDisabled, 
					totalDisabled:$scope.globalData.deteccionesAll[i].totalDisabled
				};
				
				for(var j in $scope.globalData.deteccionesAll[i].detecciones) {
					if(isNaN(j)) break;
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
//		console.log($scope.globalData.deteccionesInvalid);
//		console.log($scope.globalData.deteccionesValid);
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
				showAlert.confirmacion("Desea complementar nuevamente los Centros de Reparto para este archivo",
						$scope.recomplementCentro);
				$('body').removeClass('modal-open');
				$('.modal-backdrop').remove();
//		    	 if($scope.globalData.totalReasignacion>0){
//		    		 showAlert.confirmacion("Desea complementar nuevamente los Centros de Reparto para este archivo",
//		    				 $scope.recomplementCentro);
////		    		 $scope.showConfirmacion("Desea complementar nuevamente los Centros de Reparto para este archivo",
////		    				 function(){  $scope.recomplementCentro () });
//		    	 }else{
//		    		 alert('Para recomplementar Centros de Reparto primero deben reasignarse CP al menos a una detección');
//		    	 }
				break;
			case 4:
				if($scope.globalData.totalReasignacion>0){
					showAlert.confirmacion(
						"Si finalizas la reasignación se perderán los cambios realizados realizados en "+$scope.globalData.totalReasignacion+
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
//		console.log('este cp: '+$scope.globalData.currentCP);
//		console.log('esta posicion: '+$scope.globalData.posicionCP);
		$scope.temporalData.detecciones=detecciones;
		$scope.temporalData.cambiarCP=cambiarCP;

		//$scope.showDetail();
		$scope.showDetail2();
	};
	//gib
	$scope.showDetail2 = function(){
		$scope.showIndex.tablasDetail=true;
		$scope.showIndex.tablasValidasInvalidas=false;
		$scope.showIndex.vistaCambiaCP=false;
		$scope.loadDeteccion();
	}
	
//	showDialogChangeCP = function(){
////		ModalService.showModal({
////			templateUrl: 'views/templatemodal/templateModalEstatusCP.html',
////			controller: 'modificaCPController',
////        	scope: $scope
////		}).then(function(modal) {
////	        modal.element.modal();
////		});
////	};
	 
	$scope.showDetail = function(){
		ModalService.showModal({ 
			templateUrl: 'views/templatemodal/templateModaLRADDetailCP.html',
			controller: 'modificaRADDetailCPController',
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
		radaresProcesoService.codigoPostalReasignado(radarArchivo).success(function(data){
			if(data != true){
				$scope.showAviso("Hubo un problema al recomplementar al centro de reparto");
				return false;
			}else{				
//				for(var i=0; i<3;i++){
//					$('#modalIdd').remove();
//					$('#modalReasignacionCP2').remove();
//					$('#modalReasignacionCP').remove();
//					$('#modalReasignacionFMCP').remove();
//					$('#confirmacion').remove();
//				}
				$('body').removeClass('modal-open');
				$('.modal-backdrop').remove();
			}
		}).error(function(){
			$scope.error = data;
		});
	};
	
	function isEmpty(obj) {
		for ( var key in obj) {
			if (obj.hasOwnProperty(key))
				return false;
		}
		return true;
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
//   	 console.log('este id: '+radarArchivo);
		radaresProcesoService.listaDeteccionRepartoValida(radarArchivo).success(function(data){
			$scope.listaDeteccionValidaVO = data;
			var isLleno = isEmpty(data);
			if (isLleno == false) {
//			console.log('es falso');
				$scope.bandera = true;
				$scope.menuState.deteccionesValidas = true;
				codigoPostalReasignado();
			}else{
//	   		console.log('es verdadero');
				$scope.bandera = false;
				$scope.menuState.noDeteccionesValidas = true;
				$scope.cancelarProceso();
			}
		}).error(function(){
			$scope.error = data;
		})
	};

	function codigoPostalReasignado(){
		radaresProcesoService.codigoPostalReasignado2(radarArchivo).success(function(data){
			if(data != true){
				$scope.showAviso("Hubo un problema al finalizar la peticion");
				return false;
			}
		}).error(function(){
			$scope.error = data;
		})	
	};
	
	$scope.getVal = function(){
		if($scope.opcion == 2){
			$scope.viewFilters = true;
			$scope.viewByT = true;
			$scope.viewAll = false;
			$scope.viewInformation = false;		
		}else{
			$scope.viewFilters = false;
			$scope.viewByT = false;
			$scope.viewAll = true;
			$scope.viewInformation = false;	
		}	
	}
	
	$scope.getVal();
	$scope.localVariables={
		totalDetecciones:0, 
		objetoDetecciones:null, 
		thisCP:null, 
		thisposicionCP:null
	};
	
	$scope.loadDeteccion = function(){
		console.log('llegaste aqui ja');
		$scope.localVariables.totalDetecciones=$scope.temporalData.detecciones.length;
		
		$scope.localVariables.thisCP=$scope.globalData.currentCP;
		$scope.localVariables.thisposicionCP=$scope.globalData.posicionCP;
		$scope.localVariables.objetoDetecciones= $scope.temporalData.detecciones;
		console.log('estas detetet');
		console.log($scope.localVariables.objetoDetecciones);
		for(var i in $scope.localVariables.objetoDetecciones){
			if(isNaN(i)) break;
			$scope.localVariables.objetoDetecciones[i].show=false;
		}
	}
	
	$scope.cancelarDetail = function(){
		$scope.showIndex.tablasDetail=false;
		$scope.showIndex.tablasValidasInvalidas=true;
		$scope.showIndex.vistaCambiaCP=false;
	}
	
	$scope.camcelarVistaCP=function(){
		$scope.showIndex.tablasDetail=false;
		$scope.showIndex.tablasValidasInvalidas=true;
		$scope.showIndex.vistaCambiaCP=false;
	}
	
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
			if(isNaN(i)) break;
			var tempInvalid={
				idDeteccion:$scope.globalData.deteccionesValid[i].idDeteccion,
				codigoPostal:$scope.globalData.deteccionesValid[i].codigoPostal,
				centroReparto:$scope.globalData.deteccionesValid[i].centroReparto, 
				municipio:$scope.globalData.deteccionesValid[i].municipio, 
				entidadFederativa:$scope.globalData.deteccionesValid[i].entidadFederativa,
				detecciones:[], 
				hayDisabled:$scope.globalData.deteccionesValid[i].hayDisabled, 
				totalDisabled:$scope.globalData.deteccionesValid[i].totalDisabled
			};
			
			var tempValid={
				idDeteccion:$scope.globalData.deteccionesValid[i].idDeteccion,
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
					if(isNaN(j)) break;
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
					if(isNaN(j)) break;
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
			if(isNaN(i)) break;
			console.log('entraste al for i');
			if($scope.globalData.deteccionesInvalid<=0){
				$scope.globalData.deteccionesInvalid.push(deteccionesInvalidas[i]);
			}else{
				for(var j in $scope.globalData.deteccionesInvalid) {
					if(isNaN(j)) break;
					console.log('entraste al for j');
					console.log('comparamos esto: '+$scope.globalData.deteccionesInvalid[j].codigoPostal+
							' con esto: '+deteccionesInvalidas[i].codigoPostal);
					if($scope.globalData.deteccionesInvalid[j].codigoPostal==deteccionesInvalidas[i].codigoPostal){
						console.log('son igules');
						for(var k in deteccionesInvalidas[i].detecciones){
							if(isNaN(k)) break;
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
			if(isNaN(i)) break;
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
				$scope.cancelarDetail();
				$scope.cambiaValor(false);
				document.getElementById("chekid").checked = false;
				$scope.updateDetecciones();
			}); 
		});
	}

	$scope.cambiarCP = function(){
		if($scope.items.length==0){
			$scope.showConfirmacion("Debes seleccionar al menos una detección a modificar",function(){ });
		}else{
			$scope.showIndex.vistaCambiaCP=true;		
			$scope.showIndex.tablasDetail=false;
			$scope.showIndex.tablasValidasInvalidas=false;
//			ModalService.showModal({
//				templateUrl: 'views/templatemodal/templateModalFmEstatusCP.html',
//				controller: 'modificaFmCPControllerDetail',
//	        	scope: $scope
//			}).then(function(modal) {
//		        modal.element.modal();
//			});
		}
	}
	
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
			if(isNaN(i)) break;
			if($scope.globalData.deteccionesInvalid[i].codigoPostal==$scope.localVariables.thisCP){
				console.log('aqui esta el cp que buscas'+$scope.globalData.deteccionesInvalid[i].codigoPostal);
				var eliminar=[];
				for(var j in $scope.globalData.deteccionesInvalid[i].detecciones) {
					if(isNaN(j)) break;
					console.log('hay deteccion '+j);
					if($scope.globalData.deteccionesInvalid[i].detecciones[j].show==true){//Si estan chequeados
						ids.push($scope.globalData.deteccionesInvalid[i].detecciones[j].idDeteccion);
						
//						$scope.globalData.deteccionesInvalid[i].detecciones[j].modified=true;
//						$scope.globalData.deteccionesInvalid[i].detecciones[j].cpNew=document.getElementById("newCP").value;
//						$scope.globalData.deteccionesReasignadas.push($scope.globalData.deteccionesInvalid[i].detecciones[j]);
//						$scope.globalData.totalReasignacion++;
//						$scope.localVariables.totalDetecciones--;
//						$scope.globalData.deteccionesInvalid[i].totalDisabled--;
//						eliminar.push($scope.globalData.deteccionesInvalid[i].detecciones[j].codigoPostal);
					}
				}
//				for(var j in eliminar){
//					var index=eliminar.indexOf(eliminar[j]);
//					console.log('eliminas: '+index);
//					$scope.globalData.deteccionesInvalid[i].detecciones.splice(index,1);
//					$scope.globalData.totalInvalid--;
//				}
//				if($scope.globalData.deteccionesInvalid[i].detecciones.length<=0){
//					$scope.globalData.deteccionesInvalid.splice(i,1);
//					console.log('vas a cerrar la modal');
//					$('#modalReasignacionCP2').remove();
//				}
			}
		}
//		console.log('detecciones reasignadas');
//		console.log($scope.globalData.deteccionesReasignadas);
		console.log('se van a cambiar los cp de las siguientes detecciones:');
		console.log(ids);
		
		radaresProcesoService.modificaThisCP(radarArchivo,$scope.thisNewCP, ids).success(function(data){
			if(data){
				console.log('se van a cambiar los cp de las siguientes detecciones:');
				console.log(ids);
				$scope.showIndex.tablasDetail=false;
				$scope.showIndex.tablasValidasInvalidas=true;
				$scope.showIndex.vistaCambiaCP=false;
				$('body').removeClass('modal-open');
				$('.modal-backdrop').remove();
//				$scope.getAllDetecciones();
				$('#modalIdd').remove();
				$('#modalReasignacionCP2').remove();
				$('#modalReasignacionCP').remove();
				$('#modalReasignacionFMCP').remove();
				$('#confirmacion').remove();
				$('body').removeClass('modal-open');
				$('.modal-backdrop').remove();
				
//				$scope.showIndex.titleInicio=false;
//				$scope.showIndex.titleValidas=false;
//				$scope.showIndex.titleInvalidas=false;
//				$scope.showIndex.titleReasignadas=false;
//				$scope.showIndex.muestraTablaValidas=false;
//				$scope.showIndex.butnDown=false;
//				
//				$scope.globalData.totalDetect=0;
//				$scope.globalData.totalValid=0;
//				$scope.globalData.totalInvalid=0;
//				$scope.globalData.totalReasignacion=0;
//				$scope.globalData.currentCP=null;
//				$scope.globalData.posicionCP=null;
//				$scope.globalData.hayInvalidas=false;
//				$scope.globalData.deteccionesAll=null;
//				$scope.globalData.deteccionesValid=[];
//				$scope.globalData.deteccionesInvalid=[];
//				$scope.globalData.deteccionesReasignadas=[];
//				
//				$scope.temporalData.detecciones=null;
//				$scope.temporalData.cambiarCP=false;
//				alert('se cambiaron bien');
//				$scope.stopUpdate();
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
});