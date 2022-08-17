package edu.csc413.calculator.evaluator;

import edu.csc413.calculator.exceptions.InvalidTokenException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import java.util.Set;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField expressionTextField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] buttonText = {
        "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
        "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[buttonText.length];

    public static void main(String argv[]) {
        new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.expressionTextField.setPreferredSize(new Dimension(600, 50));
        this.expressionTextField.setFont(new Font("Courier", Font.BOLD, 28));
//        add (new JLabel ("EAST"),BorderLayout.EAST);
//        add (new JLabel ("WEST"),BorderLayout.WEST);
//        add (new JLabel ("SOUTH"),BorderLayout.SOUTH);
        add(expressionTextField, BorderLayout.NORTH);
        expressionTextField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button tempButtonReference;
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            tempButtonReference = new Button(buttonText[i]);
            tempButtonReference.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = tempButtonReference;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.buttonText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }


    // Returns index of buttonText;
    public int getIndex(String token){
        int index;
        for (int i = 0; i < buttonText.length; i++)
        {
            if (token == buttonText[i]) {
                index = i;
                return index;
            }
        }
        return 20;
    }


    /**
     * This function is triggered anytime a button is pressed
     * on our Calculator GUI.
     * @param actionEventObject Event object generated when a
     *                    button is pressed.
     */
    public void actionPerformed(ActionEvent actionEventObject) {
        String buttonPressed = actionEventObject.getActionCommand(); // translates each action pressed into a String

        /**
         * Index 1-17 (-14): Share all the same implementation
         * Store each value into expressionTextField
         *
         * Index 14: Equal Operator
         * Creates a new Evaluator Object that will be used to evaluate the expressionTextField String
         * Returns the value as a string that will then be stored inside the expressionTextField (Updated)
         * New value represents the solution to the previously entered operation.
         *
         * Index 18: Clear
         * Clears the entire text string and all entries within it
         *
         * Index 19: Clear Entry
         * Substrings expressionTextField to remove the last character in the string
         */

        int index = getIndex(buttonPressed);             // set index of Entry
        if (index >= 0 && index <20)                    // if entry is valid
            {
                switch(index)
                {
                    case 18:
                        expressionTextField.setText(""); break;
                    case 19:
                        if (expressionTextField.getText().length()>0)
                            expressionTextField.setText(expressionTextField.getText().substring(0,expressionTextField.getText().length()-1));
                        break;
                    case 0:case 1:case 2:case 3:case 4:case 5:case 6:case 7:case 8:case 9:case 10:case 11:case 12:case 13:case 15:case 16:case 17:
                    expressionTextField.setText(expressionTextField.getText() + buttonText[index]);     // store value into string
                    break;
                    case 14:
                        Evaluator compute = new Evaluator();
                        try {
                            expressionTextField.setText(Integer.toString((compute.evaluateExpression(expressionTextField.getText()))));
                        } catch (InvalidTokenException e) {
                            e.printStackTrace();
                        } break;
                }
            }
}
}
