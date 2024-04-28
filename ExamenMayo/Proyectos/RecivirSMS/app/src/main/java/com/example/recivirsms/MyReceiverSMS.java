package com.example.recivirsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiverSMS extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] smsMessages = new SmsMessage[pdus.length];

            for (int i = 0; i < smsMessages.length; i++) {
                smsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String smsSender = smsMessages[i].getOriginatingAddress();
                String smsBody = smsMessages[i].getMessageBody();

                // Aquí puedes realizar cualquier acción que desees con el mensaje SMS
                // Por ejemplo, mostrarlo en un TextView o en un Toast
                Toast.makeText(context, "SMS Received from " + smsSender + ": " + smsBody, Toast.LENGTH_LONG).show();

            }

        }
    }

}