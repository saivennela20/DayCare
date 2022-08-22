package edu.neu.csye6200;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import edu.neu.csye6200.student.Student;

public class Parsing {
	static final String fileName = "edu/neu/csye6200/inputData/csv1.txt";
	static ArrayList<Student> student = new ArrayList<Student>();
	// static Student st= new Student();

	public static void demo() {
//		FileReader fr;
//		BufferedReader inLine;
//		Parsing p = new Parsing();
//		try {
//			inLine = FileUtil.getFileReader(fileName);
//			String line = null;
//			while ((line = inLine.readLine()) != null) {
//				Scanner sc = new Scanner(line);
//				sc.useDelimiter(",");
//
//				String name = sc.next();
//				String parentname = sc.next();
//				long phonenumber = sc.nextLong();
//				String address = sc.next();
//				int age = sc.nextInt();
//				double gpa = sc.nextDouble();
//				//int sId = sc.nextInt();
//				Student s = new Student(name, parentname, phonenumber, address, age, gpa, 0);
//				p.student.add(s);
//				sc.close();
//			}
//			inLine.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		Collections.sort(student, new Comparator<Student>() {
//			@Override
//			public int compare(Student t, Student t1) {
//				return t.getAge() - t1.getAge();
//			}
//		});
//		//System.out.println(student);
//		student.forEach(System.out::println);

	}
}
