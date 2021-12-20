package com.sachindrarodrigo.express_delivery_server.controller.api_controller;

import com.sachindrarodrigo.express_delivery_server.dto.MailDto;
import com.sachindrarodrigo.express_delivery_server.dto.SimpleMessageDto;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.AgentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
@Slf4j
public class AgentController {

    private AgentService agentService;

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/get-new-packages")
    public ResponseEntity<Object> getUpcomingPackages(){
        try {
            List<MailDto> mailDto1 = agentService.getAllNewShipmentsAdmin();
            return new ResponseEntity<>(mailDto1, HttpStatus.OK);
        } catch (ExpressDeliveryException e){
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/accept-package-agent/{mailId}")
    public ResponseEntity<Object> acceptPackage(@PathVariable int mailId){
        try{
            agentService.acceptParcel(mailId);
            return new ResponseEntity<>(new SimpleMessageDto("Accepted Successfully", HttpStatus.OK), HttpStatus.OK);
        }catch (ExpressDeliveryException e){
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/reject-package-agent/{mailId}")
    public ResponseEntity<Object> rejectPackage(@PathVariable int mailId){
        try{
            agentService.rejectParcel(mailId);
            return new ResponseEntity<>(new SimpleMessageDto("Accepted Successfully", HttpStatus.OK), HttpStatus.OK);
        }catch (ExpressDeliveryException e){
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/get-accepted-packages-agent")
    public ResponseEntity<Object> getAcceptedPackages(){
        try {
            List<MailDto> mailDto1 = agentService.getAllNewAcceptedShipmentsAdmin();
            return new ResponseEntity<>(mailDto1, HttpStatus.OK);
        } catch (ExpressDeliveryException e){
            return new ResponseEntity<>(new APIException(e.getMessage(), HttpStatus.BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }
}
