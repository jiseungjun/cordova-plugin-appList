var exec = require("cordova/exec");

exports.getAppList = function(arg0, success, error) {
  exec(success, error, "appList", "getAppList", [arg0]);
};
