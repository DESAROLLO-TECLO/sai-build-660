angular.module('siidfApp').controller('consultaDeteccionesController', function($scope, $filter, fotoMultaService, catalogoService, ModalService,utileriaService) {
	$scope.parametroBusqueda = {tipoFecha : 0, startDate : "", endDate : "", estatus : 0, procesados : 0, tipoRadar : 0,
								origenPlaca: 0};
	$scope.order='fechaOrder';
	filtroTiposFecha = function(){
		fotoMultaService.filtroTiposFecha().success(function(data) {
			$scope.filterValues = data;
			$scope.parametroBusqueda.tipoFecha = $scope.filterValues[0].codigo;
			$scope.error = false;
		}).error(function(data) {
			$scope.error = data;
			$scope.filterValues = {};
		});
	}
	
	filtroRadares = function(){
		catalogoService.buscarRadares(true).success(function(data) {
			$scope.filterRadares = data;
			$scope.parametroBusqueda.tipoRadar = $scope.filterRadares[0].tipoRadarId;
		}).error(function(data){
			$scope.filterRadares = {};
		});
	}
	
	filtroEstatus = function(){
		catalogoService.buscarEstatus().success(function(data) {
			$scope.filterEstatus = data;
			$scope.parametroBusqueda.estatus = $scope.filterEstatus[0].codigo;
		}).error(function(data){
			$scope.filterEstatus = {};
		});
	}
	
	filtroProcesados = function(){
		catalogoService.buscarProcesados().success(function(data) {
			$scope.filterProcesados = data;
			$scope.parametroBusqueda.procesados = $scope.filterProcesados[0].codigo;
		}).error(function(data){
			$scope.filterProcesados = {};
		});
	}

	filtroOrigenPlaca = function(){
		catalogoService.filtroOrigenPlaca(true).success(function(data) {
			$scope.filterOrigenPlaca = data;			
			$scope.filterOrigenPlaca.splice(0,0,{codigo: 2 , codigoString: "", descripcion: "Todos"});
			$scope.parametroBusqueda.origenPlaca = $scope.filterOrigenPlaca[0].codigo;
		}).error(function(data){
			$scope.filterOrigenPlaca = {};
		});
	}
	
	$scope.consultaDeteccionesFotomulta = function (){
		if($scope.form.$invalid){
			requiredFields();
			$scope.showAviso("Formulario Incompleto", 'templateModalAviso');
		}else{
			var nombreRadar = $.grep($scope.filterRadares, function (option) {
                return option.tipoRadarId == $scope.parametroBusqueda.tipoRadar;
            })[0].nombre;
			
			fotoMultaService.consultaDeteccionesFotomulta($scope.parametroBusqueda.tipoFecha,
				  $scope.parametroBusqueda.startDate,
				  $scope.parametroBusqueda.endDate,
				  $scope.parametroBusqueda.estatus,
			      $scope.parametroBusqueda.procesados,
			      $scope.parametroBusqueda.tipoRadar,
			      nombreRadar, $scope.parametroBusqueda.origenPlaca
			).success(function(data) {
				if(data.length >= 20000){
					$scope.showAviso("Su consulta supera los 20,000 registros. En caso de requerir el reporte completo, solicitelo a soporte.", 'templateModalAviso');
				}
				var listDates= ['fecha','fechaCreacion'];
				data = utileriaService.orderData(listDates,[],[],data);
				$scope.results = data;
			}).error(function(data){
				$scope.showAviso(data.message, 'templateModalAviso');
				$scope.results = {};
			});
		}
	}
	
	$scope.generarExcelDetecciones = function(){
		fotoMultaService.generarReporteDetecciones().success(function(data, status, headers) {
			var  filename  = headers('filename');
	     	var contentType = headers('content-type');
		 	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
		 	fotoMultaService.downloadfile(file, filename);
	  		$scope.error = false;
		 }).error(function(data) {
			 $scope.showAviso(data.message, 'templateModalError');
		 });
	}
	
	requiredFields = function(){
		angular.forEach($scope.form.$error, function (field) {
            	angular.forEach(field, function(errorField){
            	errorField.$setDirty();
            })
		});
	}
	
	$scope.showAviso = function(messageTo, template) {
	      ModalService.showModal({
	        templateUrl: 'views/templatemodal/'+template+'.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	      });
	};
	
	$scope.getLegend = function(value){
		if(value == 0){
			return "CDMX";
		}else if(value == 1){
			return "Foránea";
		}
	}
	
	filtroProcesados();
	filtroEstatus();
	filtroRadares();
	filtroTiposFecha();
	filtroOrigenPlaca();
});