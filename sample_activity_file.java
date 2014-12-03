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