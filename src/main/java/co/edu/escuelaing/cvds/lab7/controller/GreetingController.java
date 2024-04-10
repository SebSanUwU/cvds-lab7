package co.edu.escuelaing.cvds.lab7.controller;

import co.edu.escuelaing.cvds.lab7.model.ToDoItem;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @GetMapping("/to-do-item/{id}")
    public String getUser(@PathVariable Integer id, Model model) {
        String uri = "https://jsonplaceholder.typicode.com/todos/" + Integer.toString(id);
        RestTemplate restTemplate = new RestTemplate();

        ToDoItem toDoItem = restTemplate.getForObject(uri, ToDoItem.class);

        model.addAttribute("toDoItem", toDoItem);
        return "to-do-item";
    }
}
