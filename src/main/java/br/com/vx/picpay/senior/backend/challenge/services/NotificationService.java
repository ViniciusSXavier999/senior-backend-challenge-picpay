package br.com.vx.picpay.senior.backend.challenge.services;

import br.com.vx.picpay.senior.backend.challenge.domain.user.User;
import br.com.vx.picpay.senior.backend.challenge.dtos.NotificationDTO;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {


    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);
/*
      ResponseEntity<String> notificationResponse =  restTemplate.postForEntity
                ("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationRequest,
                        String.class);


      if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
          System.out.println("Erro ao enviar notificação");
          throw new Exception("Serviço de notificação está fora do ar");

      }*/

        System.out.println("Notificação enviada para o usuario!");

    }
}
