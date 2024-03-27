package com.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author rahul
 */
@Data
public class RealTimeResponse {

    @JsonProperty("from_name")
    private String fromName;

    @JsonProperty("to_date")
    private String toDate;

    @JsonProperty("email")
    private String email;

    @JsonProperty("query")
    private String query;
}
