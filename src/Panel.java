import javax.swing.JPanel;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	public Panel() {
		setLayout(null);
		
		JPanel panel1 = new JPanel();
		panel1.setBounds(0, 0, 300, 25);
		add(panel1);
		
		JPanel panel3 = new JPanel();
		panel3.setBounds(0, 325, 300, 25);
		add(panel3);
		panel3.setLayout(null);
		
		JButton btnNewButton = new JButton("Previous");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(42, 0, 89, 23);
		panel3.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Next");
		btnNewButton_1.setBounds(154, 0, 89, 23);
		panel3.add(btnNewButton_1);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(0, 25, 300, 301);
		add(panel2);
		panel2.setLayout(new GridLayout(3, 3, 5, 5));
		
		for(int i=0;i<9;i++){
			JButton button=new JButton("");
			panel2.add(button);
		}
		panel2.repaint();
		panel2.revalidate();
		/*JLabel lbl1 = new JLabel("New label");
		
		
		
		JLabel lbl2 = new JLabel("New label");
		panel2.add(lbl2);
		
		JLabel lbl3 = new JLabel("New label");
		panel2.add(lbl3);
		
		JLabel lbl4 = new JLabel("New label");
		panel2.add(lbl4);
		
		JLabel lbl5 = new JLabel("New label");
		panel2.add(lbl5);
		
		JLabel lbl6 = new JLabel("New label");
		panel2.add(lbl6);
		
		JLabel lbl7 = new JLabel("New label");
		panel2.add(lbl7);
		
		JLabel lbl8 = new JLabel("New label");
		panel2.add(lbl8);
		
		JLabel lbl9 = new JLabel("New label");
		panel2.add(lbl9);*/

	}
}
