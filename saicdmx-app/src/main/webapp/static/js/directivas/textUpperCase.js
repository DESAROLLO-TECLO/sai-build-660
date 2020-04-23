angular.module('siidfApp').directive('capitalize', function() {
	  return {
		    require: 'ngModel',
		    link: function(scope, element, attrs, modelCtrl) {
		      var capitalize = function(inputValue) {
		        if (inputValue == undefined) inputValue = '';
		        var capitalized = inputValue.toUpperCase();
		        if (capitalized !== inputValue) {
		          modelCtrl.$setViewValue(capitalized);
		          modelCtrl.$render();
		        }
		        return capitalized;
		      }
		      modelCtrl.$parsers.push(capitalize);
		      capitalize(scope[attrs.ngModel]); // capitalize initial value
		    }
		  };
});


/*angular.module('siidfApp').directive('camelCase', function() {
	 return {
	     require: 'ngModel',
	     link: function(scope, element, attrs, modelCtrl) {
	    	 var camelcase = function(input){
	    		 var convert = '';
	    		 if (input == undefined) input = '';
	    		 
		    	 if(input.indexOf(' ') !== -1){
		    	      var inputPieces, i;
	
		    	      input = input.toLowerCase();
		    	      inputPieces = input.split(' ');
	
		    	      for(i = 0; i < inputPieces.length; i++){
		    	        inputPieces[i] = capitalizeString(inputPieces[i]);
		    	      }
		    	      
		    	      convert = inputPieces.toString().replace(/,/g, ' ');
	    	 		}
		    	    else {
		    	      input = input.toLowerCase();
		    	      convert = capitalizeString(input);
		    	    }
		    	 
		    	 	if(convert !== input){
		    	 		modelCtrl.$setViewValue(convert);
	          			modelCtrl.$render();
		    	 	}
		    	 	
		    	 	return convert;
	
		    	    function capitalizeString(inputString){
		    	      return inputString.substring(0,1).toUpperCase() + inputString.substring(1);
		    	    }  
	    	 	}
	    	 
	    	 	modelCtrl.$parsers.push(camelcase);
	    	    camelcase(scope[attrs.ngModel]);
	     	}
	   };
});*/

angular.module('siidfApp').directive('globalEvents', function() {
	return function(scope, element, attrs) {
		element.bind('click', function(e){
			// Do something
			alert("Do something");
		});
	}
});


angular.module('siidfApp').directive('calendarElement', function(){
    return {
    	restrict : 'A',
    	link: function(scope, element, attrs) {
    		var variable = attrs.calendarElement;
    		if(variable == 'initial'){
    			$(element).datepicker({             
    				format: 'dd/mm/yyyy',
    				todayHighlight : true,
    				autoclose : true
    			}).on('changeDate', function (selected) {
    				var minDate = new Date(selected.date.valueOf());
    				$('#' + attrs.cfinal).datepicker('setStartDate', minDate);
    			});
    		}else if(variable == 'final'){
    			$(element).datepicker({             
    				format: 'dd/mm/yyyy',
    				todayHighlight : true,
    				autoclose : true
    			}).on('changeDate', function (selected) {
    				var maxDate = new Date(selected.date.valueOf());
    				$('#' + attrs.cinicial).datepicker('setEndDate', maxDate);
    			});
    		}else if(variable == 'backward'){
    			$(element).datepicker({             
        			format: 'dd/mm/yyyy',
        			endDate: '+0d',
        			todayHighlight : true,
        			autoclose : true
        		});
    		}
    		else{ //NORMAL CALENDAR
    			$(element).datepicker({             
        			format: 'dd/mm/yyyy',
        			todayHighlight : true,
        			autoclose : true
        		});
    		}
    		
    		
    	}        	
    }        
});





 

 

 


 

 

 

 

 

 

 
 