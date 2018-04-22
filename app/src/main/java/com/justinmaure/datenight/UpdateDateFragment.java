package com.justinmaure.datenight;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.justinmaure.datenight.Objects.Date;


public class UpdateDateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private Date mParam1;


    private ImageView picture;
    private EditText dateName;
    private EditText description;
    private Switch isPublic;
    private int isPublicNum = 0;
    private Button submitBtn;
    FragmentManager fm;

    private OnFragmentInteractionListener mListener;

    public UpdateDateFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static UpdateDateFragment newInstance(Parcelable param1) {
        UpdateDateFragment fragment = new UpdateDateFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_date, container, false);

        MainActivity.fab.hide();

        picture = (ImageView) view.findViewById(R.id.datePicture);
//        picture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Add code to launch the camera to upload a picture to the image view
//            }
//        });

        dateName = (EditText) view.findViewById(R.id.dateName);

        description = (EditText) view.findViewById(R.id.dateDescription);

        isPublic = (Switch) view.findViewById(R.id.isPublic);
        isPublic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //Add code to change the text from private to public and back
                if (isPublic.isChecked()) {
                    isPublic.setText("Public");
                    isPublicNum = 1;
                } else {
                    isPublic.setText("Private");
                    isPublicNum = 0;
                }
            }
        });

        submitBtn = (Button) view.findViewById(R.id.submitBtn);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(getContext());
                db.updateDate(new Date(dateName.getText().toString(),description.getText().toString(), picture.getDrawable().toString(),isPublicNum, 0, MainActivity.currentUser.getUsername(), 0));



                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.content, new MyDatesFragment());
                transaction.addToBackStack(null);
                transaction.commit();

                db.close();
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
