package com.example.mathgameapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import java.util.Set;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setUpHomeToGameButton(view);
        setUpHomeToStatsButton(view);

        return view;
    }

    private void setUpHomeToGameButton(View view) {
        Button btnHomeToGame = view.findViewById(R.id.btnHomeToGame);
        btnHomeToGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigate_homeFragment_to_gameFragment);
            }
        });
    }

    private Set<String> getSelectedOperators (View view) {
        Set<String> operators = null;
        ToggleButton[] buttons = getOperatorButtons(view);

        for (ToggleButton currentButton : buttons) {
            if (currentButton.isChecked()) {
                operators.add(currentButton.getText().toString());
            }
        }

        return operators;
    }

    private ToggleButton[] getOperatorButtons(View view) {
        return new ToggleButton[] {
                view.findViewById(R.id.btnAddition),
                view.findViewById(R.id.btnSubtraction),
                view.findViewById(R.id.btnMultiplication),
                view.findViewById(R.id.btnDivision)
        };
    }

    private void setUpHomeToStatsButton(View view) {
        Button btnHomeToStats = view.findViewById(R.id.btnHomeToStats);
        btnHomeToStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.navigate_homeFragment_to_statisticsFragment);
            }
        });
    }
}