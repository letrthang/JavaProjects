package main;

import java.io.File;
import java.math.BigInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import gen.Student;
import gen.Students;
import gen.UniProperty;
import gen.University;

public class Main {

	public static void main(String[] args) {
		Students students = new Students();
		Student student = new Student();
		University uni = new University();
		UniProperty uniPros = new UniProperty();

		uniPros.setAddress("Sai Gon");
		uniPros.setHomepage("www.letrungthang.blogspot.com");
		uniPros.setPhone(BigInteger.valueOf(1234567890));

		uni.setCode(BigInteger.valueOf(123));
		uni.setName("Sai Gon");
		uni.getUniProperty().add(uniPros);

		student.setUniversity(uni);
		student.setId(BigInteger.valueOf(456));
		student.setName("Thang Le");

		students.setStudent(student);

		try {
			// Marshalling - Convert Java Object to to XML file

			File fileOut = new File("src/xml/studentsOutput.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance("xmlMarshall:gen");
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(students, fileOut);
			jaxbMarshaller.marshal(students, System.out);

			// Unmarshalling - load XML file to Java object
			File fileIn = new File("src/xml/studentsInput.xml");
			JAXBContext jaxbContext1 = JAXBContext.newInstance("xmlMarshall:gen");
			Unmarshaller jaxbUnmarshaller = jaxbContext1.createUnmarshaller();
			Students studentsUnmarsha = (Students) jaxbUnmarshaller.unmarshal(fileIn);
			jaxbMarshaller.marshal(studentsUnmarsha, System.out);
		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}
