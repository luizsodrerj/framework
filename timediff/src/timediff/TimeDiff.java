package timediff;

import java.text.SimpleDateFormat;
import java.util.Date;

import framework.util.DateUtil;

public class TimeDiff {

	public TimeDiff() {
	}

	
	public TimeDiffResult calcDesconto(
							String dateStop, 
							String horasDesconto,
							String minDesconto
						  ) {
		try {
			long segundo = 1000L;
			long minuto  = 60 * segundo;
			long hora    = 60 * minuto;

			String hstop   = dateStop.substring(0,2) + ":" + dateStop.substring(2,4); 		
			String dtSaida = "01/01/2000 " + hstop + ":00";

			DateUtil parsedDate = new DateUtil(DateUtil.parse(dtSaida,DateUtil.dd_MM_yyyy_HH_mm_ss));
			
			long horasDescontadas = hora * (Long.parseLong(horasDesconto));
			long minDescontados	  = minuto * (Long.parseLong(minDesconto));	
			
			long time  = parsedDate.getTime().getTime();
			long dif   = time - horasDescontadas; 
			dif 	   = dif - minDescontados;
			String res = new SimpleDateFormat("HH:mm").format(
							new Date(dif)
						 );  
			
			String minutos = res.substring(3,5);
			String horas   = res.substring(0,2);
			
			TimeDiffResult result = new TimeDiffResult();
			result.setMinutes(minutos);
			result.setHours(horas);

			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public TimeDiffResult calcDiff(String start, String stop) {
		try {
			String hstart    = start.substring(0,2) + ":" + start.substring(2,4);    
			String hstop     = stop.substring(0,2) + ":" + stop.substring(2,4); 		
			String dtPartida = "01/01/2000 " + hstart + ":00";
			String dtChegada = "01/01/2000 " + hstop + ":00";
			
			DateUtil di = new DateUtil(DateUtil.parse(dtPartida, DateUtil.dd_MM_yyyy_HH_mm_ss));
			DateUtil df = new DateUtil(DateUtil.parse(dtChegada, DateUtil.dd_MM_yyyy_HH_mm_ss));
			
			long m = DateUtil.getDifferenceInMinutes(di, df);
			long h = DateUtil.getDifferenceInHours(di, df); 
			
			TimeDiffResult result = new TimeDiffResult();
			result.setMinutes(String.valueOf(m));
			result.setHours(String.valueOf(h));

			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
	
//	public static void main(String[] args) {
//		try {
//			new TimeDiff().defaultTest();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
