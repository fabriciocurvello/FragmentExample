package com.example.android.fragmentexample;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SimpleFragment extends Fragment {


    private static final int YES = 0;
    private static final int NO = 1;

    public SimpleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        final View rootView = inflater.inflate(R.layout.fragment_simple, container, false);
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);
        final RatingBar estrela = rootView.findViewById(R.id.estrela);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(RadioGroup group, int checkedId) {
                  View radioButton = radioGroup.findViewById(checkedId);
                  int index = radioGroup.indexOfChild(radioButton);
                  TextView textView = rootView.findViewById(R.id.fragment_header);
                  switch (index) {
                      case YES:
                          textView.setText(R.string.yes_message);
                          break;
                      case NO:
                          textView.setText(R.string.no_message);
                          break;
                      default: // Nenhuma escolha feita pelo usuário.
                          // nada a fazer.
                          break;
                  }
              }
        });

        estrela.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                Log.i("----- ESTRELA -----", "onRatingChanged: " + v);

                Toast.makeText(getActivity(), "VC SELECIONOU " + v + " ESTRELAS", Toast.LENGTH_SHORT).show();
            }
        });

// Return the View for the fragment's UI.
        return rootView;
    }

}
