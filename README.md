googleplaytoken
==================

Get an access token from Google using the Google Play Services and Android APIs, and then use that access token in a Javascript program.

This is useful for accessing a user's Google or Google Play user data in Cordova apps or games.

Install & Setup
----------------------
1) Add the plugin to your build.
````
cordova plugin add https://github.com/agamemnus/googleplaytoken
````

2) Run the initialize function.
````
window.plugins.GooglePlayToken.initialize ({
 success : function (access_token) {console.log (access_token)},
 error   : function (errormessage) {console.log (errormessage)}
})
````

Usage / Function List
----------------------

initialize: Initialize, potentially ask the user to authorize and log in, and get and store an access token.
````
window.plugins.GooglePlayToken.initialize ({
 success : function (result) {},
 error   : function (result) {}
})
````
<br/>
getAccessToken: Retrieve the stored access token generated in the initialization.
````
window.plugins.GooglePlayToken.getAccessToken ({
 success : function (result) {},
 error   : function (result) {}
})
````

