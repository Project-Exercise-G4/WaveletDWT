import WaveletData.WaveletData;

class DWTModel {
    public static double[] transform(double[] data) {
        double[] coeffs = WaveletData.dataSampleCoefficients();
        return coeffs;
    }
}
