package com.sourabh.coronavirustracker.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourabh.coronavirustracker.models.IndianDataModel;
import com.sourabh.coronavirustracker.models.StateDeltaModel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndianDataService {

    private final static String COVID_DATA_URL = "https://api.covid19india.org/data.json";

    public List<IndianDataModel> getIndianData() throws IOException {

        // Used here to parse Json content
        ObjectMapper objectMapper = new ObjectMapper();
        var indianDataList = new ArrayList<IndianDataModel>();

        JsonNode rootNode = objectMapper.readTree(new URL(COVID_DATA_URL));
        JsonNode statesNode = rootNode.path("statewise");
        int i = 0;
        for (JsonNode element : statesNode) {

            var indianData = objectMapper.readValue(element.toString(), IndianDataModel.class);
            var stateDeltaObject = objectMapper.readValue(element.path("delta").toString(), StateDeltaModel.class);
            indianData.setSlNo(i++);
            indianData.setStateDeltaModels(stateDeltaObject);

            indianDataList.add(indianData);
        }

        return indianDataList;
    }
}
