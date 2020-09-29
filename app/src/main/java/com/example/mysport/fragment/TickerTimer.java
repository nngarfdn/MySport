package com.example.mysport.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import com.example.mysport.R;
import com.github.florent37.materialtextfield.MaterialTextField;

import java.util.Objects;

import ticker.views.com.ticker.widgets.circular.timer.callbacks.CircularViewCallback;
import ticker.views.com.ticker.widgets.circular.timer.view.CircularView;


public class TickerTimer extends Fragment implements View.OnClickListener {

    //private CircularView circularViewWithoutText;
    private CircularView circularViewWithTimer;
    String nameDet ;
        String photoDet;
        int colorDet;
    private MaterialTextField materialTextField;
    private Button set;
    private EditText input;

    private long p;
    private Button start, stop;


    public TickerTimer() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         Bundle mBundle = getArguments();

        assert mBundle != null;
        nameDet = mBundle.getString("name");
         photoDet = mBundle.getString("photo");
       colorDet = mBundle.getInt("color");
        View view = inflater.inflate(R.layout.fragment_ticker_timer, container, false);
        init(view);
        return view;
    }

    private void init(View v) {


//        final String status = mBundle.getString("fav_status");
//        final String key_id = mBundle.getString("key_id");

        stop = v.findViewById(R.id.stop);
        materialTextField = v.findViewById(R.id.materialTextField);
        set = v.findViewById(R.id.set);
        input = v.findViewById(R.id.input);

        start = v.findViewById(R.id.start);

        initCircularViewWithTimer(v, 0);
        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        set.setOnClickListener(this);
    }

//     private void initCircularViewWithoutText(View v) {
////        circularViewWithoutText = v.findViewById(R.id.circular_view);
//        CircularView.OptionsBuilder builderWithoutText = new
//                CircularView.OptionsBuilder()
//                .shouldDisplayText(false)
//                .setCounterInSeconds(CircularView.OptionsBuilder.INFINITE);
////        circularViewWithoutText.setOptions(builderWithoutText);
//    }

    private void initCircularViewWithTimer(View v, long scond) {

        circularViewWithTimer = v.findViewById(R.id.circular_view_with_timer);
        CircularView.OptionsBuilder builderWithTimer = new
                CircularView.OptionsBuilder()
                .shouldDisplayText(true)
                .setCounterInSeconds(scond)
                .setCircularViewCallback(new CircularViewCallback() {
                    @Override
                    public void onTimerFinish() {
                        Toast.makeText(getActivity(), "CircularCallback: Timer Finished ", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onTimerCancelled() {
                        Toast.makeText(getActivity(), "CircularCallback: Timer Cancelled ", Toast.LENGTH_SHORT).show();
                    }
                });
        circularViewWithTimer.setOptions(builderWithTimer);
    }


//    private void initCircularViewWithCustomText(View v) {
//        //circularViewWithCustomText = v.findViewById(R.id.circular_view_with_custom_text);
//        CircularView.OptionsBuilder builderWithoutText = new
//                CircularView.OptionsBuilder()
//                .setCounterInSeconds(CircularView.OptionsBuilder.INFINITE)
//                .setCustomText("Waiting for Customer");
//       // circularViewWithCustomText.setOptions(builderWithoutText);
//    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.start:
                initCircularViewWithTimer(Objects.requireNonNull(getView()), p);
                //circularViewWithoutText.startTimer();
                circularViewWithTimer.startTimer();
                input.setVisibility(View.INVISIBLE);
                set.setVisibility(View.INVISIBLE);
                start.setVisibility(View.INVISIBLE);
                stop.setVisibility(View.VISIBLE);
                materialTextField.setVisibility(View.INVISIBLE);
                //circularViewWithCustomText.startTimer();
                break;

            case R.id.stop:
                set.setVisibility(View.VISIBLE);
                start.setVisibility(View.INVISIBLE);
                stop.setVisibility(View.INVISIBLE);
                input.setVisibility(View.VISIBLE);
                materialTextField.setVisibility(View.VISIBLE);
                circularViewWithTimer.stopTimer();
                break;
            case R.id.set:
                set.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);
                stop.setVisibility(View.INVISIBLE);
                input.setVisibility(View.INVISIBLE);
                materialTextField.setVisibility(View.INVISIBLE);
                String timeInput = input.getText().toString();
                if (input.length() == 0) {
                    Toast.makeText(getActivity(), "Field can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                long millisInput = Long.parseLong(timeInput) * 60;
                if (millisInput == 0) {
                    Toast.makeText(getActivity(), "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }
                p = millisInput;
                initCircularViewWithTimer(Objects.requireNonNull(getView()), millisInput);
                input.setText("");
                break;
        }
    }


}
