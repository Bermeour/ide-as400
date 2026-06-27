package test.automation;

import com.ide.automation.auxiliary.ObjAux;
import com.ide.automation.utils.ScreenElement;

public class Test1 {

    public static void main(String[] args) throws InterruptedException {
        ObjAux obj= new ObjAux();
        obj.getTakeScreenshotAs400().pathTakeScreenshot("NombreCarpeta");
        Thread.sleep(900);
        obj.getActionsAs400().textWrite(user,"BERMEOUR");
        obj.getActionsAs400().textWrite(pas,"David0W8");//h5238fg50
        obj.getActionsAs400().keyEnter();
        obj.getActionsAs400().textWriteKeyEnter(opcion,"2");
    }

    static ScreenElement user=ScreenElement.builder().row(5).col(25).build();
    static ScreenElement pas=ScreenElement.builder().row(6).col(25).build();
    static ScreenElement opcion=ScreenElement.builder().row(20).col(7).build();
}
