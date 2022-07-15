package com.netflix.api.web;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.api.dao.NetflixDataRepository;
import com.netflix.api.entity.NetflixData;
import com.netflix.api.utility.Statistics;

@RestController
public class NetflixController {
	
	@Autowired
	private NetflixDataRepository netflixDataRepository;
	
	@GetMapping("/netflixdata")
    public List<NetflixData> listAll() {
        List<NetflixData> findAll = netflixDataRepository.findAll();
        //model.addAttribute("listStudents", listStudents);
        
        
           
        return findAll;
    }
	
	@PostMapping("/writedata")
	public void writeDataToCSV(@RequestBody NetflixData netflixData,@RequestParam(name="csv") String csv) throws ParseException {
		if(csv.equalsIgnoreCase("true")) {
		System.out.println(netflixData);
		try {
		    // create a list of objects
		    

		    // create a writer
		    //BufferedWriter writer = Files.newBufferedWriter(Paths.get("./csvf/netflix_newdata.csv"));
		    
		    FileWriter fw = new FileWriter("./csvf/netflix_titles2.csv", true);
		    BufferedWriter writer = new BufferedWriter(fw);

		    String filePathString = "./csvf/netflix_newdata.csv";
			// write header record
		    File f = new File(filePathString );
		    if(!f.exists()) { 
		    	writer.append("ID,Name,Country");
			    writer.newLine();
		    }

		    // write all records
		    	String str = netflixData.getShowId()+","+netflixData.getType()+","+netflixData.getTitle()+","+netflixData.getDirector()+","
		    			+netflixData.getCast()+","+netflixData.getCountry()+","+netflixData.getDateAdded()+","+netflixData.getReleaseYear()+","
		    			+netflixData.getRating()+","+netflixData.getDuration()+","+netflixData.getListedIn()+","+netflixData.getDescription();
		        writer.append(str);
		        writer.newLine();
		    

		    //close the writer
		    writer.close();

		} catch (IOException ex) {
		    ex.printStackTrace();
		}
	}
		else if(csv.equalsIgnoreCase("false")) {
			netflixDataRepository.save(netflixData);
		}
	}
	
	@Scheduled(cron = "0 0/2 * 1/1 * ?")
	public void readDataFromCSVAndWriteToDb() {
		List<NetflixData> netflixData = Statistics.getNetflixData();
		System.out.println(netflixData);
		for(NetflixData data :netflixData) {
			netflixDataRepository.save(data);
		}
	}

}
