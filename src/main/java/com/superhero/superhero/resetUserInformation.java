package com.superhero.superhero;


import classes.DatabaseConnection;
import classes.UserInformation;
import enums.Incident;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet(name = "reset information", value = "/resetInformation")
public class resetUserInformation extends HttpServlet {
    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("test");
        UserInformation.resetInformation();
        response.sendRedirect("incidents");

    }
}
