package gt.component;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

import gt.gamestate.UserInput;

public class GameKeyListener implements KeyListener {
    private final Consumer<UserInput> userInputConsumer;

    public GameKeyListener(Consumer<UserInput> userInputConsumer) {
        this.userInputConsumer = userInputConsumer;
    }

    private static UserInput keyCodeToUserInput(int keyCode) {
        switch (keyCode) {
        case KeyEvent.VK_BACK_SPACE: // 8
            return UserInput.BACK_SPACE_KEY_PRESSED;
        case KeyEvent.VK_ENTER: // 10
            return UserInput.ENTER_KEY_PRESSED;
        case KeyEvent.VK_ESCAPE: // 27
            return UserInput.ESC_KEY_PRESSED;
        case KeyEvent.VK_LEFT: // 37
            return UserInput.LEFT_KEY_PRESSED;
        case KeyEvent.VK_UP: // 38
            return UserInput.UP_KEY_PRESSED;
        case KeyEvent.VK_RIGHT: // 39
            return UserInput.RIGHT_KEY_PRESSED;
        case KeyEvent.VK_DOWN: // 40
            return UserInput.DOWN_KEY_PRESSED;
        case KeyEvent.VK_MINUS: // 45
            return UserInput.MINUS_KEY_PRESSED;
        case KeyEvent.VK_EQUALS: // 61
            return UserInput.EQUALS_KEY_PRESSED;
        case KeyEvent.VK_A: // 65
            return UserInput.A_KEY_PRESSED;
        case KeyEvent.VK_B: // 66
            return UserInput.B_KEY_PRESSED;
        case KeyEvent.VK_C: // 67
            return UserInput.C_KEY_PRESSED;
        case KeyEvent.VK_D: // 68
            return UserInput.D_KEY_PRESSED;
        case KeyEvent.VK_E: // 69
            return UserInput.E_KEY_PRESSED;
        case KeyEvent.VK_F: // 70
            return UserInput.F_KEY_PRESSED;
        case KeyEvent.VK_G: // 71
            return UserInput.G_KEY_PRESSED;
        case KeyEvent.VK_H: // 72
            return UserInput.H_KEY_PRESSED;
        case KeyEvent.VK_I: // 73
            return UserInput.I_KEY_PRESSED;
        case KeyEvent.VK_J: // 74
            return UserInput.J_KEY_PRESSED;
        case KeyEvent.VK_K: // 75
            return UserInput.K_KEY_PRESSED;
        case KeyEvent.VK_L: // 76
            return UserInput.L_KEY_PRESSED;
        case KeyEvent.VK_M: // 77
            return UserInput.M_KEY_PRESSED;
        case KeyEvent.VK_N: // 78
            return UserInput.N_KEY_PRESSED;
        case KeyEvent.VK_O: // 79
            return UserInput.O_KEY_PRESSED;
        case KeyEvent.VK_P: // 80
            return UserInput.P_KEY_PRESSED;
        case KeyEvent.VK_Q: // 81
            return UserInput.Q_KEY_PRESSED;
        case KeyEvent.VK_R: // 82
            return UserInput.R_KEY_PRESSED;
        case KeyEvent.VK_S: // 83
            return UserInput.S_KEY_PRESSED;
        case KeyEvent.VK_T: // 84
            return UserInput.T_KEY_PRESSED;
        case KeyEvent.VK_U: // 85
            return UserInput.U_KEY_PRESSED;
        case KeyEvent.VK_V: // 86
            return UserInput.V_KEY_PRESSED;
        case KeyEvent.VK_W: // 87
            return UserInput.W_KEY_PRESSED;
        case KeyEvent.VK_X: // 88
            return UserInput.X_KEY_PRESSED;
        case KeyEvent.VK_Y: // 89
            return UserInput.Y_KEY_PRESSED;
        case KeyEvent.VK_Z: // 90
            return UserInput.Z_KEY_PRESSED;
        case KeyEvent.VK_F3: // 114
            return UserInput.F3_KEY_PRESSED;
        case KeyEvent.VK_DELETE: // 127
            return UserInput.DELETE_KEY_PRESSED;
        default:
            return null;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Do nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        UserInput maybeUserInput = keyCodeToUserInput(e.getKeyCode());
        if (maybeUserInput != null) {
            userInputConsumer.accept(maybeUserInput);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Do nothing
    }
}
