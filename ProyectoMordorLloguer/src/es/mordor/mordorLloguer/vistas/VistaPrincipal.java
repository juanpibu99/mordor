package es.mordor.mordorLloguer.vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JToolBar;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSeparator;

public class VistaPrincipal extends JFrame {

	private JPanel contentPane;
	private JButton btnLogin;
	private JButton btnLogout;
	private JMenu mnFile;
	private JMenu mnEdit;
	private VistaLogin vistaLogin;
	private JDesktopPane desktopPane;
	private JSeparator separator;
	private JMenuItem mntmExit;
	private JMenuItem mntmPreferences;
	private JButton btnEmploye;

	
	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 415);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		mntmPreferences = new JMenuItem("Preferences");
		mnEdit.add(mntmPreferences);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.putClientProperty("styleId","attached-north");
		contentPane.add(toolBar, BorderLayout.NORTH);
		
		
		btnLogin = new JButton();
		btnLogin.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/es/mordor/mordorLloguer/resources/login.png")));
		toolBar.add(btnLogin);
		
		btnLogout = new JButton("");
		btnLogout.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/es/mordor/mordorLloguer/resources/logout.png")));
		toolBar.add(btnLogout);
		
		
		toolBar.addSeparator ( new Dimension ( 10, 10 ) );
		
		btnEmploye = new JButton("");
		btnEmploye.setIcon(new ImageIcon(VistaPrincipal.class.getResource("/es/mordor/mordorLloguer/resources/employe.png")));
		toolBar.add(btnEmploye);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
		
		

		
	}

	public JButton getBtnEmploye() {
		return btnEmploye;
	}

	public JMenuItem getMntmExit() {
		return mntmExit;
	}

	public JMenuItem getMntmPreferences() {
		return mntmPreferences;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public JButton getBtnLogin() {
		return btnLogin;
	}

	public JButton getBtnLogout() {
		return btnLogout;
	}

	public JMenu getMnFile() {
		return mnFile;
	}

	public JMenu getMnEdit() {
		return mnEdit;
	}
}
