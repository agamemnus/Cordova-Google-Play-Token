module.exports = function () {
 var exports = {}
 
 exports.initialize = function (init) {
  var success = (typeof init.success != "undefined") ? init.success : function () {}
  var error   = (typeof init.error   != "undefined") ? init.error   : function () {}
  cordova.exec (success, error, "GooglePlayToken", "tryConnect", [])
 }
 
 exports.getAccessToken = function (init) {
  var success = (typeof init.success != "undefined") ? init.success : function () {}
  var error   = (typeof init.error   != "undefined") ? init.error   : function () {}
  cordova.exec (success, error, "GooglePlayToken", "getAccessToken", [])
 }
 
 return exports
} ()
