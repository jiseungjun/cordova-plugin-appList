package kr.co.vlay.appListPlugin;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

import android.app.Activity;

public class appList extends CordovaPlugin {
    private Activity activity;
    private Context context;

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        activity = cordova.getActivity();
        context = activity.getApplicationContext();
    }

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getAppList")) {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            List<ResolveInfo> appList = packageManager.queryIntentActivities(intent, 0);

            List<JSONObject> arr = new ArrayList<JSONObject>();
            for (ResolveInfo al : appList) {
                ActivityInfo ai = al.activityInfo;                
                JSONObject item = new JSONObject();
                item.put("appId", ai.packageName.toString());
                item.put("appName", ai.loadLabel(packageManager).toString());
                arr.add(item);
            }

            JSONObject json = new JSONObject();
            json.put("datas", arr.toString());
            callbackContext.success(json);
            return true;
        }
        return false;
    }
}
