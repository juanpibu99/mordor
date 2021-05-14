package es.mordor.mordorLloguer.vistas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.alee.laf.table.WebTable;

import net.miginfocom.swing.MigLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class VistaTablaEmpleados extends JInternalFrame {
	private  WebTable table; ;
	private JComboBox comboBoxOrder;
	private JComboBox comboBoxSort;
	private DefaultComboBoxModel<String> dcmOrder;
	private DefaultComboBoxModel<String> dcmSort;
	private JButton btnAdd;
	private JButton btnRemove;
	private JMenuItem mntmAddRow;
	private JMenuItem mntmDeleteRow;
	private JPopupMenu popupMenu;
	

	/**
	 * Create the frame.
	 */
	public VistaTablaEmpleados() {
		setClosable(true);
		setBounds(100, 100, 867, 556);
		
		JPanel panel = new JPanel();
		
		JPanel panel_1 = new JPanel();
		
		btnAdd = new JButton("Add");
		
		btnRemove = new JButton("Remove");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 833, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(56)
							.addComponent(btnAdd)
							.addGap(29)
							.addComponent(btnRemove)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAdd)
						.addComponent(btnRemove))
					.addContainerGap(20, Short.MAX_VALUE))
		);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table = new WebTable();
		table.setAutoResizeMode ( JTable.AUTO_RESIZE_LAST_COLUMN );
        table.setVisibleRowCount ( 5 );
        table.optimizeColumnWidths ( true );
        table.setOptimizeRowHeight ( true );
        table.setEditable ( true );
        popupMenu = new JPopupMenu();
		
		mntmAddRow = new JMenuItem("Add row");
		popupMenu.add(mntmAddRow);
		
		mntmDeleteRow = new JMenuItem("Delete row");
		popupMenu.add(mntmDeleteRow);
		scrollPane.setViewportView(table);
	
		
		panel_1.setLayout(new MigLayout("", "[][][112.00][][][][117.00]", "[]"));
		
		JLabel lblOrderBy = new JLabel("Order by");
		panel_1.add(lblOrderBy, "cell 0 0");
		
		comboBoxOrder = new JComboBox();
		comboBoxOrder.setModel(new DefaultComboBoxModel(new String[] {"DNI", "Nombre", "Domicilio", "CP", "Email", "FechaNac", "Cargo"}));
		panel_1.add(comboBoxOrder, "cell 1 0 2 1,growx");
		
		JLabel lblNewLabel = new JLabel("Sort");
		panel_1.add(lblNewLabel, "cell 5 0,alignx trailing");
		
		comboBoxSort = new JComboBox();
		comboBoxSort.setModel(new DefaultComboBoxModel(new String[] {"Ascendant", "Descendant"}));
		panel_1.add(comboBoxSort, "cell 6 0,growx");
		getContentPane().setLayout(groupLayout);

	}






	public JMenuItem getMntmAddRow() {
		return mntmAddRow;
	}






	public JMenuItem getMntmDeleteRow() {
		return mntmDeleteRow;
	}






	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}






	public JButton getBtnAdd() {
		return btnAdd;
	}






	public JButton getBtnRemove() {
		return btnRemove;
	}






	public WebTable getTable() {
		return table;
	}



	public JComboBox getComboBoxOrder() {
		return comboBoxOrder;
	}



	public JComboBox getComboBoxSort() {
		return comboBoxSort;
	}
}
