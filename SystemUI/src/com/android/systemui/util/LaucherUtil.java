package com.android.systemui.util;

import java.util.ArrayList;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class LaucherUtil {
	
	
    /** 
     * Return PackageManager. 
     *  
     * @param context A Context of the application package implementing this class. 
     * @return a PackageManager instance. 
     */ 
    public static ActivityManager getActivityManager(Context context){ 
        return (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE); 
     
    } 
    
    public static List<String> getLaunchers(Context context){ 
        List<String> packageNames = new ArrayList<String>(); 
        PackageManager packageManager = context.getPackageManager(); 
        Intent intent = new Intent(Intent.ACTION_MAIN); 
        intent.addCategory(Intent.CATEGORY_HOME); 
     
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, packageManager.MATCH_DEFAULT_ONLY); 
     
        for(ResolveInfo resolveInfo:resolveInfos){ 
            ActivityInfo activityInfo = resolveInfo.activityInfo; 
            if(activityInfo != null) { 
                packageNames.add(resolveInfo.activityInfo.processName); 
                packageNames.add(resolveInfo.activityInfo.packageName); 
            } 
        } 
        return packageNames; 
    } 
    
    public static boolean isLauncherForeground(Context context){ 
        boolean isLauncherForeground = false; 
        ActivityManager activityManager = getActivityManager(context); 
        List<String> lanuchers = getLaunchers(context); 
        List<RunningTaskInfo> runningTaskInfos =  activityManager.getRunningTasks(1); 
     
        if(lanuchers.contains(runningTaskInfos.get(0).baseActivity.getPackageName())) { 
            isLauncherForeground = true; 
        } 
     
        return isLauncherForeground; 
    } 
    
    
    

}
