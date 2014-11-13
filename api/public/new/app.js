'use strict';

function config($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl: 'views/main.html',
		controller: 'MainCtrl',
		controllerAs: 'main'		
	});
}

angular
.module('app')
.config(config);