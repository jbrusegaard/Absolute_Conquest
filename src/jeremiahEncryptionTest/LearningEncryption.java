package jeremiahEncryptionTest;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


// TODO: Auto-generated Javadoc
/**
 * The Class LearningEncryption.
 */
public class LearningEncryption {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws InvalidKeySpecException the invalid key spec exception
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 * @throws NoSuchPaddingException the no such padding exception
	 * @throws IllegalBlockSizeException the illegal block size exception
	 * @throws BadPaddingException the bad padding exception
	 * @throws InvalidKeyException the invalid key exception
	 */
	public static void main(String args[]) throws InvalidKeySpecException, NoSuchAlgorithmException,
			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		String text = "bob";
		String key = "0JCz4TalvOGP2TUh";
		String algorithm = "AES";
		Base64.Encoder encoder = Base64.getEncoder();
		SecretKeySpec aesKey = new SecretKeySpec(key.getBytes(), algorithm);
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		byte[] encrypted = cipher.doFinal(text.getBytes());
		String encryptedString = encoder.encodeToString(encrypted);
		System.out.println(encryptedString);
		Base64.Decoder decoder = Base64.getDecoder();
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		String decrypted = new String(cipher.doFinal(decoder.decode(encryptedString)));
		System.out.println(decrypted);

		text = "jeremiah";
		key = "gfjvydjfyfusidjf";
		algorithm = "Blowfish";
		SecretKeySpec blowfishkey = new SecretKeySpec(key.getBytes(), algorithm);
		cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, blowfishkey);
		encrypted = cipher.doFinal(text.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, blowfishkey);
		System.out.println(new String(cipher.doFinal(decoder.decode(encoder.encodeToString(encrypted)))));
	}

}
