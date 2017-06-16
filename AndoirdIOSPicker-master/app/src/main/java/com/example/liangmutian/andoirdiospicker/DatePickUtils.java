package com.example.liangmutian.andoirdiospicker;

import android.app.Dialog;
import android.content.Context;

import com.example.liangmutian.mypicker.ChoosePickerDialog;
import com.example.liangmutian.mypicker.DateAndTimePickerDialog;
import com.example.liangmutian.mypicker.DatePickerDialog;
import com.example.liangmutian.mypicker.DateUtil;
import com.example.liangmutian.mypicker.TimePickerDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by feng.gao on 2017/6/16.
 */

public class DatePickUtils {
    private static Dialog dateDialog, timeDialog, chooseDialog;


    //自定义选择回调
    public interface OnSelectedListener {
        void onSelected(String itemValue, int position);
    }

    //年月日选择回调
    public interface OnDateSelectedListener {
        void onDateSelected(String date);
    }

    //年月日时分选择回调
    public interface OnDateTimeSelectedListener {
        void onDateTimeSelected(String date);
    }

    //时分的选择回调
    public interface OnTimeSelectedListener {
        void onTimeSelected(String times);
    }


    /**
     * chooseDialog
     */
    public static void showChooseDialog(Context context, List<String> mlist, final OnSelectedListener onSelectedListener) {
        ChoosePickerDialog.Builder builder = new ChoosePickerDialog.Builder(context);
        chooseDialog = builder.setData(mlist).setSelection(1).setTitle("取消")
                .setOnDataSelectedListener(new ChoosePickerDialog.OnSelectedListener() {
                    @Override
                    public void onSelected(String itemValue, int position) {
                        onSelectedListener.onSelected(itemValue, position);
                    }

                    @Override
                    public void onCancel() {

                    }
                }).create();

        chooseDialog.show();
    }

    /**
     * 年月日的选择 , 默认选择
     *
     * @param context
     * @param date                   传入的时间参数，为空则显示系统当前的时间,时间格式为 "2017-6-16"，目前格式不对则为null
     * @param onDateSelectedListener 回调接口
     */
    public static void showDateDialog(Context context, String date, final OnDateSelectedListener onDateSelectedListener) {
        DatePickerDialog.Builder builder = new DatePickerDialog.Builder(context);
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(String dates) {
                onDateSelectedListener.onDateSelected(dates);
            }

            @Override
            public void onCancel() {

            }

        });

        if (date != null) {
            List<Integer> dataList = DateUtil.getDateForString(date);
            if (dataList != null && dataList.size() >= 3) {
                builder.setSelectYear(dataList.get(0) - 1)
                        .setSelectMonth(dataList.get(1) - 1)
                        .setSelectDay(dataList.get(2) - 1);
            }

        }
        dateDialog = builder.create();
        dateDialog.show();
    }

    /**
     * 年月日的选择,如果需要设置最小日期或者最大日期，传入builder，builder.setMax()或者builder.setMin();
     *
     * @param date
     * @param onDateSelectedListener
     * @param builder
     */
    public static void showDateDialog(String date, final OnDateSelectedListener onDateSelectedListener, DatePickerDialog.Builder builder) {
        builder.setOnDateSelectedListener(new DatePickerDialog.OnDateSelectedListener() {
            @Override
            public void onDateSelected(String dates) {
                onDateSelectedListener.onDateSelected(dates);
            }

            @Override
            public void onCancel() {

            }

        });

        if (date != null) {
            List<Integer> dataList = DateUtil.getDateForString(date);
            if (dataList != null && dataList.size() >= 3) {
                builder.setSelectYear(dataList.get(0) - 1)
                        .setSelectMonth(dataList.get(1) - 1)
                        .setSelectDay(dataList.get(2) - 1);
            }

        }
        dateDialog = builder.create();
        dateDialog.show();
    }

    /**
     * 年月日时分选择
     *
     * @param context                    不多说
     * @param date                       传入的日期 ， 可以为空 ,目前定义的格式为 "2017-6-16-18-30" 格式不对会置空
     * @param onDateTimeSelectedListener 选择的接口回调
     */
    public static void showDateAndTimeDialog(Context context, String date, final OnDateTimeSelectedListener onDateTimeSelectedListener) {
        DateAndTimePickerDialog.Builder builder = new DateAndTimePickerDialog.Builder(context);
        builder.setOnDateSelectedListener(new DateAndTimePickerDialog.OnDateTimeSelectedListener() {

            @Override
            public void onDateTimeSelected(String date) {
                onDateTimeSelectedListener.onDateTimeSelected(date);

            }

            @Override
            public void onCancel() {

            }
        });

        if (date != null) {
            List<Integer> dataList = DateUtil.getDateAndTimeForString(date);
            if (dataList != null && dataList.size() >= 5) {
                builder.setSelectYear(dataList.get(0) - 1)
                        .setSelectMonth(dataList.get(1) - 1)
                        .setSelectDay(dataList.get(2) - 1).setSelectHour(dataList.get(3)).setSelectMin(dataList.get(4));
            }
        }

//        builder.setMinYear(DateUtil.getYear());
//        builder.setMinMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
//        builder.setMinDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
        dateDialog = builder.create();
        dateDialog.show();
    }


    /**
     * 年月日时分选择
     *
     * @param date                       传入的日期
     * @param builder                    建造者模式，可自定义最大最小日期时间
     * @param onDateTimeSelectedListener 选择的接口回调
     */
    public static void showDateAndTimeDialog(String date, DateAndTimePickerDialog.Builder builder, final OnDateTimeSelectedListener onDateTimeSelectedListener) {
        builder.setOnDateSelectedListener(new DateAndTimePickerDialog.OnDateTimeSelectedListener() {

            @Override
            public void onDateTimeSelected(String date) {
                onDateTimeSelectedListener.onDateTimeSelected(date);

            }

            @Override
            public void onCancel() {

            }
        });
        if (date != null) {
            List<Integer> dataList = DateUtil.getDateAndTimeForString(date);
            if (dataList != null && dataList.size() >= 5) {
                builder.setSelectYear(dataList.get(0) - 1)
                        .setSelectMonth(dataList.get(1) - 1)
                        .setSelectDay(dataList.get(2) - 1).setSelectHour(dataList.get(3)).setSelectMin(dataList.get(4));
            }
        }

//        builder.setMinYear(DateUtil.getYear());
//        builder.setMinMonth(DateUtil.getDateForString(DateUtil.getToday()).get(1));
//        builder.setMinDay(DateUtil.getDateForString(DateUtil.getToday()).get(2));
        dateDialog = builder.create();
        dateDialog.show();
    }


    /**
     * 时分选择
     *
     * @param context
     * @param date
     * @param onTimeSelectedListener
     */
    public static void showTimePick(Context context, String date, final OnTimeSelectedListener onTimeSelectedListener) {
        timeDialog = null;
        TimePickerDialog.Builder builder = new TimePickerDialog.Builder(context);
        if (date != null) {
            List<Integer> dataList = DateUtil.getTimeForString(date);
            if (dataList != null && dataList.size() >= 2) {
                builder.setSelectHour(dataList.get(0))
                        .setSelectMin(dataList.get(1));
            }
        }
        timeDialog = builder.setOnTimeSelectedListener(new TimePickerDialog.OnTimeSelectedListener() {
            @Override
            public void onTimeSelected(String times) {
                onTimeSelectedListener.onTimeSelected(times);

            }
        }).create();


        timeDialog.show();

    }

}
