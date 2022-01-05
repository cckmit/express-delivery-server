package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import com.sachindrarodrigo.express_delivery_server.domain.Mail;
import com.sachindrarodrigo.express_delivery_server.dto.*;
import com.sachindrarodrigo.express_delivery_server.exception.APIException;
import com.sachindrarodrigo.express_delivery_server.exception.ExpressDeliveryException;
import com.sachindrarodrigo.express_delivery_server.service.MailService;
import com.sachindrarodrigo.express_delivery_server.service.MailTrackingService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class ParcelWebController {

    private final MailService mailService;
    private final MailTrackingService mailTrackingService;

    @GetMapping("/send-package")
    public ModelAndView register() {
        ModelAndView mv = new ModelAndView();
        Mail mail = new Mail();
        mv.addObject("mail", mail);
        mv.setViewName("send-package.jsp");
        return mv;
    }

    @PostMapping("/send-package")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView sendPackage(@Valid @ModelAttribute("mail") Mail mail, BindingResult bindingResult, RedirectAttributes redirectAttributes,
                                    @RequestParam String pickupAddress, @RequestParam String receiverAddress, @RequestParam String receiverFirstName,
                                    @RequestParam String receiverLastName, @RequestParam String receiverPhoneNumber, @RequestParam String receiverEmail,
                                    @RequestParam String receiverCity, @RequestParam String parcelType, @RequestParam String weight, @RequestParam String pieces,
                                    @RequestParam String paymentMethod, @RequestParam String date, @RequestParam String time, @RequestParam String totalCost,
                                    @RequestParam String description) {

        ModelAndView mv = new ModelAndView();

        try{
            if(bindingResult.hasErrors()){
                mv.setViewName("send-package.jsp");
                System.out.println("errors" + bindingResult.hasErrors() + bindingResult.getAllErrors());
            }else{
                // Create mail dto with user input
                MailDto dto = new MailDto();
                dto.setPickupAddress(pickupAddress);
                dto.setReceiverAddress(receiverAddress);
                dto.setReceiverFirstName(receiverFirstName);
                dto.setReceiverLastName(receiverLastName);
                dto.setReceiverPhoneNumber(receiverPhoneNumber);
                dto.setReceiverEmail(receiverEmail);
                dto.setReceiverCity(receiverCity);
                dto.setParcelType(parcelType);
                dto.setWeight(weight);
                dto.setPieces(pieces);
                dto.setPaymentMethod(paymentMethod);
                dto.setDate(date);
                dto.setTime(time);
                dto.setTotalCost(totalCost);
                dto.setDescription(description);

                mailService.sendMail(dto);

                //Return user to "Home" page
                redirectAttributes.addFlashAttribute("success", new SimpleMessageDto("Order added successfully"));
                mv.setViewName("redirect:/home-customer");
            }
        }catch (ExpressDeliveryException e){
            //Return user to "Home" page
            redirectAttributes.addFlashAttribute("error", new APIException("User is Blacklisted"));
            mv.setViewName("redirect:/home-customer");
        }

        return mv;
    }

    @PostMapping("/initiate-return")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView initiateReturn(@RequestParam int mailId, @RequestParam int trackingId, @RequestParam String date, @RequestParam String time, @RequestParam String returnType, @RequestParam String reason, @RequestParam String description) throws ExpressDeliveryException{
        ModelAndView mv = HomePage();

        MailTrackingDto dto = new MailTrackingDto();
        mv.addObject("success", new SimpleMessageDto("Return initiated Successfully"));

        return mv;
    }

    @PostMapping("/cancel-parcel")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView cancelParcel(@RequestParam int mailId) throws ExpressDeliveryException {
        ModelAndView mv = HomePage();
        mailService.cancelParcel(mailId);
        mv.addObject("success", new SimpleMessageDto("Package cancelled Successfully"));
        return mv;
    }

    @GetMapping("/track-parcel")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ModelAndView trackParcel(@RequestParam int mailId, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView();
        try{
            mv.setViewName("track-parcel.jsp");
            mv.addObject("tracking", mailTrackingService.getTrackingInfo(mailId));
        }catch (ExpressDeliveryException e){
            redirectAttributes.addFlashAttribute("error", new APIException("No tracking info found"));
            mv.setViewName("redirect:/home-customer");
        }

        return mv;
    }

    private ModelAndView HomePage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("redirect:/home-customer");
        mv.addObject("success", new SimpleMessageDto("Package cancelled Successfully"));
        return mv;
    }

}
