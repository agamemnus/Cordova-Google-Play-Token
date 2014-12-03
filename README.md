googleplaytoken
==================

Get an access token from Google Play using the Android API with Javascript.

NOTICE: At present, the activity .java file must be modified with the addition of a ``onActivityResult`` function inside your ``CordovaActivity`` class. A sample is provided in ``/sample_activity_file.java``.

Install
----------------------
````
cordova plugin add https://github.com/agamemnus/googleplaytoken --variable APP_ID=YOUR_APP_ID
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

