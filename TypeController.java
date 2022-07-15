package com.netflix.api.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.api.entity.NetflixData;
import com.netflix.api.utility.Statistics;

@RestController
public class TypeController {

	@RequestMapping("/tvshows")
	public List<NetflixData> listFirstNTvShows(@RequestParam(required = false, name = "count") String n,
			@RequestParam(required = false, name = "movieCount") String listedIn,@RequestParam(required = false, name = "country") String country
			) {

		String type = "TV Show";
		List<NetflixData> result = null;
		if (n != null) {
			try {
				result = Statistics.listRecordsByType(type, Integer.parseInt(n));
			} catch (NumberFormatException | IOException | ParseException e) {
				e.printStackTrace();
			}
		}
		else if(listedIn != null) {
			try {
				result = Statistics.listRecordsByTypeAndListedIn(type, listedIn);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		else if(country != null) {
			result = Statistics.listRecordsByTypeAndCountry(type, country);
		}
		return result;

	}


}
