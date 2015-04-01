import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;
 
public class FileCrawler {
 
  private String fileNameToSearch;
  private List<String> result = new ArrayList<String>();
 
  public String getFileNameToSearch() {
	return fileNameToSearch;
  }
 
  public void setFileNameToSearch(String fileNameToSearch) {
	this.fileNameToSearch = fileNameToSearch;
  }
 
  public List<String> getResult() {
	return result;
  }
 
  public static void main(String[] args) {
 
	FileCrawler fileSearch = new FileCrawler();
 
        //try different directory and filename :)
	 
    File[] paths;
    paths = File.listRoots();
    for(File path:paths)
    {
    	System.out.println("Searching in..");
       System.out.println(path);
    
     String str =   path.toString();
	
	File dir = new File(str);
    File[] files = dir.listFiles();
    FileFilter fileFilter = new FileFilter() {
       public boolean accept(File file) {
          return file.isDirectory();
       }
    };
    files = dir.listFiles(fileFilter);
    System.out.println(files.length);
    if (files.length == 0) {
       System.out.println("Either dir does not exist or is not a directory");
    }
    else {
       for (int i=0; i< files.length; i++) {
          File filename = files[i];
          System.out.println(filename.toString());
        if( ! filename.toString().contains("C:")){
        	
        	//System.out.println("came here\n");
	fileSearch.searchDirectory(new File(filename.toString()), "iqra.txt");
 
	int count = fileSearch.getResult().size();
	if(count ==0){
	    System.out.println("\nNo result found!");
	}else{
	    System.out.println("\nFound " + count + " result!\n");
	    for (String matched : fileSearch.getResult()){
		System.out.println("Found : " + matched);
	    }
	}
       }
    }
    }
  }
  }
 
  public void searchDirectory(File directory, String fileNameToSearch) {
 
	setFileNameToSearch(fileNameToSearch);
 
	if (directory.isDirectory()) {
	    search(directory);
	} else {
	    System.out.println(directory.getAbsoluteFile() + " is not a directory!");
	}
 
  }
 
  private void search(File file) {
 
	if (file.isDirectory()) {
	  System.out.println("Searching directory ... " + file.getAbsoluteFile());
 
            //do you have permission to read this directory?	
	    if (file.canRead()) {
	    	try {
		for (File temp : file.listFiles()) {
		    if (temp.isDirectory()) {
			search(temp);
		    } else {
			if (getFileNameToSearch().equals(temp.getName().toLowerCase())) {			
			    result.add(temp.getAbsoluteFile().toString());
		    }
 
		}
	    }
	    	}
	    	catch (NullPointerException npe) {
	    	    
	    	}
 
	 } else {
		System.out.println(file.getAbsoluteFile() + "Permission Denied");
	 }
      }
 
  }
 
}
