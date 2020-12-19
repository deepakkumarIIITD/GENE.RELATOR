package sample;

import java.io.*;
import java.util.ArrayList;

public class result {
    private String name;
    private File FILELOC1;
    private File FILELOC2;
    private String completeresult = "";
    private String similaritypositionsdna = "";
    private String nonsimilaritypositionsdna = "";
    private String proteinsim ="";
    private String proteinnonsim = "";
    private String rnasim ="";
    private String rnanonsim = "";
    public boolean tf = true;
    public boolean viewd = false;
    result(String FILE1 , String FILE2 , File FILELOC1 , File FILELOC2) throws IOException {
        this.name = (FILE1 + " AND " + FILE2);
        this.FILELOC1 = FILELOC1;
        this.FILELOC2 = FILELOC2;
    }

    public String [] getFirstFileData() throws IOException {
        String organism = "";
        String sequence = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.FILELOC1));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.charAt(0) == '>') {
                    organism = line.substring(1, line.length());
                } else {
                    sequence = sequence.concat(line);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File couldn't be loaded." + exception.getMessage());
        }
        return new String [] {organism, sequence};
    }

    public String [] getSecondFileData() throws IOException {
        String organism = "";
        String sequence = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.FILELOC2));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.charAt(0) == '>') {
                    organism = line.substring(1, line.length());
                } else {
                    sequence = sequence.concat(line);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File couldn't be loaded." + exception.getMessage());
        }
        return new String [] {organism, sequence};
    }

    public void findSimilarity() throws IOException {
        String [] firstOrganism = getFirstFileData();
        String [] secondOrganism = getSecondFileData();
        String rna1 = DNAtoRNA(firstOrganism[1]);
        String rna2 = DNAtoRNA(secondOrganism[1]);
        ArrayList<String> protein1 = new ArrayList<>();
        protein1 = RNAtoProtein(rna1);
        ArrayList<String> protein2 = new ArrayList<>();
        protein2 = RNAtoProtein(rna2);
        this.completeresult += ("FILE 1 : " + firstOrganism[0] + "\n");
        this.completeresult += ("FILE 2 : " + secondOrganism[0] + "\n");
        double gcfile1 = gcContent(firstOrganism[1]);
        double gcfile2 = gcContent(secondOrganism[1]);
        this.completeresult += ("GC CONTENT FILE 1 : "+gcfile1+"%\n");
        this.completeresult += ("GC CONTENT FILE 2 : "+gcfile2+"%\n");
        this.completeresult += ("AT BY GC RATIO FILE 1 : "+ATbyGCratio(firstOrganism[1])+"\n");
        this.completeresult += ("AT BY GC RATIO FILE 2 : "+ATbyGCratio(secondOrganism[1])+"\n");
        this.completeresult += ("DENSITY RATIO OF FILE1 TO FILE2 : "+(gcfile1/gcfile2)+"\n");
        this.completeresult += ("DNA SIMILARITY BETWEEN ORGANISMS : "+getSimilarity(firstOrganism[1],secondOrganism[1])+"%\n");
        this.completeresult += ("RNA SIMILARITY BETWEEN ORGANISMS : "+RNAgetSimilarity(rna1,rna2)+"%\n");
        double proteinsimlar = proteinsimilarity(protein1, protein2);
        if(tf) {
            this.completeresult += ("PROTEIN SIMILARITY BETWEEN ORGANISMS : " + proteinsimlar + "%\n");
        }
        if(!tf){
            this.completeresult += ("PROTEIN SYNTHESIS FOR ONE OF THE GENE WAS NOT POSSIBLE\n");
        }
        this.completeresult += ("BASES AND NUCLEOTIDES ARE FROM FIRST FILE\n");
        this.completeresult += ("DNA SIMILARITY : \n");
        this.completeresult += similaritypositionsdna;
        this.completeresult += ("DNA DISIMILARITY : \n");
        this.completeresult += nonsimilaritypositionsdna;
        this.completeresult += ("RNA SIMILARITY : \n");
        this.completeresult += rnasim;
        this.completeresult += ("RNA DISIMILARITY : \n");
        this.completeresult += rnanonsim;
        if(tf){
            this.completeresult += ("PROTEIN SIMILARITY : \n");
            this.completeresult += proteinsim;
            this.completeresult += ("PROTEIN DISIMILARITY : \n");
            this.completeresult += proteinnonsim;
        }
    }

    public double gcContent(String DNA){
        double g = 0;
        double c = 0;
        for(int i = 0 ; i < DNA.length() ; i++){
            if(DNA.charAt(i) == 'G'){
                g++;
            }else if(DNA.charAt(i) == 'C'){
                c++;
            }
        }
        return(((g+c)/(DNA.length()))*100);
    }

    public double ATbyGCratio(String DNA){
        double a = 0;
        double t = 0;
        double g = 0;
        double c = 0;
        for(int i = 0 ; i < DNA.length() ; i++){
            if(DNA.charAt(i) == 'A'){
                a++;
            }else if(DNA.charAt(i) == 'T'){
                t++;
            }else if(DNA.charAt(i) == 'G'){
                g++;
            }else if(DNA.charAt(i) == 'C'){
                c++;
            }
        }
        return((a+t)/(g+c));
    }

    public double getSimilarity(String first, String second) {
        double similarity = 0;
        double total;
        double count = 0;
        if (first == null || second == null) {
            return -1;
        }
        if (first.length() > second.length()) {
            total = second.length();
        } else {
            total = first.length();
        }
        for (int i = 0 ; i < total ; i++) {
            if (first.charAt(i) == second.charAt(i)) {
                count++;
                this.similaritypositionsdna += ("POS : "+Integer.toString(i)+ " BASE : "+Character.toString(first.charAt(i))+"\n");
            }else{
                this.nonsimilaritypositionsdna += ("POS : "+Integer.toString(i)+ " BASE : "+Character.toString(first.charAt(i))+"\n");
            }
        }
        similarity = (count / total) * 100;
        return similarity;
    }

    public double RNAgetSimilarity(String first, String second) {
        double similarity = 0;
        double total;
        double count = 0;
        if (first == null || second == null) {
            return -1;
        }
        if (first.length() > second.length()) {
            total = second.length();
        } else {
            total = first.length();
        }
        for (int i = 0 ; i < total ; i++) {
            if (first.charAt(i) == second.charAt(i)) {
                count++;
                this.rnasim += ("POS : "+Integer.toString(i)+ " BASE : "+Character.toString(first.charAt(i))+"\n");
            }else{
                this.rnanonsim += ("POS : "+Integer.toString(i)+ " BASE : "+Character.toString(first.charAt(i))+"\n");
            }
        }

        similarity = (count / total) * 100;

        return similarity;
    }

    public double proteinsimilarity(ArrayList<String> p1 , ArrayList<String> p2){
        double similarity = 0;
        double total;
        double count = 0;

        if ((p1.size() == 0) || (p2.size() == 0)) {
            tf = false;
            return -1;
        }

        if (p1.size() > p2.size()) {
            total = p2.size();
        } else {
            total = p1.size();
        }
        for(int i = 0 ; i < total ; i++){
            if(p1.get(i).equals(p2.get(i))){
                count++;
                this.proteinsim += ("POS : "+Integer.toString(i)+ " AMINO ACID : "+p1.get(i)+"\n");
            }else{
                this.proteinnonsim += ("POS : "+Integer.toString(i)+ " AMINO ACID : "+p1.get(i)+"\n");
            }
        }
        similarity = (count / total) * 100;

        return similarity;
    }

    public String DNAtoRNA(String DNA){
        String rna = "";
        for(int i = 0 ; i < DNA.length() ; i++){
            if(DNA.charAt(i) == 'A'){
                rna += "U";
            }else if(DNA.charAt(i) == 'T'){
                rna += "A";
            }else if(DNA.charAt(i) == 'G'){
                rna += "C";
            }else if(DNA.charAt(i) == 'C'){
                rna += "G";
            }
        }
        return rna;
    }

    public ArrayList RNAtoProtein(String RNA){
        ArrayList<String> protein = new ArrayList<>();
        int length = RNA.length();
        length = length - (length%3);
        for(int i = 0 ; i < length ; i+=3){
            String mrna = RNA.substring(i,i+=3);
            String rcod = database.codontable.get(mrna);
            if(rcod.equals("@@@")){
                break;
            }
            protein.add(rcod);
        }
        return protein;
    }

    public String getName(){
        return name;
    }
    public void setCompleteresult(){
        completeresult="";
    }
    public String getCompleteresult(){
        return completeresult;
    }
}