package com.example.mathgameapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String CORRECT_ANSWERS_KEY = "correctAnswers";
    public static final int CORRECT_ANSWERS_KEY_DEFAULT_VALUE = 0;
    public static final String INCORRECT_ANSWERS_KEY = "incorrectAnswers";
    public static final int INCORRECT_ANSWERS_KEY_DEFAULT_VALUE = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game, container, false);
        Button submitAnswerButton = view.findViewById(R.id.btnSubmitAnswer);
        EditText answerEditText = view.findViewById(R.id.txtAnswer);
        TextView equationDisplay = view.findViewById(R.id.txtEquationOutput);

        hideTextViewsAtStart(view);

        submitAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCorrectAnswers();
            }
        });

        return view;
    }

    public void hideTextViewsAtStart(View view) {
        TextView previousEquationTitle = view.findViewById(R.id.txtPreviousEquationTitle);
        TextView previousEquationDisplay = view.findViewById(R.id.txtPreviousEquation);

        previousEquationTitle.setVisibility(View.INVISIBLE);
        previousEquationDisplay.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        GameFragmentArgs args = GameFragmentArgs.fromBundle(getArguments());
        String[] operators = args.getOperators();
        String operatorsToUse = "";

        for (String operator : operators) {
            operatorsToUse += operator;
        }

        TextView textView1 = view.findViewById(R.id.txtEquationOutput);
        textView1.setText(operatorsToUse);
    }

    public void updateCorrectAnswers() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        int startingValue =
                sharedPreferences.getInt(CORRECT_ANSWERS_KEY, CORRECT_ANSWERS_KEY_DEFAULT_VALUE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CORRECT_ANSWERS_KEY, startingValue + 1);
        editor.apply();

        Toast.makeText(getActivity(), "You clicked the submit button", Toast.LENGTH_SHORT).show();
    }
}