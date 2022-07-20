package br.com.wmoddev.examplesecurity.util;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class ResponseEntityUtil {
	
	/**
	 *
	 * @param str
	 * @return
	 */
	public static URI uriGenerate(String str) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/"+str).build().toUri();
	}

}
