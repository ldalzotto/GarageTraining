let Services = (function () {
    const baseUrl = "http://localhost:8080";
    let servicesLink = {"/": {}};

    let initPromise =
        $.get(baseUrl, function (data) {
            let links = data["_links"];
            Object.keys(links).forEach(function (key) {
                let currentLink = links[key]["href"];
                servicesLink["/"][key] = currentLink.split("{?")[0];
            });
            console.log(servicesLink)
        });

    function discoverLinkFromUrl(url) {
        return new Promise((resolve, reject) => {
            $.get(url, function (data) {
                let links = data["_links"];
                let servicesLink = {};
                Object.keys(links).forEach(function (key) {
                    let currentLink = links[key]["href"];
                    servicesLink[key] = currentLink.split("{?")[0];
                });
                resolve(servicesLink);
            })
        });
    }

    function getRequest(tag) {
        let currentLinks = null;
        listOfUrlTag.forEach(function (tag) {
            if (currentLinks == null) {
                currentLinks = servicesLink[tag];
            } else {
                currentLinks = currentLinks[tag]
            }
        })
    }

    return {
        getRequet: getRequest
    }

})();

Services.getRequet([]);