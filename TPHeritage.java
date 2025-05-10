public class TPHeritage {
    public static void main(String[] args){  // Correction de "agrs" en "args"
        int exercice = 5; // choisir l'exercice à executer ici
        runExercice(exercice);
    }
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
                break;  // Ajout du break manquant après le case 8
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

    // === CLASSE POINT ===
    static class Point {
        private double x, y;
        public Point(double x, double y) {
            this.x = x; this.y = y;
        }
        public double getX() { return x; }
        public double getY() { return y; }
        public void translate(double dx, double dy) {
            this.x += dx; this.y += dy;
        }
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    // === CLASSE RECTANGLE ===
    static class Rectangle {
        protected Point basGauche;
        protected double largeur, hauteur;

        public Rectangle(Point p, double l, double h) {
            this.basGauche = p;
            this.largeur = l;
            this.hauteur = h;
        }

        public double surface() {
            return largeur * hauteur;
        }

        public void translate(double dx, double dy) {
            basGauche.translate(dx, dy);
        }

        public boolean contains(Point p) {
            return (p.getX() >= basGauche.getX() && p.getX() <= basGauche.getX() + largeur
                 && p.getY() >= basGauche.getY() && p.getY() <= basGauche.getY() + hauteur);
        }

        public boolean contains(Rectangle r) {
            Point coinSupDroit = new Point(r.basGauche.getX() + r.largeur, r.basGauche.getY() + r.hauteur);
            return this.contains(r.basGauche) && this.contains(coinSupDroit);
        }

        public boolean equals(Object o) {
            if (!(o instanceof Rectangle)) return false;  // Correction pour compatibilité JDK < 16
            Rectangle other = (Rectangle) o;  // Cast explicite ajouté
            return this.basGauche.getX() == other.basGauche.getX() &&
                   this.basGauche.getY() == other.basGauche.getY() &&
                   this.largeur == other.largeur && this.hauteur == other.hauteur;
        }

        public void rotate(double angle) {
            System.out.println("Rotation non définie pour Rectangle");
        }

        public String toString() {
            return "Rectangle[" + basGauche + ", l=" + largeur + ", h=" + hauteur + "]";
        }
    }

    // === CLASSE SLANTEDRECTANGLE (héritée) ===
    static class SlantedRectangle extends Rectangle {
        private double angle;

        public SlantedRectangle(Point p, double l, double h) {
            super(p, l, h);
            this.angle = 0;
        }

        @Override
        public void rotate(double angle) {
            this.angle += angle;
        }

        @Override
        public boolean contains(Point p) {
            System.out.println("Test approximatif de contains pour SlantedRectangle");
            return false;
        }

        @Override
        public String toString() {
            return "Slanted" + super.toString() + ", angle=" + angle;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof SlantedRectangle)) return false;  // Correction pour compatibilité JDK < 16
            SlantedRectangle sr = (SlantedRectangle) o;  // Cast explicite ajouté
            return super.equals(sr) && this.angle == sr.angle;
        }
    }

    // === CLASSES POUR MASQUAGE ===
    static class A {
        void f(A o) { System.out.println("void f(A o) dans A"); }
        void f(B o) { System.out.println("void f(B o) dans A"); }
    }

    static class B extends A {
        @Override
        void f(A o) { System.out.println("void f(A o) dans B"); }
        @Override
        void f(B o) { System.out.println("void f(B o) dans B"); }
    }

    static class C {
        char ch = 'C';
        char getCh() { return ch; }
    }

    static class D extends C {
        char ch = 'D';
        char getCh() { return ch; }
    }

    // === EXERCICES ===
    public static void exercice1() {
        SlantedRectangle s = new SlantedRectangle(new Point(0,0), 2, 3);
        System.out.println(s);
    }

    public static void exercice2() {
        SlantedRectangle s = new SlantedRectangle(new Point(1,1), 2, 3);
        s.rotate(Math.PI/4);
        System.out.println(s);
    }

    public static void exercice3() {
        // Exercice 3 :
        // La classe SlantedRectangle hérite des méthodes suivantes de Rectangle :
        // - surface()
        // - translate(double, double)
        // - contains(Point)
        // - contains(Rectangle)
        // - equals(Object)
        // - toString()
        // - rotate(double)
        // Les méthodes redéfinies dans SlantedRectangle sont :
        // - rotate(double) : pour gérer l'angle d'inclinaison
        // - contains(Point) : retourne false par approximation
        // - toString() : ajoute l'angle à l'affichage
        // - equals(Object) : compare aussi l'angle

        SlantedRectangle s1 = new SlantedRectangle(new Point(0,0), 2, 3);
        SlantedRectangle s2 = new SlantedRectangle(new Point(0,0), 2, 3);
        System.out.println("surface: " + s1.surface());
        System.out.println("contains: " + s1.contains(new Point(1,1)));
        System.out.println("equals: " + s1.equals(s2));
    }

    public static void exercice4() {
        // Exercice 4 :
        // Test du comportement des appels de méthode selon le type déclaré et le type réel des objets.
        // Ce test illustre le polymorphisme :
        // - r est un Rectangle classique
        // - t est de type Rectangle mais instance de SlantedRectangle (polymorphisme)
        // - s est un SlantedRectangle
        // Les méthodes appelées dépendent du type réel à l'exécution pour les méthodes redéfinies (ex: rotate, contains).

        Point p = new Point(1,2);
        Rectangle r = new Rectangle(p, 2, 3);
        Rectangle t = new SlantedRectangle(p, 2, 3);
        SlantedRectangle s = new SlantedRectangle(p, 2, 3);

        System.out.println(r.surface());
        r.rotate(2);
        System.out.println(r.contains(p));

        System.out.println(t.surface());
        t.rotate(2);
        System.out.println(t.contains(p));

        System.out.println(s.surface());
        s.rotate(2);
        System.out.println(s.contains(p));
    }

    public static void exercice5() {
        // Exercice 5 :
        // Peut-on ajouter des SlantedRectangle dans Dessin ? OUI : ils héritent de Rectangle, donc polymorphisme.
        // Les méthodes surface, contains, hull fonctionnent-elles ?
        // - surface() : fonctionne si SlantedRectangle.surface() reste correcte, mais géométriquement l'aire pourrait différer.
        // - contains(Point) : la méthode de SlantedRectangle est approximative, donc Dessin.contains(p) sera approximatif.
        // - hull() : si basé sur les coordonnées droites du rectangle, ignore l'inclinaison. Résultat imprécis pour SlantedRectangle.

        System.out.println("Dessin peut contenir des SlantedRectangle car ils héritent de Rectangle.");
        System.out.println("Mais les méthodes comme contains peuvent nécessiter une redéfinition.");
    }

    public static void exercice6() {
        Rectangle r = new Rectangle(new Point(1, 1), 2, 2);
        System.out.println(r.toString());
        SlantedRectangle sr = new SlantedRectangle(new Point(2, 2), 3, 3);
        System.out.println(sr.toString());
    }

    public static void exercice7() {
        SlantedRectangle sr1 = new SlantedRectangle(new Point(0, 0), 2, 2);
        SlantedRectangle sr2 = new SlantedRectangle(new Point(0, 0), 2, 2);
        System.out.println(sr1.equals(sr2));
    }

    public static void exercice8() {
        // Exercice 8 :
        // Ce test illustre le polymorphisme avec redéfinition de méthode f(A o)
        // La méthode appelée dépend du type réel de l'objet appelant (a, ab, b)
        // Le paramètre b est toujours vu comme un A à la compilation, donc c'est toujours f(A)
        // Résultat attendu :
        // A appelle sa propre méthode f(A o)
        // B redéfinit f(A o), donc c'est sa version qui est appelée pour ab et b
        // Affichage attendu :
        // void f(A o) dans A
        // void f(A o) dans A
        // void f(A o) dans A
        // void f(A o) dans B
        // void f(A o) dans B
        // void f(A o) dans B
        // void f(A o) dans B
        // void f(A o) dans B
        // void f(A o) dans B

        A a = new A();
        A ab = new B();
        B b = new B();

        a.f(a); a.f(ab); a.f(b);
        ab.f(a); ab.f(ab); ab.f(b);
        b.f(a); b.f(ab); b.f(b);
    }

    public static void exercice9() { exercice8(); } // surcharge f(B o) dans B
    public static void exercice10() { exercice8(); } // surcharge f(B o) dans A

    public static void exercice11() {
        // Exercice 11 :
        // Test de l'opérateur instanceof avec classes A et B.
        // instanceof vérifie si un objet est instance directe ou héritée d'une classe.
        // Résultats attendus :
        // a instanceof A => true (a est A)
        // ab instanceof A => true (ab est un B, donc aussi un A)
        // b instanceof A => true (héritage)
        // a instanceof B => false (a n'est pas un B)
        // ab instanceof B => true (réellement un B)
        // b instanceof B => true

        A a = new A();
        A ab = new B();
        B b = new B();

        System.out.println(a instanceof A);
        System.out.println(ab instanceof A);
        System.out.println(b instanceof A);
        System.out.println(a instanceof B);
        System.out.println(ab instanceof B);
        System.out.println(b instanceof B);
    }

    public static void exercice12() {
        // Exercice 12 :
        // Rectangle a une méthode contains(Rectangle). Faut-il la redéfinir dans SlantedRectangle ?
        // - Si elle n'est pas redéfinie, le comportement sera celui d'un rectangle droit, ce qui est faux pour un rectangle incliné.
        // - On peut ajouter contains(SlantedRectangle), mais cela n'est appelé que si le paramètre est typé exactement.
        // => Aucun des deux cas ne couvre les appels polymorphes (upcasting). Pour une couverture correcte, il faut une gestion par instanceof.

        Rectangle r = new Rectangle(new Point(0, 0), 4, 4);
        SlantedRectangle sr = new SlantedRectangle(new Point(1, 1), 2, 2);
        System.out.println("r.contains(sr) ? " + r.contains(sr));  // Correction: utiliser sr directement
    }

    public static void exercice13() {
        // Exercice 13 :
        // Montre la différence entre masquage d'attribut et redéfinition de méthode.
        // En Java, les attributs ne sont pas dynamiques : l'accès c.ch utilise toujours le type déclaré (C).
        // Les méthodes, elles, sont dynamiques : cd.getCh() utilise la méthode de D.
        // Résultats attendus :
        // c.ch => 'C' (champ de C)
        // c.getCh() => 'C' (méthode de C)
        // cd.ch => 'C' (champ de C car cd est typé C)
        // cd.getCh() => 'D' (méthode de D car instance réelle = D)
        // d.ch => 'D' (champ de D)
        // d.getCh() => 'D' (méthode de D)

        C c = new C();
        C cd = new D();
        D d = new D();

        System.out.println(c.ch);
        System.out.println(c.getCh());
        System.out.println(cd.ch);
        System.out.println(cd.getCh());
        System.out.println(d.ch);
        System.out.println(d.getCh());
    }
    
    // === CLASSE DESSIN ===
    static class Dessin {
        private Rectangle[] rectangles;
        private int nbRectangles;
        private static final int MAX_RECTANGLES = 100;
        
        public Dessin() {
            rectangles = new Rectangle[MAX_RECTANGLES];
            nbRectangles = 0;
        }
        
        public boolean add(Rectangle r) {
            if (nbRectangles >= MAX_RECTANGLES) {
                return false;
            }
            rectangles[nbRectangles++] = r;
            return true;
        }
        
        public double surface() {
            double somme = 0;
            for (int i = 0; i < nbRectangles; i++) {
                somme += rectangles[i].surface();
            }
            return somme;
        }
        
        public boolean contains(Point p) {
            for (int i = 0; i < nbRectangles; i++) {
                if (rectangles[i].contains(p)) {
                    return true;
                }
            }
            return false;
        }
        
        public Rectangle hull() {
            if (nbRectangles == 0) {
                return null;
            }
            
            // Recherche des coordonnées min et max
            double minX = Double.POSITIVE_INFINITY;
            double minY = Double.POSITIVE_INFINITY;
            double maxX = Double.NEGATIVE_INFINITY;
            double maxY = Double.NEGATIVE_INFINITY;
            
            for (int i = 0; i < nbRectangles; i++) {
                Rectangle r = rectangles[i];
                double rMinX = r.basGauche.getX();
                double rMinY = r.basGauche.getY();
                double rMaxX = rMinX + r.largeur;
                double rMaxY = rMinY + r.hauteur;
                
                if (rMinX < minX) minX = rMinX;
                if (rMinY < minY) minY = rMinY;
                if (rMaxX > maxX) maxX = rMaxX;
                if (rMaxY > maxY) maxY = rMaxY;
            }
            
            // Création du rectangle englobant
            Point basGauche = new Point(minX, minY);
            double largeur = maxX - minX;
            double hauteur = maxY - minY;
            
            return new Rectangle(basGauche, largeur, hauteur);
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Dessin avec " + nbRectangles + " rectangles:\n");
            for (int i = 0; i < nbRectangles; i++) {
                sb.append("  ").append(i+1).append(": ").append(rectangles[i]).append("\n");
            }
            return sb.toString();
        }
    }
    
    public static void exercice14() {
        // Test de la classe Dessin avec des rectangles simples
        Dessin dessin = new Dessin();
        dessin.add(new Rectangle(new Point(0, 0), 2, 2));
        dessin.add(new Rectangle(new Point(3, 3), 1, 1));
        System.out.println(dessin);
        System.out.println("Surface totale: " + dessin.surface());
        System.out.println("Contient (1,1): " + dessin.contains(new Point(1, 1)));
        System.out.println("Contient (2.5,2.5): " + dessin.contains(new Point(2.5, 2.5)));
        System.out.println("Rectangle englobant: " + dessin.hull());
    }
    
    public static void exercice15() {
        // Test de polymorphisme avec Dessin et SlantedRectangle
        Dessin dessin = new Dessin();
        dessin.add(new Rectangle(new Point(0, 0), 2, 2));
        dessin.add(new SlantedRectangle(new Point(3, 3), 1, 1));
        System.out.println(dessin);
        System.out.println("Surface totale: " + dessin.surface());
        
        // Test du comportement de contains avec SlantedRectangle
        System.out.println("Contient (3.5,3.5) - point dans SlantedRectangle: " + 
                           dessin.contains(new Point(3.5, 3.5)));
        
        // Test du rectangle englobant qui ignore l'angle du SlantedRectangle
        System.out.println("Rectangle englobant: " + dessin.hull());
    }
    
    public static void exercice16() {
        // Test du cast et du traitement spécifique pour SlantedRectangle
        Dessin dessin = new Dessin();
        dessin.add(new Rectangle(new Point(0, 0), 3, 2));
        dessin.add(new SlantedRectangle(new Point(4, 1), 2, 2));
        
        // Parcourir les rectangles et traiter différemment selon leur type réel
        for (int i = 0; i < 2; i++) {
            Rectangle r = dessin.rectangles[i];
            System.out.println("Rectangle " + (i+1) + ": " + r);
            
            if (r instanceof SlantedRectangle) {
                SlantedRectangle sr = (SlantedRectangle) r;
                sr.rotate(Math.PI/4);  // Rotation spécifique aux SlantedRectangle
                System.out.println("Après rotation: " + sr);
            }
        }
    }
}