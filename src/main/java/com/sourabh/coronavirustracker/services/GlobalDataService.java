package com.sourabh.coronavirustracker.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourabh.coronavirustracker.models.global.GlobalDataModel;
import com.sourabh.coronavirustracker.models.global.GlobalTimelineDataTrackerModel;
import com.sourabh.coronavirustracker.models.global.GlobalTotalDataModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://github.com/ExpDev07/coronavirus-tracker-api
 */

@Service
public class GlobalDataService {

    private static final String GLOBAL_URL = "https://coronavirus-tracker-api.herokuapp.com/v2/locations?timelines=1";

    // In case GLOBAL_URL is dead
    // private static final String ALTERNATE_GLOBAL_URL = "https://covid-tracker-us.herokuapp.com/v2/locations?source=jhu&timelines=true";

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private List<GlobalDataModel> globalDataModelList;

    public List<GlobalDataModel> getGlobalData() throws IOException {

        List<GlobalDataModel> globalDataModelList = new ArrayList<>();

        JsonNode rootNode = getNodeFromURL();

        JsonNode locationsNode = rootNode.path("locations");
        for (JsonNode element : locationsNode) {

            var globalDataModel = objectMapper.readValue(element.toString(), GlobalDataModel.class);
            var globalTotalModel = objectMapper.readValue(element.path("latest").toString(), GlobalTotalDataModel.class);

            JsonNode confirmedTimeline = element.path("timelines");
            var node = confirmedTimeline.path("confirmed").get("timeline");
            var globalTimelineDataTracker = objectMapper.readValue(node.toString(), GlobalTimelineDataTrackerModel.class);

            var timelineList = globalTimelineDataTracker.getList();
            var newCases = globalTotalModel.getConfirmed() - timelineList.get(timelineList.size() - 2);
            globalTotalModel.setNewConfirmed(newCases);

            globalDataModel.setGlobalTotalDataModel(globalTotalModel);
            globalDataModelList.add(globalDataModel);
        }

        globalDataModelList.sort(
                Collections.reverseOrder(
                        Comparator.comparing(e -> e.getGlobalTotalDataModel().getConfirmed())));
        int i = 0;
        for (var e : globalDataModelList) {
            e.setId(i++);
        }

        this.globalDataModelList = globalDataModelList;
        return globalDataModelList;
    }


    public GlobalTotalDataModel getTotalData() throws IOException {

        JsonNode node = getNodeFromURL();
        var globalTotalDataModel = objectMapper.readValue(node.path("latest").toString(), GlobalTotalDataModel.class);
        var newCases = globalDataModelList.stream()
                .mapToInt(e -> e.getGlobalTotalDataModel().getNewConfirmed()).sum();

        globalTotalDataModel.setNewConfirmed(newCases);

        System.out.println(globalTotalDataModel);
        return globalTotalDataModel;
    }

    private static JsonNode getNodeFromURL() throws IOException {
        return objectMapper.readTree(new URL(GLOBAL_URL));
    }

}
