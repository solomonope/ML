/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esl.iso;

/**
 *
 * @author Folorunsho Solomon
 */
public class mainClass {

    public static void mainApp(String[] a) throws Exception {
        
        StringBuilder builder =  new StringBuilder();
        Iso0 iso0 = new Iso0();

       byte [] r =  iso0.createPinBlock("4960212123516537", "1111");
       
       for(byte t :r){
           builder.append(String.format("%02X",t) );
       }
        
       System.out.println(builder.toString());
    }

}
