angular.module("siidfApp").filter('groupBy', function($parse) {
	return _.memoize(function(items, filtro) {
		var getter = $parse(filtro);
		return _.groupBy(items, function(item) {
			return getter(item);
		});
	});
});

angular.module("siidfApp").filter('cortarTexto', function() {
	return function(input, limit) {
 		return (input.length > limit) ? input.substr(0, limit): input;
	};

});
 