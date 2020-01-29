

public class CommandCreator {
	
	String gitPrefix = "git clone ";
	String sshStartsWith = "https://tfs/tfs/VK/";
	String underGit = "_git/";
	
	

	public void cloneCreator(String proName, String dirName){
		
			try {
				System.out.println(gitPrefix+sshStartsWith+underGit+dirName);
				Process p = Runtime.getRuntime().exec(gitPrefix+sshStartsWith+underGit+dirName+"\"");
				p.waitFor();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}		
	}


}
