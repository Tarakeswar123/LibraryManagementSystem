package org.abc.library;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Login")
public class Login extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	String userName=request.getParameter("userName");
	String password=request.getParameter("padssword");
	PrintWriter printWriter=response.getWriter();
	// TODO Auto-generated method stub
	String url="jdbc:mysql://localhost:3306?user=root&password=9790270846";
	String query="select * from test.userdetails where UserName=? and Password=?";
	
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url);
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, userName);
		preparedStatement.setString(2, password);
		ResultSet resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
		{
			String name=resultSet.getString("Name");
			printWriter.println("Welcome:"+name);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("Home.html");
			requestDispatcher.include(request, response);
		}
		else
		{
			printWriter.println("Not a Valid UserName or Password");
		}
	}
	catch (Exception e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
