package cn.edu.uestc.imagesecure.imagesearchdemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jblas.ComplexDoubleMatrix;
import org.jblas.DoubleMatrix;
import org.jblas.Eigen;
public class PCA {
    public static void main(String[] args){
        DoubleMatrix d = new DoubleMatrix(new double[][]{{1,1,1000,1}});
        DoubleMatrix result = PCA.dimensionReduction(d,1);
        System.out.println(result);
    }
    public static DoubleMatrix dimensionReduction(DoubleMatrix source, int dimension) {
        DoubleMatrix covMatrix = source.mmul(source.transpose()).div(source.columns);
        ComplexDoubleMatrix eigVal = Eigen.eigenvalues(covMatrix);
        ComplexDoubleMatrix[] eigVectorsVal = Eigen.eigenvectors(covMatrix);
        ComplexDoubleMatrix eigVectors = eigVectorsVal[0];
        List<PCABean> beans = new ArrayList<PCA.PCABean>();
        for (int i = 0; i < eigVectors.columns; i++) {
            beans.add(new PCABean(eigVal.get(i).real(), eigVectors.getColumn(i)));
        }
        Collections.sort(beans);
        DoubleMatrix newVec = new DoubleMatrix(dimension, beans.get(0).vector.rows);
        for (int i = 0; i < dimension; i++) {
            ComplexDoubleMatrix dm = beans.get(i).vector;
            DoubleMatrix real = dm.getReal();
            newVec.putRow(i, real);
        }
        return newVec.mmul(source);
    }
    static class PCABean implements Comparable<PCABean> {
        double eigenValue;
        ComplexDoubleMatrix vector;
        public PCABean(double eigenValue, ComplexDoubleMatrix vector) {
            super();
            this.eigenValue = eigenValue;
            this.vector = vector;
        }

        public int compareTo(PCABean o) {
            return Double.compare(o.eigenValue, eigenValue);
        }
        @Override
        public String toString() {
            return "PCABean [eigenValue=" + eigenValue + ", vector=" + vector + "]";
        }
    }
}