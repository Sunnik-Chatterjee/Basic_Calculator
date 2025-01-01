package com.example.basiccalculator;
import java.util.Stack;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result,solution;
    InfixToPostFix solver;
    MaterialButton bC,bAC,b1,b0,b3,b2,b4,b5,b6,b7,b8,b9,bAdd,bSub,bDiv,bEqual,bMul,bB1,bB2,bDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        solution=findViewById(R.id.solution);
        assignId(bC,R.id.buttonC);
        assignId(b1,R.id.button_1);
        assignId(b0,R.id.button_0);
        assignId(b2,R.id.button_2);
        assignId(b3,R.id.button_3);
        assignId(b4,R.id.button_4);
        assignId(b5,R.id.button_5);
        assignId(b6,R.id.button_6);
        assignId(b7,R.id.button_7);
        assignId(b8,R.id.button_8);
        assignId(b9,R.id.button_9);
        assignId(bAC,R.id.button_AC);
        assignId(bAdd,R.id.button_add);
        assignId(bSub,R.id.button_sub);
        assignId(bMul,R.id.button_mul);
        assignId(bDiv,R.id.buttondiv);
        assignId(bDot,R.id.button_dot);
        assignId(bB1,R.id.buttonb1);
        assignId(bB2,R.id.buttonb2);
        assignId(bEqual,R.id.button_eq);

        solver=new InfixToPostFix();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        MaterialButton button=(MaterialButton) v;
        String btnString=button.getText().toString();
        String res;
        String dataToCalculate=solution.getText().toString();
        switch (btnString) {
            case "AC":
                solution.setText("");
                result.setText("");
                break;
            case "C":
                if(!dataToCalculate.isEmpty()) {
                    dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
                    solution.setText(dataToCalculate);
                }
                break;
            case "=":
                try {
                    res = solver.InToPost(dataToCalculate);
                    solution.setText("");
                    result.setText(solver.evaluatePostfix(res));
                }catch (Exception e){
                    result.setText("Invalid Expression");
                }
                break;
            default:
                dataToCalculate=dataToCalculate+btnString;
                solution.setText(dataToCalculate);
        }

    }
}