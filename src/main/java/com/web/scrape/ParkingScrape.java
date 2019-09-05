package com.web.scrape;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ParkingScrape {
    public static void main(String[] args) {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        Thread thread = new Thread();
        try
        {
            while(true)
            {
                System.out.println(new Date());
                String searchUrl = "https://www.utdallas.edu/services/transit/garages/";
                HtmlPage page = client.getPage(searchUrl);
                final Page s = page.getFrames().get(0).getEnclosedPage();
                getTableData((HtmlPage) s, "ps1");
                getTableData((HtmlPage) s, "ps3");
                getTableData((HtmlPage) s, "ps4");
                System.out.println("Sleeping for a minute will fetch data soon");
                thread.sleep(Duration.ofMinutes(Integer.valueOf(args[0])).toMillis());

            }
        } catch (Exception e) {
            System.out.println("Error occured while fetching data from the site");
        }
    }

    private static void getTableData(HtmlPage s, String tableId) {
        final HtmlTable htmlTable = (HtmlTable) s.getElementById(tableId);
        System.out.println("Parking space : " + htmlTable.getCaptionText());
        final HtmlTableHeader header = htmlTable.getHeader();
        final List<HtmlTableRow> headerRows = header.getRows();
        for (HtmlTableRow headerRow : headerRows) {
            final List<HtmlTableCell> cells = headerRow.getCells();
            cells.forEach(e -> System.out.print(e.asText() + " | "));
        }
        System.out.println();
        final List<HtmlTableBody> htmlTableBodies = htmlTable.getBodies();
        for (Iterator<HtmlTableBody> it = htmlTableBodies.iterator(); it.hasNext(); ) {
            HtmlTableBody body = it.next();
            final List<HtmlTableRow> rows = body.getRows();
            for (HtmlTableRow row: rows) {
                final List<HtmlTableCell> cells = row.getCells();
                for (HtmlTableCell cell: cells) {
                    System.out.print(cell.asText() + " | ");
                }
                System.out.println();
            }
        }
    }
}
