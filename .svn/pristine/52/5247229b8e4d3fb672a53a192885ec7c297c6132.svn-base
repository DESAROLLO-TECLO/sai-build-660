angular.module('siidfApp').controller('rptEntradaDepositoController', function($window,$scope,ReporteDinamicosServices,ModalService) {
	$scope.parametroBusqueda = {noInfraccion : 0,Deposito : 0 ,causaIngreso : 0,tipoIngreso:0};
	$scope.ListaCausaIngresos=[];
	$scope.tiposIngresos=[];
	$scope.ListaDeposito=[];
	
	CatalogosCausaIngresos = function (){
		ReporteDinamicosServices.Catalogos("causaIngresos").success(function(data){
			$scope.ListaCausaIngresos.push({"id":0,"nombre":"Todos"});
			$scope.ListaCausaIngresos=$scope.ListaCausaIngresos.concat(data);
			$scope.parametroBusqueda.causaIngreso = $scope.ListaCausaIngresos[0].id;
			$scope.error = false;
		}).error(function(data){
			$scope.error = data;
			$scope.TiposReportes = {};
		});
	};	
	
	CatalogoTiposIngresos = function (){
		ReporteDinamicosServices.Catalogos("TiposIngresos").success(function(data){
			$scope.tiposIngresos.push({"id":0,"nombre":"Todos"});
			$scope.tiposIngresos=$scope.tiposIngresos.concat(data);
			$scope.parametroBusqueda.tipoIngreso = $scope.tiposIngresos[0].id;
			$scope.error = false;
		}).error(function(data){
			$scope.error = data;
			$scope.TiposReportes = {};
		});
	};
	
	CatalogoNombreDepositos = function (){
		ReporteDinamicosServices.Catalogos("Depositos").success(function(data){
			$scope.ListaDeposito.push({"id":0,"nombre":"Todos"});
			$scope.ListaDeposito=$scope.ListaDeposito.concat(data);
			$scope.parametroBusqueda.Deposito = $scope.ListaDeposito[0].id;
			$scope.error = false;
		}).error(function(data){
			$scope.error = data;
			$scope.TiposReportes = {};
		});
	};
	
	CatalogosCausaIngresos();
	CatalogoTiposIngresos();
	CatalogoNombreDepositos();

/**Funciones para crear el reporte */

	$scope.consultaEntraDeposito = function(){
		ReporteDinamicosServices.ConsultaEntroDeposito($scope.parametroBusqueda,noInfraccion,
				                                       $scope.parametroBusqueda.Deposito,
				                                       $scope.parametroBusqueda.causaIngreso,
				                                       $scope.parametroBusqueda.tipoIngreso).success(function(data){
		$scope.TotalIngresos= data;
			
		}).error(function(data){
			$scope.error = data;
		});
};
		
});