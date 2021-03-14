package ru.anton.views.question;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.router.PageTitle;
import org.springframework.beans.factory.annotation.Autowired;
import ru.anton.data.entity.AnswerDetails;
import ru.anton.data.repository.AnswerDetailsRepository;

import java.util.Comparator;

@PageTitle("Answers")
public class TableOfAnswersToQuestionsView extends Div {

   private final AnswerDetailsRepository answerDetailsRepository;

    Grid<AnswerDetails> grid;

    @Autowired
    public TableOfAnswersToQuestionsView(AnswerDetailsRepository answerDetailsRepository) {
        this.answerDetailsRepository = answerDetailsRepository;


        grid = new Grid<>(AnswerDetails.class);
        grid.addThemeVariants(GridVariant.LUMO_WRAP_CELL_CONTENT);

        add(grid);
        grid.setColumns("textQuestion");
        grid.addComponentColumn(item ->{
            Icon icon = null;
            if(item.getStatus() == true){
                icon = VaadinIcon.CHECK.create();
                icon.setColor("green");
            }else if(item.getStatus() == false){
                icon = VaadinIcon.EXCLAMATION.create();
                icon.setColor("red");
            }
            return icon;
        }).setKey("icon").setHeader("Status").setComparator(Comparator.comparing(AnswerDetails::getStatus));

        listAnswerDetails();

    }

    private void listAnswerDetails() {
        grid.setItems(answerDetailsRepository.findAll());
    }
}
