package edu.utn.frm.action.file;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.filechooser.FileFilter;
 
public class FileExtensionFilter extends FileFilter {
    private ArrayList<String> mExtensionList;
    private String mDesc;
    
    public FileExtensionFilter (String extension) {
        mExtensionList = new ArrayList<String>(1);
        mExtensionList.add(extension);
    }
    
    public FileExtensionFilter (String extension, String description) {
        this(extension);
        setDescription(description);
    }
    
    public FileExtensionFilter(List<String> extensions) {
        mExtensionList = new ArrayList<String>(extensions.size());
        mExtensionList.addAll(extensions);
    }
    
    public FileExtensionFilter(List<String> extensions, String description) {
        this(extensions);
        setDescription(description);
    }
    
    public FileExtensionFilter(String[] extensions) {
        mExtensionList = new ArrayList<String>();
        for (int i =0; i < extensions.length; i++) {
            mExtensionList.add(extensions[i]);
        }
    }
 
    public FileExtensionFilter(String[] extensions, String description) {
        this(extensions);
        setDescription(description);
    }
    
    public void addExtension(String extension) {
        mExtensionList.add(extension);
    }
    
    public Iterator<String> getExtensions() {
        Iterator<String> i = Collections.unmodifiableList(mExtensionList).iterator();
        return i;
    }
    
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }
        String name = file.getName();
        //System.out.println("name = " + name);
        int idx = name.indexOf(".");
        String type = "";
        if (idx != -1) {
            type = name.substring(idx + 1); 
        }
        type = type.toLowerCase();
        return mExtensionList.contains(type);
    }
    
    public void setDescription(String desc) {
        mDesc = desc;
    }
    
    public String getDescription() {
        if (mDesc == null) {
            mDesc = "";
            mDesc+= "Archivos ";
            for (int i = 0; i < mExtensionList.size(); i++) {
                mDesc += (i != 0 ? ", " : "") + "." + mExtensionList.get(i).toString();
            }
        }
        return mDesc;
    }
    
    public String getType () {
        return mExtensionList.get(0).toString();
    }
    
    
    
    public static class HTMLFilter extends FileExtensionFilter {
        public HTMLFilter () {
            super(new String[] {"html", "htm"});
            setDescription("Archivos de Pagina Web");
        }
    }
    
    public static class RTFFilter extends FileExtensionFilter {
        public RTFFilter () {
            super("rtf");
            setDescription("Archivos de Formato de Texto Enriquecido");
        }
    }
    
    public static class JPEGFilter extends FileExtensionFilter {
        public static final String TYPES[] = new String[] {"jpg", "jpeg"}; 
        public JPEGFilter() {
            super(TYPES);
            setDescription("Imagenes JPEG");
        }
    }
 
    public static class ImageFilter extends FileExtensionFilter {
        public static final String TYPES[] = new String[] {"jpg", "jpeg", "bmp", "gif", "png"}; 
        public ImageFilter() {
            super(TYPES);
            setDescription("Archivos de Imagen");
        }
    }
 
    public static class TextFilter extends FileExtensionFilter {
        public static final String TYPES[] = new String[] {"txt", "text", "java"}; 
        
        public TextFilter () {
            super(TYPES);
            setDescription("Archivos de Texto");
        }
    }
    
    public static class XmlFilter extends FileExtensionFilter {
        public static final String TYPES[] = new String[] {"xml"}; 
        
        public XmlFilter () {
            super(TYPES);
            setDescription("Archivos XML (*.xml)");
        }
    }

    public static class PdfFilter extends FileExtensionFilter {
        public static final String TYPES[] = new String[] {"pdf"};

        public PdfFilter () {
            super(TYPES);
            setDescription("Archivos Adobe PDF (*.pdf)");
        }
    }
}