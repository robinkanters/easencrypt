package com.robinkanters.easencrypt.cipher;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public class DesCipher extends BaseCipher {
    private final Cipher cipher;

    public DesCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        cipher = Cipher.getInstance(getCipherString());
    }

    protected String getCipherString() {
        return "DES/ECB/PKCS5Padding";
    }

    protected String getKeyAlgorithm() {
        return "DES";
    }

    protected Cipher getCipher() {
        return cipher;
    }
}
