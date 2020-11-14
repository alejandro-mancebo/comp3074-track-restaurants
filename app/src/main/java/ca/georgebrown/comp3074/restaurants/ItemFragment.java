package ca.georgebrown.comp3074.restaurants;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ca.georgebrown.comp3074.restaurants.RestaurantManager.Restaurant;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


import static ca.georgebrown.comp3074.restaurants.MainActivity.manager;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    private ListView listView;
    private ArrayAdapter<String> listViewAdapter;
    private RestaurantManager.Restaurant currentItem;

    RestaurantDbHelper restaurantDbHelper = null;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ItemFragment newInstance(int columnCount) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        // restaurantDbHelper = new RestaurantDbHelper(getActivity());
        // final SQLiteDatabase db = restaurantDbHelper.getReadableDatabase();
        // Cursor res = restaurantDbHelper.getAllItems(db);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        restaurantDbHelper = new RestaurantDbHelper(getActivity());
        final SQLiteDatabase db = restaurantDbHelper.getReadableDatabase();
        Cursor res = restaurantDbHelper.getAllItems(db);

        final ArrayList list = RestaurantManager.ITEMS;

        listView = (ListView) view.findViewById(R.id.listViewItems);
        listView.setAdapter(new CustomListAdapter(getActivity(), list));

        if (res.getCount() == 0) {

            listViewAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_expandable_list_item_1,
                    list
            );
        } else {

            if (list.isEmpty()) {

                RestaurantManager.Restaurant item;

                while (res.moveToNext()) {
                    String index = res.getString(res.getColumnIndexOrThrow(
                            RestaurantStatement.Queries.COLUMN_NAME_ID));
                    String name = res.getString(res.getColumnIndexOrThrow(
                            RestaurantStatement.Queries.COLUMN_NAME));
                    String type = res.getString(res.getColumnIndexOrThrow(
                            RestaurantStatement.Queries.COLUMN_TYPE));
                    String price = res.getString(res.getColumnIndexOrThrow(
                            RestaurantStatement.Queries.COLUMN_PRICE));
                    String address = res.getString(res.getColumnIndexOrThrow(
                            RestaurantStatement.Queries.COLUMN_ADDRESS));
                    String phone = res.getString(res.getColumnIndexOrThrow(
                            RestaurantStatement.Queries.COLUMN_PHONE));
                    String website = res.getString(res.getColumnIndexOrThrow(
                            RestaurantStatement.Queries.COLUMN_WEBSITE));
                    String rate = res.getString(res.getColumnIndexOrThrow(
                            RestaurantStatement.Queries.COLUMN_RATE));
                    String otherTags = res.getString(res.getColumnIndexOrThrow(
                            RestaurantStatement.Queries.COLUMN_OTHER_TAGS));


                    item = RestaurantManager.createElement(index, name, type, price,
                            address, phone, website, rate, otherTags);

                    Log.d("ItemFragment", " > listView.setOnItemClickListener itemId: " + index + " - " + name + " - " + type + " - " + price);
                }

            }

            listViewAdapter = new ArrayAdapter<String>(
                    getActivity(),
                    android.R.layout.simple_expandable_list_item_1,
                    list
            );
        }
        res.close();

        // listView.setAdapter(listViewAdapter);
        listView.setAdapter(new CustomListAdapter(getActivity(), list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String index = RestaurantManager.ITEMS.get(i).id;
                // String content = RestaurantManager.ITEMS.get(i).content;

                String name = RestaurantManager.ITEMS.get(i).name;
                String type = RestaurantManager.ITEMS.get(i).type;
                String price = RestaurantManager.ITEMS.get(i).price;
                String address = RestaurantManager.ITEMS.get(i).address;
                String phone = RestaurantManager.ITEMS.get(i).phone;
                String website = RestaurantManager.ITEMS.get(i).website;
                String rate = RestaurantManager.ITEMS.get(i).rate;
                String otherTags = RestaurantManager.ITEMS.get(i).otherTags;

                Log.d("ItemFragment", " > listView.setOnItemClickListener itemId: " + index + " - " + name);

                RestaurantManager.Restaurant restaurant = new RestaurantManager.Restaurant(index, name, type, price,
                        address, phone, website, rate, otherTags);

                mListener.onListFragmentInteraction(restaurant);
                manager.beginTransaction().replace(R.id.container, new DetailsItemFragment(), null).addToBackStack(null).commit();
                // manager.beginTransaction().replace(R.id.container, new DetailsItemFragment(), null).commit();

            }
        });


        FloatingActionButton fabAddItem = view.findViewById(R.id.fabAddItem);
        fabAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                manager.beginTransaction().replace(R.id.container, new AddItemFragment(), "AddItem").addToBackStack("AddItemFragment").commit();
                // manager.beginTransaction().replace(R.id.container, new AddItemFragment(), null).commit();
            }
        });

        return view;
    }

    public RestaurantManager.Restaurant getItem() {
        return currentItem;
    }

    public void updateView(Restaurant item) {

        Log.d("ItemFragment", " > updateView Restaurant item: " + item);

    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ItemFragment", " > onResume");

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Restaurant item);

    }
}
