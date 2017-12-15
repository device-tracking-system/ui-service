app.config(function ($routeProvider, $locationProvider) {

    $locationProvider.html5Mode(true).hashPrefix('');

    $routeProvider
        .when('/app', {
            templateUrl: 'templates/login.html',
            controller : 'loginController'})
        .when('/app/components/map', {
            templateUrl: 'templates/map.html',
            controller : 'mapController'})
        .when('/app/components/user-form', {
            templateUrl: 'templates/userForm.html',
            controller : 'userFormController'})
        .otherwise({
            redirectTo: '/app'
        });

});
