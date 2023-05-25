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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final ClientMapper clientMapper;
    private final BlackLIstMapper blackLIstMapper;
    @Value("${upload.path}")
    private String upload;


    public List<User> findAllByType(TypeUser typeUser){
        return userRepo.findAllByTypeUserAndBlackListIsFalse(typeUser);
    }
//    public Page<UserDTO> specificationForBlackList(String keyWord, Pageable pageable){
//        BlackListSpecification blackListSpecification = BlackListSpecification.builder().keyWord(keyWord).build();
//        return userRepo.findAll(blackListSpecification,pageable).map(userMapper);
//    }

    public Page<ClientDTO> findAllByTypePagination(TypeUser typeUser, Pageable pageable,String keyWord){
        if (!keyWord.equals("null")) {
            UserSpecification blackListSpecification = UserSpecification.builder().keyWord(keyWord).typeUser(typeUser).build();
            return userRepo.findAll(blackListSpecification, pageable).map(clientMapper);
        }
        return userRepo.findAllByTypeUserAndBlackListIsFalse(typeUser,pageable).map(clientMapper);
    }
    public Page<BlackListDTO> blackList(Pageable pageable, String keyWord){
        if (!keyWord.equals("null")) {
            BlackListSpecification blackListSpecification = BlackListSpecification.builder().keyWord(keyWord).build();
            return userRepo.findAll(blackListSpecification, pageable).map(blackLIstMapper);
        }
        else return userRepo.findAllByBlackListIsTrue(pageable).map(blackLIstMapper);
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
    public ClientDTO findByIdDTO(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return clientMapper.apply(user.get());
        }
        else {
            return ClientDTO.builder().build();
        }
    }
    @Override
    public User findById(int id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        else {
            return User.builder().build();
        }
    }
    public void saveEntityDTO(ClientDTO clientDTO) {
        User user = clientMapper.toEntity(clientDTO);
        userRepo.save(user);

    }
    @Override
    public void saveEntity(User user) {

        userRepo.save(user);

    }

    @Override
    public void deleteById(int id) {
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
            if(user.getAgent()!=null){
                updateUser.setAgent(user.getAgent());
            }
            updateUser.setBlackList(user.isBlackList());


            userRepo.saveAndFlush(updateUser);
        }
    }

    public void updateDTO(ClientDTO clientDTO, int id) {
        User user = clientMapper.toEntity(clientDTO);
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
            if(user.getAgent()!=null){
                updateUser.setAgent(user.getAgent());
            }
            updateUser.setBlackList(user.isBlackList());


            userRepo.saveAndFlush(updateUser);
        }
    }
}
