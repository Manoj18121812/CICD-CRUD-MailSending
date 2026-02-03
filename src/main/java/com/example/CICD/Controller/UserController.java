package com.example.CICD.Controller;

import com.example.CICD.Entity.User;
import com.example.CICD.Repository.UserRepository;
import com.example.CICD.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserRepository repo;

    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public String home(Model model){
    model.addAttribute("users",repo.findAll());
    return "index";
    }

    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("user",new User());
        return "form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute User user){
    repo.save(user);
    emailService.sendEmail(
            user.getEmail(), "Registration Sucessfull..",
            "Hello"+user.getName()+", Your Data Save Sucessfully.."
    );
    return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
    model.addAttribute("user",repo.findById(id).get());
    return "form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/";
    }


}
