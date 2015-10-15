package com.studio.modifieddatepicker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.studio.modifieddatepicker.utils.DateDisplayUtils;
import com.studio.modifieddatepicker.widget.SimpleDatePickerDialog;
import com.studio.modifieddatepicker.widget.SimpleDatePickerDialogFragment;

import java.util.Calendar;
import java.util.Locale;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements SimpleDatePickerDialog.OnDateSetListener, View.OnClickListener {

    private TextView mMonthYearTextView;
    private Button mPickDateButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMonthYearTextView = (TextView) view.findViewById(R.id.monthYearTextView);
        mPickDateButton = (Button) view.findViewById(R.id.pickDateButton);
        mPickDateButton.setOnClickListener(this);
    }

    @Override
    public void onDateSet(int year, int monthOfYear) {
        mMonthYearTextView.setText(DateDisplayUtils.formatMonthYear(year, monthOfYear));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.pickDateButton) {
            displaySimpleDatePickerDialogFragment();
        }
    }

    private void displaySimpleDatePickerDialogFragment() {
        SimpleDatePickerDialogFragment datePickerDialogFragment;
        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        datePickerDialogFragment = SimpleDatePickerDialogFragment.getInstance(
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH));
        datePickerDialogFragment.setOnDateSetListener(this);
        datePickerDialogFragment.show(getChildFragmentManager(), null);
    }

}
