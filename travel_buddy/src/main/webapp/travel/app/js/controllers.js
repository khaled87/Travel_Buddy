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
            $scope.getFlightList();
            $scope.getHotels();
        };

        $scope.getFlight = function(searchedFlight) {
            ProductCatalogueProxy.getFlightList(searchedFlight).success(function(flightList) {
                console.log(flightList);
               return flightList;
            }).error(function(e) {
                console.log(e);
                return "";
            });  
        };
        
        $scope.getFlightList = function()
        {
            $scope.counter = 1;
            $scope.clearFlight1Visible = false;
            $scope.clearFlight2Visible = false;
            
            var searchedFlight1 = {};
            searchedFlight1.origin = $scope.origin;
            searchedFlight1.destination = $scope.destination;
            searchedFlight1.date = $scope.arrivalDate;
            searchedFlight1.adultCount = $scope.adultCount;
            ProductCatalogueProxy.getFlightList(searchedFlight1).success(function(flightList1) {
               $scope.trips1 = flightList1;
            }).error(function(e) {
                console.log(e);
            });  
     
            var searchedFlight2 = {};
            searchedFlight2.origin = $scope.destination;
            searchedFlight2.destination = $scope.origin;
            searchedFlight2.date = $scope.departureDate;
            searchedFlight2.adultCount = $scope.adultCount;
            ProductCatalogueProxy.getFlightList(searchedFlight2).success(function(flightList2) {
               $scope.trips2 = flightList2;
            }).error(function(e) {
                console.log(e);   
            }); 
            
        };

        $scope.selectFlight = function(flightName) {
            $scope.items2 = ["1", "2"];
            if ($scope.counter === 1)
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
        
        $scope.selectHotel = function(hotel){
            $scope.selectedHotel = hotel;
           // $scope.bfont = "font: 10px sans-serif;";
        };
        
        $scope.createPackage = function() {
            var request = {};
            request.product = $scope.package;
            request.hotel = $scope.selectedHotel;
            ProductCatalogueProxy.createPackage(request).success(function(pack) {
                $scope.packageCreated = "Package" + pack.name + "crated!";
            }).error(function(e) {
                console.log(e);
            });
        };
        
        
        $scope.getHotels = function() {

            var hotelInfo = {};
            hotelInfo.city = "Seattle";//$scope.destination;
            hotelInfo.arrivalDate = "09/04/2015";//$scope.arrivalDate;
            hotelInfo.departureDate = "09/05/2015";//$scope.departureDate;
            ProductCatalogueProxy.getHotels(hotelInfo).success(function(hotelList) {
                $scope.retrievedHotels = hotelList;
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