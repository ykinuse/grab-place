package com.grabtaxi.grabplace.model;

import com.grabtaxi.grabplace.utils.GsonUtils;

/**
 * Created by ykinuse on 13/09/2015.
 * <p/>
 * "doc": {
 * "_rev": "1-c3f479bbd1549df386733ca5434a3f5c",
 * "_id": "0d3a985c4e1d8638e7815e8d5820a39c",
 * "state": "Perak",
 * "loc": [
 * 4.11689,
 * 101.29766
 * ],
 * "label": "BIDOR RESERVOIR",
 * "type": "POI",
 * "fullAdd": "BIDOR RESERVOIR, BIDOR, PERAK, MALAYSIA",
 * "city": "Bidor"
 * }
 */
public class Place
{
    String   _id;
    String   state;
    String   label;
    String   type;
    String   fullAdd;
    String   city;
    double[] loc;

    @Override
    public String toString()
    {
        return GsonUtils.getGson().toJson(this);
    }
}
