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

@WebServlet("/RemoveName")
public class RemoveName extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	String title=request.getParameter("rtitle");
	PrintWriter printWriter=response.getWriter();
	// TODO Auto-generated method stub
	String url="jdbc:mysql://localhost:3306?user=root&password=9790270846";
	String query9="select * from test.bookmanagementsystem where BookTitle=?";
	
	try 
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection9=DriverManager.getConnection(url);
		PreparedStatement preparedStatement=connection9.prepareStatement(query9);
		preparedStatement.setString(1, title);
		ResultSet resultSet=preparedStatement.executeQuery();
		while(resultSet.next())
		{
			String name=resultSet.getString("BookTitle");
			String aUthor=resultSet.getString("BookAuthor");
			String type=resultSet.getString("BookType");
			double price=resultSet.getDouble("BookPrice");
		    printWriter.println("The Book to be Deleted:");
			printWriter.println(name+" "+aUthor+" "+type+" "+price);
		}
	} 
	catch (Exception e1) 
	{
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	String query="delete from test.bookmanagementsystem where BookTitle=?";
	
	try 
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url);
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, title);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("Home.html");
		requestDispatcher.include(request, response);
		preparedStatement.executeUpdate();
		connection.close();
	}
	catch (Exception e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
}
}
