package edu.cmu.sphinx.demo.helloworld;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import java.awt.datatransfer.Clipboard;

import javax.swing.JMenuItem;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.*;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

import java.io.*;
import java.util.*;

public class NotePade extends JFrame {

	/**
	 * 
	 */
	//Объект класса потока, в котором слушается микрофон
	public static Changer k;
	public static String[] args1;
	public static JTextField answer;
	
	Clipboard clip = getToolkit().getSystemClipboard();
	String sVal;
	static String sText;
	
	public static JLabel yousaid,mntmExit;
	
	private static final long serialVersionUID = 1L;
	private static final Frame modalBlocker = null;
	private JPanel contentPane;
	public static JTextArea textArea;
	public static JMenuItem OpenI;
    public static String word;
	  public static String str=" ";

	  public static String str1=" ",str2=" ",str3=" ";
	  public static String str4=" ";

	  public static String str6=" ";
	  public static String str7=" ",str8=" ",str9=" ";
	  
	   static int len1;

	    static int i=0;
	    int pos1;
	    int len;
	    static Font f;
	    
	    static String months[]={
	        "Jan","Feb","Mar","Apr",
	        "May","Jun","Jul","Aug",
	        "Sep","Oct","Nov","Dec"};

	    static GregorianCalendar  gcalendar;
		
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	

	
	public NotePade() {
		setTitle("Voice NotePade");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 424, 21);
		contentPane.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem OpenI = new JMenuItem("Open");
		OpenI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			try {
				openI();
			    } catch (IOException e) {}	
			}
		});
		mnFile.add(OpenI);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save as                ");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					saveI();
				} catch (IOException e) {}
			}
		});
		mnFile.add(mntmSaveAs);
		
		final JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitI();
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmCopy = new JMenuItem("Copy                Say:\"Copy\"");
		mntmCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyI();
			}
		});
		mnEdit.add(mntmCopy);
		
		JMenuItem mntmCut = new JMenuItem("Cut                     Say:\"Cut\"");
		mntmCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cutI();
				} catch (IOException e1) {}
			}
		});
		mnEdit.add(mntmCut);
		
		JMenuItem mntmPast = new JMenuItem("Past                    Say:\"Past\"");
		mntmPast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pastI();
			}
		});
		mnEdit.add(mntmPast);
		
		JMenuItem mntmTimeday = new JMenuItem("Time/Day.......Say:\"Time\"");
		mntmTimeday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
            //   td();
			}
		});
		
		JMenuItem mntmSelectsayselect = new JMenuItem("Select              Say:\"Select\"");
		mntmSelectsayselect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					iS();
				} catch (BadLocationException e1) {}
			}
		});
		mnEdit.add(mntmSelectsayselect);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Time/Day        Say:\"Time\"");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			td();
			}
		});
		mnEdit.add(mntmNewMenuItem);

		
		JMenu mnFormat = new JMenu("Format");
		menuBar.add(mnFormat);
		
		JMenu mnColor = new JMenu("Font Color");
		mnFormat.add(mnColor);
		
		JMenuItem mntmBlack = new JMenuItem("Black");
		mntmBlack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					colorBlack();
				} catch (IOException e1) {}
			}
		});
		mnColor.add(mntmBlack);
		
		JMenuItem mntmRed = new JMenuItem("Red");
		mntmRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					colorRed();
				} catch (IOException e1) {}
			}
		});
		mnColor.add(mntmRed);
		
		JMenu mnStyleFont = new JMenu("Style Font");
		mnFormat.add(mnStyleFont);
		
		JMenuItem mntmRegulary = new JMenuItem("Regulary");
		mntmRegulary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				reg();
			} catch (IOException e1) {}
			}
		});
		mnStyleFont.add(mntmRegulary);
		
		JMenuItem mntmBold = new JMenuItem("Bold");
		mntmBold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				boldI();
			} catch (IOException e1) {}
			}
		});
		mnStyleFont.add(mntmBold);
		
		JMenuItem mntmItalic = new JMenuItem("Italic");
		mntmItalic.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			try {
				italicI();
			} catch (IOException e1) {}
			}
		});
		mnStyleFont.add(mntmItalic);
		
		JMenu mnSize = new JMenu("Font Size");
		mnFormat.add(mnSize);
		
		JMenuItem mntmNormal = new JMenuItem("Normal");
		mntmNormal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					normI();
				} catch (IOException e) {}
			}
		});
		mnSize.add(mntmNormal);
		
		JMenuItem mntmBig = new JMenuItem("Big");
		mntmBig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					twentyI();
				} catch (IOException e1) {}
			}
		});
		mnSize.add(mntmBig);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmHelpsayhelp = new JMenuItem("Help");
		mntmHelpsayhelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				helpI();
			}
		});
		mnHelp.add(mntmHelpsayhelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(mntmExit, "(c)Ryabchenko Alyona");
			}
		});
		mnHelp.add(mntmAbout);
		
		JLabel lblYouSaid = new JLabel("       You said:   ");
		lblYouSaid.setForeground(Color.RED);
		lblYouSaid.setFont(new Font("Segoe Print", Font.BOLD, 11));
		menuBar.add(lblYouSaid);
		
		yousaid = new JLabel(".....................");
		yousaid.setFont(new Font("Segoe Print", Font.PLAIN, 11));
		yousaid.setForeground(Color.RED);
		menuBar.add(yousaid);
		

		
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(10, 32, 414, 219);
		contentPane.add(textArea);
		
		 f=new Font("Serif", Font.PLAIN, 14);
		 textArea.setFont(f); 

	}
	
	
//////////////////////////////////////////////	
	
	public static void main(final String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Создание и запуск потока-слушателя микрофона
					k = new Changer();
					k.start();
					args1 =args;
					
					NotePade frame = new NotePade();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//закрывает всю программу
					frame.setVisible(true);			
							
				} catch (Exception e) {}
			}
		});
	}	
	
///////////////////////////////////////////////
	
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
				new ConfigurationManager(NotePade.class.getResource("helloworld.config.xml"));
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
	                		
	                		
	                		addNum(getStringNumber(resultText));//отображение команд

	                		 if(resultText.equalsIgnoreCase("select"))
								try {iS();} catch (BadLocationException e) {}
	                		if(resultText.equalsIgnoreCase("cut")) textArea.cut();	                		 
	                		 if(resultText.equalsIgnoreCase("past")) textArea.paste();
	                		 if(resultText.equalsIgnoreCase("copy")) textArea.copy();
	                	//	 if(resultText.equalsIgnoreCase("help")) helpI();
	                	//	 if(resultText.equalsIgnoreCase("open"))
						//		try {openI();} catch (IOException e) {}
	                	//	 if(resultText.equalsIgnoreCase("save"))
						//		try {saveI();} catch (IOException e) {}
	                		 if(resultText.equalsIgnoreCase("time")) td();
	                	//	 if(resultText.equalsIgnoreCase("normal size"))
						//		try {normI();} catch (IOException e) {}
	                	//	 if(resultText.equalsIgnoreCase("big size"))
						//		try {twentyI();} catch (IOException e) {}
	                	//	 if(resultText.equalsIgnoreCase("black"))
						//		try {colorBlack();} catch (IOException e) {}
	                	//	 if(resultText.equalsIgnoreCase("red"))
						//		try {colorRed();} catch (IOException e) {}
	                		 
				}
	                		//end of if (result != null) 
	        			
	                }
			//end of while (true)
	               
	            } 
			//end of public void run()

	     }
	     // end of public static class Changer extends Thread
	
///////////////////////////////////////////////////////////////////////

//перевод в слова и цифры
	public static String getStringNumber(String sText)
	{
		String sResult = "";
		

	//	if (sText.equalsIgnoreCase("save") ) sResult = "save";
	//	if (sText.equalsIgnoreCase("open") ) sResult = "open";
		if (sText.equalsIgnoreCase("copy") ) sResult = "copy";
		if (sText.equalsIgnoreCase("cut") ) sResult = "cut";
		if (sText.equalsIgnoreCase("past") ) sResult = "past";
		if (sText.equalsIgnoreCase("select") ) sResult = "select";
		if (sText.equalsIgnoreCase("time") ) sResult = "time";
	//	if (sText.equalsIgnoreCase("black") ) sResult = "black";
	//	if (sText.equalsIgnoreCase("red") ) sResult = "red";
	//	if (sText.equalsIgnoreCase("normal size") ) sResult = "normal size";
	//	if (sText.equalsIgnoreCase("big size") ) sResult = "big size";
	//	if (sText.equalsIgnoreCase("help") ) sResult = "help";

		return sResult;
	}

//отображение распознаных команд	
	public static void addNum(String sVal){
	String sText=yousaid.getText();
	sText=sVal;
	yousaid.setText(sText);
	}
	
///////////////////////////////////////////////////////////////////////	
	
//находит последнее слово
	
	 public static boolean isWitespace(char ch){
		 return ch == ' ' || ch == '\n' || ch == '\t' || ch == '\r';
	 }
	 
	 public static void iS() throws BadLocationException {
		 String sText = textArea.getText();
		 textArea.getCaretPosition();
		 
		 int offs = textArea.getCaretPosition();
		 char ch = sText.charAt(0);
			 String text = textArea.getText(0, offs);
			 
			 int firstIndexOfSpace = text.lastIndexOf(" ")+1;
			 int firstIndexOfTab = text.lastIndexOf("\t")+1;
			 int firstIndexOfNewLine = text.lastIndexOf("\n")+1;
			 
			 int firstIndexOfWord = firstIndexOfSpace;
			 if (firstIndexOfTab > firstIndexOfWord) firstIndexOfWord = firstIndexOfTab;
			 if (firstIndexOfNewLine > firstIndexOfWord) firstIndexOfWord = firstIndexOfNewLine;
			 
			 word = text.substring(firstIndexOfWord, offs);

			  textArea.grabFocus();
	          textArea.select(firstIndexOfWord, offs);	
	          //textArea.setText(word);
			 // System.out.println("Искомое слово = "+word);
	 }
	 
/////////////////////////////////////////////////////////

//открыть файл
	 
	 public static void openI() throws IOException{
       
		    FileDialog dialog=new FileDialog(modalBlocker, str4 );
	        dialog.setVisible(true);
            str4="";
	        str1 = dialog.getDirectory();
	        str2 = dialog.getFile();
	        str3 = str1+str2;
	        File f=new File(str3);
	        FileInputStream fobj = null;
				fobj = new FileInputStream(f);
			    int len=(int)f.length();
	        for(int j=0;j<len;j++)
	        {
	            char str5 = 0;
				str5 = (char)fobj.read();
			    str4=str4 + str5;
	        }
	        textArea.setText(str4);
	 }

//сохранить файл	 
	 public static void saveI() throws IOException{
	        FileDialog dialog1=new FileDialog(modalBlocker,"Save As",FileDialog.SAVE);
	        dialog1.setVisible(true);

	        str7=dialog1.getDirectory();
	        str8=dialog1.getFile();
	        str9=str7+str8;


	        str6=textArea.getText();
	        len1=str6.length();
	        byte buf[]=str6.getBytes();

	        File f1=new File(str9);
	        FileOutputStream fobj1=new FileOutputStream(f1);
	        for(int k=0;k<len1;k++)
	        {
	        fobj1.write(buf[k]);
	        }
	        fobj1.close();
            }

//выйти
	 public void exitI(){
		 System.exit(0);
		 }

//вырезать
	 public static void cutI()throws IOException{
	        str=textArea.getSelectedText();
	        i=textArea.getText().indexOf(str);
	        textArea.replaceRange(" ",i,i+str.length());
	 }

//copy
	 public static void copyI(){
		 str=textArea.getSelectedText();
	 }

//past
	 public static void pastI(){
		 int pos1=textArea.getCaretPosition();
	     textArea.insert(str,pos1);
	 }
 
//time and day
	 public static void td(){
		 gcalendar=new GregorianCalendar();
	        String h=String.valueOf(gcalendar.get(Calendar.HOUR));
	        String m=String.valueOf(gcalendar.get(Calendar.MINUTE));
	        String s=String.valueOf(gcalendar.get(Calendar.SECOND));
	        String date=String.valueOf(gcalendar.get(Calendar.DATE));
	        String mon=months[gcalendar.get(Calendar.MONTH)];
	        String year=String.valueOf(gcalendar.get(Calendar.YEAR));
	        String hms="Time"+" - "+h+":"+m+":"+s+" Date"+"  -  "+date+"."+mon+"."+year;
	        int loc=textArea.getCaretPosition();
	        textArea.insert(hms,loc);
	 }
	 
//14
	 public static void normI()throws IOException{
	
		    String fontName=f.getName();
	        String fontFamily=f.getFamily();
	        int fontSize=f.getSize();
	        int fontStyle=f.getStyle();

	        f=new Font(fontName,fontStyle,14);
	        textArea.setFont(f);
		 
          // textArea.getSelectedText(); 		 
	      // textArea.setSelectionColor(Color.red);//цвет выделения текста
	      // textArea.setForeground(Color.blue);//меняет цвет текста  
          // textArea.setSelectedTextColor(Color.red);//цвет выделеного текста
          // textArea.setFont(new Font ("TAHOMA", Font.ITALIC, 14));//присваиваем новый стиль
	 }
	 
	//20
		 public static void twentyI()throws IOException{
		
			    String fontName=f.getName();
		        String fontFamily=f.getFamily();
		        int fontSize=f.getSize();
		        int fontStyle=f.getStyle();

		        f=new Font(fontName,fontStyle,20);
		        textArea.setFont(f);	
		 }
		 
//обычный шрифт	 
    public static void reg()throws IOException{
	 String fontName=f.getName();
     String fontFamily=f.getFamily();
     int fontSize=f.getSize();
     int fontStyle=f.getStyle();

     f=new Font(fontName,Font.PLAIN,fontSize);
     textArea.setFont(f);
    }
// меняем шрифт на bold
    public static void boldI()throws IOException{
        String fontName=f.getName();
        String fontFamily=f.getFamily();
        int fontSize=f.getSize();
        int fontStyle=f.getStyle();

        f=new Font(fontName,Font.BOLD,fontSize);
        textArea.setFont(f);
    }
 // меняем шрифт на italic
    public static void italicI()throws IOException {
        String fontName=f.getName();
        String fontFamily=f.getFamily();
        int fontSize=f.getSize();
        int fontStyle=f.getStyle();

        f=new Font(fontName,Font.ITALIC,fontSize);
        textArea.setFont(f);
    }
    
//цвет меняем red
       public static void colorRed() throws IOException{
    	   textArea.setForeground(Color.red); 
       }
//цвет меняем black
       public static void colorBlack() throws IOException{
    	   textArea.setForeground(Color.black); 
       }   
       
//
       public static void helpI(){
    	   JOptionPane.showMessageDialog(mntmExit, "This program contains next voice command:\n"
					+ "Cut\n"
					+ "Copy\n"
					+ "Past\n"
					+ "Select\n"
				);
       }
}
	

