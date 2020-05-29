package de.uniba.dsg.jaxrs.models.logic;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum(String.class)
public enum Popularity {
    // like for actors and other people of interest
    A, B, C
}
