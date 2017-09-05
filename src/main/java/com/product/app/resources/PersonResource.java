package com.product.app.resources;


import com.product.app.core.Person;
import com.product.app.dao.PersonDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/person")
public class PersonResource {

    PersonDAO personDAO;

    public PersonResource(PersonDAO personDAO) {
        super();
        this.personDAO = personDAO;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @UnitOfWork
    public List<Person> getAll() {
        List<Person> personList = personDAO.getAll();
        return personList;
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    @Produces({MediaType.APPLICATION_JSON})
    public Person get(@PathParam("id") Integer id) {
        return personDAO.findById(id);
    }

    @POST
    @UnitOfWork
    @Produces({MediaType.APPLICATION_JSON})
    public Person add(@Valid Person person) {
        Person newPerson = personDAO.insert(person);
        return newPerson;
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    @Produces({MediaType.APPLICATION_JSON})
    public Person update(@PathParam("id") Integer id, @Valid Person person) {
        person = person.setId(id);
        personDAO.update(person);
        return person;
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public void delete(@PathParam("id") Integer id) {
        personDAO.delete(personDAO.findById(id));
    }
}