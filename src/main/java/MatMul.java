import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class MatMul {

    public static void main(String [] args) {


        for (int i = 0; i < 300; i++) {
            INDArray rnd = Nd4j.rand(i+1, 300);


            INDArray mmul = rnd.mmul(rnd.transpose());

//            workaround
//            INDArray sum = mmul.add(mmul.transpose());
//            sum.divi(2);
            boolean isSymetric = testMatSym(mmul);
            if (isSymetric) {
                System.out.println("matrix is symmetric");
            } else {
                System.out.println("matrix is not symmetric");
            }
        }


    }

    private static boolean testMatSym(INDArray mmul) {
        for (int i = 0; i < mmul.columns(); i++) {
            for (int j=0; j<mmul.rows(); j++) {
                double d1 = mmul.getFloat(i,j);
                double d2 = mmul.getFloat(j,i);
                if (d1 != d2) {
                    System.out.println("diff: " + (d1-d2));
                    return false;
                }
            }
        }

        return true;
    }
}
