package test;


import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import test.test;

public class Panel extends JFrame implements ActionListener {
    private final String[] str = { 
		"7", "8", "9", ".",  
		"6", "5", "4", "-", 
		"3", "2", "1", "+",
		"0","=","random"};
                                 
    JButton[] buttons = new JButton[str.length];  //buttons for numbers
    JButton reset = new JButton("CE");         //button for reset
    JTextField display = new JTextField("0");  //textfied for result
    JTextField display1 = new JTextField("+");
    JTextField display2 = new JTextField("0");
    JTextField display3 = new JTextField("0");
    public Panel() {
        super("Calculator");
		Font font = new Font("Consolas", Font.BOLD, 18);

		// add components
		JPanel pnlHead = new JPanel(new BorderLayout());
        pnlHead.add( display, BorderLayout.NORTH);
        pnlHead.add( reset, BorderLayout.EAST);
        pnlHead.add( display1, BorderLayout.WEST);
        pnlHead.add( display2, BorderLayout.CENTER);
        pnlHead.add( display3, BorderLayout.SOUTH);
		display.setFont(font);
		display1.setFont(font);
		display2.setFont(font);
		display3.setFont(font);
		reset.setFont(font);

        JPanel pnlBody = new JPanel(new GridLayout(4, 4));
        for (int i = 0; i < str.length; i++) {
            buttons[i] = new JButton(str[i]);
			buttons[i].setFont(font);
            pnlBody.add(buttons[i]);
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.NORTH, pnlHead);
        getContentPane().add(BorderLayout.CENTER, pnlBody);

        // register listeners
        for (int i = 0; i < str.length; i++)
            buttons[i].addActionListener(this);
        reset.addActionListener(this);
        display.addActionListener(this);

		// set frame properties
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600);
        setVisible(true);
    }
	 boolean isFirstDigit = true;
	 String number = "0.0";
	 String operator = "="; 
	 int flag=0;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		Object source = e.getSource();
        String cmd = e.getActionCommand();
       
        if (source == reset)
            handleReset();
        else if ("0123456789.".indexOf(cmd) >= 0&&flag==0)
            handleNumber(cmd);
        else if ("0123456789.".indexOf(cmd) >= 0&&flag==1)
        
        	handleNumber1(cmd);
        else if (cmd=="random") {

        	String s1 = test.getRandomString(10);
        	String s2 = test.getRandomString(50);
        	String s3 = s1+"."+s2;
        	operator = "+";
        	display.setText(s3);
        	display1.setText("+");
	        display2.setText(test.getRandomString(10)+"."+test.getRandomString(50));
	        
        }
        else if(cmd=="+")
            handleOperator1(cmd);
        else if(cmd=="-")
        	handleOperator2(cmd);
        else if(cmd=="=")
        	handleOperator3(cmd);
		
	}
	public void handleReset() {
	        display.setText("0");
	        display1.setText("+");
	        display2.setText("0");
	        display3.setText("0");
	        isFirstDigit = true;
	        operator = "=";
	}
	public void handleNumber(String key) {
	        if (isFirstDigit&&(key!="."))
	            display.setText(key);
	        else if (!key.equals("."))
	            display.setText(display.getText() + key);
	        else if (display.getText().indexOf(".") < 0)
	            display.setText(display.getText() + ".");
	        isFirstDigit = false;
	}
	public void handleNumber1(String key) {
        if (isFirstDigit&&(key!="."))
            display2.setText(key);
        else if (!key.equals("."))
            display2.setText(display2.getText() + key);
        else if (display2.getText().indexOf(".") < 0)
            display2.setText(display2.getText() + ".");
        isFirstDigit = false;
}
    public void handleOperator1(String cmd) {
		 
		number = display2.getText();
		display1.setText(cmd);
        operator = cmd;
        isFirstDigit = true;
        flag=1;
    }
    public void handleOperator2(String cmd) {
		number = display2.getText();
		display1.setText(cmd);
        operator = cmd;
        isFirstDigit = true;
        flag=1;
    }
    public void handleOperator3(String cmd) {
    	flag=0;
		String dDisplay = display.getText();
		//String dDisplay1 = display1.getText();
		if(!display2.getText().contains(".")) {
			number = display2.getText() +".0";
		}
		if(!dDisplay.contains(".")) {
			dDisplay = dDisplay +".0";
		}
		if(operator=="+") {
			if(dDisplay.charAt(0)=='-') {
				dDisplay = dDisplay.substring(1);
				if(display2.getText().equals(dDisplay)) {
					number="0.0";
				}
				else if(test.compare(dDisplay,display2.getText())) {
					number = "-"+test.subBigfloat(display2.getText(), dDisplay);
				}else {
					number = test.subBigfloat(display2.getText(), dDisplay);
				}
			}else 
			{
					number = test.addBigfloat(display2.getText(), dDisplay);
				
			}
			
		}
		else if(operator=="-"){
			if(dDisplay.charAt(0)=='-') {
				dDisplay = dDisplay.substring(1);
			
				number ="-"+ test.addBigfloat(dDisplay,display2.getText() );
			}else if(display2.getText().equals(dDisplay)) {
				number="0.0";
			}
			else if(test.compare(dDisplay,display2.getText())){
				number = test.subBigfloat(dDisplay,display2.getText());
			}else {
				number ="-"+ test.subBigfloat(dDisplay,display2.getText());
			}
		}
		display3.setText(number);
        operator = cmd;
        isFirstDigit = true;
    }
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()->{
			new Panel();
		});
    }
}