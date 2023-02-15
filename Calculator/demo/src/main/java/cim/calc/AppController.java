package cim.calc;

import java.util.Arrays;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

public class AppController {
    //get all the fxml from Calc.fxml

    public void print(String str) {
        System.out.println(str);
    }

    @FXML
    private Button zeroButton;

    @FXML
    private Button oneButton;

    @FXML
    private Button twoButton;

    @FXML
    private Button threeButton;

    @FXML
    private Button fourButton;

    @FXML
    private Button fiveButton;

    @FXML
    private Button sixButton;

    @FXML
    private Button sevenButton;

    @FXML
    private Button eightButton;

    @FXML
    private Button nineButton;

    @FXML
    private Button addButton;

    @FXML
    private Button subtractButton;

    @FXML
    private Button multiplyButton;

    @FXML
    private Button divideButton;

    @FXML
    private Button equalButton;
    
    @FXML
    private TextField resultField;

    @FXML
    private TextField expressionField;

    private Operations operations = new Operations();

    
    // implement a initialize function
    @FXML
    private void initialize() {
        setButtonActions();
    }

    private void setButtonActions() {
        // add a listener to each button
        zeroButton.setOnAction(event -> {
            putButtonTextToResultField(event);
        });
        oneButton.setOnAction(event -> {
            putButtonTextToResultField(event);
        });
        twoButton.setOnAction(event -> {
            putButtonTextToResultField(event);
        });
        threeButton.setOnAction(event -> {
            putButtonTextToResultField(event);
        });
        fourButton.setOnAction(event -> {
            putButtonTextToResultField(event);
        });
        fiveButton.setOnAction(event -> {
            putButtonTextToResultField(event);
        });
        sixButton.setOnAction(event -> {
            putButtonTextToResultField(event);
        });
        sevenButton.setOnAction(event -> {
            putButtonTextToResultField(event);
        });
        eightButton.setOnAction(event -> {
            putButtonTextToResultField(event);
        });
        nineButton.setOnAction(event -> {
            putButtonTextToResultField(event);
        });
        addButton.setOnAction(event -> {
            String expression = resultField.getText();
            if (checkIfOperatorExistsAtEnd(expression)) {
                return;
            }

            if (checkIfExpressionHasTwoNumbers(expression)) {
                equalButton.getOnAction().handle(event);
            }

            putButtonTextToResultField(event);
        });
        subtractButton.setOnAction(event -> {
            String expression = resultField.getText();
            if (checkIfOperatorExistsAtEnd(expression)) {
                return;
            }

            if (checkIfExpressionHasTwoNumbers(expression)) {
                equalButton.getOnAction().handle(event);
            }

            putButtonTextToResultField(event);
        });
        multiplyButton.setOnAction(event -> {
            String expression = resultField.getText();
            if (checkIfOperatorExistsAtEnd(expression)) {
                return;
            }

            if (checkIfExpressionHasTwoNumbers(expression)) {
                equalButton.getOnAction().handle(event);
            }

            putButtonTextToResultField(event);
        });
        divideButton.setOnAction(event -> {
            String expression = resultField.getText();
            if(checkIfOperatorExistsAtEnd(expression)) {
                return;
            }

            if(checkIfExpressionHasTwoNumbers(expression)) {
                equalButton.getOnAction().handle(event);
            }

            putButtonTextToResultField(event);
        });

        equalButton.setOnAction(event -> {
            String expression = resultField.getText();
            expressionField.setText(expression);
            int result = operations.calculate(expression);
            resultField.setText(String.valueOf(result));
        });
    }

    public void putButtonTextToResultField(ActionEvent event) {
        String buttonText = ((Button) event.getSource()).getText(); // get the text from the button
        resultField.setText(String.format("%s%s", resultField.getText(), buttonText)); // put the text to the resultField
    }

    public boolean checkIfExpressionHasTwoNumbers(String expression) {
        String[] numbers = expression.split("[\\+\\-\\*\\/]");
        if(numbers.length == 1) {
            return false;
        }

        return true;
    }

    public boolean checkIfOperatorExistsAtEnd(String expression) {
        // substring starts with the given index all the way to the end of the String.
        String lastChar = expression.substring(expression.length() - 1);
        if(lastChar.matches("[\\+\\-\\*\\/\\.]")) {
            return true;
        }

        return false;
    }






}
