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

@WebServlet("/Add")
public class Add extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	String bookTitle=request.getParameter("bookTitle");
	String bookAuthor=request.getParameter("bookAuthor");
	String bookType=request.getParameter("bookType");
	String bookPrice=request.getParameter("bookPrice");
	PrintWriter printWriter=response.getWriter();
	// TODO Auto-generated method stub
	String url="jdbc:mysql://localhost:3306?user=root&password=9790270846";
	String query="insert into test.bookmanagementsystem values(?,?,?,?)";
	
	try 
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection=DriverManager.getConnection(url);
		
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		
		preparedStatement.setString(1, bookTitle);
		preparedStatement.setString(2, bookAuthor);
		preparedStatement.setString(3, bookType);
		preparedStatement.setDouble(4, Double.parseDouble(bookPrice));
		preparedStatement.executeUpdate();
		
		connection.close();
		printWriter.println("Added Book To Details are:"+bookTitle+" "+bookAuthor+" "+bookType+" "+bookPrice);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("Home.html");
		requestDispatcher.include(request, response);
	} 
	catch (Exception e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
