package edu.utn.frm.entities.utils;

import java.io.*;

public class VectorGraf implements VectorGraficable{
    private int size;
    private double v[];
    
//----------------CONSTRUCTORES--------------------------------------------
    public VectorGraf(){} // end VectorGraf()
    
    public VectorGraf(int size){
        
        v=new double[size];
        this.size=size;
        for(int i=0;i<this.size;i++){
            v[i]=0.0;
        } // end for
    } // end VectorGraf() (inicializo los elementos a 0)
    
    public VectorGraf(int size,double vv[]){
        this.size=size;
        
        v=new double[this.size];
        
        for(int i=0;i<this.size;i++){
            try{v[i]=vv[i];}catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }catch(NullPointerException e){
                e.printStackTrace();
            }
            
        } // end for
        
    } // end VectorGraf() (inicializo los elementos a los valores del arreglo)
    
    public VectorGraf(VectorGraficable t){
        
        size= t.getSize();
        v=new double[size];
        for(int i=0;i<size;i++){
            try{
                v[i]=t.getElemento(i);
            }catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            } catch(NullPointerException e){
                e.printStackTrace();
            }
        } // end for
    } // end VectorGraf()  (constructor de copia)
    
//----------------M�TODOS---------------------------------------------------
    public void setSize(int size){this.size=size;} // end setSize()
    
    public void setElemento(int posicion,double valor){
        try{this.v[posicion]=valor;}catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        
        
    } // end setElemento()
    
    public int getSize(){return this.size;} //end getSize()
    
    public double getElemento(int posicion){
        double aux=0;
        try{aux=this.v[posicion];}catch(ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return aux;
    } // end getElemento()
    
    public void intercambiarElemento(int p1,int p2){
        double aux;
        
        aux=this.v[p1];
        this.v[p1]=this.v[p2];
        this.v[p2]=aux;
    } // end intercambiarElemento()
    
//--------------OPERACIONES------------------------------------------------------------
    
    public VectorGraf sumar(VectorGraf A){
        
        VectorGraf AUX=new VectorGraf(A.size);
        
        for(int i=0;i<A.size;i++){
            AUX.v[i]=A.v[i]+this.v[i];
        } // end for
        
        return AUX;
        
    } // end sumar() (suma dos vectores)
    
    public VectorGraf restar(VectorGraf A){
        
        VectorGraf AUX=new VectorGraf(A.size);
        
        for(int i=0;i<A.size;i++){
            AUX.v[i]=A.v[i]-this.v[i];
        } // end for
        
        return AUX;
        
    } // end restar() (resta dos vectores)
    
    
    public VectorGraf multiplicar(VectorGraf A){
        
        VectorGraf AUX=new VectorGraf(this.size);
        
        for(int i=0;i< this.size;i++){
            AUX.v[i]=A.v[i]* this.v[i];
        } // end for
        
        return AUX;
        
    } // end multiplicar() (multiplica dos vectores)
    
    public void dividir(double alfa){
        
        for(int i=0;i< this.size;i++){
            this.v[i]/=alfa;
        } // end for
        
    } // end dividir()
    
    public void multiplicar(double alfa){
        
        for(int i=0;i< this.size;i++){
            this.v[i]*=alfa;
        } // end for
        
    } // end multiplicar()
    
    public void sumar(double alfa){
        
        for(int i=0;i< this.size;i++){
            this.v[i]+=alfa;
        } // end for
        
    } // end sumar()
    
    
    public VectorGraf multiplicar2(double alfa){
        VectorGraf aux=new VectorGraf(this.size);
        
        for(int i=0;i< this.size;i++){
            aux.v[i]=this.v[i]*alfa;
        } // end for
        return aux;
    } // end multiplicar2() (multiplica dos vectores)
    
    
    public void mostrar(String v){
        
        System.out.println("<<<<Vector "+v+">>>>");
        String total;
        
        total=new String("");
        
        for(int i=0;i< this.size;i++){
            try{total=total.concat("v["+i+"]="+this.v[i]+"  ");
            }catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }catch(NullPointerException e){
                e.printStackTrace();
            }
            
            if(i==(this.size/2-1)){
                total=total.concat("\n");
            } // end if
        } // end for
        System.out.println(total);
        
    } // end mostrar()
    
    public VectorGraf ingresar(){
        
        VectorGraf v;int n;
        DataInputStream datos=new DataInputStream(System.in);
        String s=new String();
        
        System.out.println("<<<<<Ingresar Vector>>>>");
        System.out.println("ingresar cantidad de elementos:");
        try{s=datos.readLine();}catch(IOException e){}
        n=(new Integer(s)).intValue();
        v=new VectorGraf(n);
        double aux;
        for(int i=0;i<v.getSize();i++){
            System.out.println("b["+i+"]=");
            try{s=datos.readLine();}catch(IOException e){}
            aux=(new Double(s)).doubleValue();
            v.setElemento(i,aux);
        } // end for
        return v;
    } // end ingresar()
    
    public String toString(String separador){
        
        String total=new String("");
        
        for(int i=0;i< this.size;i++){
            try{total=total.concat("v["+i+"]="+Mat.round(this.v[i],6)+separador);
            }catch(ArrayIndexOutOfBoundsException e){
                e.printStackTrace();
            }catch(NullPointerException e){
                e.printStackTrace();
            } // end catch()
        } // end for
        
        return total;
    } // end toString()
    
} // end class VectorGraf
