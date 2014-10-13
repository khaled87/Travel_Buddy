'use strict';

/* Services */

var productCatalogueService = angular.module('ProductCatalogueService', []);

// Representing the remote RESTful ProductCatalogue
productCatalogueService.factory('ProductCatalogueProxy', ['$http',
    function($http) {
        var url = 'http://localhost:8080/jpa_shop_skel/api/v1/products';

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
            update: function(id, product) {
                return $http.put(url + "/" + id, product);
            },
            create: function(product) {
                return $http.post(url, product);
            },
            delete: function(id) {
                return $http.delete(url + "/" + id);
            },
            count: function() {
                return $http.get(url + "/count");
            }
        };
    }]);


productCatalogueService.factory('Auth', ['$base64', '$http',
    function(base64, $http) {
     
        return {
            login: function(username, password) {
                var encoded = base64.encode(username + ':' + password);
                $http.defaults.headers.common.Authorization = 'Basic ' + encoded;
                return $http.post('http://localhost:8080/ws_shop_skel/api/v1/auth', 'Basic ' + encoded);
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
            }
        };
    }]);