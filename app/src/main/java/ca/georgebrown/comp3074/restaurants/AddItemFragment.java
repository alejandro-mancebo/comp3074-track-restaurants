package ca.georgebrown.comp3074.restaurants;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddItemFragment.OnAddItemFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnAddItemFragmentInteractionListener mListener;

    public static RestaurantManager.Restaurant item;
    public ArrayList<RestaurantManager.Restaurant> items;
    public static  RestaurantManager.Restaurant restaurant;
    RestaurantDbHelper restaurantDbHelper = null;

    public AddItemFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddItemFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddItemFragment newInstance(String param1, String param2) {
        AddItemFragment fragment = new AddItemFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);

        final TextView editTextItem = view.findViewById(R.id.editTextItem);
        final TextView txtName = view.findViewById(R.id.txtName);
        final TextView txtType = view.findViewById(R.id.txtType);
        final TextView txtPrice = view.findViewById(R.id.txtPrice);

        final TextView txtAddress = view.findViewById(R.id.txtAddress);
        final TextView txtPhone =view. findViewById(R.id.txtPhone);
        final TextView txtWebsite = view.findViewById(R.id.txtWebsite);
        final TextView txtRate = view.findViewById(R.id.txtRate);
        final TextView txtOtherTags = view.findViewById(R.id.txtOthersTags);

        Button btnSave = view.findViewById(R.id.btnSave);

        restaurantDbHelper = new RestaurantDbHelper(getActivity());
        final SQLiteDatabase db = restaurantDbHelper.getReadableDatabase();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String content = editTextItem.getText().toString();

                String name = txtName.getText().toString();
                String type = txtType.getText().toString();
                String price = txtPrice.getText().toString();

                String address = txtAddress.getText().toString();
                String phone = txtPhone.getText().toString();
                String website = txtWebsite.getText().toString();
                String rate = txtRate.getText().toString();
                String otherTags = txtOtherTags.getText().toString();


                if(!isNullOrEmpty(name) && !isNullOrEmpty(type) && !isNullOrEmpty(price)) {


                    restaurant = RestaurantManager.createElement(name, type, price,
                            address, phone, website, rate, otherTags);
//                restaurant.setPrice(price);
//                restaurant.setAddress(address);
//                restaurant.setPhone(phone);
//                restaurant.setWebsite(website);
//                restaurant.setRate(rate);
//                restaurant.setOtherTags(otherTags);

                    SQLiteDatabase db = restaurantDbHelper.getReadableDatabase();

                    //long id = restaurantDbHelper.insert(db, editTextItem.getText().toString());
                    long id = restaurantDbHelper.insert(db, restaurant);

                    //item = RestaurantManager.createElement(content, name, type);

                    mListener.OnAddItemFragmentInteraction(restaurant);
                }else{
                    // manager.beginTransaction().replace(R.id.container, new ItemFragment(), null).commit();
                }
            }
        });

        return view;
    }


    private boolean isNullOrEmpty(String strValue) {

        if(strValue == null || strValue.isEmpty())
            return true;
        return false;
    }

    private boolean isNumber(String strValue) {

        if(!isNullOrEmpty(strValue)) {

            return false;
        }

        return true;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(RestaurantManager.Restaurant item) {
        if (mListener != null) {
            mListener.OnAddItemFragmentInteraction(item);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAddItemFragmentInteractionListener) {
            mListener = (OnAddItemFragmentInteractionListener) context;
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
    public interface OnAddItemFragmentInteractionListener {
        // TODO: Update argument type and name
        //void onFragmentInteraction(Uri uri);
        void OnAddItemFragmentInteraction(RestaurantManager.Restaurant item);
    }
}
