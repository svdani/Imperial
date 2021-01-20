package com.Imperial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.imperial.entity.Rebel;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class ProductServiceControllerTest extends AbstractTest {
    
    @Override
   @Before
   public void setUp() {
      super.setUp();
   }
   @Test
   public void getProductsList() throws Exception {
      String uri = "/api";
      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
      
      int status = mvcResult.getResponse().getStatus();
      assertEquals(200, status);
      String content = mvcResult.getResponse().getContentAsString();
      Rebel[] productlist = super.mapFromJson(content, Rebel[].class);
      assertTrue(productlist.length > 0);
   }

    @Test
    public void createProduct() throws Exception {
    String uri = "/api";
    Rebel rebel = new Rebel();
    rebel.setPlaneta("Marte");
    rebel.setNombre("Ginger");
    rebel.setFechayhora("19-01-2021 18:50");
    
    String inputJson = super.mapToJson(rebel);
    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
    
    int status = mvcResult.getResponse().getStatus();
    assertEquals(201, status);
    String content = mvcResult.getResponse().getContentAsString();
    assertEquals("Product is created successfully",content);
    }

}
