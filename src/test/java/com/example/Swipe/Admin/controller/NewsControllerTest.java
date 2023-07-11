package com.example.Swipe.Admin.controller;

import com.example.Swipe.Admin.dto.BlackListDTO;
import com.example.Swipe.Admin.dto.NewsDTO;
import com.example.Swipe.Admin.entity.LCD;
import com.example.Swipe.Admin.entity.News;
import com.example.Swipe.Admin.service.impl.LCDServiceImpl;
import com.example.Swipe.Admin.service.impl.NewsServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(NewsController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsServiceImpl newsService;
    @MockBean
    private LCDServiceImpl lcdService;

    @MockBean
    private SecurityContext securityContext;


    @Test
    void updateNews() throws Exception {
        NewsDTO newsDTO = NewsDTO.builder()
                .idNews(1)
                .idLcd(1)
                .description("11111")
                .date(LocalDate.now())
                .title("111111")
                .build();


        when(newsService.findById(1)).thenReturn(News.builder().lcd(LCD.builder().idLcd(1).build()).build());

        BindingResult result = new BeanPropertyBindingResult(newsDTO,"news");

        mockMvc.perform(post("/edit_news/1")
                .flashAttr("news",newsDTO)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .flashAttr("result", result))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/news_page"));

    }
    @Test
    void updateNewsValid() throws Exception {
        NewsDTO newsDTO = NewsDTO.builder()
                .idNews(1)
                .idLcd(1)
                .description("1")
                .date(LocalDate.of(2025,12,1))
                .title("111111")
                .build();


        when(newsService.findById(1)).thenReturn(News.builder().lcd(LCD.builder().idLcd(1).build()).build());

        BindingResult result = new BeanPropertyBindingResult(newsDTO,"news");

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/edit_news/1")
                        .flashAttr("news",newsDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("result", result))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/news_edit"));

    }

    @Test
    void saveNews() throws Exception {
        NewsDTO newsDTO = NewsDTO.builder()
                .idNews(1)
                .idLcd(1)
                .description("11111")
                .date(LocalDate.now())
                .title("111111")
                .build();

        BindingResult result = new BeanPropertyBindingResult(newsDTO,"news");

        mockMvc.perform(post("/add_news/1")
                        .flashAttr("news",newsDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("result", result))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/lcd_edit/1"));

    }

    @Test
    void saveNews1() throws Exception {
        NewsDTO newsDTO = NewsDTO.builder()
                .idNews(1)
                .idLcd(1)
                .description("11111")
                .date(LocalDate.now())
                .title("111111")
                .build();

        BindingResult result = new BeanPropertyBindingResult(newsDTO,"news");

        mockMvc.perform(post("/add_news")
                        .flashAttr("news",newsDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("result", result))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/news_page"));

    }
    @Test
    void saveNewsValid() throws Exception {
        NewsDTO newsDTO = NewsDTO.builder()
                .idNews(1)
                .idLcd(1)
                .description("1")
                .date(LocalDate.of(2025,11,5))
                .title("111111")
                .build();

        BindingResult result = new BeanPropertyBindingResult(newsDTO,"news");

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/add_news/1")
                        .flashAttr("news",newsDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("result", result))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/news_add"));

    }

    @Test
    void saveNewsValid1() throws Exception {
        NewsDTO newsDTO = NewsDTO.builder()
                .idNews(1)
                .idLcd(1)
                .description("1")
                .date(LocalDate.of(2025,11,5))
                .title("111111")
                .build();

        BindingResult result = new BeanPropertyBindingResult(newsDTO,"news");

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(post("/add_news")
                        .flashAttr("news",newsDTO)
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .flashAttr("result", result))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/news_add1"));

    }

    @Test
    void deleteNews() throws Exception {
        mockMvc.perform(post("/delete_news")
                        .param("idNews","1")
                        .param("idLcd","1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/news_page"));
    }



    @Test
    void addNewsPage() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(get("/add_news/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/news_add"));

    }


    @Test
    void addNewsPage1() throws Exception {
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(get("/add_news"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/news_add1"));

    }

    @Test
    void newsMainPage() throws Exception {
        List<NewsDTO> clientDTOS = List.of(
                NewsDTO.builder().build(),
                NewsDTO.builder().build(),
                NewsDTO.builder().build(),
                NewsDTO.builder().build()
        );
        Pageable pageable = PageRequest.of(0,10);
        when(newsService.pagination(pageable,"null","idNews",1)).thenReturn(new PageImpl<NewsDTO>(clientDTOS));

        when(newsService.count()).thenReturn(10);

        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        mockMvc.perform(get("/news_page"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/news_main"));
    }



    @Test
    void editNewsPage() throws Exception {
        NewsDTO newsDTO = NewsDTO.builder()
                .idNews(1)
                .idLcd(1)
                .description("1")
                .date(LocalDate.of(2025,11,5))
                .title("111111")
                .build();
        when(newsService.findByIdDTO(1)).thenReturn(newsDTO);
        Authentication authentication = mock(Authentication.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        when(authentication.getName()).thenReturn("mail@gmail.com");

        mockMvc.perform(get("/edit_news/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("admin/news_edit"));

    }
}