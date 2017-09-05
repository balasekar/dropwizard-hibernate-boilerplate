package com.product.app.resources;


import com.codahale.metrics.annotation.Timed;
import com.product.app.core.Person;
import com.product.app.dao.PersonDAO;
import com.product.app.views.PersonView;
import io.dropwizard.hibernate.UnitOfWork;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/personView")
@Produces({MediaType.TEXT_HTML})
public class PersonViewResource {
    PersonDAO personDAO;

    public PersonViewResource(PersonDAO personDAO) {
        super();
        this.personDAO = personDAO;
    }

    @GET
    @Timed
    @UnitOfWork
    public PersonView getAll() {
        List<Person> personList = personDAO.getAll();
        return new PersonView(personList);
    }
}