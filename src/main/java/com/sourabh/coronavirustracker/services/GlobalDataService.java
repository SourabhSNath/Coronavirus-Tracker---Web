package com.sourabh.coronavirustracker.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sourabh.coronavirustracker.models.GlobalDataModel;
import com.sourabh.coronavirustracker.models.LatestGlobalTotalModel;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.*;

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

            JsonNode confirmedTimeline = element.findValue("timelines");
            var node = confirmedTimeline.path("confirmed").get("timeline");
//            System.out.println(node);

            LinkedHashMap<String, Integer> map = objectMapper.readValue(node.toString(), new TypeReference<>() {
                @Override
                public Type getType() {
                    return super.getType();
                }
            });

            Integer[] newCases = map.values().toArray(Integer[]::new);
            var newCasesLength = newCases.length;
            globalTotalModel.setPreviousConfirmed(newCases[newCasesLength - 1]);
//            System.out.println(newCases[newCasesLength - 1] + ". Older: " + newCases[newCasesLength - 2] + " Country: " + globalDataModel.getCountry());

        }


//        var something = globalDataModelList.stream().mapToLong(a -> a.getLatestGlobalTotalModel().getConfirmed()).sum();
//        System.out.println(something);


        return globalDataModelList;
    }

}
