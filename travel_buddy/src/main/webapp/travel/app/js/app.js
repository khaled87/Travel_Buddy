'use strict';

/* 
 *  The Shop App
 */
var shop = angular.module('Shop', [
    'ngRoute', 'base64', 'ngCookies',
    'ProductCatalogueControllers',
    'ProductCatalogueService'
     // More here
]);


shop.config(['$routeProvider',
    function($routeProvider) {  // Injected object $routeProvider
        $routeProvider.
               
                when('/customers', {
                    templateUrl: 'partials/customers/customers.html',
                    controller: 'homeCtrl'
                }).
                when('/orders', {
                    templateUrl: 'partials/orders/orders.html'
                    //controller: 
                }).
                when('/newProduct', { 
                    templateUrl: 'partials/products/product-new.html',
                    controller: 'NewProductsController'
                }).
                when('/updateProduct/:id', {
                    templateUrl:'partials/products/product-detail.html',
                    controller:'UpdateProductsController'
                }).
                when('/admin', {
                    templateUrl:'partials/authentication/admin_page.html',
                    controller:'AdminController'
                }).
                 when('/auth', {
                templateUrl: 'auth.html',
               controller: 'LoginCtrl'
                }).
                otherwise({
                    redirectTo: '/index.html'
                });
    }]);

shop.controller('homeCtrl', ['$scope', function($scope) {
    $scope.data = "Home data";
}]);
