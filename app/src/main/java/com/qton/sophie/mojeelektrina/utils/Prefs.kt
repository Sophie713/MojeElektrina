package com.qton.sophie.mojeelektrina.utils

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    val PREFS_FILENAME = "preferences"
    val MONTHLY_PAYMENT = "monthly"
    val SHARE_DATA = "shareData"
    val NOTIFICATIONS = "notifications"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var monthlyPayment: Int
        get() = prefs.getInt(MONTHLY_PAYMENT, 900)
        set(value) = prefs.edit().putInt(MONTHLY_PAYMENT, value).apply()
    var shareData: Boolean
        get() = prefs.getBoolean(SHARE_DATA, true)
        set(value) = prefs.edit().putBoolean(SHARE_DATA, value).apply()
    var notifications: Boolean
        get() = prefs.getBoolean(NOTIFICATIONS, true)
        set(value) = prefs.edit().putBoolean(NOTIFICATIONS, value).apply()
}