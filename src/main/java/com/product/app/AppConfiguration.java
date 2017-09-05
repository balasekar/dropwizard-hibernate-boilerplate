package com.product.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AppConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory dbConfiguration = new DataSourceFactory();

    @JsonProperty("dbConfiguration")
    public DataSourceFactory getDbConfiguration() {
        return dbConfiguration;
    }
}
