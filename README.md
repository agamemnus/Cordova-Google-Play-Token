googleplaytoken
==================

Get an access token from Google using the Google Play Services and Android APIs, and then use that access token in a Javascript program.

NOTICE: At present, the activity .java file must be modified with the addition of a ``onActivityResult`` function inside your ``CordovaActivity`` class. A sample is provided in ``/sample_activity_file.java``.

Install & Setup
----------------------
1) Add the plugin to your build.
````
cordova plugin add https://github.com/agamemnus/googleplaytoken
````

2) Modify your CordovaActivity file to add a pass-through to ``GooglePlayToken.runOnActivityResult``. (e.g.: ``/platforms/android/src/com/myapp/``):
````
package com.myapp;

import android.os.Bundle;
import org.apache.cordova.*;

import android.content.Intent;

import com.flyingsoftgames.googleplaytoken.GooglePlayToken;

public class MyApp extends CordovaActivity {
 
 @Override protected void onActivityResult (int requestCode, int resultCode, Intent data) {
  GooglePlayToken.runOnActivityResult (requestCode, resultCode, data);
 }
 
 @Override public void onCreate (Bundle savedInstanceState) {
  super.onCreate (savedInstanceState);
  super.init ();
  super.loadUrl(Config.getStartUrl());
 }
}
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

