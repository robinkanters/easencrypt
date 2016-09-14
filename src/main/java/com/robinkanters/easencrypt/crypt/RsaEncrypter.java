package com.robinkanters.easencrypt.crypt;

import java.security.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RsaEncrypter implements AsymmetricEncrypter {
    private static final int DEFAULT_KEY_SIZE = 2048;
    private static final String DEFAULT_ALGORITHM = "RSA";

    private final int keySize;

    public RsaEncrypter() {
        keySize = DEFAULT_KEY_SIZE;
    }

    public RsaEncrypter(int keySize) {
        this.keySize = keySize;
    }

    @Override
    @SuppressWarnings("WeakerAccess")
    public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        return generateKeyPair(keySize);
    }

    @Override
    @SuppressWarnings("WeakerAccess")
    public KeyPair generateKeyPair(int keySize) throws NoSuchAlgorithmException {
        final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(DEFAULT_ALGORITHM);
        keyGen.initialize(keySize);

        return keyGen.generateKeyPair();
    }

    /**
     * Encrypt the plain text using public key.
     *
     * @param text
     *          : original plain text
     * @param keyPair
     *          :The public key
     * @return Encrypted text
     */
    public byte[] encrypt(byte[] text, KeyPair keyPair) {
        return encrypt(text, keyPair.getPublic());
    }

    /**
     * Encrypt the plain text using public key.
     *
     * @param text
     *          : original plain text
     * @param key
     *          :The public key
     * @return Encrypted text
     */
    @Override
    public byte[] encrypt(byte[] text, PublicKey key) {
        try {
            return tryEncrypt(text, key);
        } catch (Exception e) {
            throw new EncryptionException(e);
        }
    }

    private byte[] tryEncrypt(byte[] text, PublicKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // get an RSA cipher object and print the provider
        final Cipher cipher = Cipher.getInstance(DEFAULT_ALGORITHM);

        // doAesEncrypt the plain text using the public key
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(text);
    }

    /**
     * Decrypt text using private key.
     *
     * @param text
     *          :encrypted text
     * @param keyPair
     *          :The private key
     * @return plain text
     */
    public byte[] decrypt(byte[] text, KeyPair keyPair) {
        return decrypt(text, keyPair.getPrivate());
    }

    /**
     * Decrypt text using private key.
     *
     * @param text
     *          :encrypted text
     * @param key
     *          :The private key
     * @return plain text
     */
    @Override
    public byte[] decrypt(byte[] text, PrivateKey key) {
        try {
            return tryDecrypt(text, key);
        } catch (Exception e) {
            throw new EncryptionException(e);
        }
    }

    private byte[] tryDecrypt(byte[] text, PrivateKey key) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, javax.crypto.IllegalBlockSizeException {
        // get an RSA cipher object and print the provider
        final Cipher cipher = Cipher.getInstance(DEFAULT_ALGORITHM);

        // tryDecrypt the text using the private key
        cipher.init(Cipher.DECRYPT_MODE, key);

        return cipher.doFinal(text);
    }

    public class EncryptionException extends RuntimeException {
        EncryptionException(Throwable cause) {
            super(cause);
        }
    }
}
