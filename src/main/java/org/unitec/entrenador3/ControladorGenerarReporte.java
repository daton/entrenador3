package org.unitec.entrenador3;


import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/reporte")
public class ControladorGenerarReporte {


    /**
     *
     * @param response La respuesta es un arreglo de bytes correspondiente al excel en formato xlsx
     * @return  El tipo de retorno es el excel
     * @throws IOException Lanza una excepcion
     */
    @RequestMapping(value="/generar", method= RequestMethod.GET)
    public @ResponseBody
    byte[] culera2(HttpServletResponse response)throws IOException {








        // Write the output to a file
        String nombre = "reporte alumno";
        FileOutputStream out = new FileOutputStream(nombre);


        // GridFSDBFile filesito=gridFsTemplate.findOne(new Query(Criteria.where("filename").is(nombre)));
        File salidaFile=new File(nombre);
        //filesito.writeTo(imageFile);


        //Creamos un documento de pdf
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);
        PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
// Add a Paragraph
        document.add(new Paragraph("iText is:").setFont(font));
// Create a List
        List list = new List()
                .setSymbolIndent(12)
                .setListSymbol("\u2022")
                .setFont(font);
// Add ListItem objects
        list.add(new ListItem("Never gonna give you up"))
                .add(new ListItem("Never gonna let you down"))
                .add(new ListItem("Never gonna run around and desert you"))
                .add(new ListItem("Never gonna make you cry"))
                .add(new ListItem("Never gonna say goodbye"))
                .add(new ListItem("Never gonna tell a lie and hurt you"));
// Add the list
        document.add(list);


        document.close();

        byte[] bytes= FileCopyUtils.copyToByteArray(salidaFile);
        System.out.println("Recobrando correctamente con este metodo del todo nuevof");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + salidaFile.getName() + "\"");
        // response.setHeader("Content-Disposition", "attachment; filename=deployment-definitions.xlsx");
        response.setContentLength(bytes.length);
       // response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setContentType("application/pdf");
        return bytes;

        /*
         Map map = new HashMap();
        map.put("success", true);
        ObjectMapper maper2=new ObjectMapper();
         */
    }
}
