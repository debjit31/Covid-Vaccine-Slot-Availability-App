package com.example.covidvaccination;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class CentreDetailsAdapter extends RecyclerView.Adapter<CentreDetailsAdapter.ViewHolder>{

    private Context mContext;
    private List<CentreDetails> centres;

    public CentreDetailsAdapter(Context mContext, List<CentreDetails> centres) {
        this.mContext = mContext;
        this.centres = centres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.centre_details_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return  vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(centres.get(position));
        CentreDetails cd = centres.get(position);
        holder.mCentreName.setText(cd.getCenterName());
        holder.mCentreAddress.setText(cd.getCenterAddress());
        holder.mCentreTimimngs.setText(cd.getCenterFromTime() + " to " + cd.getCenterToTime());
        holder.mVaccineName.setText(cd.getVaccineName());
        holder.mFees.setText(cd.getFee_type());
        holder.mAgeLimit.setText(cd.getAgeLimit());
        holder.mAvailability.setText(cd.getAvaiableCapacity());

    }

    @Override
    public int getItemCount() {
        return centres.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mCentreName;
        public TextView mCentreAddress;
        public TextView mCentreTimimngs;
        public TextView mVaccineName;
        public TextView mFees;
        public TextView mAgeLimit;
        public TextView mAvailability;

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
