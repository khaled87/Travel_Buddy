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
                when('/products', {
                    templateUrl: 'partials/products/products.html',
                    controller: 'ProductListCtrl'
                }).
                when('/products/:id', {
                    templateUrl: 'partials/products/product-detail.html',
                    controller: 'ProductDetailCtrl'
                }).
                when('/product', {
                    templateUrl: 'partials/products/product-new.html',
                    controller: 'ProductNewCtrl'
                }).  
                when('/customers', {
                    templateUrl: 'partials/customers/customers.html'
                    //controller: Not used
                }).
                when('/orders', {
                    templateUrl: 'partials/orders/orders.html'
                    //controller: Not used
                }).
                when('/auth', {
                    templateUrl: 'auth.html',
                    controller: 'LoginCtrl'
                }).
                otherwise({
                    redirectTo: '/index.html'
                });

    }]);


