package org.unitec.entrenador3;




import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.gridfs.GridFSDBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.gridfs.GridFsCriteria.whereFilename;

/**
 * Created by campitos on 23/02/17.
 */

@Controller
public class ControladorArchivo {
    @Autowired
    GridFsTemplate gridFsTemplate;



    @Autowired
    GridFsOperations gridOperations;
    /*
GUARDAR IMAGEN ELN MONGODB
 */
    @RequestMapping(value="/cargar-mongo1", method= RequestMethod.POST, headers={"Accept=text/html"})
    public @ResponseBody
    String handleFileUpload(@RequestParam("file") MultipartFile file)throws Exception{
        String nombre=file.getOriginalFilename();
        String prefijo="";
        //DateTime date=new DateTime();
      //  int dia=  date.getDayOfYear();
       // int segundo=  date.getSecondOfDay();
        long tamano= file.getSize();
        String fileName = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        if (file.getSize() > 0) {
            inputStream = file.getInputStream();


            String contenido=  file.getContentType();
            int punto=nombre.indexOf(".");
            String nombreSolo=nombre.substring(0, punto);
          //  prefijo="dia"+dia+"segundo"+segundo;
            //   System.out.println("nombre de archivo:"+fileName);
            //Guardamos imagen, si es que hay
            gridFsTemplate.store(inputStream,prefijo+nombre,file.getContentType());


        }
        System.out.println("El nombre de archivaldo es:" + nombre + " el tamaño del archivo esta:" + tamano + " se guardo como: " + prefijo + nombre);

        return  prefijo+nombre;

    }

    /*
    Para leer la imagen DE MONGODB
    */
    @RequestMapping(value="/leer-imagen/{nombre:.+}", method= RequestMethod.GET)
    public @ResponseBody
    byte[] culera2(HttpServletResponse response, @PathVariable String nombre)throws IOException {
        GridFSFile filesito=gridOperations.findOne(new Query(Criteria.where("filename").is(nombre)));
        GridFSFindIterable result = gridOperations.find(query(whereFilename().is(
                "filename.txt")));

        File imageFile=new File(filesito.getFilename());


        byte[] bytes= FileCopyUtils.copyToByteArray(imageFile);
        System.out.println("Recobrando correctamente con este metodo del todo nuevo");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + imageFile.getName() + "\"");
        response.setContentLength(bytes.length);
        response.setContentType("image/png");
        return bytes;
    }
    @RequestMapping("/correo")
    @ResponseBody
    public String enviar() throws MessagingException, UnsupportedEncodingException {


        /*
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("rapidclimate@gmail.com");


        msg.setTo("rapidclimate@outlook.com");
        msg.setFrom("raul@gmail.com");
        msg.setSubject("Resultado Egel");

        msg.setText("<h1>Resultados</h1>");

         */

      //  MimeMessage message = mailSender.createMimeMessage();
       // MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");

   //     helper.setTo("rapidclimate@outlook.com");
       // message.setTo("rapidclimate@outlook.com");
     //   helper.setFrom("rapidclimate@gmail.com", "Juan Carlos");
      //  helper.setSubject("Resultado Egel");
      //  helper.setText("<h1>Hola crayola</h1><p>Espero que todo este bien</p><br><img src='cid:miImagen'>", true);
    // getClass().getResource("egel.png");

//El siguiente es para vaadin
        //ThemeResource resource = new ThemeResource("../egel.png");
       // Image imagen = new Image(null, resource);


        //El siguiente busca el id que le asignes a tu imagen como htnml aariba en el src de la imagen
     //   helper.addInline("miImagen", new FileSystemResource("/home/campitos/Pictures/osito.jpg"));
        //la ruta de abajo es la ruta de donde se encuentra build/classess/main pero solos se pone org/egel
       // helper.addInline("miImagen", new ClassPathResource("org/egel/osillo.jpg"));


        //El siguiente es para agregar un attachment pdf, etc lo que quieras
       // helper.addAttachment("imagen",new FileSystemResource("/home/campitos/Pictures/osito.jpg"));


        try{
         //   this.mailSender.send(message);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }

        return "Hola";
    }





}
