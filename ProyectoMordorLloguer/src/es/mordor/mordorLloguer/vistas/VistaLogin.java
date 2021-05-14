package es.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;


import com.alee.extended.image.WebImage;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JButton;

public class VistaLogin extends JInternalFrame {
	private WebPasswordField PasswordField;
	private WebTextField UserField;
	private JButton btnNewButton;
	private JProgressBar progressBar;


	/**
	 * Create the frame.
	 */
	public VistaLogin() {
		setClosable(true);
		setBounds(100, 100, 353, 230);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		JPanel panel_2 = new JPanel();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(25, Short.MAX_VALUE))
		);
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		UserField = new WebTextField();
		UserField.setToolTipText("");
		UserField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(UserField);
		UserField.setColumns(10);
		UserField.setLeadingComponent ( new WebImage(new ImageIcon(VistaPrincipal.class.getResource("/es/mordor/mordorLloguer/resources/username.png"))));
		UserField.setInputPrompt("Username");
		
		
		PasswordField = new WebPasswordField();
		PasswordField.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(PasswordField);
		PasswordField.setColumns(10);
		PasswordField.setLeadingComponent ( new WebImage(new ImageIcon(VistaPrincipal.class.getResource("/es/mordor/mordorLloguer/resources/candado.png"))));
		PasswordField.setInputPrompt("Password");
		
		
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblMemberLogin = new JLabel("Member Login");
		lblMemberLogin.setFont(new Font("AnjaliOldLipi", Font.BOLD, 16));
		lblMemberLogin.setForeground(Color.DARK_GRAY);
		lblMemberLogin.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMemberLogin, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		progressBar = new JProgressBar();
		progressBar.setForeground(Color.GREEN);
		progressBar.setIndeterminate(true);
		progressBar.setVisible(false);
		panel_2.add(progressBar, BorderLayout.NORTH);
		
		btnNewButton = new JButton("LOGIN");
		btnNewButton.setFont(new Font("AnjaliOldLipi", Font.BOLD, 15));
		btnNewButton.setBackground(Color.WHITE);
		panel_2.add(btnNewButton, BorderLayout.CENTER);
		getContentPane().setLayout(groupLayout);

	}


	public JProgressBar getProgressBar() {
		return progressBar;
	}


	public WebPasswordField getPasswordField() {
		return PasswordField;
	}


	public WebTextField getUserField() {
		return UserField;
	}


	public JButton getBtnLogin() {
		return btnNewButton;
	}
}
