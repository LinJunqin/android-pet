package com.lin.person.debug;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.lin.person.R;
import com.lin.person.fragment.PersonFragment;


public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        Fragment personFragment = new PersonFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,personFragment)
                .show(personFragment).commit();
    }
}
