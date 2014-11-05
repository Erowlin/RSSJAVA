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

    console.log('Getting sites list ...');
    $http({
        url: '/channels',
        method: 'GET',
        dataType: 'json',
        data: '',
        headers: {
            'Content-Type': 'application/json',
        }
    }).success(function(data, status, headers, config) {
        console.log('Success');
        console.log(data);
        console.log(status);
        $scope.sites = data.sites;
        console.log($scope.sites);
    }).error(function(data, error) {
        console.log('Error get channels');
        //alert('[' + data + ']');
    });

    $scope.getEntries = function(id) {
        console.log('Getting entries of site ' + id + '...');
        $http({
            url: 'http://jsonstub.com/sites/' + id + '/entries',
            method: 'GET',
            dataType: 'json',
            data: '',
            headers: {
                'Content-Type': 'application/json',
                'JsonStub-User-Key': '7a965af9-41b7-4f6b-a505-140d36c465d5',
                'JsonStub-Project-Key': '226d6d25-f070-4b3b-8b81-ae52834528a4'
            }
        }).success(function(data, status, headers, config) {
            console.log('Success!');
            $scope.entries = data.entries; 
        });
    }

    $scope.slideContent = function(id) {
        console.log('id: e_' + id);

        $('#e_' + id).slideToggle();
    }

    $scope.loadHtml = function(html) {
        return $sce.trustAsHtml(html);
    }



    // Custom DOM JS 

    $("#actualise-channels").click(function(){
        $http({
            url: '/channels',
            method: 'GET',
            dataType: 'json',
            data: '',
            headers: {
                'Content-Type': 'application/json',
            }
        }).success(function(data, status, headers, config) {
            console.log('Success');
            console.log(data);
            console.log(status);
            $scope.sites = data.sites;
            console.log($scope.sites);
        }).error(function(data, error) {
            console.log('Error get channels');
            console.log(data);
        //alert('[' + data + ']');
    });
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

//    $("#add-new-channel-button").click(function(){
//        console.log('Validation');
//        $scope.createChannel = function(channel) {
//            $http({
//                url: '/channels/new',
//                method: 'POST',
//                dataType: 'json',
//                data: {
//                    'link': 'http://www.lemonde.fr/rss/une.xml',
//                    'title': 'Le Monde'
//                },
//                withCredentials: true,
//                headers: {
//                }
//            }).success(function(data, status, headers, config) {
//                $scope.entries = data.entries;
//                console.log(data);
//                console.log('NOUVEAU CHANNEL SUCCESS');
//            }).error(function(e){
//                console.log("error !");
//            });
//
//        };
//    });

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