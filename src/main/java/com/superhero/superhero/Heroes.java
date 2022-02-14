package com.superhero.superhero;

import classes.DatabaseConnection;
import enums.Incident;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "Heroes", value = "/heroes")
public class Heroes extends HttpServlet {

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int count = Incident.values().length;
        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = connection.prepareStatement("select * from heroes");
            request.setAttribute("count", count);
            request.getRequestDispatcher("/pages/heroes/index.jsp").forward(request, response);
        } catch (Exception err) {
            err.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Connection connection = DatabaseConnection.initializeDatabase();
            PreparedStatement statement = connection.prepareStatement("insert into heroes values(?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, 0);
            statement.setString(2, request.getParameter("name"));
            double latitude = Double.parseDouble(request.getParameter("latitude"));
            statement.setDouble(3, latitude);
            double longitude = Double.parseDouble(request.getParameter("longitude"));
            statement.setDouble(4, longitude);
            statement.setString(5, request.getParameter("phone"));
            statement.setString(6, request.getParameter("fIncident"));
            statement.setString(7, request.getParameter("sIncident"));
            statement.setString(8, request.getParameter("tIncident"));
            statement.executeUpdate();
            statement.close();
            connection.close();
            response.sendRedirect(request.getContextPath() + "/pages/heroes/success.jsp");
        } catch (Exception err) {
            err.printStackTrace();
            PrintWriter print = response.getWriter();
            print.println(request.getParameter("name"));
            print.println(request.getParameter("latitude"));
            print.println(request.getParameter("longitude"));
            print.println(request.getParameter("phone"));
            print.println(request.getParameter("fIncident"));
            print.println(request.getParameter("sIncident"));
            print.println(request.getParameter("tIncident"));
        }
    }

}