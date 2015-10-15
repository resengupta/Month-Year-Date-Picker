package com.studio.modifieddatepicker.widget;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.studio.modifieddatepicker.R;

/**
 * A simple dialog containing month and year picker and also provides callback on positive
 * selection.
 */
public class SimpleDatePickerDialog extends AlertDialog implements DialogInterface
        .OnClickListener, SimpleDatePickerDelegate.OnDateChangedListener {

    private static final String YEAR = "year";
    private static final String MONTH = "month";

    private SimpleDatePickerDelegate mSimpleDatePickerDelegate;
    private OnDateSetListener mDateSetListener;

    /**
     * @param context The context the dialog is to run in.
     */
    public SimpleDatePickerDialog(Context context, OnDateSetListener listener, int year,
                                  int monthOfYear) {
        this(context, 0, listener, year, monthOfYear);
    }

    /**
     * @param context The context the dialog is to run in.
     * @param theme   the theme to apply to this dialog
     */
    @SuppressLint("InflateParams")
    public SimpleDatePickerDialog(Context context, int theme, OnDateSetListener listener, int year,
                                  int monthOfYear) {
        super(context, theme);

        mDateSetListener = listener;

        Context themeContext = getContext();
        LayoutInflater inflater = LayoutInflater.from(themeContext);
        View view = inflater.inflate(R.layout.month_year_picker, null);
        setView(view);
        setButton(BUTTON_POSITIVE, themeContext.getString(android.R.string.ok), this);
        setButton(BUTTON_NEGATIVE, themeContext.getString(android.R.string.cancel), this);

        mSimpleDatePickerDelegate = new SimpleDatePickerDelegate(view);
        mSimpleDatePickerDelegate.init(year, monthOfYear, this);
    }

    @Override
    public void onDateChanged(int year, int month) {
        // Stub - do nothing
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case BUTTON_POSITIVE:
                if (mDateSetListener != null) {
                    mDateSetListener.onDateSet(
                            mSimpleDatePickerDelegate.getYear(),
                            mSimpleDatePickerDelegate.getMonth());
                }
                break;
            case BUTTON_NEGATIVE:
                cancel();
                break;
        }
    }

    @Override
    public Bundle onSaveInstanceState() {
        Bundle state = super.onSaveInstanceState();
        state.putInt(YEAR, mSimpleDatePickerDelegate.getYear());
        state.putInt(MONTH, mSimpleDatePickerDelegate.getMonth());
        return state;
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int year = savedInstanceState.getInt(YEAR);
        int month = savedInstanceState.getInt(MONTH);
        mSimpleDatePickerDelegate.init(year, month, this);
    }

    public void setMinDate(long minDate) {
        mSimpleDatePickerDelegate.setMinDate(minDate);
    }

    public void setMaxDate(long maxDate) {
        mSimpleDatePickerDelegate.setMaxDate(maxDate);
    }

    /**
     * The callback used to indicate the user is done filling in the date.
     */
    public interface OnDateSetListener {

        /**
         * @param year        The year that was set.
         * @param monthOfYear The month that was set (0-11) for compatibility with {@link
         *                    java.util.Calendar}.
         */
        void onDateSet(int year, int monthOfYear);
    }
}
