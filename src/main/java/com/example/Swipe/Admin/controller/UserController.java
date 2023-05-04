package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.TypeNotification;
import com.example.Swipe.Admin.service.impl.ContractorServiceImpl;
import com.example.Swipe.Admin.service.impl.NotaryServiceImpl;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Controller
public class UserController {
    @Value("${upload.path}")
    private String upload;
    private TypeNotification typeNotification;
    private final UserServiceImpl userServiceImpl;
    private final ContractorServiceImpl contractorServiceImpl;
    private final NotaryServiceImpl notaryServiceImpl;

    public UserController(UserServiceImpl userServiceImpl, ContractorServiceImpl contractorServiceImpl, NotaryServiceImpl notaryServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.contractorServiceImpl = contractorServiceImpl;

        this.notaryServiceImpl = notaryServiceImpl;
    }
    @GetMapping("/users")
    public String usersMain(Model model) {
        model.addAttribute("users", userServiceImpl.users());
        model.addAttribute("contractors", contractorServiceImpl.findAll());
        model.addAttribute("notaries", notaryServiceImpl.findAll());

        return "admin/user_main";
    }
    @GetMapping("/add_user")
    public String addUser(Model model){
        User user = User.builder().name("").surname("").typeNotification(TypeNotification.ME).callSms(true).dateSub(LocalDate.now()).filename("../admin/dist/img/default.jpg").mail("").number("").password("").build();
        userServiceImpl.saveEntity(user);
        return "redirect:/users";
    }
    @GetMapping("/user_edit/{id}")
    public String userEdit(@PathVariable int id, Model model){
        model.addAttribute("user",userServiceImpl.findById(id));
        model.addAttribute("type",typeNotification);
        return "admin/user_edit";
    }
    @PostMapping("/user_update/{id}")
    public String userUpdate(@PathVariable int id, @RequestParam(required = false,name = "callSms") boolean callSms, @RequestParam String name, @RequestParam String surname, @RequestParam String mail, @RequestParam String number, @RequestParam LocalDate dateSub, @RequestParam TypeNotification typeNotification, @RequestParam(name = "file") MultipartFile file, Model model) throws IOException {
        System.out.println(callSms);
        User user = User.builder().callSms(callSms).name(name).surname(surname).mail(mail).dateSub(dateSub).number(number).typeNotification(typeNotification).build();
        User preUser = userServiceImpl.findById(id);
        if (!file.isEmpty()) {
            File uploadDirGallery = new File(upload);
            if (!uploadDirGallery.exists()) {
                uploadDirGallery.mkdir();
            }
            String uuid = UUID.randomUUID().toString();
            String fileNameGallery = uuid + "-" + file.getOriginalFilename();
            String resultNameGallery = upload + "" + fileNameGallery;
            file.transferTo(new File((resultNameGallery)));
            if (!preUser.getFilename().equals("../admin/dist/img/default.jpg")) {
                String fileNameDelete = preUser.getFilename().substring(11, preUser.getFilename().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
            user.setFilename("../uploads/" + fileNameGallery);
        }
        userServiceImpl.updateEntity(user,id);
        return "redirect:/users";
    }
}
