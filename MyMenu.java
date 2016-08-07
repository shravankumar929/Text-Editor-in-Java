package mymenu;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MyMenu extends JFrame
{

    public JMenuBar mb;
    public JMenu m1, m2, m3,m4;
    public JMenuItem mi2, mi3, mi4, mi5, mi6, mi7, mi8,mi9,mi10,mi11,mi12,mi13;
    public JFileChooser jfs;
    public FileDialog fd, fs;
    public Border textBorder;
    JFrame frame=new JFrame("Indit Text Editor");
    JTextArea area=new JTextArea(40,60);
    JScrollPane scrollPane=new JScrollPane(area);
    

    public MyMenu() 
    {
        area.setBackground(Color.DARK_GRAY);
        area.setForeground(Color.white);
        area.setCaretColor(Color.white);
        mb = new JMenuBar();

        m1 = new JMenu("File");
        m2 = new JMenu("Edit");
        m4 = new JMenu("Colours");
        m3 = new JMenu("Help");
        
        mi2 = new JMenuItem("save");
        mi3 = new JMenuItem("Open File");
        mi4 = new JMenuItem("Exit");
        mi5 = new JMenuItem("Cut");
        mi6 = new JMenuItem("Copy");
        mi7 = new JMenuItem("Paste");
        mi8 = new JMenuItem("About");
        mi9 = new JMenuItem("Black");
        mi10= new JMenuItem("Dark Gray");
        mi11= new JMenuItem("Green");
        mi12= new JMenuItem("Pink");
        mi13=new JMenuItem("White");
        
        mi2.setAccelerator(KeyStroke.getKeyStroke('S',CTRL_DOWN_MASK));
        mi3.setAccelerator(KeyStroke.getKeyStroke('O',CTRL_DOWN_MASK));
        mi5.setAccelerator(KeyStroke.getKeyStroke('X',CTRL_DOWN_MASK));
        mi6.setAccelerator(KeyStroke.getKeyStroke('C',CTRL_DOWN_MASK));
        mi7.setAccelerator(KeyStroke.getKeyStroke('V',CTRL_DOWN_MASK));
        
        
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi4);
        m2.add(mi5);
        m2.add(mi6);
        m2.add(mi7);
        m3.add(mi8);
        m4.add(mi9);
        m4.add(mi10);
        m4.add(mi11);
        m4.add(mi12);
        m4.add(mi13);

        mb.add(m1);
        mb.add(m2);
        mb.add(m4);
        mb.add(m3);
        mb.setBackground(Color.gray);
        frame.setJMenuBar(mb);
        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        
	thehandler handler = new thehandler();
        fd = new FileDialog(this, "Opening File", FileDialog.LOAD);
        fs = new FileDialog(this, "Saving File", FileDialog.SAVE);
        mi2.addActionListener(handler);
        mi3.addActionListener(handler);
        mi4.addActionListener(handler);
        mi5.addActionListener(handler);
        mi6.addActionListener(handler);
        mi7.addActionListener(handler);
        mi8.addActionListener(handler);
        mi9.addActionListener(handler);
        mi10.addActionListener(handler);
        mi11.addActionListener(handler);
        mi12.addActionListener(handler);
        mi13.addActionListener(handler);
        //area.addAncestorListener(handler);
    }
    class thehandler implements ActionListener 
    {

        @Override
      
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getSource() == mi2) 
            {
                //BufferedWriter writer=new BufferedWriter(new FileWriter());
                String str=area.getText();
                JFileChooser chooser=new JFileChooser();
                chooser.setCurrentDirectory(new File("./"));
                int actionDialog=chooser.showSaveDialog(area);
                if(actionDialog==JFileChooser.APPROVE_OPTION)
                {
                       File fileName=new File(chooser.getSelectedFile()+"");
              
                       if(fileName==null)
                       {
                           return;
                       }
                       if(fileName.exists())
                       {
                           actionDialog=JOptionPane.showConfirmDialog(area,"Replacing exsiting file");
                           if(actionDialog==JOptionPane.NO_OPTION)
                                return;
                       }
                       try
                       {
                           try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
                               out.write(str);
                           }
                       }
                       catch(IOException ie)
                       {
                           System.err.println("Error: "+ie.getMessage());
                       }
                }
            }
            else if (e.getSource() == mi3) 
            {
                JFileChooser chooser=new JFileChooser();
                chooser.setCurrentDirectory(new File("./"));
                FileNameExtensionFilter filter=new FileNameExtensionFilter("text files","txt");
                chooser.setFileFilter(filter);
                int returnVal=chooser.showOpenDialog(area);
                File file=null;
                if(returnVal==JFileChooser.APPROVE_OPTION)
                {
                    file=new File(chooser.getSelectedFile()+"");
                }
                try
                {
                    BufferedReader in=new BufferedReader(new FileReader(file));
                    String s=in.readLine();
                    while(s!=null)
                    {
                        area.append(s);
                        s=in.readLine();
                    }
                }
                catch(FileNotFoundException z)
                {
                    JOptionPane.showMessageDialog(null,"File not found");
                }
                catch(IOException z)
                {
                }

            } 
            else if (e.getSource() == mi4)
            {
                System.exit(0);
            } 
            else if(e.getSource()==mi5)
            {
                area.cut();
            }
            else if(e.getSource()==mi6)
            {
                area.copy();
            }
            else if(e.getSource()==mi7)
            {
                area.paste();
            }
            else if (e.getSource() == mi8) 
            {
                JOptionPane.showMessageDialog(null, "Developed By\nShravan Kumar(B111687)\nVersion 1.1");
            }
            else if(e.getSource()==mi9)
            {
                area.setBackground(Color.black);
                area.setForeground(Color.WHITE);
            }
            else if(e.getSource()==mi10)
            {
                area.setBackground(Color.DARK_GRAY);
            }
            else if(e.getSource()==mi11)
            {
                area.setBackground(Color.GREEN);
                area.setForeground(Color.BLACK);
            }
            else if(e.getSource()==mi12)
            {
                area.setBackground(Color.PINK);
            }
            else if(e.getSource()==mi13)
            {
                area.setBackground(Color.WHITE);
                area.setCaretColor(Color.BLACK);
                area.setForeground(Color.BLACK);
            }
        }

    }
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable()
        {
        @Override
        public void run()
        {
            MyMenu myMenu = new MyMenu();
        }
        });

    }

}
