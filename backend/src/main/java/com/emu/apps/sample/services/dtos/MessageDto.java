package com.emu.apps.sample.services.dtos;

/**
 * Created by eric on 14/06/2017.
 */
public class MessageDto {

    String message;

    public MessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
