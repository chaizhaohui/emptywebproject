package common.utils;



import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
/**
 * DES算法加密解密工具类
 * @author 乔广庆
 * @date 2012-04-17
 */
public class DESUtil {
	private static final String ALGORITHM = "DES";		//算法名称
	
	/**
	 * DES加密
	 * 密钥长度不能小于8位
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String DESEncode(byte[] src, String key) throws Exception{
		SecureRandom secureRandom = new SecureRandom();
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey, secureRandom);
		return byte2hex(cipher.doFinal(src));
	}
	
	/**
	 * DES解密
	 * 密钥长度不能小于8位
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] DESDecode(byte[] src, String key) throws Exception{
		src = hex2byte(src);
		SecureRandom secureRandom = new SecureRandom();
		DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
		SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey secretKey = secretKeyFactory.generateSecret(desKeySpec);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, secretKey, secureRandom);
		return cipher.doFinal(src);
	}
	
	private static String byte2hex(byte[] b){
		String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase(); 
	}
	
	private static byte[] hex2byte(byte[] b){
		if((b.length%2)!=0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length/2];
		for (int n = 0; n < b.length; n+=2) {
			String item = new String(b,n,2);
			b2[n/2] = (byte)Integer.parseInt(item,16);
		}
		return b2;
	}
	
	public static void main(String args[]) throws Exception{
		String source = "我们是害虫我们是害虫我们是害虫我们是害虫我们是害虫我们是害虫我们是害虫我们是害虫sss";
		System.out.println("原文是：" + source);
		String b = DESUtil.DESEncode(source.getBytes(),"11111111");
		System.out.println("加密后：" + b);
		System.out.println("解密后：" + new String(DESUtil.DESDecode(b.getBytes(),"11111111")));
	}
}
