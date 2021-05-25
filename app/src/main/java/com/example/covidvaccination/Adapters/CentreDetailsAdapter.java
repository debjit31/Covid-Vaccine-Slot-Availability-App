package com.example.covidvaccination.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidvaccination.models.CentreDetails;
import com.example.covidvaccination.R;

import java.util.ArrayList;

public class CentreDetailsAdapter extends RecyclerView.Adapter<CentreDetailsAdapter.ViewHolder> {

    private LayoutInflater mLayoutInflater;
    private ArrayList<CentreDetails> centres;

    public CentreDetailsAdapter(Context mContext, ArrayList<CentreDetails> centres) {
        this.mLayoutInflater = LayoutInflater.from(mContext);
        this.centres = centres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.centre_details_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.mCentreName.setText(centres.get(position).getCenterName());
        holder.mCentreAddress.setText(centres.get(position).getCenterAddress());
        holder.mCentreTimimngs.setText(centres.get(position).getCenterFromTime() + " to " + centres.get(position).getCenterToTime());
        holder.mVaccineName.setText(centres.get(position).getVaccineName());
        holder.mFees.setText(centres.get(position).getFee_type());
        holder.mAgeLimit.setText("Age Limit : " + centres.get(position).getAgeLimit());
        holder.mAvailability.setText("Availability :  " + centres.get(position).getAvaiableCapacity());
    }

    @Override
    public int getItemCount() {
        return centres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView mCentreName, mCentreAddress, mCentreTimimngs, mVaccineName, mFees, mAgeLimit, mAvailability;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mCentreName = itemView.findViewById(R.id.centerName);
            mCentreAddress = itemView.findViewById(R.id.centerLocation);
            mCentreTimimngs = itemView.findViewById(R.id.centerTimings);
            mVaccineName = itemView.findViewById(R.id.vaccineName);
            mFees = itemView.findViewById(R.id.vaccineFees);
            mAgeLimit = itemView.findViewById(R.id.ageLimitTV);
            mAvailability = itemView.findViewById(R.id.availabilityTV);

        }
    }
}
