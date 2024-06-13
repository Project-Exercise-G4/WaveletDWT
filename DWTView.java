import java.util.Arrays;

import javax.swing.JPanel;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics;

class DWTView extends JPanel {
    private double[] coeffs;
    private int circleX, circleY;
    Shape[] shapes;

    public DWTView(double[] coeffs) {
        this.coeffs = coeffs;
        this.shapes = new Shape[coeffs.length];
    }

    public void drawShapes() {
        int width = getWidth(); // パネルの幅
        int height = getHeight(); // パネルの高さ
        double xInterval = (double) width / (coeffs.length + 1); // X座標の間隔を調整

        for (int i = 0; i < coeffs.length; i++) {
            int circleX = (int) ((i + 1) * xInterval); // X座標の計算を更新
            int circleY = height / 2; // Y座標をパネルの高さの半分に設定
            shapes[i] = new Ellipse2D.Double(circleX, circleY, 10, 10);
        }
    }

    public Shape[] getShapes() {
        return shapes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        double xInterval = (double) getWidth() / (coeffs.length + 1); // Declare and initialize xInterval

        double maxCoeff = Arrays.stream(coeffs).max().orElse(0); // Declare and initialize the variable maxCoeff

        super.paintComponent(g);
        if (coeffs != null && coeffs.length > 0) {

            for (int i = 0; i < coeffs.length; i++) {
                circleX = (int) ((i + 1) * xInterval); // X座標の計算を更新
                // 係数に基づいてy座標を計算
                circleY = (int) (getHeight() / 2 - (coeffs[i] / maxCoeff) * (getHeight() / 2));

                shapes[i] = new Ellipse2D.Double(circleX, circleY, 10, 10); // Shapeオブジェクトの位置を更新

                g.fillOval(circleX, circleY, 10, 10); // 小さな円を描画
            }
        }
    }

}
