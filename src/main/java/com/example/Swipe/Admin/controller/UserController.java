package com.example.Swipe.Admin.controller;


import com.example.Swipe.Admin.dto.AgentDTO;
import com.example.Swipe.Admin.dto.ClientDTO;
import com.example.Swipe.Admin.dto.UserAddInfoDTO;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.entity.UserAddInfo;
import com.example.Swipe.Admin.enums.Role;
import com.example.Swipe.Admin.enums.TypeNotification;
import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.mapper.ClientMapper;
import com.example.Swipe.Admin.service.impl.UserAddInfoServiceImpl;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import com.example.Swipe.Admin.validation.UniqueEmailValidator;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {
    private Logger log = LoggerFactory.getLogger(UserController.class);
    private TypeNotification typeNotification;
    private final UserServiceImpl userServiceImpl;







//    @GetMapping("/users")
//    public String usersMain(Model model) {
//        model.addAttribute("users", userServiceImpl.users());
//        model.addAttribute("contractors", contractorServiceImpl.findAll());
//        model.addAttribute("notaries", notaryServiceImpl.findAll());
//        if(tokenRepo.findAllValidTokensByAdmin(18).size()>0) {
//            model.addAttribute("jwtToken", tokenRepo.findAllValidTokensByAdmin(18).get(0).getToken());
//        }
//        else {
//            model.addAttribute("jwtToken", tokenRepo.findById(tokenRepo.findAll().size()-1));
//        }
//        return "admin/user_main";
//    }
    @GetMapping("/add_user")
    public String addUser(@RequestParam(name = "type") TypeUser type, Model model){
        model.addAttribute("type", type);
        model.addAttribute("user", ClientDTO.builder().userAddInfoDTO(UserAddInfoDTO.builder().typeNotification(TypeNotification.ME).build()).agent(AgentDTO.builder().build()).build());
        if(type.equals(TypeUser.CLIENT)) {
            return "admin/user_add";
        }
        else if(type.equals(TypeUser.CONTRACTOR)){
            return "admin/contractor_add";
        }
        else {
            return "admin/notary_add";
        }
    }

//    @PostMapping("/user_add")
//    public String userAdd(@RequestParam(name = "type")TypeUser type, @RequestParam(required = false,name = "callSms") boolean callSms, @RequestParam String name, @RequestParam String surname, @RequestParam String mail, @RequestParam String number, @RequestParam(required = false) LocalDate dateSub, @RequestParam(required = false) TypeNotification typeNotification, @RequestParam(name = "file") MultipartFile file, Model model) throws IOException {
//        System.out.println(callSms);
//
//        @Valid UserDTO user = UserDTO.builder().name(name).surname(surname).mail(mail).number(number).typeUser(type).build();
//
//
////        switch (type) {
////            case "client" -> user.setTypeUser(TypeUser.CLIENT);
////            case "contractor" -> user.setTypeUser(TypeUser.CONTRACTOR);
////            case "notary" -> user.setTypeUser(TypeUser.NOTARY);
////        }
//        if (!file.isEmpty()) {
//            File uploadDirGallery = new File(upload);
//            if (!uploadDirGallery.exists()) {
//                uploadDirGallery.mkdir();
//            }
//            String uuid = UUID.randomUUID().toString();
//            String fileNameGallery = uuid + "-" + file.getOriginalFilename();
//            String resultNameGallery = upload + "" + fileNameGallery;
//            file.transferTo(new File((resultNameGallery)));
//            user.setFilename("../uploads/" + fileNameGallery);
//        }
//        else {
//            user.setFilename("../admin/dist/img/default.jpg");
//        }
////        if(dateSub!=null&&typeNotification!=null) {
////            UserAddInfo userAddInfo = UserAddInfo.builder().callSms(callSms).dateSub(dateSub).typeNotification(typeNotification).build();
////            user.setUserAddInfo(userAddInfo);
////            userAddInfoService.saveEntity(userAddInfo);
////        }
//        User user1 = userMapper.toEntity(user);
//        userServiceImpl.saveEntity(user1);
//
//
//        return "redirect:/users";
//    }




//    @PostMapping("/user_add")
//    public String userAdd(@RequestParam(name = "type")TypeUser type, @RequestParam(required = false,name = "callSms") boolean callSms, @RequestParam String name, @RequestParam String surname,
//                          @RequestParam String mail, @RequestParam String number, @RequestParam(required = false) LocalDate dateSub, @RequestParam(required = false) TypeNotification typeNotification, @RequestParam(name = "file") MultipartFile file, Model model) throws IOException {
//        System.out.println(callSms);
//        User user = User.builder().name(name).surname(surname).mail(mail).number(number).role(Role.USER).blackList(false).typeUser(type).build();
//
//
////        switch (type) {
////            case "client" -> user.setTypeUser(TypeUser.CLIENT);
////            case "contractor" -> user.setTypeUser(TypeUser.CONTRACTOR);
////            case "notary" -> user.setTypeUser(TypeUser.NOTARY);
////        }
//        if (!file.isEmpty()) {
//            File uploadDirGallery = new File(upload);
//            if (!uploadDirGallery.exists()) {
//                uploadDirGallery.mkdir();
//            }
//            String uuid = UUID.randomUUID().toString();
//            String fileNameGallery = uuid + "-" + file.getOriginalFilename();
//            String resultNameGallery = upload + "" + fileNameGallery;
//            file.transferTo(new File((resultNameGallery)));
//            user.setFilename("../uploads/" + fileNameGallery);
//        }
//        else {
//            user.setFilename("../admin/dist/img/default.jpg");
//        }
//        if(dateSub!=null&&typeNotification!=null) {
//            UserAddInfo userAddInfo = UserAddInfo.builder().callSms(callSms).dateSub(dateSub).typeNotification(typeNotification).build();
//            user.setUserAddInfo(userAddInfo);
//            userAddInfoService.saveEntity(userAddInfo);
//        }
//
//        userServiceImpl.saveEntity(user);
//
//
//        return "redirect:/users";
//    }
    @PostMapping("/add_user")
    public String userAdd(@Valid @ModelAttribute(name = "user") ClientDTO clientDTO, BindingResult bindingResult, Model model) throws IOException {
        bindingResult = userServiceImpl.uniqueMail(clientDTO.getMail(),bindingResult,0,"add");
        if(clientDTO.getUserAddInfoDTO()!=null){
            if (clientDTO.getUserAddInfoDTO().getDateSub()!=null) {
                if (clientDTO.getUserAddInfoDTO().getDateSub().isBefore(LocalDate.now())) {
                    bindingResult.addError(new FieldError("user", "userAddInfoDTO.dateSub", "Некоректная дата"));
                }
            }
        }
        System.out.println(clientDTO);
        if (bindingResult.hasErrors()){
            System.out.println(bindingResult.getAllErrors());

            model.addAttribute("type", clientDTO.getType());
            model.addAttribute("user", clientDTO);
            if(clientDTO.getType().equals(TypeUser.CLIENT)) {
                return "admin/user_add";
            }
            else if(clientDTO.getType().equals(TypeUser.CONTRACTOR)){
                return "admin/contractor_add";
            }
            else {
                return "admin/notary_add";
            }
        }
        userServiceImpl.saveEntityDTO(clientDTO);
        log.info("Create new "+clientDTO.getType());

        return "redirect:/users";
    }

    @GetMapping("/user_edit/{id}")
    public String userEdit(@PathVariable int id, Model model){
        model.addAttribute("user",userServiceImpl.findByIdDTO(id));
        model.addAttribute("type",typeNotification);
        return "admin/user_edit";
    }
//    @PostMapping("/user_edit/{id}")
//    public String userUpdate(@PathVariable int id, @RequestParam(required = false,name = "callSms") boolean callSms, @RequestParam String name, @RequestParam String surname, @RequestParam String mail, @RequestParam String number, @RequestParam(required = false) LocalDate dateSub, @RequestParam(required = false) TypeNotification typeNotification, @RequestParam(name = "file") MultipartFile file, Model model) throws IOException {
//        System.out.println(callSms);
//        User user = User.builder().name(name).surname(surname).mail(mail).number(number).build();
//        User preUser = userServiceImpl.findById(id);
//
//        if (!file.isEmpty()) {
//            File uploadDirGallery = new File(upload);
//            if (!uploadDirGallery.exists()) {
//                uploadDirGallery.mkdir();
//            }
//            String uuid = UUID.randomUUID().toString();
//            String fileNameGallery = uuid + "-" + file.getOriginalFilename();
//            String resultNameGallery = upload + "" + fileNameGallery;
//            file.transferTo(new File((resultNameGallery)));
//            if (!preUser.getFilename().equals("../admin/dist/img/default.jpg")) {
//                String fileNameDelete = preUser.getFilename().substring(11, preUser.getFilename().length());
//                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
//                fileDelete.delete();
//            }
//            user.setFilename("../uploads/" + fileNameGallery);
//        }
//
//        userServiceImpl.updateEntity(user,id);
//        if(preUser.getUserAddInfo()!=null) {
//            UserAddInfo userAddInfo = UserAddInfo.builder().typeNotification(typeNotification).dateSub(dateSub).callSms(callSms).build();
//            userAddInfoService.updateEntity(userAddInfo,preUser.getUserAddInfo().getIdUserAddInfo());
//        }
//        return "redirect:/users";
//    }
    @PostMapping("/user_edit/{id}")
    public String userUpdate(@PathVariable int id, @Valid @ModelAttribute(name = "user") ClientDTO clientDTO,BindingResult result, Model model) {
        result = userServiceImpl.uniqueMail(clientDTO.getMail(),result,id,"update");
//        if(clientDTO.getUserAddInfoDTO()!=null){
//            if (clientDTO.getUserAddInfoDTO().getDateSub().isBefore(LocalDate.now())){
//                result.addError(new FieldError("user", "userAddInfoDTO.dateSub", "Некоректная дата"));
//            }
//        }
        if (result.hasErrors()){
            System.out.println(result);
            clientDTO.setAgent(userServiceImpl.findByIdDTO(id).getAgent());
            clientDTO.setPhoto(userServiceImpl.findByIdDTO(id).getPhoto());
            model.addAttribute("user",clientDTO);
            model.addAttribute("type",typeNotification);
            return "admin/user_edit";
        }

        clientDTO.setIdUser(id);
        userServiceImpl.updateDTO(clientDTO,id);
        log.info("User id "+id+", was update");
        return "redirect:/users";
    }

    @PostMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable int id, Model model){

        userServiceImpl.deleteById(id);
        log.info("User id "+id+", was delete");
        return "redirect:/users";
    }
}
