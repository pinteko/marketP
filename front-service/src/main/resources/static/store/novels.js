angular.module('market-front').controller('novelController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:3000';

    let current_page = 1;
    $scope.count_pages = 2;

    $scope.loadBooks = function (page, min_rating, max_rating,
                                 min_price, max_price, title_part, names, surname) {
        $http({
            url: contextPath + '/api/v1/novels',
            method: 'GET',
            params: {
                p: page,
                min_rating: min_rating,
                max_rating: max_rating,
                min_price: min_price,
                max_price: max_price,
                title_part: title_part,
                names: names,
                surname: surname
            }
        }).then(function (response) {
                $scope.bookList = response.data.content;
                console.log($scope.bookList);
            });
    };

    $scope.prevPage = function ()
    {
        if (current_page > 1) {
            current_page--;
            $scope.changePage(current_page);
        }
    };

    $scope.goNextPage = function ()
    {
        if (current_page < $scope.count_pages) {
            current_page++;
            $scope.changePage(current_page);
        }
    };

    $scope.changePage = function (page)
    {
        $scope.loadBooks(page, null, null, null, null, null, null);
        current_page = page;
        console.log(current_page);
    };

    // $scope.addNovelInCart = function (novelId){
    //     $http({
    //         url: contextPath + 'cart/' + $localStorage.springWebGuestCartId + '/add/' + novelId,
    //         method: 'GET'
    //     }).then(function (response){
    //         $scope.loadCart();
    //     });
    // };

    $scope.changeRating = function (novel_id, delta){
        $http({
            url: contextPath + '/api/v1/novels/edit/change_rating',
            method: 'GET',
            params: {
                novel_id: novel_id,
                delta: delta
            }
        }).then(function (response){
            $scope.loadBooks();
        });
    };

        // $scope.showCurrentUserInfo = function (){
        //     $http({
        //         url: contextPath + 'users/about_me',
        //         method: 'GET'
        //     }).then(function (response){
        //         $scope.aboutMe = response.data;
        //     });
        // };


    // $scope.deleteNovel = function (novel_id){
    //     $http({
    //         url: contextPath + 'novels/edit/delete_novel',
    //         method: 'DELETE',
    //         params: {
    //             novel_id: novel_id,
    //         }
    //     }).then(function (response){
    //         $scope.loadBooks();
    //     });
    // };
    //
    // $scope.createNovel = function () {
    //     console.log($scope.newNovel);
    //     console.log($scope.author);
    //     $http.post(contextPath + 'novels', $scope.newNovel)
    //         .then(function (response) {
    //             window.location.href = contextPath + 'novels.html';
    //             $scope.loadBooks();
    //         });
    // };


        $scope.loadBooks();



});