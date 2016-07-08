package com.textmining.ui.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class BaseController {

    public void error(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(message));
    }

    public void error(String clientId, String message) {
        if (clientId != null) {
            FacesContext.getCurrentInstance().addMessage(clientId, new FacesMessage(message));
        } else {
            error(message);
        }
    }
}
