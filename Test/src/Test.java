import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		 
		jumbleSentence("The cat sat on the Ikea mat.");
	}
	
	public static String jumbleSentence (String input) {
        String jumbleString = "";
         String sorted = "";
        
      String[] wordsArray = input.split(" ");
      List<String> wordsList = Arrays.asList(wordsArray);
     
      for(String word : wordsList){
       
       char[] chars = word.toLowerCase().toCharArray();
       Arrays.sort(chars);
       
       if(Character.isUpperCase(word.charAt(0))){
    	   chars[0] = Character.toUpperCase(chars[0]);
       }
       
       sorted = new String(chars);            
       
       
       jumbleString = jumbleString + sorted + " ";
      }
      
      return jumbleString;
      
   }

}
