package Generic_utility;

import java.util.Random;

public class Java_Utility {

	/**
	 * This methodis used to avoid duplicates
	 * 
	 * @return
	 * @author Shobha
	 */
	public int getRandomNum()
	{
		Random ran=new Random();
		int RanNum = ran.nextInt(1000);
	    return RanNum;
		
	}
}
