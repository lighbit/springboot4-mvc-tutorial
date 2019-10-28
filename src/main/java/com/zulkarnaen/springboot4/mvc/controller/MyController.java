package com.zulkarnaen.springboot4.mvc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zulkarnaen.springboot4.mvc.model.Pizza;
import com.zulkarnaen.springboot4.mvc.model.Student;

@Controller
@RequestMapping("/")
public class MyController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(ModelMap model) {
		model.addAttribute("greeting", "Hello World from Spring 4 MVC");
		return "myIndex";
	}

	@RequestMapping(value = "/helloagain", method = RequestMethod.GET)
	public String sayHelloAgain(ModelMap model) {
		model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
		return "myIndex";
	}

	/*
	 * This method will serve as default GET handler.
	 *
	 */
	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		Student student = new Student();
		model.addAttribute("student", student);
		return "enroll";
	}

	/*
	 * This method will be called on form submission, handling POST request It also
	 * validates the user input
	 */
	@RequestMapping(value = "/registered", method = RequestMethod.POST)
	public String saveRegistration(@Valid Student student, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "enroll";
		}

		model.addAttribute("success", "Dear " + student.getFirstName() + " , your Registration completed successfully");
		return "success";
	}

	/*
	 * Method used to populate the Section list in view. Note that here you can call
	 * external systems to provide real data.
	 */
	@ModelAttribute("sections")
	public List<String> initializeSections() {

		List<String> sections = new ArrayList<String>();
		sections.add("Graduate");
		sections.add("Post Graduate");
		sections.add("Research");
		return sections;
	}

	/*
	 * Method used to populate the country list in view. Note that here you can call
	 * external systems to provide real data.
	 */
	@ModelAttribute("countries")
	public List<String> initializeCountries() {

		List<String> countries = new ArrayList<String>();
		countries.add("USA");
		countries.add("CANADA");
		countries.add("FRANCE");
		countries.add("GERMANY");
		countries.add("ITALY");
		countries.add("OTHER");
		return countries;
	}

	/*
	 * Method used to populate the subjects list in view. Note that here you can
	 * call external systems to provide real data.
	 */
	@ModelAttribute("subjects")
	public List<String> initializeSubjects() {

		List<String> subjects = new ArrayList<String>();
		subjects.add("Physics");
		subjects.add("Chemistry");
		subjects.add("Life Science");
		subjects.add("Political Science");
		subjects.add("Computer Science");
		subjects.add("Mathmatics");
		return subjects;
	}

	@RequestMapping(value = "/pizzavalley/{pizzaName}", method = RequestMethod.GET)
	public String getPizza(@PathVariable String pizzaName, ModelMap model) {

		Pizza pizza = new Pizza(pizzaName);
		model.addAttribute("pizza", pizza);

		return "pizza";

	}

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		return "home";
	}

	@RequestMapping(value = { "/products" }, method = RequestMethod.GET)
	public String productsPage(ModelMap model) {
		return "products";
	}

	@RequestMapping(value = { "/contactus" }, method = RequestMethod.GET)
	public String contactUsPage(ModelMap model) {
		return "contactus";
	}

}

/*
 * @Controller menunjukkan bahwa kelas ini adalah pengontrol yang menangani
 * permintaan dengan pola yang dipetakan oleh @ RequestMapping. Di sini dengan
 * ‘/’, ini berfungsi sebagai pengontrol default. Metode pendaftaran baru cukup
 * sederhana, dijelaskan dengan @ RequestMethod.GET melayani permintaan GET
 * default, menambahkan objek model untuk melayani sebagai pemegang data
 * formulir, dan menyajikan halaman yang berisi formulir kosong.
 * 
 * Metode initializeSections, initializeCountries & initializeSubjects hanya
 * membuat objek tingkat permintaan yang nilainya akan digunakan dalam tampilan
 * / jsp.
 * 
 * Metode saveRegistration dijelaskan dengan @ RequestMethod.POST, dan akan
 * menangani permintaan POST form-submission. Perhatikan parameter dan pesanan
 * mereka dalam metode ini. @ Valid meminta pegas untuk memvalidasi objek
 * terkait (siswa). BindingResult berisi hasil validasi ini dan kesalahan apa
 * pun yang mungkin terjadi selama validasi ini. Perhatikan bahwa BindingResult
 * harus datang tepat setelah objek yang divalidasi, jika tidak, musim semi
 * tidak akan dapat memvalidasi dan pengecualian dilemparkan.
 * 
 * Perhatikan bahwa jika terjadi kegagalan validasi, pesan kesalahan standar /
 * umum ditampilkan di layar yang mungkin tidak diinginkan. Sebagai gantinya,
 * Anda dapat mengganti perilaku ini dengan memberikan pesan internasionalisasi
 * khusus untuk setiap bidang. Untuk melakukan itu, kita perlu mengkonfigurasi
 * MessageSource di kelas konfigurasi aplikasi dan menyediakan file properti
 * yang berisi pesan aktual yang akan kita lakukan selanjutnya.
 */