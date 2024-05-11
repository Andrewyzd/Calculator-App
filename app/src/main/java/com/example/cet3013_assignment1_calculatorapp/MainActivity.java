package com.example.cet3013_assignment1_calculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // Declare and initialize the variable
    private TextView expressionTextView,resultORerrorTextView;
    private Button clearButton,backspaceButton,positive_negative_Button,divideButton,multiButton,minusButton,plusButton,equalButton,decimalButton,
                   button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    private static int index = -1;
    private static char character;
    private static ArrayList<Double> array = new ArrayList<Double>();
    private calculatorViewModel viewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //To set up the linking to the view model
        viewModel = new ViewModelProvider(this).get(calculatorViewModel.class);
        //set references to the view
        expressionTextView = findViewById(R.id.expression);
        resultORerrorTextView = findViewById(R.id.resultORexpression);
        //set references to the operational button
        clearButton = findViewById(R.id.clearButton);
        backspaceButton = findViewById(R.id.backspaceButton);
        positive_negative_Button = findViewById(R.id.negative_positive_button);
        divideButton = findViewById(R.id.divideButton);
        multiButton = findViewById(R.id.multiplyButton);
        minusButton = findViewById(R.id.minusButton);
        plusButton = findViewById(R.id.plusButton);
        equalButton = findViewById(R.id.equalButton);
        decimalButton = findViewById(R.id.decimalButton);
        //set references to the Numerical button
        button0 = findViewById(R.id.zeroButton);
        button1 = findViewById(R.id.oneButton);
        button2 = findViewById(R.id.twoButton);
        button3 = findViewById(R.id.threeButton);
        button4 = findViewById(R.id.fourButton);
        button5 = findViewById(R.id.fiveButton);
        button6 = findViewById(R.id.sixButton);
        button7 = findViewById(R.id.sevenButton);
        button8 = findViewById(R.id.eightButton);
        button9 = findViewById(R.id.nineButton);

        final LiveData<String> liveResult = viewModel.getCurrentResult();
        final LiveData<String> liveExpression = viewModel.getCurrentProgressExpression();

        //set listener to the clear button
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setProgressExpression("");//set the expression to null
                viewModel.setResult("");//set the result to null
                index = -1; array.clear();//set the index to -1 and clear the data in array
            }
        });
        //set listener to the backspace button
        backspaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");//clear the data on text view
                int lastStringIndex = viewModel.getProgressExpression().length();// access the length of the string of expression
                if(lastStringIndex-- > 0){//if the length is greater than zero
                        viewModel.setProgressExpression((String) expressionTextView.getText());//set the string of expression
                        array.clear(); index = -1;//set the index to -1 and clear the data in array
                        viewModel.setProgressExpression(viewModel.getProgressExpression().substring(0, lastStringIndex));//set the string of expression with its substring
                }else resultORerrorTextView.setText(viewModel.getErrorMessage());//else display error message
            }
        });
        //set listener to the divide button
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                String progressExpression = viewModel.getProgressExpression();//access the string of expression
                //add "/" to the expression if the expression is not null and the last character is not any arithmetic symbol
                if (!progressExpression.equals("") && (progressExpression.charAt(progressExpression.length() - 1) != '+'
                            && progressExpression.charAt(progressExpression.length() - 1) != '-'
                            && progressExpression.charAt(progressExpression.length() - 1) != 'X'
                            && progressExpression.charAt(progressExpression.length() - 1) != '/'
                            && progressExpression.charAt(progressExpression.length() - 1) != '.'
                            && progressExpression.charAt(progressExpression.length() - 1) != '(')) {
                        viewModel.addProgressExpression("/");
                } else resultORerrorTextView.setText(viewModel.getErrorMessage());//else display error message
            }
        });
        //set listener to the multiply button
        multiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                String progressExpression = viewModel.getProgressExpression();//access the string of expression
                //add "X" to the expression if the expression is not null and the last character is not any arithmetic symbol
                if(!progressExpression.equals("") && (progressExpression.charAt(progressExpression.length()-1) != '+'
                        && progressExpression.charAt(progressExpression.length()-1) != '-'
                        && progressExpression.charAt(progressExpression.length()-1) != 'X'
                        && progressExpression.charAt(progressExpression.length()-1) != '/'
                        && progressExpression.charAt(progressExpression.length()-1) != '.'
                        && progressExpression.charAt(progressExpression.length()-1) != '(')){
                    viewModel.addProgressExpression("X");
                }else resultORerrorTextView.setText(viewModel.getErrorMessage());//else display error message
            }
        });
        //set listener to the minus button
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                String progressExpression = viewModel.getProgressExpression();//access the string of expression
                //add "-" to the expression if the expression is not null and the last character is not any arithmetic symbol
                if(progressExpression.equals("")||(!progressExpression.equals("")
                        && (progressExpression.charAt(progressExpression.length()-1) != '+'
                        && progressExpression.charAt(progressExpression.length()-1) != '-'
                        && progressExpression.charAt(progressExpression.length()-1) != 'X'
                        && progressExpression.charAt(progressExpression.length()-1) != '/'
                        && progressExpression.charAt(progressExpression.length()-1) != '.'
                        && progressExpression.charAt(progressExpression.length()-1) != '('))){
                    viewModel.addProgressExpression("-");
                }else resultORerrorTextView.setText(viewModel.getErrorMessage());//else display error message
            }
        });
        //set listener to the plus button
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                String progressExpression = viewModel.getProgressExpression();//access the string of expression
                //add "+" to the expression if the expression is not null and the last character is not any arithmetic symbol
                if(!progressExpression.equals("") && (progressExpression.charAt(progressExpression.length()-1) != '+'
                        && progressExpression.charAt(progressExpression.length()-1) != '-'
                        && progressExpression.charAt(progressExpression.length()-1) != 'X'
                        && progressExpression.charAt(progressExpression.length()-1) != '/'
                        && progressExpression.charAt(progressExpression.length()-1) != '.'
                        && progressExpression.charAt(progressExpression.length()-1) != '(')){
                    viewModel.addProgressExpression("+");
                }else resultORerrorTextView.setText(viewModel.getErrorMessage());//else display error message
            }
        });
        //set listener to the decimal button
        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                if(!viewModel.getProgressExpression().equals("")) {//if the expression is not null
                    int expressionLength = viewModel.getProgressExpression().length()-1;//access the length of the string of expression
                    char character = viewModel.getProgressExpression().charAt(viewModel.getProgressExpression().length() - 1);//access the last character of the string of expression
                    boolean haveDecimal = false;//initialize the haveDecimal to false
                    String progressExpression = viewModel.getProgressExpression();
                    //loop through the string of expression when the character of the string is numerical digit or is decimal point
                    while((progressExpression.charAt(expressionLength)>='0' && progressExpression.charAt(expressionLength)<='9') || progressExpression.charAt(expressionLength) == '.'){
                        if(progressExpression.charAt(expressionLength) == '.') haveDecimal = true;//set the haveDecimal to true if the character is decimal
                        expressionLength--;//decrease the length of the string of expression by 1
                        if(expressionLength < 0) break;// stop when the length is smaller than 0
                    }
                    //add decimal point to the expression if there is not decimal point and the last character is numerical number
                    if (!haveDecimal && (character >= '0' && character <= '9')) {
                        viewModel.addProgressExpression(".");
                    }else resultORerrorTextView.setText(viewModel.getErrorMessage());//else display error message
                }else resultORerrorTextView.setText(viewModel.getErrorMessage());//else display error message
            }
        });
        //set listener to the button 0
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                char character;//declare the variable
                //if the expression is not null
                if(!viewModel.getProgressExpression().equals("")){
                    character = viewModel.getProgressExpression().charAt(viewModel.getProgressExpression().length() - 1);//access the last character of the string of expression
                    //add the digit "0" to the expression if the last character is not any arithmetic symbol but a numerical number
                    if(character=='+'|| character=='-' || character=='X' ||character=='/'||character== '.'||(character>='0'&& character<='9')) viewModel.addProgressExpression("0");
                }
                else viewModel.addProgressExpression("0");//start the expression with "0"
            }
        });
        //set listener to the button 1
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                char character;//declare the variable
                //if the expression is not null
                if(!viewModel.getProgressExpression().equals("")){
                    character = viewModel.getProgressExpression().charAt(viewModel.getProgressExpression().length() - 1);//access the last character of the string of expression
                    //add the digit "1" to the expression if the last character is not any arithmetic symbol but a numerical number
                    if(character=='+'|| character=='-' || character=='X' ||character=='/'||character== '.'||(character>='0'&& character<='9')) viewModel.addProgressExpression("1");
                }
                else viewModel.addProgressExpression("1");//start the expression with "1"
            }
        });
        //set listener to the button 2
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                char character;//declare the variable
                //if the expression is not null
                if(!viewModel.getProgressExpression().equals("")){
                    character = viewModel.getProgressExpression().charAt(viewModel.getProgressExpression().length() - 1);//access the last character of the string of expression
                    //add the digit "2" to the expression if the last character is not any arithmetic symbol but a numerical number
                    if(character=='+'|| character=='-' || character=='X' ||character=='/'||character== '.'||(character>='0'&& character<='9')) viewModel.addProgressExpression("2");
                }
                else viewModel.addProgressExpression("2");//start the expression with "2"
            }
        });
        //set listener to the button 3
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                char character;//declare the variable
                //if the expression is not null
                if(!viewModel.getProgressExpression().equals("")){
                    character = viewModel.getProgressExpression().charAt(viewModel.getProgressExpression().length() - 1);//access the last character of the string of expression
                    //add the digit "3" to the expression if the last character is not any arithmetic symbol but a numerical number
                    if(character=='+'|| character=='-' || character=='X' ||character=='/'||character== '.'||(character>='0'&& character<='9')) viewModel.addProgressExpression("3");
                }
                else viewModel.addProgressExpression("3");//start the expression with "3"
            }
        });
        //set listener to the button 4
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                char character;//declare the variable
                //if the expression is not null
                if(!viewModel.getProgressExpression().equals("")){
                    character = viewModel.getProgressExpression().charAt(viewModel.getProgressExpression().length() - 1);//access the last character of the string of expression
                    //add the digit "4" to the expression if the last character is not any arithmetic symbol but a numerical number
                    if(character=='+'|| character=='-' || character=='X' ||character=='/'||character== '.'||(character>='0'&& character<='9')) viewModel.addProgressExpression("4");
                }
                else viewModel.addProgressExpression("4");//start the expression with "4"
            }
        });
        //set listener to the button 5
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                char character;//declare the variable
                //if the expression is not null
                if(!viewModel.getProgressExpression().equals("")){
                    character = viewModel.getProgressExpression().charAt(viewModel.getProgressExpression().length() - 1);//access the last character of the string of expression
                    //add the digit "5" to the expression if the last character is not any arithmetic symbol but a numerical number
                    if(character=='+'|| character=='-' || character=='X' ||character=='/'||character== '.'||(character>='0'&& character<='9')) viewModel.addProgressExpression("5");
                }
                else viewModel.addProgressExpression("5");//start the expression with "5"
            }
        });
        //set listener to the button 6
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                char character;//declare the variable
                //if the expression is not null
                if(!viewModel.getProgressExpression().equals("")){
                    character = viewModel.getProgressExpression().charAt(viewModel.getProgressExpression().length() - 1);//access the last character of the string of expression
                    //add the digit "6" to the expression if the last character is not any arithmetic symbol but a numerical number
                    if(character=='+'|| character=='-' || character=='X' ||character=='/'||character== '.'||(character>='0'&& character<='9')) viewModel.addProgressExpression("6");
                }
                else viewModel.addProgressExpression("6");//start the expression with "6"
            }
        });
        //set listener to the button 7
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                char character;//declare the variable
                //if the expression is not null
                if(!viewModel.getProgressExpression().equals("")){
                    character = viewModel.getProgressExpression().charAt(viewModel.getProgressExpression().length() - 1);//access the last character of the string of expression
                    //add the digit "7" to the expression if the last character is not any arithmetic symbol but a numerical number
                    if(character=='+'|| character=='-' || character=='X' ||character=='/'||character== '.'||(character>='0'&& character<='9')) viewModel.addProgressExpression("7");
                }
                else viewModel.addProgressExpression("7");//start the expression with "7"
            }
        });
        //set listener to the button 8
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                char character;//declare the variable
                //if the expression is not null
                if(!viewModel.getProgressExpression().equals("")){
                    character = viewModel.getProgressExpression().charAt(viewModel.getProgressExpression().length() - 1);//access the last character of the string of expression
                    //add the digit "8" to the expression if the last character is not any arithmetic symbol but a numerical number
                    if(character=='+'|| character=='-' || character=='X' ||character=='/'||character== '.'||(character>='0'&& character<='9')) viewModel.addProgressExpression("8");
                }
                else viewModel.addProgressExpression("8");//start the expression with "8"
            }
        });
        //set listener to the button 9
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                char character;//declare the variable
                //if the expression is not null
                if(!viewModel.getProgressExpression().equals("")){
                    character = viewModel.getProgressExpression().charAt(viewModel.getProgressExpression().length() - 1);//access the last character of the string of expression
                    //add the digit "9" to the expression if the last character is not any arithmetic symbol but a numerical number
                    if(character=='+'|| character=='-' || character=='X' ||character=='/'||character== '.' ||(character>='0'&& character<='9')) viewModel.addProgressExpression("9");
                }
                else viewModel.addProgressExpression("9");//start the expression with "9"
            }
        });
        //set listener to the positive and negative conversion button
        positive_negative_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
                String progressExpression = viewModel.getProgressExpression();//declare and assign the string of expression to the variable
                if(progressExpression.equals(""))resultORerrorTextView.setText(viewModel.getErrorMessage());//display error message if no expression
                //convert the number to negative or positive
                else if(!progressExpression.equals("") ||(progressExpression.charAt(0)>='0' && progressExpression.charAt(0)<='9')) {
                    progressExpression = PositiveNegativeConversion(viewModel.getProgressExpression().length(),viewModel.getProgressExpression());
                    viewModel.setProgressExpression(progressExpression);
                }
            }
        });
        //set listener to the equal button
        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultORerrorTextView.setText("");// clear the data on text view
             if(!viewModel.getProgressExpression().equals("")){
                 char lastCharacter = viewModel.getProgressExpression().charAt(viewModel.getProgressExpression().length()-1);
                 if((lastCharacter>='0'&&lastCharacter<='9')|| lastCharacter==')') {
                     index = -1;//set the index to -1
                     array.clear();//clear all th data in array
                     viewModel.setResult(Double.toString(CalculateResult(viewModel.getProgressExpression())));//calculate and set the result
                 }else resultORerrorTextView.setText(viewModel.getErrorMessage());//display error message if the last character is not a number
             }else resultORerrorTextView.setText(viewModel.getErrorMessage());//display error message if no expression
            }
        });

        //Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        liveExpression.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String expression) {
                expressionTextView.setText(expression);
            }
        });
        //Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        liveResult.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String result) {
                resultORerrorTextView.setText(result);
            }
        });

    }//End of onCreate
    /**
     *Function to convert the current number to negative or positive
     *@param expressionLength is the length of the string of user entered expression
     *@return String This return the updated expression with converted position or negative value
     */
    public static String PositiveNegativeConversion(int expressionLength, String progressExpression){
        String numericalStringConversion = "";//declare the variable
        expressionLength--;//decrease the length by 1
        //convert to positive if the last character is equal to ')'
        if(progressExpression.charAt(expressionLength) == ')'){
            String numberString = "";
            expressionLength--;//decrease the length by 1
            //stop loop when the negative sign is detected
            while(progressExpression.charAt(expressionLength)!='-'){
                numberString += progressExpression.charAt(expressionLength);
                expressionLength--;
            }
            numberString = Reserve(numberString);//reserve the string of number into correct order
            progressExpression = progressExpression.substring(0,expressionLength-1) + numberString;//update the expression
        }
        //convert to negative if the last character is numerical number
        else if(progressExpression.charAt(expressionLength)>='0' && progressExpression.charAt(expressionLength)<='9'){
            //continue when numerical character or decimal point is detected
            while((progressExpression.charAt(expressionLength)>='0' && progressExpression.charAt(expressionLength)<='9') || progressExpression.charAt(expressionLength) == '.'){
                numericalStringConversion += progressExpression.charAt(expressionLength);
                expressionLength--;//decrease the length by 1
                if(expressionLength < 0) break;//stop if the length is equal to zero
            }
            numericalStringConversion = Reserve(numericalStringConversion);//reserve the string of number into correct order
            if(expressionLength == 0 && progressExpression.charAt(0) == '-')progressExpression = numericalStringConversion;
            else if(expressionLength < 0)progressExpression = "-" + numericalStringConversion;//update the expression with '-' in front
            else progressExpression = progressExpression.substring(0, expressionLength +1) + "(-" + numericalStringConversion +")";//update the expression
        }
        return progressExpression;
    }
    /**
     *Function to reverse back the current number in correct sequence
     *@param numericalSubstring is the substring for negative conversion
     *@return numberString This return the converted substring in sequence order
     */
    public static String Reserve(String numericalSubstring){
        String numberString = "";
        for(int i = numericalSubstring.length()-1; i>=0 ; i--){
            numberString += numericalSubstring.charAt(i);
        }
        return numberString;
    }
    /**
     *Function to calculate the result of the overall expression
     *@param progressExpression is the string expression that user entered
     *@return result This return the result of the overall mathematical expression
     */
    public static double CalculateResult(String progressExpression){
        BigDecimal plus, minus;//declare big decimal class
        IncreaseIndex(progressExpression);//function call to decrease the index number
        double number = MultiplyAndDivide(progressExpression);//function call to search for the multiply and divide operation
        do{
            //call the MultiplyAndDivide function repeatedly if 'X' or '-' is detected, storing the number into ArrayList if not
            if(character == 'X') number *= MultiplyAndDivide(progressExpression);
            else if(character == '/') number /= MultiplyAndDivide(progressExpression);
            else{
                array.add(number);
                number = MultiplyAndDivide(progressExpression);
            }
        }while(index < progressExpression.length());
        array.add(number);
        index = -1;//reset index to -1
        IncreaseIndex(progressExpression);//function call to decrease the index number
        double result = array.get(0);//assign the first data in the list to the variable
        for(int i = 1; i<array.size();i++){
            plusAndMinus(progressExpression);//call the plusAndMinus function to perform addition and subtraction accordingly
            if(character == '+') {
                plus = new BigDecimal(Double.toString(result)).add(new BigDecimal(Double.toString(array.get(i))));
                result = plus.doubleValue();
            }
            else if(character == '-') {
                minus = new BigDecimal(Double.toString(result)).subtract(new BigDecimal(Double.toString(array.get(i))));
                result = minus.doubleValue();
            }
        }
        return result;
    }
    /**
     *Function to perform addition and subtraction
     */
    public static void plusAndMinus(String progressExpression){
        IncreaseIndex(progressExpression);//function call to reduce the index
        if(character == '('){
            IncreaseIndex(progressExpression); IncreaseIndex(progressExpression);
        }
        //access to the character when repeatedly when the '+' or '-' is not detected
        while(character != '+' && character != '-' && index < progressExpression.length()) IncreaseIndex(progressExpression);
    }
    /**
     *Function to increase the index and assign the character of the string expression at the particular index
     *@param progressExpression is the string expression that user entered
     */
    public static void IncreaseIndex(String progressExpression){
        if(++index < progressExpression.length()) character = progressExpression.charAt(index);
        else character = ' ';
    }
    /**
     *Function to perform multiply and division
     *@param progressExpression is the string expression that user entered
     *@return digit This return a sequence of number between two arithmetic operators
     */
    public static double MultiplyAndDivide(String progressExpression){
        double digit = 0.0;
        boolean isNegative = false;
        if(index == 0 && character == '-') isNegative = true;//set isNegative to true if '-' is detected
        if(character == '+' || character == '-' || character =='X'||character=='/') {
            IncreaseIndex(progressExpression);//function call to decrease the index
        }
        if(character == '('){
            //function call to decrease the index
            IncreaseIndex(progressExpression);
            IncreaseIndex(progressExpression);
            isNegative = true;//set isNegative to true
        }
        int startPoint = index;//assign current index to variable
        if((character >= '0' && character <='9') || character == '.') {
            //decrease the index when the number and decimal point is detected
            while((character >= '0' && character <='9') || character == '.') IncreaseIndex(progressExpression);
            digit = Double.parseDouble(progressExpression.substring(startPoint,index));//convert to double
            if(isNegative) digit=-digit;//set to negative value if isNegative is true
            if(character ==')') IncreaseIndex(progressExpression);//function call to decrease the index when ')' is detected
        }
        return digit;
    }
}//End of class
