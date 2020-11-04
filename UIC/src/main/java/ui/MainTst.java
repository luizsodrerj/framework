package ui;

import java.text.SimpleDateFormat;

import framework.util.DateUtil;

public class MainTst {

	public static void main(String[] args) {

		SimpleDateFormat f = new SimpleDateFormat("HH:mm");
		
		DateUtil dini = new DateUtil("01/01/2000");
		
		dini.set(DateUtil.HOUR_OF_DAY, 11);
		dini.set(DateUtil.MINUTE, 37);

		System.out.println(f.format(dini.getTime()));

		DateUtil df = (DateUtil)dini.clone();
		
		df.add(DateUtil.HOUR_OF_DAY, 8);
		
		System.out.println(f.format(df.getTime()));
		
	}

}
