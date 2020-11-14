package ca.georgebrown.comp3074.restaurants;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.appbar.AppBarLayout;

import static ca.georgebrown.comp3074.restaurants.MainActivity.manager;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        Button btnStart = view.findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // manager.beginTransaction().replace(R.id.container, new ItemFragment(), null).addToBackStack(null).commit();
                manager.beginTransaction().replace(R.id.container, new ItemFragment(), null).commit();
            }
        });

        return view;
    }

}
