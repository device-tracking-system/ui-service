app.config(function ($routeProvider, $locationProvider) {

    $locationProvider.html5Mode(true).hashPrefix('');

    $routeProvider
        .when('/app', {
            templateUrl: 'index.html',
            controller : 'loginController'})
        .otherwise({
            redirectTo: '/app'
        });

});
