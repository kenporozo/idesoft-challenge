package cl.idesoft.idesoftschallenge;

import cl.idesoft.idesoftschallenge.model.Local;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@Slf4j
public class IdesoftsChallengeApplication {

	private static final String URL = "https://farmanet.minsal.cl/index.php/ws/getLocales";

	public static List<Local> locales;

	public static void main(String[] args) {
		SpringApplication.run(IdesoftsChallengeApplication.class, args);
	}

	@Bean
	public static RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
		messageConverters.add(converter);
		restTemplate.setMessageConverters(messageConverters);
		return restTemplate;
	}

	@Bean
	CommandLineRunner run() {
		return args -> {
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			ResponseEntity<Object[]> response = restTemplate().exchange(URL, HttpMethod.GET, new HttpEntity<>(httpHeaders), Object[].class);
			Object[] objects = response.getBody();
			ObjectMapper mapper = new ObjectMapper();

			if (objects != null) {
				locales = Arrays.stream(objects)
								.map(object -> mapper.convertValue(object, Local.class))
								.collect(Collectors.toList());
			}
			log.info("The list have been started with {} records", locales.size());
		};
	}

}
