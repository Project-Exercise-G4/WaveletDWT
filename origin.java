//mvc化前のコード

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.Arrays;

class DWT {
    public static double[] transform(double[] data) {
        // DWTの実装
        return new double[] { 1,1,1,1,2,2,3,3,4,4,5,5,6,6,6,6 };
    }
}

class Visualization extends JPanel {
    private double[] initialCoeffs;
    private double[] coeffs;
    private int circleX, circleY;
    private Shape[] shapes;

    public Visualization(double[] coeffs) {
        this.coeffs = coeffs;
        this.initialCoeffs = coeffs.clone(); // 初期係数のコピー
        this.setPreferredSize(new Dimension(500, 500));
        this.shapes = new Shape[coeffs.length];
        int width = getWidth(); // パネルの幅
        int height = getHeight(); // パネルの高さ
        double xInterval = (double) width / (coeffs.length + 1); // X座標の間隔を調整
        for (int i = 0; i < coeffs.length; i++) {
            int circleX = (int) ((i + 1) * xInterval); // X座標の計算を更新
            int circleY = height / 2; // Y座標をパネルの高さの半分に設定
            shapes[i] = new Ellipse2D.Double(circleX, circleY, 10, 10);
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < shapes.length; i++) {
                    if (shapes[i].contains(e.getPoint())) {
                        if (coeffs[i] != 0) {
                            coeffs[i] = 0; // 係数が0でなければ0に設定
                        } else {
                            coeffs[i] = initialCoeffs[i]; // 係数が0であれば初期値に戻す
                        }
                        for (int j = 0; j < coeffs.length; j++) {
                            System.out.println("Coefficient " + j + " was clicked! New value: " + coeffs[j]);
                        }   
                        repaint();
                        break;
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (coeffs != null && coeffs.length > 0) {
            int width = getWidth(); // パネルの幅
            int height = getHeight(); // パネルの高さ
            double xInterval = (double) width / (coeffs.length + 1); // X座標の間隔を調整

            // 係数の最大値を見つける
            double maxCoeff = Arrays.stream(coeffs).max().getAsDouble();

            for (int i = 0; i < coeffs.length; i++) {
                circleX = (int) ((i + 1) * xInterval); // X座標の計算を更新
                // 係数に基づいてy座標を計算
                circleY = (int) (height / 2 - (coeffs[i] / maxCoeff) * (height / 2));

                shapes[i] = new Ellipse2D.Double(circleX, circleY, 10, 10); // Shapeオブジェクトの位置を更新

                g.fillOval(circleX, circleY, 10, 10); // 小さな円を描画
            }
        }
    }

}

public class origin {
    public static void main(String[] args) {

        // DWTの実行と係数の取得
        double[] data = { 1, 2, 3, 4 };
        double[] coeffs = DWT.transform(data);

        // Swing GUIの構築と係数の視覚化
        JFrame frame = new JFrame("DWT Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Visualization(coeffs));
        frame.setSize(1000, 400);
        frame.setVisible(true);
    }
}
