package com.exp.EmployeeService.controller;

import com.exp.EmployeeService.entity.EmployeeInfo;
import com.exp.EmployeeService.service.EmployeeInfoService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@WebFluxTest(controllers = EmployeeInfoController.class)
@AutoConfigureWebTestClient
@Slf4j
public class EmployeeInfoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockitoBean
    private EmployeeInfoService employeeInfoService;

//    @Test
//    void fetchAllEmployeesTest(){
//       var employeeInfoList =
//               List.of(new EmployeeInfo("1","Biswo",25));
//       Mockito.when(employeeInfoService.fetchAllEmployeeInfo()).
//               thenReturn(Flux.fromIterable(employeeInfoList));
//        webTestClient.get()
//                     .uri("/v1/fetchAllEmployees")
//                     .exchange()
//                     .expectStatus()
//                     .isFound()
//                     .expectBodyList(EmployeeInfo.class)
//                     .hasSize(1);
//
//    }

    @Test
    void addEmployeeFailTest() {
        var employeeInfo = new EmployeeInfo("1", "Biswo", 10); 
        Mockito.when(employeeInfoService.addEmployeeInfo(Mockito.any(EmployeeInfo.class)))
                .thenReturn(Mono.just(employeeInfo));

        webTestClient.post()
                .uri("/v1/saveEmployeeInfo")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(employeeInfo)
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(String.class);
    }
}
