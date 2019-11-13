package org.abc.library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NewUser")
public class NewUser extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	String name=request.getParameter("name");
	String mobile=request.getParameter("mobile");
	String userName=request.getParameter("userName");
	String password=request.getParameter("password");
	String confirmPassword=request.getParameter("cPassword");
	PrintWriter printWriter=response.getWriter();
	// TODO Auto-generated method stub
	String url="jdbc:mysql://localhost:3306?user=root&password=9790270846";
	String query="insert into test.userdetails values(?,?,?,?)";
	
	try 
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url);
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, name);
		preparedStatement.setString(2, mobile);
		preparedStatement.setString(3, userName);
		preparedStatement.setString(4, password);
		if(confirmPassword.equals(password))
		{
		preparedStatement.executeUpdate();
		}
		connection.close();
	} 
	catch (Exception e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
}
