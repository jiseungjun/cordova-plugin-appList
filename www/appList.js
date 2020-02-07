var exec = require("cordova/exec");

exports.gatAppList = function(arg0, success, error) {
  exec(success, error, "appList", "gatAppList", [arg0]);
};
