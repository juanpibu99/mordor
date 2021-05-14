package es.mordor.mordorLloguer.app;

import java.awt.EventQueue;

import com.alee.laf.WebLookAndFeel;

import es.mordor.mordorLloguer.controladores.ControladorPrincipal;
import es.mordor.mordorLloguer.vistas.VistaPrincipal;
import es.mordor.mordorLoguer.model.BBDD.AlmacenDatosDB;
import es.mordor.mordorLoguer.model.BBDD.OracleDataBase;

public class App {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebLookAndFeel.install();
					VistaPrincipal frame = new VistaPrincipal();
					AlmacenDatosDB modelo=new OracleDataBase();
					ControladorPrincipal c = new ControladorPrincipal(frame,modelo);
					c.go();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
