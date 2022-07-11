package com.dh.proyecto.Controller;

import com.dh.proyecto.Models.entities.Odontologo;
import com.dh.proyecto.Services.iServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OdontologoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    @Qualifier("odontologo_services")
    private iServices<Odontologo> services;

    @BeforeEach
    public void cargarDataSet() throws Exception {
        Odontologo odontologo = new Odontologo();
        services.guardar(odontologo);
    }

    @Test
    void registar() throws Exception {
        Odontologo odonto = new Odontologo();
        odonto.setNombre("Test");
        odonto.setApellido("registrar");

        ObjectWriter writer = new ObjectMapper()
                .configure(SerializationFeature.WRAP_ROOT_VALUE, false)
                .writer();

        String payloadJson = writer.writeValueAsString(odonto);

        MvcResult result =
                this.mockMvc.perform(MockMvcRequestBuilders.post("/v1/odontologo/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(payloadJson)
                        )
                        .andDo(print()).andExpect(status().isOk())
                        .andExpect(content().string("true"))
                        .andExpect(content().contentType("application/json"))
                        .andReturn();

        Assertions.assertEquals("true", result.getResponse().getContentAsString());
    }

    @Test
    void buscar() throws Exception {
        MvcResult mvcResult =
                this.mockMvc.perform(MockMvcRequestBuilders.get("/v1/odontologo/{id}", "1"))
                        .andDo(print()).andExpect(status().isOk())
                        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                        .andExpect(content().contentType("application/json"))
                        .andReturn();
        Assertions.assertEquals("application/json", mvcResult.getResponse().getContentType());
    }

    @Test
    void eliminar() throws Exception {
        MvcResult result =
                this.mockMvc.perform(MockMvcRequestBuilders.delete("/v1/odontologo/{id}", "1"))
                        .andDo(print()).andExpect(status().isNoContent())
                        .andExpect(content().string("true"))
                        .andReturn();
        Assertions.assertEquals("true", result.getResponse().getContentAsString());
    }

}