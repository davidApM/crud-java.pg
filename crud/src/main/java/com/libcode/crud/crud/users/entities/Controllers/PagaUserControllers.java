package com.libcode.crud.crud.users.entities.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.libcode.crud.crud.users.entities.User;
import com.libcode.crud.crud.users.entities.repository.UserRepository;

import jakarta.websocket.server.PathParam;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/")
public class PagaUserControllers {
   
    @Autowired
    private UserRepository userRepository; 
   
    @GetMapping("/users")
    public String listUsers(Model model){
        //Pasar datos a la lista. 
        model.addAttribute("users", userRepository.findAll()); 
        return "list-users"; 
    }

    //metodo para agregar usuarios 
    @GetMapping("/nuevo")
    public String formularioNuevoUser(Model model){
        //Pasar datos a la lista. 
        model.addAttribute("usuario", new User()); 
        return "form-users"; 
    }

    //Enviar datos a la vista. 
    @PostMapping("/nuevo")
    public String guardarUser(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    //Metodo para editar
    @GetMapping("/editar/{id}")
    public String formularioEditarUser(Model model, @PathVariable Long id){
        User usuario = userRepository.findById(id).get();
        model.addAttribute("usuario",usuario);
        return "form-users"; 
    }

    //Metodo para eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminarUser(@PathVariable Long id){
        userRepository.delete(new User(id));
        return  "redirect:/users"; 
    }


    


}
