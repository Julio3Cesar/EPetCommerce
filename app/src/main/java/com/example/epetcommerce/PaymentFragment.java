package com.example.epetcommerce;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.epetcommerce.database.ProductData;


/**
 * A simple {@link Fragment} subclass.
 */
public class PaymentFragment extends Fragment {
    private Button btnFinalize;
    private ImageView imgCreditCard;
    private RadioGroup radioGroupPayment;
    private RadioButton checkBill;
    private RadioButton checkCreditCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment, container, false);

        imgCreditCard = view.findViewById(R.id.imgCreditCard);

        checkBill = view.findViewById(R.id.checkBill);
        checkCreditCard = view.findViewById(R.id.checkCreditCard);
        checkCreditCard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    imgCreditCard.setVisibility(View.VISIBLE);
                }else{
                    imgCreditCard.setVisibility(View.INVISIBLE);
                }
            }
        });

        btnFinalize = view.findViewById(R.id.btnFinalize);
        btnFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductData productData = ProductData.getInstance();
                productData.getProducts().clear();

                FinalizedFragment finalizedFragment = new FinalizedFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, finalizedFragment).commit();
            }
        });

        return view;
    }

}

