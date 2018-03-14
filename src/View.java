import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class View {
    CustomClient customClient;
    int i = 0;
    int y= 40;
    int startnumber = 0;
    int endnumber =1;
    JFrame jFrame;
    JPanel jPanel;
    JTextArea jTextArea;
    JButton jButtonDel;
    JButton jButtonAdd;
    JTextField jTextField;
    JLabel jLabel;
    JTextField jTextFieldname;
    JTextField jTextFieldsurname;
    JTextField jTextFieldgroup;
    JTextField jTextFieldfakultet;
    JTextField jTextFieldsrbal;
    JTextField jTextFieldnumber;
    JButton jButtonAddStudent;
    JButton jButtonDeleteStudent;
    JButton jButtonFindStudent;




    String deleteStudent;
    boolean deleteButon =true;
    public View(){
        customClient = new CustomClient();

        jFrame = new JFrame();

        jFrame.setSize(1360,500);
        jFrame.setLayout(null);
        jLabel = new JLabel();
        jLabel.setBounds(0,0,100,50);
        jFrame.add(jLabel);
        jTextFieldname =new JTextField("");
        jTextFieldname.setBounds(1000,50,200,40);
        jFrame.add(jTextFieldname);

        jTextFieldsurname =new JTextField("");
        jTextFieldsurname.setBounds(1000,100,200,40);
        jFrame.add(jTextFieldsurname);

        jTextFieldgroup =new JTextField("");
        jTextFieldgroup.setBounds(1000,150,200,40);
        jFrame.add(jTextFieldgroup);

        jTextFieldfakultet =new JTextField("");
        jTextFieldfakultet.setBounds(1000,200,200,40);
        jFrame.add(jTextFieldfakultet);

        jTextFieldsrbal =new JTextField("");
        jTextFieldsrbal.setBounds(1000,250,200,40);
        jFrame.add(jTextFieldsrbal);

        jTextFieldnumber =new JTextField("");
        jTextFieldnumber.setBounds(1000,300,200,40);
        jFrame.add(jTextFieldnumber);
        jButtonAddStudent =new JButton("Добавить студента");
        jButtonAddStudent.setBounds(1000,350,200,40);
        jFrame.add(jButtonAddStudent);

        jButtonFindStudent =new JButton("Найти студента");
        jButtonFindStudent.setBounds(600,350,200,40);
        jFrame.add(jButtonFindStudent);

        jButtonFindStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findStudent(jTextFieldsurname.getText());
            }
        });

        jButtonDeleteStudent =new JButton("Удалить студента");
        jButtonDeleteStudent.setBounds(800,350,200,40);
        jFrame.add(jButtonDeleteStudent);

        jButtonDeleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customClient.deleteStudent(deleteStudent);
                updateStudentGui();
            }
        });

        jButtonAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customClient.createStudent(jTextFieldname.getText(),jTextFieldsurname.getText(),jTextFieldgroup.getText(),jTextFieldfakultet.getText(),jTextFieldsrbal.getText(),jTextFieldnumber.getText());
                updateStudentGui();
            }
        });




        jButtonDel =new JButton("←");
        jButtonDel.setBounds(0,50,50,50);
        jButtonAdd =new JButton("→");
        jButtonAdd.setBounds(50,50,50,50);
        jTextField = new JTextField();
        jTextField.setBounds(40,350,100,50);
        jFrame.add(jTextField);

        jFrame.add(jButtonAdd);
        jFrame.add(jButtonDel);
       /* jTextFieldNameFile = new JTextField();
        jTextFieldNameFile.setBounds(500,40,100,50);
        jFrame.add(jTextFieldNameFile);*/
        jFrame.repaint();
        jFrame.revalidate();
    ;

        jButtonDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*deleteStudentFromGui();
                updateStudentGui();*/
                if(startnumber==0)
                {
                    System.out.println("Первая страница");

                }
                else {
                    startnumber--;
                    endnumber--;
                    updateStudentGui();
                }

            }
        });
        jButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            /*    createStudent(jTextField.getText());
                updateStudentGui()*/;
               strplus();

            }
        });
        generateButton();
        jTextArea = new JTextArea();
        jTextArea.setBounds(150,40,300,400);
        jFrame.add(jTextArea);
        jTextArea.setLineWrap(true);
        jTextArea.setWrapStyleWord(true);
        jFrame.setVisible(true);
    }

    public void updateStudentGui(){
        jPanel.removeAll();
        jPanel.updateUI();
        jFrame.remove(jPanel);
        jFrame.revalidate();
        jFrame.repaint();
        generateButton();
    }


    public void findStudent(String name){
        jTextArea.setText(customClient.readFile("D:\\students\\"+name + ".txt"));


    }
    public void generateButton(){
        int bounds = 1;
        jPanel= new JPanel();
        jPanel.setLayout(null);
        int y =startnumber*6;
        int x = endnumber * 6;
        if (x>customClient.getPathFile().length)
            x = customClient.getPathFile().length;
        jPanel.setBounds(410,-5,200,400);
        for(int i =y; i <x;i++){

            int length  = customClient.getNameFile()[i].length();
            JButton jButton = new JButton(customClient.getNameFile()[i].substring(0,length -4));
            String name = customClient.getNameFile()[i];
            String path = customClient.getPathFile()[i];

            jButton.setBounds(40,bounds*50,150,50);
            jPanel.add(jButton);
            bounds++;
            jButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                   // jLabel.setText(path);
                    jTextArea.setText(customClient.readFile(path));
                    deleteStudent  = path;


                }
            });
        }


        jFrame.add(jPanel);
        jPanel.setVisible(true);
        jPanel.repaint();
        jPanel.revalidate();
        i=0;


    }

    public void strplus()
    {
        if(((endnumber)*6)>=customClient.getPathFile().length)
        {
            System.out.println("Последняя страницы");
        }
        else {
            startnumber++;
            endnumber++;
            updateStudentGui();
        }
    }

    public static void main(String[] args) {
        new View();
    }

}
