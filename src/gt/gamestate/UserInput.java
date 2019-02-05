package gt.gamestate;

public enum UserInput {
    // Mouse:
    //  -Left button
    LEFT_BUTTON_PRESSED, LEFT_BUTTON_RELEASED,
    //  -Right button
    RIGHT_BUTTON_PRESSED, RIGHT_BUTTON_RELEASED,
    // Keyboard:
    // -Arrow keys
    UP_KEY_PRESSED, DOWN_KEY_PRESSED, LEFT_KEY_PRESSED, RIGHT_KEY_PRESSED,
    // -Letter keys
    A_KEY_PRESSED, B_KEY_PRESSED, C_KEY_PRESSED, D_KEY_PRESSED, E_KEY_PRESSED, F_KEY_PRESSED, G_KEY_PRESSED, H_KEY_PRESSED, I_KEY_PRESSED, J_KEY_PRESSED,
    K_KEY_PRESSED, L_KEY_PRESSED, M_KEY_PRESSED, N_KEY_PRESSED, O_KEY_PRESSED, P_KEY_PRESSED, Q_KEY_PRESSED, R_KEY_PRESSED, S_KEY_PRESSED, T_KEY_PRESSED,
    U_KEY_PRESSED, V_KEY_PRESSED, W_KEY_PRESSED, X_KEY_PRESSED, Y_KEY_PRESSED, Z_KEY_PRESSED,
    // -Other keys
    MINUS_KEY_PRESSED, EQUALS_KEY_PRESSED,
    BACK_SPACE_KEY_PRESSED, ENTER_KEY_PRESSED, ESC_KEY_PRESSED,
    F3_KEY_PRESSED,
    DELETE_KEY_PRESSED;

    public static boolean isKeyboardInput(UserInput input) {
        switch (input) {
        case LEFT_BUTTON_PRESSED:
        case LEFT_BUTTON_RELEASED:
        case RIGHT_BUTTON_PRESSED:
        case RIGHT_BUTTON_RELEASED:
            return false;
        default:
            return true;
        }
    }

    public static Character toAscii(UserInput input) {
        switch (input) {
        case A_KEY_PRESSED:
            return Character.valueOf('A');
        case B_KEY_PRESSED:
            return Character.valueOf('B');
        case C_KEY_PRESSED:
            return Character.valueOf('C');
        case D_KEY_PRESSED:
            return Character.valueOf('D');
        case E_KEY_PRESSED:
            return Character.valueOf('E');
        case F_KEY_PRESSED:
            return Character.valueOf('F');
        case G_KEY_PRESSED:
            return Character.valueOf('G');
        case H_KEY_PRESSED:
            return Character.valueOf('H');
        case I_KEY_PRESSED:
            return Character.valueOf('I');
        case J_KEY_PRESSED:
            return Character.valueOf('J');
        case K_KEY_PRESSED:
            return Character.valueOf('K');
        case L_KEY_PRESSED:
            return Character.valueOf('L');
        case M_KEY_PRESSED:
            return Character.valueOf('M');
        case N_KEY_PRESSED:
            return Character.valueOf('N');
        case O_KEY_PRESSED:
            return Character.valueOf('O');
        case P_KEY_PRESSED:
            return Character.valueOf('P');
        case Q_KEY_PRESSED:
            return Character.valueOf('Q');
        case R_KEY_PRESSED:
            return Character.valueOf('R');
        case S_KEY_PRESSED:
            return Character.valueOf('S');
        case T_KEY_PRESSED:
            return Character.valueOf('T');
        case U_KEY_PRESSED:
            return Character.valueOf('U');
        case V_KEY_PRESSED:
            return Character.valueOf('V');
        case W_KEY_PRESSED:
            return Character.valueOf('W');
        case X_KEY_PRESSED:
            return Character.valueOf('X');
        case Y_KEY_PRESSED:
            return Character.valueOf('Y');
        case Z_KEY_PRESSED:
            return Character.valueOf('Z');
        default:
            return null;
        }
    }
}
