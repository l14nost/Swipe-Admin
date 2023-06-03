package com.example.Swipe.Admin.service.impl;

//import com.example.Swipe.Admin.entity.Contractor;
import com.example.Swipe.Admin.dto.BlackListDTO;
import com.example.Swipe.Admin.dto.ClientDTO;
import com.example.Swipe.Admin.entity.User;
import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.mapper.BlackLIstMapper;
import com.example.Swipe.Admin.mapper.ClientMapper;
import com.example.Swipe.Admin.repository.UserRepo;
import com.example.Swipe.Admin.service.UserService;
import com.example.Swipe.Admin.specification.BlackListSpecification;
import com.example.Swipe.Admin.specification.UserSpecification;
import com.example.Swipe.Admin.validation.UniqueEmail;
import com.example.Swipe.Admin.validation.UniqueEmailValidator;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Setter
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Value("${upload.path}")
    private String upload;


    public List<User> findAllByType(TypeUser typeUser){
        return userRepo.findAllByTypeUserAndBlackListIsFalse(typeUser);
    }
    public List<ClientDTO> findAllByTypeDTO(TypeUser typeUser){
        return userRepo.findAllByTypeUserAndBlackListIsFalse(typeUser).stream().map(ClientMapper::apply).toList();
    }

    public Page<ClientDTO> findAllByTypePagination(TypeUser typeUser, Pageable pageable,String keyWord){
        if (!keyWord.equals("null")) {
            UserSpecification blackListSpecification = UserSpecification.builder().keyWord(keyWord).typeUser(typeUser).build();
            return userRepo.findAll(blackListSpecification, pageable).map(ClientMapper::apply);
        }
        return userRepo.findAllByTypeUserAndBlackListIsFalse(typeUser,pageable).map(ClientMapper::apply);
    }
    public Page<BlackListDTO> blackList(Pageable pageable, String keyWord){
        if (!keyWord.equals("null")) {
            BlackListSpecification blackListSpecification = BlackListSpecification.builder().keyWord(keyWord).build();
            return userRepo.findAll(blackListSpecification, pageable).map(BlackLIstMapper::apply);
        }
        else return userRepo.findAllByBlackListIsTrue(pageable).map(BlackLIstMapper::apply);
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
    public BindingResult userByMail(String email, int id, BindingResult result){
        List<User> user = userRepo.findAllByMail(email);
        if (user.size()==0){
            return result;
        }
        else {
            if (result.hasErrors()&&result.getFieldErrorCount("mail")>0) {
//                if (user.size() == 1 && user.get(0).getIdUser() == id && id!=0) {
//                    for (int i = 0; i < result.getAllErrors().size(); i++) {
//                        if (result.getAllErrors().get(i).equals(result.getFieldError("mail"))) {
//                            result.getAllErrors().remove(result.getAllErrors().get(i));
//                        }
//                    }
//                    return result;
//                } else return result;
                return result;
            }
            else {
                if (user.size()>0&&id==0) {
                    result.addError(new FieldError("client", "mail", "Email already exists"));
                }
                return result;
            }
        }
    }
    public BindingResult userByMailUpdate(String email, int id, BindingResult result){
        List<User> user = userRepo.findAllByMail(email);
        if (user.size()==0){
            return result;
        }
        else {
                if (user.size() == 1 && user.get(0).getIdUser() == id) {
                    for (int i = 0; i < result.getAllErrors().size(); i++) {
                        if (result.getAllErrors().get(i).equals(result.getFieldError("mail"))) {
                            result.getAllErrors().remove(result.getAllErrors().get(i));
                        }
                    }
                    return result;
                } else {
                    result.addError(new FieldError("user", "mail", "Email already exists"));
                    return result;
                }
        }
    }


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
            updateUser.setBlackList(user.isBlackList());


            userRepo.saveAndFlush(updateUser);
        }
    }
}
