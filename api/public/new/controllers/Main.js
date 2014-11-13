'use strict'

/*
	 On peut utiliser this dans le cas où on utilise
	 controllerAs dans le routeProvider de app.js
	 parce qu'il est bindé sur $scope
*/
function MainController() {
	this.obj = {}; 
}

angular
.module('app')
.controller('MainCtrl', MainController);