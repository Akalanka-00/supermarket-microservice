package com.flashmart.delivery.event;

import com.flashmart.delivery.Consts.NOTIFICATION_TYPE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class NotificationBuilder {
   private String title;
   private String body;
   private int type;
   private String url;
   private boolean broadcast;
   private List<Integer> targetAudience;
   private List<String> targetUsers;
   private EmailBuilder email;

   private NotificationBuilder() {
      // Private constructor to prevent direct instantiation
   }

   public static NotificationBuilder create() {
      return new NotificationBuilder();
   }

   public NotificationBuilder BroadcastNotification(String title, String body, String url, List<Integer> targetAudience){
      this.title = title;
      this.body = body;
      this.url = url;
      this.targetAudience = targetAudience;
      this.type = NOTIFICATION_TYPE.BROADCAST;
      return this;
   }

   public NotificationBuilder UserNotification (String title, String body, String url, List<String> targetUsers) {
      this.title = title;
      this.body = body;
      this.url = url;
      this.targetUsers = targetUsers;
      this.type = NOTIFICATION_TYPE.USER_DRIVEN;
      return this;

   }

   public NotificationBuilder SendEmail(String title, EmailBuilder email) {
      this.title = title;
      this.email = email;
      this.type = NOTIFICATION_TYPE.EMAIL;
      return this;

   }
}
