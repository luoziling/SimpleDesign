package com.wzb.dbserviceimpl;

import com.wzb.dbservice.ProjectInformationDBService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DbserviceimplApplication.class})
public class DbserviceimplApplicationTests {

    @Test
    public void test(){
        System.out.println(111);
    }


    @Resource
    private ProjectInformationDBService projectInformationDBService;

    @Test
    public void contextLoads() {
        System.out.println(projectInformationDBService.selAll());
    }
//
//    @Test
//    public void testTransaction(){
//        projectInformationDBService.insTest();
//    }

}
