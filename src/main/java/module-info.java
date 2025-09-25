module ie.mtu.mtu_2025_demintia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

//    requires javafx.controls;
//    requires javafx.fxml;
//    requires javafx.web;
//    requires java.sql;
//
//    //requires com.mysql.cj;
//
//    requires org.controlsfx.controls;
//    requires com.dlsc.formsfx;
//    requires net.synedra.validatorfx;
//    requires org.kordamp.bootstrapfx.core;
//    requires eu.hansolo.tilesfx;

    opens ie.mtu.mtu_2025_demintia to javafx.fxml;
    exports ie.mtu.mtu_2025_demintia;
}