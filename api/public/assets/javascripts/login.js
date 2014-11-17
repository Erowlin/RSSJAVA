var rssjava = angular.module('rssjava', ['ngCookies', 'angular-flash.service', 'angular-flash.flash-alert-directive']).controller('LoginController', ['$scope', '$http', '$cookies', 'flash', function ($scope, $http, $cookies, flash) {
    $scope.submit = function () {
        console.log($scope.user);
    };

    $scope.login = function () {
        $http.post('/users/access', $scope.user)
            .success(function (user) {
                console.log(user);
                $cookies.userId = user.id;
                $cookies.token = user.token;
                flash.success = 'Logged in successfully !';
                window.location = '/?access_token='+user.token;
            })
            .error(function (error) {
                console.log('error');
                flash.error = 'Bad username or password.';
                //console.log(error);
            });
    };

    $scope.create = function () {
        $http.post('/users/new', $scope.user)
            .success(function (user) {
                console.log(user);
            })
            .error(function (error) {
                flash.error = 'Please enter a correct email and password.';
                console.log(error);
            });
    };
}]);

rssjava.config(function (flashProvider) {
    flashProvider.errorClassnames.push('alert-danger');
    flashProvider.warnClassnames.push('alert-warning');
});
