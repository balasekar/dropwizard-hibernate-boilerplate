package com.product.app;

import com.fasterxml.jackson.databind.MapperFeature;
import com.product.app.core.Person;
import com.product.app.dao.PersonDAO;
import com.product.app.resources.PersonResource;
import com.product.app.resources.PersonViewResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;

public class AppService extends Application<AppConfiguration> {
    public static void main(String[] args) throws Exception {
        new AppService().run(args);
    }

    private final HibernateBundle<AppConfiguration> hibernate = new HibernateBundle<AppConfiguration>(Person.class) {
        public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
            return configuration.getDbConfiguration();
        }
    };

    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(new ViewBundle<AppConfiguration>());
    }

    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        final PersonDAO personDAO = new PersonDAO(hibernate.getSessionFactory());
        final PersonResource personResource = new PersonResource(personDAO);
        final PersonViewResource personViewResource = new PersonViewResource(personDAO);

        environment.jersey().register(personResource);
        environment.jersey().register(personViewResource);
        environment.getObjectMapper().disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
    }
}
