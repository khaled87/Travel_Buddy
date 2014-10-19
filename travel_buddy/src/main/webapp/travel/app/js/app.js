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
    function($routeProvider) {  // Injected object $routeProvider
        $routeProvider.
               
                 when('/authentication', {
                    templateUrl: 'partials/authentication/authentication.html'
                   // controller: 'AuthenticationCtrl'
                }).
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
                otherwise({
                    redirectTo: '/index.html'
                });

    }]);


shop.controller('homeCtrl', ['$scope', function($scope) {
    $scope.data = "Home data";
}]);
