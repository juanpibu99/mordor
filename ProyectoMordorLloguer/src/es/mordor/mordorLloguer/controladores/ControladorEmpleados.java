package es.mordor.mordorLloguer.controladores;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import com.alee.laf.table.editors.WebDateEditor;

import es.mordor.mordorLloguer.vistas.VistaCargar;
import es.mordor.mordorLloguer.vistas.VistaLogin;
import es.mordor.mordorLloguer.vistas.VistaPreferencias;
import es.mordor.mordorLloguer.vistas.VistaPrincipal;
import es.mordor.mordorLloguer.vistas.VistaTablaEmpleados;
import es.mordor.mordorLoguer.model.BBDD.AlmacenDatosDB;
import es.mordor.mordorLoguer.model.BBDD.Empleado;
import es.mordor.mordorLoguer.model.BBDD.OracleDataBase;

public class ControladorEmpleados implements ActionListener,TableModelListener{
	
	private VistaTablaEmpleados vista;
	private VistaCargar vistaCargar;
	private AlmacenDatosDB modelo;
	
	private MyTableModel mtm;
	private static ControladorEmpleados controlador;
	
	public ControladorEmpleados(VistaTablaEmpleados vistaTablaEmpleados,AlmacenDatosDB almacenDatos) {
		this.vista=vistaTablaEmpleados;
		this.modelo=almacenDatos;
		controlador=this;
		inicializar();
	}
	
	private void inicializar() {
		
		vista.getBtnAdd().addActionListener(this);
		vista.getBtnRemove().addActionListener(this);
		vista.getComboBoxOrder().addActionListener(this);
		vista.getComboBoxSort().addActionListener(this);
		vista.getMntmAddRow().addActionListener(this);
		vista.getMntmDeleteRow().addActionListener(this);
		
		vista.getComboBoxOrder().setActionCommand("Ordenar por campos");
		vista.getComboBoxSort().setActionCommand("Ordenar por sort");
		vista.getBtnAdd().setActionCommand("Añadir Empleado");
		vista.getBtnRemove().setActionCommand("Eliminar Empleado");
		vista.getMntmAddRow().setActionCommand("Añadir Empleado por mntm");
		vista.getMntmDeleteRow().setActionCommand("Eliminar Empleado por mntm");
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String comando = arg0.getActionCommand();
		if (comando.equals("Ordenar por campos")) {
			ordenar();
		}else if (comando.equals("Ordenar por sort")) {
			ordenar();
		}else if (comando.equals("Añadir Empleado")) {
			ordenar();
		}else if (comando.equals("Eliminar Empleado")) {
			ordenar();
		}else if (comando.equals("Añadir Empleado por mntm")) {
			ordenar();
		}else if (comando.equals("Ordenar por sort")) {
			ordenar();
		}
		
	}
	
	public void go() {
		generarVistaEmpleados();
	}
	
	public void generarVistaEmpleados() {
		
		
			ControladorPrincipal.addInternalFrame(vista);
			
			ordenar();
			
				
		
		
	}
	public void ordenar() {
		int num;
		if((String)vista.getComboBoxSort().getSelectedItem()=="Ascendant")
			num=1;
		else
			num=2;
		SwingWorker<Void,Void> task=new SwingWorker<Void,Void>(){
			
		ArrayList<Empleado>	empleados;
			@Override
			protected Void doInBackground() throws Exception {
				
				vistaCargar.setVisible(true);
				
				
				try {
				
					if(!isCancelled())
					empleados=modelo.getEmpleadosOrdenadosBy((String)vista.getComboBoxOrder().getSelectedItem(),num);
					
				} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				return null;
			}
			 
			 
			 @Override
			 protected void done() {
				if(!isCancelled()) {
				 try {
					
					 	mtm=new MyEmployeeTableModel(empleados);
						vista.getTable().setModel(mtm);
						
						vista.getTable().setDefaultEditor(Date.class, new WebDateEditor());
						
						JComboBox<String> comboBox=new JComboBox<String>();
						comboBox.addItem("mecanico");
						comboBox.addItem("administrativo");
						comboBox.addItem("comercial");
						comboBox.addItem("gerente");
						
						TableColumn column=vista.getTable().getColumn("Cargo");
						column.setCellEditor(new DefaultCellEditor(comboBox));
						
						mtm.addTableModelListener(controlador);
						
						vista.getTable().addMouseListener(new MouseAdapter(){
							@Override
							public void mouseReleased(MouseEvent e) {
								if(e.getButton()==MouseEvent.BUTTON3) {
									
									int row=vista.getTable().rowAtPoint(e.getPoint());
									
									if(vista.getTable().getSelectedRowCount()<=1) {
										vista.getTable().setSelectedRow(row);
										vista.getPopupMenu().show(vista.getTable(), e.getX(), e.getY());
										
									}else if(vista.getTable().getSelectedRowCount()>1) {
										vista.getPopupMenu().show(vista.getTable(), e.getX(), e.getY());
									}
										
									
								}
							}
						});
						
					
						
						
						
						vistaCargar.doDefaultCloseAction();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}else {
					vista.dispose();
				}
			
			 }
			
		};

		vistaCargar=new VistaCargar(task);
		ControladorPrincipal.addInternalFrame(vistaCargar);
		
		vistaCargar.getLblTask().setText("Cargando Tabla de Empleados");
		
		
		task.execute();
		
	}

	private class MyEmployeeTableModel extends MyTableModel<Empleado>{
			
			//private final String[] HEADER= {"DNI","Nombre","Apellidos","Domicilio","CP","Email","Nacimiento","Cargo"};
			//private final String[] HEADER;
			
		
			
			public MyEmployeeTableModel(List<Empleado> data) {
				super (new String[]{"DNI","Nombre","Apellidos","Domicilio","CP","Email","Nacimiento","Cargo"},data);
				
			
			}
		
			
			@Override
			public Class<?> getColumnClass(int colIndex) {
				switch(colIndex) {
				case 6: return Date.class;
				default: return String.class;
				}
				
			}
			
			
			@Override
			public void setValueAt(Object aValue,int rowIndex,int columnIndex) {
				switch(columnIndex) {
				case 1:
					 data.get(rowIndex).setNombre(aValue.toString());
					 break;
				
				case 2:
					 data.get(rowIndex).setApellidos(aValue.toString());
					 break;
				case 3:
					data.get(rowIndex).setDomicilio(aValue.toString());
					break;
				case 4:
					data.get(rowIndex).setCP(aValue.toString());
					break;
				case 5:
					data.get(rowIndex).setEmail(aValue.toString());
					break;
				case 6:
					java.util.Date fecha=(java.util.Date)aValue;
					 data.get(rowIndex).setFechaNac(new java.sql.Date(fecha.getTime()));
					 break;
				case 7:
					data.get(rowIndex).setCargo(aValue.toString());
					 break;
					
				}
				fireTableCellUpdated(rowIndex,  columnIndex);
	
				
			}
	
	
			@Override
			public Object getValueAt(int row, int col)  {
				switch(col) {
				case 0:
					return data.get(row).getDNI();	
				case 1:
					return data.get(row).getNombre();
				case 2:
					return data.get(row).getApellidos();
				case 3:
					return data.get(row).getDomicilio();
				case 4:
					return data.get(row).getCP();		
				case 5:
					return data.get(row).getEmail();
				case 6:
					return data.get(row).getFechaNac();
				case 7:
					return data.get(row).getCargo();
				}
				return null;
			}
		
			
			public void removeEmployee(String dni) {
				removeElement(new Empleado(dni));
			}
		}
		@Override
		public void tableChanged(TableModelEvent arg0) {
			
			if(arg0.getType()==TableModelEvent.UPDATE) {	
				MyEmployeeTableModel mtm=(MyEmployeeTableModel)vista.getTable().getModel();
				Empleado e= mtm.getElement(arg0.getFirstRow());
				
				SwingWorker<Void,Void> task=new SwingWorker<Void,Void>(){
					
		
						@Override
						protected Void doInBackground() throws Exception {
							
							vistaCargar.setVisible(true);
							
							
							try {
							
								if(!isCancelled())
									modelo.updateEmpleado(e);
								
							} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
							}
							return null;
						}
						 
						 
						 @Override
						 protected void done() {
							if(!isCancelled()) {
							 try {
									
									
									vistaCargar.doDefaultCloseAction();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							}else {
								vista.dispose();
							}
						
						 }
						
					};

					vistaCargar=new VistaCargar(task);
					ControladorPrincipal.addInternalFrame(vistaCargar);
					
					vistaCargar.getLblTask().setText("Updating DataBase");
					
					
					task.execute();
				
	
			}
					
		}
	
	
	
	

}
