package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.entity.Contractor;
import com.example.Swipe.Admin.service.impl.ContractorServiceImpl;
import com.example.Swipe.Admin.service.impl.DocumentsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class ContractorController {
    @Value("${upload.path}")
    private String upload;
    private final ContractorServiceImpl contractorServiceImpl;
    private final DocumentsServiceImpl documentsService;

    public ContractorController(ContractorServiceImpl contractorServiceImpl, DocumentsServiceImpl documentsService) {
        this.contractorServiceImpl = contractorServiceImpl;
        this.documentsService = documentsService;
    }
    @GetMapping("/contractor_edit/{id}")
    public String contractorEdit(@PathVariable int id, Model model){
        Contractor contractor = contractorServiceImpl.findById(id);
        model.addAttribute("contractor",contractor);
        return "admin/contractor_edit";
    }
    @PostMapping("/contractor_update/{id}")
    public String contractorUpdate(@PathVariable int id, @RequestParam String name, @RequestParam String surname, @RequestParam String mail, @RequestParam(name = "file") MultipartFile file, @RequestPart(name = "document") List<MultipartFile> documents, Model model) throws IOException {
        Contractor contractor = Contractor.builder().name(name).surname(surname).mail(mail).build();
        Contractor preContractor = contractorServiceImpl.findById(id);
//        contractor.setDocuments(preContractor.getDocuments());
        if (!file.isEmpty()) {
            File uploadDirGallery = new File(upload);
            if (!uploadDirGallery.exists()) {
                uploadDirGallery.mkdir();
            }
            String uuid = UUID.randomUUID().toString();
            String fileNameGallery = uuid + "-" + file.getOriginalFilename();
            String resultNameGallery = upload + "" + fileNameGallery;
            file.transferTo(new File((resultNameGallery)));
            if (!preContractor.getFilename().equals("../admin/dist/img/default.jpg")) {
                String fileNameDelete = preContractor.getFilename().substring(11, preContractor.getFilename().length());
                File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
                fileDelete.delete();
            }
            contractor.setFilename("../uploads/" + fileNameGallery);
        }
//        for(int i = 0; i<preContractor.getDocuments().size();i++){
//            if (!documents.get(i).isEmpty()) {
//                File uploadDirGallery = new File(upload);
//                if (!uploadDirGallery.exists()) {
//                    uploadDirGallery.mkdir();
//                }
//                String uuid = UUID.randomUUID().toString();
//                String fileNameGallery = uuid + "-" + documents.get(i).getOriginalFilename();
//                contractor.getDocuments().get(i).setName(documents.get(i).getOriginalFilename());
//                String resultNameGallery = upload + "" + fileNameGallery;
//                documents.get(i).transferTo(new File((resultNameGallery)));
//                if (!preContractor.getDocuments().get(i).getFileName().equals("../admin/dist/img/default.jpg")) {
//                    String fileNameDelete = preContractor.getDocuments().get(i).getFileName().substring(11, preContractor.getDocuments().get(i).getFileName().length());
//                    File fileDelete = new File(upload.substring(1, upload.length()) + fileNameDelete);
//                    fileDelete.delete();
//                }
//                contractor.getDocuments().get(i).setFileName("../uploads/" + fileNameGallery);
//                documentsService.updateEntity(contractor.getDocuments().get(i),contractor.getDocuments().get(i).getIdDocuments() );
//            }
//        }
        contractorServiceImpl.updateEntity(contractor,id);
        return "redirect:/users";
    }
}
