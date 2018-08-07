package com.marcin.anagramator.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.marcin.anagramator.business.domain.UserQuery;
import com.marcin.anagramator.business.service.AnagramQueryService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserQueryController.class)
public class UserQueryControllerTest {
///OUT!!!!!
    @MockBean
    private AnagramQueryService anagramQueryService;
    @MockBean
    private UserQuery mockUserQuery;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAnagrams() throws Exception {
    	
    	List<String> mockStringList = new ArrayList<>();
    	mockStringList.add("daraa");
    	mockStringList.add("adaa");
        UserQuery mockUserQuery = new UserQuery();
        mockUserQuery.setUserSequeceOfLetters("adaa");
                    
        given(anagramQueryService.findAnagramsForUserQuery(mockUserQuery)).willReturn(mockStringList);
        this.mockMvc.perform(get("/processForm"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("/WEB-INF/view/inputForm.jsp"));
  }
}
