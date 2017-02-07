package com.ComeOnBaby.service;


import com.ComeOnBaby.model.Preferences;

import java.util.List;

public interface PreferencesService {
    void addNewPreferences(Preferences preferences);
    void updatePreferences(Preferences preferences);
    void deletePreferences(Preferences preferences);
    List<Preferences> getAllPreferences();
}