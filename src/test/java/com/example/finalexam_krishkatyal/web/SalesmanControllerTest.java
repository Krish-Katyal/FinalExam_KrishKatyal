package com.example.finalexam_krishkatyal.web;

import com.example.finalexam_krishkatyal.entities.Salesman;
import com.example.finalexam_krishkatyal.repositories.SalesmanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
class SalesmanControllerTest {

    Salesman salesman;

    @Autowired
    private MockMvc mockMvc;

    @Mock
    SalesmanRepository salesmanRepository;

    @Mock
    View mockView;

    @InjectMocks
    SalesmanController salesmanController;


    @BeforeEach
    void setup() throws ParseException {
        salesman = new Salesman();
        salesman.setId(1L);
        salesman.setName("John");

        String sDate1 = "2012/11/11";
        Date date1 = new SimpleDateFormat("yyyy/MM/dd").parse(sDate1);
        salesman.setDot(date1);
        salesman.setItem("hello");
        salesman.setAmount(100);

        MockitoAnnotations.openMocks(this);

        mockMvc = standaloneSetup(salesmanController).setSingleView(mockView).build();
    }


    @Test
    public void findAll_ListView() throws Exception{
        List<Salesman> list = new ArrayList<Salesman>();
        list.add(salesman);
        list.add(salesman);

        when(salesmanRepository.findAll()).thenReturn(list);
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listSalesmen",list))
                .andExpect(view().name("salesman"))
                .andExpect(model().attribute("listSalesmen",hasSize(2)));

        verify(salesmanRepository,times(1)).findAll();
        verifyNoMoreInteractions(salesmanRepository);
    }

    @Test
    void delete(){
        ArgumentCaptor<Long> idCapture = ArgumentCaptor.forClass(Long.class);
        doNothing().when(salesmanRepository).deleteById(idCapture.capture());
        salesmanRepository.deleteById(1L);
        assertEquals(1L,idCapture.getValue());
        verify(salesmanRepository,times(1)).deleteById(1L);
     }
}