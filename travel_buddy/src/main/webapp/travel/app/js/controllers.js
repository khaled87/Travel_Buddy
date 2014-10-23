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

productCatalogueControllers.controller('AdminController', ['$scope', 'ProductCatalogueProxy',
    function($scope, ProductCatalogueProxy) {
        $scope.package = null;
        $scope.callService = function()
        {
          //  $scope.getFlightList();
            $scope.getHotels();
        }
        
        $scope.getFlightList = function() {      
            $scope.flightName = "flight1";
            $scope.items = ["1","2","3"];
            
            $scope.counter = 1;
            $scope.clearFlight1Visible = false;
            $scope.clearFlight2Visible = false;
//            ProductCatalogueProxy.getFlightList($scope.flightInfo).success(function(flightList) {
//                $scope.retrievedFlight = flightList;
//                $scope.flightName = "flight1";
//                console.log(flightList);
//            }).error(function(e) {
//                console.log(e);
//            });
        };
        
        $scope.selectFlight = function(flightName) {
            $scope.items2 = ["1","2"];
            if($scope.counter === 1)
            {
               $scope.selectedFlightName1 = "f1 selected";
               $scope.counter = 2;
               $scope.clearFlight1Visible = true;
           
            }   
            else
            {
                $scope.selectedFlightName2 = "f2 selected"; 
                $scope.counter = 1;
                 $scope.clearFlight2Visible = true;
            }
        };
        
        $scope.clearFlight1 = function() {
            $scope.selectedFlightName1 = "";
            $scope.counter = 1;
              $scope.clearFlight1Visible = false;
        };
        
        $scope.clearFlight2 = function() {
            $scope.selectedFlightName2 = "";
            $scope.counter = 2;
            $scope.clearFlight2Visible = false;
        };
    
        $scope.createPackage = function() {
            ProductCatalogueProxy.createPackage($scope.package).success(function(pack) {
                $scope.packageCreated = "Package" + pack.name + "crated!";
            }).error(function(e) {
                console.log(e);
            }); 
        };
        
        $scope.getHotels = function() {
//            $scope.package = null;
//            $scope.flightName = "flight1";
//            $scope.items = ["1","2","3"];
//            $scope.counter = 1;
//            $scope.clearFlight1Visible = false;
//            $scope.clearFlight2Visible = false;
            
           var hotelInfo = {};
           hotelInfo.city = "Seattle";//$scope.destination;
           hotelInfo.arrivalDate = "09/04/2015";//$scope.arrivalDate;
           hotelInfo.departureDate = "09/05/2015";//$scope.departureDate;
           
            console.log($scope.destination);
           
            ProductCatalogueProxy.getHotels(hotelInfo).success(function(hotelList) {
                $scope.retrievedHotels = hotelList;
                console.log(hotelList);
            }).error(function(e) {
                console.log(e);
            });
        };
    }
]);

productCatalogueControllers.controller('LoginCtrl', ['$scope', 'Auth', '$location',
    function ($scope, Auth, $location) {
        $scope.login = function () {
            Auth.login($scope.user.name, $scope.user.passwd)
                    .success(function () {
                        $location.path("/admin");
                    }).error(function () {
                Auth.clearCredentials();
                $scope.message = "Bad credentials";
            });
        };

        $scope.logout = function () {
            Auth.clearCredentials();
            $location.path("/auth");
        };
    }
]);