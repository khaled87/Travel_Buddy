'use strict';

/* 
 *  The Shop App
 */
var shop = angular.module('Shop', [
    'ngRoute', 'base64', 'ngCookies',
    'ProductCatalogueControllers',
    'ProductCatalogueService'
]);

shop.config(['$routeProvider',
    function ($routeProvider) {  // Injected object $routeProvider
        $routeProvider.
                when('/home', {
                    templateUrl: 'partials/home/home.html'
                }).
                when('/user', {
                    templateUrl: 'partials/users/user-detail.html',
                    controller: 'AdminController'
                }).
                when('/products', {
                    templateUrl: 'partials/products/products.html',
                    controller: 'ProductListCtrl'
                }).
                when('/products/:id', {
                    templateUrl: 'partials/products/products.html',
                    controller: 'ProductDetailCtrl'
                }).
                when('/orders', {
                    templateUrl: 'partials/orders/orders.html'
                }).
                when('/admin', {
                    templateUrl: 'partials/authentication/admin_page.html',
                    controller: 'AdminController'
                }).
                when('/auth', {
                    templateUrl: 'auth.html',
                    controller: 'homeCtrl'
                }).
                when('/creditcard/:id', {
                    templateUrl: 'partials/products/creditcard-input.html',
                    controller: 'CreditcardController'
                }).
                otherwise({
                    redirectTo: 'partials/home/home.html'
                });
    }]);

