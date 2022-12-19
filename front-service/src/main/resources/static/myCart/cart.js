angular.module('market-front').controller('cartController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/cart/';


    $scope.loadCart = function () {
        $http.get(contextPath + 'api/v1/cart/' + $localStorage.springWebGuestCartId)
            .then(function (response) {
                $scope.cart = response.data;
                console.log($scope.cart);
            });
    };

    $scope.decrementNovel = function (novelId){
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.springWebGuestCartId + '/decrement/' + novelId,
            method: 'GET'
        }).then(function (response){
            $scope.loadCart();
        });
    };

    $scope.deleteNovel = function (novelId){
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.springWebGuestCartId + '/remove/' + novelId,
            method: 'DELETE'
        }).then(function (response){
            $scope.loadCart();
        });
    };

    $scope.addNovel = function (novelId){
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.springWebGuestCartId + '/add/' + novelId,
            method: 'GET'
        }).then(function (response){
            $scope.loadCart();
        });
    };

    $scope.clearCart = function (){
        $http({
            url: contextPath + 'api/v1/cart/' + $localStorage.springWebGuestCartId + '/clear',
            method: 'GET'
        }).then(function (response){
            $scope.loadCart();
        });
    };

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.createOrder = function (){
        $http({
            url: 'http://localhost:5555/book/orders',
            method: 'POST',
            data: $scope.orderDetails
        }).then(function (response){
            $scope.loadCart();
            $scope.orderDetails = null;
        });
    };

    $scope.loadCart();

});