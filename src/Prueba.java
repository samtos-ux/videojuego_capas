
public class Prueba {
	public static void main(String[] args) {
		String str = "Samuel";
		String[] arrs = str.split("");
		char[] arr = str.toCharArray();
		for (char c : arr) {
			System.out.print(c + "-");
		}
		System.out.println();
		for (String s : arrs) {
			System.out.print(s + "-");
		}

	}
}
