package ru.academits.gerasimenko.csv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CsvToHtmlConverter {
    public static void convert(String csvFileName) throws FileNotFoundException {
        final int stringStartIndex = 0;
        final String csvExtension = ".csv";
        final String htmlExtension = ".html";

        String fileName = csvFileName.substring(stringStartIndex, csvFileName.lastIndexOf(csvExtension));
        String htmlFileName = fileName + htmlExtension;

        try (Scanner fileScanner = new Scanner(new FileInputStream(csvFileName));
             PrintWriter fileWriter = new PrintWriter(htmlFileName)) {
            HtmlCreator.initHtml(fileWriter);
            HtmlCreator.addHead(fileWriter, fileName);
            HtmlCreator.createTable(fileWriter);

            while (fileScanner.hasNextLine()) {
                String fileLine = fileScanner.nextLine();

                if (!fileLine.contains("\"")) {
                    HtmlCreator.addTableRow(fileWriter, fileLine.split(","));
                    continue;
                }

                System.out.println("gg");
            }

            fileWriter.println("</table>");
            fileWriter.println("</body>");
            fileWriter.print("</html>");
        }
    }
}