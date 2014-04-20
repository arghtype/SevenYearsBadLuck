angular.module('years', ['ngRoute', 'ngResource'])

    .controller('MainController', function ($scope, $route, $routeParams, $location) {
        $scope.$route = $route;
        $scope.$location = $location;
        $scope.$routeParams = $routeParams;
    })

    .controller('LikeController', function ($scope, $routeParams) {
    })

    .controller('FortuneController', function ($scope, $routeParams, $resource) {
        $scope.receivedAdvice = '';
        $scope.receivedAdviceToPerson = '';

        var randomAdvice = $resource('webapi/advice');
        var adviceToPerson = $resource('webapi/advice/:name/:birthdate', {name: '@name', birthdate: '@birthdate'});

        $scope.getTextData = function (data) {
            var text = '';
            //TODO
            for (var i = 0; data[i]; i++) {
                text = text + data[i];
            }
            return text;
        };

        $scope.getRandomAdvice = function () {
            var answer = randomAdvice.get();
            answer.$promise.then(function (data) {
                $scope.receivedAdvice = $scope.getTextData(data);
            });
        };

        $scope.getAdviceToPerson = function () {
            var name = $scope.name;
            var birthdate = $scope.birthdate;
            if (!name || name == '' || !birthdate || birthdate == '') {
                return '';
            }
            var answer = adviceToPerson.get({name: name, birthdate: birthdate});
            answer.$promise.then(function (data) {
                $scope.receivedAdviceToPerson = $scope.getTextData(data);
            });
        };


    })

    .config(function ($routeProvider, $locationProvider) {
        $routeProvider
            .when('/Like', {
                templateUrl: 'like/main.html',
                controller: 'LikeController'
            })
            .when('/Fortune', {
                templateUrl: 'fortune/main.html',
                controller: 'FortuneController'
            });

    });