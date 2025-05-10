public class TPHeritage {   
  public static void main(String[] args){       
    int exercice = 5; // choisir l'exercice à executer ici        
    runExercice(exercice);    }  
  public static void runExercice(int n){        
    switch(n) {           
      case 1: exercice1();                
        break;           
      case 2: exercice2();             
        break;            
      case 3: exercice3();             
        break;            
      case 4: exercice4();               
        break;            
      case 5: exercice5();               
        break;            
      case 6: exercice6();              
        break;            
      case 7: exercice7();              
        break;            
      case 8: exercice8();             
        break;   
      case 9: exercice9();               
        break;           
      case 10: exercice10();               
        break;           
      case 11: exercice11();                
        break;            
      case 12: exercice12();              
        break;            
      case 13: exercice13();             
        break;           
      default: System.out.println("Exercice non défini");      
    }    
  }

    //Exercice n°1 - Classe Rectangle

    public class Point {
    
        private double x;
        private double y;
        public Point (double x, double y){
            this.x = x;
            this.y = y;

        }
        public double getX(){
            return x;

        }
        public double getY(){
            return y;

        }
        public void translate(double dx, double dy){
            this.x = this.x + dx;
            this.y = this.y + dy;
        }
    }
    public class Rectangle {
        private Point bottomLeft;
        private double width;
        private double height;
        private static int nbr = 0;
        

        // Constructeur avec 2 points
        public Rectangle(Point bottomLeft, Point topRight) {
            this.bottomLeft = bottomLeft;
            this.width = topRight.getX() - bottomLeft.getX();
            this.height = topRight.getY() - bottomLeft.getY();
            nbr++;
        }

        // Constructeur avec 1 point et 2 longueurs
        public Rectangle(Point bottomLeft, double width, double height) {
            this.bottomLeft = bottomLeft;
            this.width = width;
            this.height = height;
            nbr++;
        }

        // Constructeur avec 4 longueurs
        public Rectangle(double x, double y, double width, double height) {
            this.bottomLeft = new Point(x, y);
            this.width = width;
            this.height = height;
            nbr++;
        }
        

        // Méthode surface
        public double surface() {
            return width * height;
        }

        // Méthode translate
        public void translateEasy(float dx, float dy) {
            bottomLeft.translate(dx, dy);
        }
        //or
        public Point TranslateStrong(double dx, double dy) {
            return new Point(bottomLeft.getX() + dx, bottomLeft.getY() + dy);
        }

        // Méthode contains pour un point
        public boolean contains(Point p) {
            return p.getX() >= bottomLeft.getX() && 
                p.getX() <= bottomLeft.getX() + width &&
                p.getY() >= bottomLeft.getY() && 
                p.getY() <= bottomLeft.getY() + height;
        }

        // Méthode contains pour un rectangle
        public boolean contains(Rectangle r) {
            return contains(r.bottomLeft) && 
                contains(new Point(r.bottomLeft.getX() + r.width, 
                                    r.bottomLeft.getY() + r.height));
        }

        // Méthode sameAs
        public boolean sameAs(Rectangle other) {
            return this.bottomLeft.equals(other.bottomLeft) &&
                this.width == other.width &&
                this.height == other.height;
        }

        // Méthode hull (statique)
        public Rectangle hull(Rectangle[] rectangles) {
            if (rectangles.length == 0) return null;
            
            double minX = rectangles[0].bottomLeft.getX();
            double minY = rectangles[0].bottomLeft.getY();
            double maxX = rectangles[0].bottomLeft.getX() + rectangles[0].width;
            double maxY = rectangles[0].bottomLeft.getY() + rectangles[0].height;
            

            for (Rectangle r : rectangles) {
                minX = Math.min(minX, r.bottomLeft.getX());
                minY = Math.min(minY, r.bottomLeft.getY());
                maxX = Math.max(maxX, r.bottomLeft.getX() + r.width);
                maxY = Math.max(maxY, r.bottomLeft.getY() + r.height);
            }
            
            return new Rectangle(new Point(minX, minY), maxX - minX, maxY - minY);
        }
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Rectangle)) return false;
            Rectangle other = (Rectangle)obj;
            return sameAs(other);
        }
        @Override
        public String toString() {
            return "Rectangle [bottomLeft=" + bottomLeft + ", width=" + width + ", height=" + height + "]";
        }

        // Getter pour nbr
        public static int getNbr() {
            return nbr;
        }
    }
    //Exercie n°7 - Classe Dessin

    // public class Dessin{
    //     private Rectangle[] rectangles;
    //     private int count;
    //     private Rectangle hullRect;
    //     public Dessin(int capacity) {
    //         rectangles = new Rectangle[capacity];
    //         count = 0;
    //         hullRect = null;
    //     }

    //     public void add(Rectangle r) {
    //         if (count < rectangles.length) {
    //             rectangles[count++] = r;
    //             updateHull(r);
    //         }
    //     }

    //     private void updateHull(Rectangle r) {
    //         if (hullRect == null) {
    //             hullRect = new Rectangle(r.bottomLeft, r.width, r.height);
    //         } else {
    //             double minX = Math.min(hullRect.bottomLeft.getX(), r.bottomLeft.getX());
    //             double minY = Math.min(hullRect.bottomLeft.getY(), r.bottomLeft.getY());
    //             double maxX = Math.max(hullRect.bottomLeft.getX() + hullRect.width, 
    //                                 r.bottomLeft.getX() + r.width);
    //             double maxY = Math.max(hullRect.bottomLeft.getY() + hullRect.height, 
    //                                 r.bottomLeft.getY() + r.height);
                
    //             hullRect = new Rectangle(new Point(minX, minY), maxX - minX, maxY - minY);
    //         }
    //     }

    //     public double surface() {
    //         double total = 0;
    //         for (int i = 0; i < count; i++) {
    //             total += rectangles[i].surface();
    //         }
    //         return total;
    //     }

    //     public void translate(double dx, double dy) {
    //         for (int i = 0; i < count; i++) {

    //             rectangles[i].translate(dx, dy);
    //         }
    //         if (hullRect != null) {
    //             hullRect.translate(dx, dy);
    //         }
    //     }

    //     public Rectangle hull() {
    //         if (hullRect == null && count > 0) {
    //             Rectangle[] existing = new Rectangle[count];
    //             System.arraycopy(rectangles, 0, existing, 0, count);
    //             hullRect = Rectangle.hull(existing);
    //         }
    //         return hullRect;
    //     }
    // }

    //Exercices sur l'Héritage

    //Exercice n°1 - Classe SlantedRectangle

    public class SlantedRectangle extends Rectangle {
        private double angle;

        public SlantedRectangle(Point bottomLeft, double width, double height, double angle) {
            super(bottomLeft, width, height);
            this.angle = angle;
        }

        public SlantedRectangle(Point p1, Point p2, double angle) {
            super(p1, p2);
            this.angle = angle;
        }

        public SlantedRectangle(double x, double y, double width, double height, double angle) {
            super(x, y, width, height);
            this.angle = angle;
        }

        public void rotate(double deltaAngle) {
            this.angle += deltaAngle;
        }

        @Override
        public boolean contains(Point p) {
            // Implémentation plus complexe qui tient compte de l'angle
            // Pour simplifier, on garde la même implémentation que Rectangle
            return super.contains(p);
        }

        
        @Override
        public boolean contains(Rectangle r) {
            if (r instanceof SlantedRectangle) {
                SlantedRectangle sr = (SlantedRectangle)r;
                if (this.angle != sr.angle) return false;
            }
            // Implémentation simplifiée
            return super.contains(r);
        }

        public boolean contains(SlantedRectangle sr) {
            return this.angle == sr.angle && super.contains(sr);
        }

        @Override
        public boolean sameAs(Rectangle other) {
            if (!(other instanceof SlantedRectangle)) return false;
            SlantedRectangle sr = (SlantedRectangle)other;
            return super.sameAs(other) && this.angle == sr.angle;
        }

        @Override
        public String toString() {
            return super.toString() + " [angle=" + angle + "]";
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof SlantedRectangle)) return false;
            return sameAs((SlantedRectangle)obj);
        }
    }

    //Exercice n°4 - Analyse des appels de méthode

    //Les résultats des appels seraient:

    // System.out.println(r.surface()); // OK - méthode de Rectangle
    // r.rotate(2); // Erreur de compilation - rotate n'existe pas dans Rectangle
    // System.out.println(r.contains(p)); // OK - méthode de Rectangle

    // System.out.println(t.surface()); // OK - méthode héritée de Rectangle
    // t.rotate(2); // OK - méthode de SlantedRectangle (polymorphisme)
    // System.out.println(t.contains(p)); // OK - méthode redéfinie dans SlantedRectangle

    // System.out.println(s.surface()); // OK - méthode héritée de Rectangle
    // s.rotate(2); // OK - méthode de SlantedRectangle
    // System.out.println(s.contains(p)); // OK - méthode redéfinie dans SlantedRectangle

    //Exercice n°5 - Dessin avec rectangles inclinés

    // Oui, la classe Dessin peut contenir des rectangles inclinés grâce au polymorphisme. Cependant:
    // - `surface()` fonctionnera toujours correctement
    // - `contains()` pourrait ne pas fonctionner correctement si l'implémentation ne tient pas compte de l'angle
    // - `hull()` pourrait ne pas donner le résultat attendu si les rectangles sont inclinés différemment

    // Exercice n°6 - Méthode toString()

    // Dans la classe Rectangle
    // @Override
    // public String toString() {
    //     return "Rectangle [bottomLeft=" + bottomLeft + ", width=" + width + ", height=" + height + "]";
    // }

    //C'est une définition (redéfinition de la méthode de Object). Il est utile de la redéfinir dans SlantedRectangle pour inclure l'angle.

    // Exercice n°7 - Méthode equals()

    // Dans la classe Rectangle
    // @Override
    // public boolean equals(Object obj) {
    //     if (!(obj instanceof Rectangle)) return false;
    //     Rectangle other = (Rectangle)obj;
    //     return sameAs(other);
    // }

    // Dans la classe SlantedRectangle (déjà fait plus haut)
    // Exercice n°8 - Fragment de programme

    // Le fragment affichera:

    // void f(A o) dans A
    // void f(A o) dans A
    // void f(A o) dans A
    // void f(A o) dans B
    // void f(A o) dans B
    // void f(A o) dans B
    // void f(A o) dans B
    // void f(A o) dans B
    // void f(A o) dans B

    // Exercice n°9 - Ajout de f(B o) dans B

    // C'est une surcharge (pas une redéfinition). Le fragment affichera:

    // void f(A o) dans A
    // void f(A o) dans A
    // void f(A o) dans A
    // void f(A o) dans B
    // void f(A o) dans B
    // void f(A o) dans B
    // void f(B o) dans B
    // void f(B o) dans B
    // void f(B o) dans B

    // Exercice n°10 - Ajout de f(B o) dans A

    // C'est une surcharge. Le fragment affichera:

    // void f(A o) dans A
    // void f(A o) dans A
    // void f(A o) dans A
    // void f(A o) dans B
    // void f(A o) dans B
    // void f(A o) dans B
    // void f(B o) dans B
    // void f(B o) dans B
    // void f(B o) dans B

    // Exercice n°11 - instanceof

    // Le fragment affichera:
    // true
    // true
    // true
    // false
    // true
    // true

    // Exercice n°12 - Méthode contains

    // La méthode `contains(Rectangle)` devrait être redéfinie dans SlantedRectangle pour tenir compte de l'angle. Les cas non couverts:
    // - Un Rectangle standard contient-il un SlantedRectangle?
    // - Un SlantedRectangle contient-il un Rectangle standard?

    // Après ajout de contains(SlantedRectangle), il reste le cas où on essaie de vérifier si un Rectangle standard contient un SlantedRectangle (ou vice versa) avec des angles différents.

    //  Exercice n°13 - Fragment de programme

    // Le fragment affichera:

    // C
    // C
    // C
    // D
    // D
    // D

    // Exercices sur les Interfaces

    // Exercice n°1 - Classe Disque

    public class Disque {
        private Point center;
        private double radius;

        public Disque(Point center, double radius) {
            this.center = center;
            this.radius = radius;
        }

        public Disque(double x, double y, double radius) {
            this.center = new Point(x, y);
            this.radius = radius;
        }

        public void translate(double dx, double dy) {
            center.translate(dx, dy);
        }

        public double surface() {
            return Math.PI * radius * radius;
        }

        public boolean contains(Point p) {
            return Math.sqrt(Math.pow(p.getX() - center.getX(), 2) + 
                            Math.pow(p.getY() - center.getY(), 2)) <= radius;
        }
    }

    // Exercice n°2 - Interface Figure

    public interface Figure {
        double surface();
        void translate(double dx, double dy);
        boolean contains(Point p);
    }

    //Exercice n°3 - Dessin implémentant Figure

    // public class Dessin implements Figure
    // {
    //     private Figure[] figures;
    //     private int count;
    //     public Dessin(int capacity) {
    //         figures = new Figure[capacity];
    //         count = 0;
    //     }

    //     public void add(Figure f) {
    //         if (count < figures.length) {
    //             figures[count++] = f;
    //         }
    //     }

    //     @Override
    //     public double surface() {
    //         double total = 0;
    //         for (int i = 0; i < count; i++) {
    //             total += figures[i].surface();
    //         }
    //         return total;
    //     }

    //     @Override
    //     public void translate(double dx, double dy) {
    //         for (int i = 0; i < count; i++) {
    //             figures[i].translate(dx, dy);
    //         }
    //     }

    //     @Override
    //     public boolean contains(Point p) {
    //         for (int i = 0; i < count; i++) {
    //             if (figures[i].contains(p)) {
    //                 return true;
    //             }
    //         }
    //         return false;
    //     }
    // }

    // Exercices sur les Expressions Arithmétiques (Page 6)

    // Exercice n°4 - Interface ArithExpr

    public interface ArithExpr {
        double eval();
        public String prefix();
        public String suffix();
    }

    // Exercice n°5 - Classe Constant

    public class Constant implements ArithExpr {
        private double value;

        public Constant(double value) {
            this.value = value;
        }
        @Override
        public String prefix() {
            return toString();
        }
        @Override
        public String suffix() {
            return toString();
        }
        @Override
        public double eval() {
            return value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }

    //  Exercice n°6 - Classes pour les opérations

    // On peut créer une classe abstraite BinaryOperation:

    public abstract class BinaryOperation implements ArithExpr {
        protected ArithExpr left;
        protected ArithExpr right;

        public BinaryOperation(ArithExpr left, ArithExpr right) {
            this.left = left;
            this.right = right;
        }
        // Dans BinaryOperation
        public String prefix() {
            return getOperator() + " " + left.prefix() + " " + right.prefix();
        }

        public String suffix() {
            return left.suffix() + " " + right.suffix() + " " + getOperator();
        }

        protected abstract String getOperator();

        protected abstract double operate(double a, double b);

        @Override
        public double eval() {
            return operate(left.eval(), right.eval());
        }
    }

    // Classes concrètes
    public class Addition extends BinaryOperation {
        public Addition(ArithExpr left, ArithExpr right) {
            super(left, right);
        }
        @Override
        protected String getOperator() {
            return "+";
        }

        @Override
        protected double operate(double a, double b) {
            return a + b;
        }

        @Override
        public String toString() {
            return "(" + left + " + " + right + ")";
        }
    }
    public class Soustraction extends BinaryOperation {
        public Soustraction(ArithExpr left, ArithExpr right) {
            super(left, right);
        }
        @Override
        protected String getOperator() {
            return "-";
        }

        @Override
        protected double operate(double a, double b) {
            return a - b;
        }

        @Override
        public String toString() {
            return "(" + left + " - " + right + ")";
        }
    }
    public class Multiplication extends BinaryOperation {
        public Multiplication(ArithExpr left, ArithExpr right) {
            super(left, right);
        }
        @Override
        protected String getOperator() {
            return "*";
        }

        @Override
        protected double operate(double a, double b) {
            return a * b;
        }

        @Override
        public String toString() {
            return "(" + left + " * " + right + ")";
        }
    }
    public class Division extends BinaryOperation 
    {
        public Division(ArithExpr left, ArithExpr right) 
        {
            super(left, right);
        }
        @Override
        protected String getOperator() {
            return "/";
        }

        @Override
        protected double operate(double a, double b) {
            return a / b;
        }

        @Override
        public String toString() {
            return "(" + left + " / " + right + ")";
        }
    }

    

    // Soustraction, Multiplication, Division similaires...

    //    Exercice n°7 - Notations préfixe et suffixe

    // // Dans BinaryOperation
    // public String prefix() {
    //     return getOperator() + " " + left.prefix() + " " + right.prefix();
    // }

    // public String suffix() {
    //     return left.suffix() + " " + right.suffix() + " " + getOperator();
    // }

    // protected abstract String getOperator();

    // Dans Addition
    // @Override
    // protected String getOperator() {
    //     return "+";
    // }

    // Dans Constant
    // @Override
    // public String prefix() {
    //     return toString();
    // }

    // @Override
    // public String suffix() {
    //     return toString();
    // }

    //  Exercice n°8 - Ajout de variables

    // On ajoute une classe Variable :

    // public class Variable implements ArithExpr {
    //     private String name;
    //     private java.util.function.Function<String, Double> resolver;// à rechercher

    //     public Variable(String name, java.util.function.Function<String, Double> resolver) {
    //         this.name = name ;
    //         this.resolver = resolver ;
    //     }

    //     @Override
    //     public double eval() {
    //         Double value = resolver.apply(name);
    //         if (value == null) {
    //             throw new RuntimeException("Variable " + name + " non définie");
    //         }
    //         return value;
    //     }

    //     @Override
    //     public String toString() {
    //         return name;
    //     }
    // }

    // * Exercice n°9 - Classe ArithExprParser

    // public abstract class ArithExprParser {
    //     public abstract ArithExpr parse(String s);
    //     public abstract ArithExpr parse(java.io.Reader r);
    // }

    // Exercice n°10 - PrefixParser

    // public class PrefixParser extends ArithExprParser {
    //     @Override
    //     public ArithExpr parse(String s) {
    //         return parse(new java.io.StringReader(s));
    //     }

    //     @Override
    //     public ArithExpr parse(java.io.Reader r) {
    //         java.util.Scanner scanner = new java.util.Scanner(r);
    //         return parse(scanner);
    //     }

    //     private ArithExpr parse(java.util.Scanner scanner) {
    //         if (!scanner.hasNext()) {
    //             throw new RuntimeException("Expression incomplète");
    //         }
            
    //         String token = scanner.next();
    //         switch (token) {
    //             case "+":
    //                 return new Addition(parse(scanner), parse(scanner));
    //             case "-":
    //                 return new Soustraction(parse(scanner), parse(scanner));
    //             case "*":
    //                 return new Multiplication(parse(scanner), parse(scanner));
    //             case "/":
    //                 return new Division(parse(scanner), parse(scanner));
    //             default:
    //                 try {
    //                     return new Constant(Double.parseDouble(token));
    //                 } catch (NumberFormatException e) {
    //                     return new Variable(token, name -> {
    //                         // Ici on pourrait avoir un mécanisme pour résoudre les variables
    //                         throw new RuntimeException("Variable " + name + " non résolue");
    //                     });
    //                 }
    //         }
    //     }
    // }

    // Exercice n°11 - InfixParser (simplifié)

    // public class InfixParser extends ArithExprParser {
    //     @Override
    //     public ArithExpr parse(String s) {
    //         return parseExpression(new java.io.StringReader(s));
    //     }

    //     @Override
    //     public ArithExpr parse(java.io.Reader r) {
    //         return parseExpression(r);
    //     }

    //     private ArithExpr parseExpression(java.io.Reader r) {
    //         // Implémentation simplifiée - en réalité il faudrait un analyseur syntaxique plus complexe
    //         // utilisant peut-être l'algorithme Shunting-yard
    //         throw new UnsupportedOperationException("Implémentation complexe non fournie ici");
    //     }
    // }

}
