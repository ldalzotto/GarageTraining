let UsersServices;

class UsersService {

    constructor(url) {
        this.baseurl = url;
        this.users = [];
        ServicesUtil.getSearchLinks(url)
            .then(data => this.search = data)
    }

    createUser(user) {
        let UsersServiceClass = this;

        return new Promise((resolve, reject) => {
            $.post(this.baseurl, JSON.stringify(user), function (data) {
                    let currentlinks = ServicesUtil.extractLinksFromData(data);
                    UsersServiceClass.users = [];
                    UsersServiceClass.users.push(new UserService(currentlinks[HALTags.singleUser]).currentLink);
                    resolve(data);
                }
            ).fail(function (e) {
                reject(e);
            })
        })

    }

    getUsers() {
        return new Promise((resolve, reject) => {
            let GetUserPromises = [];

            this.users.forEach(userService => {
                GetUserPromises.push(userService.getUser())
            });

            Promise.all(GetUserPromises)
                .then(data => resolve(data))
        })

    }

}