package org.unitec.entrenador3;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.vaadin.server.VaadinSession;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Properties;


/**
 * Created by campitos on 1/03/17.
 */

@SpringComponent
@UIScope
public class ServicioCorreo {


    public String enviar(String alumno, String mensaje, ArrayList<Pregunta> preguntas, String temaEgel) throws MessagingException, UnsupportedEncodingException {


        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();



		/*
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);

		mailSender.setUsername("rapidclimate@gmail.com");

*/

        mailSender.setHost("mail.sierra-guadalupe.org");
        mailSender.setPort(465);

        mailSender.setUsername("daniel@sierra-guadalupe.org");
        mailSender.setPassword("topoyiyo");

        Properties props = mailSender.getJavaMailProperties();
        //props.put("mail.transport.protocol", "smtp");
        // props.put("mail.smtp.auth", "true");
        // props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "mail.sierra-guadalupe.org"); //SMTP Host

        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port
        props.put("mail.debug", "true");

        mailSender.setJavaMailProperties(props);



        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true,"UTF-8");

        helper.setTo(new String[]{"janunezp@mail.unitec.mx","rapidclimate@gmail.com"/*,"aalbarra@mail.unitec.mx"*/});
        helper.setPriority(1);

       //  message.setTo("rapidclimate@outlook.com");
        helper.setFrom("daniel@sierra-guadalupe.org", alumno);

      helper.setSubject("Resultado Egel");
       // helper.setText("<h1>Hola crayola</h1><p>"+mensaje+"</p><br><img src='cid:miImagen'>", true);
       helper.setText("<h2>Resultado del examen de "+ VaadinSession.getCurrent().getAttribute("nombre")+" "+
               VaadinSession.getCurrent().getAttribute("paterno")+" cuenta: "+
                VaadinSession.getCurrent().getAttribute("cuenta")+"</h2><h3>Tema EGEL:"+ temaEgel+"</h3><p>"+mensaje+"</p>", true);
 String nombreSustentante=  VaadinSession.getCurrent().getAttribute("paterno")+" "+VaadinSession.getCurrent().getAttribute("nombre")+"_"+ VaadinSession.getCurrent().getAttribute("cuenta");
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
        //Initialize PDF writer
        PdfWriter writer = null;
        FileOutputStream out=null;
        String nombre = nombreSustentante+"reporte.pdf";
        try {


      out = new FileOutputStream(nombre);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }




        // GridFSDBFile filesito=gridFsTemplate.findOne(new Query(Criteria.where("filename").is(nombre)));
        File salidaFile=new File(nombre);
        //filesito.writeTo(imageFile);


        //Creamos un documento de pdf
       writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        helper.addAttachment("resultado_"+nombreSustentante+"_.pdf",salidaFile);

        // Initialize document
        Document document = new Document(pdf);

        //Add paragraph to the document
        document.add((new Paragraph("UNITEC, Campus Ecatepec, Ingeniería en Sistemas Computacionales.")));
        LocalDateTime now = LocalDateTime.now();
        document.add(new Paragraph("Fecha de aplicación:"+now));
        document.add(new Paragraph(alumno));

      //  if(VaadinSession.getCurrent().getAttribute("tema")==null)  document.add(new Paragraph("Tema EGEL: "+VaadinSession.getCurrent().getAttribute("tema")));
      document.add(new Paragraph(temaEgel));

        document.add(new Paragraph("Nota:Las preguntas correctas del alumno se muestran en azul,y las incorrectas en rojo, " +
                " aún cuando la opcion correcta en este reporte es siempre la -a-,  en el examen se genera en orden aleatorio, " +
                "aquí se muestra de esta manera con el objetivo de efectuar  una revisión rápida."));

        PdfFont font = null;
        try {
            font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
        } catch (IOException e) {
            e.printStackTrace();
        }
// Agregamos las 8 preguntas desl arraylist
        int indice=1;
        for(Pregunta pregunta:preguntas) {

            Text t = new Text(indice +" ."+pregunta.getPregunta());
            Paragraph p = new Paragraph(t);
         //   document.add(new Paragraph("iText is:").setFont(font));
          //  document.add(p);
  int valor =0;
            List list = new List()
                    .setSymbolIndent(12)
                    .setListSymbol("")
                    .setFont(font);
// Add ListItem objects
            if(pregunta.isEstaBuena()){
                list.setFontColor(Color.BLUE);
            }else{
                list.setFontColor(Color.RED);
            }

            ListItem opcion0=new ListItem("a. "+pregunta.getOpciones().get(0).getTitulo());
            ListItem opcion1=new ListItem("b. "+pregunta.getOpciones().get(1).getTitulo());
            ListItem opcion2=new ListItem("c. "+pregunta.getOpciones().get(2).getTitulo());
            ListItem opcion3=new ListItem("d. "+pregunta.getOpciones().get(3).getTitulo());

            //Ajustamos todas a negro
            opcion0.setFontColor(Color.BLACK);
            opcion1.setFontColor(Color.BLACK);
            opcion2.setFontColor(Color.BLACK);
            opcion3.setFontColor(Color.BLACK);



                    if(pregunta.getOpciones().get(0).isAcierto())opcion0.setFontColor(Color.GREEN);


                    if(pregunta.getOpciones().get(1).isAcierto())opcion1.setFontColor(Color.GREEN);

                    if(pregunta.getOpciones().get(2).isAcierto())opcion2.setFontColor(Color.GREEN);

                    if(pregunta.getOpciones().get(3).isAcierto())opcion3.setFontColor(Color.GREEN);



            list.add(new ListItem(indice+". "+pregunta.getPregunta()))
                    .add(opcion0)
                    .add(opcion1)
                    .add(opcion2)
                    .add(opcion3);

// Add the list
            document.add(list);
            Paragraph salto=new Paragraph(new Text(""));
            document.add(salto);

            indice++;

        }
// Create a List



        //Close document
        document.close();


        try{
            mailSender.send(message);
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }

        return "Mensaje enviado con éxito";
    }


}
