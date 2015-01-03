module.exports = function () {
 var exports = {}
 
 exports.initialize = exports.getAccessToken = function (init) {
  var init = init || {}
  var success = (typeof init.success != "undefined") ? init.success : function () {}
  var error   = (typeof init.error   != "undefined") ? init.error   : function () {console.log ("error")}
  cordova.exec (success, error, "GooglePlayToken", "getAccessToken", [])
 }
 
 exports.setScope = function (scope) {
  if (typeof scope != "string") {console.log ("A scope parameter is required."); return}
  cordova.exec (function () {}, function () {}, "GooglePlayToken", "setScope", [scope])
 }
 return exports
} ()
