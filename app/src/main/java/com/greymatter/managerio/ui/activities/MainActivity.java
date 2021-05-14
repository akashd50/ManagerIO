package com.greymatter.managerio.ui.activities;

import android.Manifest;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.greymatter.managerio.AppServices;
import com.greymatter.managerio.R;
import com.greymatter.managerio.db.ContactsImporter;
import com.greymatter.managerio.db.DBServices;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_contacts, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        checkPermissions();
    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                                                            PackageManager.PERMISSION_GRANTED) {
            this.getLoaderManager().initLoader(0, null, callbacks);
        } else {
            this.requestPermissions(new String[] { Manifest.permission.READ_CONTACTS }, 10);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @NotNull String[] permissions,
                                           @NonNull @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 10) {
            this.getLoaderManager().initLoader(0, null, callbacks);
        }
    }

    LoaderManager.LoaderCallbacks<Cursor> callbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @NonNull
        @NotNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable @org.jetbrains.annotations.Nullable Bundle args) {
            return ContactsImporter.generateQuery(MainActivity.this);
        }

        @Override
        public void onLoadFinished(@NonNull @NotNull Loader<Cursor> loader, Cursor data) {
            if(!AppServices.getActiveUser().isContactsImported()) {
                ContactsImporter.importContacts(data);
                AppServices.getActiveUser().setContactsImported(true);
                AppServices.updateActiveUser();
            }
        }

        @Override
        public void onLoaderReset(@NonNull @NotNull Loader<Cursor> loader) {}
    };
}