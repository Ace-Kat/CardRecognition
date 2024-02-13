import core.DisplayWindow;

public class RunMe {
    public static void main(String[] args) {
        // --== Load an image to filter ==--
//        DisplayWindow.showFor("images/SetCard4boxes.jpeg", 800, 600, "EdgeDetection");

        // --== Determine your input interactively with menus ==--
        DisplayWindow.getInputInteractively(800,600);
    }
}
