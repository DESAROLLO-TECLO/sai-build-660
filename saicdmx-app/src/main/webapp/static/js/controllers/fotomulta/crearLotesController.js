angular.module('siidfApp').controller('crearLotesController', function($scope, $filter, $location, fotoMultaService, catalogoService, ModalService) {
	filtroRadares = function(){
		catalogoService.buscarRadares(false).success(function(data) {
			$scope.filterRadares = data;
			//$scope.parametroBusqueda.tipoRadar = $scope.filterRadares[0].tipoRadarId;
			$scope.error = false;
		}).error(function(data){
			$scope.error = data;
			$scope.filterRadares = {};
		});
	}
	
	filtroSalario = function(){
		catalogoService.buscarAniosSalarioMinimo().success(function(data){
			$scope.filterSalario = data;
			$scope.parametroBusqueda.salariomin = $scope.filterSalario[0].valor;
			$scope.error = false;
		}).error(function(data){
			$scope.error = data;
			$scope.filterSalario = {};
		});
	}
	

	filtroOrigenPlaca = function(){
		catalogoService.filtroOrigenPlaca(false).success(function(data) {
			$scope.filterOrigenPlaca = data;
			//$scope.parametroBusqueda.origenPlaca = $scope.filterOrigenPlaca[0].codigo;
		}).error(function(data){
			$scope.filterOrigenPlaca = {};
		});
	}
	
	$scope.validarDeteccionesParaLote = function(){
		$scope.viewpanelCrearLote = false;
		if ($scope.form.$invalid) {
			requiredFields();
			$scope.showAviso("Formulario Incompleto", "templateModalAviso");
        }else{
        	
        	$scope.nombreRadar = $.grep($scope.filterRadares, function (option) {
                return option.tipoRadarId == $scope.parametroBusqueda.tipoRadar;
            })[0].nombre;
        	
			fotoMultaService.validarDeteccionesParaLote($scope.parametroBusqueda.startDate,
														$scope.parametroBusqueda.endDate,
														$scope.parametroBusqueda.tipoRadar,
														$scope.nombreRadar, $scope.parametroBusqueda.origenPlaca
			).success(function(data){
				$scope.number = data;
				$scope.viewpanelCrearLote = true;
			}).error(function(data){
				$scope.number = 0;
				$scope.showAviso(data.message, "templateModalAviso");
			});
        }
	}
	
	$scope.generarExcelDeteccionesPorLote = function(){
		fotoMultaService.generarReporteDeteccionesPorLote().success(function(data, status, headers) {
			var  filename  = headers('filename');
	     	var contentType = headers('content-type');
		 	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
		 	fotoMultaService.downloadfile(file, filename);
	  		$scope.error = false;
		 }).error(function(data) {
			 $scope.showAviso(data.message, "templateModalError");
	 	});
	}

	$scope.crearLote = function(){
		$scope.viewpanelLoteCreado = false;
		if ($scope.form.$invalid) {
			requiredFields();
			$scope.showAviso("Formulario Incompleto", "templateModalAviso");
        }else{
			fotoMultaService.crearLote($scope.parametroBusqueda.emisionDate, 
									   $scope.parametroBusqueda.nombrelote, 
									   $scope.parametroBusqueda.salariomin, 
									   $scope.parametroBusqueda.startDate,
									   $scope.parametroBusqueda.endDate, 
									   $scope.parametroBusqueda.tipoRadar,
									   $scope.parametroBusqueda.origenPlaca)
			.success(function(data){
				$scope.nuevoLote = data;
				$scope.viewpanelLoteCreado = true;
				defaultValues();
				$scope.form.$setPristine();
				$scope.showAviso("Registro Guardado","templateModalAviso");
			}).error(function(data){
				$scope.showAviso(data.message,"templateModalError");
			});        	
        }
		
		$scope.viewpanelCrearLote = false;
	}
	
	$scope.realizarComplementacion = function(lote){
		fotoMultaService.realizarComplementacion(lote).success(function(data){
			if(data == 1){
				$location.path('/complementarRadares');
			}
			$scope.error = false;
		}).error(function(data){
			$scope.showAviso(data.message, "templateModalError");
		});
	}
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	//templateModalAviso //templateModalError
	$scope.showAviso = function(messageTo, template) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/'+ template +'.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      });
	};
	
	
	defaultValues = function(){
		$scope.parametroBusqueda = {};
		filtroSalario();
		filtroRadares();
		filtroOrigenPlaca();
		//$scope.parametroBusqueda = {emisionDate : "", startDate : "", endDate : "", nombrelote : "", salariomin : 0, tipoRadar : 0};
	}
	
	defaultValues();
	
});