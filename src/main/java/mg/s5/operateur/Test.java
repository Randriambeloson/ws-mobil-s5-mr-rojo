package mg.s5.operateur;

import java.util.ArrayList;
import java.util.List;



//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import function.Response;
import base.vue.Historique;

//@SpringBootApplication
@RestController
@CrossOrigin
public class Test {

	@GetMapping("liste")
	public ArrayList<Historique> historique() throws Exception  {
		System.out.println("testt");
		ArrayList<Historique> list = Historique.getHistorique();
		System.out.println("FIIINNN");
		Response response = new Response("200", "Okee", list);
		System.out.println("RESPONSE");
//		System.out.println(list.toString());
		return list;		
	}
}
