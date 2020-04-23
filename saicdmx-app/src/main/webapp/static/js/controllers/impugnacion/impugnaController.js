angular.module('siidfApp').controller('impugnaController', function($scope, $filter, impugnaService,$location,$routeParams,ModalService,$route) {
	$scope.totalRegistros = null;
	$scope.mostrarTabla= false;
	$scope.view = {};

	$scope.buscarImpugnaciones = function(tipoBusqueda, valor) {
	if($scope.formImpugna.$valid){
		
		impugnaService.buscarImpugnaciones(tipoBusqueda, valor).success(function(data) {	
			
			$scope.impugnacion = data;
			$scope.totalRegistros = data.length;
			$scope.error = false;
			$scope.mostrarTabla= true;
			
		}).error(function(data) {
			$scope.mostrarTabla= false;
		    showAviso(data.message);
			$scope.totalRegistros = null;
			
		});
		
	  }else{
		  $scope.formImpugna.valor.$invalid=true;
		  $scope.formImpugna.valor.$pristine=false;
	  }	
	}
	
	  $scope.verInfraccionesModifica = function(impugna) { 
		     $location.path('/modificaImpugnacion/'+impugna[0].impugnacionId);
		  };
			
	
	 $scope.verInfracciones = function(impugna) {	  
	    	$location.path('/modifica_InfraccImpugnacion/'+parseInt(impugna[0].impugnacionId));
	  };
	  
	  $scope.verDetalleConsulta = function(impugna) {
		     $location.path('/detalleImpugnacion/'+parseInt(impugna[0].impugnacionId));
		  };
		  
	  $scope.modificaInformacion = function(impugna) {
			     $location.path('/modificaImpugnacion/'+parseInt(impugna[0].impugnacionId));
		 };

	  $scope.selectedOption;
	    $scope.data = {
	     availableOptions: [
	       {id: 'numJuicio', name: 'No. de Juicio'},
	       {id: 'numOficioJuridico', name: 'No. Oficio Jurídico'}
	       
	     ],
	     selectedOption: {id: 'numJuicio', name: 'No. Oficio Jurídico'} //This sets the default value of the select in the ui
	     };
	    
	    verificaLista = function() {
		   		   
		   if (impugnaService.getListaImpugnacion().length > 0 ) {
			  $scope.impugnacion = impugnaService.getListaImpugnacion();
			  $scope.mostrarTabla= true;
			  $scope.view.rowsPerPage = impugnaService.getRowsPerPage();
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
	          if( $scope.proximoController == 'detalle_impugnacionController'  ){
	        	 
	        	  impugnaService.setListaImpugnacion($scope.impugnacion);
	        	  impugnaService.setRowsPerPage($scope.view.rowsPerPage);
	          }
	           else{        	   
	        	   if( $scope.proximoController == 'modifica_ImpugnacionController'  ){
	 	        	  impugnaService.setListaImpugnacion($scope.impugnacion);
	 	        	  impugnaService.setRowsPerPage($scope.view.rowsPerPage);
	 	          }else{
	 	        	  
	 	        	 if( $scope.proximoController == 'modifica_InfraccImpugnacionController'  ){
	 		        	  impugnaService.setListaImpugnacion($scope.impugnacion);
	 		        	  impugnaService.setRowsPerPage($scope.view.rowsPerPage);
	 		          }else{
	 		        	   
	 		              $scope.ListaImpugnacion ={};            
	 		             impugnaService.setListaImpugnacion($scope.ListaImpugnacion);
	 		          }
	 	          }  

	          }
	   
	      });     
	 
	 verificaLista();
}); 
