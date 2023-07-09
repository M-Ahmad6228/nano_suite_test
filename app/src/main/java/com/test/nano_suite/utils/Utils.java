package com.test.nano_suite.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

import com.test.nano_suite.R;

public final class Utils {

    private static ProgressDialog pd;

    public static Boolean checkInternet(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        Network activeNetwork = connectivityManager.getActiveNetwork();
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
    }

    public static void showLoader(Context context) {
        if (pd == null || !pd.isShowing()) {
            pd = new ProgressDialog(context);
            pd.show();
            pd.setContentView(R.layout.progress_dialog);
            pd.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            pd.setCancelable(false);
            pd.setCanceledOnTouchOutside(false);
        }
    }

    public static void hideLoader() {
        if (pd.isShowing()) pd.dismiss();
    }
}
