/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.vin.common;

/**
 *
 * @author MinhKudo
 */
public class Tool {
    
    public static boolean checkNull(String input) {
        return input == null || input.equalsIgnoreCase("null") || input.equalsIgnoreCase("");
    }
}
