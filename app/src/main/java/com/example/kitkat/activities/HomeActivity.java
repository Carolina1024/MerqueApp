package com.example.kitkat.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.kitkat.R;
import com.example.kitkat.fragment.ChatsFragment;
import com.example.kitkat.fragment.FiltersFragment;
import com.example.kitkat.fragment.HomeFragment;
import com.example.kitkat.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigation=findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        openFragment(new HomeFragment());
    }

    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    if(item.getItemId()==R.id.itemHome){      // aqui se abre un fragmente home
                        openFragment(new HomeFragment());
                    }else if(item.getItemId()==R.id.itemFilter){   // aqui se abre un fragmente filtros
                        openFragment(new FiltersFragment());
                    }else if (item.getItemId()==R.id.itemChats){      // aqui se abre un fragmente chats
                        openFragment(new ChatsFragment());
                    }else if (item.getItemId()==R.id.itemPerfil){      // aqui se abre un fragmente perfil
                        openFragment(new ProfileFragment());
                    }
                    return true;
                }
            };

}