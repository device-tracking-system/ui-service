app.config(function ($routeProvider, $locationProvider) {

    $locationProvider.html5Mode(true).hashPrefix('');

    $routeProvider
        .when('/app', {
            templateUrl : 'index.html',
            controller  : 'indexController'})
        .when('/app/components/login', {
            templateUrl : 'templates/login.html',
            controller  : 'loginController'})
        .when('/app/components/user-info', {
            templateUrl: 'templates/userInfo.html'})
        .when('/app/components/unauthenticated', {
            templateUrl: 'templates/unauthenticated.html'})
        .when('/app/components/map', {
            templateUrl: 'templates/map.html'})
        .when('/app/components/user-form', {
            templateUrl: 'templates/userForm.html'})
        .otherwise({
            redirectTo: '/app'
        });

});
