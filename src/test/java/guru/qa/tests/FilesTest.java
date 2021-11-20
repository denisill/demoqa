package guru.qa.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static guru.qa.tests.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FilesTest extends TestBase {

    @Test
    @DisplayName("Скачивание текстового документа и проверка его содержимого")
    void downloadTxtFileTest() throws IOException {
        open(txtFilesUrl);
        File download = $("#output").$("[href='/samples/document/txt/sample2.txt']").download();
        String fileContent = IOUtils.toString(new FileReader(download));
        assertTrue(fileContent.contains("Aeque enim contingit omnibus fidibus"));
    }

    @Test
    @DisplayName("Скачивание PDF файла и проверки содержимого")
    void pdfFileDownloadTest() throws IOException {
        open(pdfFilesUrl);
        File pdf = $("#download_3443").download();
        PDF parsedPdf = new PDF(pdf);
        assertEquals("Adobe InDesign 16.3 (Windows)", parsedPdf.creator);
        assertTrue(parsedPdf.text.contains("Считыватель PR-EH03"));
    }


    @Test
    @DisplayName("Скачивание XLS файла и проверки содержимого")
    void xlsFileDownloadTest() throws IOException {
        open(xlsFilesUrl);
        File xls = $("[href='https://ckmt.ru/TehresursPrice.xls']").download();
        XLS parsedXls = new XLS(xls);
        double numericCellValue = parsedXls.excel.getSheetAt(0).getRow(100).getCell(3).getNumericCellValue();
        assertEquals(85.5, numericCellValue);
    }

    @Test
    @DisplayName("Парсинг CSV файлов")
    void parseCsvFileTest() throws IOException, CsvException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream("csvFiles.csv");
             Reader reader = new InputStreamReader(is)) {
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> str = csvReader.readAll();
            assertEquals(6, str.get(0).length);
        }
    }
}
