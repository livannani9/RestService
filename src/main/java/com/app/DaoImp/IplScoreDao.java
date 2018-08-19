	package com.app.DaoImp;

import com.app.Exceptions.UserDaoExpection;

public class IplScoreDao {

	public void registerPlayer() {

		System.out.println("IplScore Dao entering ");
		try {
			int a = 10;
			int b = 0;
			String s=null;
//			System.out.println(a / b);

		} catch (ArithmeticException e) {
			
			System.out.println("Divider Should not be Zero");
			throw new UserDaoExpection(111, "0", "Division ");
		}catch (NullPointerException e) {
			System.out.println("Null pointer");
		throw new UserDaoExpection(222, "null Pointer", "Oparation ");
		}

		System.out.println("Close...");
	}
	

}
