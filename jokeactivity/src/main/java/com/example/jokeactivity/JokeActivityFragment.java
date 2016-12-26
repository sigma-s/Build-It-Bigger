package com.example.jokeactivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A placeholder fragment containing a simple view.
 */
public class JokeActivityFragment extends Fragment {

    public static final String ARG_JOKE = "ARG_JOKE";
    private String joke;

    public JokeActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_JOKE)) {
            joke = getArguments().getString(ARG_JOKE);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root =  inflater.inflate(R.layout.fragment_joke_activity, container, false);
        TextView textView = (TextView) root.findViewById(R.id.text_view);
        textView.setText(joke);
        return root;
    }
}
