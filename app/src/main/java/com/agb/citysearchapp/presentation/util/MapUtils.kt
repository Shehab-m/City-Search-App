package com.agb.citysearchapp.presentation.util

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log

object MapUtils {
    private const val MAPS_PACKAGE = "com.google.android.apps.maps"
    private const val MAPS_URI = "geo"
    private const val MAPS_BROWSER_URI = "https://www.google.com/maps?q="

    /**
     * Opens Google Maps with the specified latitude and longitude coordinates.
     *
     * Uses a geo URI to open the location in Google Maps and starts an activity with the intent.
     * Checks to ensure there is an application available to handle the intent
     * and handles cases where no suitable application is found.
     *
     * @param context The context from which the intent will be started.
     * @param latitude The latitude coordinate of the location to display.
     * @param longitude The longitude coordinate of the location to display.
     */
    fun openLocationInMaps(context: Context, latitude: Double, longitude: Double) {
        val uri = Uri.parse("$MAPS_URI:$latitude,$longitude")
        val intent = Intent(Intent.ACTION_VIEW, uri).apply {
            setPackage(MAPS_PACKAGE)
        }

        if (intent.resolveActivity(context.packageManager) != null) {
            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                openLocationInWebBrowser(context, latitude, longitude)
                Log.e("LocationIntent", "No application found to handle the intent", e)
            }
        } else {
            Log.e("LocationIntent", "No application found to handle the intent")
            openLocationInWebBrowser(context, latitude, longitude)
        }
    }

    /**
     * Opens a location in a web browser using Google Maps.
     *
     * @param context The context from which the activity is started.
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     */
    private fun openLocationInWebBrowser(context: Context, latitude: Double, longitude: Double) {
        try {
            val uri = Uri.parse("$MAPS_BROWSER_URI$latitude,$longitude")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: Exception) {
            Log.e("LocationIntent", "Failed to start activity for location", e)
        }
    }

}