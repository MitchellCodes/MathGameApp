package com.example.mathgameapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticsFragment extends Fragment {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CORRECT_ANSWERS_KEY = "correctAnswers";
    public static final int CORRECT_ANSWERS_KEY_DEFAULT_VALUE = 0;
    public static final String INCORRECT_ANSWERS_KEY = "incorrectAnswers";
    public static final int INCORRECT_ANSWERS_KEY_DEFAULT_VALUE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);

        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        int correctAnswerCount = sharedPreferences.getInt(CORRECT_ANSWERS_KEY, CORRECT_ANSWERS_KEY_DEFAULT_VALUE);

        TextView textView = view.findViewById(R.id.txtTesting);
        textView.setText("" + correctAnswerCount);

        return view;
    }
}