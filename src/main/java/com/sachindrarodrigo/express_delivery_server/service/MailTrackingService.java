package com.sachindrarodrigo.express_delivery_server.service;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.domain.MailTracking;
import com.sachindrarodrigo.express_delivery_server.dto.MailTrackingDto;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.repository.MailRepository;
import com.sachindrarodrigo.express_delivery_server.repository.MailTrackingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class MailTrackingService {

    private final MailTrackingRepository mailTrackingRepository;
    private final MailRepository mailRepository;

    @Transactional
    public MailTracking getTrackingInfo(int mailId) throws ExpressDeliveryException {
        Mail mail = mailRepository.findById(mailId).orElseThrow(()-> new ExpressDeliveryException("Mail not found"));
        return mailTrackingRepository.findByMail(mail);
    }

    private MailTrackingDto mapTracking(MailTracking mailTracking){
        return new MailTrackingDto(mailTracking.getMail(),mailTracking.getTrackingId(), mailTracking.getDriver(), mailTracking.getStatus1(), mailTracking.getStatus1Date(),
                mailTracking.getStatus2(), mailTracking.getStatus2Date(), mailTracking.getStatus3(), mailTracking.getStatus3Date(), mailTracking.getStatus4(), mailTracking.getStatus4Date(),
                mailTracking.getStatus5(), mailTracking.getStatus5Date(), mailTracking.getStatus6(), mailTracking.getStatus6Date(), mailTracking.getStatus7(), mailTracking.getStatus7Date(),
                mailTracking.getStatus8(), mailTracking.getStatus8Date(), mailTracking.getStatus9(), mailTracking.getStatus9Date(), mailTracking.getDeliveryPartner());
    }

}
