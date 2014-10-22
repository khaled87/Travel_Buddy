'use strict';

/* 
 *  The Shop App
 */
var shop = angular.module('Shop', [
    'ngRoute',
    'ProductCatalogueControllers',
    'ProductCatalogueService'
            // More here
]);


shop.config(['$routeProvider',
    function ($routeProvider) {  // Injected object $routeProvider
        $routeProvider.
                when('/home', {
                    templateUrl: 'partials/home/home.html'
                }).
                when('/authentication', {
                    templateUrl: 'partials/authentication/authentication.html'
                }).
                when('/products', {
                    templateUrl: 'partials/products/products.html',
                    controller: 'NavigationCtrl'
                }).
                when('/orders', {
                    templateUrl: 'partials/orders/orders.html'
                }).
                when('/newProduct', {
                    templateUrl: 'partials/products/product-new.html',
                    controller: 'NewProductsController'
                }).
                when('/updateProduct/:id', {
                    templateUrl: 'partials/products/product-detail.html',
                    controller: 'UpdateProductsController'
                }).
                when('/admin', {
                    templateUrl: 'partials/authentication/admin_page.html',
                    controller: 'AdminController'
                }).
                otherwise({
                    redirectTo: 'partials/home/home.html'
                });
    }]);


shop.controller('homeCtrl', ['$scope', function ($scope) {
        $scope.data = "Home data";
    }]);
