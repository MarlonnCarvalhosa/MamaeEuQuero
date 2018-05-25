package com.marlonncarvalhosa.mamaeeuquero.fragments;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marlonncarvalhosa.mamaeeuquero.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment {

    TextView text1;
    public final static long SECOND_MILLIS = 1000;
    public final static long MINUTE_MILLIS = SECOND_MILLIS*60;
    public final static long HOUR_MILLIS = MINUTE_MILLIS*60;

    private static final String FORMAT = "%02dh %02dm %02ds";
    private Calendar data;

    int seconds , minutes;


    public FavoritosFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_favoritos, container, false);
        idcampo(view);
        data = Calendar.getInstance();
        data.set(Calendar.HOUR_OF_DAY, 18);
        data.set(Calendar.MINUTE, 30);

        Calendar diaAtual = Calendar.getInstance();

        Calendar proximoDia = Calendar.getInstance();
        proximoDia.setTimeInMillis(data.getTimeInMillis());
        proximoDia.add(Calendar.DAY_OF_MONTH, 1);

        int hora = hoursDiff(diaAtual.getTime(), proximoDia.getTime());
        int minute = minutesDiff(diaAtual.getTime(), proximoDia.getTime());
        int segundo = secondsDiff(diaAtual.getTime(), proximoDia.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(diaAtual.getTimeInMillis());
        calendar.add(Calendar.HOUR_OF_DAY, hora);
        calendar.add(Calendar.MINUTE, minute);
        calendar.add(Calendar.SECOND, segundo);
        seconds = (hora * 60 * 60) + (minutes * 60) + seconds;


        Log.v("rfr", "Diferença final " + calendar.getTime());
        new CountDownTimer(	seconds, 1000) { // adjust the milli seconds here

            public void onTick(long millisUntilFinished) {

                data.setTimeInMillis(millisUntilFinished);
                data.add(Calendar.DAY_OF_MONTH, 1);
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                String horaFormatada = dateFormat.format(data.getTime());
                text1.setText(horaFormatada);
                /*

                text1.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toHours(millisUntilFinished),
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                                */
            }

            public void onFinish() {
                text1.setText("done!");
            }
        }.start();

        return  view;

    }

    public static int secondsDiff(Date earlierDate, Date laterDate )
    {
        if( earlierDate == null || laterDate == null ) return 0;

        return (int)((laterDate.getTime()/SECOND_MILLIS) - (earlierDate.getTime()/SECOND_MILLIS));
    }

    /**
     * Get the minutes difference
     */
    public static int minutesDiff( Date earlierDate, Date laterDate )
    {
        if( earlierDate == null || laterDate == null ) return 0;

        return (int)((laterDate.getTime()/MINUTE_MILLIS) - (earlierDate.getTime()/MINUTE_MILLIS));
    }

    /**
     * Get the hours difference
     */
    public static int hoursDiff( Date earlierDate, Date laterDate )
    {
        if( earlierDate == null || laterDate == null ) return 0;

        return (int)((laterDate.getTime()/HOUR_MILLIS) - (earlierDate.getTime()/HOUR_MILLIS));
    }


    public void idcampo (View view){

        text1 = view.findViewById(R.id.countdown_text);

    }

}
