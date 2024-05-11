package com.example.cet3013_assignment1_calculatorapp;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class calculatorViewModel extends ViewModel {
    private String progressExpression = "";
    private String result;
    //Create and initialize the LiveData with String
    private MutableLiveData<String> liveExpression = new MutableLiveData<>();
    private MutableLiveData<String> liveResult = new MutableLiveData<>();

    /**
     *setProgressExpression() - mutator method to change the string of expression and the LiveData expression
     *@param expression the string of expression that entered by user
     */
    public void setProgressExpression(String expression) {
        this.progressExpression = expression;
        liveExpression.setValue(this.getProgressExpression());
    }
    /**
     *setResult() - mutator method to change the result and the LiveData result
     *@param result the result of the expression
     */
    public void setResult(String result){
        this.result = result;
        liveResult.setValue(this.getResult());
    }
    /**
     *addProgressExpression() - mutator method to add a numerical digit or any arithmetic operator symbol to the expression
     *@param mathematical_character the numerical digit or any arithmetic operator symbol
     */
    public void addProgressExpression(String mathematical_character) {
        this.progressExpression += mathematical_character;
        liveExpression.setValue(this.getProgressExpression());
    }
    /**
     *getProgressExpression() - accessor method to get the string of expression that entered by user
     *@return String This return the string of expression that entered by user
     */
    public String getProgressExpression() {
        return this.progressExpression;
    }
    /**
     *getResult() - accessor method to get the result of the expression
     *@return String This return the result of the expression
     */
    public String getResult(){
        return this.result;
    }
    /**
     *Function to get the error message
     *@return String This return the "Error" message
     */
    public String getErrorMessage(){
        return "Syntax Error";
    }
    /**
     *Function to get the object of LiveData
     *@return LiveData object This return the object of the LiveData
     */
    public MutableLiveData<String> getCurrentProgressExpression() {
        return liveExpression;
    }
    /**
     *Function to get the object of LiveData
     *@return LiveData object This return the object of the LiveData
     */
    public MutableLiveData<String> getCurrentResult() {
        return liveResult;
    }
}//End of class