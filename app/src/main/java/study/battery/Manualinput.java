package study.battery;


import android.app.Fragment;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Manualinput extends Fragment{
    public final static String TAG = "Fragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment,null);
        EditText editText = view.findViewById(R.id.etValueBat);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        return view;
    }

}
