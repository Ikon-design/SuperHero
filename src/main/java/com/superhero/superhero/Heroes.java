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
        request.getRequestDispatcher("/pages/heroes/index.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        DatabaseConnection.insertHero(request.getParameter("name"), latitude, longitude, request.getParameter("phone"), request.getParameter("fIncident"), request.getParameter("sIncident"), request.getParameter("tIncident"));
        response.sendRedirect(request.getContextPath() + "/pages/heroes/success.jsp");
    }

}