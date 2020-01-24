public class Tester {
	
	public static void main(String[] args) {
		int k = 4;
		int n = 10;
		LinearBlockCode lbc = new LinearBlockCode(k, n);
		System.out.println(lbc);
		lbc.minDistance();
		System.out.println(lbc.isContains("0101100000"));
		System.out.println(lbc.isContains("0000011001"));
	}
}