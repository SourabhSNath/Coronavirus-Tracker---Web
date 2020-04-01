package com.sourabh.coronavirustracker.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourabh.coronavirustracker.models.GlobalDataModel;
import com.sourabh.coronavirustracker.models.LatestGlobalTotalModel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class GlobalDataService {

    //https://github.com/ExpDev07/coronavirus-tracker-api
//    private static final String GLOBAL_URL = "https://coronavirus-tracker-api.herokuapp.com/v2/locations";

    private static final String GLOBAL_URL = "https://coronavirus-tracker-api.herokuapp.com/v2/locations?timelines=1";

    @PostConstruct
    public List<GlobalDataModel> getGlobalData() throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        List<GlobalDataModel> globalDataModelList = new ArrayList<>();

        JsonNode rootNode = objectMapper.readTree(new URL(GLOBAL_URL));
        JsonNode locationsNode = rootNode.path("locations");
        for (JsonNode element : locationsNode) {

            var globalDataModel = objectMapper.readValue(element.toString(), GlobalDataModel.class);
            var globalTotalModel = objectMapper.readValue(element.path("latest").toString(), LatestGlobalTotalModel.class);
            globalDataModel.setLatestGlobalTotalModel(globalTotalModel);

            globalDataModelList.add(globalDataModel);
        }

        globalDataModelList.forEach(System.out::println);

//        var something = globalDataModelList.stream().mapToLong(a -> a.getLatestGlobalTotalModel().getConfirmed()).sum();
//        System.out.println(something);

        JsonNode confirmedTimeline = locationsNode.path("timelines");
        confirmedTimeline.forEach(e -> System.out.println("Mooooooooooooo"));

        return globalDataModelList;
    }

}
