package com.example.Swipe.Admin.service.impl;

//import com.example.Swipe.Admin.entity.Contractor;
import com.example.Swipe.Admin.dto.AdminDto;
import com.example.Swipe.Admin.dto.BlackListDTO;
import com.example.Swipe.Admin.dto.ClientDTO;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.mapper.AdminMapper;
import com.example.Swipe.Admin.mapper.BlackLIstMapper;
import com.example.Swipe.Admin.mapper.ClientMapper;
import com.example.Swipe.Admin.repository.UserRepo;
import com.example.Swipe.Admin.service.UserService;
import com.example.Swipe.Admin.specification.BlackListSpecification;
import com.example.Swipe.Admin.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Setter
public class UserServiceImpl implements UserService {
    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepo userRepo;
    @Value("${upload.path}")
    private String upload;



    public List<User> findAllByType(TypeUser typeUser){
        return userRepo.findAllByTypeUserAndBlackListIsFalse(typeUser);
    }
    public List<ClientDTO> findAllByTypeDTO(TypeUser typeUser){
        return userRepo.findAllByTypeUserAndBlackListIsFalse(typeUser).stream().map(ClientMapper::apply).collect(Collectors.toList());
    }

    public Page<ClientDTO> findAllByTypePagination(TypeUser typeUser, Pageable pageable,String keyWord,String sort){
        if (!keyWord.equals("null")) {
            UserSpecification blackListSpecification = UserSpecification.builder().keyWord(keyWord).typeUser(typeUser).sort(sort).build();
            return userRepo.findAll(blackListSpecification, pageable).map(ClientMapper::apply);
        }
        UserSpecification blackListSpecification = UserSpecification.builder().typeUser(typeUser).sort(sort).build();
        return userRepo.findAll(blackListSpecification,pageable).map(ClientMapper::apply);
    }
    public Page<ClientDTO> findAllByTypePagination(TypeUser typeUser, Pageable pageable,String keyWord,String sort,int order){
        if (!keyWord.equals("null")) {
            UserSpecification blackListSpecification = UserSpecification.builder().keyWord(keyWord).typeUser(typeUser).sort(sort).order(order).build();
            return userRepo.findAll(blackListSpecification, pageable).map(ClientMapper::apply);
        }
        UserSpecification blackListSpecification = UserSpecification.builder().typeUser(typeUser).sort(sort).order(order).build();
        return userRepo.findAll(blackListSpecification,pageable).map(ClientMapper::apply);
    }
    public Page<BlackListDTO> blackList(Pageable pageable, String keyWord,String sortedBy){
        if (!keyWord.equals("null")) {
            BlackListSpecification blackListSpecification = BlackListSpecification.builder().keyWord(keyWord).sortedBy(sortedBy).build();
            return userRepo.findAll(blackListSpecification, pageable).map(BlackLIstMapper::apply);
        }
        else {
            BlackListSpecification blackListSpecification = BlackListSpecification.builder().keyWord(keyWord).sortedBy(sortedBy).build();
            return userRepo.findAll(blackListSpecification, pageable).map(BlackLIstMapper::apply);

        }
    }
    public Page<BlackListDTO> blackList(Pageable pageable, String keyWord,String sortedBy,int order){
        if (!keyWord.equals("null")) {
            BlackListSpecification blackListSpecification = BlackListSpecification.builder().keyWord(keyWord).sortedBy(sortedBy).order(order).build();
            return userRepo.findAll(blackListSpecification, pageable).map(BlackLIstMapper::apply);
        }
        else {
            BlackListSpecification blackListSpecification = BlackListSpecification.builder().keyWord(keyWord).sortedBy(sortedBy).order(order).build();
            return userRepo.findAll(blackListSpecification, pageable).map(BlackLIstMapper::apply);

        }
    }

    public int countBlackList(){
        return userRepo.countByBlackListTrue();
    }

    public int countByTypeUser(TypeUser typeUser){
        return userRepo.countByTypeUserAndBlackListFalse(typeUser);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
    public ClientDTO findByIdDTO(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return ClientMapper.apply(user.get());
        }
        else {
            return null;
        }
    }
    @Override
    public User findById(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        else {
            return null;
        }
    }
    public void saveEntityDTO(ClientDTO clientDTO) {
        User user = ClientMapper.toEntity(clientDTO);
        if (clientDTO.getFilename()!=null) {
            if (!clientDTO.getFilename().isEmpty()) {
                File uploadDirGallery = new File(upload);
                if (!uploadDirGallery.exists()) {
                    uploadDirGallery.mkdir();
                }
                String uuid = UUID.randomUUID().toString();
                String fileNameGallery = uuid + "-" + clientDTO.getFilename().getOriginalFilename();
                String resultNameGallery = upload + "" + fileNameGallery;
                try {
                    clientDTO.getFilename().transferTo(new File((resultNameGallery)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                user.setFilename("../uploads/" + fileNameGallery);
            }
            else if (clientDTO.getFilename().isEmpty() && clientDTO.getIdUser() == 0) {
                System.out.println("+");
                user.setFilename("../admin/dist/img/default.jpg");
            }
        }

        userRepo.save(user);

    }
    @Override
    public void saveEntity(User user) {
        userRepo.save(user);
    }

    public BindingResult uniqueMail(String email,BindingResult result,int id,String method){
        List<User> users = userRepo.findAllByMail(email);
       if (users.size()>=2){
            result.addError(new FieldError("user", "mail", "Email already exists"));
            return result;
        }
        else if (method.equals("update")&&users.size()==1){
            if (users.get(0).getIdUser()==id){
                return result;
            }
            result.addError(new FieldError("user", "mail", "Email already exists"));
            return result;
        }
        else if (method.equals("add")&&users.size()!=0){
            result.addError(new FieldError("user", "mail", "Email already exists"));
            return result;
        }
        else return result;
    }
//    public BindingResult userByMail(String email, int id, BindingResult result){
//        List<User> user = userRepo.findAllByMail(email);
//        if (user.size()==0){
//            return result;
//        }
//        else {
//            if (result.hasErrors()&&result.getFieldErrorCount("mail")>0) {
////                if (user.size() == 1 && user.get(0).getIdUser() == id && id!=0) {
////                    for (int i = 0; i < result.getAllErrors().size(); i++) {
////                        if (result.getAllErrors().get(i).equals(result.getFieldError("mail"))) {
////                            result.getAllErrors().remove(result.getAllErrors().get(i));
////                        }
////                    }
////                    return result;
////                } else return result;
//                return result;
//            }
//            else {
//                if (user.size()>0&&id==0) {
//                    result.addError(new FieldError("client", "mail", "Email already exists"));
//                }
//                return result;
//            }
//        }
//    }
//    public BindingResult userByMailUpdate(String email, int id, BindingResult result){
//        List<User> user = userRepo.findAllByMail(email);
//        if (user.size()==0){
//            return result;
//        }
//        else {
//                if (user.size() == 1 && user.get(0).getIdUser() == id) {
//                    for (int i = 0; i < result.getAllErrors().size(); i++) {
//                        if (result.getAllErrors().get(i).equals(result.getFieldError("mail"))) {
//                            result.getAllErrors().remove(result.getAllErrors().get(i));
//                        }
//                    }
//                    return result;
//                } else {
//                    result.addError(new FieldError("user", "mail", "Email already exists"));
//                    return result;
//                }
//        }
//    }


    @Override
    public void deleteById(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.get().getFilename()!=null) {
            if (!user.get().getFilename().equals("../admin/dist/img/default.jpg")) {
                String fileNameDelete = user.get().getFilename().substring(11, user.get().getFilename().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
        }
        userRepo.deleteById(id);
    }

    @Override
    public void updateEntity(User user, int id) {
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()){
            User updateUser = userOptional.get();
            if(user.getFilename()!=null){
                updateUser.setFilename(user.getFilename());
            }
            if(user.getName()!=null){
                updateUser.setName(user.getName());
            }
            if(user.getSurname()!=null){
                updateUser.setSurname(user.getSurname());
            }
            if(user.getMail()!=null){
                updateUser.setMail(user.getMail());
            }
            if (user.getNumber() != null) {
                updateUser.setNumber(user.getNumber());
            }
            updateUser.setBlackList(user.isBlackList());


            userRepo.saveAndFlush(updateUser);
        }
    }

    public void updateDTO(ClientDTO clientDTO, int id) {
        User user = ClientMapper.toEntity(clientDTO);
        if (clientDTO.getFilename()!=null) {
            if (!clientDTO.getFilename().isEmpty()) {
                File uploadDirGallery = new File(upload);
                if (!uploadDirGallery.exists()) {
                    uploadDirGallery.mkdir();
                }
                String uuid = UUID.randomUUID().toString();
                String fileNameGallery = uuid + "-" + clientDTO.getFilename().getOriginalFilename();
                String resultNameGallery = upload + "" + fileNameGallery;
                try {
                    clientDTO.getFilename().transferTo(new File((resultNameGallery)));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                user.setFilename("../uploads/" + fileNameGallery);
            }
        }
        Optional<User> userOptional = userRepo.findById(id);
        if (userOptional.isPresent()){
            User updateUser = userOptional.get();
            if(user.getFilename()!=null){
                if (updateUser.getFilename()!=null) {
                    if (!updateUser.getFilename().equals("../admin/dist/img/default.jpg")) {
                        String fileNameDelete = updateUser.getFilename().substring(11, updateUser.getFilename().length());
                        File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                        fileDelete.delete();
                    }
                }
                updateUser.setFilename(user.getFilename());
            }
            if(user.getName()!=null){
                updateUser.setName(user.getName());
            }
            if(user.getSurname()!=null){
                updateUser.setSurname(user.getSurname());
            }
            if(user.getMail()!=null){
                updateUser.setMail(user.getMail());
            }
            if (user.getNumber() != null) {
                updateUser.setNumber(user.getNumber());
            }
            if (user.getUserAddInfo()!=null) {
                if (user.getUserAddInfo().getTypeNotification() != null) {
                    updateUser.getUserAddInfo().setTypeNotification(user.getUserAddInfo().getTypeNotification());
                }
                if (user.getUserAddInfo().getDateSub() != null) {
                    updateUser.getUserAddInfo().setDateSub(user.getUserAddInfo().getDateSub());
                }
                updateUser.getUserAddInfo().setCallSms(user.getUserAddInfo().isCallSms());
            }

            updateUser.setBlackList(user.isBlackList());


            userRepo.saveAndFlush(updateUser);
        }
    }

    public AdminDto getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return AdminMapper.apply(userRepo.findByMail(authentication.getName()).get());
    }

    public void updateAdmin(AdminDto adminDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = AdminMapper.toEntity(adminDto);
        Optional<User> userOptional = userRepo.findById(adminDto.getIdUser());
        if (userOptional.isPresent()){
            User userUpdate = userOptional.get();
            if (user.getMail()!=null){
                userUpdate.setMail(user.getMail());
            }
            System.out.println(adminDto.getPassword().isEmpty());
            if (adminDto.getPassword()!=null&& !adminDto.getPassword().isEmpty()){
                userUpdate.setPassword(passwordEncoder.encode(adminDto.getPassword()));
            }
            System.out.println(userUpdate.getPassword());
            userRepo.saveAndFlush(userUpdate);
        }
    }

}
