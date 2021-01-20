package com.Imperial;

import static org.assertj.core.api.Assertions.assertThat;

import com.imperial.controller.ImperialController;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ImperialApplicationTests {

    @Autowired
    private ImperialController imperialController;

    @Test
    public void contextLoads() {
        assertThat(imperialController).isNotNull(); 
    }

    

}