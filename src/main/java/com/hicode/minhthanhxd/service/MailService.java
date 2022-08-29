package com.hicode.minhthanhxd.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.hicode.minhthanhxd.model.ContentMessage;

@Service
public class MailService {

    @Autowired
    JavaMailSender sender;
    @Autowired
    ServletContext app;
    List<MimeMessage> mime_messages = new ArrayList<MimeMessage>();
    List<Path> listPath = new ArrayList<Path>();

    public void push(ContentMessage cm) throws MessagingException {

        MimeMessage mm = sender.createMimeMessage();
        Path path;
        MimeMessageHelper helper = new MimeMessageHelper(mm, true, "utf-8");
        if (cm.getAttachment() == null) {
            helper.setTo(cm.getTo());
            helper.setSubject(cm.getSubject());
            helper.setText(cm.getBody(), true);
            path = null;
            listPath.add(path);
        } else {
            helper.setTo(cm.getTo());
            helper.setSubject(cm.getSubject());
            helper.setText(cm.getBody(), true);

            try {
                File dir = Paths.get(app.getRealPath("/files/")).toFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                path = Paths.get(dir.getAbsolutePath(), cm.getAttachment().getOriginalFilename());
                cm.getAttachment().transferTo(path);
                helper.addAttachment(cm.getAttachment().getOriginalFilename(), new FileSystemResource(path));
                listPath.add(path);
            } catch (Exception e) {
                System.out.println(cm.getAttachment().getOriginalFilename());
                e.printStackTrace();
            }
        }
        mime_messages.add(mm);
    }

    @Scheduled(fixedDelay = 5000)
    public void run() {

        while (!mime_messages.isEmpty()) {
            // lấy và xóa
            MimeMessage mimeMessage = mime_messages.remove(0);

            sender.send(mimeMessage);
            deleteFile(0);

        }

    }

    private void deleteFile(int i) {
        Path  path = listPath.remove(i);
        if (path!=null){
            path.toFile().delete();
        }
      
    }

}
