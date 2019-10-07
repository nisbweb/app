export default {
    name: "session",

    isLoggedIn() {
        return (localStorage.getItem("guest") !== null || localStorage.getItem("email") !== null)
    },
    isGuest() {
        if (this.isLoggedIn()) {
            return (localStorage.getItem("guest") !== null)
        }
        return false
    },
    isUser() {
        if (this.isLoggedIn()) {
            return (localStorage.getItem("email") !== null)
        }
        return false
    },
    createSession(email, auth) {
        localStorage.setItem("email", email);
        localStorage.setItem("token", auth);
    },
    createGuestSession() {
        localStorage.setItem("guest", "true")
    },
    destroySession() {
        localStorage.removeItem("guest");
        localStorage.removeItem("email");
        localStorage.removeItem("token");
    },
    getToken() {
        return localStorage.getItem("token")
    },
    getEmail() {
        return localStorage.getItem("email")
    }

}