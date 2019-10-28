package com.zulkarnaen.springboot4.mvc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import com.zulkarnaen.springboot4.mvc.model.Pizza;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.zulkarnaen.springboot4.mvc")
public class MyConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * Configure TilesConfigurer.
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/views/**/tiles.xml" });
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		TilesViewResolver viewResolver = new TilesViewResolver();
		registry.viewResolver(viewResolver);
	}

//	/*
//	 * Configure View Resolver
//	 */
//	@Bean
//	public ViewResolver jspViewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setViewClass(JstlView.class);
//		viewResolver.setPrefix("/WEB-INF/views/pages/");
//		viewResolver.setSuffix(".jsp");
//
//		return viewResolver;
//	}

	/*
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 *
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	/*
	 * Configure MessageSource to provide internationalized messages
	 *
	 */

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		return messageSource;
	}

	/*
	 * Configure ContentNegotiationManager
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
	}

	/*
	 * Configure ContentNegotiatingViewResolver
	 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		// Define all possible view resolvers
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

		resolvers.add(jaxb2MarshallingXmlViewResolver());
		resolvers.add(jsonViewResolver());
//		resolvers.add(jspViewResolver());
//		resolvers.add(pdfViewResolver());
		resolvers.add(excelViewResolver());

		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	/*
	 * Configure View resolver to provide XML output Uses JAXB2 marshaller to
	 * marshall/unmarshall POJO's (with JAXB annotations) to XML
	 */
	@Bean
	public ViewResolver jaxb2MarshallingXmlViewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Pizza.class);
		return new Jaxb2MarshallingXmlViewResolver(marshaller);
	}

	/*
	 * Configure View resolver to provide JSON output using JACKSON library to
	 * convert object in JSON format.
	 */
	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}

	/*
	 * Configure View resolver to provide PDF output using lowagie pdf library to
	 * generate PDF output for an object content
	 */
//	@Bean
//	public ViewResolver pdfViewResolver() {
//		return new PdfViewResolver();
//	}

	/*
	 * Configure View resolver to provide XLS output using Apache POI library to
	 * generate XLS output for an object content
	 */
	@Bean
	public ViewResolver excelViewResolver() {
		return new ExcelViewResolver();
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