package com.archit.ixigo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.archit.ixigo.R;
import com.archit.ixigo.dto.Fare;
import com.archit.ixigo.dto.Flight;
import com.archit.ixigo.dto.FlightsDto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Recycler View adapter to bind data with views generated by recycler view on main screen to display all flights
 */
public class FlightAdapter extends RecyclerView.Adapter<FlightAdapter.FlightAdapterViewHolder> {

    Context context;
    FlightsDto flightsDto = new FlightsDto();
    List<Flight> flightArray = new ArrayList<>();
    List<Fare> fareArray;
    HashMap<String, String> airlines;
    HashMap<String, String> airports;
    HashMap<String, String> providers;

    final private FlightAdapterOnClickHandler mClickHandler;

    /**
     * The interface that receives onClick messages on list items.
     */
    public interface FlightAdapterOnClickHandler {
        void onClick(int position);
    }

    /**
     * Creating FLight Adapter
     * @param applicationContext - Used to talk to the UI and app resources
     * @param clickHandler - The on-click handler for this adapter. This handler is called when an item is clicked
     * @param flightsDto - Model containing all flights data
     * @param airlines - Map containing airline names mapped to respective airline codes
     * @param airports - Map containing airport names mapped to respective city codes
     * @param providers - Map containing provider names mapped to respective provider ids
     */
    public FlightAdapter(Context applicationContext, FlightAdapterOnClickHandler clickHandler, FlightsDto flightsDto, HashMap<String, String> airlines, HashMap<String, String> airports, HashMap<String, String> providers) {
        this.context = applicationContext;
        this.flightsDto = flightsDto;
        this.airlines = airlines;
        this.airports = airports;
        this.providers = providers;
        this.mClickHandler = clickHandler;
    }

    /**
     * A ViewHolder is a required part for RecyclerViews. It mostly behaves as
     * a cache of the child views for a flight item.
     */
    public class FlightAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mLogo;
        private TextView mDepartureTime;
        private TextView mArrivalTime;
        private TextView mFare;
        private TextView mCategory;
        private TextView mFlightDuration;

        public FlightAdapterViewHolder(@NonNull final View view) {
            super(view);
            mLogo = itemView.findViewById(R.id.logo);
            mDepartureTime = itemView.findViewById(R.id.departure_time);
            mArrivalTime = itemView.findViewById(R.id.arrival_time);
            mFare = itemView.findViewById(R.id.fare);
            mCategory = itemView.findViewById(R.id.category);
            mFlightDuration = itemView.findViewById(R.id.flight_duration);

            view.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mClickHandler.onClick(getAdapterPosition());
        }
    }

    /**
     * This gets called when each new ViewHolder is created.
     * @param parent - The ViewGroup that these ViewHolders are contained within.
     * @param viewType - ViewType integer to provide a different layout in case
     *                 our RecyclerView has more than one type of item
     * @return - A new ViewHolder that holds the View for each list item
     */
    @NonNull
    @Override
    public FlightAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.flight_item, parent, false);
        return new FlightAdapterViewHolder(view);
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display flight details
     * using "position" argument
     * @param holder - The ViewHolder which should be updated to represent the
     *                  contents of the item at the given position in the data set.
     * @param position - The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull FlightAdapterViewHolder holder, int position) {
        flightArray = flightsDto.getFlights();
        Flight currFlightData = flightArray.get(position);
        fareArray = currFlightData.getFares();

        holder.mLogo.setText(airlines.get(currFlightData.getAirlineCode()));
        holder.mDepartureTime.setText(currFlightData.getDisplayDepartureDate());
        holder.mArrivalTime.setText(currFlightData.getDisplayArrivalDate());
        holder.mFare.setText("₹" + fareArray.get(0).getFare().toString());
        holder.mCategory.setText(currFlightData.getClass_());
        holder.mFlightDuration.setText(currFlightData.getFlightDuration());
    }

    @Override
    public int getItemCount() {
        return flightsDto.getFlights().size();
    }
}