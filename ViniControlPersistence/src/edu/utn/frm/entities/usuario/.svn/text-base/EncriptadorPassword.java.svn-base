/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utn.frm.entities.usuario;

import java.security.MessageDigest;

/**
 *
 * @author Proyecto
 */
public class EncriptadorPassword {

    public static String encriptarPassword(String pass) {

        String passEncrypted = "";
        try {
            //passEncrypted = hash(pass);
        } catch (Exception e) {
            throw new Error("Error: Al encriptar el password. " + e.getMessage());
        }
        return pass;
    }

    /*private static String hash(String pass) throws Exception{
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(pass.getBytes());
    byte[] b = md.digest();
    int size = b.length;
    StringBuffer h = new StringBuffer(size);
    for (int i = 0; i < size; i++) {
    int u = b[i]&255;
    if (u<16){
    h.append("0"+Integer.toHexString(u));
    }else {
    h.append(Integer.toHexString(u));
    }
    }
    return h.toString();
    }*/
    private static String hash(String pass) throws Exception {
        MessageDigest messagedigest = MessageDigest.getInstance("MD5");
        messagedigest.update(pass.getBytes());
        return convert(messagedigest.digest());
    }

    /**
     * Convert a byte array into a printable format containing a
     * String of hexadecimal digit characters (two per byte).
     *
     * @param bytes Byte array representation
     */
    public static String convert(byte bytes[]) {

        StringBuffer sb = new StringBuffer(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            sb.append(convertDigit((int) (bytes[i] >> 4)));
            sb.append(convertDigit((int) (bytes[i] & 0x0f)));
        }
        return (sb.toString());

    }

    /**
     * [Private] Convert the specified value (0 .. 15) to the corresponding
     * hexadecimal digit.
     *
     * @param value Value to be converted
     */
    private static char convertDigit(int value) {

        value &= 0x0f;
        if (value >= 10) {
            return ((char) (value - 10 + 'a'));
        } else {
            return ((char) (value + '0'));
        }

    }
}
