package demo.views.helloworld;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import demo.views.MainLayout;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
@AnonymousAllowed
public class HelloWorldView extends VerticalLayout {

    private final TextField name;

    public HelloWorldView() {
        name = new TextField("Your name");
        Button sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);

        setMargin(true);
        setAlignItems(Alignment.START);

        add(name, sayHello);

        add(new H3("⬆️ Voici ce qu'on voit quand on lance une application Vaadin pour la première fois."));
        add(new Paragraph("C'est pas mal, surtout quand on considère que le code nécessaire à cela ne fait que 9 lignes."));
        add(new Paragraph("Voyons un instant ce que nous pouvons faire d'autre en utilisant Vaadin, grâce à ses composants, comme par exemple un formulaire assez basique."));

        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("21em", 2),
                new FormLayout.ResponsiveStep("32em", 3));

        Binder<FormData> binder = new BeanValidationBinder<>(FormData.class);
        FormData formData = new FormData();
        binder.setBean(formData);

        // Create form fields
        TextField nameField = new TextField("Nom");
        binder.forField(nameField).bind("name");

        EmailField emailField = new EmailField("Email");
        binder.forField(emailField).bind("email");

        PasswordField passwordField = new PasswordField("Mot de passe");
        binder.forField(passwordField).bind("password");

        DatePicker birthdatePicker = new DatePicker("Date de naissance");
        binder.forField(birthdatePicker).bind("birthdate");

        Checkbox subscribeCheckbox = new Checkbox("S'abonner à la newsletter");
        binder.forField(subscribeCheckbox).bind("subscribeNewsletter");

        TextArea commentsArea = new TextArea("Commentaires");
        binder.forField(commentsArea).bind("comments");

        // Create submit button
        Button submitButton = new Button("Soumettre", e -> {
            if (binder.validate().isOk()) {
                Notification.show("Formulaire soumis avec succès");
            } else {
                Notification.show("Le formulaire comporte des erreurs de validation");
            }
        });

        // Add components to the form layout
        formLayout.addFormItem(nameField, "Nom");
        formLayout.addFormItem(emailField, "Email");
        formLayout.addFormItem(passwordField, "Mot de passe");
        formLayout.addFormItem(birthdatePicker, "Date de naissance");
        formLayout.addFormItem(subscribeCheckbox, "S'abonner à la newsletter");
        formLayout.addFormItem(commentsArea, "Commentaires");

        // Add the form layout and submit button to the view
        add(new H3("Exemple de Formulaire"), formLayout, submitButton);
    }

    public static class FormData {

        @NotEmpty(message = "Le nom est obligatoire")
        String name;

        @Email(message = "Adresse email invalide")
        @NotEmpty(message = "L'email est obligatoire")
        String email;

        String password;

        LocalDate birthdate;

        boolean subscribeNewsletter;

        String comments;

        // Getters and setters...

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public LocalDate getBirthdate() {
            return birthdate;
        }

        public void setBirthdate(LocalDate birthdate) {
            this.birthdate = birthdate;
        }

        public boolean isSubscribeNewsletter() {
            return subscribeNewsletter;
        }

        public void setSubscribeNewsletter(boolean subscribeNewsletter) {
            this.subscribeNewsletter = subscribeNewsletter;
        }

        public String getComments() {
            return comments;
        }

        public void setComments(String comments) {
            this.comments = comments;
        }
    }

}