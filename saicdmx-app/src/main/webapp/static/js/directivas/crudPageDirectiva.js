angular.module('siidfApp').directive('crudConcesionaria', function(){
        return {
        	templateUrl : "views/catalogos/crudConcesionaria.html"
        }        
 })
 
 angular.module('siidfApp').directive('crudStatus', function(){
        return {
        	templateUrl : "views/catalogos/crudStatus.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudGruas', function(){
        return {
        	templateUrl : "views/catalogos/crudGruas.html"
        }        
 })

  angular.module('siidfApp').directive('crudTurnos', function(){
        return {
        	templateUrl : "views/catalogos/crudTurno.html",
        	link: function(scope, element, attrs) {
        		$("#timepickerInicio").timepicker({
    				showInputs : false,
    				timeFormat: 'h:mm:ss',
    				showMeridian:false,
    				defaultTime: false
    			});
        		$("#timepickerFin").timepicker({
    				showInputs : false,
    				timeFormat: 'h:mm:ss',
    				showMeridian:false,
    				defaultTime: false
    			});
        		
        		$('#datepickerInicio').datepicker({
    				autoclose : true,
    				dateFormat : 'dd/MM/yyyy',
    				todayHighlight : true
    			}).on('changeDate', function (selected) {
    	        	var minDate = new Date(selected.date.valueOf());
    	        	$('#datepickerFin').datepicker('setStartDate', minDate);
    	    	});
        		
        		$('#datepickerFin').datepicker({
    				autoclose : true,
    				dateFormat : 'dd/MM/yyyy',
    				todayHighlight : true
    			}).on('changeDate', function (selected) {
    	        	var minDate = new Date(selected.date.valueOf());
    	        	$('#datepickerInicio').datepicker('setEndDate', minDate);
    	    	});
        	}        	
        }        
 })
 
  angular.module('siidfApp').directive('crudZonaServicio', function(){
        return {
        	templateUrl : "views/catalogos/crudZonaServicio.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudTipoEmpleado', function(){
        return {
        	templateUrl : "views/catalogos/crudTipoEmpleado.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudTipoAlarma', function(){
        return {
        	templateUrl : "views/catalogos/crudTipoAlarma.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudEstado', function(){
        return {
        	templateUrl : "views/catalogos/crudEstado.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudRegion', function(){
        return {
        	templateUrl : "views/catalogos/crudRegion.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudDelegacion', function(){
        return {
        	templateUrl : "views/catalogos/crudDelegacion.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudUnidadTerritorial', function(){
        return {
        	templateUrl : "views/catalogos/crudUnidadTerritorial.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudSector', function(){
        return {
        	templateUrl : "views/catalogos/crudSector.html"
        }        
 })
 
 angular.module('siidfApp').directive('crudAgrupamiento', function(){
        return {
        	templateUrl : "views/catalogos/crudAgrupamiento.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudZona', function(){
        return {
        	templateUrl : "views/catalogos/crudZona.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudRegionDeposito', function(){
        return {
        	templateUrl : "views/catalogos/crudRegionDeposito.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudDeposito', function(){
        return {
        	templateUrl : "views/catalogos/crudDeposito.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudMarcaVehiculo', function(){
        return {
        	templateUrl : "views/catalogos/crudMarcaVehiculo.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudModeloVehiculo', function(){
        return {
        	templateUrl : "views/catalogos/crudModeloVehiculo.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudSubTipoVehiculo', function(){
        return {
        	templateUrl : "views/catalogos/crudSubTipoVehiculo.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudTipoVehiculo', function(){
        return {
        	templateUrl : "views/catalogos/crudTipoVehiculo.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudColorVehiculo', function(){
        return {
        	templateUrl : "views/catalogos/crudColorVehiculo.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudResponsableVehiculo', function(){
        return {
        	templateUrl : "views/catalogos/crudResponsableVehiculo.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudTipoLicencia', function(){
        return {
        	templateUrl : "views/catalogos/crudTipoLicencia.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudEvento', function(){
        return {
        	templateUrl : "views/catalogos/crudEvento.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudStatusInfracion', function(){
        return {
        	templateUrl : "views/catalogos/crudStatusInfracion.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudDenominacion', function(){
        return {
        	templateUrl : "views/catalogos/crudDenominacion.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudConcepto', function(){
        return {
        	templateUrl : "views/catalogos/crudConcepto.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudEntidad', function(){
        return {
        	templateUrl : "views/catalogos/crudEntidad.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudBanco', function(){
        return {
        	templateUrl : "views/catalogos/crudBanco.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudTipoIngreso', function(){
        return {
        	templateUrl : "views/catalogos/crudTipoIngreso.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudCausaIngreso', function(){
        return {
        	templateUrl : "views/catalogos/crudCausaIngreso.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudArea', function(){
        return {
        	templateUrl : "views/catalogos/crudArea.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudComponente', function(){
        return {
        	templateUrl : "views/catalogos/crudComponente.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudArticulo', function(){
        return {
        	templateUrl : "views/catalogos/crudArticulo.html",
        	controller: "articuloController"
        }        
 })
 
  angular.module('siidfApp').directive('crudPrograma', function(){
        return {
        	templateUrl : "views/catalogos/crudPrograma.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudCategoria', function(){
        return {
        	templateUrl : "views/catalogos/crudCategoria.html"
        }        
 })
 
  angular.module('siidfApp').directive('crudCausales', function(){
        return {
        	templateUrl : "views/catalogos/crudCausales.html"
        }        
 })
 
 