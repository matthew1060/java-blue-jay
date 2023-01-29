import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int countPoints = 0;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            prevPt = currPt;
            countPoints++;
        }
        return countPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double averageLen = 0;
        double Perimeter = getPerimeter(s);
        int numPoints = getNumPoints(s);
        averageLen = Perimeter/numPoints;
        return averageLen;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        double largestSide = 0;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (largestSide < currDist){
                largestSide = currDist;
            }
            prevPt = currPt;
        }
        
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0;
        Point prevPt = s.getLastPoint();
        
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            double currX = currPt.getX();
            if (largestX < currX){
                largestX = currX;
            }
            prevPt = currPt;
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeterFile = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);
            if (largestPerimeterFile < currPerimeter){
                largestPerimeterFile = currPerimeter;
            }
            
        }
        return largestPerimeterFile;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File largestPerimeterFile = null;    
        DirectoryResource dr = new DirectoryResource();
        double largestPerimeterFileValue = 0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double currPerimeter = getPerimeter(s);
            if (largestPerimeterFileValue < currPerimeter){
                largestPerimeterFileValue = currPerimeter;
                largestPerimeterFile = f;
            }
            
        }
        
        return largestPerimeterFile.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int countPoints = getNumPoints(s);
        double averageLen = getAverageLength(s);
        double largestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("Number of Points = " + countPoints);
        System.out.println("The average length of the sides are = " + averageLen);
        System.out.println("The largest side = " + largestSide);
        System.out.println("The largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPerimeterFile = getLargestPerimeterMultipleFiles();
        System.out.println("The largest Perimeter of Multiple files is = " + largestPerimeterFile);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        String largestPerimeterFileName = getFileWithLargestPerimeter();
        System.out.println("The File with the Largest Perimeter is = " + largestPerimeterFileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
