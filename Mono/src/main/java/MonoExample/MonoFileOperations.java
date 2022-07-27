package MonoExample;

import CourseUtil.Util;

public class MonoFileOperations {
	public static void main(String[] args) {
		MonoFileService.read("file1.txt")
			.subscribe(Util.onNext(),
					Util.onError(),
					Util.onComplete()
		);
		
		MonoFileService.write("file2.txt","This is File2...")
		.subscribe(Util.onNext(),
				Util.onError(),
				Util.onComplete()
		);
		
		MonoFileService.delete("file2.txt")
		.subscribe(Util.onNext(),
				Util.onError(),
				Util.onComplete()
		);
	}
}
