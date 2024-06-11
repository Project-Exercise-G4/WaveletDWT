import javax.swing.JFrame;

public class Example {
    public static void main(String[] args) {

        // DWTの実行と係数の取得
        double[] data = { 1, 2, 3, 4 };
        double[] coeffs = DWT.transform(data);

        // Swing GUIの構築と係数の視覚化
        JFrame frame = new JFrame("DWT Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Visualization view = new Visualization(coeffs);
        frame.add(view);
        frame.setSize(1000, 400);
        frame.setVisible(true);

        // Controllerの作成とビューへの追加
        Controller controller = new Controller(view, coeffs);
        view.addMouseListener(controller);
    }
}
