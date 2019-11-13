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

@WebServlet("/Otp")
public class Otp extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	String number=request.getParameter("otp");
	PrintWriter printWriter=response.getWriter();
	// TODO Auto-generated method stub
	String url="jdbc:mysqL://localhost:3306?user=root&password=9790270846";
	String query="select * from test.ForgotPassword where otp=?";
	
	try 
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(url);
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setInt(1, Integer.parseInt(number));
		ResultSet resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
		{
			int otp=resultSet.getInt("otp");
			if(otp==Integer.parseInt(number))
			{
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("NewPassword.html");
				requestDispatcher.include(request, response);
			}
			else
			{
				printWriter.println("Not a Valid Otp");
			}
		}
	} 
	catch (Exception e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
