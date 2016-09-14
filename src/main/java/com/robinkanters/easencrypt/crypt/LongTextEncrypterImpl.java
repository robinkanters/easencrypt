package com.robinkanters.easencrypt.crypt;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import java.security.*;

import static java.lang.String.format;

public class LongTextEncrypterImpl implements LongTextEncrypter {
    public static final String DIVIDER = "!!";

    private final SymmetricalCipher symmetricalCipher;
    private final AsymmetricEncrypter asymmetricEncrypter;

    public LongTextEncrypterImpl(SymmetricalCipher symmetricalCipher,
                                 AsymmetricEncrypter asymmetricEncrypter) {
        this.symmetricalCipher = symmetricalCipher;
        this.asymmetricEncrypter = asymmetricEncrypter;
    }

    public String encrypt(String plain, PublicKey publicKey) {
        SecretKey key = generateKey();
        String encodedDesKey = Hex.encodeHexString(encryptSyncPassword(key.getEncoded(), publicKey));

        String encryptedString = symmetricalCipher.encrypt(plain, key);

        return formatEncryptedString(encodedDesKey, encryptedString);
    }

    private String formatEncryptedString(String encodedDesKey, String encryptedString) {
        return format("%s%s%s", encodedDesKey, DIVIDER, encryptedString);
    }

    @Override
    public String decrypt(String encrypted, PrivateKey privateKey) throws InvalidKeyException {
        try {
            return tryDecrypt(encrypted, privateKey);
        } catch (DecoderException e) {
            throw new RuntimeException(e);
        }
    }

    private String tryDecrypt(String encrypted, PrivateKey privateKey) throws DecoderException, InvalidKeyException {
        String[] split = encrypted.split(DIVIDER);
        byte[] encryptedDesKey = Hex.decodeHex(split[0].toCharArray());
        String encryptedData = split[1];

        byte[] decryptedDesKey = decryptSyncPassword(encryptedDesKey, privateKey);

        return symmetricalCipher.decrypt(encryptedData, decryptedDesKey);
    }

    private byte[] encryptSyncPassword(byte[] key, PublicKey publicKey) {
        return asymmetricEncrypter.encrypt(key, publicKey);
    }

    private byte[] decryptSyncPassword(byte[] encryptedDesKey, PrivateKey privateKey) {
        return asymmetricEncrypter.decrypt(encryptedDesKey, privateKey);
    }

    private SecretKey generateKey() {
        return symmetricalCipher.generateKey();
    }
}
