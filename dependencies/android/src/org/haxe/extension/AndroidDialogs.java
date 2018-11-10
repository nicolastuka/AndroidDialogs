package org.haxe.extension;

import org.haxe.lime.HaxeObject;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;


/* 
	You can use the Android Extension class in order to hook
	into the Android activity lifecycle. This is not required
	for standard Java code, this is designed for when you need
	deeper integration.
	
	You can access additional references from the Extension class,
	depending on your needs:
	
	- Extension.assetManager (android.content.res.AssetManager)
	- Extension.callbackHandler (android.os.Handler)
	- Extension.mainActivity (android.app.Activity)
	- Extension.mainContext (android.content.Context)
	- Extension.mainView (android.view.View)
	
	You can also make references to static or instance methods
	and properties on Java classes. These classes can be included 
	as single files using <java path="to/File.java" /> within your
	project, or use the full Android Library Project format (such
	as this example) in order to include your own AndroidManifest
	data, additional dependencies, etc.
	
	These are also optional, though this example shows a static
	function for performing a single task, like returning a value
	back to Haxe from Java.
*/
public class AndroidDialogs extends Extension {
	
	//for toast
	private static String messageToast;
	private static int duration;
	//for alert dialog
	private static String messageAlert;
	private static String title;
	private static String confirmtext;
	private static String canceltext;
	private static String resultDialog;
	//for callbacks result
	private static HaxeObject objHaxe = null;
	
	public static void ShowToast (String msg, int d) {
		messageAlert = msg;
		if (d == 1) {
			duration = Toast.LENGTH_SHORT;
		}else if (d == 2) {
			duration = Toast.LENGTH_LONG;
		}else{
			duration = d;
		}
		Extension.mainActivity.runOnUiThread(new Runnable() {
        	@Override
            public void run() {
            	Toast.makeText(mainContext, messageAlert, duration).show();
			}
        });
	}

	public static void ShowAlertDialog (String t, String msg, String conftext, String cltext, HaxeObject hxo) {
		title = t;
		messageToast = msg;
		confirmtext = conftext;
		canceltext = cltext;
		objHaxe = hxo;
		Extension.callbackHandler.post(new Runnable() {
        	@Override
            public void run() {
            	AlertDialog.Builder dialog = new AlertDialog.Builder(Extension.mainContext);  
		        dialog.setTitle(title);  
		        dialog.setMessage(messageToast);            
		        dialog.setCancelable(false);  
		        dialog.setPositiveButton(confirmtext, new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int id) {  
		                resultDialog = confirmtext;
		                objHaxe.call("onMessageReceived",new Object[]{resultDialog});
		            }  
		        });  
		        dialog.setNegativeButton(canceltext, new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int id) {  
		                resultDialog = canceltext;
		                objHaxe.call("onMessageReceived",new Object[]{resultDialog});
		            }  
		        });            
		        dialog.show();
            }
        });
	}

	public static void ShowDialogSelectSimpleRadio (String t, String[] op, HaxeObject hxo) {
		final String[] items = new String[op.length];
		for (int i = 0; i < op.length; i++) {
			items[i] = op[i];
		}
		title = t;
		objHaxe = hxo;
		Extension.callbackHandler.post(new Runnable() {
        	@Override
            public void run() {
            	AlertDialog.Builder dialog = new AlertDialog.Builder(Extension.mainContext);  
		        dialog.setTitle(title);  
		        dialog.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int item) {
		                objHaxe.call("onOptionSelected",new Object[]{items[item]});
		            }  
		        });  
		        dialog.show();
            }
        });
	}

	public static void ShowDialogSelectMultiple (String t, String[] op, HaxeObject hxo) {
		final String[] items = new String[op.length];
		final boolean[] selecteds = new boolean[op.length];
		for (int i = 0; i < op.length; i++) {
			selecteds[i] = false;
		}
		for (int i = 0; i < op.length; i++) {
			items[i] = op[i];
		}
		title = t;
		objHaxe = hxo;
		Extension.callbackHandler.post(new Runnable() {
        	@Override
            public void run() {
            	AlertDialog.Builder dialog = new AlertDialog.Builder(Extension.mainContext);  
		        dialog.setTitle(title);  
		        dialog.setMultiChoiceItems(items, null, new DialogInterface.OnMultiChoiceClickListener() {
		            public void onClick(DialogInterface dialog, int item, boolean isChecked) {
		            	selecteds[item] = isChecked;
		                objHaxe.call("onOptionMultipleSelected", new Object[]{selecteds});
		            }  
		        });  
		        dialog.show();
            }
        });
	}
	
	
	/**
	 * Called when an activity you launched exits, giving you the requestCode 
	 * you started it with, the resultCode it returned, and any additional data 
	 * from it.
	 */
	public boolean onActivityResult (int requestCode, int resultCode, Intent data) {
		
		return true;
		
	}
	
	
	/**
	 * Called when the activity is starting.
	 */
	public void onCreate (Bundle savedInstanceState) {
		
		
		
	}
	
	
	/**
	 * Perform any final cleanup before an activity is destroyed.
	 */
	public void onDestroy () {
		
		
		
	}
	
	
	/**
	 * Called as part of the activity lifecycle when an activity is going into
	 * the background, but has not (yet) been killed.
	 */
	public void onPause () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onStop} when the current activity is being 
	 * re-displayed to the user (the user has navigated back to it).
	 */
	public void onRestart () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onRestart}, or {@link #onPause}, for your activity 
	 * to start interacting with the user.
	 */
	public void onResume () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when  
	 * the activity had been stopped, but is now again being displayed to the 
	 * user.
	 */
	public void onStart () {
		
		
		
	}
	
	
	/**
	 * Called when the activity is no longer visible to the user, because 
	 * another activity has been resumed and is covering this one. 
	 */
	public void onStop () {
		
		
		
	}
	
	
}