angular.module('siidfApp').controller('consultaLCMasivaController', function($scope, $filter, lineaCapturaMasivaService, ModalService,utileriaService) {
	$scope.viewFilters = false;
	$scope.opcion = 1;
	$scope.busquedaVO={
		startDate:"",
		endDate:"",
		cbCampoBusqueda:"1",
		idLote:"",
		nameLote:"",
		cbTipoFecha:"1",
		cbEstatusLotes:"1"
	};
	
	var busquedaVORestart={
			startDate:"",
			endDate:"",
			cbCampoBusqueda:"1",
			idLote:"",
			nameLote:"",
			cbTipoFecha:"1",
			cbEstatusLotes:"1"
		};
	
	$scope.viewCampoFecha = false;
	$scope.order='fecha_reasignacionOrder';
	$scope.foliosVO = {};
	
	$scope.consultaLotesReasignado = function(){
		if($scope.formConsultaReasig.$invalid){
			requiredFields();
			$scope.showAviso("Formulario Incompleto", 'templateModalAviso');
		}else{
			//Consumo de Service
			lineaCapturaMasivaService.consultarLotes(
				$scope.busquedaVO.startDate, 
				$scope.busquedaVO.endDate, 
				$scope.busquedaVO.cbCampoBusqueda,
				$scope.busquedaVO.idLote ,
				$scope.busquedaVO.nameLote,
				$scope.busquedaVO.cbTipoFecha,
				$scope.busquedaVO.cbEstatusLotes
			).success(function(data){
				if(data.respuesta){
					var listDates= ['fecha_reasignacion','fecha_emision'];
					data.data = utileriaService.orderData(listDates,[],[],data.data);				
					$scope.foliosVO = data.data;
					if(data.length>=40000){
						$scope.showAviso("Su consulta superó los 40,000 registros. En caso de requerir el reporte completo, solicítelo a soporte.", 'templateModalAviso');	
					}
				}
//				lineaCapturaMasivaService.consultarLotes($scope.busquedaVO.startDate, $scope.busquedaVO.endDate).success(function(data){
//					if(data.respuesta){
//						$scope.foliosVO = data.data;
//					}
//				}).error(function(data){
//					$scope.showAviso(data.message, 'templateModalAviso');
//				});
			}).error(function(data){
				$scope.foliosVO = {};
				$scope.showAviso(data.message, 'templateModalAviso');
			});
		}
	}
	$scope.descargarLoteFolios = function(id){
		//Consumo de Service
		lineaCapturaMasivaService.descargarLoteReporte(id).success(function(data, status, headers){
			var  filename  = headers('filename');
         	var contentType = headers('content-type');
    	 	var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
    	 	lineaCapturaMasivaService.downloadfile(file, filename);
		}).error(function(data){
			
		});
	};
	
	
	requiredFields = function(){
		angular.forEach($scope.formConsultaReasig.$error, function (field) {
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
	
	$scope.getVal = function(){
		
		if($scope.opcion == 2){
			$scope.foliosVO = {};
			$scope.viewFilters = true;
			$scope.viewByT = true;
			$scope.viewAll = false;
			$scope.viewInformation = false;
			$scope.viewCampoFecha = false;
		}else{
			$scope.foliosVO ={};
					
			$scope.busquedaVO.startDate = "" ; 
			$scope.busquedaVO.endDate = ""; 
			$scope.busquedaVO.cbCampoBusqueda = "1";
			$scope.busquedaVO.idLote = "";
			$scope.busquedaVO.nameLote = "" ;
			$scope.busquedaVO.cbTipoFecha = "1";
			$scope.busquedaVO.cbEstatusLotes = "1" ;
			
			$scope.viewFilters = false;
			$scope.viewByT = false;
			$scope.viewAll = true;
			$scope.viewInformation = false;
		
		}
	}
	
	$scope.seleccionaCampoBusqueda = function(){
		$scope.formConsultaReasig.$setPristine();
		
		if($scope.busquedaVO.cbCampoBusqueda == 1){
			$scope.viewCampoIdLote		= false;
			$scope.viewCampoNameLote	= false;
			$scope.busquedaVO.idLote	= "";
			$scope.busquedaVO.nameLote	= "";
		}else if($scope.busquedaVO.cbCampoBusqueda == 2){
			$scope.viewCampoIdLote	= true;
			$scope.viewCampoNameLote= false;
			$scope.busquedaVO.nameLote	= "";
		}else if($scope.busquedaVO.cbCampoBusqueda == 3){
			$scope.viewCampoIdLote	= false;
			$scope.viewCampoNameLote= true;
			$scope.busquedaVO.idLote	= "";
		}
	}
	
	$scope.seleccionaTipoFecha = function(){
		$scope.busquedaVO.endDate = "";
		$scope.busquedaVO.startDate = "";
		$scope.formConsultaReasig.$setPristine();
		
		if($scope.busquedaVO.cbTipoFecha == 1){
			$scope.busquedaVO.startDate	= "";
			$scope.busquedaVO.endDate	= "";
			$scope.viewCampoFecha		= false;
		}else if($scope.busquedaVO.cbTipoFecha > 1){
			$scope.viewCampoFecha = true;
		}
	}
});