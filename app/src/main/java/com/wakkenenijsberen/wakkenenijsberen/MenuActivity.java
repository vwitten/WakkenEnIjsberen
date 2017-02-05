package com.wakkenenijsberen.wakkenenijsberen;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

public class MenuActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        android.app.FragmentTransaction ft;
        ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.activity_menu, new StartFragment());
        ft.addToBackStack(null);
        ft.commit();
    }
}
