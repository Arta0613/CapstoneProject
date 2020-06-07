package com.example.capstoneproject.utils;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MobileAdsConfiguration {
    public void init() {
        final List<String> testDevices = new ArrayList<>();
        testDevices.add(AdRequest.DEVICE_ID_EMULATOR);
        MobileAds.setRequestConfiguration(
                new RequestConfiguration.Builder().setTestDeviceIds(testDevices).build()
        );
    }
}