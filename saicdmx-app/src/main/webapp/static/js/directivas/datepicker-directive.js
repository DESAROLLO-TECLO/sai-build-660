/*
 * Author: Jesús Gutierrez
 * Directive: datepicker-directive
 * Versión: 1.1.0
 */

angular.module('siidfApp').directive('datePicker', function() {
	return {
	       require: 'ngModel',
	       scope: {
	        mindate: '=',
	        maxdate: '=',
	        idiomaDp: '@',
	        elemento: '=',
	        valuemodel: '=',
	        },
	       link: function(scope, el, attr, ngModel) {
	        var lanG;
	        scope.$watch('idiomaDp', function(lang){
	        lanG=lang;
	       
	        creaDatePiker();
	       
	        });
	       
	        scope.$watch('valuemodel', function(newModel){
	        creaDatePiker();
	        });
	       
	       
	        function creaDatePiker(){
	        $(el).datepicker("remove");
	       
	        switch(lanG) {
	case "es_ES" :
	lanG = 'es';
	break;
	case "en_US" :
	lanG = 'en';
	break;
	default:
	lanG = 'es';
	    }
	       
	        $(el).datepicker({
	                   format: 'dd/mm/yyyy',
	                   autoclose: true,
	                   language: lanG,
	                   todayHighlight: true,
	                   todayBtn: "linked"
	               }).on('changeDate', function (selected) {
	               
	                if(scope.maxdate != undefined){
	                $('#'+scope.elemento).datepicker('setEndDate', scope.maxdate);
	                }
	               
	                if(scope.mindate != undefined){
	                $('#'+scope.elemento).datepicker('setStartDate', scope.mindate);
	                }
	               
	            });
	           
	            if(scope.valuemodel != undefined && scope.valuemodel != "" && scope.valuemodel != null){
	            var fechaDate = new Date(scope.valuemodel);
	                $('#'+scope.elemento).datepicker( "setDate",fechaDate.getDate());
	            }
	        };
	       
	       }
	   }
});