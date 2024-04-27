package com.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author rahul
 */
@Data
public class RealTimeResponse {

    @JsonProperty("from_date")
    @SerializedName("from_date")
    private String fromDate;

    @JsonProperty("to_date")
    @SerializedName("to_date")
    private String toDate;

    @JsonProperty("email")
    private String email;

    @JsonProperty("query")
    private String query;
}
