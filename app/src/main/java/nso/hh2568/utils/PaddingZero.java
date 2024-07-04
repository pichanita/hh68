package nso.hh2568.utils;

public class PaddingZero {

	public static String setLength_string(String number_value, int length_value) {
		String result = number_value;
		if( number_value.length()==0 ){
			result ="";				
		}
		else{			
			for(int i = (number_value.length()) ;i<length_value;i++){		
				result = "0"+ result;	
			}			
		}
			return result;
	}

	public static String setLength_string_back(String number_value, int length_value) {
		String result = number_value;
		if( number_value.length()==0 ){
			result ="";
		}
		else{
			for(int i = (number_value.length()) ;i<length_value;i++){
				result = result+"0";
			}
		}
		return result;
	}

	public static String setLength_int(int number_value, int length_value) {
		String result = ""+number_value;
		if( result.length()==0 ){
			result ="";				
		}
		else{			
			for(int i = (result.length()) ;i<length_value;i++){		
				result = "0"+ result;	
			}			
		}
			return result;
	}	
}
