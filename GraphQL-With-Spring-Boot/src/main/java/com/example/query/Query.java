package com.example.query;

import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

/**
 * @author rahul
 */
@Component
public class Query implements GraphQLQueryResolver {

    public String firstQuery() {
        return "First Query";
    }
}
