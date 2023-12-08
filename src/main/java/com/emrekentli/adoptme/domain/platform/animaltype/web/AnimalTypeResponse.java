package com.emrekentli.adoptme.domain.platform.animaltype.web;


import java.util.Date;

public record AnimalTypeResponse (String id, Date created, Date modified, String name) {
}