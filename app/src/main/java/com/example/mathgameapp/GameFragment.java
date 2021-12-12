package com.example.mathgameapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    private final Random rand = new Random();
    private String[] operatorsFromHome;
    private int equationSolution;

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

        hideTextViewsAtStart(view);

        submitAnswerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processInput();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        GameFragmentArgs args = GameFragmentArgs.fromBundle(getArguments());
        operatorsFromHome = args.getOperators();

        equationSolution = generateAndShowEquation();
    }

    private void processInput() {
        TextView answerInput = getView().findViewById(R.id.txtAnswer);
        String userInput = answerInput.getText().toString();
        boolean isInputValid = parseInput(userInput);

        if (isInputValid) {
            compareInputToAnswer(Integer.parseInt(userInput));
        }
        else {
            Toast.makeText(getActivity(), "Input was not a number", Toast.LENGTH_SHORT).show();
        }
    }

    private void compareInputToAnswer(int userAnswer) {
        if (userAnswer == equationSolution) {
            updateCorrectAnswers();
        }
        else {
            updateIncorrectAnswers();
        }

        moveEquationToPrevious();
        showHiddenTextViews();
        equationSolution = generateAndShowEquation();
    }

    private boolean parseInput(String inputToParse) {
        try {
            Integer.parseInt(inputToParse);
            return true;
        }
        catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void moveEquationToPrevious() {
        TextView equationOutput = getView().findViewById(R.id.txtEquationOutput);
        TextView previousEquation = getView().findViewById(R.id.txtPreviousEquation);

        String equationWithSolution = equationOutput.getText().toString();
        equationWithSolution = equationWithSolution.substring(0, equationWithSolution.length() - 1);
        equationWithSolution += equationSolution;
        previousEquation.setText(equationWithSolution);
    }

    private int generateAndShowEquation() {
        String operatorForEquation = pickRandomOperator(operatorsFromHome);
        int[] numbersForEquation = generateNumbers(operatorForEquation);

        TextView equationOutput = getView().findViewById(R.id.txtEquationOutput);
        String output = equationAsString(numbersForEquation, operatorForEquation) + " = x";
        equationOutput.setText(output);

        return calculateEquationSolution(numbersForEquation, operatorForEquation);
    }

    private String pickRandomOperator(String[] operatorsToUse) {
        return operatorsToUse[rand.nextInt(operatorsToUse.length)];
    }

    private int[] generateNumbers(String operator) {
        int firstNumber = 0;
        int secondNumber = 0;

        if (operator.equals("+") || operator.equals("-")) {
            firstNumber = rand.nextInt(144) + 1;
            secondNumber = rand.nextInt(144) + 1;
        }
        else if (operator.equals("*")) {
            firstNumber = rand.nextInt(12) + 1;
            secondNumber = rand.nextInt(12) + 1;
        }
        else {
            firstNumber = rand.nextInt(144) + 1;
            secondNumber = rand.nextInt(12) + 1;
        }

        return new int[] {firstNumber, secondNumber};
    }

    private String equationAsString(int[] numbers, String operator) {
        return numbers[0] + " " + operator + " " + numbers[1];
    }

    private int calculateEquationSolution(int[] numbers, String operator) {
        switch (operator) {
            case "+":
                return numbers[0] + numbers[1];
            case "-":
                return numbers[0] - numbers[1];
            case "*":
                return numbers[0] * numbers[1];
            case "/":
                return numbers[0] / numbers[1];
        }
        throw new IllegalArgumentException("Operator was not +, -, *, or /");
    }

    private void hideTextViewsAtStart(View view) {
        TextView previousEquationTitle = view.findViewById(R.id.txtPreviousEquationTitle);
        TextView previousEquationDisplay = view.findViewById(R.id.txtPreviousEquation);

        previousEquationTitle.setVisibility(View.INVISIBLE);
        previousEquationDisplay.setVisibility(View.INVISIBLE);
    }

    private void showHiddenTextViews() {
        TextView previousEquationTitle = getView().findViewById(R.id.txtPreviousEquationTitle);
        TextView previousEquationDisplay = getView().findViewById(R.id.txtPreviousEquation);

        previousEquationTitle.setVisibility(View.VISIBLE);
        previousEquationDisplay.setVisibility(View.VISIBLE);
    }

    private void updateCorrectAnswers() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        int startingValue =
                sharedPreferences.getInt(CORRECT_ANSWERS_KEY, CORRECT_ANSWERS_KEY_DEFAULT_VALUE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CORRECT_ANSWERS_KEY, startingValue + 1);
        editor.apply();

        Toast.makeText(getActivity(), "Your answer was correct", Toast.LENGTH_SHORT).show();
    }

    private void updateIncorrectAnswers() {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);

        int startingValue =
                sharedPreferences.getInt(INCORRECT_ANSWERS_KEY, INCORRECT_ANSWERS_KEY_DEFAULT_VALUE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(INCORRECT_ANSWERS_KEY, startingValue + 1);
        editor.apply();

        Toast.makeText(getActivity(), "Your answer was wrong", Toast.LENGTH_SHORT).show();
    }
}