package com.grayraven.ectest5;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import PoJos.SplitVoteResultMsg;

/**
 * Created by Jim on 6/20/2016.
 */
public class SplitVoteDlg extends DialogFragment {

    private static final String TAG = "VoteDlg";
    protected int mDemvotes;
    protected int mRepvotes;
    protected int mMaxvotes;
    protected EditText mDemEdit;
    protected EditText mRepEdit;
    protected String mState;

    public SplitVoteDlg(){};

    public static SplitVoteDlg newInstance(String title, int maxVotes, String state){
        SplitVoteDlg fragment = new SplitVoteDlg();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putInt("max_votes",maxVotes);
        args.putString("state", state);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.vote_split_dlg, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String title = getArguments().getString("title");
        mMaxvotes = getArguments().getInt("max_votes");
        mState = getArguments().getString("state");

        ((TextView) view.findViewById(R.id.dlgTitle)).setText(title);
        mDemEdit = (EditText) view.findViewById(R.id.dem_votes);
        mRepEdit = (EditText) view.findViewById(R.id.rep_votes);

        //handle soft enter
        mRepEdit.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP) {
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_DPAD_CENTER:
                        case KeyEvent.KEYCODE_ENTER:
                            normalClose();
                            return true;
                        default:
                            break;
                    }
                }
                return false;
            }
        });


        Button btnSave = (Button) view.findViewById(R.id.dlg_ok);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                normalClose();
            }
        });

        Button btnCancel = (Button) view.findViewById(R.id.dlg_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                dismiss();
            }
        });
    }

    void normalClose() {
        mDemvotes= Integer.parseInt(String.valueOf(mDemEdit.getText()));
        mRepvotes = Integer.parseInt(String.valueOf(mRepEdit.getText()));

        if(mDemvotes + mRepvotes != mMaxvotes) {
            Toast.makeText(getActivity(),getString(R.string.too_many_votes), Toast.LENGTH_LONG).show();
            return;
        }
        Log.d(TAG, "on ok");
        EventBus.getDefault().post(new SplitVoteResultMsg(mState, mDemvotes, mRepvotes));
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dismiss();

    }

}


