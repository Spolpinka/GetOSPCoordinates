package org.mukhortov.requests;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.logging.Logger;

public class PiwiRequest {
    Logger logger = Logger.getLogger(PiwiRequest.class.getName());
    private final String URL = "https://catalog.api.2gis.com/3.0/items/geocode?q=";
    private final String URLAfterAdress = "&fields=items.point&key=";
    private final String APIKey = "55678712-22eb-4201-8494-b803a72d0489";
    public void getHTML() {
        try {

            Document doc = Jsoup.connect(URL).get();
            String html = doc.html();
            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void useVPN() {
        Runtime rt = Runtime.getRuntime();
        try {
            Process p = rt.exec("openvpn .ovpn");//уточнить + нужен файл .ovpn
        } catch (IOException e) {
            logger.warning(e.getMessage() + "Проблема в исполнении Runtime");
        }

    }
}
