package tst;

import java.text.SimpleDateFormat;
import java.util.Date;

import timediff.TimeDiff;
import timediff.TimeDiffResult;
import framework.util.DateUtil;

public class Test {

	void testDescontoHoras() {
		TimeDiff tdif    = new TimeDiff();
		TimeDiffResult r1 = tdif.calcDesconto("1900", "0", "50");
		TimeDiffResult r2 = tdif.calcDesconto("1900", "1", "0");
		TimeDiffResult r3 = tdif.calcDesconto("1900", "1", "30");
		
		System.out.println(r1.getHours() + "h e "+r1.getMinutes() + " min");
		System.out.println(r2.getHours() + "h e "+r2.getMinutes() + " min");
		System.out.println(r3.getHours() + "h e "+r3.getMinutes() + " min");
	}
	
	void test() throws Exception {
		long s = 1000L;
		long m = 60 * s;
		long h = 60 * m;
		
		DateUtil d = new DateUtil(
						DateUtil.parse(
						  "01/01/2000 19:30:00", 
						  DateUtil.dd_MM_yyyy_HH_mm_ss
						)
					  );
		long t   = d.getTime().getTime();
		long dif = t - (0); //*(30*m)
		dif = dif - (55*m);
		
		String res = new SimpleDateFormat("HH:mm").format(
						new Date(dif)
					 );  
		
		System.out.println("resultado - "+ res);
	}

	public static void main(String[] args) {
		try {
			new Test().testDescontoHoras();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}









