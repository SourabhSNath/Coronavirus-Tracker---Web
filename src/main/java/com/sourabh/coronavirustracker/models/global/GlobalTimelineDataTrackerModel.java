package com.sourabh.coronavirustracker.models.global;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GlobalTimelineDataTrackerModel {

    private final Map<String, Integer> map = new LinkedHashMap<>();
    private final List<Integer> list = new ArrayList<>();

    public GlobalTimelineDataTrackerModel() {

    }

    @JsonAnySetter
    public void setList(String key, Integer val) {
        this.map.put(key, val);
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public List<Integer> getList() {
        this.list.addAll(getMap().values());
        return list;
    }


    @Override
    public String toString() {
        return "GlobalTimelineDataTracker{" +
                "list=" + getList() +
                '}';
    }
}
