class UserServiceClass {
    constructor() {
        this.currentUser = {};
    }

    setCurrentUser(user) {
        console.log("Current user is now : " + user);
        this.currentUser = user;

        document.getElementById(DisplayerComponent.is).currentUserPresent();
    }

    getCurrentUser() {
        return this.currentUser;
    }
}

let UserService = new UserServiceClass();