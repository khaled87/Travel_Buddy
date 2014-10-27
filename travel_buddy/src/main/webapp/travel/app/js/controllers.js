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

productCatalogueControllers.controller('ConfirmationCtrl', ['$scope', '$location', '$routeParams', 'PackageProxy',
    function ($scope, $location, $routeParams, PackageProxy) {
        PackageProxy.getPurchaseOrder($routeParams.id)
                .success(function (purchaseOrder) {
                    //$scope.purchaseOrder = purchaseOrder;
                    alert('purchaseorder' + purchaseOrder);
                }).error(function () {
            console.log("purchaseOrder: error");
        });
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
                        $scope.products = products;
                        createDashboard($scope.products);
                    }).error(function() {
                console.log("findRange: error");
            });
        }
        var createDashboard = function (dataSource) {
            debugger;
            var user = angular.element($("#headerPadding")).scope().user;
            var productdetail = "partials/products/product-detail.html";
            if (user !== null && user !== undefined && user.role === 'ADMIN') {
                productdetail = "partials/products/product-detail-admin.html";
            }
            $.get(productdetail, function (detail) {
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

productCatalogueControllers.controller('AdminController', ['$scope', 'ProductCatalogueProxy', '$location',
    function($scope, ProductCatalogueProxy, $location) {
        var calculatePrice = function() {
            debugger;
            var price = 0;
            if ($scope.selectedFlight1 !== undefined && $scope.selectedFlight1 !== null) {
                price += $scope.selectedFlight1.price;
            }
            if ($scope.selectedFlight2 !== undefined && $scope.selectedFlight2 !== null) {
                price += $scope.selectedFlight2.price;
            }
            if ($scope.selectedHotel !== undefined && $scope.selectedHotel !== null) {
                price += $scope.selectedHotel.price;
            }
            $scope.package = $scope.package || {};
            $scope.package.price = price;
        };
        
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
                console.log(flightList1);
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
                console.log(flightList2);
                $scope.trips2 = flightList2;
            }).error(function(e) {
                console.log(e);
            });

        };

        $scope.selectFlight1 = function(trip) {
            $scope.selectedFlight1 = trip;
            calculatePrice();
        };

        $scope.selectFlight2 = function(trip) {
            $scope.selectedFlight2 = trip;
            calculatePrice();
        };

        $scope.selectHotel = function(hotel) {
            $scope.selectedHotel = hotel;
            calculatePrice();
        };

        $scope.createPackage = function() {
            if ($scope.package === undefined || $scope.package === null ||
                $scope.package.name === undefined || $scope.package.name === null || $scope.package.name === "" ||
                $scope.package.price === undefined || $scope.package.price === null || $scope.package.name < 0) {
                alert('Please input product name and price');
                return;
            }
            if ($scope.hotel === null) {
                alert('Please select hotel');
                return;
            }
            var request = {};
            request.product = $scope.package;

            if ($scope.img !== undefined) {
                request.product.img = $scope.img;
            }
            if ($scope.imgSrc !== undefined) {
                request.product.imgSrc = $scope.imgSrc;
            }
            if ($scope.formData !== undefined) {
                request.product.formData = $scope.formData;
            }
            
            var nicE = new nicEditors.findEditor('package-description');
            if (nicE.getContent !== undefined) {
                request.product.description = nicE.getContent();
            }
            request.hotel = $scope.selectedHotel;
            request.flight1 = $scope.selectedFlight1;
            request.flight2 = $scope.selectedFlight2;
            ProductCatalogueProxy.createPackage(request).success(function(pack) {
                $scope.packageCreated = "Package" + pack.name + "created!";
                alert("Package " + pack.name + " created!");
                $scope.selectedHotel = null;
                $scope.selectedFlight1 = null;
                $scope.selectedFlight2 = null;
                $scope.package = null;
                $location.path("/products");
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

<<<<<<< HEAD
productCatalogueControllers.controller('CreditcardController', ['$scope', '$location', '$routeParams', 'ProductCatalogueProxy', 'PackageProxy',
    function($scope, $location, $routeParams, ProductCatalogueProxy, PackageProxy) {
        debugger;
=======
productCatalogueControllers.controller('CreditcardController', ['$scope', '$routeParams', 'ProductCatalogueProxy', 'PackageProxy',
    function($scope, $routeParams, ProductCatalogueProxy, PackageProxy) {
>>>>>>> origin/master
        PackageProxy.find($routeParams.id).success(function(product) {
            console.log(product);
            $scope.product = product;
        }).error(function(e) {
            console.log(e);
        });
        
        $scope.verify = function()
        {
           var paymentInfo = {};
           paymentInfo.account = $scope.account;
           paymentInfo.holder = $scope.holder;
           paymentInfo.ccv = $scope.ccv;
           paymentInfo.email = $scope.email;
           paymentInfo.product = $scope.product;
           ProductCatalogueProxy.verify(paymentInfo).success(function(bankResponse) {
                if(bankResponse.ok.equals("okay"))
                {
                    $location.path("/confirmation/" + bankResponse.confirmationCode);
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
        
        hideAuthorizedControls();
        
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
                    hideAuthorizedControls();
                    console.log("Bad credentials!");
                });
        };

        $scope.logout = function() {
            $scope.user = null;
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

productCatalogueControllers.controller('ProductDeleteCtrl', ['$scope', '$location', '$routeParams', 'ProductCatalogueProxy',
    function($scope, $location, $routeParams, ProductCatalogueProxy) {
        var id = $routeParams.id;
        ProductCatalogueProxy.canDelete(id).success(function(result) {
            if (result.value === true) {
                ProductCatalogueProxy.deletePackage(id).success(function() {
                }).error(function(e) {
                    console.log(e);
                });
            } else {
                alert('Cannot delete this package. There are customers having already booked it.');
            }
        }).error(function(e) {
            console.log(e);
        });
        $location.path('/products');
    }
]);
       