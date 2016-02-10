package edu.cmu.sphinx.demo.helloworld;


import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JScrollPane;





import java.awt.datatransfer.Clipboard;






import javax.swing.JTextField;





import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;

import java.awt.Font;



public class captureF extends JFrame {
	
	//Объект класса потока, в котором слушается микрофон
		public static Changer k;
		public static String[] args1;
		public static JTextField answer,jtfText2;
		
		Clipboard clip = getToolkit().getSystemClipboard();
		String sVal;
		static String sText;
	
	  static ImageArea ia = new ImageArea();
		
	  static Rectangle rectScreenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	  static Robot robot;
	  static JScrollPane jsp;

	  
	  
	//Класс для потока-прослушивателя микрофона
	public static class Changer extends Thread
	{
		public static final String String = null;

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
				new ConfigurationManager(captureF.class.getResource("helloworld.config.xml"));
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
	                		
	                		
	          			
	             					//addNum(getStringNumber(resultText)); 
	                		if(resultText.equalsIgnoreCase("clip")
	                				|| resultText.equalsIgnoreCase("save")
	        				   		|| resultText.equalsIgnoreCase("exit")
	        				   	//	|| resultText.equalsIgnoreCase("four")
	        			)
	                			addNum(getStringNumber(resultText));
	    		             		
	                	//	 if(resultText.equalsIgnoreCase("cut")) textArea.cut();			                		 
	                	//	 if(resultText.equalsIgnoreCase("past")) textArea.paste();
	                	//	 if(resultText.equalsIgnoreCase("copy")) textArea.copy();
	                	//	 if(resultText.equalsIgnoreCase("open")) openF();
	                		 if(resultText.equalsIgnoreCase("clip")) capture();
	                		 if(resultText.equalsIgnoreCase("save")) save();
	                		// if(resultText.equalsIgnoreCase("exit")) exitF() ;

	                	//	 if(resultText.equalsIgnoreCase("delete")) delF();
	                		 
				}
	                		//end of if (result != null) 
	        			
	                }
			//end of while (true)
	               
	            } 
			//end of public void run()

	     }
	     // end of public static class Changer extends Thread




	  

	/**
	 * 
	 */
	private static final long serialVersionUID = 8613843256754639409L;
	public JPanel contentPane;
	public static JLabel textFieldjtfText2;

	/**
	 * Launch the application.
	 */

	
	
	
	/**
	 * Create the frame.
	 */
	public captureF() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
	    JFrame capture = new JFrame();
		capture.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds( 0, 0, 300,60);

		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem save = new JMenuItem("Save");
		 save.addActionListener(new ActionListener() {
				@Override

					public void actionPerformed(ActionEvent arg0)
					{
						save();
					}				
				}
	      );
		mnFile.add(save);
		
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				exitF();
				}
			});
		mnFile.add(exit);
		
		final JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem captureS = new JMenuItem("Clip");
		captureS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				capture();
				}
			});

		
		mnEdit.add(captureS);
		
		
		JLabel lblYouSpeak = new JLabel("       You said:  ");
		lblYouSpeak.setFont(new Font("Segoe Print", Font.PLAIN, 11));
		menuBar.add(lblYouSpeak);
		
		textFieldjtfText2 = new JLabel("                ");
		textFieldjtfText2.setFont(new Font("Segoe Print", Font.BOLD, 11));
		menuBar.add(textFieldjtfText2);


		
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	public static void addNum(String sVal)
	{
		String sText = textFieldjtfText2.getText();
		sText= sVal;
		textFieldjtfText2.setText(sText);
		
	}
	
	
	//сохранить документ
	  public static void save() {
		  if (ia.getImage() == null) {
		      System.out.println("No captured image.");
		      return;
		    }
		    JFileChooser save = new JFileChooser();
			int option = save.showSaveDialog(save);
			if (option == JFileChooser.APPROVE_OPTION) {
			
				
		    ImageWriter writer = null;
		    ImageOutputStream ios = null;
		    try {
		      java.util.Iterator<ImageWriter> iter = ImageIO.getImageWritersByFormatName("jpeg");
		      if (!iter.hasNext()) {
		        System.out.println("Unable to save image to jpeg file type.");
		        return;
		      }
		      writer = (ImageWriter) iter.next();
		      ios = ImageIO.createImageOutputStream(new File(save.getSelectedFile().getPath()));
		      writer.setOutput(ios);
		      ImageWriteParam iwp = writer.getDefaultWriteParam();
		      iwp.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		      iwp.setCompressionQuality(0.95f);
		      writer.write(null,
		          new IIOImage((BufferedImage) ia.getImage(), null, null), iwp);

		    } catch (Exception e2) {
			  System.out.println(e2.getMessage());
		      e2.printStackTrace();
		    }
			}
	  }

public static void exitF(){
	System.exit(0);
	}
public static void capture(){

	    Toolkit kit = Toolkit.getDefaultToolkit();
	    final Dimension d = kit.getScreenSize();
	  //  capture.setSize(d);
		 JFrame capture = new JFrame();
			capture.setBounds( 0, 0, d.width, d.height);
	    Rectangle rect = new Rectangle(d);
	   try {
	      Robot robot = new Robot();
	        final BufferedImage image = robot.createScreenCapture(rect);
	      image.flush();
	      ia.setImage(image);
	   //   jsp.getHorizontalScrollBar().setValue(0);
       //  jsp.getVerticalScrollBar().setValue(0);

	      JPanel panel = new JPanel() {
	        /**
			 * 
			 */
			private static final long serialVersionUID = -5498673270374398969L;

			public void paintComponent(Graphics g) {
	          g.drawImage(image, 0, 0, d.width, d.height, this);
	        }
	      };
	      panel.setOpaque(false);
	      panel.prepareImage(image, panel);
	      panel.repaint();
	      capture.getContentPane().add(panel);
	      
	     
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    capture.setVisible(true);
	  }






public static void main(String[] args) {
	
	//Создание и запуск потока-слушателя микрофона
			k = new Changer();
			k.start();
			args1 =args;
	   
	try {
		robot = new Robot();
	} catch (AWTException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    new captureF();
	EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				captureF frame = new captureF();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
}


static class ImageArea extends JPanel {
  /**
	 * 
	 */
public static final long serialVersionUID = 1L;
public Image image;
  Point startPoint = new Point(), endPoint = new Point();
  public Rectangle selectedRectangle = new Rectangle();
public static ImageArea ia;
public static JScrollPane jsp;
  public ImageArea() {
    addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        if (image == null)
          return;
        startPoint = endPoint = e.getPoint();
        repaint();
      }
    });
    addMouseMotionListener(new MouseMotionAdapter() {
      public void mouseDragged(MouseEvent e) {
        if (image == null)
          return;
        endPoint = e.getPoint();
        repaint();
      }
    });
  
  
  }
  
  public boolean crop() {
    if (startPoint.equals(endPoint))
      return true;
    boolean succeeded = true;
    int x1 = (startPoint.x < endPoint.x) ? startPoint.x : endPoint.x;
    int y1 = (startPoint.y < endPoint.y) ? startPoint.y : endPoint.y;
    int x2 = (startPoint.x > endPoint.x) ? startPoint.x : endPoint.x;
    int y2 = (startPoint.y > endPoint.y) ? startPoint.y : endPoint.y;
    int width = (x2 - x1) + 1;
    int height = (y2 - y1) + 1;
    BufferedImage biCrop = new BufferedImage(width, height,
        BufferedImage.TYPE_INT_RGB);
    Graphics2D g2d = biCrop.createGraphics();
    BufferedImage bi = (BufferedImage) image;
    BufferedImage bi2 = bi.getSubimage(x1, y1, width, height);
    g2d.drawImage(bi2, null, 0, 0);
    g2d.dispose();
    if (succeeded)
      setImage(biCrop);
    else {
      startPoint.x = endPoint.x;
      startPoint.y = endPoint.y;
      repaint();
    }
    return succeeded;
  }
  public Image getImage() {
    return image;
  }
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null)
      g.drawImage(image, 0, 0, this);
    if (startPoint.x != endPoint.x || startPoint.y != endPoint.y) {
      int x1 = (startPoint.x < endPoint.x) ? startPoint.x : endPoint.x;
      int y1 = (startPoint.y < endPoint.y) ? startPoint.y : endPoint.y;
      int x2 = (startPoint.x > endPoint.x) ? startPoint.x : endPoint.x;
      int y2 = (startPoint.y > endPoint.y) ? startPoint.y : endPoint.y;
      selectedRectangle.x = x1;
      selectedRectangle.y = y1;
      selectedRectangle.width = (x2 - x1) + 1;
      selectedRectangle.height = (y2 - y1) + 1;
      Graphics2D g2d = (Graphics2D) g;
      g2d.draw(selectedRectangle);
    }
  }
  public void setImage(Image image) {
    this.image = image;
    setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
    revalidate();
    startPoint.x = endPoint.x;
    startPoint.y = endPoint.y;
    repaint();
  }







}

public static String getStringNumber(String sText)
{
	String sResult = "";
	

	if (sText.equalsIgnoreCase("save") ) sResult = "save";
	if (sText.equalsIgnoreCase("exit") ) sResult = "exit";
	if (sText.equalsIgnoreCase("clip") ) sResult = "clip";


	return sResult;
}
}
