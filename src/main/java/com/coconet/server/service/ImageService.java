package com.coconet.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ImageService {

    public void uploadFile(HttpServletRequest request) throws IOException, ServletException {

        //request에서 parts를 꺼내 객체에 담음
        Collection<Part> parts = request.getParts();

        //parts의 길이만큼 반복
        for(Part p : parts) {
            Part selectFile = p;    //꺼낸 파일을 객체에 저장
            InputStream pts = selectFile.getInputStream();      //pts에 데이터를 읽어옴

            String path = "D:\\Users\\JJH\\Desktop\\Coconet\\images\\";       //저장경로
            String filePath = path + selectFile.getSubmittedFileName();     //저장경로 + 파일명

            FileOutputStream fos = new FileOutputStream(filePath);          //파일 생성(같은 파일이면 덮어쓰기)

            byte[] buf = new byte[1024];
            int size = 0;

            while((size = pts.read(buf)) != -1)
                fos.write(buf, 0, size);

            fos.close();
            pts.close();
        }




        //multipartFile.transferTo(new File("D:\\Users\\JJH\\Desktop\\Coconet\\images\\"+multipartFile.getOriginalFilename()));
    }
}