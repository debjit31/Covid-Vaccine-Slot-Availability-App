package com.example.covidvaccination;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private EditText mPincode;
    private Button mNext;
    private RecyclerView mRecyclerview;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String pincode, date;

    private List<CentreDetails> centres;

    private RequestQueue rq;

    private String api_url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/findByPin?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPincode = findViewById(R.id.inputPin);
        pincode = mPincode.getText().toString();
        mNext = findViewById(R.id.submitBtn);
        mRecyclerview = findViewById(R.id.recyclerView);
        mRecyclerview.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerview.setLayoutManager(mLayoutManager);

        rq = Volley.newRequestQueue(this);

        centres = new ArrayList<>();

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
                jsonParse();
                mAdapter = new CentreDetailsAdapter(MainActivity.this,centres);
                mRecyclerview.setAdapter(mAdapter);
            }
        });
    }
    private void jsonParse(){
        //String request_url = api_url + "pincode=" + pincode + "&date=" + date;
        String request_url = String.format("%spincode=%s&date=%s", api_url, pincode, date);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, request_url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("sessions");
                            for(int i=0; i<jsonArray.length();i++){
                                CentreDetails cd = new CentreDetails();
                                JSONObject session = jsonArray.getJSONObject(i);
                                cd.setCenterName(session.getString("name"));
                                cd.setCenterAddress(session.getString("address"));
                                cd.setCenterFromTime(session.getString("from"));
                                cd.setCenterToTime(session.getString("to"));
                                cd.setVaccineName(session.getString("vaccine"));
                                cd.setFee_type(session.getString("fee_type"));
                                cd.setAgeLimit(String.valueOf(session.getInt("min_age_limit")));
                                cd.setAvaiableCapacity(session.getString("available_capacity"));
                                centres.add(cd);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        rq.add(request);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar z = Calendar.getInstance();
        z.set(Calendar.YEAR, year);
        z.set(Calendar.MONTH, month);
        z.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-YYYY");
        dateformat.setTimeZone(z.getTimeZone());
        date = dateformat.format(z.getTime());
        Log.i("date", date);
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
    }
}