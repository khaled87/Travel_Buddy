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

productCatalogueControllers.controller('ProductListCtrl', ['$scope', 'PackageProxy',
    function($scope, PackageProxy) {
        $scope.pageSize = '9';
        $scope.currentPage = 0;
        PackageProxy.count()
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
            PackageProxy.findRange(fst, $scope.pageSize)
                    .success(function(products) {
                        debugger;
                        console.log(products);
                        $scope.products = products;
                        createDashboard($scope.products);
                    }).error(function() {
                console.log("findRange: error");
            });
        }
        var createDashboard = function (dataSource) {
            $.get("partials/products/product-detail.html", function (detail) {
                $.get("partials/products/product-overview.html", function (overview) {
                    var options = {
                        marginLeft: 0,
                        marginTop: 0,
                        rightPanelTilesWidth: 250,
                        rightPanelTilesHeight: 250,
                        dataSource: dataSource,
                        maximizedState: detail,
                        minimizedState: overview,
                        rendered: function (event, ui) {
                            $('#dashboard').find('ul').igTree();
                        }
                    };

                    $('#dashboard').igTileManager(options);
                });
            });
        };
    }]);

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

        $scope.selectFlight1 = function(subTrip) {
            var sf1 = {};
            sf1.arrivalTime = subTrip.arrivalTime;
            sf1.departureTime = subTrip.departureTime;
            sf1.origin = subTrip.origin;
            sf1.destination = subTrip.destination;
            sf1.passangers = $scope.adultCount;
            $scope.selectedFlight1 = sf1;
        };

        $scope.selectFlight2 = function(subTrip) {
            var sf2 = {};
            sf2.arrivalTime = subTrip.arrivalTime;
            sf2.departureTime = subTrip.departureTime;
            sf2.origin = subTrip.origin;
            sf2.destination = subTrip.destination;
            sf2.passangers = $scope.adultCount;
            $scope.selectedFlight2 = sf2;
        };


        $scope.selectHotel = function(hotel) {
            $scope.selectedHotel = hotel;
            // $scope.bfont = "font: 10px sans-serif;";
        };

        $scope.createPackage = function() {
            var request = {};
            request.product = $scope.package;
            request.product.img = $scope.img;
            request.product.imgSrc = $scope.imgSrc;
            request.product.formData = $scope.formData;
            debugger;
            var nicE = new nicEditors.findEditor('package-description');
            request.product.detail = nicE.getContent();
            request.hotel = $scope.selectedHotel;
            request.flight1 = $scope.selectedFlight1;
            request.flight2 = $scope.selectedFlight2;
            ProductCatalogueProxy.createPackage(request).success(function(pack) {
                $scope.packageCreated = "Package" + pack.name + "created!";
                alert("Package " + pack.name + " created!");
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

productCatalogueControllers.controller('CreditcardController', ['$scope', 'ProductCatalogueProxy',
    function($scope, ProductCatalogueProxy) {
        
        $scope.verify = function()
        {
           var paymentInfo = {};
           paymentInfo.account = $scope.account;
           paymentInfo.holder = $scope.holder;
           paymentInfo.ccv = $scope.ccv;
           paymentInfo.price = 123;
           ProductCatalogueProxy.verify(paymentInfo).success(function(bankResponse) {
                if(bankResponse.ok.equals("okay"))
                {
                    alert("payment ok");
                }
                else if(bankResponse.err.equals("accountErr"))
                {
                    alert("There was an error with your account");
                }
                else if(bankResponse.err.equals("moneyErr"))
                {
                    alert("Not enough money in your account");
                }
               
            }).error(function(e) {
                console.log(e);
               // console.log(bankResponse);
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

        $scope.logout = function() {
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
       