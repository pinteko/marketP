angular.module('market-front').controller('ordersController', function ($scope, $http) {
    const contextPath = 'http://localhost:5555/cart/';

    $scope.loadOrders = function () {
        $http.get(contextPath + 'orders')
            .then(function (response) {
                $scope.ordersList = response.data;
            });
    }

    $scope.loadOrders();
});