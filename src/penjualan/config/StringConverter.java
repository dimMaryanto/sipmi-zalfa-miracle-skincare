/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjualan.config;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author dimmaryanto
 */
public class StringConverter {

    public static String getCurrency(Number number) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        return format.format(number);
    }

}