package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
  static Scanner read;
  // static String tab = "  ";
  ArrayList <User> users = new ArrayList<User>();

  public static void main (String[] args)
	{
    new Main().doMain(args); // turn the static method into an instance of Main
	}

  private void doMain (String[] args)
	{
    read = new Scanner(System.in);
    while(menuCRUD());
    read.close();
  }

  private boolean menuCRUD ()
  {
    System.out.println("[menuCRUD]");
    System.out.print("Selecione:" +
                   "\n  1) usuários;" +
                   "\n  2) projetos;" +
                   "\n  3) atividades;" +
                   "\n  0) Sair." +
                   "\n  >> ");
    
    try 
    {
      switch(Integer.parseInt(Main.read.nextLine()))
      {
        case 0: System.out.println("Saindo..."); return false;
        case 1: users(); return true;
        case 2: projects(); return true;
        case 3: activities(); return true;
        default: System.out.println("  Opção inexistente!"); return true;
      }
    }
    catch (Exception exception)
    {
      System.out.println("  Valor inválido!");
      return true;
    }
  }

  private void users ()
  {
    while(menuEditUsers());
  }

  private boolean menuEditUsers ()
  {
    System.out.println("  [usersCRUD]");
    System.out.print("  Selecione:" +
                   "\n    1) criar usuário;" +
                   "\n    2) pesquisar usuário;" +
                   "\n    3) listar usuários;" +
                   "\n    0) Voltar." +
                   "\n    >> ");
    
    try 
    {
      switch(Integer.parseInt(Main.read.nextLine()))
      {
        case 0: System.out.println("  Voltando...");; return false;
        case 1: User.createUser(users); return true;
        case 2: searchUser(); return true;
        case 3: User.showUsersIn(users);; return true;
        default: System.out.println("  Opção inexistente!"); return true;
      }
    }
    catch (Exception exception)
    {
      System.out.println("  Valor inválido!");
      return true;
    }
  }

  private void searchUser ()
  {}

  private void projects ()
  {
    System.out.println("Projects...");
  }

  private void activities ()
  {
    System.out.println("Activities...");
  }

}