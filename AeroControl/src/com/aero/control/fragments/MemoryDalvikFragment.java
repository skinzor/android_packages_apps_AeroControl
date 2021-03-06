package com.aero.control.fragments;

import android.os.Bundle;
import android.preference.PreferenceCategory;
import android.preference.PreferenceScreen;
import android.util.Log;

import com.aero.control.AeroActivity;
import com.aero.control.R;
import com.aero.control.helpers.FilePath;
import com.aero.control.helpers.PreferenceHandler;

/**
 * Created by Alexander Christ on 05.03.14.
 */
public class MemoryDalvikFragment extends PlaceHolderFragment {

    private PreferenceScreen root;
    private PreferenceCategory PrefCat;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.layout.empty_preference);
        root = this.getPreferenceScreen();
        setTitle(getActivity().getText(R.string.pref_dalvik_setttings).toString());

        // Load our custom preferences;
        loadDalvik();
    }

    public void loadDalvik() {

        String completeParamterList[] = AeroActivity.shell.getDirInfo(FilePath.DALVIK_TWEAK, true);

        // If there are already some entries, kill them all (with fire)
        if (PrefCat != null)
            root.removePreference(PrefCat);

        PrefCat = new PreferenceCategory(getActivity());
        PrefCat.setTitle(R.string.pref_dalvik_setttings_heading);
        root.addPreference(PrefCat);

        try {

            PreferenceHandler h = new PreferenceHandler(getActivity(), PrefCat, getPreferenceManager());

            h.genPrefFromDictionary(completeParamterList, FilePath.DALVIK_TWEAK);

        } catch (NullPointerException e) {
            Log.e("Aero", "I couldn't get any files!", e);
        }

    }
}