package sample;
import java.util.ArrayList;
import java.util.HashMap;
public class database {
    public static ArrayList<result> AllResults = new ArrayList<>();
    public static HashMap<String,String> codontable = new HashMap<>();
    public static void codonmaker(){
        codontable.put("UUU","PHE");
        codontable.put("UUC","PHE");
        codontable.put("UUA","LEU");
        codontable.put("UUG","LEU");
        codontable.put("CUU","LEU");
        codontable.put("CUC","LEU");
        codontable.put("CUA","LEU");
        codontable.put("CUG","LEU");
        codontable.put("AUU","LLE");
        codontable.put("AUC","LLE");
        codontable.put("AUA","LLE");
        codontable.put("AUG","MET");
        codontable.put("GUU","VAL");
        codontable.put("GUC","VAL");
        codontable.put("GUA","VAL");
        codontable.put("GUG","VAL");
        codontable.put("UCU","SER");
        codontable.put("UCC","SER");
        codontable.put("UCA","SER");
        codontable.put("UCG","SER");
        codontable.put("CCU","PRO");
        codontable.put("CCC","PRO");
        codontable.put("CCA","PRO");
        codontable.put("CCG","PRO");
        codontable.put("ACU","THR");
        codontable.put("ACC","THR");
        codontable.put("ACA","THR");
        codontable.put("ACG","THR");
        codontable.put("GCU","ALA");
        codontable.put("GCC","ALA");
        codontable.put("GCA","ALA");
        codontable.put("GCG","ALA");
        codontable.put("UAU","TYR");
        codontable.put("UAC","TYR");
        codontable.put("UAA","@@@");
        codontable.put("UAG","@@@");
        codontable.put("CAU","HIS");
        codontable.put("CAC","HIS");
        codontable.put("CAA","GLN");
        codontable.put("CAG","GLN");
        codontable.put("AAU","ASN");
        codontable.put("AAC","ASN");
        codontable.put("AAA","LYS");
        codontable.put("AAG","LYS");
        codontable.put("GAU","ASP");
        codontable.put("GAC","ASP");
        codontable.put("GAA","GLU");
        codontable.put("GAG","GLU");
        codontable.put("UGU","CYS");
        codontable.put("UGC","CYS");
        codontable.put("UGA","@@@");
        codontable.put("UGG","TRP");
        codontable.put("CGU","ARG");
        codontable.put("CGC","ARG");
        codontable.put("CGA","ARG");
        codontable.put("CGG","ARG");
        codontable.put("AGU","SER");
        codontable.put("AGC","SER");
        codontable.put("AGA","ARG");
        codontable.put("AGG","ARG");
        codontable.put("GGU","GLY");
        codontable.put("GGC","GLY");
        codontable.put("GGA","GLY");
        codontable.put("GGG","GLY");
    }
}
