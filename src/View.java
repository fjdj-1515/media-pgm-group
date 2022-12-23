import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class View extends JFrame {
    public View() {

    }
}

class TextChat extends JFrame implements ActionListener{
    private JTextArea t;
    private JLabel l;
    public TextChat() {
	this.setTitle("Text Chat");
	this.setSize(300,400);
	this.setLayout(new GridLayout(6,1));
	for(int i = 1; i <= 3; i++) {
	    this.add(new JLabel(" "));
	}
	l = new JLabel(" ");
	this.add(l);
	t = new JTextArea();
	this.add(t);
	JButton b = new JButton("送信");
	b.setPreferredSize(new Dimension(100, 50));
	this.add(b);
	b.addActionListener(this);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.pack();
	this.setVisible(true);
     }
    public void actionPerformed(ActionEvent ev) {
         String s = t.getText();
         l.setText(s);
     }
    public static void main(String argv[]) {
      new TextChat();
   }
}
