package com.example.Swipe.Admin.controller;


import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.entity.UserAddInfo;
import com.example.Swipe.Admin.enums.Role;
import com.example.Swipe.Admin.enums.TypeNotification;
import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.mapper.ClientMapper;
import com.example.Swipe.Admin.service.impl.UserAddInfoServiceImpl;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class UserController {
    @Value("${upload.path}")
    private String upload;
    private TypeNotification typeNotification;
    private final UserServiceImpl userServiceImpl;
    private final UserAddInfoServiceImpl userAddInfoService;
    private final ClientMapper clientMapper;






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
//        model.addAttribute("userDTO", UserDTO.builder().build());
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




    @PostMapping("/user_add")
    public String userAdd(@RequestParam(name = "type")TypeUser type, @RequestParam(required = false,name = "callSms") boolean callSms, @RequestParam String name, @RequestParam String surname,
                          @RequestParam String mail, @RequestParam String number, @RequestParam(required = false) LocalDate dateSub, @RequestParam(required = false) TypeNotification typeNotification, @RequestParam(name = "file") MultipartFile file, Model model) throws IOException {
        System.out.println(callSms);
        User user = User.builder().name(name).surname(surname).mail(mail).number(number).role(Role.USER).blackList(false).typeUser(type).build();


//        switch (type) {
//            case "client" -> user.setTypeUser(TypeUser.CLIENT);
//            case "contractor" -> user.setTypeUser(TypeUser.CONTRACTOR);
//            case "notary" -> user.setTypeUser(TypeUser.NOTARY);
//        }
        if (!file.isEmpty()) {
            File uploadDirGallery = new File(upload);
            if (!uploadDirGallery.exists()) {
                uploadDirGallery.mkdir();
            }
            String uuid = UUID.randomUUID().toString();
            String fileNameGallery = uuid + "-" + file.getOriginalFilename();
            String resultNameGallery = upload + "" + fileNameGallery;
            file.transferTo(new File((resultNameGallery)));
            user.setFilename("../uploads/" + fileNameGallery);
        }
        else {
            user.setFilename("../admin/dist/img/default.jpg");
        }
        if(dateSub!=null&&typeNotification!=null) {
            UserAddInfo userAddInfo = UserAddInfo.builder().callSms(callSms).dateSub(dateSub).typeNotification(typeNotification).build();
            user.setUserAddInfo(userAddInfo);
            userAddInfoService.saveEntity(userAddInfo);
        }

        userServiceImpl.saveEntity(user);


        return "redirect:/users";
    }


//@RequestParam(name = "type")TypeUser type, @RequestParam(required = false,name = "callSms") boolean callSms, @RequestParam String name, @RequestParam String surname, @RequestParam String mail, @RequestParam String number,@RequestParam(name = "file", required = false) MultipartFile file
//    @PostMapping("/user_add")
//    public String userAdd(@Valid @ModelAttribute UserDTO userDTO , Model model) throws IOException {
////        System.out.println(callSms);
//
////        User user = User.builder().name(name).surname(surname).mail(mail).number(number).role(Role.USER).blackList(false).typeUser(type).build();
//
//
////        switch (type) {
////            case "client" -> user.setTypeUser(TypeUser.CLIENT);
////            case "contractor" -> user.setTypeUser(TypeUser.CONTRACTOR);
////            case "notary" -> user.setTypeUser(TypeUser.NOTARY);
////        }
////        if (!file.isEmpty()) {
////            File uploadDirGallery = new File(upload);
////            if (!uploadDirGallery.exists()) {
////                uploadDirGallery.mkdir();
////            }
////            String uuid = UUID.randomUUID().toString();
////            String fileNameGallery = uuid + "-" + file.getOriginalFilename();
////            String resultNameGallery = upload + "" + fileNameGallery;
////            file.transferTo(new File((resultNameGallery)));
////            userDTO.setFilename("../uploads/" + fileNameGallery);
////        }
////        else {
////            userDTO.setFilename("../admin/dist/img/default.jpg");
////        }
////        if(dateSub!=null&&typeNotification!=null) {
////            UserAddInfo userAddInfo = UserAddInfo.builder().callSms(callSms).dateSub(dateSub).typeNotification(typeNotification).build();
////            userDTO.setUserAddInfo(userAddInfo);
////            userAddInfoService.saveEntity(userAddInfo);
////        }
//        User user = userMapper.toEntity(userDTO);
//        userServiceImpl.saveEntity(user);
//
//
//        return "redirect:/users";
//    }
    @GetMapping("/user_edit/{id}")
    public String userEdit(@PathVariable int id, Model model){
        model.addAttribute("user",userServiceImpl.findById(id));
        model.addAttribute("type",typeNotification);
        return "admin/user_edit";
    }
    @PostMapping("/user_update/{id}")
    public String userUpdate(@PathVariable int id, @RequestParam(required = false,name = "callSms") boolean callSms, @RequestParam String name, @RequestParam String surname, @RequestParam String mail, @RequestParam String number, @RequestParam(required = false) LocalDate dateSub, @RequestParam(required = false) TypeNotification typeNotification, @RequestParam(name = "file") MultipartFile file, Model model) throws IOException {
        System.out.println(callSms);
        User user = User.builder().name(name).surname(surname).mail(mail).number(number).build();
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
        if(preUser.getUserAddInfo()!=null) {
            UserAddInfo userAddInfo = UserAddInfo.builder().typeNotification(typeNotification).dateSub(dateSub).callSms(callSms).build();
            userAddInfoService.updateEntity(userAddInfo,preUser.getUserAddInfo().getIdUserAddInfo());
        }
        return "redirect:/users";
    }

    @PostMapping("/delete_user/{id}")
    public String deleteUser(@PathVariable int id, Model model){
        User user = userServiceImpl.findById(id);
        if(user.getFilename()!=null) {
            if (!user.getFilename().equals("../admin/dist/img/default.jpg")) {
                String fileNameDelete = user.getFilename().substring(11, user.getFilename().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
        }
        userServiceImpl.deleteById(id);
        if(user.getUserAddInfo()!=null) {
            userAddInfoService.deleteById(user.getUserAddInfo().getIdUserAddInfo());
        }
        return "redirect:/users";
    }
}
