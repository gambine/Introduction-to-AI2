import java.util.Arrays;

public class ThreeDigitNumber 
{
	int firstDigit;
	int secondDigit;
	int thirdDigit;
	boolean[] arr = {false,false,false,false};
	
	public ThreeDigitNumber(String Num)
	{
		
		this.firstDigit=Integer.parseInt(Num.substring(0,1));
		this.secondDigit=Integer.parseInt(Num.substring(1,2));
		this.thirdDigit=Integer.parseInt(Num.substring(2,3));
		Arrays.fill(arr, false);
		arr[0]=true;
	}
	public ThreeDigitNumber(Node node)
	{
		this.firstDigit = node.getNumber().firstDigit;
		this.secondDigit = node.getNumber().secondDigit;
		this.thirdDigit = node.getNumber().thirdDigit;
		Arrays.fill(arr, false);
		arr[0]=true;
	}
	public int firstDigit()
	{
		return firstDigit;
	}
	public int SecondDigit()
	{
		return secondDigit;
	}
	public int thirdDigit()
	{
		return thirdDigit;
	}
	public void firstDigitIncDec(boolean val)
	{
		if(val==true)
		{
			firstDigit+=1;
		}
		else
		{
			firstDigit-=1;
		}
		Arrays.fill(arr, false);
		arr[1]=true;
	}
	public void secondDigitIncDec(boolean val)
	{
		if(val==true)
		{
			secondDigit+=1;
		}
		else
		{
			secondDigit-=1;
		}
		Arrays.fill(arr, false);
		arr[2]=true;
	}
	public void thirdDigitIncDec(boolean val)
	{
		if(val==true)
		{
			thirdDigit+=1;
		}
		else
		{
			thirdDigit-=1;
		}
		Arrays.fill(arr, false);
		arr[3]=true;
	}
	public String getNumber()
	{
		String num= firstDigit+""+secondDigit+""+thirdDigit;
		return num; 

	}
}
