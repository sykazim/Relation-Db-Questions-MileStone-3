package com.netflix.api.utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.netflix.api.entity.NetflixData;



public class Statistics {

	private static List<NetflixData> netflixDataList = new ArrayList<>();
	
	public static List<NetflixData> getNetflixData(){
		try {
			extractCSVData();
		} catch (NumberFormatException | IOException | ParseException e) {
			e.printStackTrace();
		}
		return netflixDataList;
	}
	
	
	public static List<NetflixData> listRecordsByTypeAndListedIn(String type, String listedIn) {
		System.out.println("type and listedIn=================");
		List<NetflixData> recordsByTypeAndListedIn = new ArrayList<>();
		for (NetflixData data : netflixDataList) {
			if (data.getType().equalsIgnoreCase(type) && data.getListedIn().equalsIgnoreCase(listedIn)) {
				recordsByTypeAndListedIn.add(data);
			}
		}
		return recordsByTypeAndListedIn;
	}
	
	public static List<NetflixData> listRecordsByTypeAndCountry(String type, String country) {
		System.out.println("type and country=================");
		List<NetflixData> recordsByTypeAndCountry = new ArrayList<>();
		for (NetflixData data : netflixDataList) {
			if (data.getType().equalsIgnoreCase(type) && data.getCountry().equalsIgnoreCase(country)) {
				recordsByTypeAndCountry.add(data);
			}
		}
		return recordsByTypeAndCountry;
	}


	public static void listRecordsByTypeAndCountry(String type, String country, int n) {
		System.out.println("type and country=================");
		int counter = 0;
		for (NetflixData data : netflixDataList) {
			if (data.getType().equalsIgnoreCase(type) && data.getCountry().equalsIgnoreCase(country)) {
				System.out.println(data);
				counter++;
			}
			if (counter >= n) {
				break;
			}
		}
	}

	/*
	 * public static void listRecordsByTypeAndCountry(String type, String country,
	 * int n, Date startDate, Date endDate) throws ParseException {
	 * System.out.println("type and country================= dates"); int counter =
	 * 0; for (NetflixData data : netflixDataList) { if
	 * (data.getType().equalsIgnoreCase(type) &&
	 * data.getCountry().equalsIgnoreCase(country) &&
	 * data.getDateAdded().after(startDate) && data.getDateAdded().before(endDate))
	 * { System.out.println(data); counter++; } if (counter >= n) { break; } } }
	 */

	public static void listRecordsByListedIn(String listedIn, int n) {
		System.out.println("listed in ===================");
		int counter = 0;
		for (NetflixData data : netflixDataList) {
			if (data.getListedIn().equalsIgnoreCase(listedIn)) {
				System.out.println(data);
				counter++;
			}
			if (counter >= n) {
				break;
			}
		}
	}

	/*
	 * public static void listRecordsByListedIn(String listedIn, int n, Date
	 * startDate, Date endDate) throws ParseException {
	 * System.out.println("listed in =================== dates"); int counter = 0;
	 * 
	 * for (NetflixData data : netflixDataList) { if
	 * (data.getListedIn().equalsIgnoreCase(listedIn) &&
	 * data.getDateAdded().after(startDate) && data.getDateAdded().before(endDate))
	 * { System.out.println(data); counter++; } if (counter >= n) { break; } } }
	 */

	public static List<NetflixData> listRecordsByType(String type, int n) throws NumberFormatException, IOException, ParseException {
		List<NetflixData> firstNRecords = new ArrayList<>();
		System.out.println("type=====================");
		int counter = 0;
		for (NetflixData data : netflixDataList) {
			if (data.getType().equalsIgnoreCase(type)) {
				firstNRecords.add(data);
				counter++;
			}
			if (counter >= n) {
				break;
			}
		}
		return firstNRecords;
	}

	/*
	 * public static List<NetflixData> listRecordsByType(String type, int n, Date
	 * startDate, Date endDate) throws NumberFormatException, IOException,
	 * ParseException { List<NetflixData> firstNRecords = new ArrayList<>(); int
	 * counter = 0; for (NetflixData data : netflixDataList) { if
	 * (data.getType().equalsIgnoreCase(type) &&
	 * data.getDateAdded().after(startDate) && data.getDateAdded().before(endDate))
	 * { firstNRecords.add(data); counter++; } if (counter >= n) { break; } } return
	 * firstNRecords; }
	 */

	private static void extractCSVData() throws NumberFormatException, IOException, ParseException {
		BufferedReader reader = new BufferedReader(new FileReader("./csvf/netflix_titles2.csv"));

		// read file line by line
		String line = null;
		boolean header = true;
		Scanner scanner = null;
		int index = 0;
		netflixDataList = new ArrayList<>();

		while ((line = reader.readLine()) != null) {
			NetflixData netflixData = new NetflixData();
			scanner = new Scanner(line);
			scanner.useDelimiter(",");
			String[] splitted = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
			// System.out.println(Arrays.toString(splitted));
			// Arrays.stream(splitted).forEach(num -> System.out.println(num));
			// System.out.println("hello1");
			int length = 0;
			while (length < splitted.length && !header) {
				String data = splitted[length++];

				if (index == 0) {

					netflixData.setShowId(data);
				} else if (index == 1) {

					netflixData.setType(data);
				} else if (index == 2) {

					netflixData.setTitle(data);
				} else if (index == 3) {
					netflixData.setDirector(data);
				} else if (index == 4) {
					netflixData.setCast(data);
				} else if (index == 5) {
					// System.out.println(data);
					netflixData.setCountry(data);
				} else if (index == 6 && data != null && data.length() != 0) {
					// System.out.println(data);
					netflixData.setDateAdded(data);
					// netflixData.setDateAdded(data);
				} else if (index == 7) {
					netflixData.setReleaseYear(Integer.parseInt(data));
				} else if (index == 8) {
					netflixData.setRating(data);
				} else if (index == 9) {
					netflixData.setDuration(data);
				} else if (index == 10) {
					netflixData.setListedIn(data);
				} else if (index == 11) {
					netflixData.setDescription(data);
				} else if (index == 12) {
					break;
				}

				index++;
				// System.out.println("hello2");
			}
			index = 0;
			if (!header) {
				netflixDataList.add(netflixData);
			}
			header = false;
		}

		// close reader
		reader.close();

	}

}

