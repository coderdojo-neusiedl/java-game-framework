package net.coderdojo.neusiedl.gameengine;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Keyboard {
    public Keyboard(JComponent component) {
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),"links");
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),"rechts");
        ActionMap actionMap = component.getActionMap();
        actionMap.put("links", createActionFor(() -> System.out.println("L")));
        actionMap.put("rechts", createActionFor(() -> System.out.println("R")));
    }

    private Action createActionFor(Runnable runnable) {
        return new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                runnable.run();
            }
        };
    }
}
