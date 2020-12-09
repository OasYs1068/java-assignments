package ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PieceDragAndDropListener extends MouseAdapter implements MouseMotionListener {

    private BoardPanel boardPanel;

    private boolean dragging;
    private char originFile;
    private int originRank;
    private int dragOffsetX;
    private int dragOffsetY;

    public PieceDragAndDropListener(BoardPanel boardPanel) {
        this.boardPanel = boardPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        originFile = calculateFile(e);
        originRank = calculateRank(e);
        if (boardPanel.isBoardReversed()) {
            dragOffsetX = e.getPoint().x - boardPanel.SQUARE_DIMENSION * ('h' - calculateFile(e));
            dragOffsetY = e.getPoint().y - boardPanel.SQUARE_DIMENSION * (calculateRank(e) - 1);
        } else {
            dragOffsetX = e.getPoint().x - boardPanel.SQUARE_DIMENSION * (calculateFile(e) - 'a');
            dragOffsetY = e.getPoint().y - boardPanel.SQUARE_DIMENSION * (8 - calculateRank(e));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (dragging) {
            boardPanel.postDrag();
            boardPanel.submitMoveRequest(originFile, originRank, calculateFile(e), calculateRank(e));
        }
        dragging = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (dragging) {
            boardPanel.executeDrag(e.getPoint().x - dragOffsetX, e.getPoint().y - dragOffsetY);
        } else {
            boardPanel.preDrag(originFile, originRank, e.getPoint().x - dragOffsetX, e.getPoint().y - dragOffsetY);
            dragging = true;
        }
    }

    private char calculateFile(MouseEvent e) {
        if (boardPanel.isBoardReversed()) {
            return (char) ('h' - e.getPoint().x / boardPanel.SQUARE_DIMENSION);
        } else {
            return (char) ('a' + e.getPoint().x / boardPanel.SQUARE_DIMENSION);
        }
    }

    private int calculateRank(MouseEvent e) {
        if (boardPanel.isBoardReversed()) {
            return (char) (1 + e.getPoint().y / boardPanel.SQUARE_DIMENSION);
        } else {
            return (char) (8 - e.getPoint().y / boardPanel.SQUARE_DIMENSION);
        }
    }

}
