package com.justinmaure.datenight;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateDateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateDateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateDateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView picture;
    private EditText dateName;
    private EditText description;
    private Switch isPublic;
    private int isPublicNum = 0;
    private Button submitBtn;

    private OnFragmentInteractionListener mListener;

    public CreateDateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateDateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateDateFragment newInstance(String param1, String param2) {
        CreateDateFragment fragment = new CreateDateFragment();
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
        View view = inflater.inflate(R.layout.fragment_create_date, container, false);

//        picture = (ImageView) view.findViewById(R.id.picture);
//        picture.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //Add code to launch the camera to upload a picture to the image view
//            }
//        });

        dateName = (EditText) view.findViewById(R.id.dateName);

        description = (EditText) view.findViewById(R.id.description);

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
                //Add code to grab all variables and make a new date in the date table
                DatabaseHelper db = new DatabaseHelper(getContext());
                db.addDate(new Date(0, dateName.getText().toString(),description.getText().toString(), picture.toString(),isPublicNum, 0, MainActivity.currentUser.getUsername(), 0));
                db.close();
                dateName.setText("");
                description.setText("");
                picture.setImageResource(R.drawable.ic_launcher_background);
                isPublic.setChecked(false);
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
