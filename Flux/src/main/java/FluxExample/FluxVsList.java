package FluxExample;

import CourseUtil.Util;

public class FluxVsList {
	public static void main(String[] args) {
		
//		 every name need to wait 1 second totally you need to wait 5 second after you get all result
		System.out.println(NameGenerator.listOfNames(5)); 
		
		// every 1 second you get the result 
		NameGenerator.fluxOfNames(5).subscribe( Util.onNext() );
		
		// we have create default subscriber in Util class
		NameGenerator.fluxOfNames(5).subscribe( Util.subscriber() ); 
		
	}
}
