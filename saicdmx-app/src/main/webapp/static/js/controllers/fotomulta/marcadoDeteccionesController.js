angular.module('siidfApp').controller('marcadoDeteccionesController', function($scope, $filter, fotoMultaService,catalogoService, ModalService,utileriaService,showAlert) {
	$scope.order='fechaOrder';
	$scope.listaDispositivos =false;
	$scope.parametroBusqueda = {};
	$scope.origenPlaca = '2';
   
	getFechasCombo = function(){
		fotoMultaService.obtenerFechasCombo().success(function(data){
			$scope.fechasCombo = data;
		}).error(function(data){
		});
	}

	obtenerListas = function(data){		
		var lista = [];	
		angular.forEach(data, function(value, key) {  
			  angular.forEach(value, function(v, k) {  
				  lista.push(v);  
			    });
		  }); 
		return lista;
	}
	

	$scope.buscarDetecciones = function(fecha,tipoRadar,origenPlaca){
		
		var paramFecha = fecha==null?"todas":fecha;
		var paramTipoRadar = tipoRadar;
		var paramOrigenPlaca = origenPlaca;
		fotoMultaService.busquedaDetecCancel(paramFecha,
											 paramTipoRadar,
											 paramOrigenPlaca).success(function(data){
	     
	  
		  var listaVali = obtenerListas(data[0]);

		  $scope.totalBoschVali = $filter('filter')(listaVali, {codigo:'BOSCH'});
		  $scope.totalLaserVali = $filter('filter')(listaVali, {codigo:'LASER'});
		  $scope.totalLuzRojaVali = $filter('filter')(listaVali,{codigo:'LUZ ROJA'});
		  
//		  var listaPre = obtenerListas(data[1]);
//		  
//		  $scope.totalBoschPre = $filter('filter')(listaPre, {codigo:'BOSCH'});
//		  $scope.totalLaserPre = $filter('filter')(listaPre, {codigo:'LASER'});
//		  $scope.totalLuzRojaPre = $filter('filter')(listaPre,{codigo:'LUZ ROJA'});

		  $scope.listaDispositivos = true;

		  
		}).error(function(data){
			showAviso(data.message);
			
		});
	}
	
	
	$scope.obtenerRegistros = function(tipoLista){
		fotoMultaService.obtenerListaDetecciones(tipoLista).success(function(data){
			if(tipoLista=='getlistaValidaciones'){
				$scope.tipoDetecciones = 'Detecciones Validadas';
			}else{
				$scope.tipoDetecciones = 'Detecciones Prevalidadas';				
			}
				var listDates= ['fecha','fechaCreacion','fechaValidacion'];
				data = utileriaService.orderData(listDates,[],[],data);
				$scope.listaDetecciones = data;
		}).error(function(data){
			$scope.listaDetecciones=[];
			showAviso(data.message);
		});
	}
	
	
	cancelarDetecciones = function(tipoRadar,origenPlaca,motivo){
			
	    fotoMultaService.cancelarDetecciones(tipoRadar,
											origenPlaca,
											motivo==null ||angular.isUndefined(motivo)?"":motivo).success(function(data){
				if(data>0){
					showAviso('Se realizó el marcado de detecciones correctamente');
					$scope.hideResultados();
					getFechasCombo();
				}else{
					showAviso('No se pudo realizar el proceso de marcado');
				}
		  }).error(function(data){
			  showAviso('No se pudo realizar el proceso de marcado');
		});
	
	}
	
	$scope.descargaExcel = function() {
		fotoMultaService.descargarExcel().success(function(data, status, headers) {			
				var  filename  = headers('filename');
				var contentType = headers('content-type');
				var file = new Blob([data], {type: 'application/vnd.ms-excel;base64,'});
				save (file , 'reporteMarcadoDetecciones');
		}).error(function(data) {
			showAviso(data.message);
			$scope.mostrarTabla= false;
		});
	 }
	
	function save(file, fileName) {
		 var url = window.URL || window.webkitURL;
	   	 var blobUrl = url.createObjectURL(file);
	   	 var a         = document.createElement('a');
	   	 a.href        = blobUrl; 
		 a.target      = '_blank';
		 a.download    = fileName;
		 document.body.appendChild(a);
		 a.click();   
	}
	
	showAviso = function(messageTo) {
		ModalService.showModal({
			templateUrl: 'views/templatemodal/templateModalAviso.html',
			controller: 'mensajeModalController',
			inputs:{ message: messageTo}
      		}).then(function(modal) {
      			modal.element.modal();
  		});
	};
	
	$scope.confirmCancelacion = function(tipoRadar,origenPlaca,motivo){
//		ModalService.showModal({
//	    	templateUrl: 'views/templatemodal/templateModalConfirmacion.html',
//	        controller: 'mensajeModalController',
//	        	inputs:{ message: '¿Desea llevar a cabo el proceso de cancelación?'}
//	    }).then(function(modal) {
//	        modal.element.modal();
//	        modal.close.then(function(result) {
//	        	if(result){		        													        		
//	        		cancelarDetecciones(tipoRadar,origenPlaca,motivo);
//	        	}
//	        }); 
//	    });
		
		showAlert.confirmacion('¿Desea llevar a cabo el proceso de cancelación?',function(){ cancelarDetecciones(tipoRadar,origenPlaca,motivo) });

		
	};

	obtenerRadares = function(){
		catalogoService.buscarRadares(true).success(function(data) {	
			$scope.filterRadares = data;
			$scope.parametroBusqueda.tipoRadar = $scope.filterRadares[0].tipoRadarId;
		}).error(function(data){
			$scope.filterRadares = {};
		});
	}
	
	
	$scope.hideResultados=function(){
		$scope.listaDispositivos = false;
		$scope.listaDetecciones =[];
	}
	

	$scope.buscarDetecciones('todas',0,2);
	obtenerRadares();
	getFechasCombo();
	
});