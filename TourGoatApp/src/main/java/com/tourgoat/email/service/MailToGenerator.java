/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.email.service;

import java.awt.Desktop;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author weynishetzula
 */
public class MailToGenerator {
  public static void mailto(List<String> recipients, String subject,
            String body) throws IOException, URISyntaxException {
        String uriStr = String.format("mailto:%s?subject=%s&body=%s",
                join(",", recipients), // use semicolon ";" for Outlook!
                urlEncode(subject),
                urlEncode(body));
        URI mailTo = new URI(uriStr);
      Desktop.getDesktop().browse(new URI(uriStr));
        //Desktop.getDesktop().mail(mailTo);
    }

    private static final String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String join(String sep, Iterable<?> objs) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objs) {
            if (sb.length() > 0) {
                sb.append(sep);
            }
            sb.append(obj);
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        mailto(Arrays.asList("weynile@gmail.com"), "Hello!",
                "http://www.google.com");
    }
  
}
