/*
 * Author: Vikas Veshishth
 * Handle: gynbiprt
 * 
 * Implements Manacher's Algorithm
 */
public class LongestPalindromicSubString {
	static char dumChar = '#';

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String pal = findLongestPalSub("babcbabcbaccba");
		System.out.println(pal);

	}
	
	public static String findLongestPalSub(String str){
		if(str.length()==0) return str;
		String str_odd = oddIt(str);
		int[] p = getArray(str_odd);
		
		int mx = 0;
		for(int i=0;i<str_odd.length();i++){
			//test--System.out.println("["+str_odd.charAt(i)+"]["+p[i]+"]");
			if(p[i]>mx){
				mx=i;
			}
		}
		
		
		StringBuilder resB = new StringBuilder();
		
		for(int i = (str_odd.charAt(mx-p[mx])==dumChar)?mx-p[mx]+1:mx-p[mx];i<=mx+p[mx];i+=2){
			resB.append(str_odd.charAt(i));
		}
		
		
		return resB.toString();
		
	}
	
	private static  String oddIt(String str){
		StringBuilder sb = new StringBuilder();
		
		sb.append(dumChar);
		
		for(int i = 0;i<str.length();i++){
			sb.append(str.charAt(i));
			sb.append(dumChar);
		}
		
		return sb.toString();
	}
	
	private static int[] getArray(String str_odd){
        int N = str_odd.length();
		
		//initialize variables
		int[] P = new int[N];
		int C = 0, R=0,i=0;
		
		//by analysis of initial conditions and constraints
		P[0]=0;P[1]=1;
		C=1;
		R=2;
		i=2;//we will start at index 2 
		
		while(i<=N-1){
			int i_reflect = 2*C-i;
			
			if(i_reflect >=0 && P[i_reflect] < R-i){
				P[i]=P[i_reflect];
			}else{
				//P[i] >= P[i_reflect]
				P[i]=(R>i)?R-i:0;
				
				//check if there is more matching around i after C+P[C] and before C-P[C]
				int R_right = i+P[i];  // C+P[C]
				int R_left  = i-P[i];
				
				for(int z = 1;(R_left-z >=0 ) && (R_right+z<= N-1);z++){
					if(str_odd.charAt(R_right+z) == str_odd.charAt(R_left-z)){
						P[i]=P[i]+1;
					}else{
						break;
					}
				}
				
				//check for extending C
				if(i+P[i] > R){
					C=i;
					R = C+P[C];
				}
			}
			
			i++;
		}
		
		return P;
	}

}
