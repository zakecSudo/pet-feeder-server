package com.degree.petFeeder.configuration;

import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MqttPublisher implements MqttCallback {

    @Value("${mqtt.broker-address:mone.local}")
    private String brokerAddress = "tcp://mone.local:1883";
    @Value("${mqtt.client-id:pet-feeder_backend}")
    private String clientId = "pet-feeder-backend";
    private MqttClient mqttClient;
    private MqttConnectOptions connectionOptions;
    private static final Logger logger = LoggerFactory.getLogger(MqttPublisher.class);


    private MqttPublisher() throws MqttException {
        this.connectionOptions = new MqttConnectOptions();
        this.mqttClient = new MqttClient(brokerAddress, clientId);
        this.connectionOptions.setCleanSession(true);
        this.connectionOptions.setAutomaticReconnect(true);
        this.mqttClient.connect(this.connectionOptions);
        this.mqttClient.setCallback(this);
    }

    public void turnMotor(Float turns) throws MqttException {
        MqttMessage message = new MqttMessage(String.valueOf(turns).getBytes());
        // Nastavljanje Quality Of Service
        message.setQos(2);
        mqttClient.publish("motor/start", message);
    }

    public void disconnect() throws MqttException {
        mqttClient.disconnect();
    }

    public boolean isConnected() {
        return mqttClient.isConnected();
    }

    @Override
    public void connectionLost(Throwable throwable) {
        logger.info("Connection Lost");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        logger.info("Sent message to mqtt broker");
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) {
    }
}
