package tolotra.test;

import helper.NumeroHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class OperateurApplication {

	public static void main(String[] args) throws Exception {
		NumeroHelper num = new NumeroHelper("03415486789");
		String pref = num.getOperateur();
		System.out.println(pref);
		SpringApplication.run(OperateurApplication.class, args);
	}

}


