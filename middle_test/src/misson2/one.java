package misson2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class one {

        void testPost(String urlStr) {
            try {
                URL url = new URL(urlStr);
                URLConnection con = url.openConnection();
                con.setDoOutput(true);
                con.setRequestProperty("Pragma:", "no-cache");
                //con.setRequestProperty("Cache-Control", "no-cache");
                con.setRequestProperty("Content-Type", "text/xml; charset=utf-8");

                OutputStreamWriter out = new OutputStreamWriter(con
                        .getOutputStream());
                String xmlInfo = getXmlInfo();
                System.out.println("urlStr=" + urlStr);
                System.out.println("xmlInfo=" + xmlInfo);
                out.write(new String(xmlInfo.getBytes("ISO-8859-1")));
                out.flush();
                out.close();
                BufferedReader br = new BufferedReader(new InputStreamReader(con
                        .getInputStream()));
                String line = "";
                for (line = br.readLine(); line != null; line = br.readLine()) {
                    System.out.println(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String getXmlInfo() {
            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
            sb.append("<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
            sb.append("<soap:Body>");
            sb.append("<GetUserPostTypes xmlns=\"http://172.20.2.52:84/\" />");
            sb.append("</soap:Body>\n");
            sb.append("</soap:Envelope>");
            return sb.toString();
        }

        public static void main(String[] args) {
            String url = "http://172.20.2.52:84/userpostservice.asmx";
            new one().testPost(url);
        }
}
