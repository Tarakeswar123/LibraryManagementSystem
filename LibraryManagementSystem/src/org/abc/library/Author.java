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

@WebServlet("Author")
public class Author extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	String bookAuthor=request.getParameter("author");
	PrintWriter printWriter=response.getWriter();
	// TODO Auto-generated method stub
	String url="jdbc:mysql://localhost:3306?user=root&password=9790270846";
	String query="select * from test.bookmanagementsystem where BookAuthor=?";
	
	try 
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection connection=DriverManager.getConnection(url);
		
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		
		preparedStatement.setString(1, bookAuthor);
		ResultSet resultSet=preparedStatement.executeQuery();

			if(resultSet.next())
			{
			String bookTitle1=resultSet.getString("BookTitle");
			String bookAuthor1=resultSet.getString("BookAuthor");
			String bookType1=resultSet.getString("BookType");
			double bookPrice=resultSet.getDouble("BookPrice");
			printWriter.println(bookTitle1+" "+bookAuthor1+" "+bookType1+" "+bookPrice);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("Home.html");
			requestDispatcher.include(request, response);
		    }
			else
			{
				printWriter.println("Requested Book Not Found in Library");
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
