package entities;

import java.util.ArrayList;
import java.util.Iterator;

public class User
{
  private String username;
  private String password;
  
  public User (String username)
  {
    this.username = username;
    this.password = "";
  }

  public String getUsername ()
  {
    return username;
  }

  @Override
  public String toString ()
  {
    return "User [username=" + username + "]";
  }

  static User searchUser (String username, ArrayList <User> users)
	{
		Iterator <User> iterator = users.iterator();
	    User user;
	    
	    while (iterator.hasNext())
	    {
	    	user = iterator.next();
	    	if (username.equals(user.getUsername())) return user;
	    }
		return null;
	}

  static void createUser (ArrayList <User> users)
  {
    System.out.print("# Criar conta de usuário #" +
					   "\n  Username: ");
		String username = Main.read.nextLine();
		
		if (User.searchUser(username, users) != null)
		{
			System.out.println("  Username indisponível!");
			return;
		}
		
		User user = new User(username);
		
		users.add(user);
		
		System.out.println("  Conta de usário criada!");
		
		while(user.access(users));
  }

  boolean access (ArrayList <User> users)
	{
		System.out.println("  [Usuário: " + this.getUsername() + "]");
		System.out.print("  Selecione:" +
                   "\n    1) resumo*;" +
                   "\n    2) projetos*;" + // (projects.size() > 0 ? "*": "") +
                   "\n    3) " + (password.equals("") ? "criar": "alterar") + " senha*;" +
                   "\n    4) remover*;" +
                   "\n    0) Sair." +
                   "\n    >> ");

		try 
		{
			switch(Integer.parseInt(Main.read.nextLine()))
			{
				case 0: return false;
				case 1: /*show()*/; return true;
				case 2: /*projects()*/; return true;
				case 3: /*changePassword()*/; return false;	
				case 4: /*remove(users)*/; return false;
				default: System.out.println("    Opção inexistente!"); return true;
			}
		}
		catch (Exception exception)
		{
				System.out.println("    Valor inválido!");
		}
		return true;
	}
  
  static void showUsersIn (ArrayList <User> users)
	{
		users.forEach(user -> System.out.println(user.toString()));
	}

  boolean addTo (ArrayList <User> users)
	{
    return users.add(this);
	}

}