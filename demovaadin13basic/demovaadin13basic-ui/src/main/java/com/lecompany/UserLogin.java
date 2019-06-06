package com.lecompany;
import com.vaadin.flow.templatemodel.TemplateModel;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

/**
 * A Designer generated component for the user-login template.
 *
 * Designer will add and remove fields with @Id mappings but
 * does not overwrite or otherwise change this file.
 */
@Tag("user-login")
@HtmlImport("css/user-login.html")
public class UserLogin extends PolymerTemplate<UserLogin.UserLoginModel> {

    /**
     * Creates a new UserLogin.
     */
    public UserLogin() {
        // You can initialise any data required for the connected UI components here.
    }

    /**
     * This model binds properties between UserLogin and user-login
     */
    public interface UserLoginModel extends TemplateModel {
        // Add setters and getters for template properties here.
    }
}
