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

.controller('View_mainCtrl', ['$scope', '$http', '$sce', function($scope, $http, $sce) {

    console.log("Entering view main controller..");
    actualiseChannels();

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

    $scope.getItems = function(id) {
        console.log('Getting items of site ' + id + '...');
        $http.get('/channels/' + id + '/items')
            .success(function(data, status, headers, config) {
                console.log('Successs!');
                console.log(data);
                $scope.entries = data;
            });
    }


    $scope.slideContent = function(id, read) {
        console.log('id: e_' + id);

        $('#e_' + id).slideToggle();
        $http.post('/channels/1/items/'+id+'?read='+read)
            .success(function(data){
                console.log("success");
                console.log(data);
            });
    }

    $scope.loadHtml = function(html) {
        return $sce.trustAsHtml(html);
    }


    function actualiseChannels(scope){
        $http.get('/channels')
            .success(function(data, status, headers, config) {
                console.log("success : ");
                // console.log(data);
                $scope.channels = data;
            })
            .error(function(data, error) {
                console.log('Error get channels');
                console.log(data);
             });
    }

    $("#actualise-channels").click(function(){
        actualiseChannels($scope);
        console.log($scope.channels);
    });


    // C'est comme ça qu'on fait un appel ajax avec Angular.
    // Et si possible faudrait faire comme dans leur tuto afficiel niveau hierarchisation
    // Et faire gaffe que t'inclue une  vue avec un tag html body etc...
    $scope.createChannel = function (channel) {
        console.log(channel);
        $http.post('/channels/new', channel)
            .success(function(data, status, headers, config) {
                console.log('success', data);
            })
            .error(function(data, status, headers, config) {
                console.log('error:', data);
            });
    };
}]);


function feedController($scope, $http) {

    // Defining example datas
    /* $scope.list = {
        id: '1',
        name: 'korben',
        unread: '6',
        entries: [{
            id: '1',
            title: 'Welcome to Toronto',
            date: 'Posted at 3:56pm, April 3, 2012',
            description: 'This is an example',
            read:true
        }, {
            id: '2',
            title: 'Welcome to Beijing',
            date: 'Posted at 3:00pm, April 4, 2012',
            description: 'This is an example',
            read:true
        }, {
            id: '3',
            title: 'Welcome to Paris',
            date: 'Posted at 3:56pm, April 5, 2012',
            description: 'This is an example',
            read:false
        }]
    };*/

}