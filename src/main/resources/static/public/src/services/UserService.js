class UserService {
    constructor(url) {
        this.baseurl = url;
    }

    getUser() {
        return new Promise((resolve, reject) => {
            $.get(this.baseurl, function (data) {
                resolve(data);
            })
        })
    }
}