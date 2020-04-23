angular.module('siidfApp').controller('deteccionesSinProcesarController', function($scope, $filter, fotoMultaService, ModalService, responsiveHelper) {
	$scope.parametroBusqueda = {};
	$scope.helpers = {};
	var banderaContenV = false;
	var banderaContenNV = false;
	
	filtroTiposFecha = function(){
		fotoMultaService.filtroTiposFecha().success(function(data) {
			$scope.filterValues = data;
			$scope.parametroBusqueda.tipoFecha = $scope.filterValues[0].codigo;
			$scope.parametroBusqueda.origenPlaca = 2;
			defaultValues();
			buscarDetecciones();
		}).error(function(data) {
			$scope.filterValues = {};
		});
	}
	
	buscarDetecciones = function (){
		fotoMultaService.buscarDetecciones($scope.parametroBusqueda.tipoFecha,
										   $scope.parametroBusqueda.origenPlaca)
		  .success(function(data) {
			$scope.prevalidadas= data[0];
			$scope.validadas= data[1];
			$scope.validadasHistorico= data[2];
			$scope.validaContenedor();
		}).error(function(data){
			defaultValues();
			$scope.showAviso(data.message);
		});
	}
	
	$scope.validaContenedor = function(){
		if($scope.validadas.bosch == 0 && $scope.validadas.laser == 0 && $scope.validadas.luzRoja == 0){
			banderaContenV = true;
		}if($scope.validadasHistorico.bosch == 0 && $scope.validadasHistorico.laser == 0 && $scope.validadasHistorico.luzRoja == 0){
			banderaContenNV = true;
		}
		if(banderaContenV && banderaContenNV ){
			$scope.showAviso("No se encontraron detecciones");banderaContenV = false;banderaContenNV = false;
		}else if(banderaContenV){
		$scope.showAviso("No se encontraron detecciones actuales");banderaContenV = false;
		}else if(banderaContenNV){$scope.showAviso("No se encontraron detecciones historicas"); banderaContenNV = false;}
	}
	
	$scope.changeFilter = function() {
		$scope.helpers.showTblDetalleDetecciones = false;
		$scope.helpers.showDeteccionesMes = false;
		buscarDetecciones();
	}
	
	$scope.buscarDeteccionesPorFechaYRadar = function(codigoRadar, tipoDeteccion){
		$scope.helpers.showDeteccionesMes = false;
		$scope.helpers.showTblDetalleDetecciones = false;
		$scope.deteccionesRadar = [];
		fotoMultaService.buscarDeteccionesPorFechaYRadar($scope.parametroBusqueda.tipoFecha , codigoRadar,
														 $scope.parametroBusqueda.origenPlaca, tipoDeteccion)
	 	
	    .success(function(data){
			$scope.deteccionesRadar = data.list;
			groupSlides(data.list);
			/*$scope.itemsNumber = $.map(data, function(value, index) {
			    return [value];
			}).length;*/
			
			$scope.helpers.showTblDetalleDetecciones = true;
			$scope.radar = codigoRadar;	
		}).error(function(data){
			$scope.helpers.showTblDetalleDetecciones = false;
		});
	}
	
	groupSlides = function(l){
		var many = 3, object, group = [];
		
	    if(responsiveHelper.isMobile()){
	       many = 1;
	    }
		
	    for (i = 0; i < l.length; i += many) {
	    	object = {
	        image1: l[i]
	      };
	      if (many == 1) {}
	      if (l[i + 1] && (many == 2 || many == 3)) {
	    	  object.image2 = l[i + 1];

	      }
	      if (l[i + (many - 1)] && many == 3) {
	    	  object.image3 = l[i + 2];
	      }
	      group.push(object);
	    }
	    $scope.groupedSlides = group;
	}
	
	 

	
	$scope.buscarDeteccionesPorMes = function(fechaInicio, mes, anio){
		$scope.helpers.showDeteccionesMes = false;
		fotoMultaService.buscarDeteccionesPorMes(fechaInicio, $scope.parametroBusqueda.origenPlaca, $scope.parametroBusqueda.tipoFecha, $scope.radar).success(function(data){
			$scope.deteccionesMes = data;
			
			$scope.helpers.showDeteccionesMes = true;
			
			$scope.helpers.divRepeat = $scope.deteccionesMes.length % 4;
			$scope.helpers.titleForMonth = $scope.getMonth(mes) + ' del ' + anio;
			
			if($scope.helpers.divRepeat == 0){
				$scope.helpers.divRepeat = 0;
				$scope.helpers.repeat = false;
			}else{
				$scope.helpers.divRepeat = 4 - $scope.helpers.divRepeat;
				$scope.helpers.repeat = true;
			}
		}).error(function(data){
			$scope.helpers.showDeteccionesMes = false;
		});
	}
	
	$scope.getNumber = function(num) {
	    return new Array(num);   
	}
	
	$scope.getMonth =  function(mes){
		var month = new Array();
		month[1] = "ENERO";
		month[2] = "FEBRERO";
		month[3] = "MARZO";
		month[4] = "ABRIL";
		month[5] = "MAYO";
		month[6] = "JUNIO";
		month[7] = "JULIO";
		month[8] = "AGOSTO";
		month[9] = "SEPTIEMBRE";
		month[10] = "OCTUBRE";
		month[11] = "NOVIEMBRE";
		month[12] = "DICIEMBRE";
		return month[mes];
	}
	
	$scope.showAviso = function(messageTo) {
		ModalService.showModal({
	        templateUrl: 'views/templatemodal/templateModalAviso.html',
	        controller: 'mensajeModalController',
	        inputs:{ message: messageTo}
	      	}).then(function(modal) {
	      		modal.element.modal();
	    });
	};
	
//	$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
//		$('.multi-item-carousel').carousel({
//		  	interval: false
//		});
//		
//		$('.multi-item-carousel .item').each(function(){
//			var next = $(this).next();
//			
//		  	if (!next.length) {
//		    	next = $(this).siblings(':first');
//		  	}
//		  	
//  			next.children(':first-child').clone().appendTo($(this));
//  
// 			if (next.next().length>0) {
//    			next.next().children(':first-child').clone().appendTo($(this));
//  			} else {
//  				$(this).siblings(':first').children(':first-child').clone().appendTo($(this));
//  			}
//		});
//		
//		$('.carousel-control.left').click(function() {
//			$('#myCarousel').carousel('prev');
//	  	});
//
//		$('.carousel-control.right').click(function() {
//  			$('#myCarousel').carousel('next');
//		});
//	});
	
	defaultValues = function(){
		$scope.prevalidadas = {luzRoja : 0, bosch: 0, laser: 0, total: 0};
		$scope.validadas = {luzRoja : 0, bosch: 0, laser: 0, total: 0};
		$scope.validadasHistorico = {luzRoja : 0, bosch: 0, laser: 0, total: 0};
	}
	
	filtroTiposFecha();
});