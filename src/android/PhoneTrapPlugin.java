/**
 * Copyright (c) 2014 Felipe Ramos (perenecabuto@gmail.com) and Renan Oliveira (me@renanoliveira.net)
 * Copyright (c) 2015 Tasso Evangelista
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package me.tassoevan.cordova;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

public class PhoneTrapPlugin extends CordovaPlugin {

    private CallStateListener listener;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if ("onCall".equals(action)) {
            return this.doOnCallAction(args, callbackContext);
        } else {
            this.logError("Called invalid action: " + action);
            return false;
        }
    }

    private boolean doOnCallAction(JSONArray args, CallbackContext callbackContext) {
        try {
            if (listener == null) {
                listener = new CallStateListener();
                TelephonyManager TelephonyMgr = (TelephonyManager) cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                TelephonyMgr.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);
            }

            listener.callbackContext = callbackContext;
            
            return true;
        } catch (Exception e) {
            this.logError("Exception occurred: ".concat(e.getMessage()));
            callbackContext.error("Exception occurred: ".concat(e.getMessage()));
            return false;
        }
    }

    private void logError(String description) {
        Log.e(PhoneTrapPlugin.class.getName(), description);
    }
}
