package com.gw.alogrithm;

public class Test {


	public static void main(String[] args) {
		for(int i=122345;i<555555;i++){ 
			String str=i+"";	
			if(str.indexOf("35")==-1 && str.indexOf("53")==-1){ 
				if(!str.substring(2).startsWith("4")){
					String[] tmp = new String[6];
					for(int k = 0; k < 6; k++){
						tmp[k] = str.substring(k, k+1);
					}
					tmp = sort(tmp);
					if((tmp[0].equals("1")) &&
					   (tmp[1].equals("2")) &&
					   (tmp[2].equals("2")) &&
					   (tmp[3].equals("3")) &&
					   (tmp[4].equals("4")) &&
					   (tmp[5].equals("5")))
				    System.out.println(str); 
				} 
			} 
		} 

	}
	
	public static String[] sort(String[] arr){
		if(arr == null)
			return null;
		String[] result = arr;
		for(int i = result.length-1; i >= 0; i--){
			for(int j = i-1; j >= 0; j--){
				if(Integer.parseInt(result[i]) < Integer.parseInt(result[j])){
					String tmp = result[i];
					result[i] = result[j];
					result[j] = tmp;
				}
			}
		}
		return result;
	}

}
