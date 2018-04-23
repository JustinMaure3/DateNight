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
    private static final String ARG_PARAM1 = "param1";

    private Date mParam1;


    private ImageView picture;
    private EditText dateName;
    private EditText description;
    private Switch isPublic;
    private int isPublicNum = 0;
    private Button submitBtn;
    private Button previousButton;
    private Button nextButton;
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

        picture = view.findViewById(R.id.datePicture);
        dateName = view.findViewById(R.id.dateName);
        description = view.findViewById(R.id.dateDescription);
        isPublic = view.findViewById(R.id.isPublic);

        Button submit = view.findViewById(R.id.submitBtn);

        //The onclick to change which picture displays.
//        picture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Add code to launch the camera to upload a picture to the image view
//            }
//        });

        if(mParam1 != null){
            dateName.setText(mParam1.getDateName());
            description.setText(mParam1.getDescription());
            if (mParam1.getPublic() == 1){
                isPublic.setChecked(true);
            }else{
                isPublic.setChecked(false);
            }

        }

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

        nextButton = (Button) view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                picture.setImageResource(R.drawable.ic_local_bar_black_24dp);
            }
        });

        previousButton = (Button) view.findViewById(R.id.previousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = 1;
                picture.setImageResource(R.drawable.ic_landscape_black_24dp);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mParam1 != null){
                    DatabaseHelper db = new DatabaseHelper(getContext());
//                    mParam1.setPicture(picture.getPicture());
                    mParam1.setDateName(dateName.getText().toString());
                    mParam1.setDescription(description.getText().toString());

                    if (isPublic.isChecked()){
                        mParam1.setPublic(1);
                    }else{
                        mParam1.setPublic(0);
                    }

                    db.updateDate(mParam1);
                    fm = getActivity().getSupportFragmentManager();
                    fm.popBackStack();
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
