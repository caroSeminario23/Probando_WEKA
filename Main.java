import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.trees.J48;
import weka.classifiers.Evaluation;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        // Cargar el conjunto de datos
        DataSource source = new DataSource("iris.arff");

        // Instanciar el conjunto de datos
        Instances data = source.getDataSet();

        // Mostrar el conjunto de datos
        System.out.println(data);

        // Establecer el atributo a predecir (último atributo)
        if (data.classIndex() == -1) {
            data.setClassIndex(data.numAttributes() - 1);
        }

        // Entrenar el árbol de decisión J48 con estos datos
        entrenamientoJ48(data);
    }

    // Entrenar un árbol de decisión J48
    public static void entrenamientoJ48(Instances dataset) throws Exception {
        // Instanciar el clasificador
        J48 tree = new J48();

        // Brindar el conjunto de datos
        tree.buildClassifier(dataset);

        // Evaluar el clasificador
        Evaluation eval = new Evaluation(dataset);
        eval.crossValidateModel(tree, dataset, 10, new Random(1)); // 10 partes
        System.out.println("----------------------------");
        System.out.println("Resultados de la evaluación:");
        System.out.println(eval.toSummaryString());
    }
}
