package com.example.liangmutian.andoirdiospicker;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.liangmutian.mypicker.ChoosePickerDialog;
import com.example.liangmutian.mypicker.DateAndTimePickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.example.liangmutian.mypicker.TimePickerDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Dialog dateDialog, timeDialog, chooseDialog;
    private TextView mTextView;
    private List<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = (TextView) findViewById(R.id.textView);
        String[] data = getResources().getStringArray(R.array.list);
        for (String str : data) {
            list.add(str);
        }
    }

    /**
     * chooseDialog
     */
    private void showChooseDialog(List<String> mlist) {
        ChoosePickerDialog.Builder builder = new ChoosePickerDialog.Builder(this);
        chooseDialog = builder.setData(mlist).setSelection(1).setTitle("取消")
                .setOnDataSelectedListener(new ChoosePickerDialog.OnSelectedListener() {
                    @Override
                    public void onSelected(String itemValue, int position) {
                        mTextView.setText(itemValue);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }


    private void showDateDialog(List<Integer> date) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(String dates) {

                mTextView.setText(dates);

            }

            @Override
            public void onCancel() {

            }

        });

        if (date != null) {
            builder.setSelectYear(date.get(0) - 1)
                    .setSelectMonth(date.get(1) - 1)
                    .setSelectDay(date.get(2) - 1);
        }
        dateDialog = builder.create();
        dateDialog.show();
    }

    private void showDateAndTimeDialog(List<Integer> date) {
        DateAndTimePickerDialog.Builder builder = new DateAndTimePickerDialog.Builder(this);
        builder.setOnDateSelectedListener(new DateAndTimePickerDialog.OnDateTimeSelectedListener() {

            @Override
            public void onDateTimeSelected(String date) {
                mTextView.setText(date);

            }

            @Override
            public void onCancel() {

            }
        });
        if (date != null) {
            builder.setSelectYear(date.get(0) - 1)
                    .setSelectMonth(date.get(1) - 1)
                    .setSelectDay(date.get(2) - 1).setSelectHour(date.get(3) - 1).setSelectMin(date.get(4) - 1);
        }

//        builder.setMinYear(DateUtil.getYear());
//        builder.setMinMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
//        builder.setMinDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
        dateDialog = builder.create();
        dateDialog.show();
    }

    private void showTimePick(String date) {

        if (timeDialog == null) {

            TimePickerDialog.Builder builder = new TimePickerDialog.Builder(this);
            timeDialog = builder.setOnTimeSelectedListener(new TimePickerDialog.OnTimeSelectedListener() {
                @Override
                public void onTimeSelected(String times) {

                    mTextView.setText(times);

                }
            }).create();
        }

        timeDialog.show();

    }


    public void time(View v) {
    DatePickUtils.showTimePick(this, "1730", new DatePickUtils.OnTimeSelectedListener() {
        @Override
        public void onTimeSelected(String times) {
            mTextView.setText(times);
        }
    });

    }

    public void date(View v) {

        DatePickUtils.showDateDialog(this, "", new DatePickUtils.OnDateSelectedListener() {
            @Override
            public void onDateSelected(String date) {
                mTextView.setText(date);
            }
        });

//        showDateDialog(null);


    }

    public void zidingyi(View v) {

        showChooseDialog(list);

    }

    public void dateAndTime(View v) {
      DatePickUtils.showDateAndTimeDialog("51515", new DateAndTimePickerDialog.Builder(this).setMaxYear(2019), new DatePickUtils.OnDateTimeSelectedListener() {
          @Override
          public void onDateTimeSelected(String date) {
              mTextView.setText(date);
          }
      });

    }
}
