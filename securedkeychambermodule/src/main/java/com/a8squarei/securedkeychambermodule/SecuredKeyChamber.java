package com.a8squarei.securedkeychambermodule;

import android.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Ram Mandal(ram.mandal@8squarei.com) on 12/21/2018.
 * Programmed on Dell Xps 15 9560
 */
public class SecuredKeyChamber {

    static {
        System.loadLibrary("securedkeychamber");
    }

    public native String getKey();

    public native String getInitVector();

    public native String getAlgorithm();

    public native String getCharSet();

    public native String getTransformation();

    public String encrypt(String msg) {
        if (msg.isEmpty()) return "Message should not be empty";

        try {
            IvParameterSpec iv = new IvParameterSpec(getInitVector().getBytes(getCharSet()));
            SecretKeySpec skeySpec = new SecretKeySpec(getKey().getBytes(getCharSet()), getAlgorithm());

            Cipher cipher = Cipher.getInstance(getTransformation());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(msg.getBytes());
            return Base64.encodeToString(encrypted, Base64.DEFAULT);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String decrypt(String encrypted) {
        if (encrypted.isEmpty()) return "Message should not be empty";

        try {
            IvParameterSpec iv = new IvParameterSpec(getInitVector().getBytes(getCharSet()));
            SecretKeySpec skeySpec = new SecretKeySpec(getKey().getBytes(getCharSet()), getAlgorithm());
            Cipher cipher = Cipher.getInstance(getTransformation());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.decode(encrypted, Base64.DEFAULT));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();

            /**
             * @exception IllegalStateException if this cipher is in a wrong state
             * (e.g., has not been initialized)
             * @exception IllegalBlockSizeException if this cipher is a block cipher,
             * no padding has been requested (only in encryption mode), and the total
             * input length of the data processed by this cipher is not a multiple of
             * block size; or if this encryption algorithm is unable to
             * process the input data provided.
             * @exception BadPaddingException if this cipher is in decryption mode,
             * and (un)padding has been requested, but the decrypted data is not
             * bounded by the appropriate padding bytes
             * @see
             * {@link Cipher#doFinal()(Cipher.java:1919)}
             */
            if (ex instanceof IllegalArgumentException    //exception message  : bad base 64
                    || ex instanceof IllegalBlockSizeException) {
                return "Message might not be encrypted yet";
            } else if (ex instanceof BadPaddingException) {
                return "Encrypted data is not bounded by the appropriate padding bytes";
            }
        }

        return null;
    }
}
