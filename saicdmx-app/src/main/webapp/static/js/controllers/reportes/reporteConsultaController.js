angular.module('siidfApp').controller('reporteConsultaController', function($window,$scope,reporteService) {

	console.log('Consulta Reportes');

	   buscarReportes = function() {
			
			reporteService.getReportesLista().success(function(data) {	
				$scope.listaReportesVO = data; 	
				console.log(data);
				
			}).error(function(data) {
				
				
			});
		};
		
		$scope.openLink = function (reporte) {
	
			var url= "http://"+reporte.ip+reporte.url_tipo_rpte;		
			url+=reporte.url+"&CAMUsername="+reporte.id_usr_repnet+"&CAMPassword="+reporte.psw_repnet;		   
			$window.open(url);
		    	
		}

		buscarReportes();
	
});