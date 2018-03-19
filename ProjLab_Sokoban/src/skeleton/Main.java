package skeleton;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		TestCase[] testCases= {new TestCase01(), new TestCase02(), new TestCase03(), new TestCase04(), new TestCase05(), new TestCase06(), new TestCase07(), new TestCase08(), new TestCase09(), new TestCase10(), new TestCase11(), new TestCase12(), new TestCase13(), new TestCase14(), new TestCase15(), new TestCase16(), new TestCase17(), new TestCase18()};
		System.out.println("Add meg a szekvencia sorszamat!");
		System.out.println("> ");
		Scanner scanner=new Scanner(System.in);
		int idx=scanner.nextInt()-1;
		TestCase chosen=testCases[idx];
		System.out.println((idx+1)+". "+chosen.GetName());
		chosen.Test();
		scanner.close();
	}

}
