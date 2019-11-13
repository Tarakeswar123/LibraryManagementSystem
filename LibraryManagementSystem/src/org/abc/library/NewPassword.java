package org.abc.library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/NewPassword")
public class NewPassword extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	String mail=request.getParameter("mail");
	String newPassword=request.getParameter("newPassword");
	String cPassword=request.getParameter("cPassword");
	PrintWriter printWriter=response.getWriter();
	// TODO Auto-generated method stub
	String url="jdbc:mysql://localhost:3306?user=root&password=9790270846";
	String query="update test.userdetails set Password=? where UserName=?";
	
	try 
	{
		Connection connection=DriverManager.getConnection(url);
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, newPassword);
		preparedStatement.setString(2, mail);
		if(newPassword.equals(cPassword))
		{
			preparedStatement.executeUpdate();
			connection.close();
		}
		else
		{
			printWriter.println("Entered Passwords are Not Matching");
		}
	}
	catch (SQLException e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
