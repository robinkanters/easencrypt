package com.robinkanters.easencrypt.cipher;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static javax.crypto.Cipher.ENCRYPT_MODE;

public class AesCipher extends BaseCipher {
    private static final String CIPHER_STRING = "AES/ECB/PKCS5Padding";
    private static final String KEY_ALGORITHM = "AES";
    private final Cipher cipher;

    public AesCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        cipher = Cipher.getInstance(CIPHER_STRING);
    }

    protected String getCipherString() {
        return CIPHER_STRING;
    }

    protected String getKeyAlgorithm() {
        return KEY_ALGORITHM;
    }

    protected Cipher getCipher() {
        return cipher;
    }
}
