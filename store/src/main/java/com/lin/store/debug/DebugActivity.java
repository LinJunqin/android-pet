package com.lin.store.debug;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;


import com.lin.store.R;
import com.lin.store.fragment.StoreFragment;


public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
        Fragment storeFragment = new StoreFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container,storeFragment)
                .show(storeFragment).commit();
    }
}
