package ru.mydemo.graphql;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import javax.inject.Singleton;

@Singleton
public class HelloDataFetcher implements DataFetcher<String> {

    @Override
    public String get(DataFetchingEnvironment environment) throws Exception {
        return "World!";
    }
}
