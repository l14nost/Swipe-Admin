package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.enums.TypeUser;
import com.example.Swipe.Admin.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainUserPageController {
    private Logger log = LoggerFactory.getLogger(MainUserPageController.class);
    private final UserServiceImpl userServiceImpl;


    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String usersMain(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                            @RequestParam(name = "sizeUserPage", defaultValue = "10", required = false) int sizePage,
                            @RequestParam(name = "pageContractor", defaultValue = "0", required = false) int pageContractor,
                            @RequestParam(name = "sizeContractorPage", defaultValue = "10", required = false) int sizePageContractor,
                            @RequestParam(name = "pageNotary", defaultValue = "0", required = false) int pageNotary,
                            @RequestParam(name = "sizeNotaryPage", defaultValue = "10", required = false) int sizePageNotary,
                            @RequestParam(name = "searchClient", required = false, defaultValue = "null") String keyWordClient,
                            @RequestParam(name = "searchContractor", required = false, defaultValue = "null") String keyWordContractor,
                            @RequestParam(name = "searchNotary", required = false, defaultValue = "null") String keyWordNotary,
                            @RequestParam(name = "sortedClient", required = false,defaultValue = "idUser") String sortedClient,
                            @RequestParam(name = "sortedContractor", required = false,defaultValue = "idUser") String sortedContractor,
                            @RequestParam(name = "sortedNotary", required = false,defaultValue = "idUser") String sortedNotary,
                            @RequestParam(name = "orderClient", required = false,defaultValue = "1") int orderClient,
                            @RequestParam(name = "orderContractor", required = false,defaultValue = "1") int orderContractor,
                            @RequestParam(name = "orderNotary", required = false,defaultValue = "1") int orderNotary,
                            Model model) {
        Pageable pageClient = PageRequest.of(page,sizePage);
        Pageable pageable = PageRequest.of(pageContractor,sizePageContractor);
        Pageable pageableNotary = PageRequest.of(pageNotary,sizePageNotary);
        model.addAttribute("users", userServiceImpl.findAllByTypePagination(TypeUser.CLIENT, pageClient,keyWordClient,sortedClient,orderClient).getContent());
        model.addAttribute("pageUser", userServiceImpl.findAllByTypePagination(TypeUser.CLIENT, pageClient,keyWordClient,sortedClient,orderClient));
        model.addAttribute("contractors", userServiceImpl.findAllByTypePagination(TypeUser.CONTRACTOR, pageable,keyWordContractor,sortedContractor,orderContractor).getContent());
        model.addAttribute("pageContractor", userServiceImpl.findAllByTypePagination(TypeUser.CONTRACTOR, pageable,keyWordContractor,sortedContractor,orderContractor));
        model.addAttribute("notaries", userServiceImpl.findAllByTypePagination(TypeUser.NOTARY,pageableNotary,keyWordNotary,sortedNotary,orderNotary).getContent());
        model.addAttribute("pageNotary", userServiceImpl.findAllByTypePagination(TypeUser.NOTARY,pageableNotary,keyWordNotary,sortedNotary,orderContractor));


        model.addAttribute("typeClient", TypeUser.CLIENT );
        model.addAttribute("typeContractor", TypeUser.CONTRACTOR );
        model.addAttribute("typeNotary", TypeUser.NOTARY );

        model.addAttribute("searchClient", keyWordClient);
        model.addAttribute("searchContractor", keyWordContractor);
        model.addAttribute("searchNotary", keyWordNotary);

        model.addAttribute("sizeClient",userServiceImpl.countByTypeUser(TypeUser.CLIENT));
        model.addAttribute("sizeContractor",userServiceImpl.countByTypeUser(TypeUser.CONTRACTOR));
        model.addAttribute("sizeNotary",userServiceImpl.countByTypeUser(TypeUser.NOTARY));

        model.addAttribute("sort",sortedClient);
        model.addAttribute("order",orderClient);


        log.info("Current page client table:"+page+", size:"+sizePage);
        log.info("Current page contractor table:"+pageContractor+", size:"+sizePageContractor);
        log.info("Current page notary table:"+pageNotary+", size:"+sizePageNotary);
        return "admin/user_main";
    }


    @GetMapping("/contractors")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String contractorMain(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                            @RequestParam(name = "sizeUserPage", defaultValue = "10", required = false) int sizePage,
                            @RequestParam(name = "pageContractor", defaultValue = "0", required = false) int pageContractor,
                            @RequestParam(name = "sizeContractorPage", defaultValue = "10", required = false) int sizePageContractor,
                            @RequestParam(name = "pageNotary", defaultValue = "0", required = false) int pageNotary,
                            @RequestParam(name = "sizeNotaryPage", defaultValue = "10", required = false) int sizePageNotary,
                            @RequestParam(name = "searchClient", required = false, defaultValue = "null") String keyWordClient,
                            @RequestParam(name = "searchContractor", required = false, defaultValue = "null") String keyWordContractor,
                            @RequestParam(name = "searchNotary", required = false, defaultValue = "null") String keyWordNotary,
                            @RequestParam(name = "sortedClient", required = false,defaultValue = "idUser") String sortedClient,
                            @RequestParam(name = "sortedContractor", required = false,defaultValue = "idUser") String sortedContractor,
                            @RequestParam(name = "sortedNotary", required = false,defaultValue = "idUser") String sortedNotary,
                            @RequestParam(name = "orderClient", required = false,defaultValue = "1") int orderClient,
                            @RequestParam(name = "orderContractor", required = false,defaultValue = "1") int orderContractor,
                            @RequestParam(name = "orderNotary", required = false,defaultValue = "1") int orderNotary,
                            Model model) {
        Pageable pageClient = PageRequest.of(page,sizePage);
        Pageable pageable = PageRequest.of(pageContractor,sizePageContractor);
        Pageable pageableNotary = PageRequest.of(pageNotary,sizePageNotary);
        model.addAttribute("users", userServiceImpl.findAllByTypePagination(TypeUser.CLIENT, pageClient,keyWordClient,sortedClient,orderClient).getContent());
        model.addAttribute("pageUser", userServiceImpl.findAllByTypePagination(TypeUser.CLIENT, pageClient,keyWordClient,sortedClient,orderClient));
        model.addAttribute("contractors", userServiceImpl.findAllByTypePagination(TypeUser.CONTRACTOR, pageable,keyWordContractor,sortedContractor,orderContractor).getContent());
        model.addAttribute("pageContractor", userServiceImpl.findAllByTypePagination(TypeUser.CONTRACTOR, pageable,keyWordContractor,sortedContractor,orderContractor));
        model.addAttribute("notaries", userServiceImpl.findAllByTypePagination(TypeUser.NOTARY,pageableNotary,keyWordNotary,sortedNotary,orderNotary).getContent());
        model.addAttribute("pageNotary", userServiceImpl.findAllByTypePagination(TypeUser.NOTARY,pageableNotary,keyWordNotary,sortedNotary,orderContractor));


        model.addAttribute("typeClient", TypeUser.CLIENT );
        model.addAttribute("typeContractor", TypeUser.CONTRACTOR );
        model.addAttribute("typeNotary", TypeUser.NOTARY );

        model.addAttribute("searchClient", keyWordClient);
        model.addAttribute("searchContractor", keyWordContractor);
        model.addAttribute("searchNotary", keyWordNotary);

        model.addAttribute("sizeClient",userServiceImpl.countByTypeUser(TypeUser.CLIENT));
        model.addAttribute("sizeContractor",userServiceImpl.countByTypeUser(TypeUser.CONTRACTOR));
        model.addAttribute("sizeNotary",userServiceImpl.countByTypeUser(TypeUser.NOTARY));

        model.addAttribute("sort",sortedContractor);
        model.addAttribute("order",orderContractor);


        log.info("Current page client table:"+page+", size:"+sizePage);
        log.info("Current page contractor table:"+pageContractor+", size:"+sizePageContractor);
        log.info("Current page notary table:"+pageNotary+", size:"+sizePageNotary);
        return "admin/contractor_main";
    }



    @GetMapping("/notaries")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String notaryMain(@RequestParam(name = "page", defaultValue = "0", required = false) int page,
                            @RequestParam(name = "sizeUserPage", defaultValue = "10", required = false) int sizePage,
                            @RequestParam(name = "pageContractor", defaultValue = "0", required = false) int pageContractor,
                            @RequestParam(name = "sizeContractorPage", defaultValue = "10", required = false) int sizePageContractor,
                            @RequestParam(name = "pageNotary", defaultValue = "0", required = false) int pageNotary,
                            @RequestParam(name = "sizeNotaryPage", defaultValue = "10", required = false) int sizePageNotary,
                            @RequestParam(name = "searchClient", required = false, defaultValue = "null") String keyWordClient,
                            @RequestParam(name = "searchContractor", required = false, defaultValue = "null") String keyWordContractor,
                            @RequestParam(name = "searchNotary", required = false, defaultValue = "null") String keyWordNotary,
                            @RequestParam(name = "sortedClient", required = false,defaultValue = "idUser") String sortedClient,
                            @RequestParam(name = "sortedContractor", required = false,defaultValue = "idUser") String sortedContractor,
                            @RequestParam(name = "sortedNotary", required = false,defaultValue = "idUser") String sortedNotary,
                            @RequestParam(name = "orderClient", required = false,defaultValue = "1") int orderClient,
                            @RequestParam(name = "orderContractor", required = false,defaultValue = "1") int orderContractor,
                            @RequestParam(name = "orderNotary", required = false,defaultValue = "1") int orderNotary,
                            Model model) {
        Pageable pageClient = PageRequest.of(page,sizePage);
        Pageable pageable = PageRequest.of(pageContractor,sizePageContractor);
        Pageable pageableNotary = PageRequest.of(pageNotary,sizePageNotary);
        model.addAttribute("users", userServiceImpl.findAllByTypePagination(TypeUser.CLIENT, pageClient,keyWordClient,sortedClient,orderClient).getContent());
        model.addAttribute("pageUser", userServiceImpl.findAllByTypePagination(TypeUser.CLIENT, pageClient,keyWordClient,sortedClient,orderClient));
        model.addAttribute("contractors", userServiceImpl.findAllByTypePagination(TypeUser.CONTRACTOR, pageable,keyWordContractor,sortedContractor,orderContractor).getContent());
        model.addAttribute("pageContractor", userServiceImpl.findAllByTypePagination(TypeUser.CONTRACTOR, pageable,keyWordContractor,sortedContractor,orderContractor));
        model.addAttribute("notaries", userServiceImpl.findAllByTypePagination(TypeUser.NOTARY,pageableNotary,keyWordNotary,sortedNotary,orderNotary).getContent());
        model.addAttribute("pageNotary", userServiceImpl.findAllByTypePagination(TypeUser.NOTARY,pageableNotary,keyWordNotary,sortedNotary,orderContractor));


        model.addAttribute("typeClient", TypeUser.CLIENT );
        model.addAttribute("typeContractor", TypeUser.CONTRACTOR );
        model.addAttribute("typeNotary", TypeUser.NOTARY );

        model.addAttribute("searchClient", keyWordClient);
        model.addAttribute("searchContractor", keyWordContractor);
        model.addAttribute("searchNotary", keyWordNotary);

        model.addAttribute("sizeClient",userServiceImpl.countByTypeUser(TypeUser.CLIENT));
        model.addAttribute("sizeContractor",userServiceImpl.countByTypeUser(TypeUser.CONTRACTOR));
        model.addAttribute("sizeNotary",userServiceImpl.countByTypeUser(TypeUser.NOTARY));

        model.addAttribute("sort",sortedNotary);
        model.addAttribute("order",orderNotary);


        log.info("Current page client table:"+page+", size:"+sizePage);
        log.info("Current page contractor table:"+pageContractor+", size:"+sizePageContractor);
        log.info("Current page notary table:"+pageNotary+", size:"+sizePageNotary);
        return "admin/notary_main";
    }
}
