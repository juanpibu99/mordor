package es.mordor.mordorLloguer.controladores;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;

import com.alee.laf.table.editors.WebDateEditor;

import es.mordor.mordorLloguer.config.MyConfig;
import es.mordor.mordorLloguer.vistas.VistaCargar;
import es.mordor.mordorLloguer.vistas.VistaLogin;
import es.mordor.mordorLloguer.vistas.VistaPreferencias;
import es.mordor.mordorLloguer.vistas.VistaPrincipal;
import es.mordor.mordorLloguer.vistas.VistaTablaEmpleados;
import es.mordor.mordorLoguer.model.BBDD.AlmacenDatosDB;
import es.mordor.mordorLoguer.model.BBDD.Empleado;
import es.mordor.mordorLoguer.model.BBDD.OracleDataBase;


public class ControladorPrincipal implements ActionListener{
	private static VistaPrincipal vista;
	private VistaLogin vistaLogin;
	private VistaPreferencias vistaPreferencias;
	private VistaTablaEmpleados vistaTablaEmpleados;
	private AlmacenDatosDB almacenDatos;
	private ControladorEmpleados cEmpleados;

	
	public ControladorPrincipal(VistaPrincipal vista,AlmacenDatosDB almacenDatos) {
		super();
		this.vista = vista;
		this.almacenDatos=almacenDatos;
		inicializar();
	}
	
	private void inicializar() {
		
		vista.getBtnLogout().setEnabled(false);
		vista.getBtnEmploye().setEnabled(false);
		
		//add action listeners
		vista.getBtnLogin().addActionListener(this);
		vista.getBtnLogout().addActionListener(this);
		vista.getMntmExit().addActionListener(this);
		vista.getMntmPreferences().addActionListener(this);
		vista.getBtnEmploye().addActionListener(this);
		//add action comands
		vista.getBtnLogin().setActionCommand("Generar login");
		vista.getBtnLogout().setActionCommand("Logout");
		vista.getMntmExit().setActionCommand("Exit");
		vista.getMntmPreferences().setActionCommand("Preferences");
		vista.getBtnEmploye().setActionCommand("TablaEmpleados");
	}

	public void go() {
		vista.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String comando = arg0.getActionCommand();
		if (comando.equals("Generar login")) {
			generarLogin();
		
		}else if (comando.equals("Exit")) {
		
		}else if (comando.equals("Preferences")) {
			crearPreferencias();
		}else if (comando.equals("RegistrarDatosOracle")) {
			cambiarDatosProperties();
		}else if (comando.equals("TablaEmpleados")) {
			openEmployees();
		}else if (comando.equals("Login")) {
			cargarLogin();
		}else if (comando.equals("Logout")) {
			userLogout();
		}
		
		
	}
	
	static void addInternalFrame(JInternalFrame jif) {
		vista.getDesktopPane().add(jif);
		centrar(jif);
		jif.setVisible(true);
		
	}

	private void openEmployees() {
		if(!isOpen(vistaTablaEmpleados)) {
		
			vistaTablaEmpleados=new VistaTablaEmpleados();
	
		cEmpleados=new ControladorEmpleados(vistaTablaEmpleados,almacenDatos);
		cEmpleados.go();
		}
	}

	private void cargarLogin() {
			
			SwingWorker<Boolean,Void> task=new SwingWorker<Boolean,Void>(){
			
			
			@Override
			protected Boolean doInBackground() throws Exception {
				
				String cadena="";
				char[] password = vistaLogin.getPasswordField().getPassword();
				for(int i=0;i<password.length;i++) {
					cadena+=password[(char)i];
				}
				boolean autentificado=false;
				vistaLogin.getProgressBar().setVisible(true);
				
				try {
					
					autentificado=almacenDatos.authenticate( vistaLogin.getUserField().getText(),cadena );
				} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				return autentificado;
			}
			 
			 
			 @Override
			 protected void done() {
				 try {
					if(get()) {
						vista.getBtnEmploye().setEnabled(true);
						vista.getBtnLogin().setEnabled(false);
						vista.getBtnLogout().setEnabled(true);
						JOptionPane.showMessageDialog(vista, "Login succes", "Succes", JOptionPane.INFORMATION_MESSAGE);
						vistaLogin.doDefaultCloseAction();
					}else {
						JOptionPane.showMessageDialog(vista, "password or username incorrects", "Error", JOptionPane.ERROR_MESSAGE);
					}
					 
					
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				 vistaLogin.getProgressBar().setVisible(false);
			
			 }
			
		};
		task.execute();
		
		
		
	}
		

	

	private void userLogout() {
		vista.getBtnEmploye().setEnabled(false);
		vista.getBtnLogin().setEnabled(true);
		vista.getBtnLogout().setEnabled(false);
		if(isOpen(vistaTablaEmpleados))
			vistaTablaEmpleados.doDefaultCloseAction();
		
	}



	

	private void cambiarDatosProperties() {
		
		String cadena="";
		char[] c = vistaPreferencias.getTextFieldPassword().getPassword();
		
		for(int i=0;i<c.length;i++) {
			cadena+=c[(char)i];
		}
		
		MyConfig.getInstancia().setOracleDriver(vistaPreferencias.getTextFieldDriver().getText());
		MyConfig.getInstancia().setOracleURL(vistaPreferencias.getTextFieldURL().getText());
		MyConfig.getInstancia().setOracleUsername(vistaPreferencias.getTextFieldUser().getText());
		MyConfig.getInstancia().setOraclePassword(cadena);
		
		
	}

	private void crearPreferencias() {
		vistaPreferencias= new VistaPreferencias();
		vistaPreferencias.setVisible(true);
		
		vistaPreferencias.getTextFieldDriver().setText(MyConfig.getInstancia().getOracleDriver());
		vistaPreferencias.getTextFieldURL().setText(MyConfig.getInstancia().getOracleURL());
		vistaPreferencias.getTextFieldUser().setText(MyConfig.getInstancia().getOracleUsername());
		vistaPreferencias.getTextFieldPassword().setText(MyConfig.getInstancia().getOraclePassword());
		
		vistaPreferencias.getBtnSave().addActionListener(this);
		
		vistaPreferencias.getBtnSave().setActionCommand("RegistrarDatosOracle");
		
	}

	private void generarLogin() {
		if(!isOpen(vistaLogin)) {
			vistaLogin=new VistaLogin();
			addInternalFrame(vistaLogin);
			
			vistaLogin.getBtnLogin().addActionListener(this);
			vistaLogin.getBtnLogin().setActionCommand("Login");
			
		}else {
			JOptionPane.showMessageDialog(vista, "This window is already generated", "Error", JOptionPane.ERROR_MESSAGE);
			
		}
		
	}
	public boolean isOpen(JInternalFrame jif) {
		boolean existe=false;
		JInternalFrame[] frames= vista.getDesktopPane().getAllFrames();
		for(JInternalFrame frame:frames)
			if(frame==jif)
				existe=true;
		return existe;
	}
	public static void centrar(JInternalFrame jif) {
		Dimension deskSize=vista.getDesktopPane().getSize();
		Dimension ifSize=jif.getSize();
		jif.setLocation((deskSize.width - ifSize.width) / 2,(deskSize.height-ifSize.height)/ 2);
	}

	

	

}
