package utils;

public class Util {
	

	/**Recibe en segundos el tiempo del sleep
	*waitSecods(int)
	**/
	public void waitSecods(int segundos) {
		try{
			Thread.sleep(segundos*1000);
			}
		catch(InterruptedException e){
			System.out.println(e);
			}
	}
	/**Recibe en segundos el tiempo del sleep y le agrega medio segundo mas
	*waitSecods(int)
	**/
	public void waitHalfSecods(int segundos) {
		try{
			Thread.sleep((segundos*1000)+500);
			}
		catch(InterruptedException e){
			System.out.println(e);
			}
	}
}
