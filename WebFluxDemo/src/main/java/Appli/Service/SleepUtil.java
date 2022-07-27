package Appli.Service;

public class SleepUtil {

	public static void sleepSeconds(int seconds) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
