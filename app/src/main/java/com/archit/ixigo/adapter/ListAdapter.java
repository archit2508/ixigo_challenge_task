package com.archit.ixigo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.archit.ixigo.R;
import com.archit.ixigo.dto.Fare;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *ListAdapter to bind data with views generated by List View to display all fare options from different providers for a flight
 */
public class ListAdapter extends ArrayAdapter {

    private List<Fare> fareList = new ArrayList<>();
    private Context context;
    private HashMap<String,String> airlineNames;
    private HashMap<String,String> airportNames;
    private HashMap<String,String> providerNames;

    /**
     * Creating List Adapter
     * @param context Used to talk to the UI and app resources
     * @param fareList List of all fares available for a flight with respective provider Ids
     * @param airlines Map containing airline names mapped to respective airline codes
     * @param airports Map containing airport names mapped to respective city codes
     * @param providerNames Map containing provider names mapped to respective provider ids
     */
    public ListAdapter(Activity context, List<Fare> fareList, HashMap<String, String> airlines, HashMap<String, String> airports, HashMap<String, String> providerNames){
        super(context, R.layout.provider_details_item);
        this.fareList = fareList;
        this.context = context;
        this.airlineNames = airlines;
        this.airportNames = airports;
        this.providerNames = providerNames;
    }

    /**
     * This method returns number of items to be displayed. It is used behind the scenes to layout our views
     * @return Number of items available in our faresList
     */
    public int getCount(){
        return fareList.size();
    }

    /**
     * This method creates views for each item to be displayed in our List view
     * @param position position of the current item in the list
     * @param view used to recycle the views to create new view by reusing the current view
     * @param parent parent is a ViewGroup to which the view created by getView() is finally attached
     * @return the new created view
     */
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rowView=inflater.inflate(R.layout.provider_details_item, null,true);

        TextView mProvider = (TextView) rowView.findViewById(R.id.provider_name);
        TextView mFare = (TextView) rowView.findViewById(R.id.provider_fare);

        mProvider.setText(providerNames.get(fareList.get(position).getProviderId().toString()));
        mFare.setText("₹"+fareList.get(position).getFare().toString());

        return rowView;

    };
}
