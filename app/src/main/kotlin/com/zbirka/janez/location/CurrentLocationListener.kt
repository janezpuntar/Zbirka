package com.zbirka.janez.location

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.content.Context
import android.location.Location
import com.google.android.gms.location.*

class CurrentLocationListener(context: Context) : LiveData<Location>() {

    private val fUsedLocationProviderClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    private val locationRequest: LocationRequest = LocationRequest()
    private var locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            for (location in locationResult.locations) {
                if (location != null)
                    setValue(location)
            }
        }
    }

    init {
        locationRequest.apply {
            interval = 10_000
            fastestInterval = 5_000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        getLastLocation()
    }

    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        fUsedLocationProviderClient.lastLocation.addOnCompleteListener {
            task -> if (task.isSuccessful && task.result != null)
            value = task.result else fUsedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onActive() {
        super.onActive()
        fUsedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    override fun onInactive() {
        super.onInactive()
        fUsedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

}