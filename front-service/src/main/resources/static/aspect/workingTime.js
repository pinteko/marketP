angular.module('market-front').controller('aspectController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/app/';



    $scope.getWorkingTime = function () {
        $http.get(contextPath + 'statistic')
            .then(function (response) {
                $scope.aspectList = response.data;
            });
    };


    $scope.getWorkingTime();

});