package net.coderdojo.neusiedl.gameengine;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Keyboard {
    public Keyboard(JComponent component, Runnable left, Runnable right, Runnable stop) {
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),"links");
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),"rechts");
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released LEFT"),"stop");
        component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released RIGHT"),"stop");
        ActionMap actionMap = component.getActionMap();
        actionMap.put("links", createActionFor(left));
        actionMap.put("rechts", createActionFor(right));
        actionMap.put("stop", createActionFor(stop));
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
