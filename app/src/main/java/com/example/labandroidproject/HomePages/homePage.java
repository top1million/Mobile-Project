package com.example.labandroidproject.HomePages;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.labandroidproject.admin.admin_home_page;
import com.example.labandroidproject.Fragments.FourthFragment;
import com.example.labandroidproject.Fragments.ThirdFragment;
import com.example.labandroidproject.R;
import com.example.labandroidproject.databinding.ActivityHomePageBinding;

public class homePage extends AppCompatActivity{
    ActivityHomePageBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new admin_home_page());
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id=item.getItemId();
            if (id == R.id.navigation_home){
                replaceFragment(new admin_home_page());
            }
            else if (id == R.id.profile){
                replaceFragment(new ThirdFragment());
            }
            else if (id == R.id.settings){
                replaceFragment(new FourthFragment());
            }
            return true;
        });


    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

}
