package de.uniba.dsg.jaxrs.models.error;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class ErrorMessage {

    @XmlElement(required = true)
    private ErrorType errorType;
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(final ErrorType errorType, final String message) {
        this.errorType = errorType;
        this.message = message;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public void setErrorType(final ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "errorType=" + this.errorType +
                ", message='" + this.message + '\'' +
                '}';
    }
}
