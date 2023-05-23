package logic;

import net.sf.saxon.xqj.SaxonXQDataSource;

import javax.xml.xquery.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class TestXQuery {
    public static void main(String[] args) {
        try {
            InputStream xqyFile = new FileInputStream(new File("C:/work/coding/Java/Testfield/TestStreams/src/main/resources/courses.xqy"));
            XQDataSource xqDataSource = new SaxonXQDataSource(); // get correct config
            XQConnection connection = xqDataSource.getConnection();
            String query = "for $x in doc(\"courses.xml\")/courses/course "
                    + "where $x/fees>5000 "
                    + "return $x/title";
            XQPreparedExpression expression = connection.prepareExpression(xqyFile);
            XQResultSequence result = expression.executeQuery();
            while (result.next()) {
                System.out.println(result.getItemAsString(null));
            }
            connection.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (XQException e) {
            throw new RuntimeException(e);
        }
    }
}
