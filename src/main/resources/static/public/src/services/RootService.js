$.ajaxSetup({
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
    }
});

let RootService = (function () {

    const baseUrl = "http://localhost:8080";
    let servicesLink = {};

    console.log("Root service initialisation.");
    $.get(baseUrl, function (data) {
        servicesLink = ServicesUtil.extractLinksFromData(data);
        UsersServices = new UsersService(servicesLink[HALTags.allUsers].currentLink);
        UsersServices.createUser({
            "username": "Wesh",
            "firstname": "Loic",
            "lastname": "DAL ZOTTO",
            "city": "Nantes"
        })
    })

})();