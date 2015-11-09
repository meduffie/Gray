public class Food extends Item{
    int hpa;
    Food(String name, String desc, int hpA){ //Name, Description, Health Points added
        super(name,desc);
        hpa = hpA;
    }
    public int getHPA(){ //Get Health Points Added
        return hpa;
    }
    public void setHPA(int x){ //Set Health Points Added
        hpa = x;
    }
}