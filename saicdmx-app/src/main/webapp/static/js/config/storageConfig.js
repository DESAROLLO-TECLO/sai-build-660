angular.module('siidfApp')
.config(['$localStorageProvider',
function ($localStorageProvider) {
	
	$localStorageProvider.setKeyPrefix('-');

}])