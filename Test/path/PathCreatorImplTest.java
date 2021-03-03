package path;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class PathCreatorImplTest {
    private static PathCreatorImpl pathCreator;

    @BeforeAll
    static void initialize() {
        pathCreator = new PathCreatorImpl();
    }

    @Test
    void shouldNotBeNullIfReport() {
        //given
        String finalPath =  new String();
        String path = "E:\\testowo";
        String report = "report";
        System.out.println(finalPath);
        //when
        finalPath = pathCreator.createPath(path, report);
        //then
        assertThat(finalPath, is(notNullValue()));
    }

    @Test
    void shouldreturnNullIfReportExtensionEndsWithDot() {
        //given
        String finalPath = "";
        String path = "E:\\testowo";
        String report = "report.";
        System.out.println(finalPath);
        //when
        finalPath = pathCreator.createPath(path, report);
        //then
        assertThat(finalPath, is(nullValue()));
    }

    @Test
    void extensionOfReportShouldEndsWithTxt() {
        //given
        String finalPath = "";
        String path = "E:\\testowo";
        String report = "report";
        String extension = "";
        //when
        finalPath = pathCreator.createPath(path, report);
        File file = new File(finalPath);
        extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        //then
        assertThat(extension, is("txt"));
    }
}