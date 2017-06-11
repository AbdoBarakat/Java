package facebook2;
 
import java.util.Scanner;
 
public class FaceBook2 {
    static String data[][]=new String[100][4];
    //firstName , LastName , Email , Password
    static int size=0;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        boolean run=true;
        while(run){
            System.out.println("________________________");
            System.out.println("1 : to Sign in\n2 : to sign up\n3 : to quit");
            char a=sc.next().charAt(0);
            switch(a){
                case'3':
                    run =false;
                    break;
                case '2':
                    boolean run1=true;
                    while(run1){
                        System.out.println("enter fisrt name : ");
                        String fname=sc.next();
                        System.out.println("enter last name : ");
                        String lname=sc.next();
                        System.out.println("enter email : ");
                        String email=sc.next();
                        System.out.println("enter password : ");
                        String pass=sc.next();
                        if(signUp(fname,lname,email,pass)){
                            run1=false;
                        }else{
                            System.out.println("the email is already used\nto try again enter 1\nto return to menu enter 2");
                            char q=sc.next().charAt(0);
                            if(q!='1')run1=false;    
                        }
                    }
                    break;
                case '1':
                    boolean run2=true;
                    while(run2){
                        System.out.println("Enter your email");
                        String email=sc.next();
                        System.out.println("Enter your password");
                        String pass=sc.next();
                        int o=signIn(email,pass);
                        if(o<0){
                            System.out.println("The email or password is incorrect\nTo try again enter '1'\nTo return to menu enter '2'");
                            char q=sc.next().charAt(0);
                            if(q!='1')run2=false;
                        }else{
                            System.out.println("*Welcome "+data[o][0]+" "+data[o][1]);
                            boolean run3=true;
                            while(run3){
                                System.out.println("1 : to search\n2 : to sign out\n3 : to delete your account");
                                char s=sc.next().charAt(0);
                                switch(s){
                                    case '2':
                                        run3=false;
                                        run2=false;
                                        break;
                                    case '3':
                                        delete(o);
                                        System.out.println("Your account is successfully deleted\n     __________________________");
                                        run3=false;
                                        run2=false;
                                        break;
                                    case '1':
                                        boolean run4=true;
                                        while(run4){
                                            System.out.println("1 : to search by user name\n2 : to search by email\n3 : to return back ");
                                            char p=sc.next().charAt(0);
                                            switch(p){
                                                case '3':
                                                    run4=false;
                                                    break;
                                                case'2':
                                                    System.out.println("Enter the email");
                                                    int s1=searchEmail(sc.next());
                                                    if(s1<0)System.out.println("Not found");
                                                    else System.out.println(data[s1][0]+" "+data[s1][1]+" "+data[s1][2]);
                                                    break;
                                                case '1':
                                                    System.out.println("Enter first name(enter '-' if you don't it) :");
                                                    String fname=sc.next();
                                                    System.out.println("enter last name(enter '-' if you don't it) :");
                                                    String lname=sc.next();
                                                    int q=searchName(fname,lname);
                                                    if(q==0)System.out.println("not found");
                                                    break;
                                            }
                                        }
                                }
                            }
                        }
                    }
            }
        }
    }
    public static int signIn(String email,String pass){
        int x=searchEmail(email);
        if(x<0)return x;
        else if(data[x][3].equals(pass))return x;
        else return -1;
    }//________________________________________________________________
    public static boolean signUp(String fname,String lname,String email,String pass ){
        if(searchEmail(email)>=0){
            return false;
        }else{
            data[size][0]=fname;
            data[size][1]=lname;
            data[size][2]=email;
            data[size][3]=pass;
            size++;
            return true;
        }
    }
    //_____________________________________________________________
    public static int searchEmail(String email){
        for(int i=0;i<size;i++){
            if(data[i][2].equals(email)){
                return i;
            }
        }
        return -1;
    }
    //___________________________________________________________
    public static int searchFirstName(String fname){
        for(int i=0;i<size;i++){
            if(data[i][0].equalsIgnoreCase(fname)){
                return i;
            }
        }
        return -1;
    }
    //___________________________________________________________
    public static int searchLastName(String lname){
        for(int i=0;i<size;i++){
            if(data[i][1].equalsIgnoreCase(lname)){
                return i;
            }
        }
        return -1;
    }
    //___________________________________________________________
    public static int searchName(String fname ,String lname){
        int a=-1;
        for(int i=0;i<size;i++){
            if(data[i][0].equalsIgnoreCase(fname) || data[i][1].equalsIgnoreCase(lname)){
                System.out.println(data[i][0]+" "+ data[i][1]+" "+data[i][2]);
                a=1;
            }
        }
        return a;
    }
    //___________________________________________________________
    public static void delete(int index){
        for(int i=index;i<size;i++){
            data[i][0]=data[i+1][0];
            data[i][1]=data[i+1][1];
            data[i][2]=data[i+1][2];
            data[i][3]=data[i+1][3];
        }
        size--;
    }
   
}