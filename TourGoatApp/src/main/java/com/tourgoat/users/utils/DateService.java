/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tourgoat.users.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author fitsum
 */
public final class DateService {

    public static Date getDefultDate() {

        int year = 0000;
        int month = 00;
        int day = 00;

        String date = year + "/" + month + "/" + day;
        java.util.Date utilDate = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            utilDate = formatter.parse(date);

        } catch (ParseException e) {
            System.out.println("Defult Date issue" + e.toString());
        }

        return utilDate;

    }

    public static Date getTodayDate() {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String date = year + "/" + month + "/" + day;
        java.util.Date utilDate = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            utilDate = formatter.parse(date);

        } catch (ParseException e) {
            System.out.println("Defult Date issue" + e.toString());
        }

        return utilDate;

    }

    public static Date formatDate(Date dob) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dob);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        String date = year + "/" + month + "/" + day;
        java.util.Date utilDate = null;

        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
            utilDate = formatter.parse(date);

        } catch (ParseException e) {
            System.out.println("Defult Date issue" + e.toString());
        }

        return utilDate;

    }
}
