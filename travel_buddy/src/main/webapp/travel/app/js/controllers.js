'use strict';

/*
 * Controllers
 */

var productCatalogueControllers = angular.module('ProductCatalogueControllers', []);

// General navigation controller (possibly useful?)
productCatalogueControllers.controller('NavigationCtrl', ['$scope', '$location',
    function($scope, $location) {
        $scope.navigate = function(url) {
            console.log(url);
            $location.path(url);
        };
    }]);

productCatalogueControllers.controller('AuthenticationCtrl', ['$scope', 'AuthenticationCtrlProxy',
    function($scope, AuthenticationCtrlProxy) {

        $scope.pageSize = '7';
        $scope.currentPage = 0;
        ProductCatalogueProxy.count()
                .success(function(count) {
                    $scope.count = count.value;
                }).error(function() {
           console.log("count: error");
        });
        getRange();
        $scope.$watch('currentPage', function() {
            getRange();
        });
        function getRange() {
            var fst = $scope.pageSize * $scope.currentPage;
            ProductCatalogueProxy.findRange(fst, $scope.pageSize)
                    .success(function(products) {
                        $scope.products = products;
                    }).error(function() {
                console.log("findRange: error");
            });
        }
    }]);

productCatalogueControllers.controller('ProductDetailCtrl', ['$scope', '$location', '$routeParams', 'ProductCatalogueProxy',
    function($scope, $location, $routeParams, ProductCatalogueProxy) {
        ProductCatalogueProxy.find($routeParams.id).success(function(product) {
            $scope.product = product;
        }).error(function(e) {
            console.log(e);
        });
        
        $scope.update = function() {
            console.log($scope.product);
            ProductCatalogueProxy.update($routeParams.id, $scope.product).success(function() {
                $location.path('/products');
            }).error(function(e) {
                console.log(e);
            });
        };
        $scope.delete = function() {
            ProductCatalogueProxy.delete($routeParams.id).success(function() {
                $location.path('/products');
            }).error(function(e) {
                console.log(e);
            });
        };
    }
]);

productCatalogueControllers.controller('ProductNewCtrl', ['$scope', '$location', 'ProductCatalogueProxy',
    function($scope, $location, ProductCatalogueProxy) {
        $scope.create = function() {
            ProductCatalogueProxy.create($scope.product).success(function() {
                $location.path('/products');
            }).error(function(e) {
                console.log(e);
            });
        };
    }
]);


productCatalogueControllers.controller('LoginCtrl', ['$scope', 'Auth', '$location',
    function($scope, Auth, $location) {

        $scope.login = function() {
            Auth.login($scope.user.name, $scope.user.passwd)
                .success(function() {
                    $location.path("/products");
                }).error(function() {
                    Auth.clearCredentials();
                    $scope.message = "Bad credentials";
                });
        };

        $scope.logout = function() {
            Auth.clearCredentials();
            $location.path("/auth");
        };
    }
]);