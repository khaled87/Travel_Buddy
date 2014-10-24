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

productCatalogueControllers.controller('homeCtrl', ['$scope', 'Auth', '$cookieStore', '$location', 
    function ($scope, Auth, $cookieStore, $location) {
        var showAuthorizedControls = function(isAdmin) {
            $("#signInBtn").hide();
            $("#signOutBtn").show();
            $("#userIcon").show();
            $("#userBtn").show();
            $("#userBtn").html($scope.user.name);
            if (isAdmin === true) {
                $("#adminBtn").show();
            }
        };
        
        var hideAuthorizedControls = function() {
            $("#adminBtn").hide();
            $("#userIcon").hide();
            $("#userBtn").hide();
            $("#signOutBtn").hide();
            $("#signInBtn").show();
        };
        
        $scope.login = function (redirect) {
            Auth.login($scope.user.name, $scope.user.password)
                .success(function (user) {
                    console.log("Log in successfully!");
                    $scope.user = user;
                    $cookieStore.put('user', user);
                    showAuthorizedControls(user.role === "ADMIN");
                    if (redirect === true) {
                        if (user.role === "ADMIN") {
                            $location.path("/admin");
                        } else {
                            $location.path("/orders");
                        }
                    }
                }).error(function () {
                    Auth.clearCredentials();
                    $scope.message = "Bad credentials";
                    console.log("Bad credentials!");
                });
        };

        $scope.logout = function () {
            Auth.clearCredentials();
            hideAuthorizedControls();
            $cookieStore.remove('user');
            $location.path("/home");
        };
        
        $scope.autologin = function() {
            console.log("Auto logging in...");
            $scope.user = $cookieStore.get('user');
            console.log($scope.user);
            if ($scope.user !== undefined) {
                $scope.login(false);
            } else {
                hideAuthorizedControls();
            }
        };
}]);