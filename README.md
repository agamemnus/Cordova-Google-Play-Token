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

2) Run the getAccessToken function.

Usage / Function List
----------------------

getAccessToken (alias: initialize): Initialize, potentially ask the user to authorize and log in, and get an access token.
Note that the access token does expire every 60 minutes. A new token will be generated if getAccessToken is called more than once.
````
window.plugins.GooglePlayToken.getAccessToken ({
 success : function (access_token) {console.log (access_token)},
 error   : function (errormessage) {console.log (errormessage)}
})
````
<br/>
setScope: Set the scope for the access token. The default scope is ``Scopes.PROFILE`` which is a constant value in ``com.google.android.gms.common.Scopes`` for "profile".
````
window.plugins.GooglePlayToken.setScope (scope_string)
````

