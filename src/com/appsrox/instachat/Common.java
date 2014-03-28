package com.appsrox.instachat;

import java.util.ArrayList;
import java.util.List;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Patterns;

public class Common extends Application implements Constatns {   
	 
    public static String[] email_arr;
    private static SharedPreferences prefs;
 
    @Override
    public void onCreate() {
        super.onCreate();
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
         
        List<String> emailList = getEmailList();
        email_arr = emailList.toArray(new String[emailList.size()]);
    }
     
    private List<String> getEmailList() {
        List<String> lst = new ArrayList<String>();
        Account[] accounts = AccountManager.get(this).getAccounts();
        for (Account account : accounts) {
            if (Patterns.EMAIL_ADDRESS.matcher(account.name).matches()) {
                lst.add(account.name);
            }
        }
        return lst;
    }   
    
    public static String getPreferredEmail() {
        return prefs.getString("chat_email_id", email_arr.length==0 ? "" : email_arr[0]);
    }
     
    public static String getDisplayName() {
        String email = getPreferredEmail();
        return prefs.getString("display_name", email.substring(0, email.indexOf('@')));
    }
     
    public static boolean isNotify() {
        return prefs.getBoolean("notifications_new_message", true);
    }
     
    public static String getRingtone() {
        return prefs.getString("notifications_new_message_ringtone", android.provider.Settings.System.DEFAULT_NOTIFICATION_URI.toString());
    }
     
    public static String getServerUrl() {
        return prefs.getString("server_url_pref", SERVER_URL);
    }
     
    public static String getSenderId() {
        return prefs.getString("sender_id_pref", SENDER_ID);
    }  
}
     