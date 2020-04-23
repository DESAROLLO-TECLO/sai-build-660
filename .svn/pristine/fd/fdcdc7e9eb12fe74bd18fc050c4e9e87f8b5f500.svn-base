angular.module("siidfApp").filter("tiempoRelativo", [ function() {
	var result = function(date) {
		if (date === null) {
			return date;
		}
		var fecha = moment(date);
		var ahora = moment();
		return humanizeDuration(ahora.diff(fecha), { language: 'es', largest: 2, unitis: ['y', 'mo', 'w', 'd', 'h', 'm']});
	}
	return result;
} ]);

angular.module("siidfApp").filter("timeMinutes", [ function() {
	var result = function(date) {
		if (date === null) {
			return date;
		}
		 var fecha = new Date(date);
		 var ahora = new Date();
		
		 
		 
		 var st = new Date(moment(fecha).valueOf()).getTime();
         var et = new Date(moment(ahora).valueOf()).getTime();
         var duration = moment.duration(moment(et).diff(moment(st)));
         var minutes = duration.asMinutes();
		
		return minutes;
	}
	return result;
} ]);