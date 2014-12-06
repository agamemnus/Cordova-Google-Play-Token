package com.flyingsoftgames.googleplaytoken;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;

import com.google.android.gms.common.AccountPicker;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.Intent;

import android.app.Activity;
import android.accounts.AccountManager;

import android.os.AsyncTask;
import android.os.Bundle;

import java.io.IOException;

import android.util.Log;

public class GooglePlayToken extends CordovaPlugin {
 
 private final String LOG_TAG = "GooglePlayToken";
 private final int REQ_SIGN_IN_REQUIRED = 55664;
 private final int REQUEST_CODE_PICK_ACCOUNT = 1000;
 private CallbackContext tryConnectCallback = null;
 private String lastAccessToken = null;
 private String scope = "oauth2:" + Scopes.PROFILE;
 
 private void pickUserAccount (CallbackContext callbackContext) {
  tryConnectCallback = callbackContext;
  String[] accountTypes = new String[]{"com.google"};
  Intent intent = AccountPicker.newChooseAccountIntent(null, null, accountTypes, false, null, null, null, null);
  cordova.setActivityResultCallback (this);
  cordova.getActivity().startActivityForResult (intent, REQUEST_CODE_PICK_ACCOUNT);
 }
 
 public void onActivityResult (int requestCode, int resultCode, Intent data) {
  if ((requestCode == REQUEST_CODE_PICK_ACCOUNT) && (resultCode == Activity.RESULT_OK)) {
   new RetrieveTokenTask().execute (data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME));
  }
 }
 
 public boolean execute (String action, JSONArray inputs, CallbackContext callbackContext) throws JSONException {
  if ("setScope".equals(action)) {;
   scope = "oauth2:" + inputs.getString(0);
  } else if ("getAccessToken".equals(action)) {
   pickUserAccount (callbackContext);
  }
  return true;
 }
 
 private class RetrieveTokenTask extends AsyncTask<String, Void, String> {
  @Override protected String doInBackground (String... params) {
   String accountName = params[0];
   Context context = cordova.getActivity().getApplicationContext();
   String accessToken = null;
   try {
    if (lastAccessToken != null) {GoogleAuthUtil.clearToken (context, lastAccessToken); lastAccessToken = null;}
    accessToken = GoogleAuthUtil.getToken(context, accountName, scope);
   } catch (IOException e) {
    String errormessage = e.getMessage();
    if (tryConnectCallback != null) {tryConnectCallback.error ("Error: " + errormessage + "."); tryConnectCallback = null;}
   } catch (UserRecoverableAuthException e) {
    cordova.getActivity().startActivityForResult (e.getIntent(), REQ_SIGN_IN_REQUIRED);
   } catch (GoogleAuthException e) {
    String errormessage = e.getMessage();
    if (tryConnectCallback != null) {tryConnectCallback.error ("Error: " + errormessage + "."); tryConnectCallback = null;}
   }
   return accessToken;
  }
  
  @Override protected void onPostExecute (String accessToken) {
   if (accessToken == null) return;
   lastAccessToken = accessToken;
   super.onPostExecute (accessToken);
   if (tryConnectCallback == null) return;
   tryConnectCallback.sendPluginResult (new PluginResult (PluginResult.Status.OK, accessToken));
   tryConnectCallback = null;
  }
 }
}
