package com.sourabh.coronavirustracker.services;

import com.sourabh.coronavirustracker.models.LocationDataModel;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class would make a call to url and fetch the data
 * It fetches the data:
 * 1. When the app is run
 * 2. At 1am and 1pm everyday
 */

@Service
public class CoronaVirusDataService {

    private static final String VIRUS_CONFIRMED_DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_19-covid-Confirmed.csv";

    private List<LocationDataModel> allDataList = new ArrayList<>();

    /**
     * @ PostConstruct Tells Spring to execute this method every time when the Class is constructed
     * --<p>
     * Spring calls methods annotated with @PostConstruct only once,
     * just after the initialization of bean properties. Keep in mind that
     * these methods will run even if there is nothing to initialize.
     * --<p>
     * @ Scheduled is used for scheduling
     * cron attribute:
     * <second> <minute> <hour> <day-of-month> <month> <day-of-week> <year>
     * --<p>
     * fetchVirusData() gets the data from the csv file within the github repository
     */
    @PostConstruct
    @Scheduled(cron = "* * 1,13 * * *") // checked at 1am and 1pm everyday
    public void fetchVirusData() throws InterruptedException, IOException {

        // HttpClient is used to make Http Call to the url
        HttpClient client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder()
                .uri(URI.create(VIRUS_CONFIRMED_DATA_URL))
                .build();

        // HttpResponse.BodyHandlers.ofString() takes the response body and returns it as a String
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        this.allDataList = getData(response);
    }


    /**
     * Read the body using StringReader, required for parsing the csv data.
     * <p>
     * Java StringReader class is a character stream with string as a source.
     * It takes an input string and changes it into character stream
     *
     * @param response is used to get the String from response.body()
     * @return a list containing LocationDataModel
     * @throws IOException from CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
     */
    private List<LocationDataModel> getData(HttpResponse<String> response) throws IOException {
        var newList = new ArrayList<LocationDataModel>();

        StringReader csvBodyReader = new StringReader(response.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader);
        for (CSVRecord record : records) {
            LocationDataModel locationDataModel = new LocationDataModel();
            locationDataModel.setState(record.get("Province/State"));
            locationDataModel.setCountry(record.get("Country/Region"));

            var todayCount = Integer.parseInt(record.get(record.size() - 1));
            locationDataModel.setLatestCaseData(todayCount);
            var previousCount = Integer.parseInt(record.get(record.size() - 2));

            locationDataModel.setPreviousDayDifference(todayCount - previousCount);

            newList.add(locationDataModel);
        }
        csvBodyReader.close();
        return newList;
    }

    public List<LocationDataModel> getAllDataList() {
        allDataList.sort(Comparator.comparing(LocationDataModel::getLatestCaseData).reversed());
        var i = 1;
        for (var l : allDataList) {
            l.setId(i++);
        }
        return allDataList;
    }

    public long getTotalCases() {
        return allDataList.stream().mapToLong(LocationDataModel::getLatestCaseData).sum();
    }

    public long getTotalNewCases() {
        return allDataList.stream().mapToLong(LocationDataModel::getPreviousDayDifference).sum();
    }

    /**
     * Using java streams to turn the arraylist into a LinkedHashMap
     * The first .collect turns it into a LinkedHashMap
     * -
     * Then the map's entry set is used to sort and get the 5 top countries with the highest reported cases
     *
     * @return map with top 5 countries
     */
    public Map<String, Integer> getTopCountries() {
        var map = allDataList.stream()
                .collect(Collectors.groupingBy(LocationDataModel::getCountry, LinkedHashMap::new,
                        Collectors.summingInt(LocationDataModel::getLatestCaseData)))
                .entrySet().stream()
                .limit(5)
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldVal, newVal) -> oldVal, LinkedHashMap::new));

        // In case where the country is 'Others', get the state
        // Done here to get Diamond Cruise Ship instead of 'Others'
        final var others = "Others";
        if (map.containsKey(others)) {
            var value = map.remove(others);
            for (var data : allDataList) {
                if (data.getCountry().equals(others)) {
                    map.put(data.getState(), value);
                }
            }
            map = map.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
        }

        return map;
    }
}