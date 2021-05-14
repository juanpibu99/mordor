package es.mordor.mordorLoguer.model.BBDD;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class OracleDataBase implements AlmacenDatosDB {

	public ArrayList<Empleado> getCustomEmpleados(String where) {

		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "SELECT * FROM EMPLEADO";

		if (where != null)
			query += " WHERE " + where;

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			Empleado empleado;

			while (rs.next()) {
				empleado = new Empleado(rs.getInt("idEmpleado"), rs.getString("DNI"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("CP"), rs.getString("email"), rs.getDate("fechaNac"),
						rs.getString("cargo"), rs.getString("domicilio"),rs.getString("password"));

				empleados.add(empleado);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empleados;
	}
	public ArrayList<Empleado> getCustomEmpleadosOrder(String orderby) {

		ArrayList<Empleado> empleados = new ArrayList<Empleado>();

		DataSource ds = MyDataSource.getOracleDataSource();

		String query = "SELECT * FROM EMPLEADO";

		if (orderby != null)
			query += " ORDER BY " + orderby;

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query)) {

			Empleado empleado;

			while (rs.next()) {
				empleado = new Empleado(rs.getInt("idEmpleado"), rs.getString("DNI"), rs.getString("nombre"),
						rs.getString("apellidos"), rs.getString("CP"), rs.getString("email"), rs.getDate("fechaNac"),
						rs.getString("cargo"), rs.getString("domicilio"),rs.getString("password"));

				empleados.add(empleado);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return empleados;
	}

	@Override
	public ArrayList<Empleado> getEmpleadosPorCP(String cp) {
		// TODO Auto-generated method stub
		return getCustomEmpleados("CP='" + cp + "'");
	}

	@Override
	public ArrayList<Empleado> getEmpleadosPorCargo(String cargo) {
		return getCustomEmpleados("cargo='" + cargo + "'");
	}

	@Override
	public ArrayList<Empleado> getEmpleados() {
		return getCustomEmpleados(null);

	}

	@Override
	public Empleado getEmpleadoPorDNI(String dni) {
		ArrayList<Empleado> empleados = getCustomEmpleados("DNI='" + dni + "'");
		if (empleados.size() == 0)
			return null;
		else
			return empleados.get(0);
	}
	
	

	@Override
	public boolean updateEmpleado(Empleado empleado) {

		boolean actualizado = false;

		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();) {

			String query = "UPDATE EMPLEADO SET nombre='" + empleado.getNombre() + "', " +
												"apellidos='" + empleado.getApellidos() + "'," + 
												((empleado.getDomicilio()!=null)?"domicilio='" + empleado.getDomicilio() + "',":"") + 
												((empleado.getCP()!=null)?"CP='"	+ empleado.getCP() + "',":"") + 
												"email='" + empleado.getEmail() + "'," + 
												"fechaNac=TO_DATE('" + empleado.getFechaNac() + "','yyyy-mm-dd')," + 
												"cargo='" + empleado.getCargo() + "' " + 
												"WHERE DNI='" + empleado.getDNI() + "'";

			System.out.println(query);
			if(stmt.executeUpdate(query)==1)
				actualizado=true;

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return actualizado;

	}
	public boolean deleteEmpleado(String dni) {

		boolean borrado = false;
		String query = "DELETE FROM EMPLEADO WHERE DNI='" + dni + "'";
		DataSource ds = MyDataSource.getOracleDataSource();

		try (Connection con = ds.getConnection();
				Statement stmt = con.createStatement();
				) {


			if(stmt.executeUpdate(query)==1)
				borrado=true;

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return borrado;

	}
	public boolean authenticate (String login,String password) {
		boolean registrado=false;
		DataSource ds = MyDataSource.getOracleDataSource();
		String query = "SELECT COUNT(*) FROM EMPLEADO WHERE DNI=? AND PASSWORD=ENCRYPT_PASWD.encrypt_val(?)";
		try (Connection con = ds.getConnection();
				PreparedStatement pstmt = con.prepareStatement(query);
				){
			
			pstmt.setString(1, login);
			pstmt.setString(2, password);
			
			ResultSet rs=pstmt.executeQuery();
			
			rs.next();
			if(rs.getInt(1)==1)
				registrado=true;
			

		} catch (SQLException e) {

			e.printStackTrace();

		}
		
		
		return registrado;
	}
//	public boolean authenticate (String login,String password) {
//		boolean registrado=false;
//		DataSource ds = MyDataSource.getOracleDataSource();
//		String query = "SELECT COUNT(*) FROM EMPLEADO WHERE DNI='" + login + "' AND PASSWORD='"+password+"'";
//		try (Connection con = ds.getConnection();
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(query);) {
//
//			rs.next();
//			if(rs.getInt(1)==1)
//				registrado=true;
//			
//
//		} catch (SQLException e) {
//
//			e.printStackTrace();
//
//		}
//		
//		
//		return registrado;
//	}
//	
	public  ArrayList<Empleado> getEmpleadosOrdenadosBy(String field,int sort){
		String sorted="";
		if(sort==AlmacenDatosDB.ASCENDING) {
			sorted="asc";
		}else if(sort==AlmacenDatosDB.DESCENDING) {
			sorted="desc";
		}
				
		return getCustomEmpleadosOrder(field+" "+sorted);
	}
}
