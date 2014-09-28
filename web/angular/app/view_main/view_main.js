'use strict';

angular.module('myApp.view_main', ['ngRoute'])

.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.when('/view_main', {
            templateUrl: 'view_main/view_main.html',
            controller: 'View_mainCtrl'
        });
    }
])

.controller('View_mainCtrl', [

        function() {

            console.log("Entering view main controller..");

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

            

        }]);

    function entriesController($scope) {
        // Defining example datas
        $scope.listEntry = [{
            id: '1',
            title: 'Welcome to Toronto',
            date: 'Posted at 3:56pm, April 3, 2012',
            description: 'This is an example'
        }, {
            id: '2',
            title: 'Welcome to Beijing',
            date: 'Posted at 3:00pm, April 4, 2012',
            description: 'This is an example'
        }, {
            id: '3',
            title: 'Welcome to Paris',
            date: 'Posted at 3:56pm, April 5, 2012',
            description: 'This is an example'
        }, ];

        $scope.slideContent = function(id) {
            console.log('id: e_' + id);

            $('#e_' + id).slideToggle();
        }
    }