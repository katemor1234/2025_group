package ie.mtu.mtu_2025_demintia;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public final class ViewRouter {
    private static Scene scene;

    private ViewRouter() {}

    /** Call once from Main after the Scene is created */
    public static void attach(Scene s) {
        scene = s;
    }

    /** Swap the scene root while preserving size, styles, and window */
    public static void go(String fxml) {
        ensureAttached();
        try {
            Parent root = FXMLLoader.load(Main.class.getResource(fxml));
            scene.setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** Same as go(), but allows you to pass any node on the current scene (optional overload) */
    public static void go(String fxml, Node anyNodeOnScene) {
        try {
            Parent root = FXMLLoader.load(Main.class.getResource(fxml));
            anyNodeOnScene.getScene().setRoot(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void ensureAttached() {
        if (scene == null) {
            throw new IllegalStateException("ViewRouter not attached. Call ViewRouter.attach(scene) in Main.start().");
        }
    }
}