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

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

public class CallStateListener extends PhoneStateListener {

    private static final String PHONE_STATE_IDLE = "idle";
    private static final String PHONE_STATE_OFFHOOK = "offhook";
    private static final String PHONE_STATE_RINGING = "ringing";
    private static final String PHONE_STATE_UNKNOWN = "unknown";

    public CallbackContext callbackContext;

    public void onCallStateChanged(int state, String incomingNumber) {
        super.onCallStateChanged(state, incomingNumber);

        if (this.callbackContext == null) {
            return;
        }

        String msg;

        switch (state) {
            case TelephonyManager.CALL_STATE_IDLE:
                msg = PHONE_STATE_IDLE;
                break;

            case TelephonyManager.CALL_STATE_OFFHOOK:
                msg = PHONE_STATE_OFFHOOK;
                break;

            case TelephonyManager.CALL_STATE_RINGING:
                msg = PHONE_STATE_RINGING;
                break;

            default:
                msg = PHONE_STATE_UNKNOWN;
        }

        PluginResult result = new PluginResult(PluginResult.Status.OK, msg);
        result.setKeepCallback(true);

        callbackContext.sendPluginResult(result);
    }
}
