import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class View extends JFrame {
	public View() {

	}
}

class TextChat extends JFrame implements ActionListener {
	CommClient cl = new CommClient("localhost", 10030);
	String msg2;
	private JTextArea t;
	private JLabel l;

	public TextChat() {
		this.setTitle("Text Chat");
		this.setSize(300, 400);
		this.setLayout(new GridLayout(6, 1));
		for (int i = 1; i <= 3; i++) {
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
		while (true) {
			msg2 = cl.recv();
			l.setText(msg2);
		}
	}

	public void actionPerformed(ActionEvent ev) {
		String s = t.getText();
		cl.send(s);
	}

	public static void main(String argv[]) {
		new TextChat();
	}
}
