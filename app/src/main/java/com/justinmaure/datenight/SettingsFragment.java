package com.justinmaure.datenight;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView usernameLabel;
    Button forgotPasswordBtn;
    Button contactBtn;
    Button popularFilterBtn;
    Button recentFilterBtn;
    Button creditsBtn;
    Button logoutBtn;

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        MainActivity.navigation.setVisibility(View.VISIBLE);
        MainActivity.fab.hide();

        //Username Label
        usernameLabel = view.findViewById(R.id.usernameLabel);
        usernameLabel.setText(MainActivity.currentUser.getUsername());



        //Filter the main page to show the most recent dates
        recentFilterBtn = view.findViewById(R.id.recentFilterBtn);
        recentFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragment.filter = 0;
                MainActivity.navigation.setSelectedItemId(R.id.navigation_main);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new MainFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //Filter the main page to show the most popular dates
        popularFilterBtn = view.findViewById(R.id.popularFilterBtn);
        popularFilterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainFragment.filter = 1;
                MainActivity.navigation.setSelectedItemId(R.id.navigation_main);
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new MainFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //Forgot Password Button
        forgotPasswordBtn = view.findViewById(R.id.forgotPasswordBtn);
        forgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //mail the user their password
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto",MainActivity.currentUser.getEmail(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Forgotten Password");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello, " + MainActivity.currentUser.getUsername()
                        + "! Here is your password for Date Night... " + MainActivity.currentUser.getPassword());
                startActivity(Intent.createChooser(emailIntent, "Date Night"));
            }
        });

        //Credits button
        creditsBtn = view.findViewById(R.id.creditsBtn);
        creditsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new CreditsFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        //Contact Us
        contactBtn = view.findViewById(R.id.contactUsBtn);
        contactBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.content, new ContactUsFragment());
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        //Log out button
        logoutBtn = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginActivity.user.setLoggedIn(false);
                //Launch the login screen
                Intent i = new Intent(getActivity(), LoginActivity.class);
                startActivity(i);
            }
        });





        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
