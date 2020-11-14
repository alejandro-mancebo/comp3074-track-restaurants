package ca.georgebrown.comp3074.restaurants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DetailsItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DetailsItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailsItemFragment extends Fragment
        implements ItemFragment.OnListFragmentInteractionListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static RestaurantManager.Restaurant item;

    RestaurantDbHelper restaurantDbHelper = null;

    public DetailsItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailsItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsItemFragment newInstance(String param1, String param2) {
        DetailsItemFragment fragment = new DetailsItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_details_item, container, false);

        // final TextView textViewItem = view.findViewById(R.id.textViewItem);

        final TextView txtName = view.findViewById(R.id.txtName);
        final TextView txtType = view.findViewById(R.id.txtType);
        final TextView txtPrice = view.findViewById(R.id.txtPrice);

        final TextView txtAddress = view.findViewById(R.id.txtAddress);
        final TextView txtPhone =view. findViewById(R.id.txtPhone);
        final TextView txtWebsite = view.findViewById(R.id.txtWebsite);
        final TextView txtRate = view.findViewById(R.id.txtRate);
        final TextView txtOtherTags = view.findViewById(R.id.txtOthersTags);

        Button btnDelete = view.findViewById(R.id.btnDelete);

        restaurantDbHelper = new RestaurantDbHelper(getActivity());
        final SQLiteDatabase db = restaurantDbHelper.getReadableDatabase();

        // textViewItem.setText(item.content);
        txtName.setText(item.getName());
        txtType.setText(item.getType());
        double p = Double.parseDouble(item.getPrice());
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        txtPrice.setText(fmt.format(p));
        txtAddress.setText(item.getAddress());

        String phone = item.getPhone();
        String ph = "(" + item.getPhone().substring(0, 3) + ") " + phone.substring(3, 6) + " - " + phone.substring(6);
        txtPhone.setText(ph);
        txtWebsite.setText(item.getWebsite());
        txtRate.setText("Rate: " + item.getRate());
        txtOtherTags.setText("Tags: " + item.getOtherTags());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("DetailFragment", " > btnDelete.setOnClickListener itemId " + item.id);

                SQLiteDatabase db = restaurantDbHelper.getReadableDatabase();
                restaurantDbHelper.delete(db, item.id);

                RestaurantManager.removeElement(item);
                mListener.onFragmentInteraction(item);
            }
        });


        txtAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), MapsActivity.class);

                String address = txtAddress.getText().toString();

                double [] coordinates = getLocationFromAddress(address);

                i.putExtra("coordinates", coordinates);
                startActivityForResult(i, 1);
            }
        });

        return view;
    }

    public double [] getLocationFromAddress(String strAddress) {

        Geocoder coder = new Geocoder(this.getContext());
        List<Address> address;

        try {
            address = coder.getFromLocationName(strAddress, 1);
            if (address == null) {
                return null;
            }
            Address location = address.get(0);
            double [] coordinates = new double[2];
            coordinates[0] = location.getLatitude();
            coordinates[1] = location.getLongitude();

            return coordinates;
        } catch (Exception e) {
            return null;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(RestaurantManager.Restaurant item) {
        if (mListener != null) {
            mListener.onFragmentInteraction(item);
        }
    }

    public void updateView(RestaurantManager.Restaurant item) {

        this.item = item;
        Log.d("DetailFragment", " > updateView onFragmentInteraction item: " + item);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onListFragmentInteraction(RestaurantManager.Restaurant item) {
        Log.d("Detail", " > onListFragmentInteraction" + item.name);
        this.item = item;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(RestaurantManager.Restaurant item);
    }


}
