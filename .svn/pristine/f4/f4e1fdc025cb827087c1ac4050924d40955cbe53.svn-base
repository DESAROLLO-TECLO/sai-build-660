angular.module('siidfApp').controller('modifica_InfraccImpugnacionController', function($route,$scope,$routeParams,impugnaService,ModalService) {
    
	$scope.selected ={};
	$scope.selectedOption;
	$scope.selectedOption2;
	$scope.show = false;

	limpiaMensajes = function (){
		
		 $scope.infraccAlta = null;
		 $scope.form1.infraccAlta.$pristine = true;
		 $scope.infraccion1=null;
		 $scope.form2.infraccion.$pristine = true;
		 $scope.infraccion2=null;		
		 $scope.form3.infraccion2.$pristine = true;	
		 $scope.motivo=null;
		 $scope.form3.motivo.$pristine = true;
		 $scope.data2.selectedOption.id = "CA"; // Regresa el valor a cancelación
	}
	
	
	$scope.submitForm1 = function(infraccAlta,estatus) {
		if ($scope.form1.$valid && (infraccAlta.length ==8 || infraccAlta.length==11)){
		 var parametros =(
				 {tipo : "1",
					 impugnacionId : $scope.impugna[0].impugnacionId,
					 estatus : estatus.id,
					 infraccNum  : infraccAlta});
		 
		 impugnaService.modificaInfracc(parametros).success(function(data) {

			 if(data.resultado == -1){
				 showError(data.mensaje);
				 $scope.infraccAlta = "";
			 }else{
				 $scope.show = false;
				 showAviso(data.mensaje == 'Infraccion agregada correctamente' ?
						 'Infracción agregada correctamente' : data.mensaje);
				 limpiaMensajes();
			 }
		 }).error(function(data) {
			 
		 }); 
	
		}else{
			$scope.form1.infraccAlta.$invalid = true;
			$scope.form1.infraccAlta.$pristine = false;
		}
	}
	
	
	$scope.submitForm2 = function(estatus,infraccion1) {
		if ($scope.form2.$valid){
			console.log('Actualiza Infraccion');
			var parametros =({
				tipo : "2",
				impugnacionId : $scope.impugna[0].impugnacionId,
				estatus: estatus.id,
				infraccNum: infraccion1});
			
			impugnaService.modificaInfracc(parametros).success(function(data) {
				if(data.resultado == -1){
					showError(data.mensaje);
					$scope.infraccion1="";
				}else{
					$scope.show = false;
					showAviso(data.mensaje == 'Infracción actualizados correctamente'
						? 'Infración actualizada correctamente' : data.mensaje);
					limpiaMensajes();
				}
			}).error(function(data) {
				
			}); 
		}else{
			$scope.form2.infraccion.$invalid = true;
			$scope.form2.infraccion.$pristine = false;
		}
	}
	
	$scope.submitForm3 = function(infraccion2,motivo) {
		if ($scope.form3.$valid){
			console.log('Elimina Infraccion');
			var parametros =({
				tipo : "3",
				impugnacionId :  $scope.impugna[0].impugnacionId,
				estatus: motivo,
				infraccNum: infraccion2});
			
			impugnaService.modificaInfracc(parametros).success(function(data) {
				if(data.resultado == -1){
					showError(data.mensaje);
					$scope.infraccion2="";
					$scope.motivo = "";
				}else{
					$scope.show = false;
					showAviso(data.mensaje == 'Infraccion eliminada correctamente'
						? 'Infracción eliminada correctamente' :  data.mensaje);
					limpiaMensajes();
				}
				$scope.error = false;
			}).error(function(data) {
				$scope.error = data;
			});
		}else{
			angular.forEach($scope.form3.$error.required, function(field) {
				field.$setDirty();
			});
		}
	}
	
	obtenerInfracciones = function() {
		impugnaService.obtenerInfracciones($routeParams.id.toString()).success(function(data) {
			$scope.impInfraccion = data;
		}).error(function(data) {
			$scope.impInfraccion  = {};
		});
	}
	
	obtenerInfraccionesAlta = function(id) {
		impugnaService.obtenerInfracciones(id).success(function(data) {
			if (data.length == 0){
				$scope.impInfraccion  = {};
			}else{
				$scope.impInfraccion = data; 
		   }
		}).error(function(data) {
			$scope.impInfraccion  = {};
		});
	}
	
	buscarImpugnacionPorId = function(id) {
		impugnaService.buscarImpugnacionPorId($routeParams.id.toString()).success(function(data) {
			$scope.impugna = data;
		}).error(function(data) {
			$scope.impugna  = {};
		});
	}

    $scope.obtieneInfraccion = function() {
    	$scope.infraccion1 = $scope.infraccionVal;    	
    }
    
    $scope.obtieneInfraccion2 = function() {   	
    	$scope.infraccion2 = $scope.infraccionVal;  	
    }
    
    $scope.agregar = function(val,stat){ // Se agregó stat para visualizar en el combo el estatus de la infraccion seleccionada
    	$scope.infraccionVal = val;
    	$scope.infraccion1 = $scope.infraccionVal;
    	$scope.infraccion2 = $scope.infraccionVal;

    	$scope.data2.selectedOption.id = stat; // Selecciona el estatus de la infraccion
	}

    $scope.selectedOption;
    $scope.data = {
		availableOptions: [
		                   {id: 'CA', name: 'Cancelación'},
		                   {id: 'SN', name: 'Suspensión Negada'},
		                   {id: 'SP', name: 'Suspensión Provisional'},
		                   {id: 'SD', name: 'Suspensión Definitiva'}
		                   ],
       selectedOption: {
    	   id: 'CA', name: 'Cancelación'} //This sets the default value of the select in the ui
     };
    
    $scope.data2 = {
	     availableOptions: [
	                        {id: 'CA', name: 'Cancelación'},
	                        {id: 'SN', name: 'Suspensión Negada'},
	                        {id: 'SP', name: 'Suspensión Provisional'},
	                        {id: 'SD', name: 'Suspensión Definitiva'},
	                        {id: 'RE', name: 'Reactiva'}
	                        ],
        selectedOption: {
        	id: 'CA', name: 'Cancelación'} //This sets the default value of the select in the ui
    };
    
    buscarImpugnacionesPorNumeroJuicio = function() {
   	impugnaService.buscarImpugnaciones('numJuicio', $routeParams.id).success(function(data) {
   	 obtenerInfraccionesAlta(data[0].impugnacionId.toString());
   		   $scope.impugna = data;  		  
		}).error(function(data) {
			$scope.impugna = {};
		});
	};
    
    evaluaParametro =  function() {	
    	if(/^([0-9])*$/.test($routeParams.id)){
    		obtenerInfracciones();
            buscarImpugnacionPorId();
        }else{
       
        	buscarImpugnacionesPorNumeroJuicio();        	
        }  	
	};
	
	
	showAviso = function(messageTo) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalAviso.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
		}).then(function(modal) {
			modal.element.modal();
        });
		evaluaParametro();
	};
      
      showError = function(messageTo) {
          ModalService.showModal({
            templateUrl: 'views/templatemodal/templateModalError.html',
            controller: 'mensajeModalController',
                inputs:{ message: messageTo}
          }).then(function(modal) {
            modal.element.modal();
          });
        };
        
     $scope.$on('$locationChangeSuccess', function(event, current, previous) {
    	 $scope.proximoController = $route.current.$$route.controller;
    	 if($scope.proximoController != 'impugnaController' && $scope.proximoController != 'modifica_ImpugnacionController'){
    		 $scope.ListaImpugnacion ={};
    		 impugnaService.setListaImpugnacion($scope.ListaImpugnacion);
		 }
    });
     
	evaluaParametro();
});


