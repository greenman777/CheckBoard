module com.huawei.checkb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.huawei.checkboard to javafx.fxml;
    exports com.huawei.checkboard;
}