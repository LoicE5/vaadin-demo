package demo.views.about;

import com.flowingcode.addons.ycalendar.YearCalendar;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import demo.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.datepicker.DatePicker;
import org.vaadin.addons.tatu.ColorPicker;

import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.Locale;


@PageTitle("À propos")
@Route(value = "about", layout = MainLayout.class)
@AnonymousAllowed
public class AboutView extends VerticalLayout {

    public AboutView() {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        H2 header = new H2("Cette page est laissée vide intentionnellement.");
        header.addClassNames(Margin.Top.XLARGE, Margin.Bottom.MEDIUM);
        add(header);
        add(new Paragraph("Ajoutons-y quelques composants."));

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");

        // DatePicker
        DatePicker datePicker = new DatePicker("Select a Date");
        datePicker.setLocale(Locale.FRANCE);

        // Accordion with some content
        Accordion accordion = new Accordion();
        accordion.add("À propos de Vaadin", new Div(new Paragraph("Vaadin est un framework web fort sympathique qui propose une large collection de composants pour vous permettre de créer le site de vos rêves.")));
        accordion.add("Nos tarifs", new Div(new Paragraph("À peu près tous ce qui en jette chez nous est payant. Ce magnifique dashboard par exemple nécessite une licence. Vous pouvez entrer vos coordonnées bancaires sur cette démo.")));

        // ProgressBar
        ProgressBar progressBar = new ProgressBar();
        progressBar.setValue(0.5);

        // SplitLayout with two components
        SplitLayout splitLayout = new SplitLayout();
        Div leftPanel = new Div(new H3("Java"), new Paragraph("Java est un langage orienté objet qui utilise une machine virtuelle pour être exécuté sur un grand nombre de machines..."));
        Div rightPanel = new Div(new H3("JavaScript"), new Paragraph("JavaScript est la langage du web par excellence qui est exécuté dans tous les navigateurs et quelques serveurs. D'ailleurs, ce que nous lisons a été transformé en JavaScript..."));
        splitLayout.addToPrimary(leftPanel);
        splitLayout.addToSecondary(rightPanel);
        splitLayout.setSplitterPosition(50);
        splitLayout.setMinHeight("300px");

        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setLabel("Couleur");
        colorPicker
                .setPresets(Arrays.asList(
                        new ColorPicker.ColorPreset("#00ff00", "Vert"),
                        new ColorPicker.ColorPreset("#ff0000", "Pas vert"),
                        new ColorPicker.ColorPreset("#3d9cff", "Non plus")
                ));

        colorPicker.addValueChangeListener(event -> {
            Notification.show(event.getValue());
        });

        colorPicker.setClassName("space-below");

        H4 h41 = new H4("Sélectionner une date");
        H4 h42 = new H4("Des sections ouvrantes");
        H4 h43 = new H4("Une barre de progression");
        H4 h44 = new H4("Deux textes de largeur ajustable");
        H4 h45 = new H4("Un color picker installé via la librairie de plugins");

        h41.setClassName("sample-element");
        h42.setClassName("sample-element");
        h43.setClassName("sample-element");
        h44.setClassName("sample-element");
        h45.setClassName("sample-element");
        datePicker.setClassName("sample-element");
        accordion.setClassName("sample-element");
        progressBar.setClassName("sample-element");
        splitLayout.setClassName("sample-element");

        // Add components to the view
        add(
                h41,
                datePicker,
                h42,
                accordion,
                h43,
                progressBar,
                h44,
                splitLayout,
                h45,
                colorPicker
        );
    }

}
