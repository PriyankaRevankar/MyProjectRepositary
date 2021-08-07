package main.java.com.mindtree.campusmindstracks.utility;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
private static final String URL="jdbc:mysql://localhost:3306/webproject";
private static final String USERNAME="root";
private static final String PASSWORD="root";


//open connection
public static Connection getConnection()
{
	Connection con=null;
	try {
		con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return con;
	
}

//closing connection
public static void closingConnection(Connection con)
{
	try {
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

//opening preparedStatement
public static PreparedStatement openPreparedStatement()
{
	PreparedStatement pst=null;
	return pst;
}

//closing PreparedStatement
public static void closingPreparedStatement(PreparedStatement pst)
{
	try {
		pst.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

//opening statement
public static Statement openStatement()
{
	Statement st=null;
	return st;
}
//closing statement
public static void closingStatement(Statement st)
{
	try {
		st.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
//opening CollableStatement
public static CallableStatement openCallableStatement()
{
	CallableStatement cst=null;
	return cst;
}
//closing callableStatement
public static void closingCallableStatement(CallableStatement cst)
{
	try {
		cst.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}

//opening returnSet

//closing ResultSet
public static  ResultSet openingResultSet()
{
	ResultSet rs=null;
	return rs;
}
public static void closingResultSet(ResultSet rs)
{
	try {
		rs.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
}



































