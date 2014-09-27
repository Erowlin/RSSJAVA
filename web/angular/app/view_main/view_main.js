'use strict';

angular.module('myApp.view_main', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view_main', {
    templateUrl: 'view_main/view_main.html',
    controller: 'View_mainCtrl'
  });
}])

.controller('View_mainCtrl', [function() {
    
    console.log("totot");
    $('.nav-menu-button').click(function(e) {
        e.preventDefault();
        if ($('#nav').hasClass('active')) {
            $('.nav-inner').slideToggle(function() {
                $('#nav').toggleClass('active');
            });
        } else {
            $('.nav-inner').slideToggle();
            $('#nav').toggleClass('active');
        }

    });

    $('#toronto').click(function() {
        $('#content_toronto').slideToggle();
    });

}]);



