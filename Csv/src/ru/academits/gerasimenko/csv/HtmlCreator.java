package ru.academits.gerasimenko.csv;

import java.io.PrintWriter;

public class HtmlCreator {
    public static void initHtml(PrintWriter fileWriter) {
        fileWriter.println("<!DOCTYPE html>");
        fileWriter.println("<html lang=\"ru\">");
    }

    public static void addHead(PrintWriter fileWriter, String fileName) {
        fileWriter.println("<head>");
        fileWriter.println("\t<meta charset=\"utf-8\">");
        fileWriter.println("\t<title>" + fileName + "</title>");
        fileWriter.println("</head>");
    }

    public static void createTable(PrintWriter fileWriter) {
        fileWriter.println("<body>");
        fileWriter.println("<table>");
    }

    public static void addTableRow(PrintWriter fileWriter, String[] rowDetails) {
        fileWriter.println("\t<tr>");

        for (String detail : rowDetails) {
            fileWriter.println("\t\t<td>" + detail + "</td>");
        }

        fileWriter.println("\t</tr>");
    }
}