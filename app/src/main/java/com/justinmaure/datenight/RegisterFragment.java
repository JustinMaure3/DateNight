package com.justinmaure.datenight;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.justinmaure.datenight.Objects.User;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegisterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText usernameField;
    private EditText emailField;
    private EditText passwordField1;
    private EditText passwordField2;
    private Button registerBtn;

    private OnFragmentInteractionListener mListener;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        usernameField = (EditText) view.findViewById(R.id.UsernameField);
        emailField = (EditText) view.findViewById(R.id.EmailField);
        passwordField1 = (EditText) view.findViewById(R.id.PasswordField1);
        passwordField2 = (EditText) view.findViewById(R.id.PasswordField2);
        registerBtn = (Button) view.findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isUsernameValid(usernameField.getText().toString())){
                    if (isEmailValid(emailField.getText().toString())) {
                        if (passwordField1.getText().toString() == passwordField2.getText().toString()) {
                            DatabaseHelper db = new DatabaseHelper(getContext());
                            db.addUser(new User(0, usernameField.getText().toString(),
                                    emailField.getText().toString(), passwordField1.getText().toString()));
                            db.close();
                            Intent i = new Intent(getContext(), LoginActivity.class);
                            startActivity(i);

                        } else {
                            passwordField1.setError("This password doesn't match the second");
                            passwordField2.setError("This password doesn't match the first");
                        }

                    } else {
                        emailField.setError("This is not a valid email");
                    }
                } else {
                    usernameField.setError("This username has already been taken");
                }
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

    public Boolean isUsernameValid(String username) {
        DatabaseHelper db = new DatabaseHelper(getContext());
        ArrayList<User> users = db.getAllUsers();
        db.close();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername() == username){
                return false;
            }
        }
        return true;
    }

    public Boolean isEmailValid(String email) {
        if (email.indexOf('@') == -1) {
            return false;
        }
        return true;
    }
}
