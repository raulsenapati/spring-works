package com.example.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author rahul
 */
@Getter
@Setter
@ToString
public class InQueryRequest {

    private List<String> firstNames;
}
