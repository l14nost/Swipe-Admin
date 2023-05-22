package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainUserPageController {
    private final UserServiceImpl userServiceImpl;
    private TypeUser typeUser;


    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String usersMain(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                            @RequestParam(name = "sizeUserPage", defaultValue = "3", required = false) int sizePage,
                            @RequestParam(name = "pageContractor", defaultValue = "0", required = false) int pageContractor,
                            @RequestParam(name = "sizeContractorPage", defaultValue = "3", required = false) int sizePageContractor,
                            @RequestParam(name = "pageNotary", defaultValue = "0", required = false) int pageNotary,
                            @RequestParam(name = "sizeNotaryPage", defaultValue = "3", required = false) int sizePageNotary,
                            @RequestParam(name = "searchClient", required = false, defaultValue = "null") String keyWordClient,
                            @RequestParam(name = "searchContractor", required = false, defaultValue = "null") String keyWordContractor,
                            @RequestParam(name = "searchNotary", required = false, defaultValue = "null") String keyWordNotary,
                            Model model) {
        Pageable pageClient = PageRequest.of(page,sizePage);
        Pageable pageable = PageRequest.of(pageContractor,sizePageContractor);
        Pageable pageableNotary = PageRequest.of(pageNotary,sizePageNotary);
        model.addAttribute("users", userServiceImpl.findAllByTypePagination(TypeUser.CLIENT, pageClient,keyWordClient).getContent());
        model.addAttribute("pageUser", userServiceImpl.findAllByTypePagination(TypeUser.CLIENT, pageClient,keyWordClient));
        model.addAttribute("contractors", userServiceImpl.findAllByTypePagination(TypeUser.CONTRACTOR, pageable,keyWordContractor).getContent());
        model.addAttribute("pageContractor", userServiceImpl.findAllByTypePagination(TypeUser.CONTRACTOR, pageable,keyWordContractor));
        model.addAttribute("notaries", userServiceImpl.findAllByTypePagination(TypeUser.NOTARY,pageableNotary,keyWordNotary).getContent());
        model.addAttribute("pageNotary", userServiceImpl.findAllByTypePagination(TypeUser.NOTARY,pageableNotary,keyWordNotary));
//        model.addAttribute("url","/users");
        model.addAttribute("typeClient", TypeUser.CLIENT );
        model.addAttribute("typeContractor", TypeUser.CONTRACTOR );
        model.addAttribute("typeNotary", TypeUser.NOTARY );

        model.addAttribute("searchClient", keyWordClient);
        model.addAttribute("searchContractor", keyWordContractor);
        model.addAttribute("searchNotary", keyWordNotary);
        return "admin/user_main";
    }
}
