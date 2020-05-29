package de.uniba.dsg.jaxrs.models.error;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum(String.class)
public enum ErrorType {
    INVALID_PARAMETER
}
