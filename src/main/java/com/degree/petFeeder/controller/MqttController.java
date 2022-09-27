package com.degree.petFeeder.controller;

import com.degree.petFeeder.dto.ConnectionDTO;
import com.degree.petFeeder.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mqtt")
public class MqttController {

    @Autowired
    MqttService mqttService;

    @GetMapping(value = "/connection-status", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConnectionDTO> checkConnection() {
        return new ResponseEntity<>(mqttService.checkConnection(), HttpStatus.OK);
    }

    @PostMapping(value = "/turn-motor/{seconds}")
    public ResponseEntity<Void> turnMotor(@PathVariable(name = "seconds") Float seconds) {
        mqttService.turnMotor(seconds);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
