package ru.itis.grant.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.dao.interfaces.ApplicationDao;
import ru.itis.grant.model.Application;
import ru.itis.grant.service.interfaces.ApplicationService;
import ru.itis.grant.validation.verification.Verification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Transactional
@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    ApplicationDao applicationDao;
    @Autowired
    Verification verification;

    @Override
    public String getApplicationInString(long applicationId) {
        verification.verifyApplicationExistenceById(applicationId);
        Application application = applicationDao.getApplicationById(applicationId);
        String appString = application.toString();
        createPDFDir();
        Document document = new Document();
        try {
            Font font = getFont();
            addText(applicationId, appString, document, font);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        return appString;
    }

    private void addText(long applicationId, String text, Document document, Font font) throws DocumentException, FileNotFoundException {
        PdfWriter.getInstance(document, new FileOutputStream("pdf/Application" + applicationId + ".pdf"));
        document.open();
        Paragraph paragraph = new Paragraph(text, font);
        document.add(paragraph);
    }

    private Font getFont() throws DocumentException, IOException {
        BaseFont bf = BaseFont.createFont("helveticaneuecyr-light.otf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        return new Font(bf, 11);
    }

    private void createPDFDir() {
        Path path = Paths.get(System.getProperty("user.dir") + "/pdf");
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
