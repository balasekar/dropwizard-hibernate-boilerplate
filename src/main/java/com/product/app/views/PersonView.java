package com.product.app.views;

import com.product.app.core.Person;
import io.dropwizard.views.View;

import java.util.List;

public class PersonView extends View {

    private final List<Person> personList;

    public PersonView(List<Person> personList) {
        super("person.ftl");
        this.personList = personList;
    }

    public List<Person> getPersonList() {
        return personList;
    }
}
