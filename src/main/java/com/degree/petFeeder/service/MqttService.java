package com.degree.petFeeder.service;

import com.degree.petFeeder.configuration.MqttPublisher;
import com.degree.petFeeder.dto.ConnectionDTO;
import com.degree.petFeeder.error.ApiException;
import com.degree.petFeeder.utils.MotorUtils;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttService {

    @Autowired
    MqttPublisher mqttPublisher;

    public void turnMotor(Float seconds) {
        float turns = MotorUtils.calculateTurnsFromSeconds(seconds);
        if (turns < 0.001) {
            throw new ApiException(ApiException.INVALID_INPUT_FORMAT, "Vrtenje motorja mora biti večje ali enako 0.008s.");
        }
        try {
            mqttPublisher.turnMotor(turns);
        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
    }

    public ConnectionDTO checkConnection() {
        return new ConnectionDTO(mqttPublisher.isConnected());
    }

}
