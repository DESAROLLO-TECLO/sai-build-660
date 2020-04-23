angular.module('siidfApp').controller('canceladasImpugnaController', function($route,$scope, $filter, impugnaService,$location,ModalService,utileriaService) {
	$scope.order='fechaOrder';
	$scope.totalRegistros = null;
	$scope.mostrarTabla= false;
	$scope.view = {};
	 
	$scope.buscarCanceladas = function(tipoBusqueda, valor) {
		if($scope.formImpugna.$valid){
			impugnaService.buscarCanceladas(tipoBusqueda, valor==null?"null":valor).success(function(data) {			
			console.log(data);
				var listDates=['fecha']; var listNumbers=['sancionDias','motivacion'];
				data = utileriaService.orderData(listDates,listNumbers,[],data);		
				$scope.canceladas = data;
				impugnaService.setListaImpugnacionCancel(data);
				$scope.totalRegistros = data.length;
				$scope.error = false;
				$scope.mostrarTabla= true;
			}).error(function(data) {
				$scope.mostrarTabla= false;
				$scope.totalRegistros = null;
				showAviso(data.message);
			});
			
		}else{
			
			$scope.formImpugna.valor.$invalid=true;
			$scope.formImpugna.valor.$pristine=false;
		}	
		
		
	}
	
	$scope.verDetalleCancel = function(infraccion) {
		  
		     $location.path('/detalleCanceladas/'+infraccion);
		  };
			


	  $scope.selectedOption;
	    $scope.data = {
	     availableOptions: [
	       {id: 'PLACA', name: 'Placa/Permiso'},
	       {id: 'IMPRESA', name: 'Boleta Preimpresa'},
	       {id: 'INFRAC', name: 'No. de Infración'},
	       {id: 'LICENCIA', name: 'Licencia de Conducir'}
	       
	     ],
	     selectedOption: {id: 'PLACA', name: 'Placa/Permiso'} //This sets the default value of the select in the ui
	     };
	
	    verificaLista = function() {
			   if (impugnaService.getListaImpugnacionCancel().length > 0) {
				   $scope.canceladas = impugnaService.getListaImpugnacionCancel();
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
		   
		      
		      $scope.$on('$locationChangeSuccess', function(event, current, previous) {
		          
		    	  $scope.proximoController = $route.current.$$route.controller;
		          if( $scope.proximoController == 'canceladas_DetalleImpugnaController'  ){
		        	  impugnaService.setRowsPerPage($scope.view.rowsPerPage);
		        	  impugnaService.setListaImpugnacionCancel($scope.canceladas);
		          }
		           else{        	   
		        	   if( $scope.proximoController == 'expedienteImpugnacionController'  ){
		 	        	  impugnaService.setListaImpugnacionCancel($scope.canceladas);
		 	        	  impugnaService.setRowsPerPage($scope.view.rowsPerPage);
		 	          	}
		        	   else{   
		 		              $scope.lista ={};            
		 		             impugnaService.setListaImpugnacionCancel($scope.lista);
		 		          
		 	          		}  
		           	}
		   
		      });     		      
		      
		      
		verificaLista();
});