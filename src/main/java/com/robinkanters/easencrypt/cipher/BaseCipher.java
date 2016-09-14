package com.robinkanters.easencrypt.cipher;

import com.robinkanters.easencrypt.crypt.SymmetricalCipher;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static javax.crypto.Cipher.DECRYPT_MODE;
import static javax.crypto.Cipher.ENCRYPT_MODE;

public abstract class BaseCipher implements SymmetricalCipher {
    protected abstract String getCipherString();

    public String encrypt(String plain, SecretKey key) {
        try {
            return tryEncrypt(plain, key);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new EncryptException(e);
        }
    }

    public String decrypt(String cipher, byte[] key) throws InvalidKeyException {
        try {
            return tryDecrypt(cipher, key);
        } catch (DecoderException |IllegalBlockSizeException|BadPaddingException e) {
            throw new DecryptException(e);
        }
    }

    public SecretKey generateKey() {
        try {
            return KeyGenerator.getInstance(getKeyAlgorithm()).generateKey();
        } catch (NoSuchAlgorithmException ignored) {
            // should never occur
            return null;
        }
    }

    protected abstract String getKeyAlgorithm();

    protected abstract Cipher getCipher();

    protected String tryEncrypt(String plain, SecretKey key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        getCipher().init(ENCRYPT_MODE, key);
        return Hex.encodeHexString(getCipher().doFinal(plain.getBytes()));
    }

    protected String tryDecrypt(String cipherText, byte[] key) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, DecoderException {
        SecretKey originalKey = deserializeSecretKey(key);
        getCipher().init(DECRYPT_MODE, originalKey);
        byte[] decrypted = getCipher().doFinal(Hex.decodeHex(cipherText.toCharArray()));

        return new String(decrypted);
    }

    protected SecretKeySpec deserializeSecretKey(byte[] key) {
        return new SecretKeySpec(key, 0, key.length, getKeyAlgorithm());
    }
}
