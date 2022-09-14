package entities;

import java.util.ArrayList;
import java.util.Iterator;

public class User
{

	public enum UserType
	{
		GRA("Graduando"),
		MES("Mestrando"),
		DOU("Doutorando"),
		PRO("Professor"),
		PES("Pesquisador"),
		DES("Desenvolvedor"),
		TES("Testador"),
		ANA("Analista"), 
		TEC("Técnico");

		private final String description;

		UserType (String description)
		{
      this.description = description;
    }
	}
	private UserType userType;
  
	private String email;
  private String password;

	ArrayList <Project> projects;
  
  User (UserType userType, String email, String password)
  {
		this.userType = userType;
    this.email = email;
    this.password = password;
		this.projects = new ArrayList<Project>();
  }

	public String getEmail ()
	{
		return email;
	}

  @Override
	public String toString ()
	{
		return "User [email = " + email + ", userType = " + userType.description + ", password = " + password + "]";
	}


  static User searchUser (String email, ArrayList <User> users)
	{
		Iterator <User> iterator = users.iterator();
	    User user;
	    
	    while (iterator.hasNext())
	    {
	    	user = iterator.next();
	    	if (email.equals(user.email)) return user;
	    }
		return null;
	}

	private static void searchUser (ArrayList <User> users)
	{
		System.out.print("# Pesquisar usuário #" +
						 "\n  Email: ");
		User user = searchUser(Main.read.nextLine(), users);
		if (user == null)
		{
			System.out.println("  Email não cadastrado!");
			return;
		}
		user.show();
	}

	private static void showUsersIn (ArrayList <User> users)
	{
		users.forEach(user -> System.out.println(user.toString()));
	}

  private static void createUser (ArrayList <User> users)
  {
    System.out.print("# Criar conta de usuário #" +
					   "\n  Email: ");
		String email = Main.read.nextLine();
		
		if (User.searchUser(email, users) != null)
		{
			System.out.println("  Email já cadastrado!");
			return;
		}

		System.out.print("  [");
		for (UserType userType: UserType.values())
		{
			System.out.print(userType.ordinal() + "=" + userType.description + " ");
		}
		System.out.print("\b]");

		boolean bool;
		UserType userType = null;
		do
		{
			System.out.print("\n  Tipo de usuário: ");
			try 
			{
				userType = UserType.values()[Integer.parseInt(Main.read.nextLine())];
				bool = false;
			}
			catch (Exception exception)
			{
				System.out.print("  Valor inválido!");
				bool = true;
			}
		}
		while (bool);

		User user = new User(userType, email, null);

		users.add(user);
		
		System.out.println("  Conta de usuário criada!");
		
		while (user.access(users));
  }
/*
	private static void editUser (ArrayList <User> users)
  {
    System.out.print("# Criar conta de usuário #" +
					   "\n  Email: ");
		String email = Main.read.nextLine();
		
		if (User.searchUser(email, users) != null)
		{
			System.out.println("  Email já cadastrado!");
			return;
		}

		System.out.print("  [");
		for (UserType userType: UserType.values())
		{
			System.out.print(userType.ordinal() + "=" + userType.description + " ");
		}
		System.out.print("\b]");

		boolean bool;
		UserType userType = null;
		do
		{
			System.out.print("\n  Tipo de usuário: ");
			try 
			{
				userType = UserType.values()[Integer.parseInt(Main.read.nextLine())];
				bool = false;
			}
			catch (Exception exception)
			{
				System.out.print("  Valor inválido!");
				bool = true;
			}
		}
		while (bool);

		User user = new User(userType, email, null);

		users.add(user);
		
		System.out.println("  Conta de usuário criada!");
		
		while (user.access(users));
  }
*/

  private static void accessUser (ArrayList <User> users)
  {
    System.out.print("# Acessar conta de usuário #" +
					   "\n  Email: ");
		User user = searchUser(Main.read.nextLine(), users);
		if (user == null)
		{
			System.out.println("  Email não cadastrado!");
			return;
		}

		if (user.checkPassword())
    {
      while (user.access(users));
    }    
  }

	boolean access (ArrayList <User> users)
	{
		System.out.println("  [Usuário: " + this.email + "]");
		System.out.print("  Selecione:" +
                   "\n    1) resumo;" +
                   "\n    2) projetos" + (projects.size() > 0 ? " [" + projects.size() + "]": "") + ";" +
									 "\n    3) " + (password == null ? "criar": "alterar") + " senha;" +
                   "\n    4) remover*;" +
                   "\n    0) Sair." +
                   "\n    >> ");

		try 
		{
			switch(Integer.parseInt(Main.read.nextLine()))
			{
				case 0: return false;
				case 1: show(); return true;
				case 2: while (projects()); return true;
				case 3: changePassword(); return false;	
				case 4: remove(users); return false;
				default: System.out.println("    Opção inexistente!"); return true;
			}
		}
		catch (Exception exception)
		{
				System.out.println("    Valor inválido!");
		}
		return true;
	}
	

  static boolean menu ()
  {
    System.out.println("  [menu_usuários]");
    System.out.print("  Selecione:" +
                   "\n    1) listar usuários;" +
                   "\n    2) pesquisar usuário;" +
                   "\n    3) acessar usuário;" +
                   "\n    4) criar usuário;" +
                   "\n    0) Voltar." +
                   "\n    >> ");
    
    try 
    {
      switch(Integer.parseInt(Main.read.nextLine()))
      {
        case 0: System.out.println("  Voltando...");; return false;
        case 1: showUsersIn(Main.users);; return true;
        case 2: searchUser(Main.users); return true;
        case 3: accessUser(Main.users); return true;
        case 4: createUser(Main.users); return true;
        default: System.out.println("  Opção inexistente!"); return true;
      }
    }
    catch (Exception exception)
    {
      System.out.println("  Valor inválido!");
      return true;
    }
  }


	private boolean projects ()
	{
	System.out.println("  [Projetos de " + this.email + "]");
	System.out.print("  Selecione:" +
								 "\n    1) listar;" +
								 "\n    2) criar projeto;" +
								 "\n    3) editar projeto;" +
								 "\n    0) Voltar." +
								 "\n    >> ");

	try 
	{
		switch(Integer.parseInt(Main.read.nextLine()))
		{
			case 0: System.out.println("  Voltando..."); return false;
			case 1: Project.showProjectsIn(projects); return true;
			case 2: Project.createProject(this, projects); return true;
			//case 3: Project.editProject(); return true;
			default: System.out.println("    Opção inexistente!"); return true;
		}
	}
	catch (Exception exception)
	{
			System.out.println("    Valor inválido!");
	}
	return true;
}


	public void show ()
	{
		System.out.println(this.toString());
	}


	public boolean checkPassword ()
	{
		System.out.print("  Senha: ");
		if (password == null) 
		{
			System.out.println("null");
			return true;
		}
		if (password.equals(Main.read.nextLine()))
		{	
			return true;
		}
		System.out.println("  Senha incorreta!");
		return false;
	}

	public boolean checkPermission ()
  {
    if (userType == UserType.PRO)
    {
			return true;
		}
		if (userType == UserType.PES)
    {
			return true;
		}
    return false;
  }


	private void changePassword ()
	{
    System.out.print("# " + (password == null ? "Criar": "Alterar") + " senha de usuário #" +
					   "\n  Nova senha: ");
		this.password = Main.read.nextLine();
		System.out.println("  Nova senha cadastrada!");
	}

	private void remove (ArrayList <User> users)
	{
		System.out.print("    Confirme digitando \"" + this.email + "\" novamente: ");
		if (this.email.equals(Main.read.nextLine()))
		{
			users.remove(this);
			System.out.println("    Conta de usuário removida!");
			return;
		}
		
		System.out.println("    Conta de usuário não removida!");

		// users.forEach(user ->
		// {
		// 	user.removeFriend(this);
		// 	user.removeInvitation(this);
		// });

		// Iterator <Message> iterator = messages.iterator();
		
		// while (iterator.hasNext())
		// {
		// 	Message message = iterator.next();
		// 	if (message.isRelated(this))
		// 	{
		// 		users.forEach(user -> { user.getFeed().removeMessage(message); });
		// 		iterator.remove();
		// 	}
		// }
	}

	/*
  boolean selfAddTo (ArrayList <User> users)
	{
    return users.add(this);
	}
  */

}