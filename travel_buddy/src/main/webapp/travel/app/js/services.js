'use strict';

/* Services */

var productCatalogueService = angular.module('ProductCatalogueService', []);

// Representing the remote RESTful ProductCatalogue
productCatalogueService.factory('ProductCatalogueProxy', ['$http',
    function($http) {
        var url = 'http://localhost:8080/travel_buddy/api/v1/travels';
        var urlBank = 'http://localhost:8080/travel_buddy/api/v1/creditcard';

        return {
            deletePackage: function(id) {
                return $http.delete(url + "/" + id);
            },
            getFlightList: function(flightInfo) {
                return $http.post(url+"/flights", flightInfo);
            },
            createPackage: function(pack) {
                return $http.post(url, pack);
            },
            getHotels: function(hotelInfo){
                return $http.post(url+"/hotels", hotelInfo);
            },
            verify: function(paymentInfo){
                 return $http.post(urlBank+"/verify", paymentInfo);
            },
            canDelete: function(id) {
                return $http.get(url + "/candelete/" + id);
            }
        };
    }]);

productCatalogueService.factory('PackageProxy', ['$http',
    function($http) {
        var url = 'http://localhost:8080/travel_buddy/api/v1/products';
     
        return {
            findAll: function() {
                return $http.get(url);
            },
            findRange: function(first, count) {
                return $http.get(url + "/range?fst=" + first + "&count=" + count);
            },
            find: function(id) {
                return $http.get(url + "/" + id);
            },
            count: function() {
                return $http.get(url + "/count");
            },
            getPurchaseOrder: function(id) {
                return $http.get(url+ "/" + "confirmation" + "/" + id);
            }
        };
    }]);

productCatalogueService.factory('ConfirmationProxy', ['$http',
    function($http) {
        var url = 'http://localhost:8080/travel_buddy/api/v1/confirmation';

        return {
            getPurchaseOrder: function() {
                return $http.get(url + "id");
            }
        };
    }]);

productCatalogueService.factory('Auth', ['$base64', '$http',
    function(base64, $http) {
        return {
            login: function(username, password) {
                var encoded = base64.encode(username + ':' + password);
                $http.defaults.headers.common.Authorization = 'Basic ' + encoded;
                return $http.post('http://localhost:8080/travel_buddy/api/v1/auth', 'Basic ' + encoded);
            },
            setCredentials: function(username, password) {
                // Auth dta just set in local app , Server not contacted
                var encoded = base64.encode(username + ':' + password);
                $http.defaults.headers.common.Authorization = 'Basic ' + encoded;
            },
            
            clearCredentials: function() {
                document.execCommand("ClearAuthenticationCache"); // TODO not standard
                //$cookieStore.remove('authdata');
                $http.defaults.headers.common.Authorization = 'Basic ';
            },
        };
    }]);