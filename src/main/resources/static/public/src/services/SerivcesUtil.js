let ServicesUtil = (function () {

    function extractLinksFromData(data) {
        let extractedLinks = {};
        let links = data["_links"];
        Object.keys(links).forEach(function (key) {
            let currentLink = links[key]["href"].split("{?")[0];
            //extractedLinks[key] = currentLink.split("{?")[0];
            let queryParams = [];
            let queryParamAsString = links[key]["href"].replace("}", "")
                .split("{?")[1];
            if (queryParamAsString) {
                queryParamAsString.split(",")
                    .forEach(function (paravalue) {
                        queryParams.push(paravalue)
                    })
            }

            extractedLinks[key] = {currentLink, queryParams}

        });


        return extractedLinks;
    }

    function getSearchLinks(baseurl) {
        return new Promise((resolve, reject) => {
            $.get(baseurl + '/search', function (data) {
                let links = extractLinksFromData(data);
                resolve(links)
            })
        })

    }


    return {
        extractLinksFromData: extractLinksFromData,
        getSearchLinks: getSearchLinks
    }
})();