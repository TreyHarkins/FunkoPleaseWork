package com.example.funko;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends Activity {
    Switch onOrOffSwitch;
    Button insertButton;
    Button queryButton;
    Button updateButton;
    Button deleteButton;
    Button nextButton;
    Button previousButton;
    EditText popName;
    EditText popType;
    EditText pop_ID;
    EditText popNum;
    EditText popFandom;
    RadioButton popOn;
    EditText popUltimate;
    EditText popPrice;
    TextView IDText;
    TextView NameText;
    TextView NumText;
    TextView TypeText;
    TextView TypeFandomText;
    TextView onText;
    TextView ultimateText;
    TextView priceText;

    Cursor mCursor;

    //Listeners

    View.OnClickListener updateListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ContentValues mUpdateValues = new ContentValues();

            mUpdateValues.put(FunkoPops.COLUMN_POPNAME, popName.getText().toString().trim());
            mUpdateValues.put(FunkoPops.COLUMN_POPTYPE, popType.getText().toString().trim());
            mUpdateValues.put(FunkoPops.COLUMN_POPPRICE, popPrice.getText().toString().trim());
            mUpdateValues.put(FunkoPops.COLUMN_POPFANDOM, popFandom.getText().toString().trim());
            mUpdateValues.put(FunkoPops.COLUMN_POPULTIMATE, popUltimate.getText().toString().trim());
            mUpdateValues.put(FunkoPops.COLUMN_POPNUM, popNum.getText().toString().trim());
            if(onOrOffSwitch.isChecked()){
                mUpdateValues.put(FunkoPops.COLUMN_POPON, true);
            }
            else{
                mUpdateValues.put(FunkoPops.COLUMN_POPON, false);
            }


            String mSelectionClause = FunkoPops.COLUMN_POPNAME + " = ? AND "
                + FunkoPops.COLUMN_POPTYPE + " = ? AND "
                     + FunkoPops.COLUMN_POPNUM + " = ? AND "
                     + FunkoPops.COLUMN_POPFANDOM + " = ? AND "
                    + FunkoPops.COLUMN_POPON+ " = ? AND "
                     + FunkoPops.COLUMN_POPULTIMATE+ " = ? AND "
                     + FunkoPops.COLUMN_POPPRICE + " = ? ";

            String[] mSelectionArgs = { NameText.getText().toString().trim(), TypeText.getText().toString().trim(), NumText.getText().toString().trim(),
            TypeFandomText.getText().toString().trim(), ultimateText.getText().toString().trim(), priceText.getText().toString().trim(), onText.getText().toString().trim()};

            int numRowsUpdates= getContentResolver().update(FunkoPops.CONTENT_URI, mUpdateValues,
                    mSelectionClause, mSelectionArgs);

            clear();
        }
    };


    View.OnClickListener deleteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String mSelectionClause = FunkoPops.COLUMN_POPNAME + " = ? " + " AND " +
                    FunkoPops.COLUMN_POPTYPE + " = ? " +
                    "AND " + FunkoPops.COLUMN_POPFANDOM + " = ? " +
                    "AND " + FunkoPops.COLUMN_POPULTIMATE + " = ? " +
                    "AND " + FunkoPops.COLUMN_POPON + " = ? " +
                    "AND " + FunkoPops.COLUMN_POPPRICE + " = ? " +
                    "AND " + FunkoPops.COLUMN_POPNUM + " = ? ";

            String[] mSelectionArgs = { NameText.getText().toString().trim(),
                    TypeText.getText().toString().trim(), NumText.getText().toString().trim(), TypeFandomText.getText().toString().trim(), ultimateText.getText().toString().trim(), priceText.getText().toString().trim(), onText.getText().toString().trim()};

            int mRowsDeleted = getContentResolver().delete(FunkoPops.CONTENT_URI, mSelectionClause,
                    mSelectionArgs);

            clear();
        }
    };

    View.OnClickListener insertListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            ContentValues mNewValues = new ContentValues();

            mNewValues.put(FunkoPops.COLUMN_POPNAME, popName.getText().toString().trim());
            mNewValues.put(FunkoPops.COLUMN_POPTYPE, popType.getText().toString().trim());
            mNewValues.put(FunkoPops.COLUMN_POPNUM, popNum.getText().toString().trim());
            mNewValues.put(FunkoPops.COLUMN_POPFANDOM, popFandom.getText().toString().trim());
            mNewValues.put(FunkoPops.COLUMN_POPPRICE, popPrice.getText().toString().trim());
            if(onOrOffSwitch.isChecked()){
                mNewValues.put(FunkoPops.COLUMN_POPON, true);
            }
            else{
                mNewValues.put(FunkoPops.COLUMN_POPON, false);
            }

            mNewValues.put(FunkoPops.COLUMN_POPULTIMATE, popUltimate.getText().toString().trim());

            getContentResolver().insert(FunkoPops.CONTENT_URI, mNewValues);

            clear();
        }
    };

    View.OnClickListener queryListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mCursor = getContentResolver().query(FunkoPops.CONTENT_URI, null, null, null, null);

            if (mCursor != null) {
                if (mCursor.getCount() > 0) {
                    mCursor.moveToNext();
                    setViews();
                }
            }
        }
    };

    View.OnClickListener previousListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mCursor != null) {
                if (!mCursor.moveToPrevious()) {
                    mCursor.moveToLast();
                }

                setViews();
            }
        }
    };

    View.OnClickListener nextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mCursor != null) {
                if (!mCursor.moveToNext()) {
                    mCursor.moveToFirst();
                }
                setViews();
            }
        }
    };

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        popName = findViewById(R.id.editPopName);
        popType = findViewById(R.id.editPopType);
        pop_ID = findViewById(R.id.editPopID);
        popNum = findViewById(R.id.editPopNumber);
        popFandom = findViewById(R.id.editFandom);
        popUltimate= findViewById(R.id.editPopUltimate);
        popPrice = findViewById(R.id.editPopPrice);

        insertButton = findViewById(R.id.buttonInsert);
        queryButton = findViewById(R.id.ButtonQuery);
        updateButton = findViewById(R.id.buttonUpdate);
        deleteButton = findViewById(R.id.buttonDelete);

        IDText = findViewById(R.id.TextPopID);
        NameText = findViewById(R.id.textPopName);
        NumText = findViewById(R.id.textPopNumber);
        TypeText = findViewById(R.id.textPopType);
        TypeFandomText = findViewById(R.id.textPopFandom);
        onText = findViewById(R.id.textOnPop);
        ultimateText = findViewById(R.id.textPopUltimate);
        priceText = findViewById(R.id.textPopPrice);
        onOrOffSwitch = findViewById(R.id.OnOrOff);

        if (onOrOffSwitch.isChecked()) {
            onText.setText("On");
        } else {
            onText.setText("Off");
        }


        nextButton = findViewById(R.id.buttonRight);
        previousButton = findViewById(R.id.buttonLeft);

        insertButton.setOnClickListener(insertListener);

        updateButton.setOnClickListener(updateListener);

        deleteButton.setOnClickListener(deleteListener);

        queryButton.setOnClickListener(queryListener);

        previousButton.setOnClickListener(previousListener);

        nextButton.setOnClickListener(nextListener);
    }

    private void setViews() {
        IDText.setText(mCursor.getString(0));
        String text1 = mCursor.getString(1) + " ";
        NameText.setText(text1);
        TypeText.setText(mCursor.getString(2));
        NumText.setText(mCursor.getString(3));
        TypeFandomText.setText(mCursor.getString(4));
        ultimateText.setText(mCursor.getString(5));
        priceText.setText(mCursor.getString(6));
        onText.setText(mCursor.getString(7));
    }

    private void clear() {
        IDText.setText("");
        NameText.setText("");

        TypeText.setText("");
        NumText.setText("");
        TypeFandomText.setText("");
        ultimateText.setText("");
        priceText.setText("");
        onText.setText("");

        mCursor = null;
    }
}