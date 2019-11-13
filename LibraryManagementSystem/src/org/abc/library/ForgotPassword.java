package org.abc.library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForgotPassword")
public class ForgotPassword extends HttpServlet
{
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
	String mail=request.getParameter("mail");
	// TODO Auto-generated method stub
	String url="jdbc:mysql://localhost:3306?user=root&password=9790270846";
    String query="select * from test.userdetails where UserName=?";
	
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url);
		PreparedStatement preparedStatement=connection.prepareStatement(query);
		preparedStatement.setString(1, mail);
		ResultSet resultSet=preparedStatement.executeQuery();
		if(resultSet.next())
		{
			String userName=resultSet.getString("UserName");
			if(userName.equals(mail))
			{
				hello(mail);
			}
		}
	}
	catch (Exception e1) 
	{
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
public void hello(String mail)
{
	String url="jdbc:mysql://localhost:3306?user=root&password=9790270846";
    String query9="insert into test.ForgotPassword values(?,?)";

try 
{
	Class.forName("com.mysql.jdbc.Driver");
	Connection connection1=DriverManager.getConnection(url);
	PreparedStatement preparedStatement=connection1.prepareStatement(query9);
	preparedStatement.setString(1, mail);
	Random random=new Random();
	int number=random.nextInt(100000);
	if(number<0)
	{
		number=number*-1;
	}
	preparedStatement.setInt(2, number);
	preparedStatement.executeUpdate();
	connection1.close();
}
catch (Exception e) 
{
	// TODO Auto-generated catch block
	e.printStackTrace();
}
}
}
