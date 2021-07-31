/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableproject;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Burapa
 */
public class TableProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyFrame mf = new MyFrame();
    }

}

class MyFrame extends JFrame implements ActionListener, MouseListener {

    JFileChooser fchOpen = new JFileChooser("C:/picture New");
    int row = 0;
    String[] header = {"NAME", "PICTURE"};
    String[][] data = {{"Rundorn", "Rundorn.jpg"}};
    DefaultTableModel model = new DefaultTableModel(data, header);                // 1. สร้าง object ของ DefaultTableModel สำหร้บช่วยในการจัดการข้อมูลของ JTable
    JTable tbl = new JTable(model);                      // 2. สร้าง object ของ JTable เพื่อสร้างตารางโดยใช้ DefaltTableModel มาช่วยในการจัดการข้อมูลในตาราง
    JScrollPane scroll = new JScrollPane(tbl);          //  3.เอา ตาราง(tbl) ไปไว้บน JScrollPane เพื่อให้แสดง scroll สำหรับเลื่อนดูข้อมูลได้ แต่จะแสดงก็ต่อเมื่อ ข้อมูลมีมากเกินที่่จะแสดงในตารางได้
    JPanel pnl = new JPanel();
    JLabel lblShowN = new JLabel("Name : ");
    JLabel lblPic = new JLabel();
    JLabel lbl = new JLabel("ADD     : ");
    JTextField txtName = new JTextField("");
    JButton btnAdd = new JButton("ADD");
    JButton btnDel = new JButton("DEL");
    String path;

    public MyFrame() throws HeadlessException {
        setTitle("Table Name ");
        setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + File.separator + "iconTable.png"));                           //สร้าง Icon ของโปรแกรม
        setBounds(0, 0, 405 + 14-20-20-20, 300 + 35-20-20-20);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pnl.setBounds(0, 0, 405-20-20, 300-20-20-20);
        pnl.setLayout(null);

        scroll.setBounds(30-20, 30-20, 180, 180);

        lblShowN.setBounds(240-20-20, 30-20, 135, 30);

        lblPic.setBounds(240-20-20, 60-20, 135, 150);
        lblPic.setBorder(BorderFactory.createEtchedBorder(0));
        lbl.setBounds(30-20, 240-20-20, 600, 30);

        txtName.setBounds(90-20, 240-20-20, 120, 30);

        btnAdd.setBounds(240-20-20, 240-20-20, 60, 30);
        btnDel.setBounds(240 + 75-20-20, 240-20-20, 60, 30);
        tbl.addMouseListener(this);
        btnAdd.addActionListener(this);
        btnDel.addActionListener(this);
        pnl.add(scroll);
        pnl.add(txtName);
        pnl.add(lbl);
        pnl.add(lblPic);
        pnl.add(lblShowN);
        pnl.add(btnAdd);
        pnl.add(btnDel);

        add(pnl);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Picture", "png", "gif", "bmp", "jpg");              //ให้จำกัดว่าจะใช้ชนิดไฟล์ แบบไหนบ้าง ใส่ไว้ในตัวแปร filter
            fchOpen.setFileFilter(filter);     //เอาฟิวเตอร์ไปใส่

            fchOpen.setDialogTitle("Open");                                     //set title 
            int retureVal = fchOpen.showOpenDialog(null);                       //ให้แสดงอยู่ตรงกลาง
            if (retureVal == 0) {
                try {
                    File file = fchOpen.getSelectedFile();                              // นำไฟล์ที่เลือก ไปเก็บไว้ในตัวแปร File
                    System.out.println(file);
                    path = file.toString();                                                 // นำ Part ของไฟล์ที่เลือกเปลี่ยนเป็น String  แล้วนำไปเก็บไว้ในตัวแปร path
                    

                    repaint();                       //วาดใหม่  จะมีการวาดใหม่ อยู่ 2กรณีคือ เมื่อ component มีการเปลี่ยนแปลง หรือใช้คำสั่ง repaint();
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            } else if (retureVal == 1) {
                System.out.println("File is not Collect");
 
            }
            model.addRow(new Object[]{txtName.getText(),path});
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        row = tbl.getSelectedRow();

      Image img = Toolkit.getDefaultToolkit().createImage(path);
        lblPic.setIcon(new ImageIcon(img));         // นำ Path ของไฟล์ มาใส่ในตังแปร ImgPnl);
        lblShowN.setText(Objects.toString("Name :   "+model.getValueAt(row, 0)));
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
