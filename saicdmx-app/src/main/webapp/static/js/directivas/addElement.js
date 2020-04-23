angular.module('siidfApp').directive('addElement', function() {
   return {
       scope: {
       	aregarElement: '=agregar',
       	elementHtml: '=?',
       	numMaxElement: '=?',
       	numElementActual: '=?'
        },
       link: function(scope, el, attr) {
      
       	
       	scope.$watch('aregarElement', function(agregar){
	       	if(agregar && scope.numElementActual < scope.numMaxElement){
	       	
	       	el.parent().append(scope.elementHtml);
	       	
	       	}
       	scope.aregarElement = false;
       	}, true);
       }
   }
});