package co.edu.escuelaing.cvds.lab7.controller;

import co.edu.escuelaing.cvds.lab7.model.ToDoItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class GreetingController {

    @RequestMapping("/to-do-item/{id}")
    public String getUser(@PathVariable Integer id, Model model) {
        String uri = "https://jsonplaceholder.typicode.com/todos/{id}";
        RestTemplate restTemplate = new RestTemplate();

        try {
            ToDoItem toDoItem = restTemplate.getForObject(uri, ToDoItem.class, id);
            if (toDoItem != null) {
                model.addAttribute("toDoItem", toDoItem);
            } else {
                return "error";
            }
        } catch (Exception e) {
            return "error";
        }

        return "to-do-item";
    }
}
