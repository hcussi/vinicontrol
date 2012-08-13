package edu.utn.frm.entities.utils;

import java.util.List;

public final class Mat{

        public static final double abs(double x){
                if(x>0.0){
                        return x;
                }else{
                        return -x;
                } // end if
        } // end abs()

        public static final double sinh(double x){
                return (Math.exp(x)-Math.exp(-x))/2;
        } // end sinh()

        public static final double cosh(double x){
                return (Math.exp(x)+Math.exp(-x))/2;
        } // end cosh()

        public static final boolean isPar(int n){
                return (Math.floor(n/2)*2)==n;
        } // end isPar()
        
        public static final boolean isMultiplo(int m,int n){
                return m==(m/n)*n;
        } // end isMultiplo()
        
        public static final double sin(double x, double p){
                return Math.sin(x*p);
        } // end sin()

        public static final double round(double a,int n){
          String s=new String();int c=cantDigitos(a);
          char ss[],sr[];int cant=cantDigEnteros(a)+n+1;

          try{s=new Double(a).toString();
          }catch(NumberFormatException e){
          } // end catch()
          ss=s.toCharArray(); // n�mero original
          sr=new char[cant]; // n�mero truncado

          for(int i=0;i<cant;i++){
            if(i<c){
              if (ss[i]=='.'){sr[i]=ss[i]; // copio los decimales
              }else{ sr[i]=ss[i]; // copio los enteros
              } // end if
            }else{ sr[i]='0';} // end if
          } // end for

          s=s.copyValueOf(sr); // n�mero truncado

          return new Double(s).doubleValue();
        } // end round()

        public static final int cantDigEnteros(double a){
          int n=0;String s=new String();char c[];

          try{s=new String(new Double(a).toString());
          }catch(NumberFormatException e){} // end catch()

          c=new char[s.length()];
          c=s.toCharArray();

          for(int i=0;i<s.length();i++){
            if('.'==c[i]){
              break; // salgo al llegar los decimales
            }else{
              n++; // cuento los enteros
            } // end if
          } // end for
          return n;
        } // end cantDigEnteros()

        public static final int cantDigitos(double a){
          int n=0;String s=new String();char c[];

          try{s=new String(new Double(a).toString());
          }catch(NumberFormatException e){} // end catch()

          c=new char[s.length()];
          c=s.toCharArray();

          for(int i=0;i<s.length();i++){
              n++; // cuento los d�gitos
          } // end for
          return n;
        } // end cantDigitos()

        public static final double getMayor(VectorGraficable v){
          double mayor;
          mayor=v.getElemento(0);

          for(int i=1;i<v.getSize();i++){
            if(v.getElemento(i)>mayor){
              mayor=v.getElemento(i);
            } // end if
          } // end for

          return mayor;
        } // end getMayor()
        
        public static final double getMenor(VectorGraficable v){
          double menor;
          menor=v.getElemento(0);

          for(int i=1;i<v.getSize();i++){
            if(v.getElemento(0)<menor){
              menor=v.getElemento(i);
            } // end if
          } // end for

          return menor;
        } // end getMenor()
        
        public static final double getMayor(List<VectorGraficable> v){
          double mayor;
          mayor=getMayor(v.get(0));

          for(int i=1;i<v.size();i++){
            if(getMayor(v.get(i))>mayor){
              mayor=getMayor(v.get(i));
            } // end if
          } // end for

          return mayor;
        } // end getMayor()
        
        public static final double getMenor(List<VectorGraficable>  v){
          double menor;
          menor=getMenor(v.get(0));

          for(int i=1;i<v.size();i++){
            if(getMenor(v.get(i))<menor){
              menor=getMenor(v.get(i));
            } // end if
          } // end for

          return menor;
        } // end getMayor()
        
} // end class Mat
