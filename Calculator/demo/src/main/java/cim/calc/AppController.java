package cim.calc;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;

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

    public EventHandler<KeyEvent> setKeyActions() {
        return (event) -> {
            System.out.println(event.getCode().toString());
            // event (KeyEvent).getCode() returns a KeyCode which is the actual KeyPress.
            // Its an enum, so in switch we just need its value (unqualified expression), rather than the
            // full declaration (qualified expression).
            switch (event.getCode()) {
                case NUMPAD1:
                    oneButton.fire();
                    break;
                case NUMPAD2:
                    twoButton.fire();
                    break;
                case NUMPAD3:
                    threeButton.fire();
                    break;
                case NUMPAD4:
                    fourButton.fire();
                    break;
                case NUMPAD5:
                    fiveButton.fire();
                    break;
                case NUMPAD6:
                    sixButton.fire();
                    break;
                case NUMPAD7:
                    sevenButton.fire();
                    break;
                case NUMPAD8:
                    eightButton.fire();
                    break;
                case NUMPAD9:
                    nineButton.fire();
                    break;
                case NUMPAD0:
                    zeroButton.fire();
                    break;
                case ADD:
                    addButton.fire();
                    break;
                case SUBTRACT:
                    subtractButton.fire();
                    break;
                case MULTIPLY:
                    multiplyButton.fire();
                    break;
                case DIVIDE:
                    divideButton.fire();
                    break;
                case ENTER:
                    equalButton.fire();
                    break;
                case BACK_SPACE:
                    deleteChar();
                    break;
                default:
                    break;
            }
        };
    }

    public void putButtonTextToResultField(ActionEvent event) {
        String buttonText = ((Button) event.getSource()).getText(); // get the text from the button
        resultField.setText(String.format("%s%s", resultField.getText(), buttonText)); // put the text to the resultField
    }

    public boolean checkIfExpressionHasTwoNumbers(String expression) {
        String[] numbers = expression.split("[\\+\\-\\*\\/]"); // split the expression in one of the operators
        if(numbers.length == 1) {
            return false;   // We return false to avoid crashes.
        }

        return true;
    }

    public boolean checkIfOperatorExistsAtEnd(String expression) {
        /*
         * This is needed to avoid crashes as we always run the operation once a new operation is detected.
         * Which results in a crash if the last character is an operator.
         */

        // substring starts with the given index all the way to the end of the String.
        String lastChar = expression.substring(expression.length() - 1);
        if(lastChar.matches("[\\+\\-\\*\\/\\.]")) {
            return true;
        }

        return false;
    }

    public void deleteChar() {
        String expression = resultField.getText();
        if(expression.length() > 0) {
            expression = expression.substring(0, expression.length() - 1);
            resultField.setText(expression);
        }
    }




}
