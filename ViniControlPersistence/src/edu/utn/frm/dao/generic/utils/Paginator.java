package edu.utn.frm.dao.generic.utils;

import edu.utn.frm.dao.generic.hql.HqlBuilder;

public class Paginator{
    
    private int pageNumber;//numero de pagina que muestra desde la cero en adelante
    private int pageSize;//cantidad de roww en la pagina
    private int maxPages;//cantidad de paginas
    private boolean paginado = true;//
    private int rowCount;//cantidad de registros en total
    private int fastStep;//paso del salto
    private int rowCountMax;//cantidad maxima de registro 50
    
    public Paginator() {
        super();
        clear();
        pageSize = 20;
    } 
    
    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public void incrementPage() 
    {
        pageNumber++;
    }
    public void decrementPage()
    {
        
        pageNumber--;
    }
    public void incrementPageFastStep() 
    {
        
        pageNumber  += fastStep;
    }
    public void decrementPageFastStep() 
    {
        
        pageNumber  -= fastStep;
    }
    public int getPageSize() {
        return pageSize;
    }


    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    // cantidad de paginas en total
    public int getPageCant() {
        int pageCant=0;
        
            if(pageSize!=0){
                if((rowCount%pageSize) == 0){
            
                    pageCant=rowCount/pageSize;
            
                }else{
            
                    pageCant=(rowCount/pageSize)+1;
                }
            }

        return pageCant;
    }
    
    public int getMaxPages(){
        return maxPages;
    }
    public void setMaxPages(int p){
        maxPages=p;
    }
    
//  muestra las filas que se estan mostrando que van desde 0 hasta pageSize
    public int getPageActual(){
        
        if(rowCount!=0){
            if(rowCount>(pageNumber+1)*pageSize){
                return pageSize;
            }else{
                return pageSize-((pageNumber+1)*pageSize-rowCount); 
            }
        }else{
            return 0;
        }
    }
    
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;  
    }
    
    //cantidad de filas en total encontradas
    public int getRowCount() {
        return rowCount;
    }
    
    public int getRowCountMax() {
        return rowCountMax;
    }

    public void setRowCountMax(int rowCountMax) {
        this.rowCountMax = rowCountMax;
    }

    // es el numero de la fila en la que empieza a mostrar
    public long getNumActual(){
        
        if(getPageCant()==0){
            return 0;
        }else{
            return pageNumber*pageSize+1;
        }
    }

    public boolean isPaginado() {
        return paginado;
    }

    public void setPaginado(boolean paginado) {
        this.paginado = paginado;
    }

    public void inicializar(HqlBuilder builder) {
        
        this.rowCount=builder.getRowCount();
        
        if(!this.paginado){
            this.pageSize=this.rowCount;
        }
        else {
            if(this.pageSize<0){
                this.pageSize=0;
            }else if(this.pageSize>rowCountMax){
                this.pageSize=rowCountMax;
            }
        }

    }
    //Este borrar, es solo para la demo
//    public void inicializar() {
//        
//        this.rowCount=14;
//        
//        if(!this.paginado){
//            this.pageSize=this.rowCount;
//        }
//        
//        if(this.pageSize<0){
//            this.pageSize=0;
//        }else if(this.pageSize>rowCountMax){
//            this.pageSize=rowCountMax;
//        }
//
//    }
    
    public void clear(){
        pageNumber=0;
        maxPages=5;
        paginado=true;
        rowCount=0;
        rowCountMax=50;
    }

    public int getFastStep() {
        return fastStep;
    }

    public void setFastStep(int fastStep) {
        this.fastStep = fastStep;
    }
    
    public boolean isNextPage() {
        return getPageCant()-1>getPageNumber();
    }

    public boolean isPreviousFastStep() {
        return getPageNumber() >= getFastStep();
    }

    public boolean isNextFastStep() {
        return getPageCant()-1>getPageNumber() * getFastStep();
    }

    public boolean isPreviousPage() {
        return getPageNumber()>0;
    }
}