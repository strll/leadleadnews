package com.heima.minio.test;

import com.heima.file.service.FileStorageService;
import com.heima.minio.MinIOApplication;
import io.minio.BucketExistsArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

@SpringBootTest(classes= MinIOApplication.class)
@RunWith(SpringRunner.class)
public class MinIOTest {
    @Autowired
    private FileStorageService fileStorageService;
@Test
public void testSaveFile() throws IOException {
    // 构造URL

 //   URL url = new URL("C:\\Users\\hp\\Desktop\\QGYCV)600PY[L68K}[G8(~D.png");

// 打开连接

//    URLConnection con = url.openConnection();

// 输入流

    InputStream is =new FileInputStream("C:\\Users\\hp\\Desktop\\QGYCV)600PY[L68K}[G8(~D.png") ;
    String a=fileStorageService.uploadImgFile("", UUID.randomUUID().toString().replace("-", "")+".jpg",is);
 //   String s = fileStorageService.uploadHtmlFile("", "list.html", new FileInputStream("D:\\list.html"));
    System.out.println(a);
}

@Test
public void testSaveFile1(){
    FileInputStream fileInputStream = null;
        try {
            fileInputStream =  new FileInputStream("D:\\list.html");
            //1.创建minio链接客户端
            MinioClient minioClient = MinioClient.builder()
                    .credentials("minio", "minio123")
                    .endpoint("http://43.142.125.197:9090")
                    .build();
            String bucketName="leadnews";
            boolean b = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!b){
                minioClient.makeBucket(String.valueOf(BucketExistsArgs.builder().bucket(bucketName).build()));
            }


            //2.上传


            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("list.html")//文件名
                    .contentType("text/html")//文件类型
                    .bucket("leadnews")//桶名词  与minio创建的名词一致
                    .stream(fileInputStream, fileInputStream.available(), -1) //文件流
                    .build();
            minioClient.putObject(putObjectArgs);

            System.out.println("http://192.168.200.130:9000/leadnews/ak47.jpg");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
}


    public static void main(String[] args) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream =  new FileInputStream("D:\\list.html");;
            //1.创建minio链接客户端
            MinioClient minioClient = MinioClient.builder().credentials("minioadmin", "minioadmin").endpoint("http://43.142.125.197:9000").build();
            //2.上传
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object("list.html")//文件名
                    .contentType("text/html")//文件类型
                    .bucket("wxt")//桶名词  与minio创建的名词一致
                    .stream(fileInputStream, fileInputStream.available(), -1) //文件流
                    .build();
            minioClient.putObject(putObjectArgs);

          //  System.out.println("http://192.168.200.130:9000/leadnews/ak47.jpg");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    @Test
    public void testUpdateImgFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\hp\\Desktop\\1.png");
            String filePath = fileStorageService.uploadImgFile("", "\u202AC:\\Users\\hp\\Desktop\\1.png", fileInputStream);
            System.out.println(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}