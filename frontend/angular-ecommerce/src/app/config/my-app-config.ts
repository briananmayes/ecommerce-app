export default {

    oidc: {
        clientId: '0oa1o0qr4sjtjLmyV5d7', //public identifier of app
        issuer: 'https://dev-59914511.okta.com/oauth2/default', //issuer of tokens + url when authorizing with okta authorization server
        redirectUri: 'https://angular-ecommerce-clone.herokuapp.com/login/callback',
        scopes: ['openid', 'profile', 'email'] // provide access to information about user
    }

}
