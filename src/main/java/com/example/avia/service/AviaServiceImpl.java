package com.example.avia.service;

import com.example.avia.dto.AviaDetailsDto;
import com.example.avia.dto.AviaPermissionDto;
import com.example.avia.dto.ResponseData;
import com.example.avia.entity.AviaDetails;
import com.example.avia.entity.User;
import com.example.avia.repository.AviaDetailsRepository;
import com.example.avia.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AviaServiceImpl implements AviaService{
    @Autowired
    private static AviaDetailsRepository aviaDetailsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender sender;

    private final static String ACCOUNT_SID = "ACf73ecdd409fb";
    private final static String AUTH_ID = "d099b8077232a1";

    static {
        Twilio.init(ACCOUNT_SID, AUTH_ID);
    }

    public AviaDetails addAvia(AviaDetailsDto flightDetailsDTO) {

        AviaDetails flightDetails = new AviaDetails();
        BeanUtils.copyProperties(flightDetailsDTO, flightDetails);
        flightDetails.setPermission("PERMISSION_REQUIRED");
        flightDetails = aviaDetailsRepository.save(flightDetails);
        sendMailAndSMS(flightDetails.getAviaId());
        return flightDetails;
    }

    @Override
    public ResponseData grantAviaPermissions(AviaPermissionDto permission) {
        ResponseData response = new ResponseData();
        response.setHttpStatus(HttpStatus.BAD_REQUEST);
        response.setMessage("Please use proper spelling for Approve and Reject");
        Optional<AviaDetails> aviaDetail = aviaDetailsRepository.findById(permission.getAviaId());
        if (aviaDetail.isPresent()) {
            AviaDetails aviaDetails = aviaDetail.get();
            if (aviaDetails.getPermission().equals("APPROVED")) {
                response.setMessage("Request is already approved");
                response.setHttpStatus(HttpStatus.BAD_REQUEST);
                response.setData(aviaDetails);
                return response;
            }
            if (permission.getPermission().equalsIgnoreCase("APPROVE")) {
                aviaDetails.setPermission("APPROVED");
                aviaDetailsRepository.save(aviaDetails);
                response.setMessage("Permission Aproved Successfully");
                response.setHttpStatus(HttpStatus.OK);
                response.setData(aviaDetails);

            } else if (permission.getPermission().equalsIgnoreCase("REJECT")) {
                aviaDetails.setPermission("REJECTED");
                aviaDetailsRepository.deleteById((long) permission.getAviaId());
                response.setMessage("Permission Rejected Successfully");
                response.setHttpStatus(HttpStatus.OK);
                response.setData(aviaDetails);
            }
        } else {
            response.setMessage("Flight Id is incorrect");
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
        }

        return response;
    }

    @Override
    public User getAllPassengerDetails(int userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    private boolean sendMailAndSMS(long flightId) {
        try {

            String msg = "Hi approve, Approval is pending for flight Id " + flightId;
            User user = userRepository.findById(101).get();

            MimeMessage message = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);

            helper.setTo(user.getEmailId());
            helper.setText(msg);
            helper.setSubject("Invitation for Flight Approval");
            sender.send(message);

            Message.creator(new PhoneNumber("+375" + user.getMobileNo()), new PhoneNumber("FROM Number"), msg).create();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }

        return true;
    }
}
