package backend;

import java.math.BigInteger;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Arrays;

import exception.NoResultException;
import util.HashingCalculator;

public class LoginAuthentication implements Authentication {

	private String username;
	private String password;
	
	private DBConnect con;
	
	public LoginAuthentication(
		String username,
		String password,
		DBConnect con
	) {
		this.username = username;
		this.password = password;
		this.con = con;
	}
	
	@Override
	public boolean authenticate() throws SQLException {
		try {
			String[] authInfo = this.con.getUserAuthInfo(username);
			byte[] passwordHashed = new BigInteger(authInfo[0]).toByteArray();
			byte[] salt = new BigInteger(authInfo[1]).toByteArray();
			
			byte[] inputHashed = HashingCalculator.genHash(this.password, salt);
			return Arrays.equals(inputHashed, passwordHashed);
		}catch (NoResultException e) {
			System.out.println(e);
			return false;
		} catch(InvalidKeySpecException e) {
			System.out.println(e);
			return false;
		}
	}
	
	
}
