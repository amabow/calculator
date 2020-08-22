package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static List<String> getRpn(List<String> formula) {
        Deque<String> stack = new ArrayDeque<>();
        List<String> f = new ArrayList<>();
        String s;

        for(String token : formula){
            switch (token) {
                case "+":
                case "-":
                    while (!stack.isEmpty()) {
                        String c = stack.getFirst();
                        if (c.equals("*") || c.equals("/")) {
                            s = stack.removeFirst();
                            f.add(s);
                        } else {
                            break;
                        }
                    }
                    stack.addFirst(token);
                    break;
                case "*":
                case "/":
                case "(":
                    stack.addFirst(token);
                    break;
                case ")":
                    List<Object> list = Arrays.asList(stack.toArray());
                    int index = list.indexOf("(");

                    Deque<String> workStack = new ArrayDeque<>();
                    for (int i = 0; i <= index; i++) {
                        String c = stack.removeFirst();
                        if (!c.equals("(")) {
                            workStack.addFirst(c);
                        }
                    }

                    while (!workStack.isEmpty()) {
                        s = workStack.removeFirst();
                        f.add(s);
                    }
                    break;
                default:
                    f.add(token);
                    break;
            }
        }

        while (!stack.isEmpty()) {
            s = stack.removeFirst();
            f.add(s);
        }

        return f;
    }


    public static double calc(List<String> formula){
        // make f Reverse Polish Notation

        List<String> rpn = getRpn(formula);

        int i=0;
        double a=0, b=0;
        while(rpn.size()>1){
            switch (rpn.get(i)){
                case "+":
                    a = Double.valueOf(rpn.get(i-2));
                    b = Double.valueOf(rpn.get(i-1));
                    rpn.set(i-2, String.valueOf(a+b));
                    rpn.remove(i);
                    rpn.remove(i-1);
                    i = Math.max(i-2,0);
                    break;
                case "-":
                    a = Double.valueOf(rpn.get(i-2));
                    b = Double.valueOf(rpn.get(i-1));
                    rpn.set(i-2, String.valueOf(a-b));
                    rpn.remove(i);
                    rpn.remove(i-1);
                    i = Math.max(i-2,0);
                    break;
                case "*":
                    a = Double.valueOf(rpn.get(i-2));
                    b = Double.valueOf(rpn.get(i-1));
                    rpn.set(i-2, String.valueOf(a*b));
                    rpn.remove(i);
                    rpn.remove(i-1);
                    i = Math.max(i-2,0);
                    break;
                case "/":
                    a = Double.valueOf(rpn.get(i-2));
                    b = Double.valueOf(rpn.get(i-1));
                    rpn.set(i-2, String.valueOf(a/b));
                    rpn.remove(i);
                    rpn.remove(i-1);
                    i = Math.max(i-2,0);
                    break;
                default:
                    break;
            }
            i++;
        }
        return Double.valueOf(rpn.get(0));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button num00 = (Button) findViewById(R.id.num00);
        Button num0 = (Button) findViewById(R.id.num0);
        Button num1 = (Button) findViewById(R.id.num1);
        Button num2 = (Button) findViewById(R.id.num2);
        Button num3 = (Button) findViewById(R.id.num3);
        Button num4 = (Button) findViewById(R.id.num4);
        Button num5 = (Button) findViewById(R.id.num5);
        Button num6 = (Button) findViewById(R.id.num6);
        Button num7 = (Button) findViewById(R.id.num7);
        Button num8 = (Button) findViewById(R.id.num8);
        Button num9 = (Button) findViewById(R.id.num9);
        Button point = (Button) findViewById(R.id.point);

        num00.setOnClickListener(this);
        num0.setOnClickListener(this);
        num1.setOnClickListener(this);
        num2.setOnClickListener(this);
        num3.setOnClickListener(this);
        num4.setOnClickListener(this);
        num5.setOnClickListener(this);
        num6.setOnClickListener(this);
        num7.setOnClickListener(this);
        num8.setOnClickListener(this);
        num9.setOnClickListener(this);
        point.setOnClickListener(this);

        Button equal = (Button) findViewById(R.id.equal);
        Button clear = (Button) findViewById(R.id.C);
        Button delete = (Button) findViewById(R.id.del);
        Button parentheses_left = (Button) findViewById(R.id.parentheses_left);
        Button parentheses_right = (Button) findViewById(R.id.parentheses_right);
        Button devision = (Button) findViewById(R.id.devision);
        Button multiply = (Button) findViewById(R.id.multiply);
        Button minus = (Button) findViewById(R.id.minus);
        Button plus = (Button) findViewById(R.id.plus);

        equal.setOnClickListener(this);
        clear.setOnClickListener(this);
        delete.setOnClickListener(this);
        parentheses_left.setOnClickListener(this);
        parentheses_right.setOnClickListener(this);
        devision.setOnClickListener(this);
        multiply.setOnClickListener(this);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v != null){
            TextView history = (TextView) findViewById(R.id.history);
            TextView formula = (TextView) findViewById(R.id.formula);
            List<String> numList = new ArrayList<>();
            String f;
            switch (v.getId()) {
                case R.id.num00:
                    f = formula.getText().toString();
                    if (!f.equals("")) {
                        f = formula.getText() + "00";
                        formula.setText(f);
                    }
                    break;
                case R.id.num0:
                    f = formula.getText().toString();
                    if (!f.equals("")) {
                        f = formula.getText() + "0";
                        formula.setText(f);
                    }
                    break;
                case R.id.num1:
                    f = formula.getText() + "1";
                    formula.setText(f);
                    break;
                case R.id.num2:
                    f = formula.getText() + "2";
                    formula.setText(f);
                    break;
                case R.id.num3:
                    f = formula.getText() + "3";
                    formula.setText(f);
                    break;
                case R.id.num4:
                    f = formula.getText() + "4";
                    formula.setText(f);
                    break;
                case R.id.num5:
                    f = formula.getText() + "5";
                    formula.setText(f);
                    break;
                case R.id.num6:
                    f = formula.getText() + "6";
                    formula.setText(f);
                    break;
                case R.id.num7:
                    f = formula.getText() + "7";
                    formula.setText(f);
                    break;
                case R.id.num8:
                    f = formula.getText() + "8";
                    formula.setText(f);
                    break;
                case R.id.num9:
                    f = formula.getText() + "9";
                    formula.setText(f);
                    break;
                case R.id.point:
                    f = formula.getText() + ".";
                    formula.setText(f);
                    break;
                case R.id.del:
                    if(formula.getText().equals("")) break;
                    f = formula.getText().subSequence(0,formula.getText().length()-1).toString();
                    formula.setText(f);
                    break;
                case R.id.C:
                    f = "";
                    formula.setText(f);
                    break;
                case R.id.plus:
                    f = formula.getText().toString();
                    if(f.equals("") ||
                       f.substring(f.length()-1).equals("+") ||
                       f.substring(f.length()-1).equals("-") ||
                       f.substring(f.length()-1).equals("*") ||
                       f.substring(f.length()-1).equals("/")) break;
                    else{
                        f += "+";
                        formula.setText(f);
                        break;
                    }
                case R.id.minus:
                    f = formula.getText().toString();
                    if(f.equals("") ||
                            f.substring(f.length()-1).equals("+") ||
                            f.substring(f.length()-1).equals("-") ||
                            f.substring(f.length()-1).equals("*") ||
                            f.substring(f.length()-1).equals("/")) break;
                    else{
                        f += "-";
                        formula.setText(f);
                        break;
                    }
                case R.id.devision:
                    f = formula.getText().toString();
                    if(f.equals("") ||
                            f.substring(f.length()-1).equals("+") ||
                            f.substring(f.length()-1).equals("-") ||
                            f.substring(f.length()-1).equals("*") ||
                            f.substring(f.length()-1).equals("/")) break;
                    else{
                        f += "/";
                        formula.setText(f);
                        break;
                    }
                case R.id.multiply:
                    f = formula.getText().toString();
                    if(f.equals("") ||
                            f.substring(f.length()-1).equals("+") ||
                            f.substring(f.length()-1).equals("-") ||
                            f.substring(f.length()-1).equals("*") ||
                            f.substring(f.length()-1).equals("/")) break;
                    else{
                        f += "*";
                        formula.setText(f);
                        break;
                    }
                case R.id.parentheses_left:
                    f = formula.getText().toString();
                    if(f.equals("") ||
                            f.substring(f.length()-1).equals("+") ||
                            f.substring(f.length()-1).equals("-") ||
                            f.substring(f.length()-1).equals("*") ||
                            f.substring(f.length()-1).equals("/")){
                        f += "(";
                        formula.setText(f);
                    }
                    break;
                case R.id.parentheses_right:
                    f = formula.getText().toString();
                    if(!(f.equals("") ||
                            f.substring(f.length()-1).equals("+") ||
                            f.substring(f.length()-1).equals("-") ||
                            f.substring(f.length()-1).equals("*") ||
                            f.substring(f.length()-1).equals("/"))){
                        f += ")";
                        formula.setText(f);
                    }
                    break;
                case R.id.equal:
                    f = formula.getText().toString();

                    // parentheses check
                    int p_left= 0, p_right=0;
                    for(int i=0; i<f.length(); i++){
                        if(String.valueOf(f.charAt(i)).equals("(")) p_left++;
                        else if(String.valueOf(f.charAt(i)).equals(")")) p_right++;
                    }
                    if(p_left != p_right)break;
                    // done

                    System.out.println("f: " + f);
                    String num="";
                    List<String> list = new ArrayList<>();
                    for(int i=0; i<f.length(); i++){
                        String s = String.valueOf(f.charAt(i));
                        System.out.println("s: " + s);
                        if(s.equals("+")||s.equals("-")||s.equals("*")||s.equals("/")||s.equals("(")||s.equals(")")){
                            System.out.println("num: " + num);
                            if(!num.equals("")) list.add(num);
                            list.add(s);
                            num = "";
                        }
                        else num += s;
                    }
                    if(!num.equals("")) list.add(num);
                    System.out.println("List: " + list);
                    history.setText(f + " = " + String.valueOf(calc(list)));
                    formula.setText("");
                    break;
                default:
                    break;
            }
        }
    }

}
