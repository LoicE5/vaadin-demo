package demo.views.tictactoe;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import demo.views.MainLayout;

@PageTitle("Tic Tac Toe")
@Route(value = "tictactoe", layout = MainLayout.class)
@AnonymousAllowed
public class TicTacToeView extends VerticalLayout {

    private static final int SIZE = 3;
    private static final String PLAYER_X = "X";
    private static final String PLAYER_O = "O";

    private final Button[][] buttons;
    private String currentPlayer;

    public TicTacToeView() {
        buttons = new Button[SIZE][SIZE];
        currentPlayer = PLAYER_X;

        initializeButtons();
        setWidth("300px");
        setHeight("300px");
    }

    private void initializeButtons() {
        for (int row = 0; row < SIZE; row++) {
            HorizontalLayout rowLayout = new HorizontalLayout();
            for (int col = 0; col < SIZE; col++) {
                Button button = new Button();
                button.setWidthFull();
                button.setHeightFull();
                int finalRow = row;
                int finalCol = col;
                button.addClickListener(e -> onButtonClick(finalRow, finalCol));

                buttons[row][col] = button;
                rowLayout.add(button);
            }
            add(rowLayout);
        }
    }

    private void onButtonClick(int row, int col) {
        Button button = buttons[row][col];

        if (button.getText().isEmpty()) {
            button.setText(currentPlayer);
            if (checkForWin(row, col)) {
                showWinner();
                resetGame();
            } else if (isBoardFull()) {
                showDraw();
                resetGame();
            } else {
                switchPlayer();
            }
        }
    }

    private boolean checkForWin(int row, int col) {
        return checkRow(row) || checkColumn(col) || checkDiagonals() || checkReverseDiagonals();
    }

    private boolean checkRow(int row) {
        return buttons[row][0].getText().equals(currentPlayer)
                && buttons[row][1].getText().equals(currentPlayer)
                && buttons[row][2].getText().equals(currentPlayer);
    }

    private boolean checkColumn(int col) {
        return buttons[0][col].getText().equals(currentPlayer)
                && buttons[1][col].getText().equals(currentPlayer)
                && buttons[2][col].getText().equals(currentPlayer);
    }

    private boolean checkDiagonals() {
        return buttons[0][0].getText().equals(currentPlayer)
                && buttons[1][1].getText().equals(currentPlayer)
                && buttons[2][2].getText().equals(currentPlayer);
    }

    private boolean checkReverseDiagonals() {
        return buttons[0][2].getText().equals(currentPlayer)
                && buttons[1][1].getText().equals(currentPlayer)
                && buttons[2][0].getText().equals(currentPlayer);
    }

    private boolean isBoardFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (buttons[row][col].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showWinner() {
        Notification.show("Player " + currentPlayer + " wins!");
    }

    private void showDraw() {
        Notification.show("It's a draw!");
    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer.equals(PLAYER_X)) ? PLAYER_O : PLAYER_X;
    }

    private void resetGame() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col].setText("");
            }
        }
        currentPlayer = PLAYER_X;
    }
}
