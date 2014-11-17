var rssjava = angular.module('rssjava', ['ngCookies']).controller('LoginController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {
    $scope.submit = function () {
        console.log($scope.user);
    };

    $scope.login = function () {
        $http.post('/users/access', $scope.user)
            .success(function (user) {
                console.log(user);
                $cookies.userId = user.id;
                $cookies.token = user.token;
                window.location = '/?access_token='+user.token;
            })
            .error(function (error) {
                console.log(error);
            });
    };

    $scope.create = function () {
        $http.post('/users/new', $scope.user)
            .success(function (user) {
                console.log(user);
            })
            .error(function (error) {
                console.log(error);
            });
    };
}]);
