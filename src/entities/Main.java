package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
  static Scanner read;
  // static String tab = "  ";
  static ArrayList <User> users = new ArrayList<User>();
  static ArrayList <Project> projects = new ArrayList<Project>();
  static ArrayList <Activity> activities = new ArrayList<Activity>();

  public static void main (String[] args)
	{
    new Main().doMain(args); // turn the static method into an instance of Main
	}

  private void doMain (String[] args)
	{
    read = new Scanner(System.in);
    while (mainMenu());
    read.close();
  }

  private boolean mainMenu ()
  {
    System.out.println("[menu_principal]");
    System.out.print("Selecione:" +
                   "\n  1) usuários;" +
                   "\n  2) projetos*;" +
                   "\n  3) atividades*;" +
                   "\n  0) Sair." +
                   "\n  >> ");
    
    try 
    {
      switch(Integer.parseInt(Main.read.nextLine()))
      {
        case 0: System.out.println("Saindo..."); return false;
        case 1: while (User.menu()); return true;
        case 2: while (Project.menu()); return true;
        //case 3: while (activitiesMenu()); return true;
        default: System.out.println("  Opção inexistente!"); return true;
      }
    }
    catch (Exception exception)
    {
      System.out.println("  Valor inválido!");
      return true;
    }
  }

}