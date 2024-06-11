import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Shape;

class Controller extends MouseAdapter {
    private DWTView view;
    private DWTModel model;

    private double[] coeffs;
    private double[] initialCoeffs;

    public Controller(DWTView view, double[] coeffs) {
        this.view = view;
        this.coeffs = coeffs;
        this.initialCoeffs = coeffs.clone();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (int i = 0; i < view.getShapes().length; i++) {
            if (view.getShapes()[i].contains(e.getPoint())) {
                if (coeffs[i] != 0) {
                    coeffs[i] = 0; // 係数が0でなければ0に設定
                } else {
                    coeffs[i] = initialCoeffs[i]; // 係数が0であれば初期値に戻す
                }
                for (int j = 0; j < coeffs.length; j++) {
                    System.out.println("Coefficient " + j + " was clicked! New value: " + coeffs[j]);
                }
                view.repaint();
                break;
            }
        }
    }
}
