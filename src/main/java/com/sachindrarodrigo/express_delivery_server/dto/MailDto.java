package com.sachindrarodrigo.express_delivery_server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MailDto {
    private int mailId;
    private String pickupAddress,
            receiverAddress,
            senderPhoneNumber,
            receiverPhoneNumber,
            senderEmail,
            receiverEmail,
            senderCity,
            receiverCity,
            parcelType,
            weight,
            pieces,
            paymentMethod,
            date,
            time,
            totalCost,
            status,
            description;

    public MailDto(int mailId, String pickupAddress, String receiverAddress, String senderPhoneNumber, String receiverPhoneNumber, String senderEmail, String receiverEmail, String senderCity, String receiverCity, String parcelType, String weight, String pieces, String paymentMethod, String date, String time, String totalCost, String status, String description) {
        this.mailId = mailId;
        this.pickupAddress = pickupAddress;
        this.receiverAddress = receiverAddress;
        this.senderPhoneNumber = senderPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.senderEmail = senderEmail;
        this.receiverEmail = receiverEmail;
        this.senderCity = senderCity;
        this.receiverCity = receiverCity;
        this.parcelType = parcelType;
        this.weight = weight;
        this.pieces = pieces;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.time = time;
        this.totalCost = totalCost;
        this.status = status;
        this.description = description;
    }
}
