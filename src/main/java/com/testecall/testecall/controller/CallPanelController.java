package com.testecall.testecall.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CallPanelController {

    @PostMapping("call-panel")
	public String callPanel(@RequestBody Map<String,String> allParams) {

        String id = allParams.get("client_id");
        String secret = allParams.get("client_secret");
        String urlcas = allParams.get("urlcas");
        String urlpanel = allParams.get("urlpanel");
        String accessToken = getAccessToken(urlcas, id, secret);

        CallPanel call = new CallPanel();
        call.setProfessional(allParams.get("professional"));
        call.setLocal(allParams.get("local"));
        call.setPersonal(allParams.get("personal"));
        call.setPassword(allParams.get("password"));
        call.setPriorityColor(allParams.get("priorityColor"));
        call.setDate(LocalDate.now().toString());
        call.setPanel(allParams.get("panel"));

        call(call, accessToken, urlpanel);
        return "ok";
	}

    private void call(CallPanel call, String accessToken, String urlpanel) {
        if (!"/".equals(urlpanel.substring(urlpanel.length() -1))) {
            urlpanel += "/";
        }
        String url = String.format("%scall/", urlpanel, accessToken);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();

        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer ".concat(accessToken));
        HttpEntity httpEntity = new HttpEntity<Object>(call, headers);

        String teste = restTemplate.postForObject(url, httpEntity, String.class);
        System.out.println(teste);

    }

    @PostMapping("get-panel")
	public List getPanel(@RequestBody Map<String,String> allParams) {

        String id = allParams.get("client_id");
        String secret = allParams.get("client_secret");
        String urlcas = allParams.get("urlcas");
        String urlpanel = allParams.get("urlpanel");

        String accessToken = getAccessToken(urlcas, id, secret);
        return getPanel(urlpanel, accessToken);
	}

    private List getPanel(String urlpanel, String accessToken) {
        if (!"/".equals(urlpanel.substring(urlpanel.length() -1))) {
            urlpanel += "/";
        }
        String url = String.format("%spanel/list?access_token=%s&limit=999", urlpanel, accessToken);
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject(url, List.class);

    }

    private String getAccessToken(String urlcas, String id, String secret) {
        if (!"/".equals(urlcas.substring(urlcas.length() -1))) {
            urlcas += "/";
        }
        String url = String.format("%saccessToken?grant_type=client_credentials&client_id=%s&client_secret=%s", urlcas, id, secret);
        RestTemplate restTemplate = new RestTemplate();
        ResponseAuth response = restTemplate.getForObject(url, ResponseAuth.class);

        return response.getAccess_token();
    }

}
