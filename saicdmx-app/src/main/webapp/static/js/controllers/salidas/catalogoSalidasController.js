angular.module('siidfApp').controller('catalogoSalidasController', function( $location, $scope, salidaVehiculoService,  $routeParams, ModalService) {

	$scope.option = {};
	$scope.flagByAll = true;
	$scope.option.newCatParams = {};
	parametersGralCatSalidas = function(){
		salidaVehiculoService.comboCatSalidas().success(function(data) {
			$scope.comboCatalogo = data;
			$scope.option.catalogo  = $scope.comboCatalogo[0].codigo;
		}).error(function(data){
			$scope.showAviso("No se encontro catalogos", 'templateModalAviso');
		});
	};
	
	comboTypeSearchCompacta = function(){
		salidaVehiculoService.comboTypeSearchCompacta ().success(function(data) {
			$scope.comboBusquedaCompacta = data;
			$scope.option.tipoBusquedaCompacta  = $scope.comboBusquedaCompacta[0].codigoString;
		}).error(function(data){
			$scope.showAviso("No se encontro catalogos", 'templateModalAviso');
		});
	};
	
	comboTypeSearchAdjudica = function(){
			salidaVehiculoService.comboTypeSearchAdjudica ().success(function(data) {
			$scope.comboBusquedaAdjudica = data;
			$scope.option.tipoBusquedaAdjudica  = $scope.comboBusquedaAdjudica[0].codigoString;
		}).error(function(data){
			$scope.showAviso("No se encontro catalogos", 'templateModalAviso');
		});
	};
	
	
	$scope.flagToMoveSearch = function(){
		$scope.flagByAll = true;
		$scope.flagByRequired = false;
		$scope.tipoBusqueda = 'todo';
		$scope.results = {};
		cleanInputComboValor();
		if($scope.option.catalogo == 2){
			$scope.option.tipoBusquedaAdjudica  = $scope.comboBusquedaAdjudica[0].codigoString;
			
		}else{
			$scope.option.tipoBusquedaCompacta  = $scope.comboBusquedaCompacta[0].codigoString;
		}
		
	};
	
	$scope.listenerChangeToTip = function(){
		$scope.tipoBusqueda = $scope.option.tipoBusquedaCompacta;
		$scope.flagByAll = ($scope.tipoBusqueda == 'todo') ? true :  false ;
		$scope.flagByRequired = ($scope.flagByAll) ? false : true; 
		cleanInputComboValor();
	};
	
	$scope.listenerChangeToTip2 = function(){
		$scope.tipoBusqueda = $scope.option.tipoBusquedaAdjudica;
		$scope.flagByAll = ($scope.tipoBusqueda == 'todo') ? true :  false ;
		$scope.flagByRequired = ($scope.flagByAll) ? false : true; 
		cleanInputComboValor();
	};
	
	$scope.searchCat = function(){
		if($scope.formCatSalida.$invalid){
			requiredFields();
			$scope.showAviso("Formulario Incompleto", 'templateModalAviso');
		}else{
		$scope.tipoBusqueda = ($scope.tipoBusqueda == undefined) ? "todo" :  $scope.tipoBusqueda ;
		var salidasCatVO = {
				"idCat" : $scope.option.catalogo,
				 "tipoBusqueda" : $scope.tipoBusqueda ,
				 "valorCombo" : $scope.option.valorCombo
			 };
			salidaVehiculoService.searchBookCat(salidasCatVO).success(function(data) {
			if(data.length <= 0){
				$scope.showAviso("No se encontraron registros", 'templateModalAviso');
			}else{
				$scope.flagTypeCat = $scope.option.catalogo;
				$scope.results = data;
			}
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
		}
	};
	
	
	
	$scope.searchUpdateCatParams = function(idCat){
		$scope.modificaCat.$setPristine();
		salidaVehiculoService.searchCatByIdCat(idCat, $scope.flagTypeCat).success(function(data) {
				$scope.flagTypeCat = $scope.option.catalogo;
				$scope.option.modificarCat = data;
				comboActiveInactive();
		
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
	};
	
	comboActiveInactive = function(){
		salidaVehiculoService.comboActiveInactive().success(function(data) {
			$scope.comboActiveInactive = data;
			
		}).error(function(data){
		$scope.showAviso(data.message, 'templateModalAviso');
		});
		
	};
	
	$scope.updateRegistro = function(){
		if($scope.modificaCat.$invalid){
			requiredFieldsUpdate();
		}else{
		$('#modificaCat').modal('toggle');
		var salidasParametersCatVO = {
				"idCat" : $scope.option.modificarCat.idCat,
				 "nomTipoCat" : $scope.option.modificarCat.nomTipoCat,
				 "descripcion" : $scope.option.modificarCat.descripcion,
				 "fchCompatacion" : $scope.option.modificarCat.fchCompatacion,
				 "estatus" : $scope.option.modificarCat.estatus
		 };
		salidaVehiculoService.updateBookCat(salidasParametersCatVO, $scope.option.catalogo).success(function(data) {
			if(data == true){ $scope.showAvisoAction("Registro actualizado correctamente", 'templateModalAviso', function(){ $scope.searchCat() }); }
		}).error(function(data){
			$scope.showAviso(data.message, 'templateModalAviso');
		});
		}
	};
	
	
	$scope.newBook = function(){
		$scope.flagTypeCatNew = $scope.option.catalogo;
		comboActiveInactive();
		$scope.option.newCatParams.estatus = 'Activo';
	};
	
	$scope.newCatSalidasVO = function(){
		if($scope.newCatSalidas.$invalid){
			requiredFieldsNew();
		}else{
			$('#newCat').modal('toggle');
			var salidasParametersNewCatVO = {
					 "nomTipoCat" : $scope.option.newCatParams.nomTipoCat,
					 "descripcion" : $scope.option.newCatParams.descripcion,
					 "fchCompatacion" : $scope.option.newCatParams.fchCompatacion,
					 "estatus" : $scope.option.newCatParams.estatus
			 };
			salidaVehiculoService.newBookCat(salidasParametersNewCatVO, $scope.option.catalogo).success(function(data) {
				if(data == true){ $scope.showAvisoAction("Registro correctamente agregado", 'templateModalAviso', function(){ $scope.searchCat() }); }
			}).error(function(data){
				$scope.showAviso(data.message, 'templateModalAviso');
			});
		}
		
	};
	
	/* MODALES */
	
	$scope.showAviso = function(messageTo, template) {
	      ModalService.showModal({
	    	templateUrl: 'views/templatemodal/'+template+'.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      });
	};
	
	$scope.showAvisoAction = function(messageTo, template, action) {
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
	
	/*VALIDACION CAMPOS*/
	
	requiredFields = function(){
		angular.forEach($scope.formCatSalida.$error, function (field) {
          	angular.forEach(field, function(errorField){
          	errorField.$setDirty();
          })
		});
	}
	
	requiredFieldsUpdate = function(){
		angular.forEach($scope.modificaCat.$error, function (field) {
          	angular.forEach(field, function(errorField){
          	errorField.$setDirty();
          })
		});
	}
	
	requiredFieldsNew = function(){
		angular.forEach($scope.newCatSalidas.$error, function (field) {
          	angular.forEach(field, function(errorField){
          	errorField.$setDirty();
          })
		});
	}
	
	/*CLEAN INPUT VALOR COMBO*/
	
	cleanInputComboValor = function(){
		$scope.option.valorCombo = "";
		$scope.formCatSalida.$setPristine();
	};
	
	/*CLEAN MODALS*/
	$scope.closeNew = function(){
		
		$scope.option.newCatParams = {};
		$scope.newCatSalidas.$setPristine();
	};
	
	
	/*INICIALIZACION DE METODOS*/
	parametersGralCatSalidas();
	comboTypeSearchCompacta();
	comboTypeSearchAdjudica();
	
});
