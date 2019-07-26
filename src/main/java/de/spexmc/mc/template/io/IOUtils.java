package de.spexmc.mc.template.io;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import de.spexmc.mc.template.util.Messenger;

/**
 * Created by Lara on 23.07.2019 for template
 */
public final class IOUtils {
  public static void readURL(StringBuilder builder, URLConnection connection) throws IOException {
    if (connection != null && connection.getInputStream() != null) {
      try (final InputStreamReader streamReader = new InputStreamReader(connection.getInputStream(), Charset.defaultCharset());
           final BufferedReader bufferedReader = new BufferedReader(streamReader)) {
        int cp;
        while ((cp = bufferedReader.read()) != -1) {
          builder.append((char) cp);
        }
      }
    }
  }

  public static String getSiteContent(String urlString) {
    try {
      final URL url = new URL(urlString);
      final URLConnection connection = url.openConnection();
      final InputStream inputStream = connection.getInputStream();
      String encoding = connection.getContentEncoding();
      encoding = encoding == null ? "UTF-8" : encoding;
      return toString(inputStream, encoding);
    } catch (IOException ex) {
      Messenger.administratorMessage(ex.getMessage());
    }
    return null;
  }

  private static String toString(InputStream inputStream, String encoding) {
    try (final ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
      final byte[] buffer = new byte[8192];
      int length;
      while ((length = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, length);
      }
      return new String(outputStream.toByteArray(), encoding);
    } catch (IOException ex) {
      Messenger.administratorMessage(ex.getMessage());
    }
    return null;
  }

  public String formatString(String str) {
    return str
        .replace("&", "§").replace("<0>", "●")
        .replace("<1>", "✔").replace("<2>", "✖")
        .replace("<3>", "✿").replace("<4>", "★")
        .replace("<5>", "☆").replace("<6>", "»")
        .replace("<7>", "«").replace("<8>", "➤")
        .replace("<9>", "➜").replace("<10>", "➲")
        .replace("<11>", "➼").replace("<12>", "✦")
        .replace("<13>", "✪").replace("<14>", "✧")
        .replace("<15>", "✩").replace("<16>", "✫")
        .replace("<17>", "✬").replace("<18>", "✹")
        .replace("<19>", "❃").replace("<20>", "❀")
        .replace("<21>", "❤").replace("<22>", "♣")
        .replace("<23>", "♦").replace("<24>", "♠")
        .replace("<25>", "►").replace("<26>", "►")
        .replace("<27>", "◄").replace("<28>", "☃")
        .replace("<29>", "✚").replace("<30>", "❆")
        .replace("<31>", "❂").replace("<32>", "✮")
        .replace("<33>", "✭").replace("<34>", "✯")
        .replace("<35>", "✰").replace("<36>", "✤")
        .replace("<37>", "✾").replace("<38>", "❉")
        .replace("<39>", "☻").replace("<40>", "✘");
  }
}
