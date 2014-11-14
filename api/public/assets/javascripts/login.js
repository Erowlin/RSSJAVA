var rssjava = angular.module('rssjava', []).controller('LoginController', ['$scope', '$http', function ($scope, $http) {
    $scope.submit = function () {
        console.log($scope.user);
    };

    $scope.login = function () {
        $http.post('/users/access', $scope.user)
            .success(function (token) {
                window.location = '/?access_token='+token;
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
