package edu.cmu.sphinx.demo.helloworld;
import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
 
public class SimpleCalculator {
	//Текстовое поле, в которое будут помещаться цифры
	public static JTextField jtfText,jtfText2;
	//Объект класса потока, в котором слушается микрофон
	public static Changer k;
	public static String[] args1;
	public static JTextField answer;
 
public static void main(String[] args) {
	
	//Создание и запуск потока-слушателя микрофона
	k = new Changer();
	k.start();
	args1 =args;
 
// Создаём панель
 
JPanel windowContent= new JPanel();
//Создаём компоненты в памяти

JLabel label1 = new JLabel("Say:  A"); 
label1.setFont(new Font("Segoe Script", Font.BOLD, 13));
label1.setBounds(76, 39, 69, 14);
jtfText = new JTextField(10);
jtfText.setBounds(137, 36, 138, 20);
JLabel label2 = new JLabel("Say:  B"); 
label2.setFont(new Font("Segoe Script", Font.BOLD, 13));
label2.setBounds(76, 94, 91, 13);
jtfText2 = new JTextField(10);
jtfText2.setBounds(137, 90, 138, 20);
JLabel label3 = new JLabel("The answer is:");
label3.setFont(new Font("Segoe Script", Font.BOLD, 17));
label3.setBounds(69, 186, 146, 14);
answer = new JTextField(10);
answer.setBounds(215, 184, 108, 20);
JButton go = new JButton("+");
go.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
				
       int key = e.getKeyCode();
		        
       if (key == KeyEvent.VK_ENTER) {		// нажата клавиша - Enter
            //Твой код здесь
    	   int number1, number2;
   		try{
   			if(!jtfText.getText().isEmpty())
   				number1 = Integer.parseInt(jtfText.getText());
   			else number1 = 0;
   		   }
   		catch (Exception exc)
   		{
   			JOptionPane.showMessageDialog(null, "Bad first number!");
   			return;
   		}
   		try{
   			if(!jtfText2.getText().isEmpty())
   				number2 = Integer.parseInt(jtfText2.getText());
   			else number2 = 0;
   		   }
   		catch (Exception exc)
   		{
   			JOptionPane.showMessageDialog(null, "Bad second number!");
   			return;
   		}
   			
   		
               //считаем сумму
   			int answer1 = number1 + number2;
   			//переводим цифры в String
   			String s1 = Integer.toString( answer1 );
   			answer.setText(s1);
   		
   	
       }
    }
});
go.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
	
		int number1, number2;
		try{
			if(!jtfText.getText().isEmpty())
				number1 = Integer.parseInt(jtfText.getText());
			else number1 = 0;
		   }
		catch (Exception exc)
		{
			JOptionPane.showMessageDialog(null, "Bad first number!");
			return;
		}
		try{
			if(!jtfText2.getText().isEmpty())
				number2 = Integer.parseInt(jtfText2.getText());
			else number2 = 0;
		   }
		catch (Exception exc)
		{
			JOptionPane.showMessageDialog(null, "Bad second number!");
			return;
		}
			
		
            //считаем сумму
			int answer1 = number1 + number2;
			//переводим цифры в String
			String s1 = Integer.toString( answer1 );
			answer.setText(s1);
		}
	}
);
go.setBounds(127, 134, 69, 23);
windowContent.setLayout(null);

//Добавляем компоненты на панель

windowContent.add(label1); 
windowContent.add(jtfText); 
windowContent.add(label2); 
windowContent.add(jtfText2); 
windowContent.add(label3); 
windowContent.add(answer); 
windowContent.add(go);

//Создаём фрейм и задаём для него панель

JFrame jfFrame = new JFrame("Calculator");

jfFrame.setContentPane(windowContent);
JButton button = new JButton("-");
button.addKeyListener(new KeyAdapter() {
    @Override
    public void keyPressed(KeyEvent e) {
				
       int key = e.getKeyCode();
		        
       if (key == KeyEvent.VK_ENTER) {		// нажата клавиша - Enter
            //Твой код здесь
    	   int number1, number2;
			try{
				if(!jtfText.getText().isEmpty())
					number1 = Integer.parseInt(jtfText.getText());
				else number1 = 0;
			   }
			catch (Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Bad first number!");
				return;
			}
			try{
				if(!jtfText2.getText().isEmpty())
					number2 = Integer.parseInt(jtfText2.getText());
				else number2 = 0;
			   }
			catch (Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Bad second number!");
				return;
			}
					
           //считаем разницу
			int answer1 = number1 - number2;
			//переводим цифры в String
			String s1 = Integer.toString( answer1 );
			answer.setText(s1);
		   	
       }
    }
});
    	   
button.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
			int number1, number2;
			try{
				if(!jtfText.getText().isEmpty())
					number1 = Integer.parseInt(jtfText.getText());
				else number1 = 0;
			   }
			catch (Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Bad first number!");
				return;
			}
			try{
				if(!jtfText2.getText().isEmpty())
					number2 = Integer.parseInt(jtfText2.getText());
				else number2 = 0;
			   }
			catch (Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Bad second number!");
				return;
			}
					
            //считаем разницу
			int answer1 = number1 - number2;
			//переводим цифры в String
			String s1 = Integer.toString( answer1 );
			answer.setText(s1);
		}
	
});
button.setBounds(206, 134, 69, 23);
windowContent.add(button);

JLabel imageLabel = new JLabel(new ImageIcon(SimpleCalculator.class.getResource("/edu/cmu/sphinx/demo/helloworld/siri-icon-e1328732646576.jpg")));
windowContent.add(imageLabel);

imageLabel.setBounds(10, 215, 374, 151);





//задаём и размер и делаем фрейм видимым

jfFrame.setSize(400,400);

jfFrame.setVisible(true);

jfFrame.addWindowListener(new WinAdapter(jfFrame));
}

//Класс для потока-прослушивателя микрофона
	public static class Changer extends Thread
	{
		private static final String String = null;

		public void run()
		{
		String[] args=args1;	
			//Инициализация послушивания микрофона
			ConfigurationManager cm;

		        if (args.length > 0) 
			{
		            cm = new ConfigurationManager(args[0]);
		        } 
			else 
			{
		            cm = 
				new ConfigurationManager(SimpleCalculator.class.getResource("helloworld.config.xml"));
		        }

		        Recognizer recognizer = (Recognizer) cm.lookup("recognizer");
		        recognizer.allocate();

		        // start the microphone or exit if the programm if this is not possible
		        Microphone microphone = (Microphone) cm.lookup("microphone");
		        if (!microphone.startRecording()) 
			{
		            System.out.println("Cannot start microphone.");
		            recognizer.deallocate();
		            System.exit(1);
		        }

			//Цикл идентификации чисел и их добвления в текстовое поле
			while (true)
			{
	            		System.out.println("Start speaking. Press Ctrl-C to quit.\n");

	            		Result result = recognizer.recognize();

	            		if (result != null) 
				{
	                		String resultText = result.getBestFinalResultNoFiller();
	                
	                		System.out.println(resultText);
	                		
	                		
	                		if(resultText.equalsIgnoreCase("a one")
	    					   		|| resultText.equalsIgnoreCase("a two")
	    					   		|| resultText.equalsIgnoreCase("a three")
	    					   		|| resultText.equalsIgnoreCase("a four")
	    					   		|| resultText.equalsIgnoreCase("a five")
	    					   		|| resultText.equalsIgnoreCase("a six")
	    					   		|| resultText.equalsIgnoreCase("a seven")
	    					   		|| resultText.equalsIgnoreCase("a eight")
	    					   		|| resultText.equalsIgnoreCase("a nine")
	    					   		|| resultText.equalsIgnoreCase("a zero")
	    					   		|| resultText.equalsIgnoreCase("back"))
	                			
	             					addNum(getStringNumber(resultText));
	             					
	             					
	    	                		
	                		 if(resultText.equalsIgnoreCase("b one")
	        				   		|| resultText.equalsIgnoreCase("b two")
	        				   		|| resultText.equalsIgnoreCase("b three")
	        				   		|| resultText.equalsIgnoreCase("b four")
	        				   		|| resultText.equalsIgnoreCase("b five")
	        				   		|| resultText.equalsIgnoreCase("b six")
	        				   		|| resultText.equalsIgnoreCase("b seven")
	        				   		|| resultText.equalsIgnoreCase("b eight")
	        				   		|| resultText.equalsIgnoreCase("b nine")
	        				   		|| resultText.equalsIgnoreCase("b zero"))
	        	                	
	    		             					addNum1(getStringNumber(resultText));
	                		 if(resultText.equalsIgnoreCase("plus")) plus1();
	                		 if(resultText.equalsIgnoreCase("minus")) minus1();
	                		 if(resultText.equalsIgnoreCase("a clear")) clear();
	                		 if(resultText.equalsIgnoreCase("b clear")) clear1();
	                		 if(resultText.equalsIgnoreCase("clear")) clear2();
	                		 if(resultText.equalsIgnoreCase("a back")) removeL();
	                		 if(resultText.equalsIgnoreCase("b back")) removeL2();
	                		  
	                		
	                		 
				}
	                		//end of if (result != null) 
	        			
	                }
			//end of while (true)
	               
	            } 
			//end of public void run()


	     }
	     // end of public static class Changer extends Thread


      //Класс-адаптер, используемый для обработки события закрытия окна
	public static class WinAdapter extends WindowAdapter
	{
		JFrame jf1;
		public WinAdapter(JFrame jf)
		{
   			jf1 = jf;
		}
 		public void windowClosing(WindowEvent we)
		{
    			jf1.setVisible(false);
   			System.exit(0);
   			
   			
		}
 		
	}
	

	
	//действия, которые произойдут после нажатии кнопки +
	public static void plus1() {
	int number1, number2;
	
	//Выводим сообщение об ошибке, если не введена одна из цифр

	try{
		if(!jtfText.getText().isEmpty())
			number1 = Integer.parseInt(jtfText.getText());
		else number1 = 0;
	   }
	catch (Exception exc)
	{
		JOptionPane.showMessageDialog(null, "Bad first number!");
		return;
	}
	try{
		if(!jtfText2.getText().isEmpty())
			number2 = Integer.parseInt(jtfText2.getText());
		else number2 = 0;
	   }
	catch (Exception exc)
	{
		JOptionPane.showMessageDialog(null, "Bad second number!");
		return;
	}
		
        //считаем сумму
		int answer1 = number1 + number2;
		//переводим цифры в String
		String s1 = Integer.toString( answer1 );
		answer.setText(s1);
	}
	
//стираем все в поле jtfText
	public static void clear() {
			jtfText.setText("");
	}
	
	public static void clear1() {
		jtfText2.setText("");
	}
	
	public static void clear2() {
		jtfText.setText("");
		jtfText2.setText("");
	}


//действия, которые произойдут после нажатии кнопки +
		public static void minus1() {
			int number1, number2;
			//Выводим сообщение об ошибке, если не введена одна из цифр
			try{
				if(!jtfText.getText().isEmpty())
					number1 = Integer.parseInt(jtfText.getText());
				else number1 = 0;
			   }
			catch (Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Bad first number!");
				return;
			}
			try{
				if(!jtfText2.getText().isEmpty())
					number2 = Integer.parseInt(jtfText2.getText());
				else number2 = 0;
			   }
			catch (Exception exc)
			{
				JOptionPane.showMessageDialog(null, "Bad second number!");
				return;
			}
			
			
            //считаем разницу
			int answer1 = number1 - number2;
			//переводим цифры в String
			String s1 = Integer.toString( answer1 );
			answer.setText(s1);
		}
	
	
	
	
		//Метод, используемый для удаления последнего символа из текста в текстовому полю jtfText
		public static void removeL() 
		{
			String sVal;
			String sText = jtfText.getText();		
			if (sText == null || sText.length() == 0) { 
				return;				
				} 	
			sVal = sText.substring(0, sText.length() - 1);
		    sText = sVal;
		    jtfText.setText(sText);
		} 


		//Метод, используемый для удаления последнего символа из текста в текстовому полю jtfText
		public static void removeL2() 
		{
			String sVal;
			String sText = jtfText2.getText();		
			if (sText == null || sText.length() == 0) { 
				return;				
				} 	
			sVal = sText.substring(0, sText.length() - 1);
		    sText = sVal;
		    jtfText2.setText(sText);
		} 	
	
	//Метод, используемый для добавления текста sVal к текстовому полю jtfText
	public static void addNum(String sVal)
	{
		String sText = jtfText.getText();
		sText+= sVal;
		jtfText.setText(sText);
		
	}
	
	
	//Метод, используемый для добавления текста sVal к текстовому полю jtfText
	public static void addNum1(String sVal)
	{
		String sText = jtfText2.getText();
		sText+= sVal;
		jtfText2.setText(sText);
		
	}
	//Метод, который конвертирует аргумент sText в строку, содержащую цифру, соответствующую содержанию
	//данного аргумента, если в нём записана данная цифра словом
	public static String getStringNumber(String sText)
	{
		String sResult = "";
		

		if (sText.equalsIgnoreCase("a one") ) sResult = "1";
		if (sText.equalsIgnoreCase("a two") ) sResult = "2";
		if (sText.equalsIgnoreCase("a three") ) sResult = "3";
		if (sText.equalsIgnoreCase("a four") ) sResult = "4";
		if (sText.equalsIgnoreCase("a five") ) sResult = "5";
		if (sText.equalsIgnoreCase("a six") ) sResult = "6";
		if (sText.equalsIgnoreCase("a seven") ) sResult = "7";
		if (sText.equalsIgnoreCase("a eight") ) sResult = "8";
		if (sText.equalsIgnoreCase("a nine") ) sResult = "9";
		if (sText.equalsIgnoreCase("a zero") ) sResult = "0";

		if (sText.equalsIgnoreCase("b one") ) sResult = "1";
		if (sText.equalsIgnoreCase("b two") ) sResult = "2";
		if (sText.equalsIgnoreCase("b three") ) sResult = "3";
		if (sText.equalsIgnoreCase("b four") ) sResult = "4";
		if (sText.equalsIgnoreCase("b five") ) sResult = "5";
		if (sText.equalsIgnoreCase("b six") ) sResult = "6";
		if (sText.equalsIgnoreCase("b seven") ) sResult = "7";
		if (sText.equalsIgnoreCase("b eight") ) sResult = "8";
		if (sText.equalsIgnoreCase("b nine") ) sResult = "9";
		if (sText.equalsIgnoreCase("b zero") ) sResult = "0";
		return sResult;
	}
}
